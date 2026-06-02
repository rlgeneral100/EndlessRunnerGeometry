/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Image;
/**
 *
 * @author 350049094
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected final static int initSpeed = 15;
    protected static int speed = 15;
    
    /** Moves game objects down the screen
     */
    public void update() {
        x -= speed;
    }
    
    /** Resets game objects to original position or state
     */
    public abstract void reset();
    
    /** Finds the x-position of game objects
     * @return x-position of the game object
     */
    public int getX() {
        return x;
    }
    
    /** Finds the y-position of game objects
     * @return y-position of the game object
     */
    public int getY() {
        return y;
    }
    
    /** Finds the image representing the object
     * @return the image
     */
    public Image getGameImage() {
        return image;
    }
    
    /** Set game object to a new x-position
     * @param newX The new x-value
     */
    public void setX(int newX) {
        x = newX;
    }
    
    /** Set game object to a new y-position
     * @param newY The new y-value
     */
    public void setY(int newY) {
        y = newY;
    }
    
    /** Increase the moving speed by 1
     * @param increaseBy increase the speed of moving objects by the inputted number
     */
    public static void increaseSpeed(int increaseBy) {
        speed = initSpeed + increaseBy;
    }
    
    /** Gives the game object a new image
     * @param newImage The new image
     */
    public void setGameImage(Image newImage) {
        image = newImage;
    }
    
    /** Checks whether the player collided with other game objects
     * @return true or false on for the collision
     */
    public boolean collision() {
        return image != null && x + width > GameManager.gameObjects[2].getX() && x < GameManager.gameObjects[2].getX() + 50 && y + height > GameManager.gameObjects[2].getY() && y < GameManager.gameObjects[2].getY() + 50;
    }
}