package Source;

import java.io.*;
import java.util.Properties;

public class Score {
    private static final String SCORES_FILE_PATH = "scores.properties";





    public static void main(String[] args) {
        Score game = new Score();
        game.registerScore("Player1", 1000);
        int score = game.getScore("Player1");
        System.out.println("Player1 score: " + score);

        // Unit test
        String playerName = "Player1";
        int levelScore = 300;
        game.registerScore(playerName, levelScore);
    }

    public void registerScore(String player, int score) {
        Properties properties = loadProperties();
        int highestScore = getScore(player);
        if (score > highestScore) {
            properties.setProperty(player, String.valueOf(score));
            saveProperties(properties);
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





