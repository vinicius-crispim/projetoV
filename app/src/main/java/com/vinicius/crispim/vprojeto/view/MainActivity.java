package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView link;
    Button btnlogar;
    TextView matlogin;
    TextView senhalogin;
    AlunoController alunoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btnlogar = findViewById(R.id.btnLogar);
        link = findViewById(R.id.txtLinkCadastro);
        senhalogin = findViewById(R.id.txtSenhaEntrar);
        matlogin = findViewById(R.id.txtEmailentrar);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent troca = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(troca);
            }
        });
        btnlogar.setOnClickListener(new View.OnClickListener() {
            Aluno alunosalvo;
            Curso cursosalvo;
            @Override
            public void onClick(View view) {
                 alunosalvo= new Aluno();
                 cursosalvo= new Curso();
                alunoController = new AlunoController(getApplicationContext());
                String matriculastr = matlogin.getText().toString();
                Integer matricula = Integer.parseInt(matriculastr);
                if(alunoController.logar(senhalogin.getText().toString(),matricula).size() == 0){
                    Log.i(AppUtil.TAG, "onClick: LOGIN errado");
                    Toast.makeText(MainActivity.this,"Matricula ou senha inválidos",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Aluno aluno = alunoController.logar(senhalogin.getText().toString(),matricula).get(0);
                    Log.i(AppUtil.TAG, "onClick: LOGIN efetuado");
                    Bundle parametros = new Bundle();
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
                    Toast.makeText(MainActivity.this,"Bem-vindo "+aluno.getNome(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Menu1Activity.class);
                    intent.putExtras(parametros);
                    startActivity(intent);
                }
            }
        });
    }
}