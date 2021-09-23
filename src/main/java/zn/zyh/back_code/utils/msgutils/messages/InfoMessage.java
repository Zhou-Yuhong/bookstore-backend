package zn.zyh.back_code.utils.msgutils.messages;

/* Represents an information message, like
 * an user entering or leaving the chat */
public class InfoMessage extends Message{
    
    public String info;
    
    public InfoMessage(String info){this.info=info;}
    
    public String getInfo(){
        return this.info;
    }
    
    @Override
    public String toString() {return "[InfoMessage]"+info;}
}
