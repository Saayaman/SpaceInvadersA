import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Space Invaders");
        Board controller = new Board(frame);

        frame.getContentPane().add(controller);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        //if you close window, game will end!
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
