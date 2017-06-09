import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;


public class Board extends JPanel implements Common, KeyListener {

    JLabel jLabel;

    private ArrayList<Alien> aliens;
    private Ship ship;
    Bullet bullet;

    ArrayList<Bullet> bullets;
    int deaths = 0;
    private int speed = 1;

    Timer t;

    public Board(JFrame frame){

        initCharacters();

        addKeyListener(this);

        frame.getContentPane();
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        setBackground(Color.black);

        jLabel = new JLabel("Click Anything to Start Game", JLabel.CENTER);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("Verdana",1,20));
        jLabel.setBorder(BorderFactory.createLineBorder(Color.white));
        jLabel.setVisible(false);

        setLayout(new GridBagLayout());
        add(jLabel);

        //MUST setFocusable
        setFocusable(true);

        ActionListener s = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                moveAliens();
                movePlayer();
                moveBullets();
                repaint();
            }
        };

        t=new Timer(10, s);
        t.start();
    }

    private void initCharacters() {

        aliens = new ArrayList<>();

        for (int row=0;row<6;row++) {
            for (int column=0;column<10;column++) {
                Alien enemy = new Alien(ENEMY_initX + 50 * column, ENEMY_initY + 35 * row);
                aliens.add(enemy);
            }
        }

        ship = new Ship();
        bullets = new ArrayList<>();
        bullet = new Bullet();


    }

    private void movePlayer() {

    }

    //Paint is automatically called in JPanel, when IOException readfile() is called!
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Alien alien : aliens) {
            if (alien.getVisible()) {
                g.drawImage(alien.enemyImage, alien.getAlienX(), alien.getAlienY(), this);
            }
        }

        if (bullet.isVisible()) {
            g.drawImage(bullet.getBulletImage(), bullet.getBulletX(), bullet.getBulletY(), this);
        }
        g.drawImage(ship.getShipImage(), ship.getShipX(), ship.getShipY(), this);
    }


    private void moveAliens() {

        for (Alien alien : aliens) {

            if (alien.getVisible()){
                alien.moveAlien(speed);

                int enemyX = alien.getAlienX();
                if (enemyX >= FRAME_WIDTH - 40 || enemyX <= 10) {

                    speed = -speed;
                    Iterator i1 = aliens.iterator();

                    while (i1.hasNext()) {
                        Alien a2 = (Alien) i1.next();
                        a2.setAlienY(a2.getAlienY() + 10);
                    }
                }

                int alienX = alien.getAlienX();
                int alienY = alien.getAlienY();
                int shipY = ship.getShipY();
                int shipX = ship.getShipX();

                // in order to use intersect method, you have to make new Rectangle
                Rectangle alienRec = new Rectangle(alienX,alienY, ENEMY_WIDTH, ENEMY_HEIGHT);
                Rectangle playerRec = new Rectangle(shipX,shipY, PLAYER_WIDTH,PLAYER_HEIGHT);

                if (alienRec.intersects(playerRec)){
                    System.out.println("gameover!");
                    gameOver();
                    break;
                }

                if (alienY == FRAME_HEIGHT){
                    gameOver();
                    break;
                }
            }

        }


    }

    private void gameOver() {

        jLabel.setVisible(true);
        jLabel.setText("Game Over!");

        t.stop();

        for (Alien alien: aliens){
            alien.setVisible(false);
        }
    }


    private void moveBullets(){

        if (bullet.isVisible()){
            bullet.moveBullet();

            for (Alien alien : aliens) {

                if (alien.getVisible()){

                    int alienX = alien.getAlienX();
                    int alienY = alien.getAlienY();
                    int bulletX = bullet.getBulletX();
                    int bulletY = bullet.getBulletY();

                    if (bulletX >= (alienX)
                            && bulletX <= (alienX + ENEMY_WIDTH)
                            && bulletY >= (alienY)
                            && bulletY <= (alienY + ENEMY_HEIGHT))
                    {
                        bullet.setVisible(false);
                        alien.setVisible(false);
                        deaths++;
                    }
                }
            }

            if (deaths == aliens.size()){
                gameWon();
            }
        }
    }

    private void gameWon() {
        jLabel.setVisible(true);
        jLabel.setText("Game Won!");
        t.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
                int x = ship.getShipX();
                int y = ship.getShipY();

                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT){
                    ship.playerMove(-6);
                }

                if (key == KeyEvent.VK_RIGHT){
                    ship.playerMove(6);
                }

                if (key == KeyEvent.VK_SPACE) {

                    bullet = new Bullet(x,y);
                    bullets.add(bullet);
                    bullet.moveBullet();
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    ship.playerMove(0);
                }

                if (key == KeyEvent.VK_RIGHT) {
                    ship.playerMove(0);
                }
    }
}
