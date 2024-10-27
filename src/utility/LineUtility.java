package utility;

import object.Line;
import object.Point;
import object.Vertex;
import panel.RasterPanel;

import java.awt.*;
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

        Vertex start = new Vertex(p1);
        Vertex end = new Vertex(p2);

        return new Line(pointsArrayList, start, end);
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



    public void drawLine(Point p1, Point p2, int color) {
        int x0 = p1.getX();
        int y0 = p1.getY();
        int x1 = p2.getX();
        int y1 = p2.getY();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        System.out.println("funguj");

        while (true) {
            rasterPanelUtility.setPixel(new Point(x0, y0), color);

            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }


    // Pokus o vlastni implementaci

//    public Line calculateLine(Point a, Point b, int pixelSize) {
//
//        ArrayList<Point> points = new ArrayList<>();
//
//        int x1 = a.getX() / pixelSize;
//        int y1 = a.getY() / pixelSize;
//
//        int x2 = b.getX() / pixelSize;
//        int y2 = b.getY() / pixelSize;
//
//        if (x1 > x2) {
//            int save = x2;
//            x2 = x1;
//            x1 = save;
//
//            int savey = y2;
//            y2 = y1;
//            y1 = savey;
//
//        }
//
//        int dy;
//        int dx = (x2 - x1);
//        if (y2 > y1) {
//            dy = (y2 - y1);
//        } else {
//            dy = (y1 - y2);
//        }
//
//
//        if (x1 == x2) {
//            for (int i = y1; i < y2; i++) {
//                points.add(new Point(x1 * pixelSize, i * pixelSize));
//            }
//        }
//
//        if (dx == dy) {
//            for (int i = 0; i < dy; i++) {
//                System.out.println("great");
//                Point p;
//                if (y1 > y2) {
//                    p = new Point((x1 + i) * pixelSize, (y1 - i) * pixelSize);
//                } else {
//                    p = new Point((x1 + i) * pixelSize, (y1 + i) * pixelSize);
//                }
//
//                points.add(p);
//            }
//        }
//
//        if (dy > dx) {
//            if (dx < 4 && dy > 0) {
//                dx++;
//            }
//
//            float k = (float) dy / dx;
//
//            float storedK = k;
//
//
//            while (y2 > y1 + 1) {
//                while (k > 2 && y1 > y2 + 1) {
//                    y1++;
//                    Point i = new Point(x1 * pixelSize, y1 * pixelSize);
//                    points.add(i);
//                    k--;
//                }
//                if (y2 > y1 + 1) {
//                    if (k < 1.5 && x1 < x2) {
//                        k--;
//                        x1++;
//                        y1++;
//                        Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(p);
//                        k += storedK;
//                    } else {
//                        k -= 1.5f;
//                        y1++;
//                        Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(p);
//                    }
//                }
//            }
//
//            while (y1 > y2 + 1) {
//
//                while (k > 2 && y1 > y2 + 1) {
//                    y1--;
//                    Point i = new Point(x1 * pixelSize, y1 * pixelSize);
//                    points.add(i);
//                    k--;
//                }
//
//                if (y1 > y2 + 1) {
//                    if (k < 1.5 && x1 < x2) {
//                        k--;
//                        x1++;
//                        y1--;
//                        Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(p);
//                        k += storedK;
//                    } else {
//                        k -= 1.5f;
//                        y1--;
//                        Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(p);
//                    }
//                }
//            }
//        }
//
//
//        if (dx > dy) {
//
//            if (dy < 4 && dy > 0) {
//                dy++;
//            }
//            float k = (float) dx / dy;
//
//            float storedK = k;
//
//            if (x2 < x1 - 1) {
//                while (x2 < x1 - 1) {
//                    while (k > 2 && x1 < x2 - 1) {
//                        x1++;
//                        Point i = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(i);
//                        k--;
//                    }
//
//                }
//            }
//
//
//                while (x1 < x2 - 1) {
//                    while (k > 2 && x1 < x2 - 1) {
//                        x1++;
//                        Point i = new Point(x1 * pixelSize, y1 * pixelSize);
//                        points.add(i);
//                        k--;
//                    }
//                    System.out.println("this is K  " + k);
//
//                    int descend = 1;
//                    if (y1 > y2) {
//                        descend = (-1);
//                    }
//                    if (x1 < x2 - 1) {
//                        if (k < 1.5 && (y1 < y2 || y1 > y2)) {
//                            k--;
//                            x1++;
//                            y1 += descend;
//                            Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                            points.add(p);
//                            k += storedK;
//                        } else {
//                            k -= 1.5f;
//                            x1++;
//                            Point p = new Point(x1 * pixelSize, y1 * pixelSize);
//                            points.add(p);
//                        }
//                    }
//                }
//
//                System.out.println("this is k  " + k);
//
//        }
//
//
//            Line result = new Line(points);
//
//            return result;
//}

    }




