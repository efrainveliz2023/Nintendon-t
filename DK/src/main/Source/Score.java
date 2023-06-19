package main.Source;

import java.io.*;
import java.util.Properties;

public class Score {
    private static final String SCORES_FILE_PATH = "scores.properties";



   public void Run(int actualScore){
       registerScore("Player1",actualScore,0);
   }



    public void registerScore(String player, int score,int tiempo) {
        Properties properties = loadProperties();
        int highestScore = getScore(player);
        int lowestTime = getTime();
        if (score >highestScore) {
            properties.setProperty("Time", String.valueOf(tiempo));
            properties.setProperty(player, String.valueOf(score));
            saveProperties(properties);

        }
        if(score==highestScore){
            if (tiempo < lowestTime) {
                properties.setProperty("Time", String.valueOf(tiempo));
                saveProperties(properties);
            }
        }

    }

    public int getScore(String player) {
        Properties properties = loadProperties();
        String score = properties.getProperty(player);
        if (score != null) {
            return Integer.parseInt(score);
        }
        return 0;
    }
    public int getTime() {
        Properties properties = loadProperties();
        String time = properties.getProperty("Time");
        if (time != null) {
            return Integer.parseInt(time);
        }
        return 0;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SCORES_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            // El archivo de puntuaciones no existe, se crea uno nuevo
        }
        return properties;
    }

    private void saveProperties(Properties properties) {
        try (OutputStream outputStream = new FileOutputStream(SCORES_FILE_PATH)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}





