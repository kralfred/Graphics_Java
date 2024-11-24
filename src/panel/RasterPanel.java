package panel;

import handler.KeyInputHandler;
import handler.MouseHandler;
import object.Line;
import object.Point;
import utility.LineUtility;
import utility.RasterPanelUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.Raster;

public class RasterPanel extends JPanel {

    private RasterPanelUtility rasterPanelUtility;
    private MouseHandler mouseHandler;
    private KeyInputHandler keyInputHandler;
    private StatPanel statPanel;
    private DevInfoPanel devInfoPanel;


   public RasterPanel(DevInfoPanel devInfoPanel) {
       this.devInfoPanel = devInfoPanel;
   }

    public void setKeyInputHandler(KeyInputHandler keyInputHandler) {
        this.keyInputHandler = keyInputHandler;
    }

    public void setStatPanel(StatPanel statPanel){
        this.statPanel = statPanel;
    }

    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility) {
        this.rasterPanelUtility = rasterPanelUtility;
    }


    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
        addMouseListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rasterPanelUtility.paintPanel((Graphics2D) g);
    }

}
