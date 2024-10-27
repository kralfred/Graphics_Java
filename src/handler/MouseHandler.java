package handler;

import object.Line;
import object.Point;
import object.Polygon;
import panel.RasterPanel;
import panel.StatPanel;
import utility.LineUtility;
import utility.RasterPanelUtility;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Optional;

public class MouseHandler extends MouseAdapter implements MouseWheelListener {

    Line line;
    RasterPanel rasterPanel;
    RasterPanelUtility rasterPanelUtility;
    ArrayList<Point> points = new ArrayList<>();
    LineUtility lineUtility;
    private Point startPoint;
    private Point lineStartPoint;
    private Point endPoint;
    private StatPanel statPanel;
    private int pixelSize = 9;
    private Point currentPoint;
    private Point prevPoint = new Point(5, 5);



    public void setPixelSize(int pixelSize){
        this.pixelSize = pixelSize;
    }



    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            rasterPanelUtility.setPixelSize(rasterPanelUtility.getPixelSize() + 1);
        } else {

            rasterPanelUtility.setPixelSize(Math.max(1, rasterPanelUtility.getPixelSize() - 1));
        }

        rasterPanel.repaint();


    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("hih" + e.getX()/pixelSize + ":" + e.getY()/pixelSize);
        if(lineStartPoint == null){
            lineStartPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);

        }
        else{
            rasterPanelUtility.createLine(lineStartPoint,new Point(e.getX()/pixelSize, e.getY()/pixelSize));
            lineStartPoint = null;
        }

    }

   @Override
   public void mousePressed(MouseEvent e) {
       startPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);
       points.add(startPoint);
       rasterPanelUtility.setPixel(startPoint, Color.RED.getRGB());

   }

    @Override
    public void mouseDragged(MouseEvent e) {

        currentPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);

        if(!currentPoint.equals(prevPoint)){

            currentPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);
            System.out.println("current point:  " + currentPoint);
            rasterPanelUtility.drawTemporaryLine(startPoint ,currentPoint);

            prevPoint = currentPoint;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point endPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);
        rasterPanelUtility.createLine(startPoint, endPoint);
        rasterPanelUtility.clearTempPoints();
        startPoint = null;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        Point currentPoint = new Point(e.getX()/pixelSize, e.getY()/pixelSize);
        rasterPanelUtility.updateMousePosition(e.getX()/pixelSize, e.getY()/pixelSize);

    }



    public void setRasterPanel(RasterPanel rasterPanel){
        this.rasterPanel = rasterPanel;
    }
    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility){
        this.rasterPanelUtility = rasterPanelUtility;
    }
    public void setLineUtility(LineUtility lineUtility){
        this.lineUtility = lineUtility;
    }
    public void setStatPanel(StatPanel statPanel){
        this.statPanel = statPanel;
    }


}
