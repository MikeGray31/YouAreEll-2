package views;

import models.Message;

public class MessageTextView {

    private Message msgToDisplay;

    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;
    }

    @Override public String toString() {

        return String.format("From: %s\n" +
                        "To: %s\n" +
                        "Date: %s\n" +
                        "Message: %s\n\n",
                        this.msgToDisplay.getFromId(),
                        this.msgToDisplay.getToId(),
                        this.msgToDisplay.getTimestamp(),
                        this.msgToDisplay.getMessage());
    }
}