package android.chigoin.com.hotonepiece;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*该界面用于实现选择角色，并将选择结果传给主事件*/
public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button selectBt1=(Button) findViewById(R.id.choice_bt1);
        Button selectBt2=(Button) findViewById(R.id.choice_bt2);
        selectBt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mainActivity=new Intent(SelectActivity.this,MainActivity.class);
                SelectActivity.this.startActivity(mainActivity);
                SelectActivity.this.finish();
            }
        });
        selectBt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mainActivity=new Intent(SelectActivity.this,MainActivity.class);
                SelectActivity.this.startActivity(mainActivity);
                SelectActivity.this.finish();
            }
        });
    }
}
