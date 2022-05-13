package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity  {

    private static final String TAG = "TESTANDO REGISTRAR";
    Aluno aluno= new Aluno();
    Curso curso1 = new Curso();
    Double i;
    Curso curso2 = new Curso();
    Curso curso3 = new Curso();
    Curso curso4 = new Curso();
    Curso curso5 = new Curso();
    Spinner spinner;
    Button btncadastrar;
    TextView nome;
    TextView senha;
    TextView senhaConfirma;
    TextView celular;
    TextView CPF;
    TextView matricula;
    TextView email;
    AutoCompleteTextView txtautocursos;
    List<Curso> curso = new ArrayList<Curso>();
    Curso ultimo;
    CursoController cursoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        celular = findViewById(R.id.editTextPhone);
        senha = findViewById(R.id.txtSenhaCadastro);
        senhaConfirma = findViewById(R.id.txtConfirmaSenha);
        CPF = findViewById(R.id.txtCPF);
        matricula =findViewById(R.id.txtMatricula);
        email = findViewById(R.id.txtEmailCadastro);
        nome = findViewById(R.id.txtNome);
        txtautocursos = findViewById(R.id.txtautocursos);
        cursoController = new CursoController(getApplicationContext());
        curso = cursoController.listar();
        ArrayAdapter<Curso> adpter = new ArrayAdapter<Curso>(this, android.R.layout.simple_spinner_dropdown_item,curso);
        txtautocursos.setAdapter(adpter);
        /*curso1.setNome("Análise e Desenvolvimento de Sistemas");
        curso1.setId(1);
        curso1.setHorasnecessarias(150);
        curso.add(curso1);
        curso2.setNome("Engenharia Cívil");
        curso2.setId(2);
        curso2.setHorasnecessarias(300);
        curso.add(curso2);
        curso3.setNome("Sistema da Informação");
        curso3.setId(3);
        curso3.setHorasnecessarias(200);
        curso.add(curso3);
        curso4.setNome("Administração");
        curso4.setId(4);
        curso4.setHorasnecessarias(200);
        curso.add(curso4);
        curso5.setNome("Biomedicina");
        curso5.setId(5);
        curso5.setHorasnecessarias(500);
        curso.add(curso5);
        cursoController = new CursoController(getApplicationContext());
        Curso cur = new Curso();
        cur.setNome("Selecione seu curso");
        curso = cursoController.listar();
        curso.add(0,cur);*/
        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this, android.R.layout.simple_spinner_dropdown_item,curso);
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spnCursos);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    ultimo = curso.get(position-1);
                    aluno.setCurso(curso.get(position));
                    Log.i(AppUtil.TAG, "onCreate: AUTOCURSO:"+txtautocursos.getText().toString());

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btncadastrar = findViewById(R.id.btnCadastrarAluno);
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aluno.setNome(nome.getText().toString());
                aluno.setCPF(CPF.getText().toString());
                aluno.setEmail(email.getText().toString());
                aluno.setCelular(celular.getText().toString());
                aluno.setSenha(senha.getText().toString());
                aluno.setHorasFeitas(0);
                aluno.setCurso(cursoController.getCursoByNome(txtautocursos.getText().toString()));
                aluno.setHorasFaltando(aluno.getCurso().getHorasnecessarias());
                AlunoController alunoController = new AlunoController(getApplicationContext());
                alunoController.incluir(aluno);

                Log.i(AppUtil.TAG, "onCreate: aluno cadastrado "+aluno.getCurso().getNome());
                Toast.makeText(RegistrarActivity.this,"Aluno "+aluno.getNome()+" registrado com sucesso",
                        Toast.LENGTH_SHORT).show();
                Intent troca = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(troca);
            }

        });
    }
}