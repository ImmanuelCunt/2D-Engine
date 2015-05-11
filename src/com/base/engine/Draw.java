/**
 * Author: Simon Gerhalter
 * Last Change: 5.4.2014
 * Function: Klasse rendert Objekte.
 */

package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Draw{
    
    public static void rect(Vector2f pos, Vector2f[] vertices, float rot, Vector3f color, Texture texture, Vector2f textureCoords, Vector2f textureSize) {
        glPushMatrix(); {
            glTranslatef(pos.getX(), pos.getY(), 0);
            glRotatef(rot, 0, 0, 1);
            glColor3f(color.x, color.y, color.z);

            if(texture == null)
                glDisable(GL_TEXTURE_2D);
            else
                texture.bind();

            glBegin(GL_QUADS); {
                glTexCoord2f(textureCoords.getX(), textureCoords.getY());                                               glVertex2f(vertices[0].getX(), vertices[0].getY());
                glTexCoord2f(textureCoords.getX(), textureCoords.getY() + textureSize.getY());                          glVertex2f(vertices[1].getX(), vertices[1].getY());
                glTexCoord2f(textureCoords.getX() + textureSize.getX(), textureCoords.getY() + textureSize.getY());     glVertex2f(vertices[2].getX(), vertices[2].getY());
                glTexCoord2f(textureCoords.getX() + textureSize.getX(), textureCoords.getY());                          glVertex2f(vertices[3].getX(), vertices[3].getY());
            }
            glEnd();
        }
        glPopMatrix();
    }

    public static void circle(Vector2f pos, float radius, int resolution,Vector3f color) {
        glPushMatrix(); {
            glTranslatef(pos.getX(), pos.getY(), 0);
            glColor3f(color.x, color.y, color.z);
            glScalef(radius, radius, 1);

            glBegin(GL_TRIANGLE_FAN); {
                glVertex2f(0, 0);
                for(int i = 0; i <= resolution; i++){
                    double angle = Math.PI * 2 * i / resolution;
                    glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
                }
            }
            glEnd();
        }
        glPopMatrix();
    }

    public static void polygon(Vector2f pos, Vector2f[] vertices, float rot, Vector3f color, Texture texture, Vector2f[] textureCoords) {
        glPushMatrix(); {
            glTranslatef(pos.getX(), pos.getY(), 0);
            glRotatef(rot, 0, 0, 1);
            glColor3f(color.x, color.y, color.z);

            texture.bind();

            glBegin(GL_TRIANGLE_STRIP); {
                for(int i = 0; i < vertices.length; i++) {
                    if(vertices[i] != null)
                        glTexCoord2f(textureCoords[i].getX(), textureCoords[i].getY());     glVertex2f(vertices[i].getX(), vertices[i].getY());
                }
            }
            glEnd();
        }
        glPopMatrix();
    }
}