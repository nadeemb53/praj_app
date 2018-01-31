package org.prajwalan.app.prajwalan;

/**
 * Created by Nilesh1 on 08-01-2016.
 */
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;



class CustomPagerAdapter extends PagerAdapter {

    int[] mResource = {
            R.drawable.trailer,
            R.drawable.ola
    };

    Context mContext;
    private LayoutInflater mLayoutInflater;

    private int curPlayImg= 0;
    private ImageView imageView;
    private Handler advPageHandler = new Handler();

    public CustomPagerAdapter(Context context) {
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