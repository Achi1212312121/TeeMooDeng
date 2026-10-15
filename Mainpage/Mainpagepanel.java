package Mainpage;
import java.awt.*;
import javax.swing.*;
import Login.Usermanager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainpagepanel extends JFrame implements ActionListener{
    private Container cp;
    private JButton StartButton,QuitButton,LoginButton,info,LogoutButton,HighScorebt;
    private JLabel l1,userLabel;

    public Mainpagepanel() {
      
        super("TeeMooDeng");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        l1 = new JLabel("TeeMooDeng");
        l1.setBounds(189, 20, 300, 100);
        l1.setFont(new Font("Fredoka",Font.BOLD,40));
        l1.setForeground(Color.black);
        cp.add(l1);

        StartButton = new JButton("Start");
        StartButton.setBounds(189, 135, 250, 90);
        StartButton.setFont(new Font("Fredoka",Font.BOLD,35));
        StartButton.setFocusPainted(false);
        StartButton.setForeground(Color.white);
        StartButton.setBackground(new Color(255, 146, 43));
        StartButton.addActionListener(this);
        cp.add(StartButton);

        QuitButton = new JButton("Quit");
        QuitButton.setBounds(189, 348, 250, 90);
        QuitButton.setFont(new Font("Fredoka",Font.BOLD,35));
        QuitButton.setForeground(Color.white);
        QuitButton.setBackground(new Color(255, 146, 43));
        QuitButton.setFocusPainted(false);
        QuitButton.addActionListener(this);
        cp.add(QuitButton);

        String currentUser = Usermanager.getCurrentUser();
        if(currentUser!=null){
            userLabel = new JLabel("👤Player: " + currentUser);
            userLabel.setBounds(20, 5, 250, 30);
            userLabel.setFont(new Font("Fredoka", Font.BOLD, 18));
            userLabel.setForeground(Color.BLACK);
            cp.add(userLabel);
            
            LogoutButton = new JButton("Logout");
            LogoutButton.setBounds(430, 5, 110, 30);
            LogoutButton.setFont(new Font("Fredoka", Font.BOLD, 18));
            LogoutButton.setForeground(Color.white);
            LogoutButton.setBackground(new Color(220, 53, 69)); // สีแดง
            LogoutButton.setFocusPainted(false);
            LogoutButton.addActionListener(this);
            cp.add(LogoutButton);
        }
        else{LoginButton = new JButton("Login");
        LoginButton.setBounds(450, 5, 90, 30);
        LoginButton.setFont(new Font("Fredoka",Font.BOLD,20));
        LoginButton.setForeground(Color.white);
        LoginButton.setBackground(new Color(255, 146, 43));
        LoginButton.setFocusPainted(false);
        LoginButton.addActionListener(this);
        cp.add(LoginButton);
    }
        info = new JButton("❓");
        info.setBounds(543, 5, 90, 30);
        info.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        info.setForeground(Color.white);
        info.setBackground(new Color(255, 146, 43));
        info.setFocusPainted(false);
        info.addActionListener(this);
        cp.add(info);

        HighScorebt = new JButton("HighScore");
        HighScorebt.setFont(new Font("Tahoam",Font.BOLD,35));
        HighScorebt.setForeground(Color.white);
        HighScorebt.setBackground(new Color(255, 146, 43));
        HighScorebt.setFocusPainted(false);
        HighScorebt.addActionListener(this);
        HighScorebt.setBounds(189, 240, 250, 90);
        cp.add(HighScorebt);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //แอ็กชั่นตอนกดปุ่มต่างๆ
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==StartButton) {
            new Level.Levelpage();//เปิดหน้าต่างใหม่
            this.dispose();//ปิดหน้าต่างเดิม
        }
        else if(e.getSource()==QuitButton){
            this.dispose();
        }
        else if(e.getSource()==info){
            new Info.info();
            this.dispose();
        }
        else if( LoginButton!= null&&e.getSource()==LoginButton ){
            new Login.LoginPage();
            this.dispose();
        }
        else if( LogoutButton!= null&&e.getSource()==LogoutButton ){
            Usermanager.logout();
            JOptionPane.showMessageDialog(this, "Logged out successfully!!");
             new Mainpagepanel();
             this.dispose();
             }
             else if(e.getSource() == HighScorebt){
                new Hightscore.Hightscorepage(-1, "Easy");
                this.dispose();
             }
    }
}   