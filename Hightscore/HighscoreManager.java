package Hightscore;
import  java.io.*;
import  java.util.*;
public class HighscoreManager {
    private static final String FILE_PATH = "Data/Highscore.csv";// Path to the highscore file
    
    public  static  class ScoreEntry {
        public String username;// Username of the player
        public int score;// Score achieved by the player
        public String level;// Level at which the score was achieved

        public  ScoreEntry(String username,int score,String level){
            this.username = username;
            this.score = score;
            this.level = level;
        }
    }//บันทึกคะแนนของผู้เล่นลงในไฟล์ CSV โดยจะบันทึกเฉพาะผู้เล่นที่ไม่ใช่ Guest และจะสร้างโฟลเดอร์ Data หากยังไม่มีอยู่

    public static void saveScore(String username,int score,String level){
        if (username == null || username.trim().isEmpty()|| username.equalsIgnoreCase("Guest")) {
            return ;
        }//กำหนดให้ Guest ไม่สามารถบันทึกคะแนนได้
        File dir = new File("Data");//ถ้าโฟลเดอร์ Data ไม่ได้ถูกสร้างขึ้นมาก่อน ให้สร้างโฟลเดอร์ Data ขึ้นมา
        if(!dir.exists()){
            dir.mkdirs();//สร้างโฟลเดอร์ Data
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true))){
            writer.write(username+","+score+","+level);//บันทึกข้อมูลลงในไฟล์ CSV
            writer.newLine();//ขึ้นบรรทัดใหม่
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<ScoreEntry> getTop5Score(String targetLevel){
        Map<String,ScoreEntry> bestscoreMap = new HashMap<>();//ใช้ HashMap เพื่อเก็บคะแนนสูงสุดของแต่ละผู้เล่นในแต่ละระดับ
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();//ตรวจสอบว่าไฟล์ Highscore.csv มีอยู่หรือไม่ ถ้าไม่มีให้คืนค่าเป็น List ว่าง
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");//แยกข้อมูลในแต่ละบรรทัดโดยใช้เครื่องหมายคอมม่าเป็นตัวแบ่ง
                if (parts.length == 3) {
                    String user = parts[0].trim();//ดึงชื่อผู้เล่น
                    int score = Integer.parseInt(parts[1].trim());//ดึงคะแนนของผู้เล่น
                    String lvl =  parts[2].trim();//ดึงระดับของผู้เล่น

                    if (!user.isEmpty() && !user.equalsIgnoreCase("Guest")) {
                        if (targetLevel == null || targetLevel.equalsIgnoreCase("All") || lvl.equalsIgnoreCase(targetLevel)) {
                            String key = user.toLowerCase()+"_"+lvl.toLowerCase();//สร้างคีย์เฉพาะสำหรับผู้เล่นและระดับเพื่อเก็บคะแนนสูงสุดของผู้เล่นในระดับนั้น

                            if (!bestscoreMap.containsKey(key)||score>bestscoreMap.get(key).score) {
                                bestscoreMap.put(key, new ScoreEntry(user, score, lvl));//บันทึกคะแนนสูงสุดของผู้เล่นในระดับนั้นลงใน HashMap
                            }
                        }
                    }
                }//ตรวจสอบว่าผู้เล่นไม่ใช่ Guest และระดับตรงกับ targetLevel หรือไม่ ถ้าใช่ให้บันทึกคะแนนสูงสุดของผู้เล่นในระดับนั้นลงใน HashMap
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       List<ScoreEntry> list = new ArrayList<>(bestscoreMap.values());//สร้าง List จากค่าของ HashMap ซึ่งจะเก็บคะแนนสูงสุดของผู้เล่นในแต่ละระดับ
       list.sort((a, b) -> Integer.compare(b.score, a.score));//เรียงลำดับ List ตามคะแนนจากมากไปน้อย
       if (list.size()>5) {
            return list.subList(0, 5);//คืนค่าเฉพาะ 5 อันดับแรกของ List ถ้ามีมากกว่า 5 รายการ
       }//ถ้ามีมากกว่า 5 รายการให้คืนค่าเฉพาะ 5 อันดับแรก
       return list;//ถ้ามีไม่เกิน 5 รายการให้คืนค่าทั้งหมด
    }
}
