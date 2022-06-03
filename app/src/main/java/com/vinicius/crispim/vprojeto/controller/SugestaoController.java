package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.CategoriaDataModel;
import com.vinicius.crispim.vprojeto.datamodel.SugestaoDataModel;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Sugestao;

import java.util.List;

public class SugestaoController extends AppDataBase implements ICrud<Sugestao>{

    ContentValues dados;

    public SugestaoController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "CursoController: Conectado");
    }


    @Override
    public boolean incluir(Sugestao obj) {
        dados = new ContentValues();
        dados.put(SugestaoDataModel.TITULO,obj.getTitulo());
        dados.put(SugestaoDataModel.ID,obj.getId());
        dados.put(SugestaoDataModel.DESCRICAO,obj.getDescricao());
        dados.put(SugestaoDataModel.IMGSUGESTAO,obj.getImgSugestao());

        return insert(SugestaoDataModel.TABELA,dados);
    }

    @Override
    public boolean alterar(Sugestao obj) {
        dados = new ContentValues();
        dados.put(SugestaoDataModel.TITULO,obj.getTitulo());
        dados.put(SugestaoDataModel.ID,obj.getId());
        dados.put(SugestaoDataModel.DESCRICAO,obj.getDescricao());
        dados.put(SugestaoDataModel.IMGSUGESTAO,obj.getImgSugestao());

        return update(SugestaoDataModel.TABELA,dados);    }

    @Override
    public boolean deletar(int id) {
        return deleteById(SugestaoDataModel.TABELA,id);
    }

    @Override
    public List<Sugestao> listar() {
        return getAllSugestao(SugestaoDataModel.TABELA);
    }
    public Sugestao getSugestaoById(Integer id) {
        return getSugestaoById(SugestaoDataModel.TABELA,id);
    }
}
