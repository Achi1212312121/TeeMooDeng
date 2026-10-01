package GameModel;
import javax.swing.*;
import java.awt.*;
public  class Game extends JFrame {
    private Container cp;
    public  Game(){
        super("TeeMooDeng");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));

        this.setSize(650,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
     
}