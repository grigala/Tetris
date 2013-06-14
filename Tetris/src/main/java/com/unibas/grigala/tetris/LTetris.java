package com.unibas.grigala.tetris;

/**
 * Created by Grigala on 6/11/12.
 */

public class LTetris extends Tetris {
    private static final int BLOCK_TYPE = TileView.BLOCK_ORANGE;
    public LTetris(int x, int y) {
        super(x, y);
        initTetris();
        if(ghostEnabled)
            initGhost();
    }

    private void initTetris() {
        this.sMap[0][0] = BLOCK_TYPE;
        this.sMap[0][1] = BLOCK_TYPE;
        this.sMap[0][2] = BLOCK_TYPE;
        this.sMap[1][0] = 0;
        this.sMap[1][1] = 0;
        this.sMap[1][2] = BLOCK_TYPE;
        this.sMap[2][0] = 0;
        this.sMap[2][1] = 0;
        this.sMap[2][2] = 0;
    }

}
