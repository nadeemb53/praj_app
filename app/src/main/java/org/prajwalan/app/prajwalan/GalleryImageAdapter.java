package org.prajwalan.app.prajwalan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Nilesh1 on 31-12-2015.
 */
public class GalleryImageAdapter extends BaseAdapter
{
    private Context mContext;

    private Integer[] mImageIds = {
            /*R.drawable.natureimage1,
            R.drawable.natureimage2,
            R.drawable.natureimage3,
            R.drawable.natureimage4,
            R.drawable.natureimage5,
            R.drawable.natureimage6,
            R.drawable.natureimage7,
            R.drawable.natureimage8,
            R.drawable.natureimage9,
            R.drawable.natureimage10*/
            R.drawable.nb1, R.drawable.nb2, R.drawable.nb3, R.drawable.nb4, R.drawable.nb5, R.drawable.nb6, R.drawable.nb7,
            R.drawable.nb8, R.drawable.nb9, R.drawable.nb10, R.drawable.nb11, R.drawable.nb12, R.drawable.nb13, R.drawable.nb14
           // R.drawable.nb15, R.drawable.nb16, R.drawable.nb17, R.drawable.nb18, R.drawable.nb19, R.drawable.nb20, R.drawable.nb21,
            //R.drawable.nb22, R.drawable.nb23, R.drawable.nb24, R.drawable.nb25
    };

    public GalleryImageAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        // TODO Auto-generated method stub
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));

        i.setScaleType(ImageView.ScaleType.FIT_XY);

        return i;
    }
}
