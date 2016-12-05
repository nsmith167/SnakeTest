import javax.swing.JFrame;

public class GameFrame extends JFrame
{
    GamePanel gPanel;
    
    GameFrame()
    {
        super("Basic Snake Game");
        gPanel = new GamePanel();
        getContentPane().add(gPanel);
        setSize(1000, 800);
        setResizable(false); //user can't change frame size
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //simple method to run the Game Panel
    public void run()
    {
        gPanel.run();
    }
}
