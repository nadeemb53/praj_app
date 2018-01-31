package org.prajwalan.app.prajwalan.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.prajwalan.app.prajwalan.DB.DBContacts;
import org.prajwalan.app.prajwalan.DB.DBDownloads;
import org.prajwalan.app.prajwalan.DB.DBEvents;
import org.prajwalan.app.prajwalan.DB.DBRules;
import org.prajwalan.app.prajwalan.DB.DBWorkshops;
import org.prajwalan.app.prajwalan.DB.DBUpdates;

import java.sql.SQLException;

/**
 * Created by nadeem on 24-Jan-18.
 */
public class SQLiteDbHandler {
    private static final String DATABASE_NAME = "Prajwalan";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;

    public SQLiteDbHandler(Context c) {
        ourcontext = c;
    }

    public void createEventEntry(DBEvents event) {

        ourdatabase.delete("events","eventid = '" + event.eventid + "'",null);

        ContentValues cv = new ContentValues();
        cv.put("eventid",event.eventid);
        cv.put("eventname",event.eventname);
        cv.put("eventinfo",event.eventinfo);
        cv.put("eventfees", event.eventfees);
        cv.put("imageurl", event.imageurl);
        ourdatabase.insert("events",null,cv);
    }
    public void createContactEntry(DBContacts contact) {

        ourdatabase.delete("contacts","tableid = " + contact.tableid,null);

        ContentValues cv = new ContentValues();
        cv.put("tableid",contact.tableid);
        cv.put("committeeid", contact.committeeid);
        cv.put("committeename",contact.committeename);
        cv.put("post",contact.post);
        cv.put("name", contact.name);
        cv.put("mobno", contact.mobno);
        cv.put("emailid",contact.emailid);
        ourdatabase.insert("contacts", null, cv);
    }
    public void createRuleEntry(DBRules rule) {

        ourdatabase.delete("rules", "tableid = " + rule.tableid, null);

        ContentValues cv = new ContentValues();
        cv.put("tableid",rule.tableid);
        cv.put("eventid", rule.eventid);
        cv.put("rule", rule.rule);
        ourdatabase.insert("rules", null, cv);
    }
    public void createDateEntry(String lastdone) {

        ourdatabase.delete("lastmodified", null, null);

        ContentValues cv = new ContentValues();
        cv.put("lastdone", lastdone);
        ourdatabase.insert("lastmodified", null, cv);
    }
    public void createDownloadEntry(DBDownloads download) {

        ourdatabase.delete("downloads","tableid = " + download.tableid,null);

        ContentValues cv = new ContentValues();
        cv.put("tableid",download.tableid);
        cv.put("eventid", download.eventid);
        cv.put("description", download.description);
        cv.put("name", download.name);
        cv.put("url", download.url);
        ourdatabase.insert("downloads", null, cv);
    }
    public void createWorkshopEntry(DBWorkshops workshop) {

        ourdatabase.delete("workshops","workshopid = '" + workshop.workshopid + "'",null);

        ContentValues cv = new ContentValues();
        cv.put("workshopid",workshop.workshopid);
        cv.put("workshopname",workshop.workshopname);
        cv.put("workshopdesc",workshop.workshopdesc);
        cv.put("workshopduration", workshop.workshopduration);
        cv.put("workshopdate", workshop.workshopdate);
        cv.put("workshopvenue", workshop.workshopvenue);
        cv.put("workshopfees", workshop.workshopfees);
        cv.put("workshopinfo", workshop.workshopinfo);
        cv.put("workshopcontents", workshop.workshopcontents);
        cv.put("imageurl", workshop.imageurl);
        cv.put("workshopcontact", workshop.workshopcontact);
        ourdatabase.insert("workshops",null,cv);
    }
    public void createUpdateEntry(DBUpdates updates) {

        ourdatabase.delete("updates", "tableid = '" + updates.tableid + "'", null);

        ContentValues cv = new ContentValues();
        cv.put("tableid",updates.tableid);
        cv.put("updates",updates.updates);
        ourdatabase.insert("updates",null,cv);
    }

