package model;

import org.simpleframework.xml.Element;

/**
 * Created by bojack on 16/4/26.
 */
public class MailContent {


    @Element
    private String from;
    @Element
    private String to;
    @Element
    private String subject;
    @Element
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
