package org.prajwalan.app.prajwalan.workshop;

import android.graphics.drawable.Drawable;

/**
 * Created by Moiz on 14/01/2016.
 */
public class WorkshopItem {

    public String workshop_id;
    public String workshop_name;
    public String workshop_desc;
    public Drawable workshop_image;

    public WorkshopItem(String workshop_id, String workshop_name, String workshop_desc, Drawable workshop_image) {
        this.workshop_id = workshop_id;
        this.workshop_name = workshop_name;
        this.workshop_desc = workshop_desc;
        this.workshop_image = workshop_image;
    }


    @Override
    public boolean equals(Object o) {
        WorkshopItem temp = (WorkshopItem) o;
        return  workshop_id.equalsIgnoreCase(temp.workshop_id);
    }

}
