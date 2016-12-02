
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements KeyListener, ActionListener
{
    private Dot currApple; //"apple" currently displayed on the screen 
    private Snake snake; 
    private boolean running; //whether or not the game is running
    private Timer timer; 
    private boolean collided; //whether or not the snake collided with something
    private boolean ate; //whether or not the snake ate an apple
    
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int DELAY = 80; //delays the "ticks" of the timer
    
    public GamePanel()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        currApple = new Dot(75, 75, Color.BLACK, 15);
        snake = new Snake(200, 200);
        collided = false;
        ate = false;
        addKeyListener(this);
        setFocusable(true); //allows the panel to take input from the keyboard
    }
    
    //Runs the game
    public void run()
    {
        running = true;
        timer = new Timer(DELAY, this); //initializes the timer
        timer.start();
        
    }
    
    @Override
    /*
    * The timer creates an action event on every "tick" which calls this method
    * Essentially, this is what we want the game to do on every "tick" of the timer
    */
    public void actionPerformed(ActionEvent e) 
    {   
        if(running == true)
        {
            //TODO: add instructions for collision testing and apple placement
            snake.move();
            System.out.println(snake.getX()+","+snake.getY()); //temporary for testing
            if(collided())
            {
                System.out.println("Collision!");
                running = false;
            }
        }
        
        repaint(); //In this case calls paintComponent()
    }
    
    @Override
    /*
    * Refreshes the screen. Should draw all elements of the game that need to be displayed within the panel.
    */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        snake.draw(g);
        //currApple.draw(g);    TODO: make currApple useful, give it location, generate new coordinates, etc
    }
    
    @Override
    /*
    * Triggered any time a key on the keyboard is pressed.
    * Uses input from the arrow keys to set the snake's direction
    */
    public void keyPressed(KeyEvent e) 
    {   
        if(e.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != 'd')
            snake.setDirection('u');
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != 'u')
            snake.setDirection('d');
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != 'r')
            snake.setDirection('l');
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != 'l')
            snake.setDirection('r'); 
        else if(e.getKeyCode() == KeyEvent.VK_SPACE)  //TODO: remove this, currently only here to test growth
            snake.grow();
    }
   
    //TODO: give some definition to a collision, fix definition of what constitutes a collision
    public boolean collided()
    {
        if(snake.getSize() > 1)
        {
            for(int i = 1; i < snake.getSize(); i++)
            {
                if(snake.getX() == snake.getBody().get(i).getX() && snake.getY() == snake.getBody().get(i).getY())
                    collided = true;
            }
        }
        if(snake.getX() < 0 || (snake.getX() + snake.getThickness()) > WIDTH || snake.getY() < 0 || (snake.getY() + snake.getThickness()) > HEIGHT)
            System.out.println("Collided!");;
        
        return collided;
    }
    
    //TODO: fix definition of how a snake "eats" something
    public boolean ate()
    {
        if(snake.getX() >= currApple.getX() && snake.getX() <= (currApple.getX() + currApple.getThickness()) && snake.getY() >= currApple.getY() && snake.getY() <= (currApple.getY() + currApple.getThickness()))
            ate = true;
        return ate;
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        //does nothing
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
      //does nothing
    }    
}
    