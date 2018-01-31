package org.prajwalan.app.prajwalan.workshop;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.DB.DBWorkshops;
import org.prajwalan.app.prajwalan.R;

import java.util.List;

/**
 * Created by Moiz on 14/01/2016.
 */

public class WorkshopAdapter extends BaseAdapter {

    private List<WorkshopItem> workshopList;
    private LayoutInflater workshopLayoutInflator;
   // private Animation slideUp;
    private Context context;

    public WorkshopAdapter(Context c,List<WorkshopItem> workshopList, LayoutInflater workshopLayoutInflator) {
        this.context = c;
        this.workshopList = workshopList;
        this.workshopLayoutInflator = workshopLayoutInflator;
       // this.slideUp = AnimationUtils.loadAnimation(c,R.anim.slide_up);
    }

    @Override
    public int getCount() {
        return workshopList.size();
    }

    @Override
    public Object getItem(int position) {
        return workshopList.get(position);
    }

    public int getPositionForID(DBWorkshops workshop) {
        return workshopList.indexOf(workshop);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewWorkshopItem item;
        if(convertView==null){
            Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/sf.ttf");

            convertView = workshopLayoutInflator.inflate(R.layout.workshop_item,null);
            item = new ViewWorkshopItem();
            item.workshopImage = (ImageView)convertView.findViewById(R.id.workshop_image);
            item.workshopName = (TextView)convertView.findViewById(R.id.workshop_name);
            item.workshopDesc = (TextView)convertView.findViewById(R.id.workshop_desc);

            item.workshopName.setTypeface(face);
            item.workshopDesc.setTypeface(face);

            convertView.setTag(item);

        } else{
            item = (ViewWorkshopItem)convertView.getTag();
        }

        WorkshopItem workshopItem = workshopList.get(position);
        item.workshopName.setText(workshopItem.workshop_name);
        item.workshopDesc.setText(workshopItem.workshop_desc);
        item.workshopImage.setImageDrawable(workshopItem.workshop_image);

       // convertView.setAnimation(slideUp);
        return convertView;
    }

    private class ViewWorkshopItem {
        ImageView workshopImage;
        TextView workshopName;
        TextView workshopDesc;
    }
}
