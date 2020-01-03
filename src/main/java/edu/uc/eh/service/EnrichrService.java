package edu.uc.eh.service;

import edu.uc.eh.utils.UtilsNetwork;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

//import org.json.JSONObject;

/**
 * Created by behrouz on 10/24/16.
 */

@Service
public class EnrichrService {

    private static final Logger log = LoggerFactory.getLogger(EnrichrService.class);

//    @Value("${urls.enrichrAddList}")
//    String enrichrAddList;
//
//    @Value("${urls.enrichrViewList}")
//    String enrichrViewList;
//
//    @Value("${urls.enrichrResult}")
//    String enrichrResult;

    @Value("${urls.enrichrGeneInfo}")
    String enrichrGeneInfo;

    public JSONObject getGeneInfo(String pathwayClass, String gene) {
        String response;
        String enrichrUrlGeneInfo = String.format(enrichrGeneInfo, gene);
        JSONObject geneInfo;
        JSONObject genePathways = new JSONObject();
        JSONObject geneJsonInfo = new JSONObject();
//        log.info("Querying: " + enrichrUrlGeneInfo);

        List<String> pathways = new ArrayList<String>();


        if (pathwayClass.equals("pathways")){

            //System.out.println("in pathways ----");
            pathways.add("KEGG_2015");
            pathways.add("WikiPathways_2015");
            pathways.add("Panther_2015");
            pathways.add("KEGG_2016");
            pathways.add("WikiPathways_2016");
            pathways.add("ARCHS4_Kinases_Coexp");
            pathways.add("Reactome_2016");
            pathways.add("BioCarta_2016");
            pathways.add("Humancyc_2016");
            pathways.add("NCI-Nature_2016");
            pathways.add("Panther_2016");
            pathways.add("BioPlex_2017");

            pathways.add("huMAP");
            pathways.add("PPI_Hub_Proteins");
            pathways.add("KEA_2015");
            pathways.add("LINCS_L1000_Kinase_Perturbations_down");
            pathways.add("LINCS_L1000_Kinase_Perturbations_up");
            pathways.add("Kinase_Perturbations_from_GEO_down");
            pathways.add("Kinase_Perturbations_from_GEO_up");
            pathways.add("NURSA_Human_Endogenous_Complexome");
            pathways.add("CORUM");
            pathways.add("SILAC_Phosphoproteomics");
            pathways.add("Phosphatase_Substrates_from_DEPOD");
        }
        if (pathwayClass.equals("diseaseDrugs")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("LINCS_L1000_Chem_Pert_up");
            pathways.add("LINCS_L1000_Chem_Pert_down");
            pathways.add("LINCS_L1000_Ligand_Perturbations_up");

            pathways.add("LINCS_L1000_Ligand_Perturbations_down");
            pathways.add("ARCHS4_IDG_Coexp");
            pathways.add("DrugMatrix");

            pathways.add("Old_CMAP_up");
            pathways.add("Old_CMAP_down");
            pathways.add("GeneSigDB");

            pathways.add("OMIM_Disease");
            pathways.add("OMIM_Expanded");
            pathways.add("VirusMINT");

            pathways.add("MSigDB_Computational");
            pathways.add("MSigDB_Oncogenic_Signatures");
            pathways.add("Virus_Perturbations_from_GEO_up");

            pathways.add("Virus_Perturbations_from_GEO_down");
            pathways.add("Achilles_fitness_increase");
            pathways.add("Achilles_fitness_decrease");
            pathways.add("dbGaP");



        }

        if (pathwayClass.equals("ontology")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("GO_Cellular_Component_2017b");
            pathways.add("GO_Biological_Process_2017b");
            pathways.add("GO_Molecular_Function_2017b");

            pathways.add("MGI_Mammalian_Phenotype_2017");
            pathways.add("Human_Phenotype_Ontology");
            pathways.add("Jensen_TISSUES");

            pathways.add("Jensen_COMPARTMENTS");
            pathways.add("Jensen_DISEASES");

//            ));

        }

        if (pathwayClass.equals("transcription")){
//            List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("ChEA_2016");
            pathways.add("TRANSFAC_and_JASPAR_PWMs");
            pathways.add("ARCHS4_TFs_Coexp");

            pathways.add("Genome_Browser_PWMs");
            pathways.add("ENCODE_and_ChEA_Consensus_TFs_from_ChIP-X");
            pathways.add("Epigenomics_Roadmap_HM_ChIP-seq");

            pathways.add("TargetScan_microRNA");
            pathways.add("ENCODE_TF_ChIP-seq_2015");
            pathways.add("TF-LOF_Expression_from_GEO");

            pathways.add("ENCODE_Histone_Modifications_2015");
            pathways.add("Transcription_Factor_PPIs");

            //));

        }

        if (pathwayClass.equals("cellType")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("Human_Gene_Atlas");
            pathways.add("Mouse_Gene_Atlas");
            pathways.add("ARCHS4_Tissues");

            pathways.add("ARCHS4_Cell-lines");
            pathways.add("Allen_Brain_Atlas_up");
            pathways.add("Allen_Brain_Atlas_down");

            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_up");
            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_down");
            pathways.add("Cancer_Cell_Line_Encyclopedia");

            pathways.add("NCI-60_Cancer_Cell_Lines");
            pathways.add("Tissue_Protein_Expression_from_ProteomicsDB");
            pathways.add("Tissue_Protein_Expression_from_Human_Proteome_Map");
            pathways.add("ESCAPE");

            //));

        }



//        JSONArray Kinase_Perturbations_from_GEO = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_up = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_down = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_up = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_down = new JSONArray();
//        JSONArray WikiPathways_2013 = new JSONArray();
//        JSONArray WikiPathways_2015 = new JSONArray();
//        JSONArray WikiPathways_2016 = new JSONArray();
//        JSONArray Panther_2015 = new JSONArray();
//        JSONArray Panther_2016 = new JSONArray();
//        JSONArray KEGG_2013 = new JSONArray();
//        JSONArray KEGG_2015 = new JSONArray();
//        JSONArray KEGG_2016 = new JSONArray();
        JSONArray pathwayJson = new JSONArray();
        // Try to get a geneId from the geneJsonList
//        System.out.println("in get gene info ----");
//        System.out.println(pathwayClass);
//        System.out.println(pathways);
        try {

            response = UtilsNetwork.getInstance().readUrlXml(enrichrUrlGeneInfo);
            //log.info(response);

            JSONParser parser = new JSONParser();
            geneInfo = (JSONObject) parser.parse(response);

            JSONObject geneResults = (JSONObject) geneInfo.get("gene");

            //log.info(geneResults.toJSONString());
            for (int index=0; index < pathways.size(); index++) {
                String queryPathway = pathways.get(index);
                //System.out.println(queryPathway);

                if (geneResults.containsKey(queryPathway)) {
                    pathwayJson = (JSONArray) geneResults.get(queryPathway);
                    genePathways.put(queryPathway, pathwayJson);
//                log.info("KEGG_2013");
//                log.info(KEGG_2013.toJSONString());
                }
            }
            // ++++++++++++++++++++++++++++++++++++++++++++++
//            if (geneResults.containsKey("KEGG_2013")) {
//                KEGG_2013 = (JSONArray) geneResults.get("KEGG_2013");
////                log.info("KEGG_2013");
////                log.info(KEGG_2013.toJSONString());
//            }
//
//            if (geneResults.containsKey("KEGG_2015")) {
//                KEGG_2015 = (JSONArray) geneResults.get("KEGG_2015");
////                log.info("KEGG_2015");
////                log.info(KEGG_2015.toJSONString());
//            }
//
//            if (geneResults.containsKey("KEGG_2016")) {
//                KEGG_2016 = (JSONArray) geneResults.get("KEGG_2016");
////                log.info("KEGG_2016");
////                log.info(KEGG_2016.toJSONString());
//            }
//
//            if (geneResults.containsKey("Panther_2015")) {
//                Panther_2015 = (JSONArray) geneResults.get("Panther_2015");
////                log.info("Panther_2015");
////                log.info(Panther_2015.toJSONString());
//            }
//            if (geneResults.containsKey("Panther_2016")) {
//                Panther_2016 = (JSONArray) geneResults.get("Panther_2016");
////                log.info("Panther_2016");
////                log.info(Panther_2016.toJSONString());
//            }
//            if (geneResults.containsKey("WikiPathways_2013")) {
//                WikiPathways_2013 = (JSONArray) geneResults.get("WikiPathways_2013");
////                log.info("WikiPathways_2013");
////                log.info(WikiPathways_2013.toJSONString());
//            }
//            if (geneResults.containsKey("WikiPathways_2015")) {
//                WikiPathways_2015 = (JSONArray) geneResults.get("WikiPathways_2015");
////                log.info("WikiPathways_2015");
////                log.info(WikiPathways_2015.toJSONString());
//            }
//            if (geneResults.containsKey("WikiPathways_2016")) {
//                WikiPathways_2016 = (JSONArray) geneResults.get("WikiPathways_2016");
////                log.info("WikiPathways_2013");
////                log.info(WikiPathways_2016.toJSONString());
//            }
//            if (geneResults.containsKey("Kinase_Perturbations_from_GEO")) {
//                Kinase_Perturbations_from_GEO = (JSONArray) geneResults.get("Kinase_Perturbations_from_GEO");
////                log.info("Kinase_Perturbations_from_GEO");
////                log.info(Kinase_Perturbations_from_GEO.toJSONString());
//
//            }
//            if (geneResults.containsKey("Kinase_Perturbations_from_GEO_up")) {
//                Kinase_Perturbations_from_GEO_up = (JSONArray) geneResults.get("Kinase_Perturbations_from_GEO_up");
////                log.info("Kinase_Perturbations_from_GEO");
////                log.info(Kinase_Perturbations_from_GEO.toJSONString());
//
//            }
//            if (geneResults.containsKey("Kinase_Perturbations_from_GEO_down")) {
//                Kinase_Perturbations_from_GEO_down = (JSONArray) geneResults.get("Kinase_Perturbations_from_GEO_down");
////                log.info("Kinase_Perturbations_from_GEO");
////                log.info(Kinase_Perturbations_from_GEO.toJSONString());
//
//            }
//            if (geneResults.containsKey("LINCS_L1000_Kinase_Perturbations_up")) {
//                LINCS_L1000_Kinase_Perturbations_up = (JSONArray) geneResults.get("LINCS_L1000_Kinase_Perturbations_up");
////                log.info("Kinase_Perturbations_from_GEO");
////                log.info(Kinase_Perturbations_from_GEO.toJSONString());
//
//            }
//            if (geneResults.containsKey("LINCS_L1000_Kinase_Perturbations_down")) {
//                LINCS_L1000_Kinase_Perturbations_down = (JSONArray) geneResults.get("LINCS_L1000_Kinase_Perturbations_down");
////                log.info("Kinase_Perturbations_from_GEO");
////                log.info(Kinase_Perturbations_from_GEO.toJSONString());
//
//            }


        } catch (Exception e) {

            String msg =  String.format("Error in obtaining geneInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        //return geneJsonInfo;
//        genePathways.put("KEGG_2013", KEGG_2013);
//        genePathways.put("KEGG_2015", KEGG_2015);
//        genePathways.put("KEGG_2016", KEGG_2016);
//        genePathways.put("WikiPathways_2013", WikiPathways_2013);
//        genePathways.put("WikiPathways_2015", WikiPathways_2015);
//        genePathways.put("WikiPathways_2016", WikiPathways_2016);
//        genePathways.put("Panther_2015", Panther_2015);
//        genePathways.put("Panther_2016", Panther_2016);
//        genePathways.put("Kinase_Perturbations_from_GEO", Kinase_Perturbations_from_GEO);
//        genePathways.put("Kinase_Perturbations_from_GEO_down", Kinase_Perturbations_from_GEO_down);
//        genePathways.put("Kinase_Perturbations_from_GEO_up", Kinase_Perturbations_from_GEO_up);
//        genePathways.put("LINCS_L1000_Kinase_Perturbations_up", LINCS_L1000_Kinase_Perturbations_up);
//        genePathways.put("LINCS_L1000_Kinase_Perturbations_down", LINCS_L1000_Kinase_Perturbations_down);
//        genePathways.put("KEGG_2013", KEGG_2013);
        return genePathways;
    }


    public JSONObject getGeneInfo2(String gene) {
        String response;
        String enrichrUrlGeneInfo = String.format(enrichrGeneInfo, gene);
        JSONObject geneInfo;
        JSONObject genePathways = new JSONObject();
        JSONObject geneJsonInfo = new JSONObject();
//        log.info("Querying: " + enrichrUrlGeneInfo);

        List<String> pathways = new ArrayList<String>();

        JSONObject geneResults = new JSONObject();
        JSONArray pathwayJson = new JSONArray();

        try {

            response = UtilsNetwork.getInstance().readUrlXml(enrichrUrlGeneInfo);
            //log.info(response);

            JSONParser parser = new JSONParser();
            geneInfo = (JSONObject) parser.parse(response);

            geneResults = (JSONObject) geneInfo.get("gene");

            //log.info(geneResults.toJSONString());
//            for (int index=0; index < pathways.size(); index++) {
//                String queryPathway = pathways.get(index);
//                //System.out.println(queryPathway);
//
//                if (geneResults.containsKey(queryPathway)) {
//                    pathwayJson = (JSONArray) geneResults.get(queryPathway);
//                    genePathways.put(queryPathway, pathwayJson);
////                log.info("KEGG_2013");
////                log.info(KEGG_2013.toJSONString());
//                }
//            }



        } catch (Exception e) {

            String msg =  String.format("Error in obtaining geneInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }


        return geneResults;
    }


    public JSONObject computeNetwork(JSONObject input) {

        //int idx = 0;
        int idxKEGG_2013 = 0;
        int idxKEGG_2015 = 0;
        int idxKEGG_2016 = 0;
        int idxWikiPathways_2013 = 0;
        int idxWikiPathways_2015 = 0;
        int idxWikiPathways_2016 = 0;
        int idxPanther_2015 = 0;
        int idxPanther_2016 = 0;
        int idxKinase_Perturbations_from_GEO = 0;
        int idxKinase_Perturbations_from_GEO_up = 0;
        int idxKinase_Perturbations_from_GEO_down = 0;
        int idxLINCS_L1000_Kinase_Perturbations_up = 0;
        int idxLINCS_L1000_Kinase_Perturbations_down = 0;

        JSONObject network = new JSONObject();
        //==============================================
        JSONObject KEGG_2013 = new JSONObject();
        JSONObject KEGG_2015 = new JSONObject();
        JSONObject KEGG_2016 = new JSONObject();
        JSONObject WikiPathways_2013 = new JSONObject();
        JSONObject WikiPathways_2015 = new JSONObject();
        JSONObject WikiPathways_2016 = new JSONObject();
        JSONObject Panther_2015 = new JSONObject();
        JSONObject Panther_2016 = new JSONObject();
        JSONObject Kinase_Perturbations_from_GEO = new JSONObject();
        JSONObject Kinase_Perturbations_from_GEO_up = new JSONObject();
        JSONObject Kinase_Perturbations_from_GEO_down = new JSONObject();
        JSONObject LINCS_L1000_Kinase_Perturbations_up = new JSONObject();
        JSONObject LINCS_L1000_Kinase_Perturbations_down = new JSONObject();
        //==============================================
        JSONArray KEGG2013List = new JSONArray();
        JSONArray KEGG2015List = new JSONArray();
        JSONArray KEGG2016List = new JSONArray();
        JSONArray WikiPathways2013List = new JSONArray();
        JSONArray WikiPathways2015List = new JSONArray();
        JSONArray WikiPathways2016List = new JSONArray();
        JSONArray Panther2015List = new JSONArray();
        JSONArray Panther2016List = new JSONArray();
        JSONArray KinasePerturbations_from_GEOList = new JSONArray();
        JSONArray KinasePerturbations_from_GEO_upList = new JSONArray();
        JSONArray KinasePerturbations_from_GEO_downList = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_upList = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_downList = new JSONArray();
        //==============================================
        //This is for circular drawing of the charts
//        JSONArray KEGG_2013Parallel = new JSONArray();
//        JSONArray KEGG_2015Parallel = new JSONArray();
//        JSONArray KEGG_2016Parallel = new JSONArray();
//        JSONArray WikiPathways_2013Parallel = new JSONArray();
//        JSONArray WikiPathways_2015Parallel = new JSONArray();
//        JSONArray WikiPathways_2016Parallel = new JSONArray();
//        JSONArray Panther_2015Parallel = new JSONArray();
//        JSONArray Panther_2016Parallel = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEOParallel = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_upParallel = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_downParallel = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_upParallel = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_downParallel = new JSONArray();
        //==============================================
        //This is for circular drawing of the charts
//        JSONArray KEGG_2013Circular = new JSONArray();
//        JSONArray KEGG_2015Circular = new JSONArray();
//        JSONArray KEGG_2016Circular = new JSONArray();
//        JSONArray WikiPathways_2013Circular = new JSONArray();
//        JSONArray WikiPathways_2015Circular = new JSONArray();
//        JSONArray WikiPathways_2016Circular = new JSONArray();
//        JSONArray Panther_2015Circular = new JSONArray();
//        JSONArray Panther_2016Circular = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEOCircular = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_upCircular = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_downCircular = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_upCircular = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_downCircular = new JSONArray();
        //==============================================
        //This is for circular drawing of the charts
//        JSONArray KEGG_2013CircularList = new JSONArray();
//        JSONArray KEGG_2015CircularList = new JSONArray();
//        JSONArray KEGG_2016CircularList = new JSONArray();
//        JSONArray WikiPathways_2013CircularList = new JSONArray();
//        JSONArray WikiPathways_2015CircularList = new JSONArray();
//        JSONArray WikiPathways_2016CircularList = new JSONArray();
//        JSONArray Panther_2015CircularList = new JSONArray();
//        JSONArray Panther_2016CircularList = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEOCircularList = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_upCircularList = new JSONArray();
//        JSONArray Kinase_Perturbations_from_GEO_downCircularList = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_upCircularList = new JSONArray();
//        JSONArray LINCS_L1000_Kinase_Perturbations_downCircularList = new JSONArray();
        //==============================================
        JSONArray KEGG_2013Nodes = new JSONArray();
        JSONArray KEGG_2015Nodes = new JSONArray();
        JSONArray KEGG_2016Nodes = new JSONArray();
        JSONArray WikiPathways_2013Nodes = new JSONArray();
        JSONArray WikiPathways_2015Nodes = new JSONArray();
        JSONArray WikiPathways_2016Nodes = new JSONArray();
        JSONArray Panther_2015Nodes = new JSONArray();
        JSONArray Panther_2016Nodes = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEONodes = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEO_upNodes = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEO_downNodes = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_upNodes = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_downNodes = new JSONArray();
        //==============================================
        JSONArray KEGG_2013Edges = new JSONArray();
        JSONArray KEGG_2015Edges = new JSONArray();
        JSONArray KEGG_2016Edges = new JSONArray();
        JSONArray WikiPathways_2013Edges = new JSONArray();
        JSONArray WikiPathways_2015Edges = new JSONArray();
        JSONArray WikiPathways_2016Edges = new JSONArray();
        JSONArray Panther_2015Edges = new JSONArray();
        JSONArray Panther_2016Edges = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEOEdges = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEO_upEdges = new JSONArray();
        JSONArray Kinase_Perturbations_from_GEO_downEdges = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_upEdges = new JSONArray();
        JSONArray LINCS_L1000_Kinase_Perturbations_downEdges = new JSONArray();
        //==============================================

        JSONObject newGeneNodeKEGG_2013 = new JSONObject();
        JSONObject newGeneNodeKEGG_2015 = new JSONObject();
        JSONObject newGeneNodeKEGG_2016 = new JSONObject();
        JSONObject newGeneNodeWikiPathways_2013 = new JSONObject();
        JSONObject newGeneNodeWikiPathways_2015 = new JSONObject();
        JSONObject newGeneNodeWikiPathways_2016 = new JSONObject();
        JSONObject newGeneNodePanther_2015 = new JSONObject();
        JSONObject newGeneNodePanther_2016 = new JSONObject();
        JSONObject newGeneNodeKinase_Perturbations_from_GEO = new JSONObject();
        JSONObject newGeneNodeKinase_Perturbations_from_GEO_up = new JSONObject();
        JSONObject newGeneNodeKinase_Perturbations_from_GEO_down = new JSONObject();
        JSONObject newGeneNodeLINCS_L1000_Kinase_Perturbations_up = new JSONObject();
        JSONObject newGeneNodeLINCS_L1000_Kinase_Perturbations_down = new JSONObject();
        JSONObject newGeneNode = new JSONObject();
        JSONObject newPathNode = new JSONObject();
        JSONObject newEdgeNode = new JSONObject();


        //==============================================
        JSONObject KEGG2013Unique = new JSONObject();
        JSONObject KEGG2015Unique = new JSONObject();
        JSONObject KEGG2016Unique = new JSONObject();
        JSONObject WikiPathways2013Unique = new JSONObject();
        JSONObject WikiPathways2015Unique = new JSONObject();
        JSONObject WikiPathways2016Unique = new JSONObject();
        JSONObject Panther2015Unique = new JSONObject();
        JSONObject Panther2016Unique = new JSONObject();
        JSONObject KinasePerturbationsfromGEOUnique = new JSONObject();
        JSONObject KinasePerturbationsfromGEOUpUnique = new JSONObject();
        JSONObject KinasePerturbationsfromGEODownUnique = new JSONObject();
        JSONObject LINCSL1000KinasePerturbationsUpUnique = new JSONObject();
        JSONObject LINCSL1000KinasePerturbationsDownUnique = new JSONObject();
        //==============================================
//        JSONObject KEGG2013PathValue = new JSONObject();
//        JSONObject KEGG2015PathValue = new JSONObject();
//        JSONObject KEGG2016PathValue = new JSONObject();
//        JSONObject WikiPathways2013PathValue = new JSONObject();
//        JSONObject WikiPathways2015PathValue = new JSONObject();
//        JSONObject WikiPathways2016PathValue = new JSONObject();
//        JSONObject Panther2015PathValue = new JSONObject();
//        JSONObject Panther2016PathValue = new JSONObject();
//        JSONObject KinasePerturbationsfromGEOPathValue = new JSONObject();
//        JSONObject KinasePerturbationsfromGEOUpPathValue = new JSONObject();
//        JSONObject KinasePerturbationsfromGEODownPathValue = new JSONObject();
//        JSONObject LINCSL1000KinasePerturbationsUpPathValue = new JSONObject();
//        JSONObject LINCSL1000KinasePerturbationsDownPathValue = new JSONObject();
        //==============================================


        // Second loop for generating node and edge
        //==============================================
        //==============================================
        //==============================================
        //==============================================
        //==============================================
        for (Object key : input.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            JSONObject keyValue = (JSONObject) input.get(keyStr);

            String pathway;


            KEGG2013List = (JSONArray)(keyValue.get("KEGG_2013"));
            KEGG2015List = (JSONArray) (keyValue.get("KEGG_2015"));
            KEGG2016List = (JSONArray) (keyValue.get("KEGG_2016"));
            WikiPathways2013List = (JSONArray) (keyValue.get("WikiPathways_2013"));
            WikiPathways2015List = (JSONArray) (keyValue.get("WikiPathways_2015"));
            WikiPathways2016List = (JSONArray) (keyValue.get("WikiPathways_2016"));
            Panther2015List = (JSONArray) (keyValue.get("Panther_2015"));
            Panther2016List = (JSONArray) (keyValue.get("Panther_2016"));
            KinasePerturbations_from_GEOList = (JSONArray) (keyValue.get("Kinase_Perturbations_from_GEO"));
            KinasePerturbations_from_GEO_upList = (JSONArray) (keyValue.get("Kinase_Perturbations_from_GEO_up"));
            KinasePerturbations_from_GEO_downList = (JSONArray) (keyValue.get("Kinase_Perturbations_from_GEO_down"));
            LINCS_L1000_Kinase_Perturbations_upList = (JSONArray) (keyValue.get("LINCS_L1000_Kinase_Perturbations_up"));
            LINCS_L1000_Kinase_Perturbations_downList = (JSONArray) (keyValue.get("LINCS_L1000_Kinase_Perturbations_down"));

            //This is for Json format
//            JSONObject keyValueTmpKEGG2013 = new JSONObject();
//            keyValueTmpKEGG2013.put(keyStr,KEGG2013List);
//            KEGG_2013Circular.add(keyValueTmpKEGG2013);
            //This is for list format
//            ArrayList keyValueTmpKEGG2013List = new ArrayList();
//            keyValueTmpKEGG2013List.add(keyStr);
//            keyValueTmpKEGG2013List.add(KEGG2013List);
//            KEGG_2013CircularList.add(keyValueTmpKEGG2013List);

            //===================================================

//            JSONObject keyValueTmpKEGG2015 = new JSONObject();
//            keyValueTmpKEGG2015.put(keyStr,KEGG2015List);
//            KEGG_2015Circular.add(keyValueTmpKEGG2015);

//            ArrayList keyValueTmpKEGG2015List = new ArrayList();
//            keyValueTmpKEGG2015List.add(keyStr);
//            keyValueTmpKEGG2015List.add(KEGG2015List);
//            KEGG_2015CircularList.add(keyValueTmpKEGG2015List);

            //===================================================

//            JSONObject keyValueTmpKEGG2016 = new JSONObject();
//            keyValueTmpKEGG2016.put(keyStr,KEGG2016List);
//            KEGG_2016Circular.add(keyValueTmpKEGG2016);

//            ArrayList keyValueTmpKEGG2016List = new ArrayList();
//            keyValueTmpKEGG2016List.add(keyStr);
//            keyValueTmpKEGG2016List.add(KEGG2016List);
//            KEGG_2016CircularList.add(keyValueTmpKEGG2016List);

            //===================================================

//            JSONObject keyValueTmpKWikiPathways2013List = new JSONObject();
//            keyValueTmpKWikiPathways2013List.put(keyStr,WikiPathways2013List);
//            WikiPathways_2013Circular.add(keyValueTmpKWikiPathways2013List);

//            ArrayList keyValueTmpKWikiPathways2013List2 = new ArrayList();
//            keyValueTmpKWikiPathways2013List2.add(keyStr);
//            keyValueTmpKWikiPathways2013List2.add(WikiPathways2013List);
//            WikiPathways_2013CircularList.add(keyValueTmpKWikiPathways2013List2);

            //===================================================

//            JSONObject keyValueTmpKWikiPathways2015List = new JSONObject();
//            keyValueTmpKWikiPathways2015List.put(keyStr,WikiPathways2015List);
//            WikiPathways_2015Circular.add(keyValueTmpKWikiPathways2015List);

//            ArrayList keyValueTmpKWikiPathways2015List2 = new ArrayList();
//            keyValueTmpKWikiPathways2015List2.add(keyStr);
//            keyValueTmpKWikiPathways2015List2.add(WikiPathways2015List);
//            WikiPathways_2015CircularList.add(keyValueTmpKWikiPathways2015List2);

            //===================================================

//            JSONObject keyValueTmpKWikiPathways2016List = new JSONObject();
//            keyValueTmpKWikiPathways2016List.put(keyStr,WikiPathways2016List);
//            WikiPathways_2016Circular.add(keyValueTmpKWikiPathways2016List);
//
//            ArrayList keyValueTmpKWikiPathways2016List2 = new ArrayList();
//            keyValueTmpKWikiPathways2016List2.add(keyStr);
//            keyValueTmpKWikiPathways2016List2.add(WikiPathways2016List);
//            WikiPathways_2016CircularList.add(keyValueTmpKWikiPathways2016List2);

            //===================================================

//            JSONObject keyValueTmpKPanther2015List = new JSONObject();
//            keyValueTmpKPanther2015List.put(keyStr,Panther2015List);
//            Panther_2015Circular.add(keyValueTmpKPanther2015List);
//
//            ArrayList keyValueTmpKPanther2015List2 = new ArrayList();
//            keyValueTmpKPanther2015List2.add(keyStr);
//            keyValueTmpKPanther2015List2.add(Panther2015List);
//            Panther_2015CircularList.add(keyValueTmpKPanther2015List2);

            //===================================================
//            JSONObject keyValueTmpPanther2016List = new JSONObject();
//            keyValueTmpPanther2016List.put(keyStr,Panther2016List);
//            Panther_2016Circular.add(keyValueTmpPanther2016List);
//
//            ArrayList keyValueTmpPanther2016List2 = new ArrayList();
//            keyValueTmpPanther2016List2.add(keyStr);
//            keyValueTmpPanther2016List2.add(Panther2016List);
//            Panther_2016CircularList.add(keyValueTmpPanther2016List2);

            //===================================================
//            JSONObject keyValueKinasePerturbations_from_GEOList = new JSONObject();
//            keyValueKinasePerturbations_from_GEOList.put(keyStr,KinasePerturbations_from_GEOList);
//            Kinase_Perturbations_from_GEOCircular.add(keyValueKinasePerturbations_from_GEOList);
//
//            ArrayList keyValueKinasePerturbations_from_GEOList2 = new ArrayList();
//            keyValueKinasePerturbations_from_GEOList2.add(keyStr);
//            keyValueKinasePerturbations_from_GEOList2.add(KinasePerturbations_from_GEOList);
//            Kinase_Perturbations_from_GEOCircularList.add(keyValueKinasePerturbations_from_GEOList2);

            //===================================================

//            JSONObject keyValueKinasePerturbations_from_GEO_upList = new JSONObject();
//            keyValueKinasePerturbations_from_GEO_upList.put(keyStr,KinasePerturbations_from_GEO_upList);
//            Kinase_Perturbations_from_GEO_upCircular.add(keyValueKinasePerturbations_from_GEO_upList);
//
//            ArrayList keyValueKinasePerturbations_from_GEO_upList2 = new ArrayList();
//            keyValueKinasePerturbations_from_GEO_upList2.add(keyStr);
//            keyValueKinasePerturbations_from_GEO_upList2.add(KinasePerturbations_from_GEO_upList);
//            Kinase_Perturbations_from_GEO_upCircularList.add(keyValueKinasePerturbations_from_GEO_upList2);

            //===================================================

//            JSONObject keyValueKinasePerturbations_from_GEO_downList = new JSONObject();
//            keyValueKinasePerturbations_from_GEO_downList.put(keyStr,KinasePerturbations_from_GEO_downList);
//            Kinase_Perturbations_from_GEO_downCircular.add(keyValueKinasePerturbations_from_GEO_downList);
//
//            ArrayList keyValueKinasePerturbations_from_GEO_downList2 = new ArrayList();
//            keyValueKinasePerturbations_from_GEO_downList2.add(keyStr);
//            keyValueKinasePerturbations_from_GEO_downList2.add(KinasePerturbations_from_GEO_downList);
//            Kinase_Perturbations_from_GEO_downCircularList.add(keyValueKinasePerturbations_from_GEO_downList2);

            //===================================================
//
//            JSONObject keyValueLINCS_L1000_Kinase_Perturbations_upList = new JSONObject();
//            keyValueLINCS_L1000_Kinase_Perturbations_upList.put(keyStr,LINCS_L1000_Kinase_Perturbations_upList);
//            LINCS_L1000_Kinase_Perturbations_upCircular.add(keyValueLINCS_L1000_Kinase_Perturbations_upList);
//
//            ArrayList keyValueLINCS_L1000_Kinase_Perturbations_upList2 = new ArrayList();
//            keyValueLINCS_L1000_Kinase_Perturbations_upList2.add(keyStr);
//            keyValueLINCS_L1000_Kinase_Perturbations_upList2.add(LINCS_L1000_Kinase_Perturbations_upList);
//            LINCS_L1000_Kinase_Perturbations_upCircularList.add(keyValueLINCS_L1000_Kinase_Perturbations_upList2);

            //===================================================

//            JSONObject keyValueLINCS_L1000_Kinase_Perturbations_downList = new JSONObject();
//            keyValueLINCS_L1000_Kinase_Perturbations_downList.put(keyStr,LINCS_L1000_Kinase_Perturbations_downList);
//            LINCS_L1000_Kinase_Perturbations_downCircular.add(keyValueLINCS_L1000_Kinase_Perturbations_downList);
//
//            ArrayList keyValueLINCS_L1000_Kinase_Perturbations_downList2 = new ArrayList();
//            keyValueLINCS_L1000_Kinase_Perturbations_downList2.add(keyStr);
//            keyValueLINCS_L1000_Kinase_Perturbations_downList2.add(LINCS_L1000_Kinase_Perturbations_downList);
//            LINCS_L1000_Kinase_Perturbations_downCircularList.add(keyValueLINCS_L1000_Kinase_Perturbations_downList2);

            //===================================================
            //newGeneNode = generateNode(keyStr, idx, 1);


            newGeneNodeKEGG_2013 = generateNode(keyStr, idxKEGG_2013, 1, 0.0);
            KEGG_2013Nodes.add(newGeneNodeKEGG_2013);

            newGeneNodeKEGG_2015 = generateNode(keyStr, idxKEGG_2015, 1, 0.0);
            KEGG_2015Nodes.add(newGeneNodeKEGG_2015);

            newGeneNodeKEGG_2016 = generateNode(keyStr, idxKEGG_2016, 1, 0.0);
            KEGG_2016Nodes.add(newGeneNodeKEGG_2016);

            newGeneNodeWikiPathways_2013 = generateNode(keyStr, idxWikiPathways_2013, 1, 0.0);
            WikiPathways_2013Nodes.add(newGeneNodeWikiPathways_2013);

            newGeneNodeWikiPathways_2015 = generateNode(keyStr, idxWikiPathways_2015, 1, 0.0);
            WikiPathways_2015Nodes.add(newGeneNodeWikiPathways_2015);

            newGeneNodeWikiPathways_2016 = generateNode(keyStr, idxWikiPathways_2016, 1, 0.0);
            WikiPathways_2016Nodes.add(newGeneNodeWikiPathways_2016);

            newGeneNodePanther_2015 = generateNode(keyStr, idxPanther_2015, 1, 0.0);
            Panther_2015Nodes.add(newGeneNodePanther_2015);

            newGeneNodePanther_2016 = generateNode(keyStr, idxPanther_2016, 1, 0.0);
            Panther_2016Nodes.add(newGeneNodePanther_2016);

            newGeneNodeKinase_Perturbations_from_GEO = generateNode(keyStr, idxKinase_Perturbations_from_GEO, 1, 0.0);
            Kinase_Perturbations_from_GEONodes.add(newGeneNodeKinase_Perturbations_from_GEO);

            newGeneNodeKinase_Perturbations_from_GEO_up = generateNode(keyStr, idxKinase_Perturbations_from_GEO_up, 1, 0.0);
            Kinase_Perturbations_from_GEO_upNodes.add(newGeneNodeKinase_Perturbations_from_GEO_up);

            newGeneNodeKinase_Perturbations_from_GEO_down = generateNode(keyStr, idxKinase_Perturbations_from_GEO_down, 1, 0.0);
            Kinase_Perturbations_from_GEO_downNodes.add(newGeneNodeKinase_Perturbations_from_GEO_down);

            newGeneNodeLINCS_L1000_Kinase_Perturbations_up = generateNode(keyStr, idxLINCS_L1000_Kinase_Perturbations_up, 1, 0.0);
            LINCS_L1000_Kinase_Perturbations_upNodes.add(newGeneNodeLINCS_L1000_Kinase_Perturbations_up);

            newGeneNodeLINCS_L1000_Kinase_Perturbations_down = generateNode(keyStr, idxLINCS_L1000_Kinase_Perturbations_down, 1, 0.0);
            LINCS_L1000_Kinase_Perturbations_downNodes.add(newGeneNodeLINCS_L1000_Kinase_Perturbations_down);

            idxKEGG_2013 = idxKEGG_2013 + 1;
            idxKEGG_2015 = idxKEGG_2015 + 1;
            idxKEGG_2016 = idxKEGG_2016 + 1;
            idxWikiPathways_2013 = idxWikiPathways_2013 + 1;
            idxWikiPathways_2015 = idxWikiPathways_2015 + 1;
            idxWikiPathways_2016 = idxWikiPathways_2016 + 1;
            idxPanther_2015 = idxPanther_2015 + 1;
            idxPanther_2016 = idxPanther_2016 + 1;
            idxKinase_Perturbations_from_GEO = idxKinase_Perturbations_from_GEO + 1;
            idxKinase_Perturbations_from_GEO_up = idxKinase_Perturbations_from_GEO_up + 1;
            idxKinase_Perturbations_from_GEO_down = idxKinase_Perturbations_from_GEO_down + 1;
            idxLINCS_L1000_Kinase_Perturbations_up = idxLINCS_L1000_Kinase_Perturbations_up + 1;
            idxLINCS_L1000_Kinase_Perturbations_down = idxLINCS_L1000_Kinase_Perturbations_down + 1;
            //idx = idx + 1;


//            log.info("Gene");
//            log.info(keyStr);

            //KEGG_2013
            for(int i = 0; i < KEGG2013List.size(); i++)
            {
                pathway = (String) KEGG2013List.get(i);
                if (! KEGG2013Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKEGG_2013, 2, 0.0);
                    //idx = idx + 1;
                    idxKEGG_2013 = idxKEGG_2013 + 1;
                    KEGG2013Unique.put(pathway,newPathNode);
                    KEGG_2013Nodes.add(KEGG2013Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode.get("idx"));
//                newEdgeNode.put("target", ((JSONObject) KEGG2013Unique.get(pathway)).get("idx"));//["idx"];//.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKEGG_2013.get("idx"), (int)((JSONObject) KEGG2013Unique.get(pathway)).get("idx"));
                KEGG_2013Edges.add(newEdgeNode);

//                This is for parallel layout
//                keyValueTmpKEGG2013 = new JSONObject();
//                keyValueTmpKEGG2013.put("Pathway",pathway);
//                keyValueTmpKEGG2013.put("Gene",keyStr);
//
//                KEGG_2013Parallel.add(keyValueTmpKEGG2013);

            }

            //KEGG_2015
            for(int i = 0; i < KEGG2015List.size(); i++)
            {
                pathway = (String) KEGG2015List.get(i);

                if (! KEGG2015Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKEGG_2015, 2, 0.0);
                    //idx = idx + 1;
                    idxKEGG_2015 = idxKEGG_2015 + 1;
                    KEGG2015Unique.put(pathway,newPathNode);
                    KEGG_2015Nodes.add(KEGG2015Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KEGG2015Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKEGG_2015.get("idx"), (int)((JSONObject) KEGG2015Unique.get(pathway)).get("idx"));
                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KEGG2015Unique.get(pathway));
                KEGG_2015Edges.add(newEdgeNode);

//                This is for parallel layout
//                keyValueTmpKEGG2015 = new JSONObject();
//                keyValueTmpKEGG2015.put("Pathway",pathway);
//                keyValueTmpKEGG2015.put("Gene",keyStr);
//
//                KEGG_2015Parallel.add(keyValueTmpKEGG2015);

            }

            //KEGG_2016
            for(int i = 0; i < KEGG2016List.size(); i++)
            {
                pathway = (String) KEGG2016List.get(i);
//                log.info("pathway");
//                log.info(pathway);
                if (! KEGG2016Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKEGG_2016, 2, 0.0);
                    idxKEGG_2016 = idxKEGG_2016 + 1;
                    //idx = idx + 1;
                    KEGG2016Unique.put(pathway,newPathNode);
                    KEGG_2016Nodes.add(KEGG2016Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KEGG2016Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKEGG_2016.get("idx"), (int)((JSONObject) KEGG2016Unique.get(pathway)).get("idx"));
                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KEGG2016Unique.get(pathway));
                KEGG_2016Edges.add(newEdgeNode);

//                This is for parallel layout
//                keyValueTmpKEGG2016 = new JSONObject();
//                keyValueTmpKEGG2016.put("Pathway",pathway);
//                keyValueTmpKEGG2016.put("Gene",keyStr);
//
//                KEGG_2016Parallel.add(keyValueTmpKEGG2016);
            }

            //WikiPathways2013
            for(int i = 0; i < WikiPathways2013List.size(); i++)
            {
                pathway = (String) WikiPathways2013List.get(i);
                if (! WikiPathways2013Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxWikiPathways_2013, 2, 0.0);
                    //idx = idx + 1;
                    idxWikiPathways_2013 = idxWikiPathways_2013 + 1;
                    WikiPathways2013Unique.put(pathway,newPathNode);
                    WikiPathways_2013Nodes.add(WikiPathways2013Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", WikiPathways2013Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeWikiPathways_2013.get("idx"), (int)((JSONObject) WikiPathways2013Unique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) WikiPathways2013Unique.get(pathway));
                WikiPathways_2013Edges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueTmpKWikiPathways2013List = new JSONObject();
//                keyValueTmpKWikiPathways2013List.put("Pathway",pathway);
//                keyValueTmpKWikiPathways2013List.put("Gene",keyStr);
//
//                WikiPathways_2013Parallel.add(keyValueTmpKWikiPathways2013List);
            }

            //WikiPathways2015
            for(int i = 0; i < WikiPathways2015List.size(); i++)
            {
                pathway = (String) WikiPathways2015List.get(i);
                if (! WikiPathways2015Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxWikiPathways_2015, 2, 0.0);
                    //idx = idx + 1;
                    idxWikiPathways_2015 = idxWikiPathways_2015 + 1;
                    WikiPathways2015Unique.put(pathway,newPathNode);
                    WikiPathways_2015Nodes.add(WikiPathways2015Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", WikiPathways2015Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeWikiPathways_2015.get("idx"), (int)((JSONObject) WikiPathways2015Unique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) WikiPathways2015Unique.get(pathway));

                WikiPathways_2015Edges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueTmpKWikiPathways2015List = new JSONObject();
//                keyValueTmpKWikiPathways2015List.put("Pathway",pathway);
//                keyValueTmpKWikiPathways2015List.put("Gene",keyStr);
//
//                WikiPathways_2015Parallel.add(keyValueTmpKWikiPathways2015List);
            }

            //WikiPathways2016
            for(int i = 0; i < WikiPathways2016List.size(); i++)
            {
                pathway = (String) WikiPathways2016List.get(i);
                if (! WikiPathways2016Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxWikiPathways_2016, 2, 0.0);
                    //idx = idx + 1;
                    idxWikiPathways_2016 = idxWikiPathways_2016 + 1;
                    WikiPathways2016Unique.put(pathway,newPathNode);
                    WikiPathways_2016Nodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", WikiPathways2016Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeWikiPathways_2016.get("idx"), (int)((JSONObject) WikiPathways2016Unique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) WikiPathways2016Unique.get(pathway));
                WikiPathways_2016Edges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueTmpKWikiPathways2016List = new JSONObject();
//                keyValueTmpKWikiPathways2016List.put("Pathway",pathway);
//                keyValueTmpKWikiPathways2016List.put("Gene",keyStr);
//
//                WikiPathways_2016Parallel.add(keyValueTmpKWikiPathways2016List);
            }

            //Panther2015
            for(int i = 0; i < Panther2015List.size(); i++)
            {
                pathway = (String) Panther2015List.get(i);
                if (! Panther2015Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxPanther_2015, 2, 0.0);
                    //idx = idx + 1;
                    idxPanther_2015 = idxPanther_2015 + 1;
                    Panther2015Unique.put(pathway,newPathNode);
                    Panther_2015Nodes.add(Panther2015Unique.get(pathway));
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", Panther2015Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodePanther_2015.get("idx"), (int)((JSONObject) Panther2015Unique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) Panther2015Unique.get(pathway));
                Panther_2015Edges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueTmpKPanther2015List = new JSONObject();
//                keyValueTmpKPanther2015List.put("Pathway",pathway);
//                keyValueTmpKPanther2015List.put("Gene",keyStr);
//
//                Panther_2015Parallel.add(keyValueTmpKPanther2015List);
            }

            //Panther2016
            for(int i = 0; i < Panther2016List.size(); i++)
            {
                pathway = (String) Panther2016List.get(i);
                if (! Panther2016Unique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxPanther_2016, 2, 0.0);
                    //idx = idx + 1;
                    idxPanther_2016 = idxPanther_2016 + 1;
                    Panther2016Unique.put(pathway,newPathNode);
                    Panther_2016Nodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", Panther2016Unique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodePanther_2016.get("idx"), (int)((JSONObject) Panther2016Unique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) Panther2016Unique.get(pathway));
                Panther_2016Edges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueTmpPanther2016List = new JSONObject();
//                keyValueTmpPanther2016List.put("Pathway",pathway);
//                keyValueTmpPanther2016List.put("Gene",keyStr);
//
//                Panther_2016Parallel.add(keyValueTmpPanther2016List);
            }

            //KinasePerturbations_from_GEOList
            for(int i = 0; i < KinasePerturbations_from_GEOList.size(); i++)
            {

                pathway = (String) KinasePerturbations_from_GEOList.get(i);
                if (! KinasePerturbationsfromGEOUnique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKinase_Perturbations_from_GEO, 2, 0.0);//200, (int)KinasePerturbationsfromGEOPathValue.get(pathway), 2);
                    //idx = idx + 1;
                    idxKinase_Perturbations_from_GEO = idxKinase_Perturbations_from_GEO + 1;
                    KinasePerturbationsfromGEOUnique.put(pathway,newPathNode);
                    Kinase_Perturbations_from_GEONodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KinasePerturbationsfromGEOUnique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKinase_Perturbations_from_GEO.get("idx"), (int)((JSONObject) KinasePerturbationsfromGEOUnique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KinasePerturbationsfromGEOUnique.get(pathway));
                Kinase_Perturbations_from_GEOEdges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueKinasePerturbations_from_GEOList = new JSONObject();
//                keyValueKinasePerturbations_from_GEOList.put("Pathway",pathway);
//                keyValueKinasePerturbations_from_GEOList.put("Gene",keyStr);
//
//                Kinase_Perturbations_from_GEOParallel.add(keyValueKinasePerturbations_from_GEOList);
            }

            //KinasePerturbations_from_GEO_upList
            for(int i = 0; i < KinasePerturbations_from_GEO_upList.size(); i++)
            {

                pathway = (String) KinasePerturbations_from_GEO_upList.get(i);
                if (! KinasePerturbationsfromGEOUpUnique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKinase_Perturbations_from_GEO_up, 2, 0.0);//200, (int)KinasePerturbationsfromGEOPathValue.get(pathway), 2);
                    //idx = idx + 1;
                    idxKinase_Perturbations_from_GEO_up = idxKinase_Perturbations_from_GEO_up + 1;
                    KinasePerturbationsfromGEOUpUnique.put(pathway,newPathNode);
                    Kinase_Perturbations_from_GEO_upNodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KinasePerturbationsfromGEOUnique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKinase_Perturbations_from_GEO_up.get("idx"), (int)((JSONObject) KinasePerturbationsfromGEOUpUnique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KinasePerturbationsfromGEOUnique.get(pathway));
                Kinase_Perturbations_from_GEO_upEdges.add(newEdgeNode);


                //                This is for parallel layout
//                keyValueKinasePerturbations_from_GEO_upList = new JSONObject();
//                keyValueKinasePerturbations_from_GEO_upList.put("Pathway",pathway);
//                keyValueKinasePerturbations_from_GEO_upList.put("Gene",keyStr);
//
//                Kinase_Perturbations_from_GEO_upParallel.add(keyValueKinasePerturbations_from_GEO_upList);
            }

            //KinasePerturbations_from_GEO_downList
            for(int i = 0; i < KinasePerturbations_from_GEO_downList.size(); i++)
            {

                pathway = (String) KinasePerturbations_from_GEO_downList.get(i);
                if (! KinasePerturbationsfromGEODownUnique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxKinase_Perturbations_from_GEO_down, 2, 0.0);//200, (int)KinasePerturbationsfromGEOPathValue.get(pathway), 2);
                    //idx = idx + 1;
                    idxKinase_Perturbations_from_GEO_down = idxKinase_Perturbations_from_GEO_down + 1;
                    KinasePerturbationsfromGEODownUnique.put(pathway,newPathNode);
                    Kinase_Perturbations_from_GEO_downNodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KinasePerturbationsfromGEOUnique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeKinase_Perturbations_from_GEO_down.get("idx"), (int)((JSONObject) KinasePerturbationsfromGEODownUnique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KinasePerturbationsfromGEOUnique.get(pathway));
                Kinase_Perturbations_from_GEO_downEdges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueKinasePerturbations_from_GEO_downList = new JSONObject();
//                keyValueKinasePerturbations_from_GEO_downList.put("Pathway",pathway);
//                keyValueKinasePerturbations_from_GEO_downList.put("Gene",keyStr);
//
//                Kinase_Perturbations_from_GEO_downParallel.add(keyValueKinasePerturbations_from_GEO_downList);

            }

            //LINCS_L1000_Kinase_Perturbations_up
            for(int i = 0; i < LINCS_L1000_Kinase_Perturbations_upList.size(); i++)
            {

                pathway = (String) LINCS_L1000_Kinase_Perturbations_upList.get(i);
                if (! LINCSL1000KinasePerturbationsUpUnique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxLINCS_L1000_Kinase_Perturbations_up, 2, 0.0);//200, (int)KinasePerturbationsfromGEOPathValue.get(pathway), 2);
                    //idx = idx + 1;
                    idxLINCS_L1000_Kinase_Perturbations_up = idxLINCS_L1000_Kinase_Perturbations_up + 1;
                    LINCSL1000KinasePerturbationsUpUnique.put(pathway,newPathNode);
                    LINCS_L1000_Kinase_Perturbations_upNodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KinasePerturbationsfromGEOUnique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeLINCS_L1000_Kinase_Perturbations_up.get("idx"), (int)((JSONObject) LINCSL1000KinasePerturbationsUpUnique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KinasePerturbationsfromGEOUnique.get(pathway));
                LINCS_L1000_Kinase_Perturbations_upEdges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueLINCS_L1000_Kinase_Perturbations_upList = new JSONObject();
//
//                keyValueLINCS_L1000_Kinase_Perturbations_upList.put("Pathway",pathway);
//                keyValueLINCS_L1000_Kinase_Perturbations_upList.put("Gene",keyStr);
//                LINCS_L1000_Kinase_Perturbations_upParallel.add(keyValueLINCS_L1000_Kinase_Perturbations_upList);


            }

            //LINCS_L1000_Kinase_Perturbations_down
            for(int i = 0; i < LINCS_L1000_Kinase_Perturbations_downList.size(); i++)
            {

                pathway = (String) LINCS_L1000_Kinase_Perturbations_downList.get(i);
                if (! LINCSL1000KinasePerturbationsDownUnique.containsKey(pathway)) {
                    newPathNode = generateNode(pathway, idxLINCS_L1000_Kinase_Perturbations_down, 2, 0.0);//200, (int)KinasePerturbationsfromGEOPathValue.get(pathway), 2);
                    //idx = idx + 1;
                    idxLINCS_L1000_Kinase_Perturbations_down = idxLINCS_L1000_Kinase_Perturbations_down + 1;
                    LINCSL1000KinasePerturbationsDownUnique.put(pathway,newPathNode);
                    LINCS_L1000_Kinase_Perturbations_downNodes.add(newPathNode);
                }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode);
//                newEdgeNode.put("target", KinasePerturbationsfromGEOUnique.get(pathway));
                newEdgeNode = generateEdgeNode((int)newGeneNodeLINCS_L1000_Kinase_Perturbations_down.get("idx"), (int)((JSONObject) LINCSL1000KinasePerturbationsDownUnique.get(pathway)).get("idx"));

                //newEdgeNode = generateEdgeNode(newGeneNode, (JSONObject) KinasePerturbationsfromGEOUnique.get(pathway));
                LINCS_L1000_Kinase_Perturbations_downEdges.add(newEdgeNode);

                //                This is for parallel layout
//                keyValueLINCS_L1000_Kinase_Perturbations_downList = new JSONObject();
//                keyValueLINCS_L1000_Kinase_Perturbations_downList.put("Pathway",pathway);
//                keyValueLINCS_L1000_Kinase_Perturbations_downList.put("Gene",keyStr);
//
//                LINCS_L1000_Kinase_Perturbations_downParallel.add(keyValueLINCS_L1000_Kinase_Perturbations_downList);

            }


        }



        //System.out.println(KEGG2013PathValue);
        //System.out.println(KEGG2015PathValue);
        //System.out.println(KEGG2016PathValue);

        //==============================================

//        KEGG_2013.put("parallel", KEGG_2013Parallel);
//        KEGG_2015.put("parallel", KEGG_2015Parallel);
//        KEGG_2016.put("parallel", KEGG_2016Parallel);
//        WikiPathways_2013.put("parallel", WikiPathways_2013Parallel);
//        WikiPathways_2015.put("parallel", WikiPathways_2015Parallel);
//        WikiPathways_2016.put("parallel", WikiPathways_2016Parallel);
//        Panther_2015.put("parallel", Panther_2015Parallel);
//        Panther_2016.put("parallel", Panther_2016Parallel);
//        Kinase_Perturbations_from_GEO.put("parallel", Kinase_Perturbations_from_GEOParallel);
//        Kinase_Perturbations_from_GEO_up.put("parallel", Kinase_Perturbations_from_GEO_upParallel);
//        Kinase_Perturbations_from_GEO_down.put("parallel", Kinase_Perturbations_from_GEO_downParallel);
//        LINCS_L1000_Kinase_Perturbations_up.put("parallel", LINCS_L1000_Kinase_Perturbations_upParallel);
//        LINCS_L1000_Kinase_Perturbations_down.put("parallel", LINCS_L1000_Kinase_Perturbations_downParallel);


        //=======Changing jsonarray to list because of the customizedCircularView
//        //==============================================
//
//        KEGG_2013.put("circular", KEGG_2013CircularList);
//        KEGG_2015.put("circular", KEGG_2015CircularList);
//        KEGG_2016.put("circular", KEGG_2016CircularList);
//        WikiPathways_2013.put("circular", WikiPathways_2013CircularList);
//        WikiPathways_2015.put("circular", WikiPathways_2015CircularList);
//        WikiPathways_2016.put("circular", WikiPathways_2016CircularList);
//        Panther_2015.put("circular", Panther_2015CircularList);
//        Panther_2016.put("circular", Panther_2016CircularList);
//        Kinase_Perturbations_from_GEO.put("circular", Kinase_Perturbations_from_GEOCircularList);
//        Kinase_Perturbations_from_GEO_up.put("circular", Kinase_Perturbations_from_GEO_upCircularList);
//        Kinase_Perturbations_from_GEO_down.put("circular", Kinase_Perturbations_from_GEO_downCircularList);
//        LINCS_L1000_Kinase_Perturbations_up.put("circular", LINCS_L1000_Kinase_Perturbations_upCircularList);
//        LINCS_L1000_Kinase_Perturbations_down.put("circular", LINCS_L1000_Kinase_Perturbations_downCircularList);


        //==============================================

//        KEGG_2013.put("circular", KEGG_2013Circular);
//        KEGG_2015.put("circular", KEGG_2015Circular);
//        KEGG_2016.put("circular", KEGG_2016Circular);
//        WikiPathways_2013.put("circular", WikiPathways_2013Circular);
//        WikiPathways_2015.put("circular", WikiPathways_2015Circular);
//        WikiPathways_2016.put("circular", WikiPathways_2016Circular);
//        Panther_2015.put("circular", Panther_2015Circular);
//        Panther_2016.put("circular", Panther_2016Circular);
//        Kinase_Perturbations_from_GEO.put("circular", Kinase_Perturbations_from_GEOCircular);
//        Kinase_Perturbations_from_GEO_up.put("circular", Kinase_Perturbations_from_GEO_upCircular);
//        Kinase_Perturbations_from_GEO_down.put("circular", Kinase_Perturbations_from_GEO_downCircular);
//        LINCS_L1000_Kinase_Perturbations_up.put("circular", LINCS_L1000_Kinase_Perturbations_upCircular);
//        LINCS_L1000_Kinase_Perturbations_down.put("circular", LINCS_L1000_Kinase_Perturbations_downCircular);

        //==============================================

        KEGG_2013.put("nodes", KEGG_2013Nodes);
        KEGG_2013.put("edges", KEGG_2013Edges);
        KEGG_2015.put("nodes", KEGG_2015Nodes);
        KEGG_2015.put("edges", KEGG_2015Edges);
        KEGG_2016.put("nodes", KEGG_2016Nodes);
        KEGG_2016.put("edges", KEGG_2016Edges);
        WikiPathways_2013.put("nodes", WikiPathways_2013Nodes);
        WikiPathways_2013.put("edges", WikiPathways_2013Edges);
        WikiPathways_2015.put("nodes", WikiPathways_2015Nodes);
        WikiPathways_2015.put("edges", WikiPathways_2015Edges);
        WikiPathways_2016.put("nodes", WikiPathways_2016Nodes);
        WikiPathways_2016.put("edges", WikiPathways_2016Edges);
        Panther_2015.put("nodes", Panther_2015Nodes);
        Panther_2015.put("edges", Panther_2015Edges);
        Panther_2016.put("nodes", Panther_2016Nodes);
        Panther_2016.put("edges", Panther_2016Edges);
        Kinase_Perturbations_from_GEO.put("nodes", Kinase_Perturbations_from_GEONodes);
        Kinase_Perturbations_from_GEO.put("edges", Kinase_Perturbations_from_GEOEdges);
        Kinase_Perturbations_from_GEO_up.put("nodes", Kinase_Perturbations_from_GEO_upNodes);
        Kinase_Perturbations_from_GEO_up.put("edges", Kinase_Perturbations_from_GEO_upEdges);
        Kinase_Perturbations_from_GEO_down.put("nodes", Kinase_Perturbations_from_GEO_downNodes);
        Kinase_Perturbations_from_GEO_down.put("edges", Kinase_Perturbations_from_GEO_downEdges);
        LINCS_L1000_Kinase_Perturbations_up.put("nodes", LINCS_L1000_Kinase_Perturbations_upNodes);
        LINCS_L1000_Kinase_Perturbations_up.put("edges", LINCS_L1000_Kinase_Perturbations_upEdges);
        LINCS_L1000_Kinase_Perturbations_down.put("nodes", LINCS_L1000_Kinase_Perturbations_downNodes);
        LINCS_L1000_Kinase_Perturbations_down.put("edges", LINCS_L1000_Kinase_Perturbations_downEdges);
        //==============================================
        network.put("KEGG_2013", KEGG_2013);
        network.put("KEGG_2015", KEGG_2015);
        network.put("KEGG_2016", KEGG_2016);
        network.put("WikiPathways_2013", WikiPathways_2013);
        network.put("WikiPathways_2015", WikiPathways_2015);
        network.put("WikiPathways_2016", WikiPathways_2016);
        network.put("Panther_2015", Panther_2015);
        network.put("Panther_2016", Panther_2016);
        network.put("Kinase_Perturbations_from_GEO", Kinase_Perturbations_from_GEO);
        network.put("Kinase_Perturbations_from_GEO_up", Kinase_Perturbations_from_GEO_up);
        network.put("Kinase_Perturbations_from_GEO_down", Kinase_Perturbations_from_GEO_down);
//        network.put("LINCS_L1000_Kinase_Perturbations_up", LINCS_L1000_Kinase_Perturbations_up);
//        network.put("LINCS_L1000_Kinase_Perturbations_down", LINCS_L1000_Kinase_Perturbations_down);

        log.info("First Network(KEGG, ...)");
        log.info(network.toString());
        return network;
    }



    public JSONObject computeNetwork2(JSONObject input) {

        int idx = 0;
        List<String> pathways = new ArrayList<String>();

        System.out.println("computing network for gene-pathway association");
//        if (pathwayClass.equals("pathways")){


            pathways.add("KEGG_2015");
            pathways.add("WikiPathways_2015");
            pathways.add("Panther_2015");
            pathways.add("KEGG_2016");
            pathways.add("WikiPathways_2016");
            pathways.add("ARCHS4_Kinases_Coexp");
            pathways.add("Reactome_2016");
            pathways.add("BioCarta_2016");
            pathways.add("Humancyc_2016");
            pathways.add("NCI-Nature_2016");
            pathways.add("Panther_2016");
            pathways.add("BioPlex_2017");

            pathways.add("huMAP");
            pathways.add("PPI_Hub_Proteins");
            pathways.add("KEA_2015");
            pathways.add("LINCS_L1000_Kinase_Perturbations_down");
            pathways.add("LINCS_L1000_Kinase_Perturbations_up");
            pathways.add("Kinase_Perturbations_from_GEO_down");
            pathways.add("Kinase_Perturbations_from_GEO_up");
            pathways.add("NURSA_Human_Endogenous_Complexome");
            pathways.add("CORUM");
            pathways.add("SILAC_Phosphoproteomics");
            pathways.add("Phosphatase_Substrates_from_DEPOD");
//        }
//        if (pathwayClass.equals("diseaseDrugs")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("LINCS_L1000_Chem_Pert_up");
            pathways.add("LINCS_L1000_Chem_Pert_down");
            pathways.add("LINCS_L1000_Ligand_Perturbations_up");

            pathways.add("LINCS_L1000_Ligand_Perturbations_down");
            pathways.add("ARCHS4_IDG_Coexp");
            pathways.add("DrugMatrix");

            pathways.add("Old_CMAP_up");
            pathways.add("Old_CMAP_down");
            pathways.add("GeneSigDB");

            pathways.add("OMIM_Disease");
            pathways.add("OMIM_Expanded");
            pathways.add("VirusMINT");

            pathways.add("MSigDB_Computational");
            pathways.add("MSigDB_Oncogenic_Signatures");
            pathways.add("Virus_Perturbations_from_GEO_up");

            pathways.add("Virus_Perturbations_from_GEO_down");
            pathways.add("Achilles_fitness_increase");
            pathways.add("Achilles_fitness_decrease");
            pathways.add("dbGaP");



//        }
//
//        if (pathwayClass.equals("ontology")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("GO_Cellular_Component_2017b");
            pathways.add("GO_Biological_Process_2017b");
            pathways.add("GO_Molecular_Function_2017b");

            pathways.add("MGI_Mammalian_Phenotype_2017");
            pathways.add("Human_Phenotype_Ontology");
            pathways.add("Jensen_TISSUES");

            pathways.add("Jensen_COMPARTMENTS");
            pathways.add("Jensen_DISEASES");

//            ));

//        }
//
//        if (pathwayClass.equals("transcription")){
//            List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("ChEA_2016");
            pathways.add("TRANSFAC_and_JASPAR_PWMs");
            pathways.add("ARCHS4_TFs_Coexp");

            pathways.add("Genome_Browser_PWMs");
            pathways.add("ENCODE_and_ChEA_Consensus_TFs_from_ChIP-X");
            pathways.add("Epigenomics_Roadmap_HM_ChIP-seq");

            pathways.add("TargetScan_microRNA");
            pathways.add("ENCODE_TF_ChIP-seq_2015");
            pathways.add("TF-LOF_Expression_from_GEO");

            pathways.add("ENCODE_Histone_Modifications_2015");
            pathways.add("Transcription_Factor_PPIs");

            //));

//        }
//
//        if (pathwayClass.equals("cellType")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("Human_Gene_Atlas");
            pathways.add("Mouse_Gene_Atlas");
            pathways.add("ARCHS4_Tissues");

            pathways.add("ARCHS4_Cell-lines");
            pathways.add("Allen_Brain_Atlas_up");
            pathways.add("Allen_Brain_Atlas_down");

            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_up");
            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_down");
            pathways.add("Cancer_Cell_Line_Encyclopedia");

            pathways.add("NCI-60_Cancer_Cell_Lines");
            pathways.add("Tissue_Protein_Expression_from_ProteomicsDB");
            pathways.add("Tissue_Protein_Expression_from_Human_Proteome_Map");
            pathways.add("ESCAPE");

            //));

//        }
        JSONObject newNode = new JSONObject();
        JSONObject newPathNode = new JSONObject();
        JSONObject newEdgeNode = new JSONObject();
        JSONObject network = new JSONObject();
        //System.out.println(String.valueOf(pathways));
        for (int index = 0; index < pathways.size(); index++) {
            String queryPathway = pathways.get(index);
            //System.out.println(queryPathway);
            idx = 0;
            JSONObject unique = new JSONObject();
            JSONArray nodes = new JSONArray();
            JSONArray edges = new JSONArray();
            for (Object key : input.keySet()) {
                //based on you key types
                String keyStr = (String) key;
                JSONObject keyValue = (JSONObject) input.get(keyStr);

                String pathway;

                if (keyValue.containsKey(queryPathway)) {
                    JSONArray queryList = (JSONArray) keyValue.get(queryPathway);
                    //JSONArray queryList = (JSONArray) (keyValue.get(queryPathway));


                    newNode = generateNode(keyStr, idx, 1, 0.0);
                    idx = idx + 1;
                    nodes.add(newNode);

                    for (int i = 0; i < queryList.size(); i++) {
                        pathway = (String) queryList.get(i);
                        if (!unique.containsKey(pathway)) {
                            newPathNode = generateNode(pathway, idx, 2, 0.0);
                            idx = idx + 1;

                            unique.put(pathway, newPathNode);
                            nodes.add(unique.get(pathway));
                        }
                        newEdgeNode = generateEdgeNode((int) newNode.get("idx"), (int) ((JSONObject) unique.get(pathway)).get("idx"));
                        edges.add(newEdgeNode);

                    }
                }
            }
            JSONObject nodeEdgeJson = new JSONObject();
            nodeEdgeJson.put("nodes", nodes);
            nodeEdgeJson.put("edges", edges);
            //==============================================
            network.put(queryPathway, nodeEdgeJson);
        }

        System.out.println("Network is computed");
//        System.out.println(network.toString());
        return network;
    }

    public JSONObject computeNetwork3(String pathwayClass, JSONObject input) {

        int idx = 0;
        List<String> pathways = new ArrayList<String>();

        System.out.println("computing network for gene-pathway association");
        if (pathwayClass.equals("pathways")){


            pathways.add("KEGG_2015");
            pathways.add("WikiPathways_2015");
            pathways.add("Panther_2015");
            pathways.add("KEGG_2016");
            pathways.add("WikiPathways_2016");
            pathways.add("ARCHS4_Kinases_Coexp");
            pathways.add("Reactome_2016");
            pathways.add("BioCarta_2016");
            pathways.add("Humancyc_2016");
            pathways.add("NCI-Nature_2016");
            pathways.add("Panther_2016");
            pathways.add("BioPlex_2017");

            pathways.add("huMAP");
            pathways.add("PPI_Hub_Proteins");
            pathways.add("KEA_2015");
            pathways.add("LINCS_L1000_Kinase_Perturbations_down");
            pathways.add("LINCS_L1000_Kinase_Perturbations_up");
            pathways.add("Kinase_Perturbations_from_GEO_down");
            pathways.add("Kinase_Perturbations_from_GEO_up");
            pathways.add("NURSA_Human_Endogenous_Complexome");
            pathways.add("CORUM");
            pathways.add("SILAC_Phosphoproteomics");
            pathways.add("Phosphatase_Substrates_from_DEPOD");
        }
        if (pathwayClass.equals("diseaseDrugs")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("LINCS_L1000_Chem_Pert_up");
            pathways.add("LINCS_L1000_Chem_Pert_down");
            pathways.add("LINCS_L1000_Ligand_Perturbations_up");

            pathways.add("LINCS_L1000_Ligand_Perturbations_down");
            pathways.add("ARCHS4_IDG_Coexp");
            pathways.add("DrugMatrix");

            pathways.add("Old_CMAP_up");
            pathways.add("Old_CMAP_down");
            pathways.add("GeneSigDB");

            pathways.add("OMIM_Disease");
            pathways.add("OMIM_Expanded");
            pathways.add("VirusMINT");

            pathways.add("MSigDB_Computational");
            pathways.add("MSigDB_Oncogenic_Signatures");
            pathways.add("Virus_Perturbations_from_GEO_up");

            pathways.add("Virus_Perturbations_from_GEO_down");
            pathways.add("Achilles_fitness_increase");
            pathways.add("Achilles_fitness_decrease");
            pathways.add("dbGaP");



        }

        if (pathwayClass.equals("ontology")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("GO_Cellular_Component_2017b");
            pathways.add("GO_Biological_Process_2017b");
            pathways.add("GO_Molecular_Function_2017b");

            pathways.add("MGI_Mammalian_Phenotype_2017");
            pathways.add("Human_Phenotype_Ontology");
            pathways.add("Jensen_TISSUES");

            pathways.add("Jensen_COMPARTMENTS");
            pathways.add("Jensen_DISEASES");

//            ));

        }

        if (pathwayClass.equals("transcription")){
//            List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("ChEA_2016");
            pathways.add("TRANSFAC_and_JASPAR_PWMs");
            pathways.add("ARCHS4_TFs_Coexp");

            pathways.add("Genome_Browser_PWMs");
            pathways.add("ENCODE_and_ChEA_Consensus_TFs_from_ChIP-X");
            pathways.add("Epigenomics_Roadmap_HM_ChIP-seq");

            pathways.add("TargetScan_microRNA");
            pathways.add("ENCODE_TF_ChIP-seq_2015");
            pathways.add("TF-LOF_Expression_from_GEO");

            pathways.add("ENCODE_Histone_Modifications_2015");
            pathways.add("Transcription_Factor_PPIs");

            //));

        }

        if (pathwayClass.equals("cellType")){
            //List<String> pathways = new ArrayList<>(Arrays.asList(
            pathways.add("Human_Gene_Atlas");
            pathways.add("Mouse_Gene_Atlas");
            pathways.add("ARCHS4_Tissues");

            pathways.add("ARCHS4_Cell-lines");
            pathways.add("Allen_Brain_Atlas_up");
            pathways.add("Allen_Brain_Atlas_down");

            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_up");
            pathways.add("GTEx_Tissue_Sample_Gene_Expression_Profiles_down");
            pathways.add("Cancer_Cell_Line_Encyclopedia");

            pathways.add("NCI-60_Cancer_Cell_Lines");
            pathways.add("Tissue_Protein_Expression_from_ProteomicsDB");
            pathways.add("Tissue_Protein_Expression_from_Human_Proteome_Map");
            pathways.add("ESCAPE");

            //));

        }
        JSONObject newNode = new JSONObject();
        JSONObject newPathNode = new JSONObject();
        JSONObject newEdgeNode = new JSONObject();
        JSONObject network = new JSONObject();
        //System.out.println(String.valueOf(pathways));
        for (int index = 0; index < pathways.size(); index++) {
            String queryPathway = pathways.get(index);
            //System.out.println(queryPathway);
            idx = 0;
            JSONObject unique = new JSONObject();
            JSONArray nodes = new JSONArray();
            JSONArray edges = new JSONArray();
            for (Object key : input.keySet()) {
                //based on you key types
                String keyStr = (String) key;
                JSONObject keyValue = (JSONObject) input.get(keyStr);

                String pathway;

                if (keyValue.containsKey(queryPathway)) {
                    JSONArray queryList = (JSONArray) keyValue.get(queryPathway);
                    //JSONArray queryList = (JSONArray) (keyValue.get(queryPathway));


                    newNode = generateNode(keyStr, idx, 1, 0.0);
                    idx = idx + 1;
                    nodes.add(newNode);

                    for (int i = 0; i < queryList.size(); i++) {
                        pathway = (String) queryList.get(i);
                        if (!unique.containsKey(pathway)) {
                            newPathNode = generateNode(pathway, idx, 2, 0.0);
                            idx = idx + 1;

                            unique.put(pathway, newPathNode);
                            nodes.add(unique.get(pathway));
                        }
                        newEdgeNode = generateEdgeNode((int) newNode.get("idx"), (int) ((JSONObject) unique.get(pathway)).get("idx"));
                        edges.add(newEdgeNode);

                    }
                }
            }
            JSONObject nodeEdgeJson = new JSONObject();
            nodeEdgeJson.put("nodes", nodes);
            nodeEdgeJson.put("edges", edges);
            //==============================================
            network.put(queryPathway, nodeEdgeJson);
        }

        System.out.println("Network is computed");
//        System.out.println(network.toString());
        return network;
    }



    JSONObject generateEdgeNode(int sourceTag, int targetTag)
    {
        JSONObject node = new JSONObject();
        node.put("source", sourceTag);
        node.put("target", targetTag);
        node.put("weight", 1);
        //node.put("value", 1);
        //node.put("weight", 1);
        return node;
    }



    JSONObject generateNode(String name, int idx, int tag, double value)//, float center, int value)
    {
        JSONObject node = new JSONObject();
//        Random random = new Random();
//        double rand = random.nextDouble();
//        double scaled = rand * 400;
        if (name.length() > 33) {
            node.put("name", name.substring(0, 30) + "...");
        }else{
            node.put("name", name);
        }

        node.put("full_name", name);
        node.put("idx", idx);
        //node.put("px", center + scaled);
        //node.put("x", center + scaled);
        //rand = random.nextDouble();
        //scaled = rand * 200;
        //node.put("y", center + scaled);
        //node.put("py", center + scaled);
        node.put("value",value);
        node.put("group",tag);
        node.put("weight",0);
        return node;
    }


}
