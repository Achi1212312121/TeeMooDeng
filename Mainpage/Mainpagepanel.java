package Mainpage;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainpagepanel extends JFrame implements ActionListener{
    private Container cp;
    private JButton StartButton,QuitButton,LoginButton,info;
    private JLabel l1;

    public Mainpagepanel() {
      
        super("TeeMooDeng");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        l1 = new JLabel("TeeMooDeng");
        l1.setBounds(205, 20, 300, 100);
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
        QuitButton.setBounds(189, 275, 250, 90);
        QuitButton.setFont(new Font("Fredoka",Font.BOLD,35));
        QuitButton.setForeground(Color.white);
        QuitButton.setBackground(new Color(255, 146, 43));
        QuitButton.setFocusPainted(false);
        QuitButton.addActionListener(this);
        cp.add(QuitButton);

        LoginButton = new JButton("Login");
        LoginButton.setBounds(450, 5, 90, 30);
        LoginButton.setFont(new Font("Fredoka",Font.BOLD,20));
        LoginButton.setForeground(Color.white);
        LoginButton.setBackground(new Color(255, 146, 43));
        LoginButton.setFocusPainted(false);
        LoginButton.addActionListener(this);
        cp.add(LoginButton);

        info = new JButton("❓");
        info.setBounds(543, 5, 90, 30);
        info.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        info.setForeground(Color.white);
        info.setBackground(new Color(255, 146, 43));
        info.setFocusPainted(false);
        info.addActionListener(this);
        cp.add(info);

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
        else if(e.getSource()==LoginButton){
            new Login.LoginPage();
            this.dispose();
        }
    }
}   