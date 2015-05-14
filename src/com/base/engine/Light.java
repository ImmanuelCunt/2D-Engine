/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Mit Hilfe des FragmentShaders können durch dieser Klasse Lichter dargestellt werden.
 */

package com.base.engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Light {
    private Vector2f pos;
    private float radius;
    private Vector3f color;
    private boolean shadowsEnabled;

    private boolean remove;

    private static int shaderProgram;
    private static boolean shaderInit;

    public Light(Vector2f pos, float radius, Vector3f color, boolean shadowsEnabled) {
        this.setPos(new Vector2f(pos.getX(), Display.getHeight() - pos.getY()));
        this.setRadius(radius);
        this.setColor(color);
        this.setShadowsEnabled(shadowsEnabled);

        this.remove = false;
        if(!shaderInit)
            initShader();
    }

    //Initialisiert den FragmentShader, der für die Beleuchtung zuständig ist.
    private void initShader() {
        System.out.println("Light initialised");
        shaderProgram = glCreateProgram();
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        StringBuilder fragmentShaderSource = new StringBuilder();

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("res/fragmentShader.fs"));
            while ((line = reader.readLine()) != null) {
                fragmentShaderSource.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);

        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment shader not compiled!");
        }

        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);

        shaderInit = true;
    }
	
	public static void destroy() {
        shaderInit = false;
    }

    public void render(ArrayList<GameObject> gameObjects) {

        if(shadowsEnabled) {
            System.out.println("hi");
            glColorMask(false, false, false, false);
            glStencilFunc(GL_ALWAYS, 1, 1);
            glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);

            for (GameObject gameObject: gameObjects) {
                Vector2f[] hitBox = gameObject.getHitBox();
                for (int i = 0; i < hitBox.length; i++) {
                    Vector2f currentVertex = new Vector2f(hitBox[i].getX() + gameObject.getPos().getX(), Display.getHeight() - hitBox[i].getY() - gameObject.getPos().getY());
                    Vector2f nextVertex = new Vector2f(hitBox[(i + 1) % hitBox.length].getX() + gameObject.getPos().getX(),
                            Display.getHeight() - hitBox[(i + 1) % hitBox.length].getY() - gameObject.getPos().getY());
                    Vector2f edge = nextVertex.sub(currentVertex);
                    Vector2f normal = new Vector2f(edge.getY(), -edge.getX());
                    Vector2f lightToCurrent = currentVertex.sub(pos);
                    if (normal.dot(lightToCurrent) > 0) {
                        Vector2f point1 = currentVertex.add(currentVertex.sub(pos).scale(800));
                        Vector2f point2 = nextVertex.add(nextVertex.sub(pos).scale(800));
                        glBegin(GL_QUADS); {
                            glVertex2f(currentVertex.getX(), Display.getHeight() - currentVertex.getY());
                            glVertex2f(point1.getX(), Display.getHeight() - point1.getY());
                            glVertex2f(point2.getX(), Display.getHeight() - point2.getY());
                            glVertex2f(nextVertex.getX(), Display.getHeight() - nextVertex.getY());
                        } glEnd();
                    }
                }
            }

            glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
            glStencilFunc(GL_EQUAL, 0, 1);
            glColorMask(true, true, true, true);
        }

        glUseProgram(shaderProgram);
        glUniform2f(glGetUniformLocation(shaderProgram, "lightLocation"), pos.getX(), Display.getHeight() - pos.getY());
        glUniform3f(glGetUniformLocation(shaderProgram, "lightColor"), getColor().x, getColor().y, getColor().z);
        glUniform1f(glGetUniformLocation(shaderProgram, "radius"), getRadius());

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        glBegin(GL_QUADS); {
            glVertex2f(0, 0);
            glVertex2f(0, Display.getHeight());
            glVertex2f(Display.getWidth(), Display.getHeight());
            glVertex2f(Display.getWidth(), 0);
        } glEnd();

        glDisable(GL_BLEND);
        glUseProgram(0);
        glClear(GL_STENCIL_BUFFER_BIT);
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public boolean isShadowsEnabled() {
        return shadowsEnabled;
    }

    public void setShadowsEnabled(boolean shadowsEnabled) {
        this.shadowsEnabled = shadowsEnabled;
    }
}
