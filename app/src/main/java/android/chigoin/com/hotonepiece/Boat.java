package android.chigoin.com.hotonepiece;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by chigoin on 2017/12/4.
 */


/**
 * Created by 李进熙 on 2017/11/20.
 */
/*该类为实体类，实现boat*/
public class Boat {
    /** 船在海中的位置 */
    private int pos_in_the_sea;
    /**船的血量*/
    private int BloodValue;
    /**船的护甲值*/
    private int Armor;
    /**船的弹药*/
    private int Ammunition;
    /**船的id*/
    private int BoatId;

    private  String name;

    private  int imgId;

    private int hallowsCount;

    private int weaponCount;

    /**
     小船构造函数
     */
    public Boat (int bloodValue, int armor, int ammunition, int boatId,String str ,int imgId,int position,int weaponCount,int hallowsCount){
        setPos_in_the_sea(position);
        setAmmunition(ammunition);
        setArmor(armor);
        setImgId(imgId);
        setBloodValue(bloodValue);
        setBoatId(boatId);
        setHallowsCount(hallowsCount);
        setWeaponCount(weaponCount);
    }

    /**
     * 使船在海中前进，更新船在海中的位置和在船在图形界面上的位置
     * step 船在海中前进的步数
     */

    public void setBoatId(int boatId) {
        this.BoatId = boatId;
    }
    public int  getBoatId(){return BoatId;}
    public void setHallowsCount(int hallowsCount){
        this.hallowsCount=hallowsCount;
    }
    public int getHallowsCount(){
        return hallowsCount;
    }
    public void setWeaponCount(int weaponCount){
        this.weaponCount=weaponCount;
    }
    public int getWeaponCount(){
        return weaponCount;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public int getImgId(){return imgId;}

    public int getAmmunition() {
        return Ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.Ammunition = ammunition;
    }


    public int getBloodValue() {
        return BloodValue;
    }

    public void setBloodValue(int bloodValue) {
        this.BloodValue = bloodValue;
    }

    public int getArmor() {
        return Armor;
    }

    public void setArmor(int armor) {
        this.Armor = armor;
    }

    public int getPos_in_the_sea() {
        return pos_in_the_sea;
    }

    public void setPos_in_the_sea(int pos_in_the_sea) {
        this.pos_in_the_sea = pos_in_the_sea;
    }
/*定义该方法用于控制床的前进*/
    public  void makeBoatMove(int step) {
        if (Game.checkWin(step) == 2) {
            if (Game.getCurrentPlayer().getPid() == 0) {
                if (step > 0) {
                    for (int i = 0; i < step; i++) {
                        if (Game.getCurrentBoat().changeAddress()) {
                            MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);
                        } else {
                            MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getImageId());
                        }

                        Game.getCurrentBoat().setPos_in_the_sea(Game.getCurrentBoat().getPos_in_the_sea() + 1);
                        MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d1);
                    }

                } else {
                    int position = Game.getCurrentBoat().getPos_in_the_sea();
                    MainActivity.gameMap.get(position).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(position).getImageId());
                    position = position + step;
                    Game.getCurrentBoat().setPos_in_the_sea(position);
                    MainActivity.gameMap.get(position).getBlockButton().setBackgroundResource(R.drawable.d1);
                }

            } else {
                if (Game.checkWin(step) == 2) {
                    if (step > 0) {
                        for (int i = 0; i < step; i++) {
                            if (Game.getCurrentBoat().changeAddress()) {
                                MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d1);
                            } else {
                                MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getImageId());
                            }
                            Game.getCurrentBoat().setPos_in_the_sea(Game.getCurrentBoat().getPos_in_the_sea() + 1);
                            MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);

                        }
                    } else {
                        int position = Game.getCurrentBoat().getPos_in_the_sea();
                        MainActivity.gameMap.get(position).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(position).getImageId());
                        position = position + step;
                        Game.getCurrentBoat().setPos_in_the_sea(position);
                        MainActivity.gameMap.get(position).getBlockButton().setBackgroundResource(R.drawable.d2);
                    }
                }
            }
        }

    }
  /* 定义该函数实现船的攻击效果*/
    public  int makeBoatAttack(int diceNumber){
        int attacking;
        Game.changeID();
        int t = Game.getCurrentBoat().getPos_in_the_sea();
        Game.changeID();
        if(t-Game.getCurrentBoat().getPos_in_the_sea()<diceNumber&&Game.getCurrentBoat().getPos_in_the_sea()-t<0) {
            attacking =Game.getCurrentBoat().getAmmunition();
            Game.getCurrentBoat().setAmmunition(0);
            //Game.updateData();
            return attacking;
        }
            return 0;
        }

/*定义该方法来使传收到伤害*/
    public Boolean receiveAttack(int attack){
          Game.changeID();
        if (Game.getCurrentBoat().getArmor() >= attack) {
            Game.getCurrentBoat().setArmor(Game.getCurrentBoat().getArmor() - attack);
            Game.changeID();
            MainActivity.informationBox.showInform("护甲被损耗");
            //Game.updateData();
            return false;
        }//有护甲就消耗护甲
        else {

            Game.getCurrentBoat().setBloodValue(Game.getCurrentBoat().getArmor() + Game.getCurrentBoat().getBloodValue() - attack);
            Game.getCurrentBoat().setArmor(0);
            //若护甲不够用，直接消耗生命值
            //MainActivity.informationBox.showInform("555555");
            Game.changeID();
            MainActivity.informationBox.showInform("生命被损耗");
            //Game.updateData();
            return true;
        }
    }
/*用于更新船的的位置*/
    public static int updateBoat(){
        Game.getCurrentBoat().setArmor(Game.getCurrentBoat().getAmmunition()+MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getAttack());
        Game.getCurrentBoat().setAmmunition(Game.getCurrentBoat().getAmmunition()+MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getDefence());
        return MainActivity.gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getMoveBlock();
    }
/*判断重合的函数，如果船的位置重合，就换位置*/
    public Boolean changeAddress(){
        int k = Game.getCurrentBoat().getPos_in_the_sea();
        Game.changeID();
        int i = Game.getCurrentBoat().getPos_in_the_sea();
        Game.changeID();
        if(k==i){
            MainActivity.informationBox.showInform("击退敌人的船");
           /* Game.changeID();
            Game.getCurrentBoat().setPos_in_the_sea(k);*/
            return true;
        }
        else
            return false;
    }


/*定义船的武器系统*/
    public  void  getWeapon1(){
        Game.getCurrentBoat().setAmmunition(Game.getCurrentBoat().getArmor()+Game.getCurrentBoat().getAmmunition());
        Game.getCurrentBoat().setArmor(0);
    }

    public  void getWeapon2(){
        Game.changeID();
        int t = Game.getCurrentBoat().getArmor();
        Game.changeID();
        Game.getCurrentBoat().setAmmunition(Game.getCurrentBoat().getAmmunition()+t);
    }

/*定义船的宝具系统*/
    public void getHallows1(){
        int t = (int)(Math.random()* 8+ 4);
        Game.getCurrentBoat().receiveAttack(t);

    }

    public  void  getHallows2(){
        int t = (int)(Math.random()*8+5);
        Game.getCurrentBoat().makeBoatMove(t);

    }





}
