/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.primefaces.json.JSONArray;

/**
 *
 * @author Adelmir
 */
public class JsonArraytoJavaList {

    private static Logger logger = Logger.getLogger(JsonArraytoJavaList.class);

    public static List<String> convertJsonArraytoJavaList(JSONArray jsonString) {
        
        Gson converter = new Gson();
        
        String jsonStringArray = "[\"recuperação de informação\",\"analise combinatória\",\"ciência da computação\"]";

        Type type = new TypeToken<List<String>>() {
        }.getType();

//        List<String> list = converter.fromJson(jsonStringArray, type);
        List<String> myModelList = converter.fromJson(jsonString.toString(), type);
        
        //convert List to Array in Java
        String[] strArray = myModelList.toArray(new String[0]);

        logger.info("Java List created from JSON String Array - example");
        logger.info("JSON String Array : " + jsonStringArray);
        logger.info("Java List : " + myModelList);
        logger.info("String array : " + Arrays.toString(strArray));

//        // let's now convert Java array to JSON array -
//        String toJson = converter.toJson(list);
//        logger.info("Json array created from Java List : " + toJson);
//        // example to convert JSON int array into Java array and List of integer
//        String json = "[101,201,301,401,501]";
//        type = new TypeToken<List<Integer>>() {
//        }.getType();
//
//        List<Integer> iList = converter.fromJson(json, type);
//        Integer[] iArray = iList.toArray(new Integer[0]);
//
//        logger.info("Example to convert numeric JSON array to integer List and array in Java");
//        logger.info("Numeric JSON array : " + json);
//        logger.info("Java List of Integers : " + iList);
//        logger.info("Integer array in Java : " + Arrays.toString(iArray));
//
//        // Again, let's convert this Java int array back to Json numeric array 
//        String toJsonInt = converter.toJson(iList);
//
//        logger.info("numeric JSON array create from Java collection : " + toJsonInt);
        return (myModelList);
    }

    public static void main(String args[]) {

       String jsonStringArray = "[\"recuperação de informação\",\"analise combinatória\",\"ciência da computação\"]";

        Gson converter = new Gson();

        Type type = new TypeToken<List<String>>() {
        }.getType();

        List<String> list = converter.fromJson(jsonStringArray, type);

        //convert List to Array in Java
        String[] strArray = list.toArray(new String[0]);

        logger.info("Java List created from JSON String Array - example");
        logger.info("JSON String Array : " + jsonStringArray);
        logger.info("Java List : " + list);
        logger.info("String array : " + Arrays.toString(strArray));

//        // let's now convert Java array to JSON array -
//        String toJson = converter.toJson(list);
//        logger.info("Json array created from Java List : " + toJson);
//        // example to convert JSON int array into Java array and List of integer
//        String json = "[101,201,301,401,501]";
//        type = new TypeToken<List<Integer>>() {
//        }.getType();
//
//        List<Integer> iList = converter.fromJson(json, type);
//        Integer[] iArray = iList.toArray(new Integer[0]);
//
//        logger.info("Example to convert numeric JSON array to integer List and array in Java");
//        logger.info("Numeric JSON array : " + json);
//        logger.info("Java List of Integers : " + iList);
//        logger.info("Integer array in Java : " + Arrays.toString(iArray));
//
//        // Again, let's convert this Java int array back to Json numeric array 
//        String toJsonInt = converter.toJson(iList);
//
//        logger.info("numeric JSON array create from Java collection : " + toJsonInt);

    }
}
