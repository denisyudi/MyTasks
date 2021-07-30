package com.denisyudi.mytasks.dao;

import androidx.annotation.Nullable;

import com.denisyudi.mytasks.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private final static List<Tarefa> tarefas = new ArrayList<>();
    private static int contadorIds = 1;

    public void salva(Tarefa tarefa) {
        tarefa.setId(contadorIds);
        tarefas.add(tarefa);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Tarefa tarefa) {
        Tarefa tarefaEncontrada = buscaTarefaPeloId(tarefa);
        if (tarefaEncontrada != null) {
            int posicaoTarefa = tarefas.indexOf(tarefaEncontrada);
            tarefas.set(posicaoTarefa, tarefa);
        }
    }

    @Nullable
    private Tarefa buscaTarefaPeloId(Tarefa tarefa) {
        for (Tarefa t:
             tarefas) {
            if (t.getId() == tarefa.getId()){
                return t;
            }
        }
        return null;
    }

    public List<Tarefa> todos() {
        return new ArrayList<>(tarefas);
    }

    public void remove(Tarefa tarefa) {
        Tarefa tarefaDevolvida = buscaTarefaPeloId(tarefa);
        if(tarefaDevolvida != null) {
            tarefas.remove(tarefaDevolvida);
        }
    }
}
