package ru.nsu.ccfit.kupzov.lab3.gameview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ru.nsu.ccfit.kupzov.lab3.observer.Observer;

import ru.nsu.ccfit.kupzov.lab3.action.ActionQueue;
import ru.nsu.ccfit.kupzov.lab3.action.TetrisAction;
import ru.nsu.ccfit.kupzov.lab3.controller.TetrisController;
import ru.nsu.ccfit.kupzov.lab3.menuview.GreedingView;
import ru.nsu.ccfit.kupzov.lab3.menuview.MenuScoreView;

public class TetrisView extends JFrame implements ActionListener, Observer<String> {
    private final ActionQueue<TetrisAction> actionQueue;
    private final Timer timer;
    private final TetrisController tetrisController;

    public TetrisView(TetrisController tetrisController, ActionQueue<TetrisAction> actionQueue) {
        this.actionQueue = actionQueue;
        this.tetrisController = tetrisController;

        timer = new Timer(400, this);
        addKeyListener(new TetrisInputHandler(actionQueue));

        SwingUtilities.invokeLater(() -> {

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem newGameMenuItem = new JMenuItem("New game");
        newGameMenuItem.addActionListener(e -> actionQueue.addAction(TetrisAction.NEW_GAME));
        menu.add(newGameMenuItem);

        JMenuItem scoresMenuItem = new JMenuItem("Scores");
        scoresMenuItem.addActionListener(e -> new MenuScoreView());
        menu.add(scoresMenuItem);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> new GreedingView());
        menu.add(aboutMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        menu.add(exitMenuItem);

        setJMenuBar(menuBar);

        createPanels(tetrisController);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        });
    }
    void createPanels(TetrisController tetrisController) {

        FieldView fieldView = new FieldView(tetrisController,false);
        fieldView.setPreferredSize(new Dimension(200, 440));

        FieldView previewView = new FieldView(tetrisController,true);
        previewView.setPreferredSize(new Dimension(100, 100));

        ScoreView scoreView = new ScoreView(tetrisController);
        scoreView.setPreferredSize(new Dimension(100, 50));

        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(200, 440));
        gamePanel.add(fieldView);

        JPanel previewPanel = new JPanel();
        previewPanel.setPreferredSize(new Dimension(100, 100));
        previewPanel.add(previewView);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setPreferredSize(new Dimension(100, 50));
        pointsPanel.add(scoreView);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        backPanel.setPreferredSize(new Dimension(100, 440));
        backPanel.add(previewPanel);
        backPanel.add(pointsPanel);

        add(gamePanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.EAST);
    }
    public void updateGameView(TetrisController tetrisController) {
        SwingUtilities.invokeLater(() -> {
            this.getContentPane().removeAll();
            createPanels(tetrisController);
            revalidate();
            repaint();
        });
    }
    public void showGameOver() {
        SwingUtilities.invokeLater(() -> {
            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));

            JLabel gameOverLabel = new JLabel("Game Over!", JLabel.CENTER);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
            gameOverPanel.add(gameOverLabel);

            this.getContentPane().removeAll();
            this.getContentPane().add(gameOverPanel);
            revalidate();
            repaint();
        });
    }

    public void run() {
        timer.start();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        actionQueue.addAction(TetrisAction.STEP_GAME);
    }


    @Override
    public void update(String context) {
        switch (context) {
            case "RUN":
                this.tetrisController.getViewByController().run();
                break;
            case "END_GAME":
                showGameOver();
                break;
            case "NEW_GAME":
                updateGameView(this.tetrisController);
                break;
        }
    }
}
