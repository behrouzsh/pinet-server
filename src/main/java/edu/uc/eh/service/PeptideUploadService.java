package edu.uc.eh.service;

import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import edu.uc.eh.structures.StringDoubleStringList;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import uk.ac.ebi.pride.utilities.pridemod.ModReader;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shamsabz on 1/15/19.
 */
@Service
public class PeptideUploadService {
    private static final Logger log = LoggerFactory.getLogger(PeptideUploadService.class);

    @Autowired
    UniprotService2 uniprotService = new UniprotService2();

    Pattern numberRegex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
    Pattern characterRegex = Pattern.compile("(.*?)(\\d+)(.*)");
    Pattern outsideParenthesis = Pattern.compile("s/^([^(]*)//g");
    Pattern insideParenthesis = Pattern.compile("\\(.*?\\)");
    Pattern insideBrackets = Pattern.compile("\\[(.*?)\\]");




    public JSONArray computePeptideProteinRelation(JSONArray inputJsonArray) throws Exception {
        JSONArray responseArray = new JSONArray();
        ArrayList keys = new ArrayList();
        String pep;
        System.out.println("In computePeptideProteinRelation");
        for (int i = 0; i < inputJsonArray.size(); i++) {
            JSONObject eachRow = (JSONObject) inputJsonArray.get(i);


            //===========================================
            if(eachRow.containsKey("Peptide"))
            {

                pep = (String) eachRow.get("Peptide");
                System.out.println(pep);



                Matcher insideBracketsMatcher = insideBrackets.matcher(pep);

                Matcher insideParenthesisMatcher = insideParenthesis.matcher(pep);

                Matcher m = Pattern.compile("\\((.*?)\\)").matcher(pep);
                while(m.find()) {
                    System.out.println("here0");
                    System.out.println(m.group(1));
                    System.out.println(m.group(1));
                }

                while (insideParenthesisMatcher.find()) {
                    System.out.println("here1");
                    System.out.println(insideParenthesisMatcher.group(1));
                }
                System.out.println("-----");
                pep = pep.replaceAll("\\(.*?\\)", "");
                System.out.println(pep);
                //Matcher insideMatcher = characterRegex.matcher(pep);
                while (insideBracketsMatcher.find()) {
                    System.out.println("here2");

                    System.out.println(insideBracketsMatcher.group(0));
                    System.out.println(insideBracketsMatcher.group(1));
                }



                System.out.println("---------------------------------------------");

            }
            //===========================================
            if(eachRow.containsKey("peptide"))
            {
                pep = (String) eachRow.get("peptide");
            }
            //===========================================
            if(eachRow.containsKey("PEPTIDE"))
            {
                pep = (String) eachRow.get("PEPTIDE");
            }
            //===========================================
            if(eachRow.containsKey("PEPTIDES"))
            {
                pep = (String) eachRow.get("PEPTIDES");
            }
            //===========================================
            if(eachRow.containsKey("Peptides"))
            {
                pep = (String) eachRow.get("Peptides");
            }
            //===========================================
            if(eachRow.containsKey("peptides"))
            {
                pep = (String) eachRow.get("peptides");
            }

        }
        return responseArray;
    }


}
