package com.landtanin.teacherattendancemonitoring;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.landtanin.teacherattendancemonitoring.manager.Contextor;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public class MainApplication extends Application {

    RealmConfiguration config;
    private final String DATABASE_NAME="landtanin.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Contextor.getInstance().init(getApplicationContext());

        try {
            config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .schemaVersion(0)
                    .rxFactory(new RealmObservableFactory())
                    .build();
            Realm.setDefaultConfiguration(config);
        } catch (RealmMigrationNeededException rme) {
            config = new RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(config);

        } catch (Exception e) {
            Realm.init(this);
            config = new RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(config);
            Log.e("initListener: " + e.getMessage(),e.toString());
        }
        finally {
            Realm.init(this);
            Realm.getDefaultInstance().setAutoRefresh(true);
            Log.e("TaninTest: ", Realm.getDefaultInstance().getPath() );
        }


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
