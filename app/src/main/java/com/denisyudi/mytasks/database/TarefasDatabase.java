package com.denisyudi.mytasks.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.denisyudi.mytasks.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1, exportSchema = false)
public abstract class TarefasDatabase extends RoomDatabase {
}
