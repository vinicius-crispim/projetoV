package com.vinicius.crispim.vprojeto.datamodel;

import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Aluno;

public class AlunoDataModel {
    //nome da tabela
    public static final String TABELA = "aluno";
/* private String nome;
    private Integer matricula;
    private String email;
    private String celular;
    private String CPF;
    private String senha;
    private Curso curso;
    private Integer horasFeitas;
    private Integer horasFaltando;*/
    //atributos
    public static final String MATRICULA = "matricula";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String CELULAR = "celular";
    public static final String CPF = "cpf";
    public static final String SENHA = "senha";
    public static final String IDCURSO = "idcurso";
    public static final String HORASFEITAS = "horasfeitas";
    public static final String HORASFALTANDO = "horasfaltando";


    //query para criar tabela no banco
    public static String queryCriarTabela = "";

    //gera script para criar tabela
    public static String criarTabela(){
        queryCriarTabela+="CREATE TABLE " + TABELA +" (";
        queryCriarTabela+=MATRICULA+" integer primary key autoincrement, ";
        queryCriarTabela+=NOME+" text, ";
        queryCriarTabela+=EMAIL+" text, ";
        queryCriarTabela+=CELULAR+" text, ";
        queryCriarTabela+=CPF+" text, ";
        queryCriarTabela+=SENHA+" text, ";
        queryCriarTabela+=HORASFALTANDO+" integer, ";
        queryCriarTabela+=HORASFEITAS+" integer, ";
        queryCriarTabela+=IDCURSO+" INTEGER CONSTRAINT idcurso REFERENCES curso (id) ";
        //queryCriarTabela += IDCATEGORIA + " INTEGER CONSTRAINT idcategoria REFERENCES categoria (id) ";
        queryCriarTabela+=");";


        return queryCriarTabela;
    }public static String inserirAluno(Aluno aluno){
        queryCriarTabela+="INSERT INTO " + TABELA +" ";
        queryCriarTabela+="(nome, email,celular,cpf,senha,horasfaltando,horasfeitas,idcurso) \n";
        queryCriarTabela+="VALUES ('";
        queryCriarTabela+=aluno.getNome()+"', '";
        queryCriarTabela+=aluno.getEmail()+"', '";
        queryCriarTabela+=aluno.getCurso().getId()+"', '";
        queryCriarTabela+=aluno.getCelular()+"', '";
        queryCriarTabela+=aluno.getCPF()+"', '";
        queryCriarTabela+=aluno.getSenha()+"', '";
        queryCriarTabela+=aluno.getHorasFaltando()+"', '";
        queryCriarTabela+=aluno.getHorasFeitas()+"'";
        queryCriarTabela+=");";
        Log.d(AppUtil.TAG, "inserirAluno: TESTE"+queryCriarTabela);


        return queryCriarTabela;
    }
}
