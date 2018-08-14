package com.example.daniel.pruebatecnicagithub.model.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.daniel.pruebatecnicagithub.model.pojo.Repositorio;


@Database(entities = {Repositorio.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database-name";
    private static AppDatabase INSTANCE;

    public abstract RepositorioDao repositorioDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
