package edu.uc.eh.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shamsabz on 2/19/19.
 */
@Service
public class PeptideRegexServive {

    private static final Logger log = LoggerFactory.getLogger(PeptideRegexServive.class);

    private static final String REGEX = "(.*)(\\d+)(.*)?# 3 capturing groups";
    private static final String INPUT = "This is a sample Text, 1234, with numbers in between.";

    /**
     * Parse compound letter and modification.html mass from string e.g. K[+80]
     * @param input
     * @return
     */
    public JSONObject getMotifAndModification(String input){
        JSONObject motifAndModification = new JSONObject();
        motifAndModification.put("peptide", input);
        ArrayList<String> modifications = new ArrayList<String>();

        //String s = "KAY[+80]SF[myC]GTVE[pY]MA(UNIMOD:0011)PE(UNIMOD:0023)VVNR[+42.331]";


        String aminoAndInsideBrackets = "[A-Z]\\[([-+]?([0-9]*\\.[0-9]+|[0-9]+))\\]";
        String insideBracketsShorthand = "\\[([a-z]*[A-Z])\\]";
        String paranthesesAndInside = "\\([^)]*?\\)";
        String upperCasePattern = "[A-Z]+";
        //re6 = ".\\[([^}]+)\\]";
        String output = input.replaceAll(paranthesesAndInside, "");
        String motif = "";
//        System.out.println(s);
//        System.out.println(output);
        Pattern p8 = Pattern.compile(upperCasePattern);
        Matcher m8 = p8.matcher(output);
        //System.out.println("Before m8.group(1)");
        while(m8.find()) {
            motif += m8.group(0);
            //System.out.println(m8.group(0));
            //System.out.println(m6.group(1));
            //System.out.println(m6.group(2));
        }
        motifAndModification.put("motif", motif);
//        System.out.println(motif);
//        System.out.println("After m8.group(1)");
        Pattern p7 = Pattern.compile(aminoAndInsideBrackets);
        Matcher m7 = p7.matcher(input);
        //System.out.println("Before m7.group(1)");
        while(m7.find()) {
            modifications.add(m7.group(0));
            //System.out.println(m7.group(0));
            //System.out.println(m6.group(1));
            //System.out.println(m6.group(2));
        }
//        System.out.println("After m7.group(1)");
//        System.out.println("-----------");
        Pattern p6 = Pattern.compile(insideBracketsShorthand);
        Matcher m6 = p6.matcher(input);
        //System.out.println("Before m6.group(1)");
        while(m6.find()) {
            modifications.add(m6.group(0));
            //System.out.println(m6.group(0));
            //System.out.println(m6.group(1));
            //System.out.println(m6.group(2));
        }
//        System.out.println("After m6.group(1)");
//        System.out.println("-----------");
//        String re2 = "\\([^()]*\\)|\\[[^()]*\\]";
//        Pattern p2 = Pattern.compile("\\[(.*?)\\]");
//        Matcher m2 = p2.matcher(s);
//        System.out.println("Before m2.group(1)");
//        while(m2.find()) {
//            System.out.println(m2.group(0));
//        }
//        while (m2.find()) {
//
//            s = m2.replaceAll("");
//            m2 = p2.matcher(s);
//        }
//        System.out.println(s);
//        System.out.println("After m2.group(1) --------");
        Pattern p = Pattern.compile(paranthesesAndInside);
        Matcher m = p.matcher(input);
        while (m.find()) {
            //System.out.println(m.group(0));
            modifications.add(m.group(0));

        }

//        System.out.println("Finitto --------");
//        System.out.println("Finitto --------");
        motifAndModification.put("modifications", modifications);
        return motifAndModification;
    }
}
