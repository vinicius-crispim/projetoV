package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

public class CoordenadorDataModel {
    //nome da tabela
    public static final String TABELA = "coordenador";

    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String NOME = "nome";
    public static final String CELULAR = "celular";
    public static final String CPF = "cpf";
    public static final String SENHA = "senha";



    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE "+TABELA+" (";
        queryCriarTabela+=ID+" integer primary key autoincrement, ";
        queryCriarTabela+=NOME+" text, ";
        queryCriarTabela+=EMAIL+" text, ";
        queryCriarTabela+=CELULAR+" text, ";
        queryCriarTabela+=CPF+" text, ";
        queryCriarTabela+=SENHA+" text ";
        queryCriarTabela+=")";


        return queryCriarTabela;
    }
    public static String inserirCliente(Coordenador coordenador){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(nome,email,celular,cpf,senha) ";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+=coordenador.getNome()+"', '";
        queryCriarTabela+=coordenador.getEmail()+"', '";
        queryCriarTabela+=coordenador.getCelular()+"', '";
        queryCriarTabela+=coordenador.getCPF()+"'";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "Inserir curso: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
