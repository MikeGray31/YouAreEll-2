package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
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

//    public static void main(String[] args) {
//        // hmm: is this Dependency Injection?
//        YouAreEll urlhandler = new YouAreEll();
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
//    }
}
