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
            rasterPanelUtility.keyboardListener('F');
            System.out.println("F key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_W) {
            rasterPanelUtility.keyboardListener('W');
            System.out.println("W key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_E) {
            rasterPanelUtility.keyboardListener('E');
            System.out.println("E key pressed");


        }else if (e.getKeyCode() == KeyEvent.VK_G) {
            rasterPanelUtility.keyboardListener('G');
            System.out.println("G key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_H) {
            rasterPanelUtility.keyboardListener('H');
            System.out.println("H key pressed");


        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            rasterPanelUtility.keyboardListener('S');
            System.out.println("S key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_D) {
            rasterPanelUtility.keyboardListener('D');
            System.out.println("D key pressed");

        }else if (e.getKeyCode() == KeyEvent.VK_L) {
            rasterPanelUtility.keyboardListener('L');
            System.out.println("L key pressed");
        }

        else if (e.getKeyCode() == KeyEvent.VK_K) {
            rasterPanelUtility.keyboardListener('K');
            System.out.println("K key pressed");
        }
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            rasterPanelUtility.keyboardListener('P');
            System.out.println("K key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_1) {
            rasterPanelUtility.keyboardListener('1');
            System.out.println("1 key pressed");
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            rasterPanelUtility.keyboardListener('A');
            System.out.println("A key pressed");
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            rasterPanelUtility.keyReleased(e.getKeyCode());
        }
    }





    public void setRasterPanelUtility(RasterPanelUtility rasterPanelUtility){
        this.rasterPanelUtility = rasterPanelUtility;
    }


}
