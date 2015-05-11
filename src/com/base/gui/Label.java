package com.base.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;

import java.awt.Font;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;

/**
 * Created by Simon on 03.01.14.
 * Diese Klasse war nur ein Test um ein Gui für Lwjgl zu entwickeln - Funktioniert teilweise, kann aber noch erweitert werden
 */
public class Label {

    private TrueTypeFont font;

    private String text;
    private int x;
    private int y;
    private Color color;

    public Label(String text, int x, int y, int size, Color color) {
        this.setText(text);
        this.setX(x);
        this.setY(y);
        this.setColor(color);

        Font awtFont = new Font("Arial", Font.PLAIN, size);
        font = new TrueTypeFont(awtFont, true);
    }

    public void render() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        font.drawString(getX(), Display.getHeight() - getY() - font.getHeight(), getText(), getColor());

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
