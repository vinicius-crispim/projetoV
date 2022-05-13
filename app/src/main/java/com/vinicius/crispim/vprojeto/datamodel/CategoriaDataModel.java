package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Curso;

public class CategoriaDataModel {
    //nome da tabela
    public static final String TABELA = "categoria";

    public static final String ID = "id";
    public static final String NOME = "nome";


    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE "+TABELA+" (";
        queryCriarTabela+=ID+" integer primary key autoincrement, ";
        queryCriarTabela+=NOME+" text ";
        queryCriarTabela+=")";


        return queryCriarTabela;
    }
    public static String inserirCliente(Categoria categoria){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(nome) ";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+=categoria.getNome()+"'";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "Inserir curso: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
