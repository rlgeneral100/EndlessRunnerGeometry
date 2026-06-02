import java.awt.Toolkit;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 350049094
 */
public class Collectible extends GameObject {
    static int collectibleNum = 0;
    // Constructor method
    Collectible() {
        super();
        x = (int) (Math.random() * 1600);
        y = (int) (Math.random() * 500);
        width = 60;
        height = 60;
        image = Toolkit.getDefaultToolkit().getImage("GeoCoin.png");
        collectibleNum++;
    }
    
    /** Resets collectible to a random position off-screen
     */
    @Override
    public void reset() {
        x = (int) (Math.random() * 1600) + 1600;
        y = (int) (Math.random() * 500);
    }
}
