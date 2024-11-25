package utility;

import handler.KeyInputHandler;
import handler.MouseHandler;
import handler.PointOrientationHandler;
import object.*;
import object.Point;
import object.Polygon;
import panel.DevInfoPanel;
import panel.RasterPanel;
import panel.StatPanel;


import java.awt.*;
import java.util.*;


public class RasterPanelUtility {


    private RasterPanel rasterPanel;
    private KeyInputHandler keyInputHandler;
    int[][] gridSize;
    int pixelSize;
    int width;
    int height;
    private StatPanel statPanel;
    private MouseHandler mouseHandler;
    private LineUtility lineUtility;
    private Boolean shiftPressed = Boolean.FALSE;
    private ArrayList<Line> createdLines = new ArrayList<>();
    private ArrayList<Point> tempPoints = new ArrayList<>();
    private Line temporaryLine;
    private ArrayList<Point> tempEndPoints = new ArrayList<>();
    private PolygonUtility polygonUtility;
    private Boolean fPressed = Boolean.FALSE;
    private Polygon polygon;
    private ArrayList<Point> tempCrossedPoints = new ArrayList<>();
    private int radius;
    private int sides;
    private LinkedList<Point> crossedPoints = new LinkedList<>();
    private ConnectedLines connectedLines = null;
    private ArrayList<ConnectedLines> connectedLinesList = new ArrayList<>();
    private ArrayList<ConnectedPoint> connectedPointsList = new ArrayList<>();
    private LinkedList<Polygon> polygonsList = new LinkedList<>();
    private DevInfoPanel devInfoPanel;
    private Point mousePosition;
    private PointOrientationHandler pointOrientationHandler;


    public RasterPanelUtility(DevInfoPanel devInfoPanel) {

        this.devInfoPanel = devInfoPanel;
        this.width = 800;
        this.height = 600;
        this.gridSize = new int[width][height];
        this.pixelSize = 9;
    }

