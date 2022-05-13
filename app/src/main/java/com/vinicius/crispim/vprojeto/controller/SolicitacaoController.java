package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.SolicitacaoDataModel;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

import java.util.List;

public class SolicitacaoController extends AppDataBase implements ICrud<Solicitacao>{

    ContentValues dados;

    public SolicitacaoController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "CursoController: Conectado");
    }


    @Override
    public boolean incluir(Solicitacao obj) {
        dados = new ContentValues();
        dados.put(SolicitacaoDataModel.TITULO,obj.getTitulo());
        dados.put(SolicitacaoDataModel.ID,obj.getId());
        dados.put(SolicitacaoDataModel.DATA,obj.getData());
        dados.put(SolicitacaoDataModel.DESCRICAO,obj.getDescricao());
        dados.put(SolicitacaoDataModel.CARGA,obj.getCarga());
        dados.put(SolicitacaoDataModel.INSTITUICAO,obj.getInstituicao());
        dados.put(SolicitacaoDataModel.STATUS,obj.getStatus());
        dados.put(SolicitacaoDataModel.RESPOSTA,obj.getResposta());
        dados.put(SolicitacaoDataModel.IMAGEM,obj.getImagem());

        return insert(SolicitacaoDataModel.TABELA,dados);
    }

    @Override
    public boolean alterar(Solicitacao obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public List<Solicitacao> listar() {
        return getAllSolicitacao(SolicitacaoDataModel.TABELA);
    }
}
