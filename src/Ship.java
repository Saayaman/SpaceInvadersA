import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by ayako_sayama on 2017-06-01.
 */
public class Ship implements Common{

    private Image shipImage;
    private int playerX;
    private int playerY;

    public Ship(){

        try{ shipImage = ImageIO.read(new File("src/Image/ship.gif"));}
        catch (IOException e){ e.printStackTrace(); }

        playerX = (FRAME_WIDTH / 2 - 20);
        playerY = 540;
    }

    public void playerMove(int x){
        playerX += x;

        if (playerX >= FRAME_WIDTH - 40){
            playerX = FRAME_WIDTH - 40;
        } else if (playerX <= 0 + 10){
            playerX = 0 + 10;
        }

    }

    //GETTER and SETTER


    public int getShipX() {
        return playerX;
    }

    public int getShipY() {
        return playerY;
    }

    public Image getShipImage() {
        return shipImage;
    }
}
