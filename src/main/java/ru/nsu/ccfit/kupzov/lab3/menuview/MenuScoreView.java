package ru.nsu.ccfit.kupzov.lab3.menuview;

import ru.nsu.ccfit.kupzov.lab3.model.Score;

import javax.swing.*;
import java.util.List;

public class MenuScoreView extends JFrame {
    List<Integer> scoreList;
    private static final int TOP_RECORDS = 5;
    public MenuScoreView(){
        scoreList = Score.getResults();

        JTextArea textArea = new JTextArea();
        int numbertoShowRecords = 0;
        if(TOP_RECORDS < scoreList.size()){
            numbertoShowRecords = TOP_RECORDS;
        }else{
            numbertoShowRecords = scoreList.size();
        }
        for (Integer i = 1; i <= numbertoShowRecords; i++) {
            textArea.append(i.toString() + ": " + scoreList.get(scoreList.size()-i) + "\n");
        }

        add(textArea);
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
