package object;

import java.util.ArrayList;

public class Line {

    private ArrayList<Point> points;


    public Line(ArrayList<Point> points) {

        this.points = points;

    }



    public void addPoint(Point point) {
        points.add(point);
    }
    public ArrayList<Point> getPoints() {
        return points;
    }





}
