package android.chigoin.com.hotonepiece;

import android.widget.TextView;

/**
 * Created by chigoin on 2017/11/16.
 */
/*信息通知类*/
class InformationBox {
    private TextView textBox;
    public void setTextBox(TextView textBox){
        this.textBox=textBox;
    }
    public TextView getTextBox(){
        return textBox;
    }
    public void showInform(String inform){
        this.textBox.setText(inform);
    }
}
