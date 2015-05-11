package com.base.util.pathfinder;

import java.util.ArrayList;

/**
 * Created by Simon on 10.06.2014.
 * Class with multiple pathfinder algorithms
 */
public class Pathfinder {

    private int [][] game;

    private ArrayList<Point> openList;
    private ArrayList<Point> closedList;

    public Pathfinder(int[][] game) {
        this.game = game;
        openList = new ArrayList<Point>();
        closedList = new ArrayList<Point>();
    }

    public ArrayList<Point> findPathRect(int startX, int startY, int finishX, int finishY) {
        closedList.add(new Point(startX, startY, 1, heuristicRect(startX, startY, finishX, finishY), null));

        int size = 0;
        while(closedList.get(size).getX() != finishX || closedList.get(size).getY() != finishY) {
            addPointRect(closedList.get(size).getX() - 1, closedList.get(size).getY(), closedList.get(size), finishX, finishY);
            addPointRect(closedList.get(size).getX() + 1, closedList.get(size).getY(), closedList.get(size), finishX, finishY);
            addPointRect(closedList.get(size).getX(), closedList.get(size).getY() - 1, closedList.get(size), finishX, finishY);
            addPointRect(closedList.get(size).getX(), closedList.get(size).getY() + 1, closedList.get(size), finishX, finishY);

            if(openList.size() > 0) {
                int shortestPath = getShortestPath();
                closedList.add(openList.get(shortestPath));
                openList.remove(shortestPath);

                size++;
            } else {
                System.out.println("Kein Weg Gefunden");
                return null;
            }
        }
        return closedList.get(size).getPoints(new ArrayList<Point>());
    }

    public ArrayList<Point> findPathHex(int startX, int startY, int finishX, int finishY) {
        closedList.add(new Point(startX, startY, 1, heuristicHex(startX, startY, finishX, finishY), null));

        int size = 0;
        while(closedList.get(size).getX() != finishX || closedList.get(size).getY() != finishY) {
            addPointHex(closedList.get(size).getX() - 1, closedList.get(size).getY(), closedList.get(size), finishX, finishY);
            addPointHex(closedList.get(size).getX() + 1, closedList.get(size).getY(), closedList.get(size), finishX, finishY);
            addPointHex(closedList.get(size).getX(), closedList.get(size).getY() - 1, closedList.get(size), finishX, finishY);
            addPointHex(closedList.get(size).getX(), closedList.get(size).getY() + 1, closedList.get(size), finishX, finishY);
            if(closedList.get(size).getY()%2 == 0) {
                addPointHex(closedList.get(size).getX() - 1, closedList.get(size).getY() - 1, closedList.get(size), finishX, finishY);
                addPointHex(closedList.get(size).getX() - 1, closedList.get(size).getY() + 1, closedList.get(size), finishX, finishY);
            } else {
                addPointHex(closedList.get(size).getX() + 1, closedList.get(size).getY() - 1, closedList.get(size), finishX, finishY);
                addPointHex(closedList.get(size).getX() + 1, closedList.get(size).getY() + 1, closedList.get(size), finishX, finishY);
            }

            if(openList.size() > 0) {
                int shortestPath = getShortestPath();
                closedList.add(openList.get(shortestPath));
                openList.remove(shortestPath);

                size++;
            } else {
                System.out.println("Kein Weg Gefunden");
                return null;
            }
        }
        return closedList.get(size).getPoints(new ArrayList<Point>());
    }

    public ArrayList<Point> findRangeRect(int startX, int startY, int range) {
        openList.add(new Point(startX, startY, 1, 0, null));

        for(int i = 0; i < range; i++) {
            for(int j = openList.size()-1; j >= 0; j--) {
                addPoint(openList.get(j).getX() - 1, openList.get(j).getY(), openList.get(j));
                addPoint(openList.get(j).getX() + 1, openList.get(j).getY(), openList.get(j));
                addPoint(openList.get(j).getX(), openList.get(j).getY() - 1, openList.get(j));
                addPoint(openList.get(j).getX(), openList.get(j).getY() + 1, openList.get(j));

                closedList.add(openList.get(j));
                openList.remove(j);
            }
        }
        closedList.addAll(openList);
        return closedList;
    }

