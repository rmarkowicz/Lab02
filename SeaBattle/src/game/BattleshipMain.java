package game;



import java.util.Random;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import game.Board.Cell;


public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();


    //creating screen game
    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 700);


        Text t = new Text();
        Text t1 = new Text();

        t.setText("Instruction: 1: Right-click to set the ships horizontally 2: Left-click to set the ships vertically");


        root.setTop(t);








        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                root.setRight(new Text("INSTRUCTION: asdsadsa"));
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        HBox hbox = new HBox(100, enemyBoard, playerBoard);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #F0F8FF;");

        root.setCenter(hbox);
        return root;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("SeaBattle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();


        TextArea textArea = new TextArea();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
