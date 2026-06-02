import java.awt.event.ActionEvent;      // event management
import java.awt.event.ActionListener;   // action listeners
import javax.swing.Timer;               // timer module
import java.awt.Graphics;               // graphics library
import java.awt.Image;                  // for rendering images
import java.awt.Toolkit;                // more rendering
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;         // for when you press keys on keyboard
import java.awt.AWTEvent;
import java.awt.*;                      // imports the java graphics

public class MainDrawingArea extends javax.swing.JPanel {
    // Fields
    static Image startTitle = Toolkit.getDefaultToolkit().getImage("GeoTitle.png"); // Starting page title
    static Image lives = Toolkit.getDefaultToolkit().getImage("heart.png"); // Heart images representing lives
    
    Timer t1;
    static boolean gameOver = true;
    static boolean gamePause = false;
    static boolean isJumping = false;
    static boolean jumpHigher = false;
    static boolean isFalling = false;
    static boolean landed = false;
    static int randomNum;
    
    static int curKey; // the current key being pressed

    /**
     * Creates new form MainDrawingArea
     */
    public MainDrawingArea() {
        initComponents();
        setFocusable(true);                                     // lets the main panel be focused so you can press the keys
        addKeyListener(new MainDrawingArea.AL());                         // adds a key listener
        t1 = new Timer(20, new MainDrawingArea.TimerListener());             // makes the delay
        t1.start();                                                          // starts the timer
        this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK
                | AWTEvent.KEY_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK
                | AWTEvent.COMPONENT_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK);// enables mouse events so you can refocus on the panel so you can control the game
        
        // GameManager getting ready to start game
        GameManager.setup();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // While game running
        if (!gameOver && !gamePause) {
            requestFocus();
            GameManager.update();
            
            // Reset background image if off-screen
            if (GameManager.gameObjects[0].getX() <= -1600) {
                GameManager.gameObjects[0].reset();
            }
            
      
            
            //Game is over once player loses all lives
            if (((Player) GameManager.gameObjects[2]).getLives() <= 0) {
                gameOver = true;
            }
            
            // Verifications for platforms
            for (int i = 10; i < GameManager.gameObjects.length; i++) {
                // One-third chance of regerating a platform once off-screen
                if (GameManager.gameObjects[i].getX() < -1000) {
                    randomNum = (int) (Math.random() * 3) + 1;
                    switch (randomNum) {
                        case 1:
                            GameManager.gameObjects[i].reset();
                            break;
                        default:
                            // Do nothing
                            break;
                    }
                }
                
                // Once colliding with a platform and was not on the platform before
                if (GameManager.gameObjects[i].collision() && !landed) {
                    ((Platform) GameManager.gameObjects[i]).setPlatform(); // Set ground to a new value
                    isJumping = false;
                    landed = true;
                } else if (landed && GameManager.gameObjects[i].collision()) { // If one the platform and was on the platform
                    // Let game proceed as normal
                } else if (landed && !GameManager.gameObjects[i].collision() && !isJumping) { // Fall off the platform once no longer touching it
                    ((Player) GameManager.gameObjects[2]).resetGround(); // Reset ground
                    ((Player) GameManager.gameObjects[2]).setFallVel(); // Make player fall
                    isFalling = true;
                    landed = false;
                }
            }
            
            // Check for collision with collectible coin
            if (GameManager.gameObjects[3].collision()) {
                GameManager.points += 100; // Increase points by 100
                GameManager.gameObjects[3].reset(); // Put to a random location
            } else if (GameManager.gameObjects[3].getX() < -1000) { // If coin goes off-screen
                // Half-chance of regenerating coin to a new location
                randomNum = (int) (Math.random() * 2) + 1;
                switch (randomNum) {
                    case 1:
                        GameManager.gameObjects[3].reset(); 
                        break;
                    default:
                        // Do nothing
                        break;
                }
            }
            
            // Second background follows first background
            GameManager.gameObjects[1].setX(GameManager.gameObjects[0].getX() + 1600);
            
            // Draw all game objects on-screen
            for (int i = 0; i < GameManager.gameObjects.length; i++) {
                g.drawImage(GameManager.gameObjects[i].getGameImage(), (int) GameManager.gameObjects[i].getX(), (int) GameManager.gameObjects[i].getY(), this);
            }
            
            // Draw the number of lives as hearts on screen
            for (int i = 0; i < ((Player) GameManager.gameObjects[2]).getLives(); i++) {
                g.drawImage(lives, 60 * i, 0, this);
            }
            
            // Draw the number of points earned on-screen
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(GameManager.points), 700, 50);
        } else if (!gameOver && gamePause) {
            // Draw things on the pause page
            g.drawImage(GameManager.gameObjects[0].getGameImage(), 0, 0, this);
            g.drawImage(startTitle, 450, 100, this);
            g.setColor(Color.green);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Endless Edition", 550, 220);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(GameManager.points), 700, 50);
        } else {
            // Draw images when the game is over
            g.drawImage(GameManager.gameObjects[0].getGameImage(), 0, 0, this);
            g.drawImage(startTitle, 450, 100, this);
            g.setColor(Color.green);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Endless Edition", 550, 220);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(GameManager.points), 700, 50);
            MainFrameArea.enablePage();
        }    
    }
    
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            repaint();                                      // repaints the screen
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1500, 900));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 957, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // get the key pressed event - key that was pressed
            curKey = e.getKeyCode();
            switch (curKey) {
                // Space causes the player to jump
                case KeyEvent.VK_SPACE:
                    if (!isJumping && !gameOver && !gamePause) {
                        isJumping = true;
                        jumpHigher = true;
                        ((Player) GameManager.gameObjects[2]).setJumpVel();
                    }   break;
                // Escape causes exiting the game
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                // Enter causes the game to start
                case KeyEvent.VK_ENTER:
                    if (gameOver || gamePause) {
                        MainFrameArea.disablePage();
                    }   break;
                // P causes the came to pause
                case KeyEvent.VK_P:
                    if (gamePause && !gameOver) {
                        MainFrameArea.playPage();
                    } else if (!gamePause && !gameOver) {
                        MainFrameArea.pausePage();
                    }   break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            curKey = e.getKeyCode();
            // Jump higher will be false once space is released
            if (curKey == KeyEvent.VK_SPACE) {
                jumpHigher = false;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
