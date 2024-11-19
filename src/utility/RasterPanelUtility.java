package utility;

import handler.KeyInputHandler;
import handler.MouseHandler;
import object.*;
import object.Point;
import object.Polygon;
import panel.RasterPanel;
import panel.StatPanel;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


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



    public RasterPanelUtility() {

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

        // Otherwise, no intersection
        return false;
    }

       public void checkForConnectionSection(Line drawnLine){


           for (Line checkedLine : createdLines) {

               if (crossLinesCheck(drawnLine, checkedLine) && drawnLine != checkedLine) {
                   System.out.println("Intersection detected!");
               }
           }

       }



        public void checkForPolygons(ConnectedLines connectedLines, Point closingPoint){
            if(connectedLines.getLines() == connectedLines.getPoints()){


            }
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

          if(fPressed){
              radius = polygonUtility.getRadius();
              sides = polygonUtility.getSides();
              polygon = new Polygon(polygonUtility.calculatePolygonVertices(x,y,radius,sides));
              System.out.println("funguje");
              drawPolygon(polygon, sides);

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
            drawLine(line, Color.blue.getRGB());
            createdLines.add(line);

        }else{
            Line line = lineUtility.createLine(startPoint, endPoint);
            System.out.println("created lines = " +  createdLines.size());
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
                      System.out.println("contains");
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
        System.out.println("tempPoint  " + tempPoints.size());
        System.out.println("tempPoint crossed " + tempCrossedPoints.size());


    }



   public void clearTempPoints(){
         tempPoints.clear();
         tempCrossedPoints.clear();
   }


    public void drawPolygon(Polygon polygon, int vertices){

        createLine(polygon.getPoints().get(vertices - 1), polygon.getPoints().get(0));

        for(int i = 0; i < vertices - 1; i++){
            System.out.println("good" + i);
            createLine(polygon.getPoints().get(i), polygon.getPoints().get(i + 1));
        }
    }



    public void drawLine(Line newLine, int color) {

        for (Point point : newLine.getPoints()) {
            setPixel(point, color);
        }
        setPixel(newLine.getPoints().get(0), Color.red.getRGB());
        setPixel(newLine.getPoints().get(newLine.getPoints().size() - 1), Color.red.getRGB());

        statPanel.repaint();

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


    public void setLineThickness(char a){
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
        }

        else if (a == 'L') {
            polygonUtility.setSides(polygonUtility.getSides()-1);
            statPanel.updatePolygonSideCount(polygonUtility.getSides());
        }
    }

    public void clear(){
        tempPoints.clear();
        tempEndPoints.clear();
        createdLines.clear();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gridSize[x][y] = Color.black.getRGB();
            }
        }

        statPanel.repaint();
        rasterPanel.repaint();
    }



    public void fKeyReleased(){
        this.fPressed = false;
    }
    public void fKeyPressed(){
        this.fPressed = true;

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
    public void setKey(){
        fPressed = true;
    }

    }




