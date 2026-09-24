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
        cp.setBackground(new Color(255, 182, 193));

        l1 = new JLabel("TeeMooDeng");
        l1.setBounds(184, 10, 300, 100);
        l1.setFont(new Font("Tahoma",Font.BOLD,40));
        l1.setForeground(Color.DARK_GRAY);
        cp.add(l1);

        StartButton = new JButton("Start");
        StartButton.setBounds(189, 135, 250, 90);
        StartButton.setFont(new Font("Tahoma",Font.BOLD,35));
        StartButton.setFocusPainted(false);
        StartButton.setForeground(Color.darkGray);
        StartButton.setBackground(new Color(255, 235, 205));
        StartButton.addActionListener(this);
        cp.add(StartButton);

        QuitButton = new JButton("Quit");
        QuitButton.setBounds(189, 275, 250, 90);
        QuitButton.setFont(new Font("Tahoma",Font.BOLD,35));
        QuitButton.setForeground(Color.darkGray);
        QuitButton.setBackground(new Color(255, 235, 205));
        QuitButton.setFocusPainted(false);
        QuitButton.addActionListener(this);
        cp.add(QuitButton);

        LoginButton = new JButton("Login");
        LoginButton.setBounds(450, 5, 90, 30);
        LoginButton.setForeground(Color.DARK_GRAY);
        LoginButton.setBackground(new Color(255, 235, 205));
        LoginButton.setFocusPainted(false);
        LoginButton.addActionListener(this);
        cp.add(LoginButton);

        info = new JButton("❓");
        info.setBounds(543, 5, 90, 30);
        info.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        info.setForeground(Color.DARK_GRAY);
        info.setBackground(new Color(255, 235, 205));
        info.setFocusPainted(false);
        info.addActionListener(this);
        cp.add(info);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==StartButton) {
            new Level.Levelpage();
            this.dispose();
        }
    }
     
}   