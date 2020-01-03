package edu.uc.eh.service;

import edu.uc.eh.utils.UtilsNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chojnasm on 11/9/15.
 */

@Service
public class PrositeService {

    private static final Logger log = LoggerFactory.getLogger(PrositeService.class);

    @Value("${urls.prosite}")
    String prositeTemplate;

    @Value("${resources.mapping}")
    String psiModUrl;


    public String getTable(String peptide) {

        String response;
        String prositeUrl = String.format(prositeTemplate, peptide);

        log.info("Querying: " + prositeUrl);
        //log.info("PsiMod: " + UtilsIO.getInstance());



        try {
            //response = UtilsNetwork.getInstance().readUrl(prositeUrl);
            response = UtilsNetwork.getInstance().readUrl(prositeUrl);
            String msg =  String.format("success", response);

            log.warn(msg);

        } catch (Exception e) {

                String msg =  String.format("Prosite error! Peptide %s not found", peptide);
                log.warn(msg);
                throw new RuntimeException(msg);
                //response = toString({"n_match" : "NA", "n_seq" : 0, "matchset" : [ {"sequence_ac" : " ", "sequence_id" : "Prosite API error", "sequence_db" : " ", "start" : 0, "stop" : 0, "signature_ac" : " " } ] });

        }

        log.info("Prosite Response: " + response);
        return response;
    }
}
