package object;

import java.util.ArrayList;

public class ConnectedPoint {


    ArrayList<Line> connectedLines;
    Point currentPoint;

    public ConnectedPoint(ArrayList<Line> connectedLines, Point currentPoint) {
        this.currentPoint = currentPoint;
        this.connectedLines = connectedLines;
    }

    public void addNeighbour(Line line) {
       connectedLines.add(line);
    }

    public void setConnectedLines(){

    }
    public boolean containsLine(Line line){
        return connectedLines.contains(line);
    }

    public void addConnectedLine(Line line){
        connectedLines.add(line);
    }
    public void removeConnectedLine(Line line){
        connectedLines.remove(line);
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }
    public ArrayList<Line> getConnectedLines() {
        return connectedLines;
    }


}
