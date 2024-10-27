package object;

import java.util.ArrayList;

public class Line {

    private ArrayList<Point> points;
    private Point start;
    private Point end;

    public Line(ArrayList<Point> points, Point start, Point end) {

        this.points = points;
        this.start = start;
        this.end = end;
    }

    public Line(ArrayList<Point> points) {
        this(points, null, null);
    }

    public void addPoint(Point point) {
        points.add(point);
    }
    public ArrayList<Point> getPoints() {
        return points;
    }
    public Point getStart() {
        return start;
    }
    public Point getEnd() {
        return end;
    }




}
