package Billiards;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private final Component  canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    public Color color;
    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;
    public Ball(Component c){
        this.canvas = c;
        this.color = Color.GRAY;
        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }
    public Ball(Component c, Color color){
        this.canvas = c;
        this.color = color;
        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }
    public Ball(Component c, Color color, int x, int y){
        this.canvas = c;
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public void draw (Graphics2D g2){
        g2.setColor(this.color);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }
    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }

        if(isInHole()){
            Thread.currentThread().interrupt();
            removeBall(this);
            BounceFrame.incCaughtText();
        }
        BounceFrame.canvas.repaint();
    }
    public boolean isInHole() {
        int centerX = x + XSIZE/2;
        int centerY = y + YSIZE/2;

        for (Hole hole : BallCanvas.holes) {
            if (hole.catched(centerX, centerY)) {
                return true;
            }
        }

        return false;
    }
    public void removeBall(Ball ball) {
        int id = BallCanvas.balls.indexOf(ball);
        BallCanvas.balls.set(id, null);
    }
}