package edu.uc.eh.service;

import edu.uc.eh.utils.UtilsIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by behrouz on 11/19/16.
 */
@Service
public class KinaseService {

    private static final Logger log = LoggerFactory.getLogger(KinaseService.class);

    @Value("${resources.kinase2GeneHuman}")
    String kinase2GeneHumanInfo;

    @Value("${resources.gene2KinaseHuman}")
    String gene2KinaseHumanInfo;

    @Value("${resources.geneName2KinaseName}")
    String geneName2KinaseNameHumanInfo;

//    kinase2GeneHuman: /kinase/kinase2Gene_human.json
//    gene2KinaseHuman: /kinase/gene2Kinase_human.json
//    geneName2KinaseName: /kinase/geneName2KinaseName_human.json



    public JSONObject computeKinaseNetwork(String[] input) {

        System.out.println("In computeKinaseNetwork");

        int idx = 0;
        String kinase;
        String gene;
        String inputGene;
        String gene2KinaseString;
        String kinase2GeneString;
        String geneName2KinaseNAmeString;
        String kinaseMap;
        ArrayList inutArray = new ArrayList();
        JSONObject newNode = new JSONObject();
        JSONObject newEdgeNode = new JSONObject();
        Map<String,String> map = new HashMap<String,String>();

        JSONObject gene2KinaseJson = new JSONObject();
        JSONObject kinase2GeneJson = new JSONObject();
        JSONObject geneName2KinaseNAmeJson = new JSONObject();

        JSONObject network = new JSONObject();
        JSONObject nodeUnique = new JSONObject();
        JSONObject nodeUniqueTable = new JSONObject();
        JSONArray edges = new JSONArray();
        JSONArray nodes = new JSONArray();

        JSONArray gene2KinaseList = new JSONArray();
        JSONArray kinase2GeneList = new JSONArray();


        try {
//            kinase2GeneJson = UtilsIO.getInstance().readJsonFile(kinase2GeneHumanInfo);
//            //kinase2GeneString = UtilsNetwork.getInstance().readUrlXml(kinase2GeneHumanInfo);
//            log.info(kinase2GeneString);
//            JSONParser parser = new JSONParser();
//            kinase2GeneJson = (JSONObject) parser.parse(kinase2GeneString);
            kinase2GeneJson = UtilsIO.getInstance().readJsonFile(kinase2GeneHumanInfo);
            //log.info(kinase2GeneJson.toString());

        } catch (Exception e) {
            String msg =  String.format("Error in obtaining kinase2GeneHumanInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        try {
//            gene2KinaseString = UtilsNetwork.getInstance().readUrlXml(gene2KinaseHumanInfo);
//            JSONParser parser = new JSONParser();
//            gene2KinaseJson = (JSONObject) parser.parse(gene2KinaseString);

            gene2KinaseJson = UtilsIO.getInstance().readJsonFile(gene2KinaseHumanInfo);
            //log.info(gene2KinaseJson.toString());
        } catch (Exception e) {
            String msg =  String.format("Error in obtaining gene2KinaseHumanInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }


        try {
//            geneName2KinaseNAmeString = UtilsNetwork.getInstance().readUrlXml(geneName2KinaseNameHumanInfo);
//            JSONParser parser = new JSONParser();
//            geneName2KinaseNAmeJson = (JSONObject) parser.parse(geneName2KinaseNAmeString);

            geneName2KinaseNAmeJson = UtilsIO.getInstance().readJsonFile(geneName2KinaseNameHumanInfo);
            //log.info(geneName2KinaseNAmeJson.toString());

            for(Iterator iterator = geneName2KinaseNAmeJson.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String value = (String) geneName2KinaseNAmeJson.get(key);
                //System.out.println(key);
                //System.out.println(geneName2KinaseNAmeJson.get(key));
                map.put(key,value);
            }

        } catch (Exception e) {
            String msg =  String.format("Error in obtaining geneName2KinaseNameHumanInfo");
            log.warn(msg);
            throw new RuntimeException(msg);
        }

        for (int i = 0; i < input.length; i++) {
            inutArray.add(input[i]);
        }

        //First only add the input genes
        for (int i = 0; i < input.length; i++) {
            log.info("inputgene");
            System.out.println(input[i]);
            inputGene = input[i];
            //Add new node if not existed to nodeunique
            if (!nodeUnique.containsKey(inputGene)) {
                if (map.containsKey(inputGene)) {
                    kinaseMap = map.get(inputGene).toString();
                } else {
                    kinaseMap = "";
                }
                newNode = generateNode(inputGene, kinaseMap, idx, 0);//tag 0 is for grey
                idx = idx + 1;
                nodes.add(newNode);
                nodeUnique.put(inputGene, newNode);

                JSONObject nodeUniqueTableElem = new JSONObject();
                nodeUniqueTableElem.put("gene", inputGene.toUpperCase());
//                nodeUniqueTableElem.put("upstream", "");
//                nodeUniqueTableElem.put("downstream", "");

                nodeUniqueTable.put(inputGene.toUpperCase(), nodeUniqueTableElem);
            }
        }






        for (int i = 0; i < input.length; i++) {
            log.info("inputgene");
            System.out.println(input[i]);
            gene2KinaseList = new JSONArray();
            kinase2GeneList = new JSONArray();

            inputGene = input[i];

            if (kinase2GeneJson.containsKey(inputGene.toUpperCase())) {
                log.info("kinase2GeneList");
                kinase2GeneList = (JSONArray) (kinase2GeneJson.get(inputGene.toUpperCase()));

                log.info(kinase2GeneList.toString());
                ((JSONObject) nodeUniqueTable.get(inputGene.toUpperCase())).put("downstream", kinase2GeneList);

            }
            else{
                ((JSONObject) nodeUniqueTable.get(inputGene.toUpperCase())).put("downstream", new JSONArray());
            }

            if (gene2KinaseJson.containsKey(inputGene.toUpperCase())) {
                log.info("gene2KinaseList");
                gene2KinaseList = (JSONArray) (gene2KinaseJson.get(inputGene.toUpperCase()));
                log.info(gene2KinaseList.toString());
                ((JSONObject) nodeUniqueTable.get(inputGene.toUpperCase())).put("upstream", gene2KinaseList);
            }
            else{
                ((JSONObject) nodeUniqueTable.get(inputGene.toUpperCase())).put("upstream", new JSONArray());
            }

//kinase2Gene ========================================
//kinase2Gene ========================================
            for (int j = 0; j < kinase2GeneList.size(); j++) {
                gene = (String) kinase2GeneList.get(j);
//                log.info("gene-1");
//                log.info(gene);
                if (!nodeUnique.containsKey(gene)) {
                    //log.info(gene);
                    if (map.containsKey(gene))
                    {
                        kinaseMap = map.get(gene).toString();
                    }
                    else
                    {
                        kinaseMap = "";
                    }
                    if (inutArray.contains(gene)){
                        newNode = generateNode(gene, kinaseMap, idx, 0);
                        idx = idx + 1;

                        }
                    else{
                        newNode = generateNode(gene, kinaseMap, idx, 1);//tag 1 is for green nodes that are phosphorelated by our query genes
                        idx = idx + 1;
                    }

                    nodeUnique.put(gene, newNode);
                    nodes.add(newNode);
                }
             newEdgeNode = generateEdgeNode((int) ((JSONObject) nodeUnique.get(inputGene)).get("idx"), (int) ((JSONObject) nodeUnique.get(gene)).get("idx"), 1);
                edges.add(newEdgeNode);
            }
//Gene2Kinase ========================================
//Gene2Kinase ========================================
            for (int j = 0; j < gene2KinaseList.size(); j++) {
                gene = (String) gene2KinaseList.get(j);
//                log.info("gene-2");
//                log.info(gene);
                if (!nodeUnique.containsKey(gene)) {
                    //log.info(gene);
                    if (map.containsKey(gene))
                    {
                        kinaseMap = map.get(gene).toString();
                    }
                    else
                    {
                        kinaseMap = "";
                    }
                    if (inutArray.contains(gene)){
                        newNode = generateNode(gene, kinaseMap, idx, 0);
                        idx = idx + 1;
                    }
                    else{
                        newNode = generateNode(gene, kinaseMap, idx, 2);//tag 2 is for red nodes that phosphorelate the query genes
                        idx = idx + 1;
                    }

                    nodeUnique.put(gene, newNode);
                    nodes.add(newNode);
                }
                newEdgeNode = generateEdgeNode((int) ((JSONObject) nodeUnique.get(gene)).get("idx"), (int) ((JSONObject) nodeUnique.get(inputGene)).get("idx"), 2);
                edges.add(newEdgeNode);
            }
        }
        //==============================================
        JSONArray table = new JSONArray();
        for(Object key : nodeUniqueTable.keySet()) {

            String keyStr = (String) key;


            Object keyvalue = nodeUniqueTable.get(keyStr);
            table.add(keyvalue);

        }

        JSONObject output = new JSONObject();
        network.put("nodes", nodes);
        network.put("edges", edges);

        output.put("table", table);
        output.put("network", network);
        //log.info("Kinase Network");
        System.out.println(network.toString());
        //System.out.println(nodeUniqueTable.toJSONString());
        return output;
    }





    JSONObject generateEdgeNode(int sourceTag, int targetTag, int tag)
    {
        JSONObject node = new JSONObject();
        node.put("source", sourceTag);
        node.put("target", targetTag);
        node.put("tag", tag);
        //node.put("value", 1);
        //node.put("weight", 1);
        return node;
    }



    JSONObject generateNode(String name, String id,int idx, int tag)//, float center, int value)
    {
        JSONObject node = new JSONObject();

        node.put("name", name);
        node.put("full_name", name);
        node.put("id", id);
        node.put("idx", idx);
        node.put("group", tag);


        node.put("weight",0);
        return node;
    }
}
