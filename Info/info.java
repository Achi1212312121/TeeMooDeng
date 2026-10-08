package Info;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class info extends JFrame implements ActionListener{
    private Container cp;
    private JLabel InfoTitle;
    private JTextArea Description;
    private JButton BackBT, NextBT;
    private int page = 1;

    private static final String PAGE1 =
          "เป้าหมายของเกม\n"
        + "ตี”หมูเด้ง”ที่ขึ้นออกมาจากหลุมให้ได้มากที่สุด\n\n"
        + " วิธีการเล่น\n"
        + "1.เมื่อเริ่มเกมหมูเด้งจะโผล่มาจากหลุมขนาด3×3\n"
        + "2.ให้ใช้เมาส์คลิกตีที่หลุมที่มีหมูเด้งโผล่ขึ้นมาเพื่อสะสมคะแนน\n"
        + "3.หากคลิกถูกหลุม จะได้รับคะแนนเพิ่มตามระดับหมูเด้งที่ตี\n\n"
        + "ระดับความยากง่าย\n"
        + "1.Easy||เวลา 30 วินาที\n"
        + "2.Medium||เวลา 25 วินาที\n"
        + "3.Hard||เวลา 20 วินาที";

    private static final String PAGE2 =
          "ประเภทของหมูเด้งและของเพิ่มคะแนน\n"
        + "1.หมูเด้งปกติ 5 คะแนน\n"
        + "2.หมูเด้งเทวดา 10 คะแนน\n"
        + "3.หมูเด้งสายรุ้ง 20 คะแนน\n"
        +"4.ครูบา 30 คะแนน\n"
        + "5.evilหมูเด้ง -30 คะแนน\n\n"
        + "ประเภทของลดคะแนน\n"
        + "1.ตีผิดหลุม -5 คะแนน\n"
        + "2.ระเบิด -10 คะแนน\n"
        + "3.ครูบุญ -20 คะแนน";

    public info() {
        super("Info");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        InfoTitle = new JLabel(" วิธีเล่นเกม TeeMooDeng ");
        InfoTitle.setBounds(60, 15, 530, 50);
        InfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
        InfoTitle.setFont(new Font("Tahoma",Font.BOLD,30));
        InfoTitle.setForeground(Color.black);
        cp.add(InfoTitle);

        Description = new JTextArea();
        Description.setBounds(20, 75, 600, 345);
        Description.setFont(new Font("Tahoma",Font.BOLD,20));
        Description.setForeground(Color.black);
        Description.setBackground(new Color(233, 250, 200));
        Description.setEditable(false);
        Description.setFocusable(false);
        Description.setLineWrap(true);
        Description.setWrapStyleWord(true);
        cp.add(Description);

        BackBT = new JButton("←");
        BackBT.setFont(new Font("Segoe UI Symbol", Font.BOLD, 55));
        BackBT.setBounds(10, 428, 145, 80);
        BackBT.setBackground(new Color(255, 146, 43));
        BackBT.setForeground(Color.BLACK);
        BackBT.setFocusPainted(false);
        BackBT.addActionListener(this);
        cp.add(BackBT);

        NextBT = new JButton("→");
        NextBT.setFont(new Font("Segoe UI Symbol", Font.BOLD, 55));
        NextBT.setBounds(480, 428, 145, 80);
        NextBT.setBackground(new Color(255, 146, 43));
        NextBT.setForeground(Color.BLACK);
        NextBT.setFocusPainted(false);
        NextBT.addActionListener(this);
        cp.add(NextBT);

        showPage();

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void showPage() {
        Description.setText(page == 1 ? PAGE1 : PAGE2);
        NextBT.setVisible(page == 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==BackBT){
            if(page == 1){
                new Mainpage.Mainpagepanel();
                this.dispose();
            } else {
                page = 1;
                showPage();
            }
        }
        else if(e.getSource()==NextBT){
            page = 2;
            showPage();
        }
    }
}
