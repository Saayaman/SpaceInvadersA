import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by ayako_sayama on 2017-06-02.
 */
public class Bullet {
    private Image bulletImage;

    private int bulletX;
    private int bulletY;

    private boolean isVisible;

    public Bullet(){

    }

    public Bullet(int playerX, int playerY){

        try{ bulletImage = ImageIO.read(new File("src/Image/shot.gif"));}
        catch (IOException e){ e.printStackTrace(); }

        isVisible = true;

        bulletX = playerX + 10 ;
        bulletY = playerY - 10;

    }

    public void moveBullet(){
        bulletY -= 3;
    }

    public void destroyed(){
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }


    public Image getBulletImage() {
        return bulletImage;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }
}
