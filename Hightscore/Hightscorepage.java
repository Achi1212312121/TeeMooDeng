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
        this.currentlevel = Level;//เก็บระดับปัจจุบัน
        this.currentscore = score;//เก็บคะแนนปัจจุบัน
        String currentUser = Usermanager.getCurrentUser();//ดึงชื่อผู้เล่นปัจจุบันจาก Usermanager
        if (currentUser == null) {
            currentUser = "Guest";//ถ้าไม่มีผู้เล่นปัจจุบันให้ตั้งค่าเป็น Guest
        }
        if (score>=0) {
            HighscoreManager.saveScore(currentUser, score, Level);//บันทึกคะแนนของผู้เล่นปัจจุบันลงในไฟล์ Highscore.csv
        }

        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));
        
        topJPanel = new JPanel();//สร้าง JPanel สำหรับแสดงหัวข้อ Highscore และ Top Moodeng Slayer
        topJPanel.setLayout(new GridLayout(2,1));//ตั้งค่า Layout เป็น GridLayout 2 แถว 1 คอลัมน์
        topJPanel.setBounds(140, 75, 370, 85);
        topJPanel.setBackground(new Color(255, 146, 43));
        topJPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));//สร้างเส้นขอบสีดำรอบ JPanel
        
        titLabel = new JLabel("High Score "+"("+Level+")",SwingConstants.CENTER);//สร้าง JLabel สำหรับแสดงหัวข้อ Highscore และระดับปัจจุบัน
        titLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        titLabel.setForeground(Color.white);

        subtitLabel = new JLabel("Top Moodeng Slayer",SwingConstants.CENTER);//สร้าง JLabel สำหรับแสดงหัวข้อ Top Moodeng Slayer
        subtitLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        subtitLabel.setForeground(Color.white);

        topJPanel.add(titLabel);//เพิ่ม JLabel ทั้งสองลงใน JPanel
        topJPanel.add(subtitLabel);//เพิ่ม JLabel ทั้งสองลงใน JPanel

        cp.add(topJPanel);

        JPanel levelFilterPanel = new JPanel(new GridLayout(1, 3, 10, 0));//สร้าง JPanel สำหรับปุ่มกรองระดับ โดยใช้ GridLayout 1 แถว 3 คอลัมน์ และเว้นระยะห่างระหว่างปุ่ม 10 พิกเซล
        levelFilterPanel.setBounds(135, 28, 380, 40);
        levelFilterPanel.setOpaque(false);//ตั้งค่า JPanel ให้โปร่งใส

        easybt = new JButton("Easy");//สร้างปุ่ม Easy
        mediumbt = new JButton("Medium");//สร้างปุ่ม Medium
        hardbt = new JButton("Hard");//สร้างปุ่ม Hard
        
        JButton[] levelbt = {easybt,mediumbt,hardbt};//สร้างอาเรย์ของปุ่มกรองระดับ
        for (JButton btn : levelbt) {
            btn.setFont(new Font("Tahoma",Font.BOLD,18));
            btn.setBackground(new Color(255, 146, 43));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            levelFilterPanel.add(btn);//เพิ่มปุ่มกรองระดับลงใน JPanel
        }
        cp.add(levelFilterPanel);//เพิ่ม JPanel สำหรับปุ่มกรองระดับลงใน Container
        if (score>=0) {
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
        }//ถ้าคะแนนของผู้เล่นมากกว่า 0 ให้แสดง JPanel สำหรับแสดงคะแนนของผู้เล่นปัจจุบัน
        
        
        String[] columNames = {"#","Player","Level","Score"};//สร้างอาเรย์ของชื่อคอลัมน์สำหรับ JTable
        List<HighscoreManager.ScoreEntry> top5 = HighscoreManager.getTop5Score(Level);//ดึงคะแนนสูงสุด 5 อันดับแรกจาก HighscoreManager ตามระดับปัจจุบัน
        Object[][] data = new Object[top5.size()][4];//สร้างอาเรย์ 2 มิติสำหรับเก็บข้อมูลของ JTable โดยมีจำนวนแถวเท่ากับจำนวนคะแนนสูงสุด 5 อันดับแรก และมี 4 คอลัมน์
        String[] medals ={"1", "2", "3", "4", "5"};//สร้างอาเรย์ของหมายเลขอันดับสำหรับแสดงใน JTable

        for(int i=0 ; i<top5.size() ; i++){
            HighscoreManager.ScoreEntry entry = top5.get(i);//ดึงข้อมูลของคะแนนสูงสุดอันดับที่ i
            data[i][0] = medals[i];//เก็บหมายเลขอันดับลงในอาเรย์ 2 มิติ data
            data[i][1] = entry.username;//เก็บชื่อผู้เล่นลงในอาเรย์ 2 มิติ data
            data[i][2] = entry.level;//เก็บระดับของผู้เล่นลงในอาเรย์ 2 มิติ data
            data[i][3] = entry.score;//เก็บคะแนนของผู้เล่นลงในอาเรย์ 2 มิติ data
        }//วนลูปเพื่อเก็บข้อมูลของคะแนนสูงสุด 5 อันดับแรกลงในอาเรย์ 2 มิติ data
        DefaultTableModel model = new DefaultTableModel(data,columNames){
            @Override 
            public boolean isCellEditable(int row,int coloum){
                return  false;//ปิดไม่ให้แก้ไขข้อมูลใน JTable
            }            
        };//สร้าง DefaultTableModel โดยใช้ข้อมูลและชื่อคอลัมน์ที่เตรียมไว้
        table = new JTable(model);//สร้าง JTable โดยใช้ DefaultTableModel ที่เก็บข้อมูลและชื่อคอลัมน์
        table.setFont(new Font("Tahoma",Font.BOLD,18));
        table.setRowHeight(30);//ตั้งค่าความสูงของแถวใน JTable
        table.setBackground(new Color(255, 146, 43));
        table.setForeground(Color.white);//ตั้งค่าสีพื้นหลังและสีตัวอักษรของ JTable
        table.setGridColor(Color.BLACK);//ตั้งค่าสีของเส้นกริดใน JTable
        table.setShowGrid(true);//ตั้งค่าให้แสดงเส้นกริดใน JTable
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();//สร้าง DefaultTableCellRenderer สำหรับจัดตำแหน่งข้อความให้อยู่ตรงกลาง
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);//ตั้งค่าการจัดตำแหน่งข้อความให้อยู่ตรงกลาง
        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);//ตั้งค่า DefaultTableCellRenderer ให้กับทุกคอลัมน์ใน JTable
        }//วนลูปเพื่อกำหนด DefaultTableCellRenderer ให้กับทุกคอลัมน์ใน JTable

        table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD,18));
        table.getTableHeader().setBackground(new Color(255, 146, 43));
        table.getTableHeader().setForeground(Color.white);
        table.setRowSelectionAllowed(false);   // ปิดไม่ให้เลือกแถว
        table.setColumnSelectionAllowed(false); // ปิดไม่ให้เลือกคอลัมน์
        table.setCellSelectionEnabled(false);  // ปิดการเลือกช่อง (Cell)
        table.setFocusable(false);             // ปิด Focus (เส้นกรอบเวลาคลิก)
        table.getTableHeader().setReorderingAllowed(false);// ปิดไม่ให้ลากคอลัมน์เปลี่ยนตำแหน่ง
        table.getTableHeader().setResizingAllowed(false);// ปิดไม่ให้ปรับขนาดคอลัมน์

        scrollPane = new JScrollPane(table);//สร้าง JScrollPane สำหรับ JTable เพื่อให้สามารถเลื่อนดูข้อมูลได้
        scrollPane.setBounds(50, 180, 535, 180);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cp.add(scrollPane);//เพิ่ม JScrollPane ลงใน Container

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
    this.currentlevel = selectedLevel;//อัปเดตระดับปัจจุบัน
    titLabel.setText("High Score (" + selectedLevel + ")");//อัปเดตข้อความของ JLabel สำหรับหัวข้อ Highscore และระดับปัจจุบัน
    
    List<HighscoreManager.ScoreEntry> top5 = HighscoreManager.getTop5Score(selectedLevel);//ดึงคะแนนสูงสุด 5 อันดับแรกจาก HighscoreManager ตามระดับที่เลือก
    DefaultTableModel model = (DefaultTableModel) table.getModel();//ดึง DefaultTableModel ของ JTable
    model.setRowCount(0); // ล้างข้อมูลเก่า

    String[] medals = {"1", "2", "3", "4", "5"};//สร้างอาเรย์ของหมายเลขอันดับสำหรับแสดงใน JTable
    for (int i = 0; i < top5.size(); i++) {
        HighscoreManager.ScoreEntry entry = top5.get(i);//ดึงข้อมูลของคะแนนสูงสุดอันดับที่ i
        model.addRow(new Object[]{medals[i], entry.username, entry.level, entry.score});//เพิ่มแถวใหม่ลงใน JTable โดยใช้ข้อมูลของคะแนนสูงสุดอันดับที่ i
    }//วนลูปเพื่อเพิ่มข้อมูลของคะแนนสูงสุด 5 อันดับแรกลงใน JTable
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            if (currentscore>=0) {
                new GameOverPage(currentscore, currentlevel);//ถ้าคะแนนของผู้เล่นมากกว่า 0 ให้กลับไปที่หน้า GameOverPage โดยส่งคะแนนและระดับปัจจุบันไปด้วย
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
        refreshTable("Easy");//ถ้าผู้เล่นคลิกปุ่ม Easy ให้เรียกใช้เมธอด refreshTable() โดยส่งค่า "Easy" ไปเป็นพารามิเตอร์
    } else if (e.getSource() == mediumbt) {
        refreshTable("Medium");//ถ้าผู้เล่นคลิกปุ่ม Medium ให้เรียกใช้เมธอด refreshTable() โดยส่งค่า "Medium" ไปเป็นพารามิเตอร์
    } else if (e.getSource() == hardbt) {
        refreshTable("Hard");//ถ้าผู้เล่นคลิกปุ่ม Hard ให้เรียกใช้เมธอด refreshTable() โดยส่งค่า "Hard" ไปเป็นพารามิเตอร์
    }
    }
     
}
