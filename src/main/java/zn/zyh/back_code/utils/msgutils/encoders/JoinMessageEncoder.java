package zn.zyh.back_code.utils.msgutils.encoders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import zn.zyh.back_code.utils.msgutils.messages.JoinMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.alibaba.fastjson.JSONObject;
import java.io.StringWriter;

public class JoinMessageEncoder implements Encoder.Text<JoinMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(JoinMessage joinMessage) throws EncodeException {
//        StringWriter swriter = new StringWriter();
//        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
//            jsonGen.writeStartObject()
//                    .write("type", "join")
//                    .write("name", joinMessage.getName())
//                    .writeEnd();
//        }
//        return swriter.toString();
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("type","join");
            jsonObject.put("name",joinMessage.getName());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
