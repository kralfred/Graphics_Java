package panel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;


public class DevInfoPanel extends JPanel {
    JLabel pixelCountLabel = new JLabel("Pixels: 0");
    JLabel lineCountLabel = new JLabel("Lines: 0");
    JLabel polygonCountLabel = new JLabel("Polygons: 0");
    JLabel overlappingPixelCountLabel = new JLabel("Overlapping Pixels: 0");
    JLabel connectedPointsLabel = new JLabel("Connected Points: 0");
    JLabel connectedLinesLabel = new JLabel("Connected Lines: 0");
    JLabel groupOfConnectedLinesLabel = new JLabel("Groups of Lines: 0");

    public DevInfoPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        add(createLabel(pixelCountLabel));
        add(createLabel(lineCountLabel));
        add(createLabel(polygonCountLabel));
        add(createLabel(overlappingPixelCountLabel));
        add(createLabel(connectedPointsLabel));
        add(createLabel(connectedLinesLabel));
        add(createLabel(groupOfConnectedLinesLabel));


        setVisible(false);
        setBackground(new Color(0));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    private JLabel createLabel(JLabel label) {
        label.setForeground(new Color(211, 211, 211));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(new Color(64, 64, 64));
        int cornerRadius = 20;
        int width = getWidth();
        int height = getHeight();
        g.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
    }

    public void updateDevInfoPanel(int connectedPoints, int connectedLines, int groupOfConnectedLines, int polygonCount) {
        connectedPointsLabel.setText("Connected Points: " + connectedPoints);
        connectedLinesLabel.setText("Connected Lines: " + connectedLines);
        groupOfConnectedLinesLabel.setText("Groups of Lines: " + groupOfConnectedLines);
        polygonCountLabel.setText("Polygons: " + polygonCount);
    }
}