        public void paintPanel(Graphics2D g2d) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = gridSize[x][y];
                    g2d.setColor(new Color(color));
                    g2d.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
            }
        }


    public Point findIntersection(Point p1, Point p2, Point p3, Point p4) {
        double A1 = p2.getY() - p1.getY();
        double B1 = p1.getX() - p2.getX();
        double C1 = A1 * p1.getX() + B1 * p1.getY();


        double A2 = p4.getY() - p3.getY();
        double B2 = p3.getX() - p4.getX();
        double C2 = A2 * p3.getX() + B2 * p3.getY();


        double determinant = A1 * B2 - A2 * B1;

        if (determinant == 0) {
            return null;
        } else {
            double x = (B2 * C1 - B1 * C2) / determinant;
            double y = (A1 * C2 - A2 * C1) / determinant;


            return new Point((int) Math.round(x), (int) Math.round(y));

        }
    }


    public boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }

    public int orientation(Point p, Point q, Point r) {
        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    public boolean crossLinesCheck(Line l1, Line l2) {
        Point l1Start = l1.getPoints().get(0);
        Point l1End = l1.getPoints().get(l1.getPoints().size() - 1);
        Point l2Start = l2.getPoints().get(0);
        Point l2End = l2.getPoints().get(l2.getPoints().size() - 1);

        int o1 = orientation(l1Start, l1End, l2Start);
        int o2 = orientation(l1Start, l1End, l2End);
        int o3 = orientation(l2Start, l2End, l1Start);
        int o4 = orientation(l2Start, l2End, l1End);


        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == 0 && onSegment(l1Start, l2Start, l1End)) {
            System.out.println("Special case: l2Start is on segment l1");
            return true;
        }
        if (o2 == 0 && onSegment(l1Start, l2End, l1End)) {
            System.out.println("Special case: l2End is on segment l1");
            return true;
        }
        if (o3 == 0 && onSegment(l2Start, l1Start, l2End)) {
            System.out.println("Special case: l1Start is on segment l2");
            return true;
        }
        if (o4 == 0 && onSegment(l2Start, l1End, l2End)) {
            System.out.println("Special case: l1End is on segment l2");
            return true;
        }


        return false;
    }



    public void connectConnectedLines(ConnectedLines lines1, ConnectedLines lines2) {
        for(Line line: lines1.getLines()){
            lines2.addLine(line);
        }
        for(ConnectedPoint connectedPoint : lines1.getPoints()){
            lines2.addConnectedPoint(connectedPoint);
        }

    }


    public boolean checkForPoint(ConnectedLines connectedLines, Point p){
        for(ConnectedPoint connectedPoint : connectedLines.getPoints()){
            if(connectedPoint.getCurrentPoint().equals(p)){
                return true;
            }
        }
        return false;
    }


    public void removeLineWithPoint(ConnectedPoint connectedPoint, Point p){
        connectedPoint.getConnectedLines().removeIf(line -> line.getPoints().contains(p));
    }





      public void split(Line line,ConnectedLines connectedLines, Point splitPoint){


        ArrayList<Point> section = new ArrayList<>();
        ArrayList<Line> splitLines = new ArrayList<>();

        ConnectedPoint splitConnectedPoint = null;

          Point lastPoint = line.getPoints().get(line.getPoints().size() - 1);
          System.out.println("connected Points size" + connectedLines.getLines().size());
        Point previousPoint = null;
        Line sectionLine = null;

        if(!line.getPoints().contains(splitPoint)){
            System.out.println("Special case: splitPoint is not on line");
        }else{

        for(Point point : line.getPoints()){

            if(checkForPoint(connectedLines,point) && previousPoint != splitPoint){

                System.out.println("cheking point");
                      previousPoint = point;
                      section.clear();


            }else if(point.equals(splitPoint) && previousPoint == null){


                previousPoint = splitPoint;
                section.clear();
            }
            else if(point.equals(splitPoint) && previousPoint != null){
                for(ConnectedPoint connectedPoint : connectedLines.getPoints()){
                    if(connectedPoint.getCurrentPoint().equals(previousPoint)){
                        System.out.println("previous Point");

                        removeLineWithPoint(connectedPoint, point);
                        sectionLine = new Line(section);
                        connectedPoint.addConnectedLine(sectionLine);
                        for(Point p : sectionLine.getPoints()){
                            setPixel(p, Color.GRAY.getRGB());
                        }

                        splitLines.add(sectionLine);
                        splitConnectedPoint = new ConnectedPoint(splitLines, splitPoint);
                        connectedLines.addConnectedPoint(splitConnectedPoint);

                    }
                }
                previousPoint = splitPoint;
                section.clear();

            }else if(checkForPoint(connectedLines,point) && previousPoint == splitPoint){
                System.out.println("previous Point is split Point");
                for(ConnectedPoint connectedPoint : connectedLines.getPoints()){
                    if(connectedPoint.getCurrentPoint().equals(point)){
                        removeLineWithPoint(connectedPoint, splitPoint);
                        sectionLine = new Line(section);
                        if(splitConnectedPoint == null){
                            splitLines.add(sectionLine);
                            splitConnectedPoint = new ConnectedPoint(splitLines, splitPoint);
                        }else{
                            splitConnectedPoint.addConnectedLine(sectionLine);
                        }

                        connectedLines.addConnectedPoint(splitConnectedPoint);
                        connectedPoint.addConnectedLine(sectionLine);
                        for(Point p : sectionLine.getPoints()){
                            setPixel(p, Color.GRAY.getRGB());
                        }

                    }
                    previousPoint = null;
                    break;

                }
            }else if(point.equals(lastPoint) && previousPoint == null){


            }

            else{
                section.add(point);
            }
        }
        if(splitConnectedPoint != null){
            connectedLines.addConnectedPoint(splitConnectedPoint);

        }

      }

      }



       public void checkForConnectionSection(Line drawnLine){

        ConnectedLines prev = null;

           for (Line checkedLine : createdLines) {

               if (crossLinesCheck(drawnLine, checkedLine)) {

                   Point inter = findIntersection(drawnLine.getPoints().get(0),drawnLine.getPoints().get(drawnLine.getPoints().size() - 1),
                           checkedLine.getPoints().get(0), checkedLine.getPoints().get(checkedLine.getPoints().size() - 1));

                   setPixel(inter, Color.YELLOW.getRGB());

                   boolean addNewConnectedLine = true;

                   if(connectedLinesList.isEmpty()) {
                       if(prev == null){
                           ArrayList<Line> connectedLines = new ArrayList<>();
                           connectedLines.add(drawnLine);
                           connectedLines.add(checkedLine);
                           ArrayList<ConnectedPoint> currentConnectedPoints = new ArrayList<>();
                           ConnectedPoint connectionPoint = new ConnectedPoint(connectedLines, findIntersection(drawnLine.getPoints().get(0),drawnLine.getPoints().get(drawnLine.getPoints().size() - 1),
                                   checkedLine.getPoints().get(0), checkedLine.getPoints().get(checkedLine.getPoints().size() - 1)));
                           currentConnectedPoints.add(connectionPoint);
                           connectedPointsList.add(connectionPoint);
                           prev = new ConnectedLines(connectedLines, currentConnectedPoints);

                       }else{
                           prev.addLine(checkedLine);
                       }

                   }else{

                       ConnectedLines merge = null;
                       for(ConnectedLines connectedLine : connectedLinesList){
                           if(connectedLine.containsLine(checkedLine) && connectedLine.containsLine(drawnLine)){
                               addNewConnectedLine = false;
                               System.out.println("new polygon");

                           }else if(connectedLine.containsLine(checkedLine) && !connectedLine.containsLine(drawnLine)){
                               addNewConnectedLine = false;

                               if(prev == null){

                                   split(checkedLine,connectedLine,inter);
                                   connectedLine.addLine(drawnLine);
                                   prev = connectedLine;

                               }else {

                                   merge = connectedLine;

                                   System.out.println("checked line");

                               }
                           }
                       }



                       if(merge!=null){
                           connectedLinesList.remove(prev);
                           connectConnectedLines(merge, prev);
                           connectedLinesList.remove(merge);
                           connectedLinesList.add(prev);
                           split(drawnLine,prev,inter);
                           System.out.println("2 connected lines merged");
                       }

                       if(addNewConnectedLine){
                           ArrayList<Line> connectedLines = new ArrayList<>();
                           connectedLines.add(drawnLine);
                           connectedLines.add(checkedLine);
                           ArrayList<ConnectedPoint> currentConnectedPoints = new ArrayList<>();


                           ConnectedPoint connectionPoint = new ConnectedPoint(connectedLines, inter);
                           currentConnectedPoints.add(connectionPoint);

                           prev = new ConnectedLines(connectedLines, currentConnectedPoints);
                           connectedLinesList.add(prev);
                           System.out.println("new connected line different");

                       }
                   }
                   System.out.println("Intersection detected!");
               }
           }

           if(prev != null && connectedLinesList.isEmpty()){

               connectedLinesList = new ArrayList<>();
               connectedLinesList.add(prev);
               System.out.println("new connected line added  " + connectedLinesList.size());
           }

           updateDevInfoPanel();


       }



        public int getPixelSize(){
        return pixelSize;
        }

        public void setPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
        mouseHandler.setPixelSize(pixelSize);
            statPanel.updatePixelSize(pixelSize);
            statPanel.repaint();

        }
        public void updateMousePosition(int x, int y) {
          statPanel.updateMousePosition(x, y);
          mousePosition = new Point(x, y);

        }
    public int getPixelColor(int x, int y) {

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return -1;
        }
        return gridSize[y][x];
    }

    public void setPixelColor(int x, int y, int color) {

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        gridSize[y][x] = color;
        repaintPixel(x, y, color);
    }
    public void repaintPixel(int x, int y, int newColor) {

        gridSize[x][y] = newColor;


        rasterPanel.repaint();
    }




    public void seedFill(Point p, Polygon polygon, int targetColor, int fillColor) {

        if (pointOrientationHandler.isPointInsidePolygon(p, polygon.getPoints())) {
            System.out.println("polygon inside");




            int currentColor = getPixelColor(p.getX(), p.getY());


            if (polygon.getPoints().contains(p)) {
                return;
            }


            setPixel(p, fillColor);


            seedFill(new Point(p.getX() + 1, p.getY()), polygon, targetColor, fillColor);
            seedFill(new Point(p.getX() - 1, p.getY()), polygon, targetColor, fillColor);
            seedFill(new Point(p.getX(), p.getY() + 1), polygon, targetColor, fillColor);
            seedFill(new Point(p.getX(), p.getY() - 1), polygon, targetColor, fillColor);
        }
    }





    private boolean isValidPoint(Point p) {
        return p.getX() >= 0 && p.getX() < width && p.getY() >= 0 && p.getY() < height;
    }

    private void addPointIfValid(Stack<Point> stack, Point p, int targetColor, int fillColor) {
        if (isValidPoint(p) && gridSize[p.getX()][p.getY()] == targetColor) {
            stack.push(p);
        }
    }


    public void setPixel(Point point, int color) {
        if (point.getX() >= 0 && point.getX() < width && point.getY() >= 0 && point.getY() < height) {
            int x = point.getX();
            int y = point.getY();


            gridSize[x][y] = color;
            rasterPanel.repaint(x * pixelSize, y * pixelSize, pixelSize, pixelSize);


            statPanel.repaint();
        }
    }


        public void createLine(Point startPoint, Point endPoint){


        if(shiftPressed){
            Line line = shiftLine(startPoint, endPoint);
            drawLine(line, Color.RED.getRGB());
            createdLines.add(line);

        }else{
            Line line = lineUtility.createLine(startPoint, endPoint);
            drawLine(line, Color.blue.getRGB());
            checkForConnectionSection(line);
            createdLines.add(line);

        }


            System.out.println(createdLines.size());
        }


    public void drawTemporaryLine(Point startPoint, Point endPoint) {

        if (shiftPressed) {
            temporaryLine = shiftLine(startPoint, endPoint);
        }
        else{
            temporaryLine = lineUtility.createLine(startPoint,endPoint);
        }


        for(Line line : createdLines){
              for(Point p1 : temporaryLine.getPoints()) {

                  if(line.getPoints().contains(p1)){
                      tempCrossedPoints.add(p1);

                  }else{
                      tempPoints.add(p1);
                  }
            }
        }

        Iterator<Point> iterator = tempPoints.iterator();
        while (iterator.hasNext()) {
            Point p1 = iterator.next();
            if (temporaryLine.getPoints().contains(p1)) {
                setPixel(p1, Color.red.getRGB());

            }else{
                if(tempCrossedPoints.contains(p1)){
                    setPixel(p1, Color.blue.getRGB());

                }
                else{
                    setPixel(p1, Color.black.getRGB());

                }
                iterator.remove();

            }
        }

    }



   public void clearTempPoints(){
         tempPoints.clear();
         tempCrossedPoints.clear();
   }


    public void drawPolygon(Polygon polygon, int vertices){

        createLine(polygon.getPoints().get(vertices - 1), polygon.getPoints().get(0));

        for(int i = 0; i < vertices - 1; i++){
            createLine(polygon.getPoints().get(i), polygon.getPoints().get(i + 1));
        }
    }

    public void seedFill() {
        Point p = new Point(mousePosition.getX(), mousePosition.getY());


        if (pointOrientationHandler.isPointInsidePolygon(p, polygon.getPoints()) && polygon != null) {
            System.out.println("Point is inside");
            seedFill(p,polygon, Color.RED.getRGB(), Color.BLUE.getRGB());
        }
    }



    public void drawLine(Line newLine, int color) {

        for (Point point : newLine.getPoints()) {
            setPixel(point, color);
        }
        setPixel(newLine.getPoints().get(0), Color.red.getRGB());
        setPixel(newLine.getPoints().get(newLine.getPoints().size() - 1), Color.red.getRGB());

    }

    // KeyBoard action


    public void keyPressed(int keyCode){
        shiftPressed = true;
    }
    public void keyReleased(int keyCode){
        shiftPressed = false;
    }

    public Line shiftLine(Point startPoint, Point endPoint){
        int deltaX = endPoint.getX() - startPoint.getX();
        int deltaY = endPoint.getY() - startPoint.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            endPoint = new Point(endPoint.getX(), startPoint.getY());
        } else {
            endPoint = new Point(startPoint.getX(), endPoint.getY());
        }
        return lineUtility.createLine(startPoint, endPoint);
    }


    public void keyboardListener(char a){
        if(a == 'W'){
            lineUtility.setThickness(lineUtility.getThickness()+1);
            statPanel.updateLineThickness(lineUtility.getThickness());
        } else if (a == 'E') {
            lineUtility.setThickness(lineUtility.getThickness()-1);
            statPanel.updateLineThickness(lineUtility.getThickness());
        }else if (a == 'S') {
            polygonUtility.setRadius(polygonUtility.getRadius()+1);
            statPanel.updatePolygonRadius(polygonUtility.getRadius());
        }else if (a == 'D') {
            polygonUtility.setRadius(polygonUtility.getRadius()-1);
            statPanel.updatePolygonRadius(polygonUtility.getRadius());
        }else if (a == 'G') {
            polygonUtility.setSides(polygonUtility.getSides()+1);
            statPanel.updatePolygonSideCount(polygonUtility.getSides());
        }else if (a == 'H') {
            polygonUtility.setSides(polygonUtility.getSides()-1);
            statPanel.updatePolygonSideCount(polygonUtility.getSides());
        }else if (a == 'K') {
            if (devInfoPanel.isVisible()) {
                devInfoPanel.setVisible(false);
            } else {
                devInfoPanel.setVisible(true);
                updateDevInfoPanel();
            }
        }
        else if(a =='A'){
             seedFill();
            }
        else if(a =='F'){
            radius = polygonUtility.getRadius();
            sides = polygonUtility.getSides();
            polygon = new Polygon(polygonUtility.calculatePolygonVertices(mousePosition.getX(),mousePosition.getY(),radius,sides));
            drawPolygon(polygon, sides);
        }else if(a =='P'){
            paintConnectedLines();
        }else if(a =='1'){
            paintConnectedLines();
        }
    }


    public ArrayList<Line> getSharedLines() {
        Map<Line, Integer> lineOccurrences = new HashMap<>();
        ArrayList<Line> sharedLines = new ArrayList<>();

        for (ConnectedLines connectedLines : connectedLinesList) {
            for (ConnectedPoint connectedPoint : connectedLines.getPoints()) {
                for (Line line : connectedPoint.getConnectedLines()) {
                    lineOccurrences.put(line, lineOccurrences.getOrDefault(line, 0) + 1);
                }
            }
        }


        for (Map.Entry<Line, Integer> entry : lineOccurrences.entrySet()) {
            if (entry.getValue() >= 2) {
                sharedLines.add(entry.getKey());
            }
        }

        return sharedLines;
    }

    public void paintConnectedLines(){

        ArrayList<Line> sharedLines = getSharedLines();

        System.out.println(sharedLines.size());
        for(Line line : sharedLines){
            drawLine(line, Color.YELLOW.getRGB());
        }
        ArrayList<Line> lines = new ArrayList<>();
        for(ConnectedLines connectedLines : connectedLinesList){
            for(ConnectedPoint connectedPoint : connectedLines.getPoints()){
                for(Line line : connectedPoint.getConnectedLines()){
                    lines.add(line);
                }
            }
        }
        for(Line line : lines){
            for(int i = 0; i < line.getPoints().size(); i++){

            }
        }


    }




    public void updateDevInfoPanel(){

        if(devInfoPanel.isVisible()){

            int connectedPoints = 0;
            int connectedLines = 0;
            for(ConnectedLines connectedLines1 : connectedLinesList){
                connectedPoints += connectedLines1.getPoints().size();
                connectedLines += connectedLines1.getLines().size();
            }

            devInfoPanel.updateDevInfoPanel(connectedPoints,connectedLines, connectedLinesList.size(), polygonsList.size());
            devInfoPanel.repaint();
        }

    }

    public void clear(){
        tempPoints.clear();
        tempEndPoints.clear();
        createdLines.clear();
        connectedLinesList.clear();
        connectedPointsList.clear();
        mousePosition = null;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gridSize[x][y] = Color.black.getRGB();
            }
        }

        statPanel.repaint();
        rasterPanel.repaint();
    }






    public boolean isShiftPressed() {
        return shiftPressed;
    }
    public void setPolygonUtility(PolygonUtility polygonUtility) {
        this.polygonUtility = polygonUtility;
    }
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }
    public void setStatPanel(StatPanel statusPanel) {
        this.statPanel = statusPanel;
    }
    public void setRasterPanel(RasterPanel rasterPanel) {
        this.rasterPanel = rasterPanel;
    }
    public void setLineUtility(LineUtility lineUtility) {
        this.lineUtility = lineUtility;
    }
    public void setPointOrientationHandler(PointOrientationHandler pointOrientationHandler){
        this.pointOrientationHandler = pointOrientationHandler;
    }


    }




