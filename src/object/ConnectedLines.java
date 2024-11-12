package object;

import utility.LineUtility;

import java.util.ArrayList;
import java.util.LinkedList;

public class ConnectedLines {

    ArrayList<Line> lines;
    ArrayList<ConnectedPoint> connectedPoints;
    private ArrayList<ConnectedLine> connectedInnerLines;
    private LineUtility lineUtility;

  public ConnectedLines(ArrayList<Line> lines, ArrayList<ConnectedPoint> connectedPoints) {
      this.lines = lines;
      this.connectedPoints = connectedPoints;
      this.lineUtility = lineUtility;
  }


  public void addLine(ConnectedPoint connectedPoint, Line line) {
      lines.add(line);
      this.connectedPoints.add(connectedPoint);
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

    public void split(Line line, Point point){

      ArrayList<Line> newList = new ArrayList<>();
       ArrayList<Point> checkPoints = line.getPoints();
       ArrayList<Point> firstSection = new ArrayList<>();
        LinkedList<Point> secondSection = new LinkedList<>();
      ArrayList<Line> newConnectedPointLines = new ArrayList<>();
        ArrayList<ConnectedPoint> connectedPointsCheck = new ArrayList<>();
      for(ConnectedPoint connectedPoint: connectedPoints){

          if(connectedPoint.getConnectedLines().contains(line)){

              connectedPointsCheck.add(connectedPoint);

          }
      }


          if(connectedPointsCheck.size() == 1){

              connectedPointsCheck.get(0).removeConnectedLine(line);

              if(checkPoints.get(0).equals(connectedPointsCheck.get(0))){


                  for(int i = 0; i < checkPoints.size(); i++){
                      firstSection.add(checkPoints.get(i));


                      if(checkPoints.get(i).equals(point)){

                          Line firstLine = new Line(firstSection);
                          newConnectedPointLines.add(firstLine);
                          ConnectedPoint secondConnectedPoint = new ConnectedPoint(newConnectedPointLines,point);
                          connectedPointsCheck.get(0).addConnectedLine(firstLine);

                          firstSection.clear();
                          firstSection.add(checkPoints.get(i));

                      }
                  }


              }



          }
          else{

          }


    }




  public void createPolygon(Line line, Point firstPoint, Point secondPoint){

  }

}

