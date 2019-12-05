package controllers;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private CloseableHttpClient httpclient;

    public TransactionController() {
        this.httpclient = HttpClients.createDefault();
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        try{
            switch (method){
                case "get":
                    return get(mainurl);
                case "put":
                    return put(mainurl, jpayload);
                case "post":
                    return post(mainurl, jpayload);
            }
        }
        catch (IOException e ){
            return e.getMessage();
        }
        return "Encountered error";
    }

    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(this.rootURL + url);
        CloseableHttpResponse response = this.httpclient.execute(httpGet);

        try {
            HttpEntity entity = response.getEntity();
            String result = new BufferedReader(new InputStreamReader(entity.getContent())).lines().collect(Collectors.joining("\n"));
            EntityUtils.consume(entity);
            return result;
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { response.close(); }
        return null;
    }

    public String post(String url, String content) throws IOException {
        String result = "";
        HttpPost httpPost = new HttpPost(this.rootURL + url);
        httpPost.setEntity(new StringEntity(content));
        CloseableHttpResponse response = this.httpclient.execute(httpPost);
        try {
            HttpEntity entity2 = response.getEntity();
            result = EntityUtils.toString(entity2);
            EntityUtils.consume(entity2);
        } finally {
            response.close();
        }
        return result;
    }

    public String put(String url, String content) throws IOException {
        String result = "";
        HttpPut httpPut = new HttpPut(this.rootURL + url);
        httpPut.setEntity(new StringEntity(content));
        CloseableHttpResponse response = this.httpclient.execute(httpPut);
        try {
            HttpEntity entity2 = response.getEntity();
            result = EntityUtils.toString(entity2);
            EntityUtils.consume(entity2);
        } finally {
            response.close();
        }
        return result;
    }
}
