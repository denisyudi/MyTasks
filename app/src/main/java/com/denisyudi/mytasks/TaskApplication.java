package com.denisyudi.mytasks;

import android.app.Application;

import com.denisyudi.mytasks.dao.TarefaDAO;

public class TaskApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TarefaDAO tarefaDAO = new TarefaDAO();
    }
}
