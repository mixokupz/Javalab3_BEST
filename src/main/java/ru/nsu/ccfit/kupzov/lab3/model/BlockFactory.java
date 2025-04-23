package ru.nsu.ccfit.kupzov.lab3.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockFactory {
    Map<String,String> factory;
    private static final Logger logger = Logger.getLogger(BlockFactory.class.getName());

    public BlockFactory(){
        factory = new HashMap<>();
        try(InputStream stream = getClass().getClassLoader().getResourceAsStream("command_list.properties")){
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        factory.put(parts[0].toLowerCase(), parts[1]);
                    }
                }
            }


        }catch (IOException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
    public Block makeRandomBlock(){
        Random random = new Random();
        Object[] values = factory.values().toArray();
        String randomValue = (String)values[random.nextInt(values.length)];
        Block block = null;
        try {
            Class blockClass = Class.forName(randomValue);
            try {
                block = (Block) blockClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                logger.log(Level.SEVERE,e.getMessage());
            }
        } catch (ClassNotFoundException| NullPointerException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
        return block;
    }

}
