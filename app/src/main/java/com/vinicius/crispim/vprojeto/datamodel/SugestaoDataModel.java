package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Sugestao;

public class SugestaoDataModel {
    //nome da tabela
    public static final String TABELA = "sugestao";

    public static final String ID = "id";
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final String IMGSUGESTAO = "imgsugestao";


    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE "+TABELA+" (";
        queryCriarTabela+=ID+" integer primary key autoincrement, ";
        queryCriarTabela+=TITULO+" text, ";
        queryCriarTabela+=DESCRICAO+" text, ";
        queryCriarTabela+=IMGSUGESTAO+" BLOB ";
        queryCriarTabela+=")";


        return queryCriarTabela;
    }
    public static String inserirCliente(Sugestao sugestao){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(titulo,descricao,imgsugestao) ";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+= sugestao.getTitulo()+"',";
        queryCriarTabela+= sugestao.getDescricao()+"',";
        queryCriarTabela+= sugestao.getImgSugestao()+"'";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "Inserir curso: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