    public DBEvents getEventData(String eventid) {
        Cursor c = ourdatabase.rawQuery("select * from events where eventid = '" + eventid + "'", null);
        int eventname = c.getColumnIndex("eventname");
        int eventinfo = c.getColumnIndex("eventinfo");
        int eventfees = c.getColumnIndex("eventfees");
        int imageurl = c.getColumnIndex("imageurl");
        if(c.moveToFirst()) {
            DBEvents result = new DBEvents();
            result.eventid = eventid;
            result.eventinfo = c.getString(eventinfo);
            result.eventname = c.getString(eventname);
            result.eventfees = c.getString(eventfees);
            result.imageurl = c.getString(imageurl);
            return  result;
        }
        return null;
    }
    public DBContacts[] getContactData(String committeeid) {
        Cursor c = ourdatabase.rawQuery("select * from `contacts` where committeeid = '" + committeeid + "'", null);
        int committeename = c.getColumnIndex("committeename");
        int post = c.getColumnIndex("post");
        int name = c.getColumnIndex("name");
        int mobno = c.getColumnIndex("mobno");
        int emailid = c.getColumnIndex("emailid");
        int rowcount = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            rowcount++;
        }
        if(rowcount == 0) {
            return  null;
        }
        DBContacts results[] = new DBContacts[rowcount];
        int i=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            results[i] = new DBContacts();
            results[i].committeename = c.getString(committeename);
            results[i].post = c.getString(post);
            results[i].name = c.getString(name);
            results[i].mobno = c.getString(mobno);
            results[i].emailid = c.getString(emailid);
            i++;
        }
        return results;
    }
    public DBRules[] getRuleData(String eventid) {
        Cursor c = ourdatabase.rawQuery("select * from rules where eventid = '" + eventid + "'", null);
        int rule = c.getColumnIndex("rule");
        int rowcount = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            rowcount++;
        }
        if(rowcount == 0) {
            return  null;
        }
        DBRules[] results = new DBRules[rowcount];
        int i=0;

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            results[i] = new DBRules();
            results[i].rule = c.getString(rule);
            i++;
        }
        return results;
    }
    public DBDownloads[] getDownloadData(String eventid) {
        Cursor c = ourdatabase.rawQuery("select * from downloads where eventid = '" + eventid + "'", null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int desc = c.getColumnIndex("description");
        int url = c.getColumnIndex("url");
        int rowcount = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            rowcount++;
        }
        if(rowcount == 0) {
            return  null;
        }
        DBDownloads[] results = new DBDownloads[rowcount];
        int i=0;

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            results[i] = new DBDownloads();
            results[i].id = c.getString(id);
            results[i].name = c.getString(name);
            results[i].description = c.getString(desc);
            results[i].url = c.getString(url);
            i++;
        }
        return results;
    }
    public DBWorkshops[] getAllWorkshopData() {
        Cursor c = ourdatabase.rawQuery("select * from workshops", null);
        int workshopid = c.getColumnIndex("workshopid");
        int workshopname = c.getColumnIndex("workshopname");
        int workshopdesc = c.getColumnIndex("workshopdesc");
        int workshopduration = c.getColumnIndex("workshopduration");
        int workshopdate = c.getColumnIndex("workshopdate");
        int workshopvenue = c.getColumnIndex("workshopvenue");
        int workshopfees = c.getColumnIndex("workshopfees");
        int workshopinfo = c.getColumnIndex("workshopinfo");
        int workshopcontents = c.getColumnIndex("workshopcontents");
        int imageurl = c.getColumnIndex("imageurl");
        int workshopcontact = c.getColumnIndex("workshopcontact");
        Log.d("data : ",imageurl+"  "+workshopcontact);
        int rowcount = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            rowcount++;
        }

        if(rowcount == 0) {
            return  null;
        }

        DBWorkshops[] results = new DBWorkshops[rowcount];
        int i=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            results[i] = new DBWorkshops();
            results[i].workshopid = c.getString(workshopid);
            results[i].workshopname = c.getString(workshopname);
            results[i].workshopdesc = c.getString(workshopdesc);
            results[i].workshopduration = c.getString(workshopduration);
            results[i].workshopdate = c.getString(workshopdate);
            results[i].workshopvenue = c.getString(workshopvenue);
            results[i].workshopfees = c.getString(workshopfees);
            results[i].workshopinfo = c.getString(workshopinfo);
            results[i].workshopcontents = c.getString(workshopcontents);
            results[i].imageurl = c.getString(imageurl);
            results[i].workshopcontact = c.getString(workshopcontact);
            Log.d("data : ", results[i].workshopcontact);
            i++;
        }

        return results;
    }
    public DBWorkshops getWorkshopData(String workshopid) {
        Cursor c = ourdatabase.rawQuery("select * from workshops where workshopid = '" + workshopid + "'", null);
        int workshopname = c.getColumnIndex("workshopname");
        int workshopdesc = c.getColumnIndex("workshopdesc");
        int workshopduration = c.getColumnIndex("workshopduration");
        int workshopdate = c.getColumnIndex("workshopdate");
        int workshopvenue = c.getColumnIndex("workshopvenue");
        int workshopfees = c.getColumnIndex("workshopfees");
        int workshopinfo = c.getColumnIndex("workshopinfo");
        int workshopcontents = c.getColumnIndex("workshopcontents");
        int imageurl = c.getColumnIndex("imageurl");
        int workshopcontact = c.getColumnIndex("workshopcontact");

        DBWorkshops results = null;
        if(c.moveToFirst()) {
            results = new DBWorkshops();
            results.workshopid = workshopid;
            results.workshopname = c.getString(workshopname);
            results.workshopdesc = c.getString(workshopdesc);
            results.workshopduration = c.getString(workshopduration);
            results.workshopdate = c.getString(workshopdate);
            results.workshopvenue = c.getString(workshopvenue);
            results.workshopfees = c.getString(workshopfees);
            results.workshopinfo = c.getString(workshopinfo);
            results.workshopcontents = c.getString(workshopcontents);
            results.imageurl = c.getString(imageurl);
            results.workshopcontact = c.getString(workshopcontact);
        }
        return results;
    }
    public DBUpdates[] getAllUpdatesData() {
        Cursor c = ourdatabase.rawQuery("select * from updates", null);
        int updates = c.getColumnIndex("updates");
        int rowcount = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            rowcount++;
        }
        if(rowcount == 0) {
            return  null;
        }
        DBUpdates[] results = new DBUpdates[rowcount];
        int i=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            results[i] = new DBUpdates();
            results[i].updates = c.getString(updates);
            i++;
        }
        return results;
    }
    public String getDateData() {
        Cursor c = ourdatabase.rawQuery("select * from lastmodified", null);
        int lastdone = c.getColumnIndex("lastdone");
        if(c.moveToFirst()) {
            return  c.getString(lastdone);
        }
        return null;
    }

    public SQLiteDbHandler open() throws SQLException{
        ourhelper = new DbHelper(ourcontext);
        ourdatabase = ourhelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourhelper.close();
    }

    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS events(eventid varchar(20) PRIMARY KEY,eventname varchar(50) NOT NULL,eventinfo varchar(1000) NOT NULL,eventfees varchar(20) NOT NULL,imageurl varchar(500) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS rules(id int(5) PRIMARY KEY,tableid int(5) NOT NULL,eventid varchar(20) NOT NULL,rule varchar(1000) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS downloads ( id int(11) PRIMARY KEY,tableid int(5) NOT NULL,eventid varchar(20) NOT NULL,description varchar(1000) NOT NULL,name varchar(300) NOT NULL,url varchar(1000) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS workshops (id int(11) PRIMARY KEY,workshopid varchar(20) NOT NULL,workshopname varchar(100) NOT NULL,workshopdesc varchar(300) NOT NULL,workshopduration varchar(200) NOT NULL,workshopdate varchar(200) NOT NULL,workshopvenue varchar(200) NOT NULL,workshopfees varchar(500) NOT NULL,workshopinfo varchar(5000) NOT NULL,workshopcontents varchar(1000) NOT NULL,imageurl varchar(500) NOT NULL,workshopcontact varchar(200) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS contacts(id int(11) PRIMARY KEY,tableid int(5) NOT NULL,committeeid varchar(20) NOT NULL,committeename varchar(50) NOT NULL,post varchar(20) NOT NULL, name varchar(50) NOT NULL,mobno varchar(20) NOT NULL,emailid varchar(50) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS updates(id int(11) PRIMARY KEY,tableid int(5) NOT NULL,updates varchar(200) NOT NULL)");
            db.execSQL("CREATE TABLE IF NOT EXISTS lastmodified(lastdone date(50))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS events");
            db.execSQL("DROP TABLE IF EXISTS rules");
            db.execSQL("DROP TABLE IF EXISTS downloads");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            db.execSQL("DROP TABLE IF EXISTS lastmodified");
            onCreate(db);
        }
    }
}