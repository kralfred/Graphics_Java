package panel;

import handler.KeyInputHandler;
import handler.MouseHandler;
import handler.PointOrientationHandler;
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
    private DevInfoPanel devInfoPanel = new DevInfoPanel();
    private PointOrientationHandler pointOrientationHandler;

    public MainPanel(int width, int height){


        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        this.keyInputHandler = new KeyInputHandler();
        this.lineUtility = new LineUtility();
        this.rasterPanelUtility = new RasterPanelUtility(devInfoPanel);
        this.rasterPanel = new RasterPanel(devInfoPanel);
        this.statPanel = new StatPanel();
        this.mouseHandler = new MouseHandler();
        this.polygonUtility = new PolygonUtility();
        this.pointOrientationHandler = new PointOrientationHandler();
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
        rasterPanelUtility.setPointOrientationHandler(pointOrientationHandler);

        rasterPanelUtility.setLineUtility(lineUtility);
        rasterPanelUtility.setPolygonUtility(polygonUtility);



        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(width,height);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.addKeyListener(keyInputHandler);

        mainFrame.add(statPanel);
        mainFrame.add(devInfoPanel, BorderLayout.EAST);
        mainFrame.add(rasterPanel, BorderLayout.CENTER);



    }

}
