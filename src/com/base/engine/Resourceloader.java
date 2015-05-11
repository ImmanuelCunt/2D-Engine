/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Einfache Klasse um Dateien zu öffnen
 */

package com.base.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Resourceloader {

    public static Texture loadTexture(String name) {
        try {
            Texture texture = TextureLoader.getTexture("png", new FileInputStream(new File("res/textures/" + name + ".png")));
            return texture;
        } catch (IOException ex) {
            System.out.println("Failed to load image!");
        }
        return null;
    }
}
