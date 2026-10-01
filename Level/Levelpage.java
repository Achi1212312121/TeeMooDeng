package Level;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Levelpage extends JFrame implements ActionListener{
    private Container cp;
    private JLabel SelectLevel;
    private JButton EasyBT,MediumBT,HardBT,BackBT;
    public Levelpage() {
        super("SelectLevel");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        SelectLevel = new JLabel("SelectLevel");
        SelectLevel.setBounds(209, 20, 250, 50);
        SelectLevel.setFont(new Font("Fredoka",Font.BOLD,40));
        SelectLevel.setForeground(Color.black);
        cp.add(SelectLevel);

        EasyBT = new JButton("Easy");
        EasyBT.setBounds(209,100,230,80);
        EasyBT.setForeground(Color.white);
        EasyBT.setFont(new Font("Fredoka",Font.BOLD,40));
        EasyBT.setBackground(new Color(255, 146, 43));
        EasyBT.setFocusPainted(false);
        EasyBT.addActionListener(this);
        cp.add(EasyBT);

        MediumBT = new JButton("Medium");
        MediumBT.setBounds(209,200,230,80);
        MediumBT.setForeground(Color.white);
        MediumBT.setFont(new Font("Fredoka",Font.BOLD,40));
        MediumBT.setBackground(new Color(255, 146, 43));
        MediumBT.setFocusPainted(false);
        MediumBT.addActionListener(this);
        cp.add(MediumBT);

        HardBT = new JButton("Hard");
        HardBT.setBounds(209, 300, 230, 80);
        HardBT.setForeground(Color.white);
        HardBT.setFont(new Font("Fredoka",Font.BOLD,40));
        HardBT.setBackground(new Color(255, 146, 43));
        HardBT.setFocusPainted(false);
        HardBT.addActionListener(this);
        cp.add(HardBT);

        BackBT = new JButton("🔙");
        BackBT.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
        BackBT.setBounds(10, 428, 145, 80);
        BackBT.setBackground(new Color(255, 146, 43));
        BackBT.setForeground(Color.WHITE);
        BackBT.setFocusPainted(false);
        BackBT.addActionListener(this);
        BackBT.addActionListener(this);
        cp.add(BackBT);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                if(e.getSource()==EasyBT){
                    new GameModel.Game();
                    this.dispose();
                }
                else if(e.getSource()==MediumBT){
                    new GameModel.Game();
                    this.dispose();
                }
                else if(e.getSource()==HardBT){
                    new GameModel.Game();
                    this.dispose();
                }
                else if(e.getSource()==BackBT){
                    new Mainpage.Mainpagepanel();
                    this.dispose();
                }
    }
}