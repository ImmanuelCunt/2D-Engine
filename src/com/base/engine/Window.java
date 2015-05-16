/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Erstellt das Fenster. Es können auch Eigenschaften des Fensters geändert/aufgerufen werden.
 */

package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glMatrixMode;


public class Window {
    private static int m_width, m_height;

    public static void createWindow(int width, int height, String title) {
        m_width = width;
        m_height = height;
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void render() {
        Display.update();
    }

    public static void dispose() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
        Light.destroy();
    }

    public static void setFullscreen(boolean fullscreen) {
        try {
            if (fullscreen) {
                Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
            } else {
                Display.setDisplayMode(new DisplayMode(m_width, m_height));
            }
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void initTextures() {
        /*
        try {
            wallTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/wall.png"));
            floorTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/floor.png"));
            crosshairTex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/crosshair.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    public static int getWidth() {
        return Display.getDisplayMode().getWidth();
    }

    public static int getHeight() {
        return Display.getDisplayMode().getHeight();
    }

    public static String getTitle() {
        return Display.getTitle();
    }

    public static void setTitle(String title) {
        Display.setTitle(title);
    }
}