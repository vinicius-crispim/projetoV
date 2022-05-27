package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Curso;

public class VisualizaAlunoActivity extends AppCompatActivity {
    EditText txtSeusDados_Nome;
    CursoController cursoController;
    EditText txtSeusDados_Matricula;
    EditText txtSeusDados_Telefone;
    EditText txtSeusDados_Email;
    EditText txtSeusDados_CPF;
    EditText txtSeusDados_Curso;
    EditText txtSeusDados_Curso_Coordenador;
    EditText txtSeusDados_Curso_Horas;
    EditText txtSeusDados_Horas;
    EditText txtSeusDados_HorasFaltando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_visualiza_aluno);
        txtSeusDados_Nome = findViewById(R.id.txtPesquisaAlunoNome);
        txtSeusDados_Matricula =findViewById(R.id.txtPesquisaAlunoMatricula);
        txtSeusDados_Telefone = findViewById(R.id.txtPesquisaAlunoTelefone);
        txtSeusDados_Email = findViewById(R.id.txtPesquisaAlunoEmail);
        txtSeusDados_CPF = findViewById(R.id.txtPesquisaAlunoCPF);
        txtSeusDados_Curso = findViewById(R.id.txtPesquisaAlunoCurso);
        txtSeusDados_Curso_Coordenador = findViewById(R.id.txtPesquisaAlunoCoordenador);
        txtSeusDados_Curso_Horas = findViewById(R.id.txtPesquisaAlunoHorasNecessarias);
        txtSeusDados_Horas = findViewById(R.id.txtPesquisaAlunoHorasFeitas);
        txtSeusDados_HorasFaltando = findViewById(R.id.txtPesquisaAlunoHorasFaltando);
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
        Coordenador coordenador = new Coordenador();
        coordenador.setNome(parametros.getString("nomecoordenador"));
        coordenador.setSenha(parametros.getString("senhacoordenador"));
        coordenador.setCelular(parametros.getString("celularcoordenador"));
        coordenador.setCPF(parametros.getString("CPFcoordenador"));
        coordenador.setEmail(parametros.getString("emailcoordenador"));
        coordenador.setId(parametros.getInt("idcoordenador"));
        curso.setCoordenador(coordenador);
        aluno.setCurso(curso);
        Log.i(AppUtil.TAG, "onCreate: Nome:"+aluno.getNome());
        txtSeusDados_Nome.setText(aluno.getNome());
        txtSeusDados_Matricula.setText(aluno.getMatricula().toString());
        txtSeusDados_Telefone.getText().append(aluno.getCelular());
        txtSeusDados_Email.getText().append(aluno.getEmail());
        txtSeusDados_CPF.getText().append(aluno.getCPF());
        txtSeusDados_Curso.getText().append(aluno.getCurso().getNome());
        txtSeusDados_Curso_Coordenador.getText().append(aluno.getCurso().getCoordenador().getNome());
        txtSeusDados_Curso_Horas.getText().append(aluno.getCurso().getHorasnecessarias().toString());
        txtSeusDados_Horas.getText().append(aluno.getHorasFeitas().toString());
        txtSeusDados_HorasFaltando.getText().append(aluno.getHorasFaltando().toString());
    }
}