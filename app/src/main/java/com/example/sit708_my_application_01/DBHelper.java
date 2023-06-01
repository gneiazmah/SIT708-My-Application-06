package com.example.sit708_my_application_01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context con;

    private SQLiteDatabase db;

    public DBHelper(Context con){
        super(con, "DB_ITUBE", null, 1);
        this.con=con;
    }

    public DBHelper OpenDB(){
        DBConnector dbCon=new DBConnector(con);
        db=dbCon.getWritableDatabase();
        return this;
    }

    public boolean CreateNewUser(UserClass userClass){
        try
        {
            db.execSQL("insert into userInfo values('"+userClass.getUserId()+"','"+userClass.getFullName()+"','"+userClass.getPassword()+"')");
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<UserClass> ValidLogin(String UserId, String Password)
    {
        ArrayList<UserClass> userList=new ArrayList<UserClass>();
        try
        {
            Cursor cursor=db.rawQuery("select * from userInfo where UserID='"+UserId+"'and Password='"+Password+"'",null);
            if(cursor.moveToFirst())
            {
                do{
                    UserClass user=new UserClass();
                    user.setUserId(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    userList.add(user);

                }while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  userList;
    }


    public boolean CreateNewPlayListItem(PlayList playList){
        try
        {
            db.execSQL("insert into PlayList values('"+playList.getID()+"','"+playList.getName()+"')");
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public Cursor SearchAllPlayLists()
    {
        Cursor cursor=null;
        try {
            cursor=db.rawQuery("Select Name from PlayList ", null);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return cursor;
    }

    public ArrayList<PlayList> SearchPlayItem(Integer ID)
    {
        ArrayList<PlayList> myPlayList = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("Select * from PlayList where ID='" + ID + "' ", null);
            if (cursor.moveToFirst()) {
                do {
                    PlayList playList = new PlayList();
                    playList.setID(cursor.getInt(0));
                    playList.setName(cursor.getString(1));

                    myPlayList.add(playList);

                } while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return myPlayList;
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
