package utility;

import object.Line;
import object.Point;
import panel.RasterPanel;

import java.awt.*;
import java.util.ArrayList;

public class PolygonUtility {

    private RasterPanelUtility rasterPanelUtility;
    private RasterPanel rasterPanel;
    private int radius = 5;
    private int sides = 5;


    public PolygonUtility() {

    }

    //pouzil jsem Bresenhamuv algoritmus, rychly a jednoduchy pro praci na vyzkouseni. Funguje ale pouze s gridem takze na vetsi presnost by se nehodil


    public ArrayList<Point> calculatePolygonVertices(int centerX, int centerY,int radius,int  sides) {
        ArrayList<Point> vertices = new ArrayList<>(); // Use ArrayList to store vertices

        double angleStep = 2 * Math.PI / sides;

        for (int i = 0; i < sides; i++) {
            double angle = i * angleStep;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            vertices.add(new Point(x, y));
        }
        return vertices;
    }

    public int getRadius() {
        return radius;
    }

    public int getSides() {
        return sides;
    }

    public void setRadius(int radius){
        if(radius > 2){
            this.radius = radius;
        }else{
            System.out.println("Radius must be greater than 2");
        }
    }
    public void setSides(int sides){
        if(sides > 2){
            this.sides = sides;
        }else{
            System.out.println("Polygon must have at least 3 sides");
        }
    }

    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility){
        this.rasterPanelUtility = rasterPanelUtility;
    }
    public void setRasterPanel(RasterPanel rasterPanel){
        this.rasterPanel = rasterPanel;
    }



}
