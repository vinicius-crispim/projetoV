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
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

import java.util.List;

public class HomeFragment extends Fragment {
    ListView lista_sugestao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        lista_sugestao = (ListView) view.findViewById(R.id.lista_sugestao);
        this.CarregarFeed();
        return view;
    }
    protected void CarregarFeed(){
        SugestaoController sugestaoController = new SugestaoController(getContext());
        List<Sugestao> sugestoes = sugestaoController.listar();
        lista_sugestao.setAdapter((ListAdapter) new LinhaConsultarAdapter(this, sugestoes));

    }
}
