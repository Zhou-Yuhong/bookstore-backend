package zn.zyh.back_code.producer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static zn.zyh.back_code.constant.RabbitmqConstant.*;
@Component
public class OrderProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendOrder(JSONObject jsonObject){
        this.rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME,QUEUE_ADD_ORDER_BINDING_KEY,jsonObject);
    }
}
