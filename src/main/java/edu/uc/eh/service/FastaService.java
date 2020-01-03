package edu.uc.eh.service;

import edu.uc.eh.peptideMatch.Fasta;
import edu.uc.eh.utils.UtilsNetwork;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by shamsabz on 2/1/19.
 */
@Service
public class FastaService {
    private static final Logger log = LoggerFactory.getLogger(FastaService.class);

    @Value("${urls.uniprotFasta}")
    String uniprotTemplate;

    public JSONObject getTable(String protein) {
        JSONObject fastaJson = new JSONObject();
        JSONObject response;
        //String xmlResponse;
        System.out.println("protein: " + protein);
        System.out.println(String.format(uniprotTemplate, protein));
        System.out.println(String.format(uniprotTemplate, protein));
        Map uniprotMap;
//        System.out.println("Querying: " + uniprotUrl);
//        System.out.println("Querying: " + uniprotUrl);
//        System.out.println("Querying: " + uniprotUrl);
        try {

            String uniprotUrl = String.format(uniprotTemplate, protein);
            response = UtilsNetwork.getInstance().readFastaUrl(uniprotUrl, protein);
            System.out.println(response);
            //log.info("Response from readXml: ");


            //System.out.println("uniprotMap:" + uniprotMap.toString());
        } catch (Exception e) {

            String msg =  String.format("Uniprot %s not found", protein);
            log.warn(msg);
            throw new RuntimeException(msg);

        }
        return response;

    }
}
