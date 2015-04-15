package br.com.myowncompany.cadastrocontatos;

import android.util.Log;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5193 on 14/04/15.
 */
public class WebClient {
    private static final String url = "http://www.caelum.com.br/mobile";
    public String post(String jsonMsg){

        String jsonResp = "";

        try {
            HttpPost post = new HttpPost(url);
            post.setHeader("Accept", "application/json");
            post.setHeader("Contenttype", "application/json");

            post.setEntity(new StringEntity(jsonMsg));
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            jsonResp = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }
}
