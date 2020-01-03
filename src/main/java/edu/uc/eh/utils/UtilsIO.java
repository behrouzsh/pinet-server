package edu.uc.eh.utils;


import edu.uc.eh.structures.CSV2ColBean;
import edu.uc.eh.structures.DiffIdentifier;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Created by chojnasm on 11/24/15.
 * Modified by Behrouzsh on 11/10/16.
 * IO Utils delivered as Singleton
 */
public class UtilsIO {

    private static final Logger log = LoggerFactory.getLogger(UtilsIO.class);
    private static UtilsIO instance;

    private UtilsIO()  {
    }

    static {

            instance = new UtilsIO();

    }

    public static UtilsIO getInstance() {

        return instance;
    }


    /**
     * Read resource file from absolute path, which is relative to src/main/resources.
     * Store data in a structure enabling quick search for identifiers of modifications
     *
     * @param fileName
     * @return JSONObject
     */
    public ArrayList readListFileConvertToLowerCase(String fileName) {
        Class<?> cl = getClass();
        InputStream is = cl.getResourceAsStream(fileName);
        ArrayList pcg = new ArrayList();
        StringBuilder sb = new StringBuilder();
        //Map<Character, List<DiffIdentifier>> map = new HashMap<>();

        try(BufferedReader br =
                    new BufferedReader(new InputStreamReader(is))) {

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String[] geneListSplit = sb.toString().split(",");
            for (int i = 0;  i < geneListSplit.length; i++) {

                pcg.add(geneListSplit[i].replaceAll("[^a-zA-Z0-9-_\\s]", "").replaceAll("\\s", "").toLowerCase());
            }
            br.close();
        } catch (IOException e) {
            log.error("IOException: " + e);
        }

        return pcg;
    }

    public ArrayList readListFile(String fileName) {
        Class<?> cl = getClass();
        InputStream is = cl.getResourceAsStream(fileName);
        ArrayList pcg = new ArrayList();
        StringBuilder sb = new StringBuilder();
        //Map<Character, List<DiffIdentifier>> map = new HashMap<>();

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(is))) {

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String[] geneListSplit = sb.toString().split(",");
            for (int i = 0; i < geneListSplit.length; i++) {

                pcg.add(geneListSplit[i].replaceAll("[^a-zA-Z0-9-_\\s]", "").replaceAll("\\s", ""));
            }

            br.close();
        } catch (IOException e) {
            log.error("IOException: " + e);
        }

        return pcg;
    }

    /**
     * Read resource file from absolute path, which is relative to src/main/resources.
     * Store data in a structure enabling quick search for identifiers of modifications
     *
     * @param fileName
     * @return JSONArray
     */
    public JSONArray readJsonArrayFile(String fileName) {
        Class<?> cl = getClass();
        InputStream is = cl.getResourceAsStream(fileName);
        JSONArray pcg = new JSONArray();
        StringBuilder sb = new StringBuilder();
        //Map<Character, List<DiffIdentifier>> map = new HashMap<>();

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(is))) {

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            JSONParser parser = new JSONParser();
            pcg = (JSONArray) parser.parse(sb.toString());
            //pcg = new JSONObject(sb.toString());
            //pcg = (JSONObject) sb.toString();
            br.close();
        } catch (IOException e) {
            log.error("IOException: " + e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pcg;
    }


    /**
     * Read resource file from absolute path, which is relative to src/main/resources.
     * Store data in a structure enabling quick search for identifiers of modifications
     *
     * @param fileName
     * @return JSONObject
     */
    public JSONObject readJsonFile(String fileName) {
        Class<?> cl = getClass();
        InputStream is = cl.getResourceAsStream(fileName);
        JSONObject pcg = new JSONObject();
        StringBuilder sb = new StringBuilder();
        //Map<Character, List<DiffIdentifier>> map = new HashMap<>();

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(is))) {

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            JSONParser parser = new JSONParser();
            pcg = (JSONObject) parser.parse(sb.toString());
            //pcg = new JSONObject(sb.toString());
            //pcg = (JSONObject) sb.toString();
            br.close();
        } catch (IOException e) {
            log.error("IOException: " + e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pcg;
    }





    /**
     * Read resource file from absolute path, which is relative to src/main/resources.
     * Store data in a structure enabling quick search for identifiers of modifications
     *
     * @param fileName
     * @return Map
     */
    public Map<Character, List<DiffIdentifier>> readResource(String fileName) {

        Class<?> cl = getClass();
        InputStream is = cl.getResourceAsStream(fileName);
        Map<Character, List<DiffIdentifier>> map = new HashMap<>();
        //System.out.println(fileName);
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(is))) {
            //System.out.println(fileName);
            String inputLine;

            while ((inputLine = br.readLine()) != null) {

                //System.out.println(inputLine);
                String[] fields = inputLine.split("\t");
                if (fields[0].equals("identifier")) continue;

                String identifier = fields[0];
                Character c = fields[1].charAt(0);
                Double d = Double.parseDouble(fields[2]);
                String description = fields[3];

                DiffIdentifier di = new DiffIdentifier(d, identifier, description);

                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }

                List<DiffIdentifier> list = map.get(c);
                list.add(di);
                Collections.sort(list);
            }

            br.close();
        } catch (IOException e) {
            log.error("IOException: " + e);
        }

        return map;
    }



}
