package panel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class DevInfoPanel extends JPanel {
    JLabel pixelCountLabel = new JLabel("");
    JLabel lineCountLabel = new JLabel("Mouse Position:  x=0  y=0");
    JLabel polygonCountLabel = new JLabel("Line thickness:  1");
    JLabel overlappingPixelCountLabel = new JLabel("Polygon radius:  2");



    public DevInfoPanel() {
        setLayout(null);

        add(pixelCountLabel);
        add(lineCountLabel);
        add(polygonCountLabel);
        add(overlappingPixelCountLabel);

        pixelCountLabel.setForeground(new Color(211, 211, 211));
        lineCountLabel.setForeground(new Color(211, 211, 211));
        polygonCountLabel.setForeground(new Color(211, 211, 211));
        overlappingPixelCountLabel.setForeground(new Color(211, 211, 211));


        setVisible(false);
        setLayout(new FlowLayout());
        setBounds(15,15,150,120);
        setBackground(new Color(0));
        SoftBevelBorder border = new SoftBevelBorder(BevelBorder.RAISED);
        setBorder(border);

    }


    public void showDevInfoPanel(int pixelCount, int lineCount, int polygonCount, int overlappingPixelCount){

    }


}
