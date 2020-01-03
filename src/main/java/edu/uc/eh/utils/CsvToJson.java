package edu.uc.eh.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by shamsabz on 5/31/19.
 */
public class CsvToJson {

    private static final Logger log = LoggerFactory.getLogger(UtilsFormat.class);
    private static CsvToJson instance;


    public static JSONArray convert(File input) throws Exception {

        JSONParser parser = new JSONParser();
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        // Read data from CSV file
        List<? extends Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

        ObjectMapper mapper = new ObjectMapper();

        JSONArray jsonObject = (JSONArray) parser.parse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
        System.out.println("CSV to JSON");
        System.out.println(jsonObject.toString());
        System.out.println("-----------------");

        return jsonObject;
    }
}
