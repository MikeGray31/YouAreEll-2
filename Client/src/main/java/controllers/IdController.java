package controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import views.IdTextView;

public class IdController {

    private Id myId;
    private ArrayList<Id> allIds;
    private TransactionController transactionController;

    public IdController(TransactionController transactionController) throws JsonProcessingException {
        this.allIds = new ArrayList<>();
        this.myId = null;
        this.transactionController = transactionController;
        getIds();
    }

    public ArrayList<Id> getIds() throws JsonProcessingException{
        String output = transactionController.MakeURLCall("/ids", "get", "");
        ObjectMapper mapper = new ObjectMapper();
        this.allIds = mapper.readValue(output, new TypeReference<ArrayList<Id>>() {});
        return this.allIds;
    }

    public String printIds(ArrayList<Id> ids) {
        String output = "";
        for (Id id : ids) {
            output += new IdTextView(id).toString();
        }
        return output;
    }

    public Id getIdByGithubName (String githubName) {
        for (Id id : this.allIds) {
            if (githubName.equals(id.getGithubId())) return id;
        }
        return null;
    }

    public Id postId(Id id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(id);
        String result = transactionController.MakeURLCall("/ids", "post", jsonData);
        System.out.println("response: " + result);
        getIds();
        return getIdByGithubName(id.getGithubId());
    }

    public Id putId(Id id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(id);
        String result = transactionController.MakeURLCall("/ids", "put", jsonData);
        System.out.println("response: " + result);
        getIds();
        return getIdByGithubName(id.getGithubId());
    }

    public void setMyId(Id myId) {
        this.myId = myId;
    }

    public String setMyId(String GithubName){
        Id thisId = getIdByGithubName(GithubName);
        if (thisId != null) {
            setMyId(thisId);
            return ("Local ID Set");
        } else {
            return ("User ID Not Found");
        }
    }

    public Id getMyId() {
        return myId;
    }


}