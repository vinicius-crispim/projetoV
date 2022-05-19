package com.vinicius.crispim.vprojeto.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;
import com.vinicius.crispim.vprojeto.view.MenuCoordenadorActivity;

import java.util.List;

public class SolicitacoesFragment extends Fragment {
    ListView lista_solicitacao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_coordenador, container,false);
        ((MenuCoordenadorActivity) getActivity()).setActionBarTitle("Solicitações");
        lista_solicitacao = (ListView) view.findViewById(R.id.lista_solicitacao_coordenador);
        this.CarregarFeed();
        return view;
    }
    protected void CarregarFeed(){
        SolicitacaoController solicitacaoController = new SolicitacaoController(getContext());
        List<Solicitacao> solicitacoes = solicitacaoController.listar();
        lista_solicitacao.setAdapter((ListAdapter) new LinhaConsultarSolicitacoesAdapter(this, solicitacoes));

    }
}
