package GUI;

import Logic.GameBoard;
import Logic.Setting;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class mainGamePage {
    public static Label p1Score;
    public static Label p2Score;
    public static Rectangle p1Color;
    public static Rectangle p2Color;
    public static AnchorPane game;
    public static HBox gamePanel;
    Button backButton;
    Button endGame;
    public static Main main;
    public static Stage primaryStage;

    public void maineGame (Stage primaryStage,Main main) throws InterruptedException {
        mainGamePage.main = main;
        mainGamePage.primaryStage = primaryStage;
        GameBoard.fill();
        primaryStage.setResizable(false);
        GameBoard.makePane();
        gamePanel = new HBox(10);
        game = new AnchorPane(GameBoard.pane);
        AnchorPane.setLeftAnchor(GameBoard.pane,10.0);
        AnchorPane.setTopAnchor(GameBoard.pane,10.0);
        AnchorPane.setRightAnchor(GameBoard.pane,10.0);
        AnchorPane.setBottomAnchor(GameBoard.pane,10.0);
        gamePanel.getChildren().add(game);

        gamePanel.getChildren().add(makeRight());
        gamePanel.setPrefSize(((Setting.rows + 1) * 4) + Setting.rows * 35 + 170
                , ((Setting.columns + 1) * 4) + Setting.columns * 35 + 20);
        Scene scene = new Scene(gamePanel, ((Setting.rows + 1) * 4) + Setting.rows * 35 + 170
                , ((Setting.columns + 1) * 4) + Setting.columns * 35 + 20);
        scene.getStylesheets().add(getClass().getResource("mainPageCSS.css").toExternalForm());
        primaryStage.setTitle("point line");
        primaryStage.setScene(scene);
    }

    public static void refreshGamePlay (){
        Platform.runLater(() -> {
            game.getChildren().remove(0);
            try {
                game.getChildren().add(GameBoard.makePane());
                AnchorPane.setLeftAnchor(GameBoard.pane,10.0);
                AnchorPane.setTopAnchor(GameBoard.pane,10.0);
                AnchorPane.setRightAnchor(GameBoard.pane,10.0);
                AnchorPane.setBottomAnchor(GameBoard.pane,10.0);

                VBox box = (VBox) gamePanel.getChildren().get(1);
                AnchorPane pane = (AnchorPane) box.getChildren().get(0);
                Label p1Label = (Label) pane.getChildren().get(0);
                Label p2Label = (Label) pane.getChildren().get(1);

                p1Label.setText("Player1 score: " + Main.player1.getPoint());
                p2Label.setText("Player2 score: " + Main.player2.getPoint());
                if (Main.round % 2 == 0){
                    p1Score.setBorder(new Border(new BorderStroke(Main.player1.getColor(),BorderStrokeStyle.SOLID
                            ,CornerRadii.EMPTY,new BorderWidths(3))));
                    p2Score.setBorder(null);
                }
                else {
                    p2Score.setBorder(new Border(new BorderStroke(Main.player2.getColor(),BorderStrokeStyle.SOLID
                            ,CornerRadii.EMPTY,new BorderWidths(3))));
                    p1Score.setBorder(null);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public VBox makeRight (){
        VBox box = new VBox(10);
        box.setId("box");

        AnchorPane pane = new AnchorPane();
        p1Score = new Label();
        p1Score.setText("Player1 score: " + Main.player1.getPoint());

        p2Score = new Label("Player2 score: " + Main.player2.getPoint());

        if (Main.round % 2 == 0){
            p1Score.setBorder(new Border(new BorderStroke(Main.player1.getColor(),BorderStrokeStyle.SOLID
                    ,CornerRadii.EMPTY,new BorderWidths(3))));
            p2Score.setBorder(null);
        }
        else {
            p2Score.setBorder(new Border(new BorderStroke(Main.player2.getColor(),BorderStrokeStyle.SOLID
                    ,CornerRadii.EMPTY,new BorderWidths(3))));
            p1Score.setBorder(null);
        }

        VBox colorBoxes = new VBox(10);

        AnchorPane p1ColorBox = new AnchorPane();
        Label p1ColorLabel = new Label(Main.player1.getName());
        p1ColorLabel.setMaxWidth(65);
        p1Color = new Rectangle(65,20,Main.player1.getColor());
        p1ColorBox.getChildren().addAll(p1ColorLabel,p1Color);

        AnchorPane.setLeftAnchor(p1ColorLabel,5.0);
        AnchorPane.setRightAnchor(p1Color,5.0);

        AnchorPane p2ColorBox = new AnchorPane();
        Label p2ColorLabel = new Label(Main.player2.getName());
        p2ColorLabel.setMaxWidth(65);
        p2Color = new Rectangle(66,20,Main.player2.getColor());
        p2ColorBox.getChildren().addAll(p2ColorLabel,p2Color);

        AnchorPane.setLeftAnchor(p2ColorLabel,5.0);
        AnchorPane.setRightAnchor(p2Color,5.0);

        colorBoxes.getChildren().addAll(p1ColorBox,p2ColorBox);

        endGame = new Button("end Game");
        endGame.setPrefSize(90,15);
        endGame.setId("end");
        endGame.setTooltip(new Tooltip("end the game right now"));

        endGame.setOnMousePressed(event -> EndGame());

        backButton = new Button("Back");
        backButton.setPrefSize(90,15);
        backButton.setOnMousePressed(event -> {
            try {
                main.restart(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        backButton.setId("backButton");
        backButton.setTooltip(new Tooltip("back to menu"));

        VBox buttons = new VBox(10,backButton,endGame);

        pane.getChildren().addAll(p1Score,p2Score,colorBoxes,buttons);

        AnchorPane.setTopAnchor(p1Score,8.0);
        AnchorPane.setLeftAnchor(p1Score,20.0);
        AnchorPane.setRightAnchor(p1Score,20.0);

        AnchorPane.setTopAnchor(p2Score,33.0);
        AnchorPane.setLeftAnchor(p2Score,20.0);
        AnchorPane.setRightAnchor(p2Score,20.0);

        AnchorPane.setTopAnchor(colorBoxes,(double) (Setting.columns * 35 / 2  - 20));
        AnchorPane.setLeftAnchor(colorBoxes,5.0);
        AnchorPane.setRightAnchor(colorBoxes,5.0);

        AnchorPane.setBottomAnchor(buttons, 10.0);
        AnchorPane.setLeftAnchor(buttons,22.5);
        AnchorPane.setRightAnchor(buttons,22.5);


        box.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
                ,CornerRadii.EMPTY,new BorderWidths(3))));
        box.setPrefSize(139,((Setting.columns + 1) * 4) + Setting.columns * 35 + 16);

        pane.setPrefSize(139,((Setting.columns + 1) * 4) + Setting.columns * 35 + 16);
        box.getChildren().add(pane);
        return box;
    }

    public static void EndGame (){
        Alert winner = new Alert(Alert.AlertType.INFORMATION);
        if (Main.player1.getIntPoint() > Main.player2.getIntPoint()) {
            winner.setTitle("winner is " + Main.player1.getName());
        } else if (Main.player2.getIntPoint() > Main.player1.getIntPoint()) {
            winner.setTitle("winner is " + Main.player2.getName());
        } else {
            winner.setTitle("that's a fair");
        }
        winner.setContentText(Main.player1.getName() + ": " + Main.player1.getIntPoint() + "\n"
                + Main.player2.getName() + ": " + Main.player2.getIntPoint());
        winner.showAndWait();
        main.restart(primaryStage);
    }
}
