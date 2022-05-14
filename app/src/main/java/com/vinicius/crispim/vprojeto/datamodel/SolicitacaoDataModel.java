package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

public class SolicitacaoDataModel {
    //nome da tabela
    public static final String TABELA = "solicitacao";

    public static final String ID = "id";
    public static final String TITULO = "titulo";
    public static final String DATA = "data";
    public static final String IMAGEM = "imagem";
    public static final String CARGA = "carga";
    public static final String INSTITUICAO = "instituicao";
    public static final String STATUS = "status";
    public static final String DESCRICAO = "descricao";
    public static final String RESPOSTA = "resposta";
    public static final String IDCATEGORIA = "idcategoria";


    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE "+TABELA+" (";
        queryCriarTabela+=ID+" integer primary key autoincrement, ";
        queryCriarTabela+=TITULO+" text, ";
        queryCriarTabela+=DATA+" text, ";
        queryCriarTabela+=CARGA+" integer, ";
        queryCriarTabela+=INSTITUICAO+" text, ";
        queryCriarTabela+=STATUS+" text, ";
        queryCriarTabela+=DESCRICAO+" text, ";
        queryCriarTabela+=RESPOSTA+" text, ";
        queryCriarTabela+=IMAGEM+" BLOB, ";
        queryCriarTabela+=IDCATEGORIA+" INTEGER CONSTRAINT idcategoria REFERENCES categoria (id) ";
        queryCriarTabela+=")";


        return queryCriarTabela;
    }
    public static String inserirCliente(Solicitacao solicitacao){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(titulo, data, carga, instituicao, status, descricao, resposta, imagem, idcategoria) ";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+=solicitacao.getTitulo()+"', '";
        queryCriarTabela+=solicitacao.getData()+"', '";
        queryCriarTabela+=solicitacao.getCarga()+"', '";
        queryCriarTabela+=solicitacao.getInstituicao()+"', '";
        queryCriarTabela+=solicitacao.getStatus()+"', '";
        queryCriarTabela+=solicitacao.getDescricao()+"', '";
        queryCriarTabela+=solicitacao.getResposta()+"', '";
        queryCriarTabela+=solicitacao.getImagem()+"', '";
        queryCriarTabela+=solicitacao.getCategoria().getId()+"'";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "Inserir curso: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
