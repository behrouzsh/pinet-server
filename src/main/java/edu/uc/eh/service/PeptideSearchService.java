package edu.uc.eh.service;

import edu.uc.eh.peptideMatch.PeptideMatchCMD;
import edu.uc.eh.peptideMatch.Fasta;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shamsabz on 9/20/17.
 */

@Service
public class PeptideSearchService {

    private static final Logger log = LoggerFactory.getLogger(PeptideSearchService.class);

//    private final PeptideMatchCMD peptideMatchCMD;
//   // private final Fasta fasta;
//
//    @Autowired
//    public PeptideSearchService(PeptideMatchCMD peptideMatchCMD) {
//        this.peptideMatchCMD = peptideMatchCMD;
//        this.fasta = fasta;
//
//    }



    public JSONObject getTable(String organism,String[] peptides) {

        String response;
        //System.out.println(Arrays.toString(peptides));
        JSONObject peptideJson = new JSONObject();
        //String xmlResponse;
        //peptide[0] is the peptide, peptide[1] is the organism

        ArrayList<Fasta> queries = new ArrayList<Fasta>();
        for (int i = 0; i < peptides.length; i++) {
            queries.add(new Fasta(peptides[i], peptides[i]));
        }
        peptideJson = PeptideMatchCMD.doLocalQuery(organism, queries, 3, false);

        log.info("Searching for the input peptides");
        return peptideJson;
    }
}
