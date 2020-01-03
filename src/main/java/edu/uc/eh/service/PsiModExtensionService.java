package edu.uc.eh.service;

import edu.uc.eh.utils.UtilsIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shamsabz on 9/6/17.
 */
@Service
public class PsiModExtensionService {
    private static final Logger log = LoggerFactory.getLogger(PsiModExtensionService.class);


    @Value("${resources.psiModExtensionMapping}")
    String mapping;

    public JSONObject getInfo(String input)  {
        JSONObject info = new JSONObject();
        JSONObject infoReturn = new JSONObject();
        JSONObject infoTemp = new JSONObject();
        JSONObject infoAll = new JSONObject();
        JSONArray is_aArray = new JSONArray();

        List<String> is_a = new ArrayList<String>();
        String is_aElement;

        try {
            infoAll = UtilsIO.getInstance().readJsonFile(mapping);

        } catch (Exception e) {
            String msg =  String.format("Error in obtaining psiModExtensionMapping");
            log.warn(msg);
            throw new RuntimeException(msg);
        }
        if (infoAll.containsKey(input)) {
            info = (JSONObject) infoAll.get(input);
            is_a = (List<String>) info.get("is_a");
            for (int i = 0; i < is_a.size(); i++) {
                //System.out.println(is_a.get(i));
                is_aElement = is_a.get(i);
                JSONObject is_aArrayElement = new JSONObject();
                if (infoAll.containsKey(is_aElement)) {
                    infoTemp =(JSONObject) infoAll.get(is_aElement);
                    is_aArrayElement.put("mod",is_aElement);
                    is_aArrayElement.put("defstr",infoTemp.get("defstr"));
                    is_aArrayElement.put("DiffAvg",infoTemp.get("DiffAvg"));
                }

                is_aArray.add(is_aArrayElement);
            }

        }

        infoReturn.put("name", info.get("name"));
        infoReturn.put("defstr", info.get("defstr"));
//        infoReturn.put("DiffAvg", info.get("DiffAvg"));
        infoReturn.put("is_a", is_aArray);
        return infoReturn;

    }
}
