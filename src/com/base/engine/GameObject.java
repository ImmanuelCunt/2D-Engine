
/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Grundlegende Klasse für Objekte wie "Rectangele", "Circle", "Polygon",...
 */

package com.base.engine;

import org.lwjgl.util.vector.Vector3f;

public class GameObject {

    private Vector2f pos;
    private Vector3f color;

    private boolean remove;

    public GameObject(Vector2f pos, Vector3f color) {
        this.setPos(pos);
        this.setColor(color);

        this.remove = false;
    }

    public void input() {

    }
    
    public void update(double frameTime) {
    }

    public void render() {

    }

    public Vector2f[] getVertices() {
        return null;
    }

    public Vector2f[] getHitBox() {
        return getVertices();
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public boolean isRemove() {
        return remove;
    }

    public void remove() {
        this.remove = true;
    }

    //Help Methods

    protected void moveTo(Vector2f destination, float speed, double frameTime) {

        Vector2f dif = destination.sub(getPos());

        if(Math.abs(dif.getX()) > speed*0.01 || Math.abs(dif.getY()) > speed*0.01) {
            dif = dif.normalized();

            setPos(getPos().add(dif.mul(speed).mul((float) frameTime)));
        }
    }
}