package ar.edu.unq.obj2.hotels.notifications;

public class Email {
    public final String from;
    public final String to;
    public final String subject;
    public final String body;

    public Email(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String readable(){
        return from+"\n"+to+"\n"+subject+"\n"+body;
    }
}
