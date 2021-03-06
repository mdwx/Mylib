package com.mylib;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import greendao.generator.DaoMaster;
import greendao.generator.DaoSession;

/**
 * Created by Vince on 2017/1/5.
 * E_mail :  xhys01@163.com
 * Description :
 */

public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    /*Singleton */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            synchronized (DaoSession.class) {
                if (daoSession == null) {
                    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "sqlite-db-encrypted" : "sqlite-db");
                    Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
                    daoSession = new DaoMaster(db).newSession();
                }
            }
        }
       return daoSession;
    }
}