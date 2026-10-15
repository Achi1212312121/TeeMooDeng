package GameModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public  class Game extends JFrame implements ActionListener {
    private Container cp;//ตัวcontainer
    private JButton Button[][]; //ตัวbutton
    private  JPanel p,toppanel;//ตัวpanel
    private  int gametimer,score=0;//ตัวนับเวลาและคะแนน
    private  String level;//ตัวlevel
    private  Timer timer;//ตัวนับถอยหลัง
    private  Timer modelTimer;//ตัวนับเวลาหมูเด้งออกมา
    private  JLabel timelabl,scorLabel;//บอกเวลามบอกคะแนน
    private  ImageIcon[] moodengIcons = new ImageIcon[7];//รูปหมูเด้งกับของ
    private  int[] moleScore = {5,10,20,30,-10,-20,-30};//คะแนนของหมุเด้งและอื่นๆ
    private int currentMoleRow = -1;//ตัวเช็กสถานะแถว
    private int currentMoleCol = -1;//ตัวเช็กสถานะหลัก
    private int currentMoleType = -1;//ประเภทหมูเด้ง
    private Random random = new Random();//ตัวสุ่ม
    private  int modeldelay=800;//ตัวหน่วงเวลา
    private  int[] availableTypes;//ประเภทของต่างๆในlevelนั้นๆ

    public  Game(String level){
        super("TeeMooDeng-Level:"+level);
        this.level = level;//เก็บlevelที่เลือก
        if(level.equals("Easy")){
            gametimer = 30;//เวลา
            modeldelay = 900;//ทุกๆ0.9วิ
            availableTypes = new int[]{0,1,4};//ตัวหมูเด้งที่จะออกมา
        }else if (level.equals("Medium")) {
           gametimer = 30;;
            modeldelay = 600;//ทุกๆ0.6วิ
            availableTypes = new int[]{0,1,2,4,5};//ตัวหมูเด้งที่จะออกมา
        }else if(level.equals("Hard")){
            gametimer = 30;
            modeldelay = 450;//ทุกๆ0.45วิ
            availableTypes = new int[]{0,1,2,3,4,5,6};//ตัวหมูเด้งที่จะออกมา
        }
        String[]imagePath ={
            "Image/normalmoodeng-nobg.png",//index 0
            "Image/anglemoodeng-nobg.png",//index 1
            "Image/ranbowmoodeng-nobg.png",//index 2
            "Image/kruba-nobg.png",//index 3
            "Image/boom-nobg.png",//index 4
            "Image/krubaevil.png",//index 5
            "Image/evilmoodeng-nobg.png"//index 6
        };
        for(int i=0;i<7;i++){
            ImageIcon rawIcon = new ImageIcon(imagePath[i]);//เอารูปจากpathมาใส่ในImageIcon
            Image img = rawIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);//ปรับขนาดรูปให้เป็น200*200
            moodengIcons[i] = new ImageIcon(img);//เอารูปที่ปรับขนาดแล้วมาใส่ในarray
        }
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout()); 
        cp.setBackground(new Color(233, 250, 200));

        toppanel = new JPanel();
        toppanel.setLayout(new GridLayout(2, 1));
        toppanel.setBackground(new Color(233, 250, 200));
       
        timelabl = new JLabel("Remaining time:"+gametimer+" sec",SwingConstants.CENTER);
        timelabl.setFont(new Font("Tahoma",Font.BOLD,25));
        timelabl.setForeground(Color.BLACK);
        toppanel.add(timelabl);

        scorLabel = new JLabel("Score:"+score,SwingConstants.CENTER);
        scorLabel.setFont(new Font("Tahoma",Font.BOLD,25));
        scorLabel.setForeground(Color.black);
        toppanel.add(scorLabel);

        cp.add(toppanel,BorderLayout.NORTH);//เอาpanelที่มีlabelเวลาและคะแนนไปใส่ในcontainer
        p = new JPanel();//ตัวpanelที่เอาbuttonใส่
        p.setLayout(new GridLayout(3,3));//เอาbuttonใส่ในpanel
        Button = new JButton[3][3];//สร้างbuttonเป็นarray 3*3
       for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
                Button[i][j] = new JButton();
                Button[i][j].setPreferredSize(new Dimension(200,200));
                    Button[i][j].addActionListener(this);
                    Button[i][j].setBackground(new Color(255,146,33));
                    Button[i][j].setFocusPainted(false); 
                    p.add(Button[i][j]);
        }
       }
       cp.add(p,BorderLayout.CENTER);//เอาpanelที่มีbuttonไปใส่ในcontainer

       modelTimer = new Timer(modeldelay, new ActionListener() {    
        @Override
        public void actionPerformed(ActionEvent e) {
            spawMole();
        }
       });//ตัวคุมเวลาหมูเด้งออกมา

       timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gametimer--;//ลดเวลาลง1วิ
            timelabl.setText("Remaining time:"+gametimer+"sce");//อัพเดทlabelเวลา
            if (gametimer<=0) {
                timer.stop();
                modelTimer.stop();
                JOptionPane.showMessageDialog(Game.this, "Time Out!!\nYourScore:"+score,"Game Over",JOptionPane.INFORMATION_MESSAGE);
                new GameOverPage(score,level);//เปิดหน้าต่างGameOverPageโดยส่งscoreและlevelไปเป็นพารามิเตอร์
                Game.this.dispose();
            }
        }
       });//ตัวคุมเวลานับถอยหลัง
        timer.start();//เริ่มนับถอยหลัง
        modelTimer.start();//เริ่มนับเวลาหมูเด้งออกมา
        this.setSize(650,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void spawMole(){
        if(currentMoleRow != -1 && currentMoleCol != -1){
            Button[currentMoleRow][currentMoleCol].setIcon(null);//ถ้ามีหมูเด้งอยู่แล้วให้ลบออกก่อน
        }
        currentMoleCol = random.nextInt(3);//สุ่มหลัก
        currentMoleRow = random.nextInt(3);//สุ่มแถว
        int randomindex = random.nextInt(availableTypes.length);//สุ่มประเภทหมูเด้ง
        currentMoleType = availableTypes[randomindex];//เอาประเภทหมูเด้งที่สุ่มได้มาใส่ในตัวแปร
        Button[currentMoleRow][currentMoleCol].setIcon(moodengIcons[currentMoleType]);//เอารูปหมูเด้งที่สุ่มได้มาใส่ในbutton
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       JButton clickedButton = (JButton) e.getSource();//เอาbuttonที่กดมาใส่ในตัวแปร
       if (currentMoleCol != -1 && currentMoleRow != -1 && clickedButton == Button[currentMoleRow][currentMoleCol]) {
         score += moleScore[currentMoleType];//ถ้ากดถูกbuttonที่มีหมูเด้งอยู่ให้เพิ่มคะแนนและลบหมูเด้งออก
         Button[currentMoleRow][currentMoleCol].setIcon(null);//ลบหมูเด้งออก
         currentMoleRow = -1;//ลบหมูเด้งออก
         currentMoleCol = -1;//ลบหมูเด้งออก
       }
       else score -= 5;//ถ้ากดbuttonที่ไม่มีหมูเด้งอยู่ให้ลบคะแนน5
       if (score<0) {
        score = 0;//ถ้าคะแนนติดลบให้เป็น0
       }
       scorLabel.setText("Score:" + score);//อัพเดทlabelคะแนน
    }
     
}