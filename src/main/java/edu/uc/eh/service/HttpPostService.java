package edu.uc.eh.service;

import org.springframework.stereotype.Service;
import java.net.*;
import java.io.*;
/**
 * Created by shamsabz on 7/20/17.
 */
@Service
public class HttpPostService {


    static URL u;
    public static void doPost()
    {
        String s=URLEncoder.encode("A Test string to send to a servlet");

        try
        {
            HttpPostService post = new HttpPostService();
            post.u = new URL("http://amp.pharm.mssm.edu/Enrichr/addList");

            // Open the connection and prepare to POST
            URLConnection uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setAllowUserInteraction(false);

            DataOutputStream dstream = new DataOutputStream(uc.getOutputStream());

            // The POST line
            dstream.writeBytes(s);
            dstream.close();

            // Read Response
            InputStream in = uc.getInputStream();
            int x;
            while ( (x = in.read()) != -1)
            {
                System.out.write(x);
            }
            in.close();

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuffer buf = new StringBuffer();
            String line;
            while ((line = r.readLine())!=null) {
                buf.append(line);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();	// should do real exception handling
        }
    }
}