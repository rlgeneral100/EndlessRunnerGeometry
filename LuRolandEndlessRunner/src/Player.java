import java.awt.Toolkit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 350049094
 */
public class Player extends GameObject {
    private final static double jumpForce = 25;
    private double playerVel;
    private final static double velDecay = 2;
    private final static double originGround = 600;
    private double ground;
    private int lives;
    
    // Constructor method
    Player() {
        super();
        x = 100;
        y = 600;
        width = 50;
        height = 50;
        image = Toolkit.getDefaultToolkit().getImage("GeometryCube.png");
        playerVel = 0;
        ground = 600;
        lives = 3;
    }
    
    /** Puts the player back on ground
     */
    @Override
    public void reset(){
       y = (int) ground;
    }
    
    /** Updates the player's position
     */
    @Override
    public void update(){
        if (MainDrawingArea.isJumping || MainDrawingArea.isFalling) {
            y -= playerVel;
            playerVel -= velDecay;
            if (MainDrawingArea.jumpHigher && y > 100) {
                   playerVel += velDecay;
            } else if (y <= 100) {
                MainDrawingArea.jumpHigher = false;
            }
            
            if (y >= ground) {
                MainDrawingArea.isJumping = false;
                this.reset();
            }
        }
        
    }
    
    /** Sets player's y-velocity to jump velocity
     */
    public void setJumpVel() {
        playerVel = jumpForce;
    }
    
    /** Sets player's y-velocity to 0
     */
    public void setFallVel() {
        playerVel = 0;
    }
    
    /** Counteracts velocity decay to allow jumping higher if needed
     */
    public void jumpHigher() {
        playerVel += 0.1;
    }
    
    /** Resets the ground to original y-position instead of the platform
     */
    public void resetGround() {
        ground = originGround;
    }
    
    /** Finds the y-position of the ground to determine time of landing
     * @return the y-position of the ground
     */
    public double getGround() {
        return ground;
    }
    
    /** Chance the position of the ground
     * @param newGround New value of the ground
     */
    public void setGround(int newGround) {
        ground = newGround;
    }
    
    /** Prevents the player from colliding with things since things already collide with it.
     * @return false for no collision
     */
    @Override
    public boolean collision() {
        return false; // do nothing
    }
    
    /** Gets the private field lives for player class
     * @return Number of lives left
     */
    public int getLives() {
        return lives;
    }
    
    /** Removes lives upon collision with obstacles
     */
    public void loseLife() {
        lives--;
    }
}
