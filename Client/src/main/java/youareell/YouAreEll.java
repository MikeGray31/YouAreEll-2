package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController trCtrl;

    public YouAreEll () throws JsonProcessingException {
        this.trCtrl = new TransactionController();
        this.idCtrl = new IdController(this.trCtrl);
        this.msgCtrl = new MessageController(this.idCtrl, this.trCtrl);
    }

    public String setMyId(String GithubName){
        return idCtrl.setMyId(GithubName);
    }

    public MessageController getMsgCtrl() {
        return msgCtrl;
    }

    public IdController getIdCtrl() {
        return idCtrl;
    }

    public TransactionController getTrCtrl() {
        return trCtrl;
    }

    public String get_ids() {
        return MakeURLCall("/ids", "get", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "get", "");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        return trCtrl.MakeURLCall(mainurl, method, jpayload);
    }

    public String sendMessage(String message) throws IOException {
        idCtrl.getIds();
        String senderId = idCtrl.getMyId().getGithubId();
        if(senderId != null) return sendMessage(message, senderId);
        else return "Please set id with command: ids setCurrent <id>";
    }

    public String sendMessage(String message, String sender) throws IOException {
        idCtrl.getIds();
        if(idCtrl.getIdByGithubName(sender) != null){
            return new MessageTextView(msgCtrl.postMessage(idCtrl.getIdByGithubName(sender),null, message)).toString();
        }
        else return "Sender not found";

    }

    public String sendMessage(String message, String sender, String receiver) throws IOException {
        idCtrl.getIds();
        return null;
    }

    public String view_all_ids() throws JsonProcessingException {
        return IdTextView.printIds(idCtrl.getIds());
    }

    public String getId(String gitName) {
        Id idFound = idCtrl.getIdByGithubName(gitName);
        if (idFound != null) return new IdTextView(idFound).toString();
        else return "ID does not exist";
    }

    public void putOrPostId(String name, String gitName) throws JsonProcessingException {
        if(idCtrl.getIdByGithubName(gitName) == null) idCtrl.postId(new Id(name, gitName));
        else idCtrl.getIdByGithubName(gitName).setName(name);
    }

    public String view_all_messages() throws JsonProcessingException {
        return msgCtrl.printMessages(msgCtrl.getMessages(20,""));
    }

    public String view_messages_to_user(String githubId) throws JsonProcessingException {
        return msgCtrl.printMessages(msgCtrl.getMessages(20,githubId));
    }
}
