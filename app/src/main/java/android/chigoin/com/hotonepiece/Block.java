package android.chigoin.com.hotonepiece;

import android.widget.Button;

/**
 * Created by chigoin on 2017/11/15.
 */
/*该类为实体类，用于实现图格，且与button关联*/
public class Block {
   private int imageId;
   private Button blockButton;
   private int attack;
   private int defence;
   private int moveBlock;
   private boolean anotherChance;

    public  Block(int attack,int defence,int moveBlock,int imageId,boolean anotherChance){
       this.attack=attack;
       this.defence=defence;
       this.moveBlock=moveBlock;
       this.imageId=imageId;
       this.anotherChance=anotherChance;
   }

   public void setImageId(int imageId){this.imageId=imageId;}
   public void setBlockButton(Button blockButton){
       this.blockButton=blockButton;
   }
   public void setAttack(int attack){this.attack=attack;}
   public void setDefence(int defence) {this.defence=defence;}
   public void setMoveBlock(int moveBlock){this.moveBlock=moveBlock;}
   public void setAnotherChance(boolean anotherChance){
      this.anotherChance=anotherChance;
   }

   public int getAttack(){
       return attack;
   }
   public int getDefence(){return defence;}
   public int getMoveBlock(){
       return moveBlock;
   }
   public Button getBlockButton(){
       return blockButton;
   }
   public int getImageId(){return imageId;}
   public boolean getAnotherCHance(){return anotherChance;}

}
