import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class gp extends JPanel implements Runnable{
    
        //screen setting
        final int originalTileSize=16;
        final int scale=3;
        final int tileSize=originalTileSize*scale;
        final int maxScreenCol=7;
        final int maxScreenRow=13;
        final int screenWidth=tileSize*maxScreenCol;
        final int screenheight=tileSize*maxScreenRow;
        keyh kh=new keyh();
        Thread gameThread;
    int Playerx=100;
    int Playery=100;
    int Playerspeed=4;
    int fps=60;

    public gp(){
        this.setPreferredSize(new Dimension(screenWidth,screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }   
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval =1000000000/fps;
        double nextDrawTime =System.nanoTime()+drawInterval;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            System.out.println("Current time :"+currentTime);
            update();
            repaint();
            try {
                double remainingTime =nextDrawTime -System.nanoTime();
                
                remainingTime=remainingTime/1000000;
                if (remainingTime<0) {
                    remainingTime=0;
                }
                nextDrawTime+=drawInterval;
                Thread.sleep((long)remainingTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public void update(){
        if (kh.upPressed == true) {
            Playery -= Playerspeed;
        }
        else if (kh.downPressed == true) {
            Playery += Playerspeed;
        }
        else if (kh.leftPressed == true) {
            Playerx -= Playerspeed;
        }
        else if (kh.rightPressed == true) {
            Playerx += Playerspeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(Playerx, Playery, tileSize, tileSize);
        g2.dispose();
    }
}