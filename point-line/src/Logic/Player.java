package Logic;


import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int point;
    public Player (String name,Color color){
        this.name = name;
        this.color = color;
        this.point = 0;
    }

    public String getName() {
        return name;
    }


    public Color getColor() {
        return color;
    }


    public String getPoint() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(point);

        return stringBuilder.toString();
    }

    public int getIntPoint(){
        return point;
    }

    public void setPoint() {
        this.point++;
    }
}
