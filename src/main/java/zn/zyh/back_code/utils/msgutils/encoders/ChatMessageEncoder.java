package zn.zyh.back_code.utils.msgutils.encoders;

import com.fasterxml.jackson.core.JsonGenerator;
import zn.zyh.back_code.utils.msgutils.messages.ChatMessage;

import java.io.StringWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

/* Encode a ChatMessage as JSON.
 * For example, (new ChatMessage("Peter","Duke","How are you?"))
 * is encoded as follows:
 * {
 *   "type": "chat",
 *   "target": "Duke",
 *   "message": "How are you?"
 * }
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
//        StringWriter swriter = new StringWriter();
//        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
//            jsonGen.writeStartObject()
//                    .write("type", "chat")
//                    .write("name", chatMessage.getName())
//                    .write("target", chatMessage.getTarget())
//                    .write("message", chatMessage.getMessage())
//                    .writeEnd();
//        }
//        return swriter.toString();
        JSONObject jsonObject=new JSONObject();
        try{

            jsonObject.put("type","chat");
            jsonObject.put("name",chatMessage.getName());
            jsonObject.put("target",chatMessage.getTarget());
            jsonObject.put("message",chatMessage.getMessage());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
