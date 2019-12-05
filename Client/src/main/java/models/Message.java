package models;

import java.util.Date;

/*
 * POJO for an Message object
 */
public class Message implements Comparable<Message> {

    private String sequence;
    private Date timestamp;
    private String fromId;
    private String toId;
    private String message;

    public Message (String message, String fromId, String toId, String sequence, Date timestamp) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
        this.sequence = sequence;
        this.timestamp = timestamp;
    }

    public Message (String fromId, String toId, String message) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
        this.sequence = "N/A";
        this.timestamp = null;
    }


    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public String getMessage() {
        return message;
    }

    public String getSequence() {
        return sequence;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "Message: " +
                "\n   sequence = " + sequence  +
                "\n   timestamp = " + timestamp +
                "\n   fromId = " + fromId +
                "\n   toId = " + toId +
                "\n   message = " + message;
    }

    @Override
    public int compareTo(Message message) {
        return this.timestamp.compareTo(message.timestamp);
    }
}