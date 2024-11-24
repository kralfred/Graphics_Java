package utility;

import object.Line;
import object.Point;

import java.util.ArrayList;

public class LineUtility {

    private int thickness = 1;
    private RasterPanelUtility rasterPanelUtility;


    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility) {
        this.rasterPanelUtility = rasterPanelUtility;
    }
    public int getThickness() {
        return thickness;
    }

    public Line createLine(Point p1, Point p2) {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();

        ArrayList<Point> pointsArrayList = new ArrayList<>();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;
        int e2;


        pointsArrayList.add(new Point(x1, y1));
        addThickPixels(pointsArrayList, x1, y1, thickness);


        while (x1 != x2 || y1 != y2) {
            e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }


            pointsArrayList.add(new Point(x1, y1));
            addThickPixels(pointsArrayList, x1, y1, thickness);
        }



        return new Line(pointsArrayList);
    }


    private void addThickPixels(ArrayList<Point> pointsArrayList, int x, int y, int thickness) {
        for (int i = -thickness / 2; i <= thickness / 2; i++) {
            for (int j = -thickness / 2; j <= thickness / 2; j++) {
                if (!(i == 0 && j == 0)) {
                    pointsArrayList.add(new Point(x + i, y + j));
                }
            }
        }
    }

    public void setThickness(int thickness){
        if(thickness > 0 && thickness < 8){
            this.thickness = thickness;
        }else{
            System.out.println("Thickness should be between 0 and 8");
        }
    }



    }




