package edu.uc.eh.service;

import edu.uc.eh.structures.DiffIdentifier;
import edu.uc.eh.structures.CharacterDouble;
import edu.uc.eh.structures.StringDoubleStringList;
import edu.uc.eh.structures.IntStringDoubleStringString;
import edu.uc.eh.utils.UtilsFormat;
import edu.uc.eh.utils.UtilsIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by chojnasm on 11/25/15.
 * Manage access to PSI-MOD ontology.
 */

@Service
public class PsiModService {

    private static final Logger log = LoggerFactory.getLogger(PsiModService.class);


//    public PsiModService() {
//
//        mapping = UtilsIO.getInstance().readResource("/psi-mod/mapping2.csv");
//    }


    @Value("${resources.mappingInfo}")
    String mappingInfo;

//    public PsiModService(Map<Character, List<DiffIdentifier>> mapping) {
//        this.mapping = mapping;
//    }

    /**
     * Get ontology identifier for given peptide modification.html (e.g. K[+80])
     *
     * @param modification
     * @return
     */
    public StringDoubleStringList getIdentifier(String modification, double epsilon) {
//        log.info(mapping.toString());
        Map<Character, List<DiffIdentifier>> mapping;

        try {

            mapping = UtilsIO.getInstance().readResource(mappingInfo);


        } catch (Exception e) {
            String msg =  String.format("Error in obtaining mappingInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        CharacterDouble cd = UtilsFormat.getInstance().modificationToCharDouble(modification);
        List<DiffIdentifier> list = mapping.get(cd.getCharacter());
        String currentIdentifier = "";

        Double minDiff = Double.MAX_VALUE;
        Double originalDiff = null;
        String description = null;
        Boolean flag = true;
        int i;
        List<StringDoubleStringList> similar = null;
        List<IntStringDoubleStringString> inRange = new ArrayList<>();
        String descriptionAll;
        int id = 0;
        //inRange = new ArrayList<>();
        if(list == null) {
            String msg =  String.format("Modification %s not found", modification);
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        for (DiffIdentifier di : list) {
            //log.info("PsimodService Response: " + di);

            double delta = Math.abs(di.getDiff() - cd.getaDouble());
            if (delta <= minDiff ) {
//            if (delta <= minDiff && delta < epsilon) {
//                log.info("di.getDiff(): " + di.getDiff());
//                log.info("cd.getaDouble(): " + cd.getaDouble());
//                log.info("delta: " + delta);
//                log.info("minDiff: " + minDiff);
//                log.info("epsilon: " + epsilon);

                if(delta == minDiff){
                    if(similar == null)similar = new ArrayList<>();

                    similar.add(new StringDoubleStringList(di.getIdentifier(),di.getDiff(),di.getDescription(),null));

                }else{
                    minDiff = delta;
                    originalDiff = di.getDiff();
                    currentIdentifier = di.getIdentifier();
                    description = di.getDescription();
                    similar = null;
                }

            }
            if ( delta < epsilon) {
                descriptionAll = "{"+di.getIdentifier() +"},{"+ di.getDiff().toString()  +"},{"+ di.getDescription() +"}";

                inRange.add(new IntStringDoubleStringString(id, di.getIdentifier(), di.getDiff(), di.getDescription(), descriptionAll));
                id = id + 1;
            }

        }


        if (inRange.size() < 5) {
            System.out.println(modification);

            flag = true;
            while (flag) {
                System.out.println(inRange.size());
                for (DiffIdentifier di : list) {

                    //log.info("PsimodService Response: " + di);

                    double delta = Math.abs(di.getDiff() - cd.getaDouble());
                    //System.out.println(delta);
                    if ( epsilon  <= delta && delta < epsilon + 1.0) {
                        descriptionAll = "{"+di.getIdentifier() +"},{"+ di.getDiff().toString()  +"},{"+ di.getDescription() +"}";
                        System.out.println(delta);
                        System.out.println(descriptionAll);
                        inRange.add(new IntStringDoubleStringString(id, di.getIdentifier(), di.getDiff(), di.getDescription(), descriptionAll));
                        id = id + 1;
                    }
                }

                if (inRange.size() >= 5) {
                    System.out.println("------------------ In inrange > 5 ------------------");
                    System.out.println(modification);
                    for (i = 0; i < inRange.size(); i++)
                    {
                        System.out.println(inRange.get(i).getId());
                        System.out.println(inRange.get(i).getString());
                    }

                    System.out.println("================= In inrange > 5 =================");
                    flag = false;
                }
                epsilon += 1.0;
            }

//            System.out.println(inRange.size());
//            System.out.println("===========");
        }

        return new StringDoubleStringList(currentIdentifier, originalDiff, description,inRange);
    }
}
