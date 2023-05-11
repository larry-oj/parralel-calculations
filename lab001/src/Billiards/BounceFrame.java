package Billiards;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class BounceFrame extends JFrame {
    static BallCanvas canvas;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private static BallThread prevThread = null;
    private static int caught = 0;
    public static synchronized void increment() {
        caught++;
    }
    private static final JLabel caughtText = new JLabel("Caught balls: 0");
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Billiards");
        BounceFrame.canvas = new BallCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonAddOne = new JButton("Add 1");
        JButton buttonAddTen = new JButton("Add 10");
        JButton buttonAddRed = new JButton("Add Red");
        JButton buttonAddBlue = new JButton("Add Blue");
        JButton buttonRemoveHoles = new JButton("Remove holes");
        JButton buttonAddHundredBlue = new JButton("Add 1 Red | 100 Blue");
        JButton buttonJoinAbleBalls = new JButton("Add 1 join-able ball");
        JButton buttonStop = new JButton("Stop");

        // holes
        final int holeSize = 25;
        canvas.addHole(new Hole(canvas, holeSize, holeSize));
        canvas.addHole(new Hole(canvas, WIDTH / 2, holeSize));
        canvas.addHole(new Hole(canvas, WIDTH - holeSize, holeSize));
        canvas.addHole(new Hole(canvas, holeSize, HEIGHT - holeSize * 3));
        canvas.addHole(new Hole(canvas, WIDTH / 2, HEIGHT - holeSize * 3));
        canvas.addHole(new Hole(canvas, WIDTH - holeSize, HEIGHT - holeSize * 3));
        canvas.repaint();

        buttonAddOne.addActionListener(e -> {
            addBall();
        });
        buttonAddTen.addActionListener(e -> {
            for( int i = 0; i < 10; i++){
                addBall();
            }
        });
        buttonAddRed.addActionListener(e -> {
            addBall(Color.RED);
        });
        buttonAddBlue.addActionListener(e -> {
            addBall(Color.BLUE);
        });
        buttonAddHundredBlue.addActionListener(e -> {
            for( int i = 0; i < 100; i++){
                addBall(Color.BLUE, 70, 70);
            }
            addBall(Color.RED, 70, 70);
        });
        buttonStop.addActionListener(e -> {
                System.exit(0);
        });
        buttonRemoveHoles.addActionListener(e -> {
            canvas.deleteHoles();
        });
        buttonJoinAbleBalls.addActionListener(e -> {
            addBall(true);
        });
        buttonPanel.add(buttonAddOne);
        buttonPanel.add(buttonAddTen);
        buttonPanel.add(buttonAddBlue);
        buttonPanel.add(buttonAddRed);
        buttonPanel.add(buttonAddHundredBlue);
        buttonPanel.add(buttonRemoveHoles);
        buttonPanel.add(buttonJoinAbleBalls);
        buttonPanel.add(buttonStop);
        buttonPanel.add(caughtText);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
    public void addBall(){
        Billiards.Ball b = new Billiards.Ball(canvas);
        canvas.add(b);
        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Thread name = " + thread.getName());
    }

    public void addBall(Color color){
        Ball b = new Ball(canvas, color);
        canvas.add(b);
        BallThread thread = new BallThread(b);
        if(color.equals(Color.RED)){
            thread.setPriority(MAX_PRIORITY);
        }
        else { // Color.BLUE
            thread.setPriority(MIN_PRIORITY);
        }
        thread.start();
        System.out.println("Thread name = " + thread.getName());
    }

    public void addBall(Color color, int x, int y){
        Ball b = new Ball(canvas, color, x, y);
        canvas.add(b);
        BallThread thread = new BallThread(b);
        if(color.equals(Color.RED)){
            thread.setPriority(MAX_PRIORITY);
        }
        else {
            thread.setPriority(MIN_PRIORITY);
        }
        thread.start();
        System.out.println("Thread name = " + thread.getName());
    }

    public void addBall(boolean joinAble){
        Ball b = new Ball(canvas, Color.ORANGE);
        canvas.add(b);
        BallThread thread;
        if(prevThread == null){
            thread = new BallThread(b, null);
        }
        else{
            thread = new BallThread(b, prevThread);
        }
        thread.start();
        prevThread = thread;
        System.out.println("Thread name = " + thread.getName());
    }

    public static void incCaughtText() {
        increment();
        caughtText.setText("Caught balls: " + caught);
    }
}