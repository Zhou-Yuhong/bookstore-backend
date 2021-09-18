package zn.zyh.back_code.constant;

public class RabbitmqConstant {
    //topic交换机的名字
    public static final String TOPIC_EXCHANGE_NAME="topic.bookstore";
    //queue的名字
    public static final String QUEUE_ADD_BOOK_NAME="queue.addBook";
    public static final String QUEUE_ADD_ORDER_NAME="queue.addOrder";
    //queue绑定topic的名字
    public static final String QUEUE_ADD_BOOK_BINDING_KEY="binding.addBook";
    public static final String QUEUE_ADD_ORDER_BINDING_KEY="binding.addOrder";
}
