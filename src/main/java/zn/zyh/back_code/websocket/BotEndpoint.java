package zn.zyh.back_code.websocket;

import org.springframework.stereotype.Component;
import zn.zyh.back_code.utils.msgutils.decoders.MessageDecoder;
import zn.zyh.back_code.utils.msgutils.encoders.*;
import zn.zyh.back_code.utils.msgutils.messages.*;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value = "/chatRoom",
        decoders = { MessageDecoder.class },
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class,
                InfoMessageEncoder.class, UsersMessageEncoder.class }
)
@Component
public class BotEndpoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    private static Queue<Session> mySession = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void openConnection(Session session) {
        mySession.add(session);
        logger.log(Level.INFO, "Connection opened.");
    }
    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());

        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getName());
            session.getUserProperties().put("active", true);
            logger.log(Level.INFO, "Received: {0}", jmsg.toString());
            sendAll(session, new InfoMessage(jmsg.getName() + " has joined the chat"));
            sendAll(session, new ChatMessage("Duke", jmsg.getName(), "Hi there!!"));
            sendAll(session, new UsersMessage(this.getUserList(session)));

        } else if (msg instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage cmsg = (ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());
            sendAll(session, cmsg);
        }
    }
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session, new InfoMessage(name + " has left the chat"));
            sendAll(session, new UsersMessage(this.getUserList(session)));
        }
        logger.log(Level.INFO, "Connection closed.");
    }
    @OnError
    public void error(Session session, Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }

    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : mySession){//session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        //users.add("Duke");
        for (Session s : mySession){//session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
