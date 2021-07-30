package com.denisyudi.mytasks.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denisyudi.mytasks.R;
import com.denisyudi.mytasks.dao.TarefaDAO;
import com.denisyudi.mytasks.model.Tarefa;
import com.denisyudi.mytasks.ui.ListaTarefasView;
import com.denisyudi.mytasks.ui.adapter.ListaTarefasAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.denisyudi.mytasks.ui.activity.ConstantesActivites.CHAVE_TAREFA;

public class MainActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minhas Tarefas";


    private final TarefaDAO dao = new TarefaDAO();
    private ListaTarefasAdapter adapter;
    private final ListaTarefasView listaTarefasView = new ListaTarefasView(context);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);

        configuraFabNovaTarefa();
        configuraLista();



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_tarefas_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_tarefas_menu_remover) {
            listaTarefasView.confirmaRemocao(item);

        }
        return super.onContextItemSelected(item);
    }



    private void configuraFabNovaTarefa() {
        FloatingActionButton botaoNovaTarefa = findViewById(R.id.activity_main_fab_nova_tarefa);
        botaoNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioTarefaActivity();
            }
        });
    }

    private void abreFormularioTarefaActivity() {
        startActivity(new Intent(MainActivity.this, FormularioTarefaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaTarefasView.atualizaTarefa();

    }



    private void configuraLista() {
        ListView listaDeTarefas = findViewById(R.id.activity_main_lista_de_tarefas);
        listaTarefasView.configuraAdapter(listaDeTarefas);
        configuraListenerClickItem(listaDeTarefas);
        registerForContextMenu(listaDeTarefas);
    }



    private void configuraListenerClickItem(ListView listaDeTarefas) {
        listaDeTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Tarefa tarefaEscolhida = (Tarefa) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaTarefa(tarefaEscolhida);

            }
        });
    }

    private void abreFormularioModoEditaTarefa(Tarefa tarefa) {
        Intent toForm = new Intent(MainActivity.this, FormularioTarefaActivity.class);
        toForm.putExtra(CHAVE_TAREFA, tarefa);
        startActivity(toForm);
    }


}
