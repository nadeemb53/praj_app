package org.prajwalan.app.prajwalan;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Nilesh1 on 11-01-2016.
 */

class TshirtAdapter extends android.support.v4.view.PagerAdapter {

    int[] mResource = {
            R.drawable.t1,
            R.drawable.t2,
           // R.drawable.t3


    };

    Context mContext;
    private LayoutInflater mLayoutInflater;

    private int curPlayImg= 0;
    private ImageView imageView;
    private Handler advPageHandler = new Handler();

    public TshirtAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResource.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        imageView = (ImageView) itemView.findViewById(R.id.pager_image);
        imageView.setImageResource(mResource[position]);



        container.addView(itemView,0);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}