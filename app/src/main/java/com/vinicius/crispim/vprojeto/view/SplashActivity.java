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
import com.vinicius.crispim.vprojeto.controller.CoordenadorController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.datamodel.CategoriaDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CoordenadorDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CursoDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

import java.util.Date;

public class SplashActivity extends AppCompatActivity {
    CursoController cursoController;
    CategoriaController categoriaController;
    CoordenadorController coordenadorController;
    AlunoController alunoController;
    Coordenador coordenador;
    Coordenador coordenador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        cursoController = new CursoController(getApplicationContext());
        categoriaController = new CategoriaController(getApplicationContext());
        alunoController = new AlunoController(getApplicationContext());
        coordenadorController = new CoordenadorController(getApplicationContext());
        trocartela();
    }
    private void trocartela() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               /* coordenador = new Coordenador();
                coordenador.setCelular("(41) 99955-8866");
                coordenador.setCPF("111.111.111-00");
                coordenador.setNome("Carlos Gouveia");
                coordenador2 = new Coordenador();
                coordenador2.setCelular("(41) 96845-1453");
                coordenador2.setCPF("324.562.271-21");
                coordenador2.setNome("Rosangela Silva");
                Curso curso = new Curso();
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
                categoria3.setNome("Pesquisa");;
                coordenador.setEmail("gouveia@unifacear.com");
                coordenador.setSenha("123456");
                coordenador2.setEmail("rosangela@unifacear.com");
                coordenador2.setSenha("789");
                coordenador.setId(1);
                coordenador2.setId(2);
                categoriaController.incluir(categoria);
                categoriaController.incluir(categoria2);
                categoriaController.incluir(categoria3);
                coordenadorController.incluir(coordenador);
                coordenadorController.incluir(coordenador2);
                curso.setCoordenador(coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(0));
                curso2.setCoordenador(coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(0));
                curso3.setCoordenador(coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(1));
                curso4.setCoordenador(coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(1));
                Log.i(AppUtil.TAG, "run: COORDENADOR: "+coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(0).getNome());
                Log.i(AppUtil.TAG, "run: COORDENADOR: "+coordenadorController.getAllCoordenadores(CoordenadorDataModel.TABELA).get(1).getNome());
                cursoController.incluir(curso);
                cursoController.incluir(curso2);
                cursoController.incluir(curso3);
                cursoController.incluir(curso4);
                curso.setId(1);
                Aluno aluno = new Aluno();
                aluno.setCurso(curso);
                aluno.setHorasFeitas(100);
                aluno.setCelular("41 997548613");
                aluno.setHorasFaltando(50);
                aluno.setSenha("12");
                aluno.setCPF("121.238.754-49");
                aluno.setEmail("vinicrispim02@hotmail.com");
                aluno.setNome("Vinicius Crispim");
                alunoController.incluir(aluno);*/
                Intent troca = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(troca);
                finish();
          /*      AlunoController alunoController = new AlunoController(getApplicationContext());
                alunoController.listar();
                for (Aluno aluno2:alunoController.listar()
                     ) {
                    Log.i(AppUtil.TAG, "run: ALUNOS: "+aluno2);
                }*/
            }
        },3000);
    }
}