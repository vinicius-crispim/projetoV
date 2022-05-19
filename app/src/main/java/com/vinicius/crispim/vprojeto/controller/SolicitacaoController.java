package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.CoordenadorDataModel;
import com.vinicius.crispim.vprojeto.datamodel.SolicitacaoDataModel;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

import java.util.List;

public class SolicitacaoController extends AppDataBase implements ICrud<Solicitacao>{

    ContentValues dados;
    CoordenadorController coordenadorController;
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
        dados.put(SolicitacaoDataModel.IDCATEGORIA,obj.getCategoria().getId());
        dados.put(SolicitacaoDataModel.IDALUNO,obj.getAluno().getMatricula());
        Log.i(AppUtil.TAG, "incluir: COORDENADOR:"+obj.getAluno().getCurso().getCoordenador().getId());
        dados.put(SolicitacaoDataModel.IDCOORDENADOR,obj.getAluno().getCurso().getCoordenador().getId());

        return insert(SolicitacaoDataModel.TABELA,dados);
    }

    @Override
    public boolean alterar(Solicitacao obj) {
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
        dados.put(SolicitacaoDataModel.IDCATEGORIA,obj.getCategoria().getId());
        dados.put(SolicitacaoDataModel.IDALUNO,obj.getAluno().getMatricula());
        dados.put(SolicitacaoDataModel.IDCOORDENADOR,obj.getAluno().getCurso().getCoordenador().getId());
        return update(SolicitacaoDataModel.TABELA,dados);
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
