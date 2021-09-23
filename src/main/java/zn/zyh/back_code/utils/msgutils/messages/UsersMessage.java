package zn.zyh.back_code.utils.msgutils.messages;

import java.util.List;

public class UsersMessage extends Message{
    private List<String> userlist;

    public UsersMessage(List<String> userlist) {
        this.userlist = userlist;
    }

    public List<String> getUserList() {
        return userlist;
    }

    /* For logging purposes */
    @Override
    public String toString() {
        return "[UsersMessage] " + userlist.toString();
    }
}