    public ArrayList<Point> findRangeHex(int startX, int startY, int range) {
        openList.add(new Point(startX, startY, 1, 0, null));

        for(int i = 0; i < range; i++) {
            for(int j = openList.size()-1; j >= 0; j--) {
                addPoint(openList.get(j).getX() - 1, openList.get(j).getY(), openList.get(j));
                addPoint(openList.get(j).getX() + 1, openList.get(j).getY(), openList.get(j));
                addPoint(openList.get(j).getX(), openList.get(j).getY() - 1, openList.get(j));
                addPoint(openList.get(j).getX(), openList.get(j).getY() + 1, openList.get(j));

                if(openList.get(j).getY()%2 == 0) {
                    addPoint(openList.get(j).getX() - 1, openList.get(j).getY() - 1, openList.get(j));
                    addPoint(openList.get(j).getX() - 1, openList.get(j).getY() + 1, openList.get(j));
                } else {
                    addPoint(openList.get(j).getX() + 1, openList.get(j).getY() - 1, openList.get(j));
                    addPoint(openList.get(j).getX() + 1, openList.get(j).getY() + 1, openList.get(j));
                }

                closedList.add(openList.get(j));
                openList.remove(j);
            }
        }
        closedList.addAll(openList);
        return closedList;
    }

    public void delete() {
        openList = new ArrayList<Point>();
        closedList = new ArrayList<Point>();
    }

    public ArrayList<Point> getPath() {
        return closedList;
    }

    //-----Help Methods-----

    private int heuristicRect(int x1, int y1, int x2, int y2) {
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    private int heuristicHex(int x1, int y1, int x2, int y2) {
        int deltaX = x2-x1;
        int deltaY = Math.abs(y2-y1);

        if(y1%2 == 0) {
            if(deltaX > 0)
                deltaX -= deltaY/2;
            else if(deltaX < 0)
                deltaX -= (deltaY/2+1);
        } else {
            if(deltaX > 0)
                deltaX -= (deltaY/2+1);
            else if(deltaX < 0)
                deltaX -= deltaY/2;
        }
        return Math.abs(deltaX)+deltaY;
    }

    private void addPointRect(int x, int y, Point point, int finishX, int finishY) {
        if(x >= 0 && y >= 0 && x < game.length && y < game[0].length) {
            if(!isPointInList(x, y, closedList) && !isPointInList(x, y, openList) && game[y][x] == 0)
                openList.add(new Point(x, y, 1, heuristicRect(x, y, finishX, finishY), point));
        }
    }

    private void addPointHex(int x, int y, Point point, int finishX, int finishY) {
        if(x >= 0 && y >= 0 && x < game.length && y < game[0].length) {
            if(!isPointInList(x, y, closedList) && !isPointInList(x, y, openList) && game[y][x] == 0)
                openList.add(new Point(x, y, 1, heuristicHex(x, y, finishX, finishY), point));
        }
    }

    private void addPoint(int x, int y, Point point) {
        if(x >= 0 && y >= 0 && x < game.length && y < game[0].length) {
            if(!isPointInList(x, y, closedList) && !isPointInList(x, y, openList) && game[y][x] == 0)
                openList.add(new Point(x, y, 1, 0, point));
        }
    }

    private boolean isPointInList(int x, int y, ArrayList<Point> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() == x && list.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    private int getShortestPath() {
        int best = openList.get(0).getTotalCost();

        for (int i = 1; i < openList.size(); i++) {
            if (openList.get(i).getTotalCost() < best) {
                best = openList.get(i).getTotalCost();
            }
        }

        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).getTotalCost() == best) {
                return i;
            }
        }
        return 0;
    }
}
