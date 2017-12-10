package android.chigoin.com.hotonepiece;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;
/*用于实现主界面的类*/
public class MainActivity extends AppCompatActivity {
   public int ptChoose=0;//用于选择模式,0：不处理，1：可以进行攻击，2：
   public static  List<Block> gameMap;
   private int hancock=0;
   public static InformationBox informationBox;
   private Dice dice;
   private int diceNumber;
    public static int blockNumber;
    private Game game;
    public static TextView textHp1;
    public static TextView textAm1;
    public static TextView textDe1;
    public static TextView textCu1;
    public static TextView textHp2;
    public static TextView textAm2;
    public static TextView textDe2;
    public static TextView textCu2;
    public static Boolean winCondition=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // CardActivity.stringNameList();
        game=new Game();
        gameinit();
        gameButtonInit();
        cardAttribute();
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textHp1=(TextView) findViewById(R.id.hp_text1);
        textDe1=(TextView) findViewById(R.id.dp_text1);
        textAm1=(TextView) findViewById(R.id.mp_text1);
        textCu1=(TextView) findViewById(R.id.cp_text1);
        textHp2=(TextView) findViewById(R.id.hp_text2);
        textDe2=(TextView) findViewById(R.id.dp_text2);
        textAm2=(TextView) findViewById(R.id.mp_text2);
        textCu2=(TextView) findViewById(R.id.cp_text2);
        Button attackButton=(Button) findViewById(R.id.attack_bt);
        Button moveButton=(Button) findViewById(R.id.move_bt);
        Button weaponButton=(Button) findViewById(R.id.weapon_bt);
        Button hallowsButton=(Button) findViewById(R.id.hallows_bt);
        Game.updateData();
        weaponButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Game.getCurrentBoat().getWeaponCount() > 0) {
                    if (Game.getCurrentBoat().getBoatId() == 0) {
                        if (Game.getCurrentBoat().getArmor() != 0) {
                            Toast.makeText(MainActivity.this, "你使用了武器:丈八蛇矛", Toast.LENGTH_LONG).show();
                            Game.getCurrentBoat().getWeapon1();
                            Game.getCurrentBoat().setWeaponCount(Game.getCurrentBoat().getWeaponCount() - 1);
                        } else {
                            Toast.makeText(MainActivity.this, "当前护甲值为0", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Game.changeID();
                        int i = Game.getCurrentBoat().getPos_in_the_sea();
                        Game.changeID();
                        if (i != 0) {
                            Toast.makeText(MainActivity.this, "你使用了武器:破甲弓", Toast.LENGTH_LONG).show();
                            Game.getCurrentBoat().getWeapon2();
                            Game.getCurrentBoat().setWeaponCount(Game.getCurrentBoat().getWeaponCount() - 1);
                        } else {
                            Toast.makeText(MainActivity.this, "当前敌人护甲值为0", Toast.LENGTH_LONG).show();
                        }
                    }
                    Game.updateData();
                }
            }
        });
        hallowsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(Game.getCurrentBoat().getHallowsCount()>0){
                    if(Game.getCurrentBoat().getBoatId()==0) {
                        Toast.makeText(MainActivity.this, "你使用了宝具:冥王", Toast.LENGTH_LONG).show();
                        Game.getCurrentBoat().getHallows1();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "你使用了宝具:海王", Toast.LENGTH_LONG).show();
                        Game.getCurrentBoat().getHallows2();
                    }

                    Game.getCurrentBoat().setHallowsCount(Game.getCurrentBoat().getHallowsCount()-1);
                    Game.checkBlood();

                }
                Game.updateData();
            }
        });
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (ptChoose){
                    case 0:
                        break;
                    case 1:
                        if(Game.getCurrentPlayer().getPid()==0) {
                            if (Game.getCurrentBoat().receiveAttack(Game.getCurrentBoat().makeBoatAttack(diceNumber))) {
                                Game.checkBlood();
                                hancock = 1;
                                Toast.makeText(MainActivity.this,"Boa Hancock发动了石化",Toast.LENGTH_SHORT).show();
                            }
                           Game.changeID();
                        }
                        else {
                            Game.getCurrentBoat().receiveAttack(Game.getCurrentBoat().makeBoatAttack(diceNumber));
                            Game.checkBlood();
                            if(0==hancock){
                                Game.changeID();
                            }
                            else
                                hancock=0;
                        ptChoose=0;}
                        break;
                    case 2:
                        informationBox.showInform("你当前只能移动。");
                        break;
                    default:
                }
             Game.updateData();
            }

        });
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (ptChoose){
                    case 0:
                        break;
                    case 1:
                        //if (Game.getCurrentPlayer().getPid()==0){
                            Game.getCurrentBoat().makeBoatMove(diceNumber);
                             int step=Boat.updateBoat();
                        Toast.makeText(MainActivity.this,"你获得了:"+gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getAttack()+"弹药,"+gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getDefence()+"防御.可以多走"+step+"格",Toast.LENGTH_SHORT).show();
                        Game.getCurrentBoat().makeBoatMove(step);
                        if (Game.getCurrentBoat().changeAddress()){
                            Game.changeID();
                            Game.getCurrentBoat().makeBoatMove(-step);
                            Game.changeID();

                        }
                        if(0==hancock){
                                    Game.changeID();
                                }
                                else
                                    hancock=0;
                        ptChoose=0;
                        if(winCondition){
                            endStart();
                        }
                        break;
                    case 2:
                        if (Game.getCurrentPlayer().getPid()==0){
                            Game.getCurrentBoat().makeBoatMove(diceNumber);
                           if (Game.getCurrentBoat().changeAddress()){
                                Game.getCurrentBoat().makeBoatMove(diceNumber);
                               Toast.makeText(MainActivity.this,"Monkey D.Luffy发动了技能橡胶果实",Toast.LENGTH_SHORT).show();
                                Game.changeID();
                                Game.getCurrentBoat().makeBoatMove(-diceNumber);
                                Game.changeID();
                           }
                            Game.changeID();
                        }
                        else {
                            Game.getCurrentBoat().makeBoatMove(diceNumber);
                            if (Game.getCurrentBoat().changeAddress()){
                                Game.changeID();
                                Game.getCurrentBoat().makeBoatMove(-diceNumber);
                                Game.changeID();
                                gameMap.get(Game.getCurrentBoat().getPos_in_the_sea()).getBlockButton().setBackgroundResource(R.drawable.d2);
                            }
                            if(0==hancock){
                                Game.changeID();

                            }
                            else
                                hancock=0;
                        }
                        ptChoose=0;
                        if(winCondition){
                            endStart();
                        }
                        break;
                    default:
                }
                Game.updateData();
            }
        });
        informationBox=new InformationBox();
        informationBox.setTextBox((TextView) findViewById(R.id.inform_text));
        dice=new Dice();
        dice.setDiceRoll((RollPagerView) findViewById(R.id.dice_roll_view));
        dice.diceInit(this);
        dice.getDiceRoll().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                diceNumber = dice.getRandom() + 1;
                if (Game.getCurrentBoat().getPos_in_the_sea()==0){
                    if (game.checkStart(diceNumber)) {
                        Game.getCurrentBoat().makeBoatMove(2);
                        ptChoose=0;
                    }
                    else {
                        informationBox.showInform("你当前无法启航");
                        Game.changeID();
                    }
                }
                else {
                    Game.changeID();
                    int t = Game.getCurrentBoat().getPos_in_the_sea();
                    Game.changeID();
                    if ((t - Game.getCurrentBoat().getPos_in_the_sea() < diceNumber) && (Game.getCurrentBoat().getPos_in_the_sea() - t < 0)) {
                        informationBox.showInform("你当前可以进行攻击也可以前进");
                        ptChoose = 1;
                    } else {
                        informationBox.showInform("你当前只可以前进");
                        ptChoose = 2;
                    }
                }
            }
        });

    }
  /* 初始化游戏*/
    public void  gameinit(){
        Map map=new Map();
        gameMap=map.initMap();
    }
 /* 该方法用于实现查看地图格属性*/
    public void cardAttribute() {
        for (blockNumber = 0; blockNumber < 53; blockNumber++) {
            gameMap.get(blockNumber).getBlockButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CardActivity.class);
                    intent.putExtra(CardActivity.ATTACK, gameMap.get(blockNumber).getAttack());
                    intent.putExtra(CardActivity.DEFEND, gameMap.get(blockNumber).getDefence());
                    intent.putExtra(CardActivity.CARD_IMAGE, gameMap.get(blockNumber).getImageId());
                    intent.putExtra(CardActivity.MOVE, gameMap.get(blockNumber).getMoveBlock());
                    startActivity(intent);
                }
            });
        }
    }
    /* 该方法是不得已而为之，需要将界面上的空间资源一个个找出来 */
    public void gameButtonInit(){
        gameMap.get(0).setBlockButton((Button) findViewById(R.id.block1));
        gameMap.get(0).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(0).getImageId()));
        gameMap.get(1).setBlockButton((Button) findViewById(R.id.block2));
        gameMap.get(1).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(1).getImageId()));
        gameMap.get(2).setBlockButton((Button) findViewById(R.id.block3));
        gameMap.get(2).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(2).getImageId()));
        gameMap.get(3).setBlockButton((Button) findViewById(R.id.block4));
        gameMap.get(3).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(3).getImageId()));
        gameMap.get(4).setBlockButton((Button) findViewById(R.id.block5));
        gameMap.get(4).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(4).getImageId()));
        gameMap.get(5).setBlockButton((Button) findViewById(R.id.block6));
        gameMap.get(5).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(5).getImageId()));
        gameMap.get(6).setBlockButton((Button) findViewById(R.id.block7));
        gameMap.get(6).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(6).getImageId()));
        gameMap.get(7).setBlockButton((Button) findViewById(R.id.block8));
        gameMap.get(7).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(7).getImageId()));
        gameMap.get(8).setBlockButton((Button) findViewById(R.id.block9));
        gameMap.get(8).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(8).getImageId()));
        gameMap.get(9).setBlockButton((Button) findViewById(R.id.block10));
        gameMap.get(9).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(9).getImageId()));
        gameMap.get(10).setBlockButton((Button) findViewById(R.id.block11));
        gameMap.get(10).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(10).getImageId()));
        gameMap.get(11).setBlockButton((Button) findViewById(R.id.block12));
        gameMap.get(11).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(11).getImageId()));
        gameMap.get(12).setBlockButton((Button) findViewById(R.id.block13));
        gameMap.get(12).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(12).getImageId()));
        gameMap.get(13).setBlockButton((Button) findViewById(R.id.block14));
        gameMap.get(13).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(13).getImageId()));
        gameMap.get(14).setBlockButton((Button) findViewById(R.id.block15));
        gameMap.get(14).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(14).getImageId()));
        gameMap.get(15).setBlockButton((Button) findViewById(R.id.block16));
        gameMap.get(15).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(15).getImageId()));
        gameMap.get(16).setBlockButton((Button) findViewById(R.id.block17));
        gameMap.get(16).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(16).getImageId()));
        gameMap.get(17).setBlockButton((Button) findViewById(R.id.block18));
        gameMap.get(17).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(17).getImageId()));
        gameMap.get(18).setBlockButton((Button) findViewById(R.id.block19));
        gameMap.get(18).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(18).getImageId()));
        gameMap.get(19).setBlockButton((Button) findViewById(R.id.block20));
        gameMap.get(19).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(19).getImageId()));
        gameMap.get(20).setBlockButton((Button) findViewById(R.id.block21));
        gameMap.get(20).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(20).getImageId()));
        gameMap.get(21).setBlockButton((Button) findViewById(R.id.block22));
        gameMap.get(21).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(21).getImageId()));
        gameMap.get(22).setBlockButton((Button) findViewById(R.id.block23));
        gameMap.get(22).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(22).getImageId()));
        gameMap.get(23).setBlockButton((Button) findViewById(R.id.block24));
        gameMap.get(23).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(23).getImageId()));
        gameMap.get(24).setBlockButton((Button) findViewById(R.id.block25));
        gameMap.get(24).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(24).getImageId()));
        gameMap.get(25).setBlockButton((Button) findViewById(R.id.block26));
        gameMap.get(25).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(25).getImageId()));
        gameMap.get(26).setBlockButton((Button) findViewById(R.id.block27));
        gameMap.get(26).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(26).getImageId()));
        gameMap.get(27).setBlockButton((Button) findViewById(R.id.block28));
        gameMap.get(27).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(27).getImageId()));
        gameMap.get(28).setBlockButton((Button) findViewById(R.id.block29));
        gameMap.get(28).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(28).getImageId()));
        gameMap.get(29).setBlockButton((Button) findViewById(R.id.block30));
        gameMap.get(29).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(29).getImageId()));
        gameMap.get(30).setBlockButton((Button) findViewById(R.id.block31));
        gameMap.get(30).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(30).getImageId()));
        gameMap.get(31).setBlockButton((Button) findViewById(R.id.block32));
        gameMap.get(31).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(31).getImageId()));
        gameMap.get(32).setBlockButton((Button) findViewById(R.id.block33));
        gameMap.get(32).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(32).getImageId()));
        gameMap.get(33).setBlockButton((Button) findViewById(R.id.block34));
        gameMap.get(33).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(33).getImageId()));
        gameMap.get(34).setBlockButton((Button) findViewById(R.id.block35));
        gameMap.get(34).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(34).getImageId()));
        gameMap.get(35).setBlockButton((Button) findViewById(R.id.block36));
        gameMap.get(35).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(35).getImageId()));
        gameMap.get(36).setBlockButton((Button) findViewById(R.id.block37));
        gameMap.get(36).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(36).getImageId()));
        gameMap.get(37).setBlockButton((Button) findViewById(R.id.block38));
        gameMap.get(37).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(37).getImageId()));
        gameMap.get(38).setBlockButton((Button) findViewById(R.id.block39));
        gameMap.get(38).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(38).getImageId()));
        gameMap.get(39).setBlockButton((Button) findViewById(R.id.block40));
        gameMap.get(39).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(39).getImageId()));
        gameMap.get(40).setBlockButton((Button) findViewById(R.id.block41));
        gameMap.get(40).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(40).getImageId()));
        gameMap.get(41).setBlockButton((Button) findViewById(R.id.block42));
        gameMap.get(41).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(41).getImageId()));
        gameMap.get(42).setBlockButton((Button) findViewById(R.id.block43));
        gameMap.get(42).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(42).getImageId()));
        gameMap.get(43).setBlockButton((Button) findViewById(R.id.block44));
        gameMap.get(43).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(43).getImageId()));
        gameMap.get(44).setBlockButton((Button) findViewById(R.id.block45));
        gameMap.get(44).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(44).getImageId()));
        gameMap.get(45).setBlockButton((Button) findViewById(R.id.block46));
        gameMap.get(45).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(45).getImageId()));
        gameMap.get(46).setBlockButton((Button) findViewById(R.id.block47));
        gameMap.get(46).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(46).getImageId()));
        gameMap.get(47).setBlockButton((Button) findViewById(R.id.block48));
        gameMap.get(47).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(47).getImageId()));
        gameMap.get(48).setBlockButton((Button) findViewById(R.id.block49));
        gameMap.get(48).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(48).getImageId()));
        gameMap.get(49).setBlockButton((Button) findViewById(R.id.block50));
        gameMap.get(49).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(49).getImageId()));
        gameMap.get(50).setBlockButton((Button) findViewById(R.id.block51));
        gameMap.get(50).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(50).getImageId()));
        gameMap.get(51).setBlockButton((Button) findViewById(R.id.block52));
        gameMap.get(51).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(51).getImageId()));
        gameMap.get(52).setBlockButton((Button) findViewById(R.id.block53));
        gameMap.get(52).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(52).getImageId()));
        gameMap.get(53).setBlockButton((Button) findViewById(R.id.block54));
        gameMap.get(53).getBlockButton().setBackgroundDrawable(getResources().getDrawable(gameMap.get(53).getImageId()));
    }

/*该方法用于启动结束界面*/
public void endStart(){

    Intent endActivity=new Intent(MainActivity.this,EndActivity.class);
    MainActivity.this.startActivity(endActivity);
    MainActivity.this.finish();
}

}
