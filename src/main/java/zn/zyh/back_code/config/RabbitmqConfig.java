package zn.zyh.back_code.config;
import static zn.zyh.back_code.constant.RabbitmqConstant.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitmqConfig {
    //定义队列
    @Bean
    public Queue topicAddBook(){ return new Queue(QUEUE_ADD_BOOK_NAME);}
    @Bean
    public Queue topicAddOrder(){ return new Queue(QUEUE_ADD_ORDER_NAME);}

    //定义topic交换机
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }
    //将队列与交换机绑定
    @Bean
    public Binding bindingTopicExchangeWithAddBook(){
        return BindingBuilder.bind(topicAddBook()).to(topicExchange()).with(QUEUE_ADD_BOOK_BINDING_KEY);
    }
    @Bean
    public Binding bindingTopicExchangeWithAddOrder(){
        return BindingBuilder.bind(topicAddOrder()).to(topicExchange()).with(QUEUE_ADD_ORDER_BINDING_KEY);
    }
}
