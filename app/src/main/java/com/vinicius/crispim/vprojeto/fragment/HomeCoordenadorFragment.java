package com.vinicius.crispim.vprojeto.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.MenuCoordenadorActivity;

import java.util.List;

public class HomeCoordenadorFragment extends Fragment {
    ListView lista_sugestao_coordenador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_coordenador, container,false);
        lista_sugestao_coordenador = (ListView) view.findViewById(R.id.lista_sugestao_coordenador);
        this.CarregarCoordenador();
        return view;
    }
    protected void CarregarCoordenador(){
        SugestaoController sugestaoController = new SugestaoController(getContext());
        List<Sugestao> sugestoes = sugestaoController.listar();
        lista_sugestao_coordenador.setAdapter((ListAdapter) new LinhaConsultarCoordenadorAdapter(this, sugestoes));

    }
}
