package android.chigoin.com.hotonepiece;

import android.content.Context;
import android.graphics.Color;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

/**
 * Created by chigoin on 2017/11/17.
 */
       /* 定义dice类用于实现摇骰子*/
public class Dice {
    RollPagerView diceRoll;

    public void setDiceRoll(RollPagerView diceRoll){
        this.diceRoll=diceRoll;
    }
    public RollPagerView getDiceRoll(){return diceRoll;}

    public int getRandom(){
        return this.diceRoll.getViewPager().getCurrentItem();
    }

    public void diceInit(Context activity){
        DiceAdapter adapter=new DiceAdapter();
        diceRoll.setPlayDelay(1000);
        //设置透明度
        diceRoll.setAnimationDurtion(600);
        //设置适配器
        diceRoll.setAdapter(adapter);
        diceRoll.setHintView(null);
    }
}
