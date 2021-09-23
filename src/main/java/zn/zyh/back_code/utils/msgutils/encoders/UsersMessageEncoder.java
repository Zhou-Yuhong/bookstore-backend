package zn.zyh.back_code.utils.msgutils.encoders;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import zn.zyh.back_code.utils.msgutils.messages.UsersMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;
import java.util.List;
public class UsersMessageEncoder implements Encoder.Text<UsersMessage> {
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(UsersMessage usersMessage) throws EncodeException {
//        StringWriter swriter = new StringWriter();
//        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
//            jsonGen.writeStartObject()
//                    .write("type", "users")
//                    .writeStartArray("userlist");
//            for (String user : usersMessage.getUserList())
//                jsonGen.write(user);
//            jsonGen.writeEnd().writeEnd();
//        }
//        return swriter.toString();
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("type","user");
            List<String> users=usersMessage.getUserList();
            jsonObject.put("userlist",users);

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
