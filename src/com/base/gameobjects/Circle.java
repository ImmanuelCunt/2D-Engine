/**
 * Author: Simon Gerhalter
 * Last Change: 5.4.2014
 * Function: Kreis ohne Textur
 */

package com.base.gameobjects;

import com.base.engine.Draw;
import com.base.engine.GameObject;
import com.base.engine.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Circle extends GameObject{

    private float radius;
    private float resolution;

    public Circle(Vector2f pos, float radius, Vector3f color) {
        super(pos, color);
        this.setRadius(radius);
        if(radius/2 > 10)
            this.resolution = radius/2;
        else
            this.resolution = 10;
    }

    public void input() {

    }

    public void update(double frameTime) {

    }

    public void render() {
        Draw.circle(getPos(), getRadius(), (int) resolution,getColor());
    }

    public Vector2f[] getVertices() {
        Vector2f[] toReturn = new Vector2f[(int) (resolution+1)];
        for(int i = 0; i < toReturn.length; i++){
            double angle = Math.PI * 2 * i / resolution;
            toReturn[i] = new Vector2f((float)Math.cos(angle) * getRadius(), (float)Math.sin(angle) * getRadius());
        }
        return toReturn;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
