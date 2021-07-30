package com.denisyudi.mytasks.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String titulo;
    private String descricao;

    @Ignore
    public Tarefa(String titulo, String descricao) {

        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Tarefa() {

    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo + " - " + descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean idValido() {
        return id > 0;
    }
}
