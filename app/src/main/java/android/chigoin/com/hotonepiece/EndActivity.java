package android.chigoin.com.hotonepiece;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*该类为结束界面类*/
public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Button button=(Button) findViewById(R.id.end_bt);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EndActivity.this.finish();
            }
        });
    }
}
