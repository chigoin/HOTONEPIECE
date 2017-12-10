package android.chigoin.com.hotonepiece;

/**
 * Created by chigoin on 2017/12/4.
 */

/**
 * Created by 李进熙 on 2017/12/4.
 */
//该类主要用于存放各类函数，简化主函数。
public class Game {
   private static int  Id = 0;private static Player player = new Player("Monkey D.Luffy",0);
   private static Player player1 = new Player("Boa Hancock",1);
   private static Boat boat2 = new Boat(5,5,5,1,"Liaoning warship",R.drawable.d1,0,2,1);
   private static Boat boat1 = new Boat(5,5,5,0,"T HOUSANDS SUNNY",R.drawable.d2,0,2,1);

//该函数用于得到当前船的id
    public static Boat getCurrentBoat(){
        if(Id==0){
            return boat1;
        }
        if(Id==1){
            return  boat2;
        }
        return  boat1;
    }
//得到当前的玩家id
    public static Player getCurrentPlayer(){
        if(Id==0){
            return  player;
        }
        if (Id==1){
            return  player1;
        }
        return  player;
    }
  //用于切换玩家id
    public static void changeID(){
        Id=(Id-1)*(Id-1);
    }

    //检查玩家的血量是否符合继续游戏的条件
    public static void checkBlood(){
        changeID();
        if (getCurrentBoat().getBloodValue()<=0){
            getCurrentBoat().setBloodValue(3);
            MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource( MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getImageId());
            getCurrentBoat().setPos_in_the_sea(0);
            //Game.updateData();
            MainActivity.informationBox.showInform("回到原点");
            if (getCurrentPlayer().getPid()==0){

                MainActivity.gameMap.get(0).getBlockButton().setBackgroundResource(R.drawable.d1);
            }
            else {
                MainActivity.gameMap.get(0).getBlockButton().setBackgroundResource(R.drawable.d2);
            }

        }
        changeID();
    }

    //检查玩家是否位于起点处
    public boolean checkStart(int diceRandom){

        if(diceRandom>3){
            return true;
        }
        return false;
    }

//检查胜利条件
    public static int checkWin(int diceRandom){
        if(Game.getCurrentBoat().getPos_in_the_sea()+diceRandom>53){

            MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getImageId());
            getCurrentBoat().setPos_in_the_sea(53-(Game.getCurrentBoat().getPos_in_the_sea()+diceRandom-53));
            int k = Game.getCurrentBoat().getPos_in_the_sea();
            Game.changeID();
            int i = Game.getCurrentBoat().getPos_in_the_sea();
            Game.changeID();
            if(k==i) {
                Game.changeID();
                MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getImageId());
                Game.getCurrentBoat().setPos_in_the_sea(k-1);
                if (getCurrentPlayer().getPid() == 0) {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d1);
                } else {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);
                }
                changeID();
                if (getCurrentPlayer().getPid() == 0) {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d1);
                } else {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);
                }


            }
            else {
                if (getCurrentPlayer().getPid() == 0) {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d1);
                } else {
                    MainActivity.gameMap.get(getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);
                }

                return 1;
            }
            MainActivity.informationBox.showInform("越过终点后退回");
        }
        else {
            if(Game.getCurrentBoat().getPos_in_the_sea()+diceRandom<53){
                return 2;
            }
            if (Game.getCurrentBoat().getPos_in_the_sea()+diceRandom==53) {
                MainActivity.informationBox.showInform("你找到了海贼王的宝藏");
                MainActivity.winCondition=true;
            }
        }
        return 0;
    }
//实时更新玩家的各种信息
    public static void updateData() {
        if (getCurrentBoat().getBoatId() == 0) {
            MainActivity.textHp1.setText(String.valueOf(getCurrentBoat().getBloodValue()));
            MainActivity.textDe1.setText(String.valueOf(getCurrentBoat().getArmor()));
            MainActivity.textAm1.setText(String.valueOf(getCurrentBoat().getAmmunition()));
            MainActivity.textCu1.setText("yes");
            MainActivity.textCu2.setText("no");
            changeID();
            MainActivity.textHp2.setText(String.valueOf(getCurrentBoat().getBloodValue()));
            MainActivity.textDe2.setText(String.valueOf(getCurrentBoat().getArmor()));
            MainActivity.textAm2.setText(String.valueOf(getCurrentBoat().getAmmunition()));
            changeID();

        }
        else{
            MainActivity.textHp2.setText(String.valueOf(getCurrentBoat().getBloodValue()));
            MainActivity.textDe2.setText(String.valueOf(getCurrentBoat().getArmor()));
            MainActivity.textAm2.setText(String.valueOf(getCurrentBoat().getAmmunition()));
            MainActivity.textCu1.setText("no");
            MainActivity.textCu2.setText("yes");
            changeID();
            MainActivity.textHp1.setText(String.valueOf(getCurrentBoat().getBloodValue()));
            MainActivity.textDe1.setText(String.valueOf(getCurrentBoat().getArmor()));
            MainActivity.textAm1.setText(String.valueOf(getCurrentBoat().getAmmunition()));
            changeID();
        }
    }

}





