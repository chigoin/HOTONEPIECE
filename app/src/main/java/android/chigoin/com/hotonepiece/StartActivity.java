package android.chigoin.com.hotonepiece;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*该界面已被废弃*/
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button startButton=(Button) findViewById(R.id.start_bt);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
              /*  Intent anotherActivity=new Intent(StartActivity.this,SelectActivity.class);
                StartActivity.this.startActivity(anotherActivity);
                StartActivity.this.finish();*/
            }

        });

    }
}
