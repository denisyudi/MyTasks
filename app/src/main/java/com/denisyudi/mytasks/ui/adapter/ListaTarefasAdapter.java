package com.denisyudi.mytasks.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.denisyudi.mytasks.R;
import com.denisyudi.mytasks.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ListaTarefasAdapter extends BaseAdapter {

    private final List<Tarefa> tarefas = new ArrayList<>();
    private final Context context;

    public ListaTarefasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Tarefa getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Tarefa tarefa = tarefas.get(position);
        vincula(viewCriada, tarefa);
        return viewCriada;
    }

    private void vincula(View viewCriada, Tarefa tarefa) {
        TextView titulo = viewCriada.findViewById(R.id.item_tarefa_titulo);
        titulo.setText(tarefa.getTitulo());
        TextView descricao = viewCriada.findViewById(R.id.item_tarefa_descricao);
        descricao.setText(tarefa.getDescricao());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_tarefa, viewGroup, false);
    }


    public void atualiza(List<Tarefa> tarefas) {
        this.tarefas.clear();
        this.tarefas.addAll(tarefas);
        notifyDataSetChanged();
    }
    public void remove(Tarefa tarefaEscolhida) {
        tarefas.remove(tarefaEscolhida);
        notifyDataSetChanged();
    }
}
