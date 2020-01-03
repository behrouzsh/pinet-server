package edu.uc.eh.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by shamsabz on 7/19/17.
 */

@Service
public class EnrichrPostService2 {

    private static final Logger log = LoggerFactory.getLogger(EnrichrPostService2.class);
    static URL u;
    @Value("${urls.pir}")
    String pirTemplate;
    private final String USER_AGENT = "Mozilla/5.0";

    public String[] getTable(String[] genes) throws IOException {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());




//
//
//
//
//
//        String s=URLEncoder.encode("A Test string to send to a servlet");
//
//        try
//        {
//            HTTP_post post = new HTTP_post();
//            post.u = new URL("http://myhost/servlet");
//
//            // Open the connection and prepare to POST
//            URLConnection uc = u.openConnection();
//            uc.setDoOutput(true);
//            uc.setDoInput(true);
//            uc.setAllowUserInteraction(false);
//
//            DataOutputStream dstream = new DataOutputStream(uc.getOutputStream());
//
//            // The POST line
//            dstream.writeBytes(s);
//            dstream.close();
//
//            // Read Response
//            InputStream in = uc.getInputStream();
//            int x;
//            while ( (x = in.read()) != -1)
//            {
//                System.out.write(x);
//            }
//            in.close();
//
//            BufferedReader r = new BufferedReader(new InputStreamReader(in));
//            StringBuffer buf = new StringBuffer();
//            String line;
//            while ((line = r.readLine())!=null) {
//                buf.append(line);
//            }
//
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();	// should do real exception handling
//        }
//
//
////    String json = "{foo: 123, bar: \"hello\"}";
////
////    DefaultHttpClient httpclient = new DefaultHttpClient();
////    HttpPost httpPost = new HttpPost("http://posttestserver.com/post.php");
////        try {
////            httpPost.setEntity(new StringEntity(json));
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
////        HttpResponse response2 = null;
////        try {
////            response2 = httpclient.execute(httpPost);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        try {
////        System.out.println(response2.getStatusLine());
////        HttpEntity entity2 = response2.getEntity();
////        // do something useful with the response body
////        // and ensure it is fully consumed
////        EntityUtils.consume(entity2);
////    } finally {
////        httpPost.releaseConnection();
////    }
//
//
//    HttpClient httpclient = HttpClients.createDefault();
//    HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
//
//    // Request parameters and other properties.
//    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//    params.add(new BasicNameValuePair("param-1", "12345"));
//    params.add(new BasicNameValuePair("param-2", "Hello!"));
//    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//
//    //Execute and get the response.
//    HttpResponse response = httpclient.execute(httppost);
//    HttpEntity entity = response.getEntity();
//
//if (entity != null) {
//        InputStream instream = entity.getContent();
//        try {
//            // do something usefu
//        } finally {
//            instream.close();
//        }
//    }
//
//
//
//
//
//
//
//
//    PostMethod post = new PostMethod("http://amp.pharm.mssm.edu/Enrichr/addList");
//    NameValuePair[] data = {
//            new NameValuePair("user", "joe"),
//            new NameValuePair("password", "bloggs")
//    };
//    post.setRequestBody(data);
//// execute method and handle any error responses.
////...
//    InputStream in = post.getResponseBodyAsStream();
//
//
//
//
//
//
//
//    HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
//
//
//
//    HttpPost request = new HttpPost("http://yoururl");
//    StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
//    request.addHeader("content-type", "application/x-www-form-urlencoded");
//    request.setEntity(params);
//    HttpResponse response = httpClient.execute(request);
//
//    public EnrichrPostService2() throws UnsupportedEncodingException {
//    }
//
//    handle response here...
//
//
//
//
//
//
//
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        ArrayList<String> inputList = new ArrayList<String>();
//        inputList.add("BRAF");
//        inputList.add("TP53");
//        JSONArray obj = new JSONArray();
//
//        JSONObject jo = new JSONObject();
//        jo.put("firstName", "John");
//        jo.put("lastName", "Doe");
//
//        JSONArray ja = new JSONArray();
//        ja.add(jo);
//        StringEntity mainObj = new StringEntity();
//        mainObj.
//        //JSONObject mainObj = new JSONObject();
//        //mainObj.put("list", inputList);
//        //obj.add()
//
//        try {
//
//            HttpPost request = new HttpPost("http://amp.pharm.mssm.edu/Enrichr/addList");
//            StringEntity params = mainObj;
//            //StringEntity params =new StringEntity("{\"list\":\"BRAF\"} ");
//            //request.addHeader("content-type", "application/x-www-form-urlencoded");
//            request.setEntity(params);
//            HttpResponse response = httpClient.execute(request);
//            log.info(response.toString());
//
//            //handle response here...
//
//        }catch (Exception ex) {
//
//            //handle exception here
//
//        } finally {
//            //Deprecated
//            //httpClient.getConnectionManager().shutdown();
//        }
//
//
//
////        HttpClient httpclient = HttpClients.createDefault();
////        HttpPost httppost = new HttpPost("http://amp.pharm.mssm.edu/Enrichr/addList");
//
////        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
////
////        JSONObject payload = new JSONObject();
////
////        String genes_str = "BRAF";
////
////        params.add(new BasicNameValuePair("list", genes_str));
////
////
////        payload.put("list",genes_str);
//
//
//
//
//
//
//
////
////        ArrayList<NameValuePair> postParameters;
////        //httpclient = new DefaultHttpClient();
////        //httppost = new HttpPost("your login link");
////
////
////        postParameters = new ArrayList<NameValuePair>();
////        postParameters.add(new BasicNameValuePair("param1", "param1_value"));
////        postParameters.add(new BasicNameValuePair("param2", "param2_value"));
////
////        httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
////
////
////
////
////
////
////
////        //httppost.setEntity((HttpEntity) params);
////        //httppost.setEntity(new UrlEncodedFormEntity((Iterable<? extends NameValuePair>) payload));
////
//////Execute and get the response.
////        HttpResponse response = null;
////        try {
////            response = httpclient.execute(httppost);
////            log.info(response.toString());
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        HttpEntity entity = response.getEntity();
////
////        if (entity != null) {
////            InputStream instream = null;
////            try {
////                instream = entity.getContent();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            try {
////                // do something useful
////            } finally {
////                try {
////                    instream.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////
////
//


        return (genes);
    }
}


