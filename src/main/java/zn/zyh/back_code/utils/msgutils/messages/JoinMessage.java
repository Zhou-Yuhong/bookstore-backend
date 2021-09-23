package zn.zyh.back_code.utils.msgutils.messages;

public class JoinMessage extends Message{
    private String name;

    public JoinMessage(String name){ this.name=name; }
    public String getName(){return name;}

    @Override
    public String toString(){return "[JoinMessage]"+name; }
}
