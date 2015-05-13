/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: In dieser Klasse können Objekte, Lichter hinzugefügt und verändert werden. Sie werden automatisch gerendert.
 */

package com.base.game;

import com.base.engine.*;
import com.base.gameobjects.Polygon;
import com.base.gameobjects.Rectangle;
import com.base.util.pathfinder.Pathfinder;
import com.base.util.pathfinder.Point;
import org.lwjgl.util.vector.Vector3f;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


public class Game {

    private ArrayList<GameObject> gameObjects;
    private ArrayList<Light> lights;


    public Game() {
        gameObjects = new ArrayList<GameObject>();
        lights = new ArrayList<Light>();
    }
    
    public void input() {
        if(Input.getKeyDown(Input.KEY_UP)) {
            lights.add(new Light(Input.getMousePosition(), 10,
                    new Vector3f((float) (Math.random()*1), (float) (Math.random()*1), (float) (Math.random()*3)), true));
        }

        if(Input.getKeyDown(Input.KEY_DOWN)) {
            gameObjects.add(new Polygon(Input.getMousePosition(), (Vector2f[]) null, 0, new Vector3f(0, 0, 0)));
        }

        if(Input.getKeyDown(Input.KEY_SPACE)) {
            gameObjects.removeAll(gameObjects);
            lights.removeAll(lights);
        }

        for(GameObject gameObject: gameObjects)
            gameObject.input();
    }

    public void update(double frameTime) {


        for(GameObject gameObject: gameObjects)
            gameObject.update(frameTime);
    }

    public void render() {
        for(GameObject gameObject : gameObjects) {
            gameObject.render();
        }
        for(Light light: lights)
            light.render(gameObjects);
    }
}