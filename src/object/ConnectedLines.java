package object;

import utility.LineUtility;

import java.util.ArrayList;
import java.util.LinkedList;

public class ConnectedLines {

    ArrayList<Line> lines;
    ArrayList<ConnectedPoint> connectedPoints;
    private LineUtility lineUtility;

  public ConnectedLines(ArrayList<Line> lines, ArrayList<ConnectedPoint> connectedPoints) {
      this.lines = lines;
      this.connectedPoints = connectedPoints;
      this.lineUtility = lineUtility;
  }


  public void addLine(Line line) {
      lines.add(line);
  }
  public void addConnectedPoint(ConnectedPoint connectedPoint) {
      connectedPoints.add(connectedPoint);
  }

    public boolean containsLine(Line line) {
        return lines.contains(line);
    }

    public ArrayList<ConnectedPoint> getPoints(){
      return connectedPoints;
    }
    public ArrayList<Line> getLines() {
      return lines;
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

