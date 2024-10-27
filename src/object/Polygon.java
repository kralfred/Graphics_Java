package object;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<Point> points;

    public Polygon(ArrayList<Point> points){
  this.points = points;
    }


    public ArrayList<Point> getPoints() {
        return points;
    }

}
