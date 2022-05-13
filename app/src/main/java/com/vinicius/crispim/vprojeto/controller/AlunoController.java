package com.vinicius.crispim.vprojeto.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.appdatabase.AppDataBase;
import com.vinicius.crispim.vprojeto.datamodel.AlunoDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.view.RegistrarActivity;

import java.util.List;

public class AlunoController extends AppDataBase implements ICrud<Aluno>{

    ContentValues dados;

    public AlunoController(Context context) {
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
    public boolean incluir(Aluno obj) {
        dados = new ContentValues();
        dados.put(AlunoDataModel.NOME,obj.getNome());
        dados.put(AlunoDataModel.EMAIL,obj.getEmail());
        dados.put(AlunoDataModel.CPF,obj.getCPF());
        dados.put(AlunoDataModel.CELULAR,obj.getCelular());
        dados.put(AlunoDataModel.HORASFALTANDO,obj.getHorasFaltando());
        dados.put(AlunoDataModel.HORASFEITAS,obj.getHorasFeitas());
        dados.put(AlunoDataModel.MATRICULA,obj.getMatricula());
        dados.put(AlunoDataModel.SENHA,obj.getSenha());
        dados.put(AlunoDataModel.IDCURSO,obj.getCurso().getId());

        return insert(AlunoDataModel.TABELA,dados);
        //agora enviar os dados para classe AppDataBase, usando metodo que adiciona o obj no banco
    }

    @Override
    public boolean alterar(Aluno obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public List<Aluno> listar() {
        return  getAllAlunos(AlunoDataModel.TABELA,null,null);    }

    public List<Aluno> logar(String senha,Integer matricula) {
        return getAllAlunos(AlunoDataModel.TABELA,senha,matricula);    }
}
