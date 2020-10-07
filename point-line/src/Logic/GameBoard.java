package Logic;


import GUI.Main;
import GUI.mainGamePage;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class GameBoard {
    public static char[][] gamePlay = new char[Setting.rows * 2 + 1][Setting.columns * 2 + 1];
    public static GridPane pane;
    private static int sw = 0;

    public static void fill() {
        for (int i = 0; i < Setting.rows * 2 + 1; i++) {
            for (int j = 0; j < Setting.columns * 2 + 1; j++) {
                if (i % 2 == 1) {
                    gamePlay[i][j] = ' ';
                } else {
                    if (j % 2 == 1) {
                        gamePlay[i][j] = ' ';
                    } else {
                        gamePlay[i][j] = '.';
                    }
                }
            }
        }
    }

    public static GridPane makePane() throws InterruptedException {
        Thread thread = new Thread(() -> {
            GridPane gridPane = new GridPane();
            Rectangle rectangle = null;
            for (int i = 0; i < Setting.rows * 2 + 1; i++) {
                for (int j = 0; j < Setting.columns * 2 + 1; j++) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            rectangle = new Rectangle(4, 4, Color.BLACK);
                        } else {
                            if (gamePlay[i][j] == ' ') {
                                rectangle = new Rectangle(4, 35, Color.DARKGRAY);
                                rectangleEventHandler(rectangle, i, j);
                            } else if (gamePlay[i][j] == '-') {
                                rectangle = new Rectangle(4, 35, Main.player1.getColor());
                            } else if (gamePlay[i][j] == '|') {
                                rectangle = new Rectangle(4, 35, Main.player2.getColor());
                            }
                        }
                    } else {
                        if (j % 2 == 0) {
                            if (gamePlay[i][j] == ' ') {
                                rectangle = new Rectangle(35, 4, Color.DARKGRAY);
                                rectangleEventHandler(rectangle, i, j);

                            } else if (gamePlay[i][j] == '-') {
                                rectangle = new Rectangle(35, 4, Main.player1.getColor());
                            } else if (gamePlay[i][j] == '|') {
                                rectangle = new Rectangle(35, 4, Main.player2.getColor());
                            }
                        } else {
                            if (gamePlay[i][j] == ' ') {
                                rectangle = new Rectangle(33, 33, Color.valueOf("dcf5b5"));
                                rectangle.setStroke(Color.WHITE);
                            } else if (gamePlay[i][j] == '0') {
                                rectangle = new Rectangle(33, 33, Main.player1.getColor());
                                rectangle.setStroke(Main.player1.getColor().invert());
                            } else if (gamePlay[i][j] == '1') {
                                rectangle = new Rectangle(33, 33, Main.player2.getColor());
                                rectangle.setStroke(Main.player2.getColor().invert());
                            }
                        }
                    }
                    gridPane.add(rectangle, i, j);
                }
            }
            pane = gridPane;
        });

        thread.start();
        thread.join();
        return pane;
    }

    private static void rectangleEventHandler(Rectangle rectangle, int i, int j) {
        rectangle.setX(i);
        rectangle.setY(j);
        rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.ROYALBLUE));
        rectangle.setOnMouseExited(event -> rectangle.setFill(Color.DARKGRAY));

        rectangle.setOnMousePressed(event -> {
            try {
                checkAndReplace((int) rectangle.getX(), (int) rectangle.getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public static void checkAndReplace(int x, int y) throws InterruptedException {
        Thread thread = new Thread(() -> {
            sw = 0;
            if (Main.round % 2 == 0) {
                gamePlay[x][y] = '-';
            } else {
                gamePlay[x][y] = '|';
            }


            if (x % 2 == 0) {
                if (x == 0) {
                    nextColumnCheck(x, y);
                } else if (x == Setting.rows * 2) {
                    preColumnCheck(x, y);
                } else {
                    nextColumnCheck(x, y);
                    preColumnCheck(x, y);
                }
            } else {
                if (y == 0) {
                    nextRowCheck(x, y);
                } else if (y == Setting.columns * 2) {
                    preRowCheck(x, y);
                } else {
                    nextRowCheck(x, y);
                    preRowCheck(x, y);
                }
            }
            if (sw == 1) {
                Main.round--;
            }
        });

        thread.start();
        thread.join();

        mainGamePage.refreshGamePlay();
        endGame();
        Main.round++;
    }


    private static void preRowCheck(int x, int y) {
        if (gamePlay[x][y - 2] != ' ') {
            if (gamePlay[x - 1][y - 1] != ' ') {
                if (gamePlay[x + 1][y - 1] != ' ') {
                    if (Main.round % 2 == 0) {
                        gamePlay[x][y - 1] = '0';
                        Main.player1.setPoint();
                    } else {
                        gamePlay[x][y - 1] = '1';
                        Main.player2.setPoint();
                    }
                    sw = 1;
                }
            }
        }
    }

    private static void nextRowCheck(int x, int y) {
        if (gamePlay[x][y + 2] != ' ') {
            if (gamePlay[x + 1][y + 1] != ' ') {
                if (gamePlay[x - 1][y + 1] != ' ') {
                    if (Main.round % 2 == 0) {
                        gamePlay[x][y + 1] = '0';
                        Main.player1.setPoint();
                    } else {
                        gamePlay[x][y + 1] = '1';
                        Main.player2.setPoint();
                    }
                    sw = 1;
                }
            }
        }
    }

    private static void nextColumnCheck(int x, int y) {
        if (gamePlay[x + 2][y] != ' ') {
            if (gamePlay[x + 1][y + 1] != ' ') {
                if (gamePlay[x + 1][y - 1] != ' ') {
                    if (Main.round % 2 == 0) {
                        gamePlay[x + 1][y] = '0';
                        Main.player1.setPoint();
                    } else {
                        gamePlay[x + 1][y] = '1';
                        Main.player2.setPoint();
                    }
                    sw = 1;
                }
            }
        }
    }

    private static void preColumnCheck(int x, int y) {
        if (gamePlay[x - 2][y] != ' ') {
            if (gamePlay[x - 1][y + 1] != ' ') {
                if (gamePlay[x - 1][y - 1] != ' ') {
                    if (Main.round % 2 == 0) {
                        gamePlay[x - 1][y] = '0';
                        Main.player1.setPoint();
                    } else {
                        gamePlay[x - 1][y] = '1';
                        Main.player2.setPoint();
                    }
                    sw = 1;
                }
            }
        }
    }


    public static void endGame() {
        for (int i = 0; i < Setting.rows * 2 + 1; i++) {
            for (int j = 0; j < Setting.columns * 2 + 1; j++) {
                if (gamePlay[i][j] == ' ') return;
            }
        }
        mainGamePage.EndGame();
    }
}