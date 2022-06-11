import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.GameStatus;
import model.Player;
import service.GameManager;

public class Main {
    public static void main(final String[] args) throws IOException {
        Player player1  = new Player("Varsha", true);
        Player player2 = new Player("Akshita", false);
        GameManager gameManager = new GameManager(player1, player2);
        File file = new File("/Users/varsha.lalwani/personal/Chess/src/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String input = reader.readLine();
        while (input != null) {
            String[] inputSplit = input.split(" ");
            if (inputSplit[0].equals("Varsha")) {
                if (gameManager.playerMove(player1, Integer.valueOf(inputSplit[1]),
                        Integer.valueOf(inputSplit[2]),
                        Integer.valueOf(inputSplit[3]),
                        Integer.valueOf(inputSplit[4]))) {
                    System.out.printf("Varsha moved from %s %s to %s %s %n",inputSplit[1],
                            inputSplit[2], inputSplit[3], inputSplit[4]);
                    GameStatus status = gameManager.getGameStatus();
                    if (!status.equals(GameStatus.ACTIVE)) {
                        System.exit(0);
                    }
                } else {
                    System.out.printf("Invalid move of Varsha from %s %s to %s %s %n",inputSplit[1],
                            inputSplit[2], inputSplit[3], inputSplit[4]);
                }
            } else if (inputSplit[0].equals("Akshita")) {
                if (gameManager.playerMove(player2, Integer.valueOf(inputSplit[1]),
                        Integer.valueOf(inputSplit[2]),
                        Integer.valueOf(inputSplit[3]),
                        Integer.valueOf(inputSplit[4]))) {
                    System.out.printf("Akshita moved from %s %s to %s %s %n",inputSplit[1],
                            inputSplit[2], inputSplit[3], inputSplit[4]);
                    GameStatus status = gameManager.getGameStatus();
                    if (!status.equals(GameStatus.ACTIVE)) {
                        System.exit(0);
                    }
                } else {
                    System.out.printf("Invalid move of Akshita from %s %s to %s %s %n",inputSplit[1],
                            inputSplit[2], inputSplit[3], inputSplit[4]);
                }
            } else {
                System.out.println("Invalid User %n");
            }
            input = reader.readLine();
        }
    }
}
