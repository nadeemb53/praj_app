package org.prajwalan.app.prajwalan.handlers;

import android.content.Context;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.DBContacts;
import org.prajwalan.app.prajwalan.DB.DBRules;
import org.prajwalan.app.prajwalan.DB.User;
import org.prajwalan.app.prajwalan.DB.DBDownloads;
import org.prajwalan.app.prajwalan.DB.DBEvents;
import org.prajwalan.app.prajwalan.DB.DBUpdates;
import org.prajwalan.app.prajwalan.DB.DBWorkshops;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Moiz on 17/01/2016.
 */
public class JSONHandler {

    private Context context;

    public JSONHandler(Context context) {
        this.context = context;
    }

    public boolean getSuccess(String result) throws Exception {
        JSONObject resultObj = new JSONObject(result);
        String success = resultObj.getString("success");
        return success.equalsIgnoreCase("true");
    }

    public String getFailureReason(String result) throws Exception {
        JSONObject resultObj = new JSONObject(result);
        String failureReason = resultObj.getString("reason");
        return failureReason;
    }

    public User getUserData(String result) throws Exception {
        User u = new User();

        JSONObject resultObj = new JSONObject(result);
        JSONObject userData = resultObj.getJSONObject("userdata");

        u.fname = userData.getString("fname");
        u.lname = userData.getString("lname");
        u.email = userData.getString("email");
        u.mobile = userData.getString("mobile");
        u.college = userData.getString("college");

        return u;
    }

    public boolean parseNStore(String result) throws Exception {
        boolean done = false;

        JSONObject resultObj = new JSONObject(result);

        JSONArray eventsArray = resultObj.getJSONArray("events");
        boolean a = parseEvents(eventsArray);

        JSONArray rulesArray = resultObj.getJSONArray("rules");
        boolean b = parseRules(rulesArray);

        JSONArray downloadsArray = resultObj.getJSONArray("downloads");
        boolean c = parseDownloads(downloadsArray);

        JSONArray workshopsArray = resultObj.getJSONArray("workshops");
        boolean d = parseWorkshops(workshopsArray);

        JSONArray contactsArray = resultObj.getJSONArray("contacts");
        boolean e = parseContacts(contactsArray);

        JSONArray updates = resultObj.getJSONArray("updates");
        boolean f = parseUpdates(updates);

        if(a && b && c && d && e && f) {
            String date = resultObj.getString("date");
            done = parseDate(date);
        }

        return done;
    }

    boolean parseEvents(JSONArray events) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<events.length(); i++) {
                try {
                    JSONObject obj = events.getJSONObject(i);
                    DBEvents e = new DBEvents();
                    e.eventid = obj.getString("eventid");
                    e.eventname = obj.getString("eventname");
                    e.eventinfo = obj.getString("eventinfo");
                    e.eventfees = obj.getString("eventfees");
                    e.imageurl = obj.getString("imageurl");
                    db.createEventEntry(e);
                }
                catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    done = false;
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseRules(JSONArray rules) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<rules.length(); i++) {
                try {
                    JSONObject obj = rules.getJSONObject(i);
                    DBRules r = new DBRules();
                    r.tableid = obj.getString("tableid");
                    r.eventid = obj.getString("eventid");
                    r.rule = obj.getString("rule");
                    db.createRuleEntry(r);
                }
                catch (Exception e) {
                    done = false;
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseDownloads(JSONArray downloads) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<downloads.length(); i++) {
                try {
                    JSONObject obj = downloads.getJSONObject(i);
                    DBDownloads d = new DBDownloads();
                    d.tableid = obj.getString("tableid");
                    d.eventid = obj.getString("eventid");
                    d.description = obj.getString("description");
                    d.name = obj.getString("name");
                    d.url = obj.getString("url");
                    db.createDownloadEntry(d);
                }
                catch (Exception e) {
                    done = false;
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseWorkshops(JSONArray workshops) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<workshops.length(); i++) {
                try {
                    JSONObject obj = workshops.getJSONObject(i);
                    DBWorkshops workshop = new DBWorkshops();
                    workshop.workshopid = obj.getString("workshopid");
                    workshop.workshopname = obj.getString("workshopname");
                    workshop.workshopdesc = obj.getString("workshopdesc");
                    workshop.workshopduration = obj.getString("workshopduration");
                    workshop.workshopdate = obj.getString("workshopdate");
                    workshop.workshopvenue = obj.getString("workshopvenue");
                    workshop.workshopfees = obj.getString("workshopfees");
                    workshop.workshopinfo = obj.getString("workshopinfo");
                    workshop.workshopcontents = obj.getString("workshopcontents");
                    workshop.imageurl = obj.getString("imageurl");
                    workshop.workshopcontact = obj.getString("workshopcontact");
                    db.createWorkshopEntry(workshop);
                }
                catch (Exception e) {
                    done = false;
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseContacts(JSONArray contacts) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<contacts.length(); i++) {
                try {
                    JSONObject obj = contacts.getJSONObject(i);
                    DBContacts c = new DBContacts();
                    c.tableid = obj.getString("tableid");
                    c.committeeid = obj.getString("committeeid");
                    c.committeename = obj.getString("committeename");
                    c.name = obj.getString("name");
                    c.post = obj.getString("post");
                    c.mobno = obj.getString("mobno");
                    c.emailid = obj.getString("emailid");
                    db.createContactEntry(c);
                }
                catch (Exception e) {
                    done = false;
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseDate(String date) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();
            db.createDateEntry(date);
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

    boolean parseUpdates(JSONArray updates) {
        boolean done = true;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();

            for(int i=0; i<updates.length(); i++) {
                try {
                    JSONObject obj = updates.getJSONObject(i);
                    DBUpdates u = new DBUpdates();
                    u.tableid = obj.getString("tableid");
                    u.updates = obj.getString("updates");
                    db.createUpdateEntry(u);
                }
                catch (Exception e) {
                    done = false;
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            done = false;
        }
        return done;
    }

}
