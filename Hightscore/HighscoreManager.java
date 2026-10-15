package Hightscore;
import  java.io.*;
import  java.util.*;
public class HighscoreManager {
    private static final String FILE_PATH = "Data/Highscore.csv";
    
    public  static  class ScoreEntry {
        public String username;
        public int score;
        public String level;

        public  ScoreEntry(String username,int score,String level){
            this.username = username;
            this.score = score;
            this.level = level;
        }
    }

    public static void saveScore(String username,int score,String level){
        if (username == null || username.trim().isEmpty()|| username.equalsIgnoreCase("Guest")) {
            return ;
        }
        File dir = new File("Data");
        if(!dir.exists()){
            dir.mkdirs();
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true))){
            writer.write(username+","+score+","+level);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<ScoreEntry> getTop5Score(String targetLevel){
        Map<String,ScoreEntry> bestscoreMap = new HashMap<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String user = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    String lvl =  parts[2].trim();

                    if (!user.isEmpty() && !user.equalsIgnoreCase("Guest")) {
                        if (targetLevel == null || targetLevel.equalsIgnoreCase("All") || lvl.equalsIgnoreCase(targetLevel)) {
                            String key = user.toLowerCase()+"_"+lvl.toLowerCase();

                            if (!bestscoreMap.containsKey(key)||score>bestscoreMap.get(key).score) {
                                bestscoreMap.put(key, new ScoreEntry(user, score, lvl));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       List<ScoreEntry> list = new ArrayList<>(bestscoreMap.values());
       list.sort((a, b) -> Integer.compare(b.score, a.score));
       if (list.size()>5) {
            return list.subList(0, 5);
       }
       return list;
    }
}
