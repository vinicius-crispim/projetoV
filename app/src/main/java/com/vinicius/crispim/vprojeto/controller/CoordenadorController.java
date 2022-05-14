package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.AlunoDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CoordenadorDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Coordenador;

import java.util.List;

public class CoordenadorController extends AppDataBase implements ICrud<Coordenador>{

    ContentValues dados;

    public CoordenadorController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "AlunoController: Conectado");
    }

    private static final String TAG = "TESTANDO CADASTRO ";

    public boolean verificaCadastro(String senha, String senha2){
        if(senha.equals(senha2)){
            Log.i(TAG, "verificaCadastro: ALUNO CADASTRADO");
            return true;
        } else {
            Log.i(TAG, "verificaCadastro: ALUNO NÃO CADASTRADO, SENHAS DIFERENTES");
        return false;
        }
    }

    @Override
    public boolean incluir(Coordenador obj) {
        dados = new ContentValues();
        dados.put(AlunoDataModel.NOME,obj.getNome());
        dados.put(AlunoDataModel.EMAIL,obj.getEmail());
        dados.put(AlunoDataModel.CPF,obj.getCPF());
        dados.put(AlunoDataModel.CELULAR,obj.getCelular());
        dados.put(AlunoDataModel.SENHA,obj.getSenha());

        return insert(CoordenadorDataModel.TABELA,dados);
        //agora enviar os dados para classe AppDataBase, usando metodo que adiciona o obj no banco
    }

    @Override
    public boolean alterar(Coordenador obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public List<Coordenador> listar() {
        return  getAllCoordenadores(CoordenadorDataModel.TABELA);
    }

}
