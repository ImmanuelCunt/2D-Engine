/**
 * Created by Simon on 16.05.2015.
 */

package com.base.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private int tileSize;
    private int width, height;
    private BufferedImage spriteSheet;

    public SpriteSheet(String path, int width, int height, int tileSize) {
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        try {
            spriteSheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int xPos, int yPos) {
        if ((xPos * tileSize > width - tileSize) || (yPos * tileSize > height - tileSize))
            System.out.println("Sprite-position out of range..");
        else if (spriteSheet == null)
            System.out.println("No sprite-sheet loaded..");
        else
            return spriteSheet.getSubimage(xPos * tileSize, yPos * tileSize, tileSize, tileSize);
        return null;
    }
}
