package controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {

    private Id myId;
    private ArrayList<Id> storedIds;
    private TransactionController transactionController;

    public IdController(TransactionController transactionController) throws JsonProcessingException {
        this.storedIds = new ArrayList<>();
        this.myId = null;
        this.transactionController = transactionController;
        getIds();
    }

    public ArrayList<Id> getIds() {
        String result = transactionController.MakeURLCall("/ids", "GET", "");
        ObjectMapper mapper = new ObjectMapper();

        return null;
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }

    public void setMyId(Id myId) {
        this.myId = myId;
    }

    public Id getMyId() {
        return myId;
    }


}