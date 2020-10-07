package GUI;

import Logic.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Main extends Application {
    public static AnchorPane pane;
    public static Player player1;
    public static Player player2;
    public static int round = 0;
    @Override
    public void start(Stage primaryStage) {
        player1 = new Player("player 1", Color.RED);
        player2 = new Player("player 2", Color.GREEN);

        pane = new AnchorPane();
        Button newGame = new Button("new game");
        newGame.setMinSize(150,50);
        newGame.setId("newGame");
        newGame.setTooltip(new Tooltip("new game"));

        pane.getChildren().add(newGame);
        AnchorPane.setLeftAnchor(newGame, (double) 75);
        AnchorPane.setTopAnchor(newGame, (double) 25);

        newGame.setOnMousePressed(event -> {
            NewGame newGameDuty = new NewGame();
            newGameDuty.newGamePane(primaryStage,this);
        });


        Rectangle spacer = new Rectangle(300,5,Color.RED);
        spacer.setOpacity(0.4);
        pane.getChildren().add(spacer);

        AnchorPane.setLeftAnchor(spacer, (double) 0);
        AnchorPane.setTopAnchor(spacer, (double) 95);

        Button exitButton = new Button("Exit");
        exitButton.setMinSize(150,50);
        exitButton.setId("exitButton");
        exitButton.setTooltip(new Tooltip("Exit the game"));
        exitButton.setOnMousePressed(event -> {
            try {
                stop();
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pane.getChildren().add(exitButton);
        AnchorPane.setLeftAnchor(exitButton,(double) 75);
        AnchorPane.setBottomAnchor(exitButton,(double) 25);

        Scene scene = new Scene(pane, 300, 200);
        scene.getStylesheets().add(getClass().getResource("mainCSS.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("menu");
        primaryStage.show();
    }


    public void restart(Stage primaryStage) {
        this.start(primaryStage);
    }
}