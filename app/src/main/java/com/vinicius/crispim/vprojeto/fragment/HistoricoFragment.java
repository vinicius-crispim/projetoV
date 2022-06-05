package com.vinicius.crispim.vprojeto.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoricoFragment extends Fragment {
    ListView lista_historico;
    private View view;
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_historico, container,false);
        lista_historico = (ListView) view.findViewById(R.id.lista_historico);
        spinner = (Spinner) view.findViewById(R.id.spnSolicitacoesAluno);
        List<String> opcoes = new ArrayList<>(Arrays.asList("TODAS","EM ANALISE","DEFERIDA","INDEFERIDA"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, opcoes );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarregarFeed();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
    protected void CarregarFeed(){
        SolicitacaoController solicitacaoController = new SolicitacaoController(getContext());
        Menu1Activity activity = (Menu1Activity) view.getContext();
        List<Solicitacao> solicitacoes = solicitacaoController.listarByAluno(activity.getAluno().getMatricula(),spinner.getSelectedItem().toString());
        lista_historico.setAdapter((ListAdapter) new LinhaConsultarHistoricoAlunoAdapter(this,solicitacoes));

    }
}
