import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Alien implements Common {

    private String enemyImg;
    public BufferedImage enemyImage;


    public boolean isVisible;
    private int enemyX;
    private int enemyY;

    private int direction;
    private int speed;


    public Alien(int x, int y){
        try{ enemyImage = ImageIO.read(new File("src/Image/enemy.gif"));}
        catch (IOException e){ e.printStackTrace(); }

        this.enemyX = x;
        this.enemyY = y;
        isVisible = true;
//        this.speed = speed;
//        this.direction = direction;
    }


    public void moveAlien(int moveInt) {
        //move 4px each time enemyMove is read


//        if (enemyX == FRAME_WIDTH -40 || enemyX == 20){
//            speed = -speed;
//        }
//
        this.enemyX += moveInt;

//        this.enemyX += speed;

    }

    //GETTERS and SETTERS

    public String getEnemyImg() {
        return enemyImg;
    }

    public BufferedImage getEnemyImage() {
        return enemyImage;
    }

    public int getAlienX() {
        return enemyX;
    }

    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public int getAlienY() {
        return enemyY;
    }

    public void setAlienY(int enemyY) {
        this.enemyY = enemyY;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}
