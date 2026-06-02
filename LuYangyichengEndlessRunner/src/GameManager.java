public final class GameManager {
    
    // Don't let anyone instatiate this class.
    private GameManager() {}
    
    // Fields
    static int points;
    static GameObject [] gameObjects = new GameObject[14];
    
    /** Clears all variables to start a new game.
     */
    static void setup() {
        points = 0;
        
        // 2 backgrounds
        gameObjects[0] = new Background();
        gameObjects[1] = new Background();
        
        // 1 player
        gameObjects[2] = new Player();
        
        // 1 collectible
        gameObjects[3] = new Collectible();

        // 6 obstacles        
        for(int i = 4; i < 10; i++) {
            gameObjects[i] = new Obstacle();
        }
        
        // 4 platforms
        for(int i = 10; i < gameObjects.length; i++) {
            gameObjects[i] = new Platform();
        }
    }
    
    /** Updates variables every frame
     */
    static void update() {
        points++;
        GameObject.increaseSpeed((int) points/500);
        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i].update();
        }
        // Array of game objects
        // For loop to go through game method
        int randomNum;
              // Manages all game obstacles
            for (int i = 4; i < 10; i++) {
                // Resets the obstacles if necessary
                if (GameManager.gameObjects[i].getX() < -1000) {
                    // A one-thid chance of regenerating obstacles if they go off-screen
                    randomNum = (int) (Math.random() * 3) + 1;
                    switch (randomNum) {
                        case 1:
                            // Randomly set number of spikes to 1, 2, 3, or 4
                            randomNum = (int) (Math.random() * 4);
                            ((Obstacle) GameManager.gameObjects[i]).setGameImage(randomNum);
                            GameManager.gameObjects[i].reset();
                            break;
                        default:
                            // Do nothing
                            break;
                    }
                }
                
                // Check for collision with the obstacles
                if (GameManager.gameObjects[i].collision() && !((Obstacle) GameManager.gameObjects[i]).getObstacle()) {
                    ((Player) GameManager.gameObjects[2]).loseLife(); // Lose life if collided
                    ((Obstacle) GameManager.gameObjects[i]).setObstacle(true); // Do not take more than one life for the same obstacle
                } else if (!GameManager.gameObjects[i].collision() && ((Obstacle) GameManager.gameObjects[i]).getObstacle()) { // If out of collision with the obstacle
                    ((Obstacle) GameManager.gameObjects[i]).setObstacle(false);
                }
            }
            
    }
}