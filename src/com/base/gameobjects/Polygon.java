/**
 * Author: Simon Gerhalter
 * Last Change: 5.4.2014
 * Function: Durch ein Vector2f array können beliebige Formen gerendert werden, besitz noch keine Texur Unterstützung
 */

package com.base.gameobjects;

import com.base.engine.*;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Polygon extends GameObject {

    private Vector2f[] vertices, textureCoords;
    private Texture texture;
    private float rot;

    public Polygon(Vector2f pos, Vector2f[] vertices, float rot, Vector3f color) {
        super(pos, color);
        this.vertices = vertices;
        this.rot = rot;
    }

    public Polygon(Vector2f pos, String name, float rot, Vector3f color) {
        super(pos, color);
        initPolygon(new Bitmap(name));
        this.texture = Resourceloader.loadTexture(name);
        this.rot = rot;
    }

    private void initPolygon(Bitmap bitmap) {
        bitmap.flipY();

        int count = 0;
        for(int i = 0; i < bitmap.getWidth(); i++) {
            for(int j = 0; j < bitmap.getHeight(); j++) {
                if(bitmap.getPixel(i, j) < -16777016) {
                    count++;
                }
            }
        }

        vertices = new Vector2f[count];
        textureCoords = new Vector2f[count];
        int color;

        for(int i = 0; i < bitmap.getWidth(); i++) {
            for(int j = 0; j < bitmap.getHeight(); j++) {
                if((color = bitmap.getPixel(i, j)) < -16777016) {
                    vertices[color + 16777216] = new Vector2f(i, j);
                    textureCoords[color + 16777216] = new Vector2f((float)i/bitmap.getWidth(), 1-(float)j/bitmap.getHeight());
                }
            }
        }
    }

    public void input() {

    }

    public void update(double frameTime) {
    }

    public void render() {
        Draw.polygon(this.getPos(), vertices, rot, this.getColor(), texture, textureCoords);
    }

    public Vector2f[] getVertices() {
        return vertices;
    }
}
