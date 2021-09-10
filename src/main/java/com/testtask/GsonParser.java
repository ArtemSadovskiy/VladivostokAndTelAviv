package com.testtask;

import com.google.gson.Gson;
import com.testtask.model.Root;

import java.io.FileReader;

public class GsonParser {
    public Root parser(){

        Gson gson = new Gson();

        try(FileReader reader = new FileReader("tickets.json")){
            Root root = gson.fromJson(reader, Root.class);
            return root;
        } catch (Exception e){
            System.out.println("Parsing error " + e.toString());
        }
        return null;
    }
}
