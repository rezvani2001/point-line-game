package GUI;

import Logic.Player;
import Logic.Setting;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class NewGame {

    TextField name;
    ComboBox colorPicker;
    Main main;

    public void newGamePane(Stage primaryStage,Main main){
        VBox box = new VBox();
        this.main = main;
        AnchorPane labelPane = new AnchorPane();
        labelPane.setPrefSize(300,50);
        Label playerLabel = new Label("Player 1");
        labelPane.getChildren().add(playerLabel);

        AnchorPane.setLeftAnchor(playerLabel,127.5);
        AnchorPane.setRightAnchor(playerLabel,127.5);
        AnchorPane.setTopAnchor(playerLabel,20.0);
        box.getChildren().add(labelPane);


        AnchorPane nameAnchor = new AnchorPane();
        nameAnchor.setPrefSize(300,40);

        Label nameLabel = new Label("name");
        name = new TextField(Main.player1.getName());
        name.setPrefSize(200,20);

        name.addEventHandler(ActionEvent.ACTION, (EventHandler<Event>) event -> secondPlayer(primaryStage));

        name.setTooltip(new Tooltip("please enter your name"));

        nameAnchor.getChildren().addAll(nameLabel,name);
        AnchorPane.setLeftAnchor(nameLabel,20.0);
        AnchorPane.setRightAnchor(name,20.0);
        box.getChildren().add(nameAnchor);


        AnchorPane colorAnchor = new AnchorPane();
        colorAnchor.setPrefSize(200,50);

        Label colorLabel = new Label("Color");
        ComboBox colorPicker = setColorPicker();

        colorPicker.setTooltip(new Tooltip("color of your pen!"));

        colorAnchor.getChildren().addAll(colorLabel,colorPicker);
        AnchorPane.setLeftAnchor(colorLabel,20.0);
        AnchorPane.setTopAnchor(colorLabel,5.0);
        AnchorPane.setRightAnchor(colorPicker,20.0);
        box.getChildren().add(colorAnchor);

        AnchorPane buttons = new AnchorPane();
        buttons.setPrefSize(300,50);

        Button nextButton = new Button("Next");
        nextButton.setMinSize(100,30);
        nextButton.setId("nextButton");
        nextButton.setTooltip(new Tooltip("next player"));
        nextButton.setOnMousePressed(event -> {
            if ( nameChecker(name.getText()).length() != 0 ) {
                if (colorPicker.getValue() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("please choose a color");
                    alert.showAndWait();
                }else {
                    Main.player1 = new Player(name.getText(), getSelectedColor((Rectangle) colorPicker.getValue()));

                    secondPlayer(primaryStage);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("please insert your name");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setMinSize(100,30);
        backButton.setId("backButton");
        backButton.setTooltip(new Tooltip("back to menu"));
        backButton.setOnMousePressed(event -> {
            try {
                main.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttons.getChildren().addAll(backButton,nextButton);
        AnchorPane.setLeftAnchor(backButton,25.0);
        AnchorPane.setRightAnchor(nextButton,25.0);

        box.getChildren().add(buttons);
        Scene scene = new Scene(box,300,200);
        scene.getStylesheets().add(getClass().getResource("NewGameCSS.css").toExternalForm());
        primaryStage.setTitle("player 1");
        primaryStage.setScene(scene);
    }





    public void secondPlayer (Stage primaryStage){
        VBox box = new VBox();

        AnchorPane labelPane = new AnchorPane();
        labelPane.setPrefSize(300,50);
        Label playerLabel = new Label("Player 2");
        labelPane.getChildren().add(playerLabel);

        AnchorPane.setLeftAnchor(playerLabel,127.5);
        AnchorPane.setRightAnchor(playerLabel,127.5);
        AnchorPane.setTopAnchor(playerLabel,20.0);
        box.getChildren().add(labelPane);


        AnchorPane nameAnchor = new AnchorPane();
        nameAnchor.setPrefSize(300,40);

        Label nameLabel = new Label("name");
        name = new TextField(Main.player2.getName());
        name.setPrefSize(200,20);

        name.addEventHandler(ActionEvent.ACTION, (EventHandler<Event>) event -> widesAndLength(primaryStage));

        name.setTooltip(new Tooltip("please enter your name"));

        nameAnchor.getChildren().addAll(nameLabel,name);
        AnchorPane.setLeftAnchor(nameLabel,20.0);
        AnchorPane.setRightAnchor(name,20.0);
        box.getChildren().add(nameAnchor);


        AnchorPane colorAnchor = new AnchorPane();
        colorAnchor.setPrefSize(200,50);

        Label colorLabel = new Label("Color");
        colorPicker = setColorPicker();
        colorPicker.setPrefSize(200,30);

        colorPicker.setTooltip(new Tooltip("color of your pen!"));

        colorAnchor.getChildren().addAll(colorLabel,colorPicker);
        AnchorPane.setLeftAnchor(colorLabel,20.0);
        AnchorPane.setTopAnchor(colorLabel,5.0);
        AnchorPane.setRightAnchor(colorPicker,20.0);
        box.getChildren().add(colorAnchor);

        AnchorPane buttons = new AnchorPane();
        buttons.setPrefSize(300,50);

        Button nextButton = new Button("Next");
        nextButton.setMinSize(100,30);
        nextButton.setId("nextButton");
        nextButton.setTooltip(new Tooltip("game board diameters"));
        nextButton.setOnMousePressed(event -> {
            if (nameChecker(name.getText()).length() != 0) {
                if (!nameChecker(name.getText()).equals(nameChecker(Main.player1.getName()))) {
                    if (colorPicker.getValue() == null){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("please choose a color");
                            alert.showAndWait();
                    }else{
                        if (!getSelectedColor((Rectangle) colorPicker.getValue()).equals(Main.player1.getColor())) {
                            Main.player2 = new Player(name.getText(), getSelectedColor((Rectangle) colorPicker.getValue()));

                            widesAndLength(primaryStage);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("this color is selected by player 1");
                            alert.showAndWait();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("this name is selected by player 1");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("please insert your name");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setMinSize(100,30);
        backButton.setId("backButton");
        backButton.setOnMousePressed(event -> this.newGamePane(primaryStage, main));
        backButton.setTooltip(new Tooltip("change previous player"));
        buttons.getChildren().addAll(backButton,nextButton);
        AnchorPane.setLeftAnchor(backButton,25.0);
        AnchorPane.setRightAnchor(nextButton,25.0);

        box.getChildren().add(buttons);

        Scene scene = new Scene(box,300,200);
        scene.getStylesheets().add(getClass().getResource("NewGameCSS.css").toExternalForm());
        primaryStage.setTitle("player 2");
        primaryStage.setScene(scene);
    }



    public void widesAndLength(Stage primaryStage){
        VBox box = new VBox();

        AnchorPane labelPane = new AnchorPane();
        labelPane.setPrefSize(300,50);
        Label label = new Label("Dimensions");
        labelPane.getChildren().add(label);

        AnchorPane.setLeftAnchor(label,117.5);
        AnchorPane.setRightAnchor(label,117.5);
        AnchorPane.setTopAnchor(label,20.0);

        AnchorPane row = new AnchorPane();
        row.setPrefSize(200,40);
        String rowN = "";
        rowN += Setting.rows;
        Label rowLabel = new Label("rows");
        TextField rowField = new TextField(rowN);
        rowField.setAlignment(Pos.CENTER);

        rowField.setTooltip(new Tooltip("the height of the game board\nis a number between 5 and 30"));

        rowField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                rowField.setText(newValue.replaceAll("[^\\d]", ""));
            }

        });


        rowField.setPrefSize(200,20);


        row.getChildren().addAll(rowLabel,rowField);
        AnchorPane.setRightAnchor(rowField,20.0);
        AnchorPane.setLeftAnchor(rowLabel,20.0);

        AnchorPane columns = new AnchorPane();
        columns.setPrefSize(200,50);
        Label columnLabel = new Label("columns");
        String columnN = "";
        columnN += Setting.columns;
        TextField columnField = new TextField(columnN);
        columnField.setAlignment(Pos.CENTER);

        columnField.setTooltip(new Tooltip("the width of the game board\nis a number between 5 and 19"));

        columnField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                columnField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });



        columnField.setPrefSize(200,20);



        columns.getChildren().addAll(columnLabel,columnField);

        AnchorPane.setRightAnchor(columnField,20.0);
        AnchorPane.setLeftAnchor(columnLabel,20.0);

        AnchorPane buttons = new AnchorPane();
        buttons.setPrefSize(300,50);

        Button backButton = new Button("Back");
        backButton.setMinSize(100,30);
        backButton.setId("backButton");
        backButton.setTooltip(new Tooltip("change previous players"));
        backButton.setOnMousePressed(event -> secondPlayer(primaryStage));

        Button nextButton = new Button("Next");
        nextButton.setMinSize(100,30);
        nextButton.setId("nextButton");
        nextButton.setTooltip(new Tooltip("lets play the game!"));
        nextButton.setOnMousePressed(event -> {
            if (rowField.getText().length() < 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rowField can't be empty");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }

            if (columnField.getText().length() < 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columnField can't be empty");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }

            int rows = Integer.parseInt(rowField.getText());
            int column = Integer.parseInt(columnField.getText());

            if (rows > 30) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rows can't be bigger than 30");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else if (rows < 5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rows can't be smaller than 5");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else{
                Setting.rows = rows;
            }


            if (column >= 20) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columns can't be bigger than 19");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else if (column < 5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columns can't be smaller than 5");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else{
                Setting.columns = column;
            }


            mainGamePage game = new mainGamePage();
            try {
                game.maineGame(primaryStage,main);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        rowField.addEventHandler(ActionEvent.ACTION, event -> columnField.requestFocus());
        columnField.addEventHandler(ActionEvent.ACTION,event -> {
            if (rowField.getText().length() < 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rowField can't be empty");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }

            if (columnField.getText().length() < 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columnField can't be empty");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }


            int rows = Integer.parseInt(rowField.getText());
            int column = Integer.parseInt(columnField.getText());

            if (rows > 30) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rows can't be bigger than 30");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else if (rows < 5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("rows can't be smaller than 5");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else{
                Setting.rows = rows;
            }


            if (column >= 20) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columns can't be bigger than 19");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else if (column < 5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("columns can't be smaller than 5");
                alert.showAndWait();
                widesAndLength(primaryStage);
                return;
            }else{
                Setting.columns = column;
            }


            mainGamePage game = new mainGamePage();
            try {
                game.maineGame(primaryStage,main);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        buttons.getChildren().addAll(backButton,nextButton);
        AnchorPane.setLeftAnchor(backButton,25.0);
        AnchorPane.setRightAnchor(nextButton,25.0);


        box.getChildren().addAll(labelPane,row,columns,buttons);

        Scene scene = new Scene(box,300,200);
        scene.getStylesheets().add(getClass().getResource("NewGameCSS.css").toExternalForm());
        primaryStage.setTitle("diameters");
        primaryStage.setScene(scene);
    }



    public String nameChecker (String name){
        StringBuilder nameBuilder = new StringBuilder(name);
        for (int i = 0; i < nameBuilder.length(); i++) {
            if (nameBuilder.charAt(i) == ' '){
                for (int j = i ; j < nameBuilder.length() - 1; j++) {
                    nameBuilder.setCharAt(j,nameBuilder.charAt(j + 1));
                }
                i--;
                nameBuilder.setLength(nameBuilder.length() - 1);
            }
        }
        return nameBuilder.toString();
    }


    public ComboBox setColorPicker (){
        ComboBox<Rectangle> comboBox = new ComboBox<>();
        comboBox.setMaxSize(200,30);
        comboBox.setEditable(false);
        Rectangle rectangle ;

        rectangle = new Rectangle(185,20,Color.BLACK);
        rectangle.setAccessibleText("Black");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185,20,Color.RED);
        rectangle.setAccessibleText("Red");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.GREEN);
        rectangle.setAccessibleText("Green");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.ROYALBLUE.darker());
        rectangle.setAccessibleText("RoyalBlue");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.BURLYWOOD);
        rectangle.setAccessibleText("BurlyWood");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.CORAL);
        rectangle.setAccessibleText("Coral");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 ,Color.valueOf("fd5e11"));
        rectangle.setAccessibleText("fd5e11");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.FUCHSIA);
        rectangle.setAccessibleText("Fuchsia");
        comboBox.getItems().add(rectangle);

        rectangle = new Rectangle(185 , 20 , Color.PURPLE);
        rectangle.setAccessibleText("Purple");
        comboBox.getItems().add(rectangle);

        return comboBox;
    }


    public Color getSelectedColor(Rectangle value){
        switch (value.getAccessibleText()){
            case "Red" :
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "RoyalBlue":
                return Color.ROYALBLUE.darker();
            case "BurlyWood":
                return Color.BURLYWOOD;
            case "Coral":
                return Color.CORAL;
            case "Fuchsia":
                return Color.FUCHSIA;
            case "Purple":
                return Color.PURPLE;
            case "fd5e11" :
                return Color.valueOf("fd5e11");
            case "Black" :
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }
}
