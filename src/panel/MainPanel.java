package panel;

import handler.KeyInputHandler;
import handler.MouseHandler;
import utility.LineUtility;
import utility.PolygonUtility;
import utility.RasterPanelUtility;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {


    private int width = 800, height = 800;
    private RasterPanel rasterPanel;
    private StatPanel statPanel;
    private MouseHandler mouseHandler;
    private RasterPanelUtility rasterPanelUtility;
    private LineUtility lineUtility;
    private KeyInputHandler keyInputHandler;
    private PolygonUtility polygonUtility;

    public MainPanel(int width, int height){


        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        this.keyInputHandler = new KeyInputHandler();
        this.lineUtility = new LineUtility();
        this.rasterPanelUtility = new RasterPanelUtility();
        this.rasterPanel = new RasterPanel();
        this.statPanel = new StatPanel();
        this.mouseHandler = new MouseHandler();
        this.polygonUtility = new PolygonUtility();

        polygonUtility.setRasterPanelUtility(rasterPanelUtility);
        polygonUtility.setRasterPanel(rasterPanel);

        lineUtility.setRasterPanelUtility(rasterPanelUtility);


        keyInputHandler.setRasterPanelUtility(rasterPanelUtility);

        mouseHandler.setRasterPanel(rasterPanel);
        mouseHandler.setRasterPanelUtility(rasterPanelUtility);
        mouseHandler.setLineUtility(lineUtility);
        mouseHandler.setStatPanel(statPanel);

        rasterPanel.setKeyInputHandler(keyInputHandler);
        rasterPanel.addKeyListener(keyInputHandler);
        rasterPanel.setMouseHandler(mouseHandler);
        rasterPanel.setRasterPanelUtility(rasterPanelUtility);
        rasterPanel.setFocusable(true);
        rasterPanel.requestFocusInWindow();
        rasterPanel.setStatPanel(statPanel);

        rasterPanelUtility.setDimensions(width, height);
        rasterPanelUtility.setMouseHandler(mouseHandler);
        rasterPanelUtility.setStatPanel(statPanel);
        rasterPanelUtility.setRasterPanel(rasterPanel);

        rasterPanelUtility.setLineUtility(lineUtility);
        rasterPanelUtility.setPolygonUtility(polygonUtility);



        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(width,height);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        mainFrame.addKeyListener(keyInputHandler);
        mainFrame.add(statPanel);
        mainFrame.add(rasterPanel);



    }

}
