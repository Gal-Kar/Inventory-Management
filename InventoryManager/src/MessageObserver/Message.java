package MessageObserver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String msg;
    private LocalDateTime sentTime;

    public Message(String msg) {
        this.msg = msg;
        sentTime=LocalDateTime.now();

    }

    public String getMsg() {
        return msg;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return
                dtf.format(sentTime) + " " +msg;
    }
}
