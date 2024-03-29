package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.util.MaskType;
import com.vinicius.crispim.vprojeto.util.MaskUtil;

import java.util.ArrayList;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity {

    private static final String TAG = "TESTANDO REGISTRAR";
    Aluno aluno = new Aluno();
    Curso curso1 = new Curso();
    Double i;
    Curso curso2 = new Curso();
    Curso curso3 = new Curso();
    Curso curso4 = new Curso();
    Curso curso5 = new Curso();
    Button btncadastrar;
    TextView nome;
    TextView senha;
    TextView senhaConfirma;
    EditText celular;
    EditText CPF;
    TextView matricula;
    TextView email;
    AutoCompleteTextView txtautocursos;
    List<Curso> curso = new ArrayList<Curso>();
    Curso ultimo;
    CursoController cursoController;
    AlunoController alunoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registrar);
        celular = findViewById(R.id.editTextPhone);
        senha = findViewById(R.id.txtSenhaCadastro);
        senhaConfirma = findViewById(R.id.txtConfirmaSenha);
        CPF = findViewById(R.id.txtCPF);
        matricula = findViewById(R.id.txtMatricula);
        email = findViewById(R.id.txtEmailCadastro);
        nome = findViewById(R.id.txtTituloLinha);
        txtautocursos = findViewById(R.id.txtautocursos);
        cursoController = new CursoController(getApplicationContext());
        curso = cursoController.listar();
        ArrayAdapter<Curso> adpter = new ArrayAdapter<Curso>(this, R.layout.spinner_item, curso);
        txtautocursos.setAdapter(adpter);
        CPF.addTextChangedListener(MaskUtil.insert(CPF, MaskType.CPF));
        celular.addTextChangedListener(MaskUtil.insert(celular, MaskType.CEL));
        btncadastrar = findViewById(R.id.btnCadastrarAluno);
        alunoController = new AlunoController(getApplicationContext());
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (senhaConfirma.getText().toString().equals(senha.getText().toString())) {
                    int aux = 0;
                    for (Aluno alunofor : alunoController.listar()) {
                        if (alunofor.getSenha().equals(senha.getText().toString())) {
                            Toast.makeText(RegistrarActivity.this, "Esta senha ja está em uso",
                                    Toast.LENGTH_SHORT).show();
                            aux++;
                        }
                    }
                    if (aux == 0) {
                        if(ValidaValores() == true) {
                            aluno.setNome(nome.getText().toString());
                            aluno.setCPF(CPF.getText().toString());
                            aluno.setEmail(email.getText().toString());
                            aluno.setCelular(celular.getText().toString());
                            aluno.setSenha(senha.getText().toString());
                            aluno.setHorasFeitas(0);
                            aluno.setCurso(cursoController.getCursoByNome(txtautocursos.getText().toString()));
                            aluno.setHorasFaltando(aluno.getCurso().getHorasnecessarias());
                            alunoController.incluir(aluno);

                            Log.i(AppUtil.TAG, "onCreate: aluno cadastrado " + aluno.getCurso().getNome());
                            Toast.makeText(RegistrarActivity.this, "Aluno " + aluno.getNome() + " registrado com sucesso",
                                    Toast.LENGTH_SHORT).show();
                            Intent troca = new Intent(RegistrarActivity.this, MainActivity.class);
                            startActivity(troca);
                        }
                    }

                } else {
                    Toast.makeText(RegistrarActivity.this, "As senhas não são iguais",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private Boolean ValidaValores() {
        if (nome.getText().toString().equals("") &&
                senha.getText().toString().equals("") &&
                senhaConfirma.getText().toString().equals("") &&
                celular.getText().toString().equals("") &&
                CPF.getText().toString().equals("") &&
                matricula.getText().toString().equals("") &&
                email.getText().toString().equals("")) {
            Toast.makeText(RegistrarActivity.this, "Todos os campos devem ser preenchidos",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}