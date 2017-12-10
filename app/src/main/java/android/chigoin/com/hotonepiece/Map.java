package android.chigoin.com.hotonepiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chigoin on 2017/11/15.
 */
/*该类通过与block类关联，实现地图格属性的赋值*/
public class Map {
    private List<Block> blockList=new ArrayList<>();
    private Block[] blocks=
            {new Block(0,0,0,R.drawable.direction_down,false)
                    ,new Block(2,0,0,R.drawable.direction_right,false),
                    new Block(0,1,0,R.drawable.direction_up,false),
                    new Block(0,0,2,R.drawable.direction_left,false),
                    new Block(0,0,1,R.drawable.direction_down,false)};

    public List<Block> initMap(){
        blockList.clear();
        Block[] mapBlocks=new Block[54];
        for(int i=0;i<54;i++){
            mapBlocks[i]=new Block(0,0,0,0,false);
        }
        for(int i=0;i<54;i++){
            if((i>=0&&i<=7)||(i>=18&&i<=25)||(i>=36&&i<=43)){
                mapBlocks[i].setImageId(R.drawable.direction_right);
            }
            if((i>=9&&i<=16)||(i>=27&&i<=34)||(i>=45&&i<=53)){
                mapBlocks[i].setImageId(R.drawable.direction_left);
            }
            if(8==i||17==i||26==i||35==i||44==i)
            {
                mapBlocks[i].setImageId(R.drawable.direction_down);
            }
            blockList.add(i,mapBlocks[i]);
        }
        int res=0;
        for(int i=1;i<54;i++){
            res=i%5;
           switch (res){
               case 0:
                   mapBlocks[i].setAttack(blocks[0].getAttack());
                   mapBlocks[i].setDefence(blocks[0].getDefence());
                   mapBlocks[i].setMoveBlock(blocks[0].getMoveBlock());
                   break;
               case 1:
                   mapBlocks[i].setAttack(blocks[1].getAttack());
                   mapBlocks[i].setDefence(blocks[1].getDefence());
                   mapBlocks[i].setMoveBlock(blocks[1].getMoveBlock());
                   break;
               case 2:
                   mapBlocks[i].setAttack(blocks[2].getAttack());
                   mapBlocks[i].setDefence(blocks[2].getDefence());
                   mapBlocks[i].setMoveBlock(blocks[2].getMoveBlock());
                   break;
               case 3:
                   mapBlocks[i].setAttack(blocks[3].getAttack());
                   mapBlocks[i].setDefence(blocks[3].getDefence());
                   mapBlocks[i].setMoveBlock(blocks[3].getMoveBlock());
                   break;
               case 4:
                   mapBlocks[i].setAttack(blocks[4].getAttack());
                   mapBlocks[i].setDefence(blocks[4].getDefence());
                   mapBlocks[i].setMoveBlock(blocks[4].getMoveBlock());
                   break;
               default:
           }
        }
        return blockList;
    }

}
