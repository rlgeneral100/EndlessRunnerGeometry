import java.awt.Toolkit;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 350049094
 */
public class Platform extends GameObject {
    // Constructor method
    Platform() {
        super();
        x = (int) (Math.random() * 1600) + 1600;
        y = (int) (Math.random() * 400) + 100;
        width = 140;
        height = 24;
        image = Toolkit.getDefaultToolkit().getImage("platform.png");
    }
    
    /** Sets platform to a random location
     */
    @Override
    public void reset() {
        x = (int) (Math.random() * 1600) + 1600;
        y = (int) (Math.random() * 400) + 100;
    }
    
    /** Set the platform as ground and place player on it upon collision with platform
     */
    public void setPlatform() {
        ((Player) GameManager.gameObjects[2]).setGround(y - 45);
        GameManager.gameObjects[2].setY(y - 45);
    }
    
    /** Overrides the original collision method for a smoother collision with platform
     * @return True or false based on state of collision
     */
    @Override
    public boolean collision() {
        if (image != null && x + width > GameManager.gameObjects[2].getX() && x < GameManager.gameObjects[2].getX() + 50 && y + 5 > GameManager.gameObjects[2].getY() && y < GameManager.gameObjects[2].getY() + 50) {
            return true;
        }
        return false;
    }
}
