package panel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class StatPanel extends JPanel {


    JLabel pixelSizeLabel = new JLabel("Pixel Size: 9");
    JLabel mousePositionLabel = new JLabel("Mouse Position:  x=0  y=0");
    JLabel linePointCountLabel = new JLabel("Line thickness:  1");
    JLabel polygonRadiusLabel = new JLabel("Polygon radius:  2");
    JLabel polygonSideCountLabel = new JLabel("Polygon sides:  5");
    public StatPanel() {


        add(pixelSizeLabel);
        add(mousePositionLabel);
        add(linePointCountLabel);
        add(polygonRadiusLabel);
        add(polygonSideCountLabel);
        pixelSizeLabel.setForeground(new Color(211, 211, 211));
        mousePositionLabel.setForeground(new Color(211, 211, 211));
        linePointCountLabel.setForeground(new Color(211, 211, 211));
        polygonRadiusLabel.setForeground(new Color(211, 211, 211));
        polygonSideCountLabel.setForeground(new Color(211, 211, 211));
        setLayout(new FlowLayout());
        setBounds(15,15,150,120);
        setBackground(new Color(0));
        SoftBevelBorder border = new SoftBevelBorder(BevelBorder.RAISED);
        setBorder(border);

    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(64, 64, 64));

        int cornerRadius = 30;
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        g.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
    }


    public void updatePixelSize(int pixelSize){
        pixelSizeLabel.setText("Pixel Size: " + pixelSize);
    }
    public void updateLineThickness(int lineThickness){
     linePointCountLabel.setText("Thickness: " + lineThickness);
    }
    public void updateMousePosition(int x, int y){
        mousePositionLabel.setText("Mouse Position: " + x + ", " + y);
    }
    public void updatePolygonRadius(int r){
        polygonRadiusLabel.setText("Polygon radius: " + r);
    }
    public void updatePolygonSideCount(int x){
        polygonSideCountLabel.setText("Polygon sides: " + x);
    }

}
