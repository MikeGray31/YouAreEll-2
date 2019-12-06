package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {

    @JsonProperty("userid")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("github")
    private String githubId;

    public Id (String name, String githubId) {
        this.name = name;
        this.githubId = githubId;
        this.userId = "N/A";
    }

    public Id (String name, String githubId, String userId) {
        this.name = name;
        this.githubId = githubId;
        this.userId = userId;
    }

    public Id () {
        this.name = null;
        this.githubId = null;
        this.userId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}