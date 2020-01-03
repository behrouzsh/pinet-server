package edu.uc.eh.service;

/**
 * Created by shamsabz on 8/27/17.
 */
import edu.uc.eh.structures.CSV2ColBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//import org.json.JSONObject;

/**
 * Created by behrouz on 10/24/16.
 */

@Service
public class NetworkFromCSVService {
    private static final Logger log = LoggerFactory.getLogger(NetworkFromCSVService.class);



    public List<CSV2ColBean> readCSV(String fileName)
    {
        log.info(fileName);
        log.info("here2");
        List<CSV2ColBean> Rows = readRowsFromCSV(fileName);
        log.info(fileName);
        log.info("here3");
        log.info(Rows.toString());

        // let's print all the person read from CSV file
        for (CSV2ColBean b : Rows) {
            System.out.println(b);
        }

        return Rows;

    }

    private List<CSV2ColBean> readRowsFromCSV(String fileName) {


        Path pathToFile = Paths.get(fileName); // create an instance of BufferedReader // using try with resource, Java 7 feature to close resources try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {


        List<CSV2ColBean> Rows = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                    StandardCharsets.US_ASCII)) {
            // read the first line from the text file
            String line = br.readLine(); // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from
                // each line of // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                CSV2ColBean book = createObject(attributes);
                // adding book into ArrayList
                Rows.add(book);
                // read next line before looping
                // if end of file reached, line would be null l
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return Rows;
    }

    private static CSV2ColBean createObject(String[] metadata) {
        String name = metadata[0];
        String price = metadata[1];
        // create and return book of this metadata
        return new CSV2ColBean(name, price);
    }


    public JSONObject computeNetworkFromInputJson(JSONArray input) {

        JSONArray nodes = new JSONArray();
        //==============================================
        JSONArray edges = new JSONArray();
        //==============================================
        JSONObject nodesUnique = new JSONObject();
        //==============================================
        JSONObject newNode = new JSONObject();
        //==============================================
        JSONObject newEdge = new JSONObject();

        JSONObject network = new JSONObject();
        //log.info(mapping.toString());
//        log.info("here");
        String gene;
        String pathway;
        Double relation;
        int idx = 0;
        int tag1, tag2, edgeId;


        for(int i = 0; i < input.size(); i++) {
            //JSONObject inputItem = (JSONObject) input.get(i);
            tag1 = 1;
            tag2 = 1;
            edgeId = 1;

            gene = (String) ((JSONObject)input.get(i)).get("Category1");
            pathway = (String) ((JSONObject)input.get(i)).get("Category2");
            relation = Double.parseDouble((String) ((JSONObject)input.get(i)).get("Relation"));
            if (((JSONObject) input.get(i)).containsKey("Category1Id")){
                tag1 = Integer.parseInt((String)((JSONObject)input.get(i)).get("Category1Id"));
            }

            if (((JSONObject) input.get(i)).containsKey("Category2Id")){
                tag2 = Integer.parseInt((String)((JSONObject)input.get(i)).get("Category2Id"));
            }

            if (((JSONObject) input.get(i)).containsKey("EdgeId")){
                edgeId = Integer.parseInt((String)((JSONObject)input.get(i)).get("EdgeId"));
            }

            System.out.println(gene);
            System.out.println(pathway);
            System.out.println(relation);

//            log.info(gene);
//            log.info(pathway);
            if(tag1 != -1) {
                if (!nodesUnique.containsKey(gene)) {
                    newNode = generateNode(gene, idx, tag1);
                    //idx = idx + 1;
                    idx = idx + 1;
                    nodesUnique.put(gene, newNode);
                    nodes.add(nodesUnique.get(gene));
                }
            }
            if(tag2 != -1) {
                if (!nodesUnique.containsKey(pathway)) {
                    newNode = generateNode(pathway, idx, tag2);
                    //idx = idx + 1;
                    idx = idx + 1;
                    nodesUnique.put(pathway, newNode);
                    nodes.add(nodesUnique.get(pathway));
                }
            }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode.get("idx"));
//                newEdgeNode.put("target", ((JSONObject) KEGG2013Unique.get(pathway)).get("idx"));//["idx"];//.get(pathway));
            if(tag2 != -1 && tag2 != -1) {
                newEdge = generateEdgeNode((int) ((JSONObject) nodesUnique.get(gene)).get("idx"), (int) ((JSONObject) nodesUnique.get(pathway)).get("idx"), relation, edgeId);
                edges.add(newEdge);
            }


        }

        network.put("nodes", nodes);
        network.put("edges", edges);

        System.out.print("Network From CSV");
        System.out.print(network.toString());
        return network;
    }


    public JSONObject computeNetwork() {
        List<CSV2ColBean> mapping;

        mapping = readCSV("src/main/resources/csv-extra/cluster-enrichment.csv");
//        log.info(mapping.toString());

//    public NetworkFromCSVService(List<CSV2ColBean> mapping) {
//            this.mapping = mapping;
//        }
        JSONArray nodes = new JSONArray();
        //==============================================
        JSONArray edges = new JSONArray();
        //==============================================
        JSONObject nodesUnique = new JSONObject();
        //==============================================
        JSONObject newNode = new JSONObject();
        //==============================================
        JSONObject newEdge = new JSONObject();

        JSONObject network = new JSONObject();
        //log.info(mapping.toString());
//        log.info("here");
        String gene;
        String pathway;
        int idx = 0;

        for(int i = 0; i < mapping.size(); i++) {
            gene = (String) mapping.get(i).getcol1();
            pathway = (String) mapping.get(i).getcol2();
//            log.info(gene);
//            log.info(pathway);

            if (! nodesUnique.containsKey(gene)) {
                newNode = generateNode(gene, idx, 1);
                //idx = idx + 1;
                idx = idx + 1;
                nodesUnique.put(gene,newNode);
                nodes.add(nodesUnique.get(gene));
            }

            if (! nodesUnique.containsKey(pathway)) {
                newNode = generateNode(pathway, idx, 2);
                //idx = idx + 1;
                idx = idx + 1;
                nodesUnique.put(pathway,newNode);
                nodes.add(nodesUnique.get(pathway));
            }
//                newEdgeNode = new JSONObject();
//                newEdgeNode.put("source", newGeneNode.get("idx"));
//                newEdgeNode.put("target", ((JSONObject) KEGG2013Unique.get(pathway)).get("idx"));//["idx"];//.get(pathway));
            newEdge = generateEdgeNode((int)((JSONObject) nodesUnique.get(gene)).get("idx"), (int)((JSONObject) nodesUnique.get(pathway)).get("idx"), 1.0, 1);
            edges.add(newEdge);


        }

        network.put("nodes", nodes);
        network.put("edges", edges);

        log.info("First Network(KEGG, ...)");
        log.info(network.toString());
        return network;
    }





    JSONObject generateEdgeNode(int sourceTag, int targetTag, double value, int edgeId)
    {
        JSONObject node = new JSONObject();
        node.put("source", sourceTag);
        node.put("target", targetTag);
        node.put("weight", value);
        node.put("tag", edgeId);
        //node.put("value", 1);
        //node.put("weight", 1);
        return node;
    }



    JSONObject generateNode(String name, int idx, int tag)//, float center, int value)
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
        //node.put("value",value);
        node.put("group",tag);
        node.put("weight",0);

        return node;
    }


}
