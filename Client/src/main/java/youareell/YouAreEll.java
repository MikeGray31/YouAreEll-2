package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController trCtrl;

    public YouAreEll () throws JsonProcessingException {
        // used j because i seems awkward
        this.trCtrl = new TransactionController();
        this.msgCtrl = new MessageController();
        this.idCtrl = new IdController(trCtrl);
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
        return MakeURLCall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        return trCtrl.MakeURLCall(mainurl, method, jpayload);
    }

//    public static void main(String[] args) {
//        // hmm: is this Dependency Injection?
//        YouAreEll urlhandler = new YouAreEll();
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
//    }
}
