package Login;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SignUpPage extends JFrame implements ActionListener {
    private  Container cp;
    private  JButton LoginBt,Signupbt1,Clerabt,Show1,Show2;
    private  JToggleButton SignupBt;
    private JLabel Username,Password,ConfirmPassword;
    private JTextField Name;
    private JPasswordField Psword,ConPassword;
    public SignUpPage(){
        
        super("SignUp");
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(Color.white);

        LoginBt = new JButton("Login");
        LoginBt.setBounds(110, 50, 182, 64);
        LoginBt.setFont(new Font("Tahoma",Font.BOLD,20));
        LoginBt.setBackground(new Color(255, 146, 43));
        LoginBt.setForeground(Color.white);
        LoginBt.setFocusPainted(false);
        LoginBt.addActionListener(this);
        cp.add(LoginBt);

        SignupBt = new JToggleButton("Signup");
        SignupBt.setBounds(345, 50, 182, 64);
        SignupBt.setFont(new Font("Tahoma",Font.BOLD,20));
        SignupBt.setBackground(new Color(255, 146, 43));
        SignupBt.setForeground(Color.white);
        SignupBt.setFocusPainted(false);
        SignupBt.setSelected(true);
        SignupBt.addItemListener(e -> {
       //ทำให้ปุ่มไม่เด้งกลับมา
        if (!SignupBt.isSelected()) {
        SignupBt.setSelected(true);
        }
        });
        cp.add(SignupBt);

        Username = new JLabel("Username");
        Username.setBounds(99, 35, 120, 200);
        Username.setForeground(Color.black);
        Username.setFont(new Font("Tahoma",Font.BOLD,18));
        cp.add(Username);

        Name = new JTextField(50);
        Name.setBounds(99, 150, 455, 35);
        Name.setFont(new Font("Tahoma",Font.BOLD,20));
        Name.setForeground(Color.white);
        Name.setBackground(new Color(255, 146, 43));
        cp.add(Name);

        Password = new JLabel("Password");
        Password.setBounds(99,100,200,200);
        Password.setFont(new Font("Tahoma",Font.BOLD,19));
        Password.setForeground(Color.BLACK);
        cp.add(Password);

        Psword = new JPasswordField(50);
        Psword.setBounds(99, 220, 359, 35);
        Psword.setForeground(Color.white);
        Psword.setBackground(new Color(255, 146, 43));
        Psword.setFont(new Font("Tahoma",Font.BOLD,19));
        cp.add(Psword);

        ConfirmPassword = new  JLabel("ConfirmPassword");
        ConfirmPassword.setBounds(99, 170, 200, 200);
        ConfirmPassword.setForeground(Color.BLACK);
        ConfirmPassword.setFont(new Font("Tahoma",Font.BOLD,19));
        cp.add(ConfirmPassword);

        ConPassword = new JPasswordField(50);
        ConPassword.setBounds(99, 290, 359, 35);
        ConPassword.setForeground(Color.white);
        ConPassword.setBackground(new Color(255, 146, 43));
        ConPassword.setFont(new Font("Tahoma",Font.BOLD,19));
        cp.add(ConPassword);

        Show1 = new JButton("Show");
        Show1.setForeground(Color.white);
        Show1.setBackground(new Color(255, 146, 43));
        Show1.setBounds(482, 223, 68, 30);
        Show1.setFont(new Font("Tahoma",Font.BOLD,10));
        Show1.setFocusPainted(false);
        cp.add(Show1);

        Show2 = new JButton("Show");
        Show2.setForeground(Color.white);
        Show2.setBackground(new Color(255, 146, 43));
        Show2.setBounds(482, 293, 68, 30);
        Show2.setFont(new Font("Tahoma",Font.BOLD,10));
        Show2.setFocusPainted(false);
        cp.add(Show2);

        Signupbt1 = new JButton("Signup");
        Signupbt1.setForeground(Color.white);
        Signupbt1.setBackground(new Color(255, 146, 43));
        Signupbt1.setFont(new Font("Tahoma",Font.BOLD,30));
        Signupbt1.setBounds(130,360,160,68);
        Signupbt1.setFocusPainted(false);
        Signupbt1.addActionListener(this);
        cp.add(Signupbt1);

        Clerabt = new JButton("Clear");
        Clerabt.setBackground(new Color(255, 146, 43));
        Clerabt.setFont(new Font("Tahoma",Font.BOLD,30));
        Clerabt.setForeground(Color.white);
        Clerabt.setBounds(350, 360, 160, 68);
        Clerabt.setFocusable(false);
        cp.add(Clerabt);

        Draw1 b = new Draw1();
        b.setBounds(33, 23, 573, 470);
        cp.add(b);
        
        Draw a = new Draw();
        a.setBounds(16, 8, 606, 495); 
        cp.add(a);

        this.setSize(650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    class Draw extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(new Color(217,217,217));
            g.fillRect(0, 0, w, h);
        }
    }
    class Draw1 extends  JPanel{
        protected  void paintComponent(Graphics g){
            super.paintComponent(g);
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(new Color(255,249,219));
            g.fillRect(0, 0, w, h);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Signupbt1){
            new LoginPage();
            this.dispose();
        }
        else if(e.getSource()==LoginBt){
            new LoginPage();
            this.dispose();
        }
    }
}
