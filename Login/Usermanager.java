package Login;
import java.io.*;
import java.util.LinkedHashMap;

public class Usermanager {
    private static final String File_name="Data/Username_Password.csv";//ตัวแปรเก็บชื่อไฟล์
    private static LinkedHashMap<String,String> user = new LinkedHashMap<>();//ตัวแปรเก็บusernameและpassword
    private static String currentUser = null;//ตัวแปรเก็บusernameที่loginอยู่
    
    static {
        loadUser();//โหลดuserจากไฟล์
    }
    private static void loadUser(){
        File file = new File(File_name);
        if(!file.exists()) return ;//ถ้าไฟล์ไม่มีอยู่ให้return
        try(BufferedReader reader = new BufferedReader(new FileReader(file)) ){
            String line;//ตัวแปรเก็บบรรทัดที่อ่านจากไฟล์
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");//แยกusernameและpasswordด้วยเครื่องหมายคอมม่า
                if(data.length == 2){
                    user.put(data[0].trim(),data[1].trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean register(String username, String password){
        if (username.contains(",")) {
            return false;
        }
        if (user.containsKey(username)) {
            return false;
        }//ถ้ามีusernameนี้อยู่แล้วให้return false
        user.put(username, password);//ถ้าไม่มีusernameนี้อยู่แล้วให้เพิ่มusernameและpasswordลงในuser
        saveuser();//บันทึกuserลงในไฟล์
        return true;
    }
    public static void saveuser(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Username_Password.csv"))){
            for (String u : user.keySet()) {
                writer.write(u+","+user.get(u));//บันทึกusernameและpasswordลงในไฟล์
                writer.newLine();//ขึ้นบรรทัดใหม่
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean login(String username,String password){
        if (user.containsKey(username) && user.get(username).equals(password)) {
            currentUser = username; //ถ้าusernameและpasswordถูกต้องให้return trueและเก็บusernameที่loginอยู่ในcurrentUser
            return true;
        }
        return false;
    }
    public static String getCurrentUser(){
        return currentUser;
    }
    public  static  String logout(){
        return  currentUser = null;
    }
}
