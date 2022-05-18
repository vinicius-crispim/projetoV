package com.vinicius.crispim.vprojeto.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

public class PerfilFragment extends Fragment {
    TextInputEditText txtSeusDados_Nome;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container,false);
        txtSeusDados_Nome = view.findViewById(R.id.txtSeusDados_Nome);
        txtSeusDados_Matricula = view.findViewById(R.id.txtSeusDados_Matricula);
        txtSeusDados_Telefone = view.findViewById(R.id.txtSeusDados_Telefone);
        txtSeusDados_Email = view.findViewById(R.id.txtSeusDados_Email);
        txtSeusDados_CPF = view.findViewById(R.id.txtSeusDados_CPF);
        txtSeusDados_Curso = view.findViewById(R.id.txtSeusDados_Curso);
        txtSeusDados_Curso_Coordenador = view.findViewById(R.id.txtSeusDados_Curso_Coordenador);
        txtSeusDados_Curso_Horas = view.findViewById(R.id.txtSeusDados_Curso_Horas);
        txtSeusDados_Horas = view.findViewById(R.id.txtSeusDados_Horas);
        txtSeusDados_HorasFaltando = view.findViewById(R.id.txtSeusDados_HorasFaltando);
        Menu1Activity activity = (Menu1Activity) getActivity();
        Aluno aluno = activity.getAluno();
        txtSeusDados_Nome.setText(aluno.getNome());
        txtSeusDados_Matricula.setText(aluno.getMatricula().toString());
        txtSeusDados_Telefone.getText().append(aluno.getCelular());
        txtSeusDados_Email.getText().append(aluno.getEmail());
        txtSeusDados_CPF.getText().append(aluno.getCPF());
        txtSeusDados_Curso.getText().append(aluno.getCurso().getNome());
        txtSeusDados_Curso_Coordenador.getText().append("Carlos Gouveia");
        txtSeusDados_Curso_Horas.getText().append(aluno.getCurso().getHorasnecessarias().toString());
        txtSeusDados_Horas.getText().append(aluno.getHorasFeitas().toString());
        txtSeusDados_HorasFaltando.getText().append(aluno.getHorasFaltando().toString());
        return view;
    }
}
