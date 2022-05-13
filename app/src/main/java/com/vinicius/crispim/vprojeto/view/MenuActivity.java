package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;

public class MenuActivity extends AppCompatActivity {
    private Button btnEnviarSolicitacao;
    private Button btnSuasHoras;
    private Button btnSugeAtividades;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        Intent intentreceptor = getIntent();
        Bundle parametros = intentreceptor.getExtras();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        aluno.setNome(parametros.getString("nome"));
        aluno.setSenha(parametros.getString("senha"));
        aluno.setCelular(parametros.getString("celular"));
        aluno.setCPF(parametros.getString("CPF"));
        aluno.setEmail(parametros.getString("email"));
        aluno.setMatricula(parametros.getInt("matricula"));
        aluno.setHorasFeitas(parametros.getInt("horasfeitas"));
        aluno.setHorasFaltando(parametros.getInt("horasfaltando"));
        curso.setHorasnecessarias(parametros.getInt("horasnecessariascurso"));
        curso.setId(parametros.getInt("idcurso"));
        curso.setNome(parametros.getString("nomecurso"));
        aluno.setCurso(curso);
        btnSair = findViewById(R.id.btnSair);
        btnSugeAtividades = findViewById(R.id.btnSugeAtividades);
        btnSuasHoras = findViewById(R.id.btnSuasHoras);
        btnEnviarSolicitacao = findViewById(R.id.btnEnviarSolicitacao);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(AppUtil.TAG, "onClick: Usuario saindo: "+aluno+" --- CURSO: "+aluno.getCurso().getNome());
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });btnEnviarSolicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(AppUtil.TAG, "onClick:Tela Enviar: "+aluno+" --- CURSO: "+aluno.getNome());
                parametros.putString("nome",aluno.getNome());
                parametros.putString("senha",aluno.getSenha());
                parametros.putString("celular",aluno.getCelular());
                parametros.putString("CPF",aluno.getCPF());
                parametros.putString("email",aluno.getEmail());
                parametros.putInt("matricula",aluno.getMatricula());
                parametros.putInt("horasfeitas",aluno.getHorasFeitas());
                parametros.putInt("horasfaltando",aluno.getHorasFaltando());
                parametros.putString("nomecurso",aluno.getCurso().getNome());
                parametros.putInt("idcurso",aluno.getCurso().getId());
                parametros.putInt("horasnecessariascurso",aluno.getCurso().getHorasnecessarias());
                Intent intent = new Intent(MenuActivity.this,SolicitacaoActivity.class);
                intent.putExtras(parametros);
                startActivity(intent);
            }
        });


    }
}