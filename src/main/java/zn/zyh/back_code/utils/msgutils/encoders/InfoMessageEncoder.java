package zn.zyh.back_code.utils.msgutils.encoders;

import zn.zyh.back_code.utils.msgutils.messages.InfoMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
public class InfoMessageEncoder implements Encoder.Text<InfoMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(InfoMessage joinMessage) throws EncodeException {
//        StringWriter swriter = new StringWriter();
//        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
//            jsonGen.writeStartObject()
//                    .write("type", "info")
//                    .write("info", joinMessage.getInfo())
//                    .writeEnd();
//        }
//        return swriter.toString();
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("type","info");
            jsonObject.put("info",joinMessage.getInfo());
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
