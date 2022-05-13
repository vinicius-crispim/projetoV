package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.CategoriaController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Curso;

public class SplashActivity extends AppCompatActivity {
    CursoController cursoController;
    CategoriaController categoriaController;
    AlunoController alunoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        cursoController = new CursoController(getApplicationContext());
        categoriaController = new CategoriaController(getApplicationContext());
        alunoController = new AlunoController(getApplicationContext());
        trocartela();
    }
    private void trocartela() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Curso curso = new Curso();
                curso.setNome("Analise e Desenvolvimento de Sistemas");
                curso.setHorasnecessarias(150);
                Curso curso2 = new Curso();
                curso2.setNome("Sistemas de Informação");
                curso2.setHorasnecessarias(150);
                Curso curso3 = new Curso();
                curso3.setNome("Administração");
                curso3.setHorasnecessarias(200);
                Curso curso4 = new Curso();
                curso4.setNome("Biomedicina");
                curso4.setHorasnecessarias(400);
                Categoria categoria = new Categoria();
                categoria.setNome("Capacitação");
                Categoria categoria2 = new Categoria();
                categoria2.setNome("Eventos");
                Categoria categoria3 = new Categoria();
                categoria3.setNome("Pesquisa");
                cursoController.incluir(curso);
                cursoController.incluir(curso2);
                cursoController.incluir(curso3);
                cursoController.incluir(curso4);
                categoriaController.incluir(categoria);
                categoriaController.incluir(categoria2);
                categoriaController.incluir(categoria3);*/
                Intent troca = new Intent(SplashActivity.this,Menu1Activity.class);
                startActivity(troca);
                finish();
                AlunoController alunoController = new AlunoController(getApplicationContext());
                alunoController.listar();
                for (Aluno aluno:alunoController.listar()
                     ) {
                    Log.i(AppUtil.TAG, "run: ALUNOS: "+aluno);
                }
            }
        },3000);
    }
}