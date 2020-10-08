package com.mvfollower.moviesfollower.databases;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FavoritesObjectEntity.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class, ArrayListConvertor.class})
public abstract class FavoritesAppDatabase extends RoomDatabase {

    private static final String LOG_TAG = FavoritesAppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "LocalDatabase";
    private static FavoritesAppDatabase sInstance;

    public static FavoritesAppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FavoritesAppDatabase.class,
                        FavoritesAppDatabase.DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        // From version 1 to version 2
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Remove the table
            database.execSQL("DROP TABLE IF EXISTS favorites");
        }
    };

    public abstract FavoritesDao favoritesDao();
}
