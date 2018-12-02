package ru.maslenkin.testtaskcft.xmlparser.sending;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.List;

public class Sending {
    public static void send(Document document, String sendURL) {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(sendURL);
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("data", document.asXML()));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpclient.execute(httpPost);
            ((CloseableHttpClient) httpclient).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
