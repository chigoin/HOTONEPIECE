package android.chigoin.com.hotonepiece;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
/*该类实现了一个界面，用于查看卡片的属性*/
public class CardActivity extends AppCompatActivity {
    public static final String CARD_IMAGE="card_image";
    public static final String ATTACK="attack";
    public static final String DEFEND="defend";
    public static final String MOVE="move";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent=getIntent();
        int cardId=intent.getIntExtra(CARD_IMAGE,0);
        int attack=intent.getIntExtra(ATTACK,0);
        int defend=intent.getIntExtra(DEFEND,0);
        int move=intent.getIntExtra(MOVE,0);
        ImageView cardImageView=(ImageView) findViewById(R.id.card_image);
        TextView cardTextView=(TextView) findViewById(R.id.attribute_text);
        Glide.with(this).load(cardId).into(cardImageView);
        cardTextView.setText("欢迎来到此block:\n"+"攻击:"+attack+"\n护甲:"+defend+"\n额外增加移动数:"+move);
    }
}
