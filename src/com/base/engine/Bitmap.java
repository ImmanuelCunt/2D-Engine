/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Klasse lädt eine Bitmap um z.B. eine Map für ein Spiel zu laden.
 */

package com.base.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bitmap {

    private int width;
    private int height;
    private int[] pixels;

    public Bitmap(String name) {
        try {
            BufferedImage image = ImageIO.read(new File("./res/bitmaps/" + name + ".png"));

            width = image.getWidth();
            height = image.getHeight();

            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }
    
    public Bitmap flipX() {
        int[] temp = new int[pixels.length];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp[i + j * width] = pixels[(width - i - 1) + j * width];
            }
        }

        pixels = temp;

        return this;
    }

    public Bitmap flipY() {
        int[] temp = new int[pixels.length];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp[i + j * width] = pixels[i + (height - j - 1) * width];
            }
        }

        pixels = temp;

        return this;
    }
    
    public Vector2f getStart() {
        Vector2f start = null;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(pixels[j + i * width] == -8454144) {
                    start = new Vector2f(j, i);
                }
            }
        }
        return start;
    }
    
    public Vector2f getStop() {
        Vector2f stop = null;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(pixels[j + i * width] == -65536) {
                    stop = new Vector2f(j, i);
                }
            }
        }
        return stop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixel() {
        int[][] toreturn = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                toreturn[j][i] = pixels[j + i * width];
            }
        }
        return toreturn;
    }

    public int getPixel(int x, int y) {
        return pixels[x + y * width];
    }

    public void setPixel(int x, int y, int value) {
        pixels[x + y * width] = value;
    }
}
