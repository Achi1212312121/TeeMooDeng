package Hightscore;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import GameModel.GameOverPage;
import  java.util.List;
import Login.Usermanager;

import java.awt.event.*;
public class Hightscorepage extends JFrame implements ActionListener{
    private Container cp;  
    private JPanel topJPanel,bestJPanel;    
    private JLabel titLabel,subtitLabel,bestScoreLabel;
    private  String currentlevel;
    private  JTable table;
    private  JScrollPane scrollPane; 
    private  JButton playAgainBT,mainButton,backButton;
    private  int currentscore;
    private  JButton easybt,mediumbt,hardbt;
    public  Hightscorepage(int score,String Level){
        
        super("Highscore");
        this.currentlevel = Level;
        this.currentscore = score;
        String currentUser = Usermanager.getCurrentUser();
        if (currentUser == null) {
            currentUser = "Guest";
        }
        if (score>=0) {
            HighscoreManager.saveScore(currentUser, score, Level);
        }

        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));
        
        topJPanel = new JPanel();
        topJPanel.setLayout(new GridLayout(2,1));
        topJPanel.setBounds(140, 75, 370, 85);
        topJPanel.setBackground(new Color(255, 146, 43));
        topJPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        
        titLabel = new JLabel("High Score "+"("+Level+")",SwingConstants.CENTER);
        titLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        titLabel.setForeground(Color.white);

        subtitLabel = new JLabel("Top Moodeng Slayer",SwingConstants.CENTER);
        subtitLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        subtitLabel.setForeground(Color.white);

        topJPanel.add(titLabel);
        topJPanel.add(subtitLabel);

        cp.add(topJPanel);

        JPanel levelFilterPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        levelFilterPanel.setBounds(135, 28, 380, 40);
        levelFilterPanel.setOpaque(false);

        easybt = new JButton("Easy");
        mediumbt = new JButton("Medium");
        hardbt = new JButton("Hard");
        
        JButton[] levelbt = {easybt,mediumbt,hardbt};
        for (JButton btn : levelbt) {
            btn.setFont(new Font("Tahoma",Font.BOLD,18));
            btn.setBackground(new Color(255, 146, 43));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            levelFilterPanel.add(btn);
        }
        cp.add(levelFilterPanel);
        if (score>0) {
            bestJPanel = new JPanel(new BorderLayout());
        bestJPanel.setBounds(50, 170, 535, 38);
        bestJPanel.setBackground(new Color(255, 146, 43));
        bestJPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        bestScoreLabel = new JLabel(currentUser+"| Your Score:"+score+"pts||Level:"+Level,SwingConstants.CENTER);
        bestScoreLabel.setFont(new Font("Tahoma",Font.BOLD,25));
        bestScoreLabel.setForeground(Color.white);
        bestJPanel.add(bestScoreLabel, BorderLayout.CENTER);
        bestJPanel.add(bestScoreLabel);

        cp.add(bestJPanel);
        }
        
        
        String[] columNames = {"#","Player","Level","Score"};
        List<HighscoreManager.ScoreEntry> top5 = HighscoreManager.getTop5Score(Level);
        Object[][] data = new Object[top5.size()][4];
        String[] medals ={"1", "2", "3", "4", "5"};

        for(int i=0 ; i<top5.size() ; i++){
            HighscoreManager.ScoreEntry entry = top5.get(i);
            data[i][0] = medals[i];
            data[i][1] = entry.username;
            data[i][2] = entry.level;
            data[i][3] = entry.score;
        }
        DefaultTableModel model = new DefaultTableModel(data,columNames){
            @Override 
            public boolean isCellEditable(int row,int coloum){
                return  false;
            }            
        };
        table = new JTable(model);
        table.setFont(new Font("Tahoma",Font.BOLD,18));
        table.setRowHeight(30);
        table.setBackground(new Color(255, 146, 43));
        table.setForeground(Color.white);
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD,18));
        table.getTableHeader().setBackground(new Color(255, 146, 43));
        table.getTableHeader().setForeground(Color.white);
        table.setRowSelectionAllowed(false);   // ปิดไม่ให้เลือกแถว
        table.setColumnSelectionAllowed(false); // ปิดไม่ให้เลือกคอลัมน์
        table.setCellSelectionEnabled(false);  // ปิดการเลือกช่อง (Cell)
        table.setFocusable(false);             // ปิด Focus (เส้นกรอบเวลาคลิก)
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 180, 535, 180);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cp.add(scrollPane);

        playAgainBT = new JButton("Playagain");
        playAgainBT.setBounds(120, 380, 180, 75);
        playAgainBT.setFont(new Font("Tahoma", Font.BOLD, 30));
        playAgainBT.setForeground(Color.WHITE);
        playAgainBT.setBackground(new Color(255, 146, 43));
        playAgainBT.setFocusPainted(false);
        playAgainBT.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playAgainBT.addActionListener(this);
        if (score<0) {
            playAgainBT.setVisible(false);
        }
        cp.add(playAgainBT);

        if (score<0) {
            mainButton = new JButton("MainMenu");
        mainButton.setBounds(223, 380, 180, 75);
        mainButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        mainButton.setForeground(Color.WHITE);
        mainButton.setBackground(new Color(255, 146, 43));
        mainButton.setFocusPainted(false);
        mainButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainButton.addActionListener(this);
        }else
            {
        mainButton = new JButton("MainMenu");
        mainButton.setBounds(340, 380, 180, 75);
        mainButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        mainButton.setForeground(Color.WHITE);
        mainButton.setBackground(new Color(255, 146, 43));
        mainButton.setFocusPainted(false);
        mainButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainButton.addActionListener(this);}
        cp.add(mainButton);

        backButton = new JButton("🔙");
        backButton.setBounds(10, 15, 100, 40);
        backButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 146, 43));
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        if (score<0) {
            backButton.setVisible(false);
        }
        cp.add(backButton);
       
       
        this.setSize(650,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        }
    private void refreshTable(String selectedLevel) {
    this.currentlevel = selectedLevel;
    titLabel.setText("High Score (" + selectedLevel + ")");
    
    List<HighscoreManager.ScoreEntry> top5 = HighscoreManager.getTop5Score(selectedLevel);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // ล้างข้อมูลเก่า

    String[] medals = {"1", "2", "3", "4", "5"};
    for (int i = 0; i < top5.size(); i++) {
        HighscoreManager.ScoreEntry entry = top5.get(i);
        model.addRow(new Object[]{medals[i], entry.username, entry.level, entry.score});
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            if (currentscore>0) {
                new GameOverPage(currentscore, currentlevel);
            this.dispose();
            }
            else{
                new Mainpage.Mainpagepanel();
                this.dispose();
            }
        }
        else if (e.getSource() == playAgainBT) {
            new Level.Levelpage();
            this.dispose();
        }
        else if(e.getSource() == mainButton){
            new Mainpage.Mainpagepanel();
            this.dispose();
        }
        else if (e.getSource() == easybt) {
        refreshTable("Easy");
    } else if (e.getSource() == mediumbt) {
        refreshTable("Medium");
    } else if (e.getSource() == hardbt) {
        refreshTable("Hard");
    }
    }
     
}
