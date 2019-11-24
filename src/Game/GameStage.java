package Game;
import javax.swing.*;
import java.awt.*;


public class GameStage extends JFrame {
    ActionGame aG = new ActionGame(this);
    Game.Sound_cdjv sound = new Game.Sound_cdjv("C:\\Users\\phamv\\IdeaProjects\\ABCDEFGHIK\\Resource\\Textures\\Nhacnen.wav");
    public static Dimension size = new Dimension(1100,750);  // 700 550

    public GameStage() {
        setTitle("Tower Defence");
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1,0,0));
        add(aG);
      //  sound.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        GameStage gst = new GameStage();
    }
}
