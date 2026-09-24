package Level;

import javax.swing.*;
import java.awt.*;

public class Levelpage extends JFrame {
    
    public Levelpage() {
        super("Select Level");
        
        Container cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("เลือกความยาก", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        title.setBounds(175, 30, 300, 40);
        cp.add(title);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}