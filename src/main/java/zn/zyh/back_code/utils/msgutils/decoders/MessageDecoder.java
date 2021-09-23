package zn.zyh.back_code.utils.msgutils.decoders;

import zn.zyh.back_code.utils.msgutils.messages.*;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.io.StringReader;
import java.util.*;

public class MessageDecoder implements Decoder.Text<Message> {
    /* Stores the name-value pairs from a JSON message as a Map */
//    private Map<String,String> messageMap;
    private String type;
    private String name;
    private String target;
    private String message;
    private String info;
    private List<String> userlist=new ArrayList<>();
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    /* Create a new Message object if the message can be decoded */
    @Override
    public Message decode(String string) throws DecodeException {
        Message msg=null;
        if(willDecode(string)){
            switch (type){
                case "join":
                    msg=new JoinMessage(name);
                    break;
                case "chat":
                    msg=new ChatMessage(name,target,message);
                    break;
                case "user":
                    msg=new UsersMessage(userlist);
                    break;
                case "info":
                    msg=new InfoMessage(info);
                    break;
            }
        }else {
            throw new DecodeException(string, "[Message] Can't decode.");
        }
        return msg;

    }
    @Override
    public boolean willDecode(String string){
        JSONObject jsonObject=JSONObject.parseObject(string);
        boolean result=true;
        try {
            type=jsonObject.getString("type");
            switch (type){
                case "join":
                    this.name=jsonObject.getString("name");
                    break;
                case "chat":
                    this.name=jsonObject.getString("name");
                    this.target=jsonObject.getString("target");
                    this.message=jsonObject.getString("message");
                    break;
                case "user":
                    JSONArray jsonArray=jsonObject.getJSONArray("userlist");
                    this.userlist.clear();
                    for(Object it:jsonArray){
                       this.userlist.add((String)it);
                    }
                    break;
                case "info":
                    info=jsonObject.getString("info");
                    break;

            }

        }catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    /* Decode a JSON message into a Map and check if it contains
     * all the required fields according to its type. */
//    @Override
//    public boolean willDecode(String string) {
//        boolean decodes = false;
//        /* Convert the message into a map */
//        messageMap = new HashMap<>();

//        JsonParser parser = Json.createParser(new StringReader(string));
//        while (parser.hasNext()) {
//            if (parser.next() == JsonParser.Event.KEY_NAME) {
//                String key = parser.getString();
//                parser.next();
//                String value = parser.getString();
//                messageMap.put(key, value);
//            }
//        }
//        /* Check the kind of message and if all fields are included */
//        Set keys = messageMap.keySet();
//        if (keys.contains("type")) {
//            switch (messageMap.get("type")) {
//                case "join":
//                    if (keys.contains("name"))
//                        decodes = true;
//                    break;
//                case "chat":
//                    String[] chatMsgKeys = {"name", "target", "message"};
//                    if (keys.containsAll(Arrays.asList(chatMsgKeys)))
//                        decodes = true;
//                    break;
//            }
//        }
//        return decodes;
//        JSONObject jsonObject=JSONObject.parseObject(string);
//        boolean decodes = true;
//        try{
//            String type
//
//        }catch (Exception e){
//            e.printStackTrace();
//            decodes = false;
//        }
//
//
//    }
}
