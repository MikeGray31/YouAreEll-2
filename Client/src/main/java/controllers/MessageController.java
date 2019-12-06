package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import views.MessageTextView;

public class MessageController {

    private HashSet<Message> messagesSeen;
    private ArrayList<Message> allMessages;
    private IdController idCtrl;
    private TransactionController trCtrl;

    public MessageController(IdController idCtrl, TransactionController trCtrl){
        this.messagesSeen = new HashSet<>();
        this.allMessages = new ArrayList<>();
        this.idCtrl = idCtrl;
        this.trCtrl = trCtrl;
    }

    public ArrayList<Message> getMessages(int numberOfMessages, String githubId) throws JsonProcessingException {
        getAllMessages();
        if (idCtrl.getIdByGithubName(githubId) == null && !githubId.equals(""))  return new ArrayList<Message>();
        ArrayList<Message> results = getMessagesForId(idCtrl.getIdByGithubName(githubId));
        Collections.sort(results);
        if (numberOfMessages > 0 && numberOfMessages < results.size())  results = new ArrayList<>(results.subList(0, numberOfMessages));
        return results;
    }

    public ArrayList<Message> getAllMessages() throws JsonProcessingException {
        idCtrl.getIds();
        this.allMessages.clear();
        String result = this.trCtrl.MakeURLCall("/messages", "get", "");
        ObjectMapper mapper = new ObjectMapper();
        this.allMessages = mapper.readValue(result, new TypeReference<ArrayList<Message>>() {});
        return this.allMessages;
    }

    public ArrayList<Message> getMessagesForId(Id id) {
        ArrayList<Message> result = new ArrayList<>();
        for(Message m : this.allMessages) {
            if(m.getFromId().equals(id)) result.add(m);
        }
        return result;
    }

    public Message getMessageForSequence(String seq) {
        for (Message m : this.allMessages) {
            if (m.getSequence().equals(seq)) return m;
        }
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        ArrayList<Message> result = new ArrayList<>();
        for(Message m : this.allMessages){
            if(m.getFromId().equals(friendId) && m.getToId().equals(myId)) result.add(m);
        }
        return result;
    }

    public Message postMessage(Id myId, Id toId, String msg) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String toGitId;
        if(toId != null) toGitId = toId.getGithubId();
        else toGitId = "";
        String payload = mapper.writeValueAsString(new Message(myId.getGithubId(),toGitId,msg));
        String url = "/ids/" + myId.getGithubId() + "/messages";
        String result = this.trCtrl.post(url, payload);
        return mapper.readValue(result,Message.class);
    }

    public String printMessages(ArrayList<Message> messages) {
        String output = "";
        for (Message message: messages) {
            output += new MessageTextView(message).toString();
        }
        return output;
    }
}