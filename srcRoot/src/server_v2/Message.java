package server_v2;

public class Message {
    private MessageType type;
    private User user;
    private Object data;

    Message(Object data, User user, MessageType messageType) {
        this.data = data;
        this.type = messageType;
        this.user = user;
    }

    public Message(MessageType type) {
        this(null,null,type);
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
