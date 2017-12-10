package android.chigoin.com.hotonepiece;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by chigoin on 2017/11/17.
 */

/*将dice类和该类绑定，实现界面效果*/
public class DiceAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
