package com.unibas.grigala.tetris;

/**
 * Created by Grigala on 6/11/12.
 */

public class ITetris extends Tetris{
    private static final int BLOCK_TYPE = TileView.BLOCK_BLUE;
    public static final int I_SIZE = 4;
    public ITetris(int x, int y) {
        super(x, y);
        initTetris();
        if(ghostEnabled)
            initGhost();
    }

    private void initTetris() {
        sMap = new int[I_SIZE][I_SIZE];
        gMap = new int[I_SIZE][I_SIZE];
        for(int col = 0; col < I_SIZE; col++) {
            for(int row = 0; row < I_SIZE; row++) {
                sMap[col][row] = 0;
                gMap[col][row] = 0;
            }
        }
        this.sMap[0][0] = 0;
        this.sMap[0][1] = 0;
        this.sMap[0][2] = 0;
        this.sMap[0][3] = 0;
        this.sMap[1][0] = BLOCK_TYPE;
        this.sMap[1][1] = BLOCK_TYPE;
        this.sMap[1][2] = BLOCK_TYPE;
        this.sMap[1][3] = BLOCK_TYPE;
        this.sMap[2][0] = 0;
        this.sMap[2][1] = 0;
        this.sMap[2][2] = 0;
        this.sMap[2][3] = 0;
        this.sMap[3][0] = 0;
        this.sMap[3][1] = 0;
        this.sMap[3][2] = 0;
        this.sMap[3][3] = 0;
    }

    @Override
    protected boolean isColusionX(int newX, int[][] tMap,TetrisMap map) {

        if(newX >= -2 && newX < TetrisMap.MAP_X_SIZE) {
            for(int col = 0; col < this.getSize(); col++){
                for(int row = 0; row < this.getSize(); row++) {
                    if (tMap[col][row] != TileView.BLOCK_EMPTY) {
                        if (newX + col >= TetrisMap.MAP_X_SIZE || newX + col < 0 ||
                                map.getMapValue(newX + col, getYPos() + row) != TileView.BLOCK_EMPTY)
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
    @Override
    protected void initGhost() {
        copyTetrisMap(sMap, gMap, I_SIZE);
        ghostPos.set(getXPos(), getYPos());
        setGhostY();
    }
    @Override
    public boolean rotateTetris(TetrisMap map) {
        int[][] temp = new int[I_SIZE][I_SIZE];
        for(int col = 0; col < I_SIZE; col++){
            for(int row = 0; row < I_SIZE; row++) {
                temp[col][row] = sMap[row][3-col];
            }
        }
        if(!isColusionX(getXPos(), temp, map) && !isColusionY(getYPos(), getXPos(), temp, map, false)) {
            sMap = temp;
            resetGhost(I_SIZE);
            copyTetrisMap(temp, gMap, I_SIZE);
            setGhostY();
            return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return I_SIZE;
    }
}
