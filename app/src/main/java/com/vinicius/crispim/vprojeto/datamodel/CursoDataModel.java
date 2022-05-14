package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;

public class CursoDataModel {
    //nome da tabela
    public static final String TABELA = "curso";

    public static final String ID = "id";
    public static final String IDCOORDENADOR = "idcoordenador";
    public static final String NOME = "nome";
    public static final String HORASNECESSARIAS = "horasnecessarias";


    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE "+TABELA+" (";
        queryCriarTabela+=ID+" integer primary key autoincrement, ";
        queryCriarTabela+=NOME+" text, ";
        queryCriarTabela+=HORASNECESSARIAS+" integer, ";
        queryCriarTabela+=IDCOORDENADOR+" INTEGER CONSTRAINT idcoordenador REFERENCES coordenador (id) ";
        queryCriarTabela+=")";


        return queryCriarTabela;
    }
    public static String inserirCliente(Curso curso){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(nome, horasnecessarias,idcoordenador) ";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+=curso.getNome()+"', '";
        queryCriarTabela+=curso.getHorasnecessarias()+"', ";
        queryCriarTabela+=curso.getCoordenador().getId()+"";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "Inserir curso: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
