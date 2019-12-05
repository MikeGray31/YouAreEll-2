package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import models.Id;
import models.Message;

public class MessageController {

    private IdController idCtrl;
    private TransactionController trCtrl;

    public MessageController(IdController idCtrl, TransactionController trCtrl){
        this.idCtrl = idCtrl;
        this.trCtrl = trCtrl;
    }

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        return null;
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}