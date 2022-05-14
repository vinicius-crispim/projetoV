package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.AlunoDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CursoDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Curso;

import java.util.List;

public class CursoController extends AppDataBase implements ICrud<Curso>{

    ContentValues dados;

    public CursoController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "CursoController: Conectado");
    }


    @Override
    public boolean incluir(Curso obj) {
        dados = new ContentValues();
        dados.put(CursoDataModel.NOME,obj.getNome());
        dados.put(CursoDataModel.ID,obj.getId());
        dados.put(CursoDataModel.HORASNECESSARIAS,obj.getHorasnecessarias());

        return insert(CursoDataModel.TABELA,dados);
    }

    @Override
    public boolean alterar(Curso obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public List<Curso> listar() {
        return getAllCursos(CursoDataModel.TABELA);
    }public Curso getCursoByNome(String nome) {
        return getCursoByNome(CursoDataModel.TABELA,nome);
    }
    public Curso getCursoByID (String id){
        return getCursoById(CursoDataModel.TABELA,id);
    }
}
