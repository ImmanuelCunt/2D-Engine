package com.base.gui;

import com.base.engine.Input;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.*;
import java.awt.Font;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;

/**
 * Created by Simon on 03.01.14.
 * Diese Klasse war nur ein Test um ein Gui für Lwjgl zu entwickeln - Funktioniert nicht, kann aber erweitert werden.
 */
public class Button {

    private static final int OFFSET = 1;

    private TrueTypeFont font;

    private String text;
    private int x;
    private int y;
    private int sx;
    private int sy;
    private Color color;

    public Button(String text, int x, int y, int sx, int sy, Color color) {
        this.setText(text);
        this.setX(x);
        this.setY(y);
        this.setSx(sx);
        this.setSy(sy);
        this.setColor(color);

        Font awtFont = new Font("Arial", Font.PLAIN, sy - OFFSET * 2);
        font = new TrueTypeFont(awtFont, true);
    }

    public void render() {
        //Draw.rect(x - font.getWidth(text)/2, y, sx, sy, 0, 1, 1, 1, null);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        font.drawString(getX(), Display.getHeight() - getY() - font.getHeight() + OFFSET, getText(), getColor());

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }

    public boolean pressed() {
        Rectangle r1 = new Rectangle((int) Input.getMousePosition().getX(), (int)Input.getMousePosition().getY(), 1, 1);
        Rectangle r2 = new Rectangle(x, y, sx, sy);

        return r1.intersects(r2);
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

    public int getSx() {
        return sx;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    public int getSy() {
        return sy;
    }

    public void setSy(int sy) {
        this.sy = sy;
    }
}
