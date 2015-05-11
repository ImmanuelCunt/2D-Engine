/**
 * Author: Simon Gerhalter
 * Last Change: 5.4.2014
 * Function: Einfaches Rechteck mit Textur Unterstützung
 */

package com.base.gameobjects;

import com.base.engine.Draw;
import com.base.engine.GameObject;
import com.base.engine.Input;
import com.base.engine.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Rectangle extends GameObject{

    private Texture texture;

    private Vector2f pos;
    private Vector2f size;
    private float rot;
    private Vector3f color;
    private Vector2f textureCoords;
    private Vector2f textureSize;

    private boolean remove;

    private Vector2f destination;

    public Rectangle(Vector2f pos, Vector2f size, int rot, Vector3f color,Texture texture, Vector2f textureCoords, Vector2f textureSize) {
        super(pos, color);
        this.setSize(size);
        this.setRot(rot);
        this.setTexture(texture);
        this.setTextureCoords(textureCoords);
        this.textureSize = textureSize;

        this.remove = false;
    }

    public void input() {
        if(Input.getMouseDown(1)) {
            destination = Input.getMousePosition();
        }
    }

    public void update(double frameTime) {
        if(destination != null) {
            moveTo(destination, 200, frameTime);
        }
    }

    public void render() {
        Draw.rect(pos, getVertices(), rot, color, texture, textureCoords, textureSize);
    }

    public Vector2f[] getVertices() {
        return new Vector2f[] {
                new Vector2f(0, 0),
                new Vector2f(0, getSize().getY()),
                new Vector2f(getSize().getX(), getSize().getY()),
                new Vector2f(getSize().getX(), 0)
        };
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public Vector2f getTextureCoords() {
        return textureCoords;
    }

    public void setTextureCoords(Vector2f textureCoords) {
        this.textureCoords = textureCoords;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public boolean isRemove() {
        return remove;
    }

    public void remove() {
        this.remove = true;
    }
}
