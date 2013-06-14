package com.unibas.grigala.tetris;

import android.graphics.Point;

/**
 * Created by Grigala on 24/5/13.
 */
public class Tetris {
    public static final int SIZE = 3;
    public static boolean ghostEnabled = true;
    public int[][] sMap;
    public int[][] gMap;

    private Point pos;
    protected Point ghostPos;

    public int getGhostXPos(){
        return ghostPos.x;
    }

    public int getGhostYPos(){
        return ghostPos.y;
    }

    public Tetris(int x, int y){
        sMap = new int[SIZE][SIZE];
        gMap = new int[SIZE][SIZE];

        initTetris(SIZE);

        pos = new Point(x, y);
        ghostPos = new Point(x, y);
    }

    protected void initTetris(int tetrisSize){
        for (int col = 0; col < tetrisSize; col++){
            for(int row = 0; row < tetrisSize; row++){
                sMap[col][row] = TileView.BLOCK_EMPTY;
                gMap[col][row] = TileView.BLOCK_EMPTY;
            }
        }
    }

    protected void initGhost(){
        copyTetrisMap(sMap, gMap, Tetris.SIZE);
        ghostPos.set(pos.x, pos.y);
        setGhostY();
    }

    protected void resetGhost(int size){
        for(int col = 0; col < size; col++){
            for(int row = 0; row < size; row++){
                gMap[col][row] = TileView.BLOCK_EMPTY;
            }
        }
    }

    public boolean rotateTetris(TetrisMap map){
        int[][] temp = new int[SIZE][SIZE];
        for(int col = 0; col < SIZE; col++){
            for(int row = 0; row < SIZE; row++){
                temp[col][row] = sMap[row][2-col];
            }
        }

        if(!isColusionX(this.pos.x, temp, map) && !isColusionY(this.pos.y, this.pos.x, temp, map, false)){
            sMap = temp;
            resetGhost(SIZE);
            copyTetrisMap(temp, gMap, SIZE);
            setGhostY();
            return true;
        }
        else if(!isColusionX(this.pos.x-1, temp, map) && !isColusionY(this.pos.y, this.pos.x-1, temp, map, false)) {
            this.pos.x -=1;
            this.ghostPos.x -=1;
            sMap = temp;
            resetGhost(SIZE);
            copyTetrisMap(temp, gMap, SIZE);
            setGhostY();
            return true;
        }
        else if(!isColusionX(this.pos.x+1, temp, map) && !isColusionY(this.pos.y, this.pos.x+1, temp, map, false)) {
            this.pos.x +=1;
            this.ghostPos.x +=1;
            sMap = temp;
            resetGhost(SIZE);
            copyTetrisMap(temp, gMap, SIZE);
            setGhostY();
            return true;
        }

        return false;
    }
    /**
     * @return the pos
     */
    public Point getPos() {
        return pos;
    }

    public boolean setPos(int x, int y, TetrisMap map) {
        if(x >= 0 && x < TetrisMap.MAP_X_SIZE) {
            for(int col = 0; col < this.getSize(); col++){
                for(int row = 0; row < this.getSize(); row++) {
                    if (sMap[col][row] != TileView.BLOCK_EMPTY) {
                        if (x + col >= TetrisMap.MAP_X_SIZE || x + col < 0 ||
                                y + row >= TetrisMap.MAP_Y_SIZE ||
                                map.getMapValue(x + col, y + row) != TileView.BLOCK_EMPTY)
                            return false;
                    }
                }
            }
        }
        this.pos.x = x;
        this.pos.y = y;
        return true;
    }

    protected boolean isColusionY(int newY, int newX, int[][] tMap,TetrisMap map, boolean isGhost) {
        // TODO Auto-generated method stub
        if(newY < TetrisMap.MAP_Y_SIZE) {
            for(int col = 0; col < this.getSize(); col++){
                for(int row = 0; row < this.getSize(); row++) {
                    if (tMap[col][row] != TileView.BLOCK_EMPTY) {
                        if (isGhost) {//TODO need to think about if condition
                            if ((newX + col) >= 0 && (newX + col) < TetrisMap.MAP_X_SIZE) {
                                if (newY + row >= TetrisMap.MAP_Y_SIZE ||
                                        map.getMapValue(newX + col, newY + row) != TileView.BLOCK_EMPTY)
                                    return true;
                            }
                        }
                        else {
                            if ((newX + col) >= 0 && (newX + col) < TetrisMap.MAP_X_SIZE) {
                                if (newY + row >= TetrisMap.MAP_Y_SIZE ||
                                        map.getMapValue(newX + col, newY + row) != TileView.BLOCK_EMPTY)
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        else
            return true;
        //if no collisions
        return false;
    }
    /**
     * This function move Tetris down by 1
     * @param map - to check if possible
     * @return true is success else false
     */
    public boolean moveDown(TetrisMap map) {
        if(!isColusionY(this.pos.y+1, this.pos.x, sMap, map, false)) {
            this.pos.y++;
            return true;
        }
        else
            return false;
    }

    protected boolean isColusionX(int newX, int[][] tMap,TetrisMap map) {
        // TODO Auto-generated method stub
        if(newX >= -1 && newX < TetrisMap.MAP_X_SIZE) {
            for(int col = 0; col < this.getSize(); col++){
                for(int row = 0; row < this.getSize(); row++) {
                    if (tMap[col][row] != TileView.BLOCK_EMPTY) {
                        if (newX + col >= TetrisMap.MAP_X_SIZE || newX + col < 0 ||
                                map.getMapValue(newX + col, this.pos.y + row) != TileView.BLOCK_EMPTY)
                            return true;
                    }
                }
            }
        }
        else
            return true;
        //if no collisions
        return false;
    }


    public boolean moveLeft(TetrisMap map) {
        if(!isColusionX(this.pos.x-1, sMap, map)) {
            this.pos.x--;
            this.ghostPos.x--;
            setGhostY();
            return true;
        }
        return false;
    }

    public boolean moveRight(TetrisMap map) {
        if(!isColusionX(this.pos.x+1, sMap, map)) {
            this.pos.x++;
            this.ghostPos.x++;
            setGhostY();
            return true;
        }
        return false;
    }

    public void drop(TetrisMap map) {
        if(ghostEnabled)
            this.pos.y = this.ghostPos.y;
        else
            for (int y = 0; y < TetrisMap.MAP_Y_SIZE && !isColusionY(y, this.pos.x, sMap, map, false); y++)
                this.pos.y = y;
    }

    /**
     * @return the x position
     */
    public int getXPos() {
        return pos.x;
    }

    /**
     * @return the y position
     */
    public int getYPos() {
        return pos.y;
    }

    public int getSize() {
        // TODO Auto-generated method stub
        return SIZE;
    }

    protected void copyTetrisMap(int [][] srcMap, int[][] destMap, int size) {
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (srcMap[x][y] != TileView.BLOCK_EMPTY)
                    destMap[x][y] = TileView.BLOCK_GHOST;
            }
        }
    }

    protected void setGhostY() {
        for (ghostPos.y = this.pos.y;
             !isColusionY(this.ghostPos.y+1, this.ghostPos.x, gMap, MainMap.mapOld, true);
             this.ghostPos.y++);
    }

    public boolean onGhost() {
        if(pos.y == ghostPos.y)
            return true;
        return false;
    }


}
