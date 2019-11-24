package Game;

import Game.ActionGame;
import Game.Block;
import Game.Value;

import java.awt.*;

public class GameField {
    public int worldWidth = 12;
    public int worldHeight = 8;
    public int blockSize = 72;

    public Block[][] block;


    public GameField() {
        define();
    }

    public void define(){
        block = new Block[worldHeight][worldWidth];

        for(int y=0;y < block.length; y++){
            for(int x=0;x < block[0].length; x++){
                block[y][x] = new Block((ActionGame.myWidth/2)-((worldWidth*blockSize)/2) + (x * blockSize), y * blockSize, blockSize, blockSize, Value.groundGrass,Value.airAir);
            }
        }
    }
    public void physic(){
        for(int y=0; y<block.length; y++){
            for(int x=0; x<block[0].length; x++){
                block[y][x].physic();
            }
        }
    }

    public void draw(Graphics g){
        for(int y=0;y<block.length; y++){
            for(int x=0;x < block[0].length; x++){
                block[y][x].draw(g);
            }
        }
        for(int y=0;y<block.length; y++){
            for(int x=0;x < block[0].length; x++){
                block[y][x].fight(g);
            }
        }

    }
}

