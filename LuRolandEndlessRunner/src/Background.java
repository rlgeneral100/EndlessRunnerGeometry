import java.awt.Toolkit;
import java.awt.Image;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 350049094
 */
public class Background extends GameObject {
    // Constructor method
    Background() {
        super();
        x = 0;
        width = 1600;
        height = 900;
        image = Toolkit.getDefaultToolkit().getImage("GeoBackground.png");
    }
    
    /** Sets the background back to starting position
     */
    @Override
    public void reset() {
       // Puts background to original position 
       x = 0;
    }
    
    /** Prevents background from participating in collisions.
     * @return false because never collides
     */
    @Override
    public boolean collision() {
        return false; // do nothing
    }
}
