package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.CategoriaDataModel;
import com.vinicius.crispim.vprojeto.datamodel.SolicitacaoDataModel;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

import java.util.List;

public class CategoriaController extends AppDataBase implements ICrud<Categoria>{

    ContentValues dados;

    public CategoriaController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "CursoController: Conectado");
    }


    @Override
    public boolean incluir(Categoria obj) {
        dados = new ContentValues();
        dados.put(CategoriaDataModel.NOME,obj.getNome());
        dados.put(CategoriaDataModel.ID,obj.getId());

        return insert(CategoriaDataModel.TABELA,dados);
    }

    @Override
    public boolean alterar(Categoria obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public List<Categoria> listar() {
        return getAllCategoria(CategoriaDataModel.TABELA);
    }
}
