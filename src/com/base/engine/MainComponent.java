package com.base.engine;

import com.base.game.Game;
import java.awt.*;

public class MainComponent {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth = (int) screenSize.getWidth();
    private static int screenHeight = (int) screenSize.getHeight();
    public static final String TITLE = "Test123";
    public static final double FRAME_CAP = 60.0;
    private boolean isRunning;
    private boolean isFullscreen = false;
    private Game game;

    public MainComponent() {
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();
    }

    public void start() {
        if (isRunning) {
            return;
        }
        run();
    }

    public void stop() {
        if (!isRunning) {
            return;
        }

        isRunning = false;
    }

    private void run() {
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Delay.getTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;

            long startTime = Delay.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) Delay.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {
                render = true;

                unprocessedTime -= frameTime;

                if (Window.isCloseRequested()) {
                    stop();
                }

                Delay.setDelta(frameTime);

                isRunning = !game.input();
                Input.update();

                if(!isFullscreen){

                }

                game.update(frameTime);

                if (frameCounter >= Delay.SECOND) {
                    Window.setTitle(TITLE + " - FPS: " + frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if (render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        cleanUp();
    }

    private void render() {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }

    private void cleanUp() {
        Window.dispose();
    }

    public static void main(String[] args) {
        Window.createWindow(WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent();
        game.start();
    }
}
