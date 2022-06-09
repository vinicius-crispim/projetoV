package com.vinicius.crispim.vprojeto.fragment;

import static com.vinicius.crispim.vprojeto.api.AppUtil.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;
import com.vinicius.crispim.vprojeto.view.MenuCoordenadorActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SolicitacoesFragment extends Fragment {
    ListView lista_solicitacao;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_coordenador, container,false);
        lista_solicitacao = (ListView) view.findViewById(R.id.lista_solicitacao_coordenador);
        spinner = (Spinner) view.findViewById(R.id.spnSolicitacoes);
        List<String> opcoes = new ArrayList<>(Arrays.asList("Todas","Em analise","Deferida","Indeferida"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item, opcoes );

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
        MenuCoordenadorActivity activity = (MenuCoordenadorActivity) getActivity();
        List<Solicitacao> solicitacoes = solicitacaoController.listarByFiltro(spinner.getSelectedItem().toString().toUpperCase(Locale.ROOT),activity.getCoordenador().getId());
        lista_solicitacao.setAdapter((ListAdapter) new LinhaConsultarSolicitacoesAdapter(this, solicitacoes,spinner.getSelectedItem().toString(),activity.getCoordenador().getId()));
        for (Solicitacao aluno2:solicitacoes
        ) {
            Log.i(TAG, "run: ALUNOS: "+aluno2.getTitulo());
        }
    }
}
