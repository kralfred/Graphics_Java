package object;

import java.util.ArrayList;

public class ConnectedLines {

    ArrayList<Line> lines;
    ArrayList<ConnectedPoint> connectedPoints;
    private ArrayList<ConnectedLine> connectedInnerLines;

  public ConnectedLines(ArrayList<Line> lines, ArrayList<ConnectedPoint> connectedPoints) {
      this.lines = lines;
      this.connectedPoints = connectedPoints;
  }


  public void addLine(Line line, Point connectionPoint) {
      this.lines.add(line);
      this.connectedPoints.add(connectionPoint);
  }

    public boolean containsLine(Line line) {
        return lines.contains(line);
    }

    public int getPoints(){
      return connectedPoints.size();
    }
    public int getLines() {
      return lines.size();
    }

    public void setInnerLine(){
      this.connectedInnerLines = new ArrayList<>();
    }

  public void createPolygon(Line line, Point firstPoint, Point secondPoint){

  }

}

