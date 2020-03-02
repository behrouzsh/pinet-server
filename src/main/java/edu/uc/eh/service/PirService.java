package edu.uc.eh.service;


import edu.uc.eh.utils.UtilsNetwork;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Map;

//import java.util.Map;

/**
 * Created by behrouzsh on 7/17/17.
 */

@Service
public class PirService {

    private static final Logger log = LoggerFactory.getLogger(PirService.class);

    @Value("${urls.pir}")
    String pirTemplate;

    public JSONObject getTable(String[] peptide) {

        String response;
        System.out.println(Arrays.toString(peptide));
        JSONObject pirJson = new JSONObject();
        //String xmlResponse;
        //peptide[0] is the peptide, peptide[1] is the organism
        String pirUrl = String.format(pirTemplate, peptide[0], peptide[1]);
        Map pirMap;
        log.info("Querying: " + pirUrl);


        try {
            response = UtilsNetwork.getInstance().readUrlXml(pirUrl);
//            log.info("Response from readXml: ");
//            log.info(response);

            pirJson = UtilsNetwork.loadXMLFromStringPIR(response);
            System.out.println("pirMap:" + pirJson.toString());
        } catch (Exception e) {

            String msg =  String.format("Error is finding Peptide from PIR");
            log.warn(msg);
            throw new RuntimeException(msg);

        }



        //String pirJson = "{\"length\": "+pirMap.get("length").toString()+", \"sequence\": \""+pirMap.get("sequence").toString()+"\"}";

        //JSONObject pirJsonSecond = new JSONObject(pirMap);
        //System.out.println("JsonFormat");
        //System.out.println(pirJsonSecond.toString());

        return pirJson;
        //return pirMap;//.toString();
    }

}
