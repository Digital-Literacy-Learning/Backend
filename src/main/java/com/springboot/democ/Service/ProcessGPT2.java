package com.springboot.democ.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessGPT2 {

    public String PublisherGPT(String article) throws Exception {


        ProcessBuilder pb = new ProcessBuilder("python3","category.py",article);
        Map<String , String> environment = pb.environment();
        environment.put("PATH" , "/usr/bin/python3");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        StringBuilder resultBuilder = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

        try{
            String line;
            while((line=br.readLine()) != null){

                resultBuilder.append(line);
            }
        } finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return resultBuilder.toString();

    }

}