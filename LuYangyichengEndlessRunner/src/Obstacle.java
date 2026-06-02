/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Image;
import java.awt.Toolkit; 
/**
 *
 * @author 350049094
 */
public class Obstacle extends GameObject {
    private static int obstacleNum = 0;
    private boolean inObstacle;
    private final static Image [] spikes = {Toolkit.getDefaultToolkit().getImage("GeoSpike1.png"), Toolkit.getDefaultToolkit().getImage("GeoSpike2.png"),
    Toolkit.getDefaultToolkit().getImage("GeoSpike3.png"), Toolkit.getDefaultToolkit().getImage("GeoSpike4.png")};
    
    // Constructor method
    Obstacle () {
        super();
        x = (int) (Math.random() * 1600) + 1600;
        y = 590;
        height = 60;
        inObstacle = false;
        obstacleNum++;
        // image
    }
   
   /** Resets obstacle to a random position off-screen
    */
    @Override
    public void reset() {
       x = (int) (Math.random() * 1600) + 1600;
    }
    
    /** Finds the length of the spike based on the image used; can be 1, 2, 3, or 4 spikes
     * @return length of the spike image
     */
    private int getSpikeLength() {
        int imageIndex = -1;
        for (int i = 0; i < spikes.length; i++) {
            if (image.equals(spikes[i])) {
                imageIndex = i;
            }
        }
        
        switch (imageIndex) {
            case 0:
                return 55;
            case 1:
                return 105;
            case 2:
                return 153;
            case 3:
                return 203;
            default:
                return 0;
        }
    }
    
    /** Sets the image based on number of spikes requested (1, 2, 3, or 4)
     * @param index The number of spikes requested
     */
    public void setGameImage(int index) {
        image = spikes[index];
        width = this.getSpikeLength();
    }
    
    /** Checks whether the player is colliding with the obstacle.
     * @return true or false based on state of the collision
     */
    public boolean getObstacle() {
        return inObstacle;
    }
    
    /** Chance the sate of the collision
     * @param newObstacle True or false to chance the inObstacle field of the class
     */
    public void setObstacle(boolean newObstacle) {
        inObstacle = newObstacle;
    }
}

