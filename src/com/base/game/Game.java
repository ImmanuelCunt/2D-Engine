/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: In dieser Klasse können Objekte, Lichter hinzugefügt und verändert werden. Sie werden automatisch gerendert.
 */

package com.base.game;

import com.base.engine.*;
import com.base.gameobjects.Rectangle;
import com.base.util.pathfinder.Pathfinder;
import com.base.util.pathfinder.Point;
import org.lwjgl.util.vector.Vector3f;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


public class Game {

    public static final int X = 200;
    public static final int Y = 200;

    private ArrayList<GameObject> gameObjects;

    private int[][] field;

    public Game() {
        gameObjects = new ArrayList<GameObject>();

        field = new int[X][Y];

        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                double ran = Math.random();
                if(ran < .1)
                    field[i][j] = 1;
                else
                    field[i][j] = 0;


            }
        }

        updateGameObjects();
    }
    
    public void input() {
        if(Input.getKeyDown(Input.KEY_SPACE)) {
            Pathfinder pathfinder = new Pathfinder(field);

            long time = System.nanoTime();
            ArrayList<Point> path = pathfinder.findPathRect(0, 0, X-1, Y-1);
            System.out.println(System.nanoTime() - time);

            if(path != null) {
                for(int i = 0; i < path.size(); i++) {
                    System.out.println(path.get(i).getX() + "\t" + path.get(i).getY());
                }
            }
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
    }

    //-----Help Methods-----

    private void updateGameObjects() {
        gameObjects.removeAll(gameObjects);

        int sizeY = MainComponent.HEIGHT/Y;
        int sizeX = MainComponent.WIDTH/X;

        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 1)
                    gameObjects.add(new Rectangle(new Vector2f(sizeX*i ,sizeY*j), new Vector2f(sizeX, sizeY), 0, new Vector3f(1 ,0 , 1), null, new Vector2f(1, 1), new Vector2f(1, 1)));
            }
        }
    }
}