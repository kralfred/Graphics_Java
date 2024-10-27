package handler;

import utility.LineUtility;
import utility.RasterPanelUtility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputHandler extends KeyAdapter {


    private RasterPanelUtility rasterPanelUtility;



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            rasterPanelUtility.keyPressed(e.getKeyCode());
        }else if (e.getKeyCode() == KeyEvent.VK_C) {
            rasterPanelUtility.clear();
            System.out.println("C key pressed");
        }

        else if (e.getKeyCode() == KeyEvent.VK_F) {
            rasterPanelUtility.setKey();
            System.out.println("F key pressed");


        }else if (e.getKeyCode() == KeyEvent.VK_W) {
            rasterPanelUtility.setLineThickness('W');
            System.out.println("W key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_E) {
            rasterPanelUtility.setLineThickness('E');
            System.out.println("E key pressed");


        }else if (e.getKeyCode() == KeyEvent.VK_G) {
            rasterPanelUtility.setLineThickness('G');
            System.out.println("G key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_H) {
            rasterPanelUtility.setLineThickness('H');
            System.out.println("H key pressed");


        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            rasterPanelUtility.setLineThickness('S');
            System.out.println("S key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_D) {
            rasterPanelUtility.setLineThickness('D');
            System.out.println("D key pressed");

        }else if (e.getKeyCode() == KeyEvent.VK_L) {
            rasterPanelUtility.setLineThickness('L');
            System.out.println("L key pressed");
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            rasterPanelUtility.keyReleased(e.getKeyCode());
        }else if (e.getKeyCode() == KeyEvent.VK_F) {
            rasterPanelUtility.fKeyReleased();
            System.out.println("F key pressed");
        }
    }





    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility){
        this.rasterPanelUtility = rasterPanelUtility;
    }


}
