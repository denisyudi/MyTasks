package com.denisyudi.mytasks.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.denisyudi.mytasks.dao.TarefaDAO;
import com.denisyudi.mytasks.model.Tarefa;
import com.denisyudi.mytasks.ui.adapter.ListaTarefasAdapter;

public class ListaTarefasView {

    private final ListaTarefasAdapter adapter;
    private final TarefaDAO dao;
    private Context context;


    public ListaTarefasView(Context context) {
        this.context = context;
        this.adapter = new ListaTarefasAdapter(this.context);
        dao = new TarefaDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context).setTitle("Removendo tarefa").setMessage("Tem certeza que quer remover a tarefa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Tarefa tarefaEscolhida = adapter.getItem(menuInfo.position);
                        remove(tarefaEscolhida);
                    }
                }).setNegativeButton("Não", null).show();
    }

    public void atualizaTarefa() {
        adapter.atualiza(dao.todos());
    }

    public void remove(Tarefa tarefaEscolhida) {
        dao.remove(tarefaEscolhida);
        adapter.remove(tarefaEscolhida);
    }

    public void configuraAdapter(ListView listaDeTarefas) {

        listaDeTarefas.setAdapter(adapter);
    }
}
