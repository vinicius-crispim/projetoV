package com.vinicius.crispim.vprojeto.fragment;

import static com.vinicius.crispim.vprojeto.api.AppUtil.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.VisualizaAlunoActivity;

import java.util.List;

public class HomeCoordenadorFragment extends Fragment {
    ListView lista_sugestao_coordenador;
    AutoCompleteTextView txtPesquisarAluno;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_coordenador, container,false);
        lista_sugestao_coordenador = (ListView) view.findViewById(R.id.lista_sugestao_coordenador);
        txtPesquisarAluno = view.findViewById(R.id.txtPesquisarAluno);
        AlunoController alunoController = new AlunoController(getContext());
        List<Aluno> alunos = alunoController.listar();
        ArrayAdapter<Aluno> adpter = new ArrayAdapter<>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,alunos);
        txtPesquisarAluno.setAdapter(adpter);
        Log.i(TAG, "onCreateView: Pesquisando ALuno: "+txtPesquisarAluno.getText());
        txtPesquisarAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = alunoController.findByNome(txtPesquisarAluno.getText().toString());
                Log.i(TAG, "onItemSelected: ALUNO SELECIONADO:"+aluno);
                Log.i(TAG, "onItemSelected: ALUNO MATRICULA:"+aluno.getMatricula());
                Log.i(TAG, "onItemSelected: ALUNO SELECIONADO:"+aluno.getCurso().getNome());
                Bundle parametros = new Bundle();
                parametros.putString("nome", aluno.getNome());
                parametros.putString("senha", aluno.getSenha());
                parametros.putString("celular", aluno.getCelular());
                parametros.putString("CPF", aluno.getCPF());
                parametros.putString("email", aluno.getEmail());
                parametros.putInt("matricula", aluno.getMatricula());
                parametros.putInt("horasfeitas", aluno.getHorasFeitas());
                parametros.putInt("horasfaltando", aluno.getHorasFaltando());
                parametros.putString("nomecurso", aluno.getCurso().getNome());
                parametros.putInt("idcurso", aluno.getCurso().getId());
                parametros.putInt("horasnecessariascurso", aluno.getCurso().getHorasnecessarias());
                parametros.putString("nomecoordenador", aluno.getCurso().getCoordenador().getNome());
                parametros.putString("senhacoordenador", aluno.getCurso().getCoordenador().getSenha());
                parametros.putString("celularcoordenador", aluno.getCurso().getCoordenador().getCelular());
                parametros.putString("CPFcoordenador", aluno.getCurso().getCoordenador().getCPF());
                parametros.putString("emailcoordenador", aluno.getCurso().getCoordenador().getEmail());
                parametros.putInt("idcoordenador", aluno.getCurso().getCoordenador().getId());
                Intent intent = new Intent(getContext(), VisualizaAlunoActivity.class);
                intent.putExtras(parametros);
                startActivity(intent);
            }
        });
        this.CarregarCoordenador();
        return view;
    }

    protected void CarregarCoordenador(){
        SugestaoController sugestaoController = new SugestaoController(getContext());
        List<Sugestao> sugestoes = sugestaoController.listar();
        lista_sugestao_coordenador.setAdapter((ListAdapter) new LinhaConsultarCoordenadorAdapter(this, sugestoes));
    }

}
