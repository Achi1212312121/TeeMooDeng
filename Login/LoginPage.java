package Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginPage extends JFrame implements ActionListener {
     
    private Container cp;
     private JLabel Login,Username,Password;
     private JTextField User;
     private JPasswordField Pass;
     private JButton LoginBt,SignupBT,BackBt,ShowBt;
     public  LoginPage(){
        super("Login");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(233, 250, 200));
        
        Login = new JLabel("Login");
        Login.setBounds(268, -10, 171, 91);
        Login.setFont(new Font("Tahoma",Font.BOLD,40));
        Login.setForeground(Color.black);
        cp.add(Login);
        
        Username = new JLabel("Username");
        Username.setBounds(85, 80, 110, 40);
        Username.setFont(new Font("Tahoma",Font.BOLD,20));
        Username.setForeground(Color.BLACK);
        cp.add(Username);
       
        User = new JTextField(50);
        User.setBounds(85, 120, 470, 40);
        User.setFont(new Font("Tahoma",Font.BOLD,20));
        cp.add(User);

        Password = new JLabel("Password");
        Password.setBounds(85, 165, 110, 40);
        Password.setFont(new Font("Tahoma",Font.BOLD,20));
        Password.setForeground(Color.BLACK);
        cp.add(Password);

      ShowBt = new JButton("Show");
      ShowBt.setBounds(500, 212, 69, 35);
      ShowBt.setBackground(new Color(255, 146, 43));   
      ShowBt.setFont(new Font("Tahoma",Font.BOLD,10));
      ShowBt.setForeground(Color.white);
      ShowBt.setFocusPainted(false);
      ShowBt.addActionListener(this);
      cp.add(ShowBt);
      
      LoginBt = new JButton("Login");
      LoginBt.setBounds(135,285,150,85);
      LoginBt.setFont(new Font("Tahoma",Font.BOLD,30));
      LoginBt.setBackground(new Color(255, 146, 43));
      LoginBt.setForeground(Color.white);
      LoginBt.setFocusPainted(false);
      LoginBt.addActionListener(this);
      cp.add(LoginBt);

      SignupBT = new JButton("SignUp");
      SignupBT.setBounds(345,285,150,85);
      SignupBT.setFont(new Font("Tahoma",Font.BOLD,30));
      SignupBT.setBackground(new Color(255, 146, 43));
      SignupBT.setForeground(Color.white);
      SignupBT.setFocusPainted(false);
      SignupBT.addActionListener(this);
      cp.add(SignupBT);

      BackBt = new JButton("🔙");
      BackBt.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
      BackBt.setBounds(10, 428, 145, 80);
      BackBt.setBackground(new Color(255, 146, 43));
      BackBt.setForeground(Color.WHITE);
      BackBt.setFocusPainted(false);
      BackBt.addActionListener(this);
      cp.add(BackBt);

      Pass = new JPasswordField(50);
      Pass.setBounds(85, 210, 400, 40);
      Pass.setFont(new Font("Tahoma",Font.BOLD,20));
      cp.add(Pass);

      Draw1 a = new Draw1();
      a.setBounds(55, 80, 530, 340); 
      cp.add(a);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
     }
     class Draw1 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ล้างหน้าจอก่อนวาด
        
        int w = getWidth();  // ดึงความกว้างจริงของ JPanel (550)
        int h = getHeight(); // ดึงความสูงจริงของ JPanel (350)

        // วาดสี่เหลี่ยมสีครีมเต็มพื้นที่ Panel
        g.setColor(new Color(255, 249, 219));   
        g.fillRect(0, 0, w, h);

        // วาดเส้นขอบสีดำรอบๆ Panel (ลบออก 1 เพื่อไม่ให้เส้นขอบหลุดเฟรม)
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, w - 1, h - 1);
    }
}
     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource()==BackBt) {
            new Mainpage.Mainpagepanel();
            this.dispose();
         }
         else if(e.getSource()==LoginBt){
            new Mainpage.Mainpagepanel();
            this.dispose();
         }
         else if(e.getSource()==SignupBT){
            new SignUpPage();
            this.dispose();
         }
     }
}