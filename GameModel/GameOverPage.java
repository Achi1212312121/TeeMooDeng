package GameModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Hightscore.Hightscorepage;

public class GameOverPage extends JFrame implements ActionListener{
    private Container cp;
    private JLabel GameOverTitle, ScoreLabel;
    private JButton PlayAgainBT, HighScoreBT, MainMenuBT;
    private  String level;
    private  int score;
    
    public GameOverPage(int score,String level) {
        super("GameOver");
        this.level = level;
        this.score = score;
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        GameOverTitle = new JLabel("Game Over", SwingConstants.CENTER);
        GameOverTitle.setBounds(125, 10, 400, 45);
        GameOverTitle.setFont(new Font("Fredoka",Font.BOLD,34));
        GameOverTitle.setForeground(Color.black);
        cp.add(GameOverTitle);

        ScoreLabel = new JLabel("Your Score:" + score, SwingConstants.CENTER);
        ScoreLabel.setBounds(125, 55, 400, 45);
        ScoreLabel.setFont(new Font("Fredoka",Font.BOLD,34));
        ScoreLabel.setForeground(Color.black);
        cp.add(ScoreLabel);

        PlayAgainBT = new JButton("Play Again");
        PlayAgainBT.setBounds(175, 125, 300, 90);
        PlayAgainBT.setFont(new Font("Fredoka",Font.BOLD,40));
        PlayAgainBT.setForeground(Color.white);
        PlayAgainBT.setBackground(new Color(255, 146, 43));
        PlayAgainBT.setFocusPainted(false);
        PlayAgainBT.addActionListener(this);
        cp.add(PlayAgainBT);

        HighScoreBT = new JButton("HighScore");
        HighScoreBT.setBounds(175, 245, 300, 90);
        HighScoreBT.setFont(new Font("Fredoka",Font.BOLD,40));
        HighScoreBT.setForeground(Color.white);
        HighScoreBT.setBackground(new Color(255, 146, 43));
        HighScoreBT.setFocusPainted(false);
        HighScoreBT.addActionListener(this);
        cp.add(HighScoreBT);

        MainMenuBT = new JButton("MainMenu");
        MainMenuBT.setBounds(175, 365, 300, 90);
        MainMenuBT.setFont(new Font("Fredoka",Font.BOLD,40));
        MainMenuBT.setForeground(Color.white);
        MainMenuBT.setBackground(new Color(255, 146, 43));
        MainMenuBT.setFocusPainted(false);
        MainMenuBT.addActionListener(this);
        cp.add(MainMenuBT);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==PlayAgainBT){
            new Game(level);
            this.dispose();
        }
        else if(e.getSource()==HighScoreBT){
            new Hightscore.Hightscorepage(score, level);
            this.dispose();
        }
        else if(e.getSource()==MainMenuBT){
            new Mainpage.Mainpagepanel();
            this.dispose();
        }
    }
}