package ru.nsu.ccfit.kupzov.lab3.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.ccfit.kupzov.lab3.observable.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score extends Observable<Integer> {

    Integer score;
    private static final Logger logger = Logger.getLogger(Score.class.getName());

    public Score(){
        score = 0;
    }

    public void add(int count){
        score+=count*count;

        notify(score);
    }

    public Integer get(){
        return score;
    }
    public static void save(Integer score) {
        try(FileWriter fileWriter = new FileWriter("records.txt",true)) {
            fileWriter.write(String.valueOf(score) + '\n');

        }catch (IOException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
    public static List<Integer> getResults() {
        ArrayList<Integer> recordsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    recordsList.add(Integer.parseInt(line));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        //(a,b) -> a.compare(b)
        recordsList.sort(Integer::compareTo);
        return recordsList;
    }

}
