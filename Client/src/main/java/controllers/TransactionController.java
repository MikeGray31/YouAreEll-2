package controllers;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;


public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private CloseableHttpClient httpclient;

    public TransactionController() {
        this.httpclient = HttpClients.createDefault();
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        return null;
    }

    public String get(String url) throws IOException {
        return null;
    }

    public String post(String url, String content) throws IOException {
        return null;
    }

    public String put(String url, String content) throws IOException {
        return null;
    }
}
