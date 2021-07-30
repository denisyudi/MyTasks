package com.denisyudi.mytasks.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denisyudi.mytasks.R;
import com.denisyudi.mytasks.dao.TarefaDAO;
import com.denisyudi.mytasks.model.Tarefa;

import static com.denisyudi.mytasks.ui.activity.ConstantesActivites.CHAVE_TAREFA;

public class FormularioTarefaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVA_TAREFA = "Nova tarefa";
    private static final String TITULO_APPBAR_EDITA_TAREFA = "Edita tarefa";
    private EditText campoTitulo;
    private EditText campoDescricao;

    private final TarefaDAO dao = new TarefaDAO();

    private Tarefa tarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);

        inicializacaoCampos();

        carregaTarefa();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_tarefa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.activity_formulario_tarefa_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaTarefa() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_TAREFA)) {
            setTitle(TITULO_APPBAR_EDITA_TAREFA);
            tarefa = (Tarefa) dados.getSerializableExtra(CHAVE_TAREFA);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVA_TAREFA);
            tarefa = new Tarefa();
        }
    }

    private void preencheCampos() {
        campoTitulo.setText(tarefa.getTitulo());
        campoDescricao.setText(tarefa.getDescricao());
    }


    private void finalizaFormulario() {
        preencheTarefa();
        if (tarefa.idValido()) {
            dao.edita(tarefa);
        } else {
            dao.salva(tarefa);
        }
        finish();
    }

    private void inicializacaoCampos() {
        campoTitulo = findViewById(R.id.activity_formulario_tarefa_titulo);
        campoDescricao = findViewById(R.id.activity_formulario_tarefa_descricao);
    }

    private void preencheTarefa() {
        String titulo = campoTitulo.getText().toString();
        String descricao = campoDescricao.getText().toString();

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
    }
}