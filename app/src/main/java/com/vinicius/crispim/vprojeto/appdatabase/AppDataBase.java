package com.vinicius.crispim.vprojeto.appdatabase;

import static com.vinicius.crispim.vprojeto.api.AppUtil.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CoordenadorController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.datamodel.AlunoDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CategoriaDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CoordenadorDataModel;
import com.vinicius.crispim.vprojeto.datamodel.CursoDataModel;
import com.vinicius.crispim.vprojeto.datamodel.SolicitacaoDataModel;
import com.vinicius.crispim.vprojeto.datamodel.SugestaoDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {
    public static final String DB_NAME = "AppTimeManager.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;
    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Log.d(TAG, "AppDataBase: Criando Banco de Dados");

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AlunoDataModel.criarTabela());
        db.execSQL(CategoriaDataModel.criarTabela());
        db.execSQL(SolicitacaoDataModel.criarTabela());
        db.execSQL(CoordenadorDataModel.criarTabela());
        db.execSQL(CursoDataModel.criarTabela());
        db.execSQL(SugestaoDataModel.criarTabela());
        Log.i(TAG, "onCreate: Tabela Curso criada com sucesso"+CursoDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String tabela, ContentValues values){
        db = getWritableDatabase();

        boolean retorno = false;
        try {
            retorno=db.insert(tabela,null,values)>0;
        }catch (Exception e) {
            Log.d(TAG, "insert: ERRO "+e.getMessage());
        }
        return retorno;
    }
    public Boolean deleteById(String tabela, int id){
        db = getWritableDatabase();

        boolean retorno = false;
        try {
            retorno=db.delete(tabela,"id = ?",new String[] {String.valueOf(id)}) > 0;
        }catch (Exception e) {
            Log.d(TAG, "insert: ERRO "+e.getMessage());
        }
        return retorno;
    }
    public boolean update(String tabela, ContentValues dados){

        db = getWritableDatabase();

        boolean retorno = false;

        // Regra de negócio

        try {
            // O que deve ser realizado?
            // Salvar os dados

            //
            //
            //  retorno = db.insert(tabela,null,dados) > 0;
            //  retorno = db.delete(tabela,                  "id = ?",new String[] {String.valueOf(id)}) > 0;
            retorno = db.update(tabela,dados,"id = ?",new String[] {String.valueOf(dados.get("id"))}) > 0;


        }catch (Exception e){
            Log.d(TAG, "update: "+e.getMessage());
        }
        return retorno; // FALSE ou TRUE
    } public boolean updateAluno(String tabela, ContentValues dados){

        db = getWritableDatabase();

        boolean retorno = false;

        // Regra de negócio

        try {
            // O que deve ser realizado?
            // Salvar os dados

            //
            //
            //  retorno = db.insert(tabela,null,dados) > 0;
            //  retorno = db.delete(tabela,                  "id = ?",new String[] {String.valueOf(id)}) > 0;
            retorno = db.update(tabela,dados,"matricula = ?",new String[] {String.valueOf(dados.get("matricula"))}) > 0;


        }catch (Exception e){
            Log.d(TAG, "update: "+e.getMessage());
        }
        return retorno; // FALSE ou TRUE
    }
    @SuppressLint("Range")
    public List<Aluno> getAllAlunos(String tabela, String senha,Integer matricula){
        List<Aluno> alunos = new ArrayList<>();
        db = getWritableDatabase();
        if(senha !=null && matricula != null) {
            String sql = "select * from " + tabela + " where senha = '" + senha + "' and matricula = "+matricula;
            Cursor cursor;
            Cursor cursor2;

            cursor = db.rawQuery(sql, null);
            Aluno obj;
            Curso curso;
            if (cursor.moveToFirst()) {
                do {
                    obj = new Aluno();
                    curso = new Curso();

                    obj.setMatricula(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.MATRICULA)));
                    obj.setNome(cursor.getString(cursor.getColumnIndex(AlunoDataModel.NOME)));
                    obj.setHorasFaltando(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFALTANDO)));
                    obj.setHorasFeitas(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFEITAS)));
                    obj.setSenha(cursor.getString(cursor.getColumnIndex(AlunoDataModel.SENHA)));
                    obj.setCelular(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CELULAR)));
                    obj.setCPF(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CPF)));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex(AlunoDataModel.EMAIL)));
                    Integer idcurso = cursor.getInt(cursor.getColumnIndex(AlunoDataModel.IDCURSO));
                    Log.i(TAG, "getAllAlunos: "+idcurso);
                    Log.i(TAG, "getAllAlunos: "+obj);
                    List<Curso> cursos = getAllCursos(CursoDataModel.TABELA);
                    for (Curso curso1:cursos) {
                        if (curso1.getId() == idcurso){
                            obj.setCurso(curso1);
                            Log.i(TAG, "getAllAlunos: CURSO: "+obj.getCurso());
                        }
                    }
                    alunos.add(obj);
                    Log.i("LISTAR", "getAllCursos: " + obj.getNome());
                } while (cursor.moveToNext());
            }
        }else{
            String sql = "select * from " + tabela;
            Cursor cursor;


            cursor = db.rawQuery(sql, null);
            Aluno obj;
            Curso curso;
            if (cursor.moveToFirst()) {
                do {
                    obj = new Aluno();
                    curso = new Curso();
                    obj.setMatricula(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.MATRICULA)));
                    obj.setNome(cursor.getString(cursor.getColumnIndex(AlunoDataModel.NOME)));
                    obj.setHorasFaltando(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFALTANDO)));
                    obj.setHorasFeitas(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFEITAS)));
                    obj.setSenha(cursor.getString(cursor.getColumnIndex(AlunoDataModel.SENHA)));
                    obj.setCelular(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CELULAR)));
                    obj.setCPF(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CPF)));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex(AlunoDataModel.EMAIL)));
                    Integer idcurso = cursor.getInt(cursor.getColumnIndex(AlunoDataModel.IDCURSO));
                    List<Curso> cursos = getAllCursos(CursoDataModel.TABELA);
                    for (Curso curso1:cursos) {
                        if (curso1.getId() == idcurso){
                            obj.setCurso(curso1);
                            Log.i(TAG, "getAllAlunos: CURSO: "+obj.getCurso());
                        }
                    }
                    alunos.add(obj);
                    Log.i("LISTAR", "getAllCursos: " + obj.getNome());
                } while (cursor.moveToNext());
            }
        }

        return alunos;
    }@SuppressLint("Range")
    public List<Coordenador> getAllCoordenador(String tabela, String senha){
        List<Coordenador> coordenadores = new ArrayList<>();
        db = getWritableDatabase();
        if(senha !=null) {
            String sql = "select * from " + tabela + " where senha = '" + senha+"'";
            Cursor cursor;
            cursor = db.rawQuery(sql, null);
            Coordenador obj;
            if (cursor.moveToFirst()) {
                do {
                    obj = new Coordenador();

                    obj.setId(cursor.getInt(cursor.getColumnIndex(CoordenadorDataModel.ID)));
                    obj.setNome(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.NOME)));
                    obj.setSenha(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.SENHA)));
                    obj.setCelular(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CELULAR)));
                    obj.setCPF(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CPF)));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.EMAIL)));
                    coordenadores.add(obj);
                    Log.i("LISTAR", "getAllCoordenadores: " + obj.getNome());
                } while (cursor.moveToNext());
            }
        }else{
            String sql = "select * from " + tabela;
            Cursor cursor;


            cursor = db.rawQuery(sql, null);
            Coordenador obj;
            if (cursor.moveToFirst()) {
                do {
                    obj = new Coordenador();
                    obj.setId(cursor.getInt(cursor.getColumnIndex(CoordenadorDataModel.ID)));
                    obj.setNome(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.NOME)));
                    obj.setSenha(cursor.getString(cursor.getColumnIndex(AlunoDataModel.SENHA)));
                    obj.setCelular(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CELULAR)));
                    obj.setCPF(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CPF)));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex(AlunoDataModel.EMAIL)));
                    coordenadores.add(obj);
                    Log.i("LISTAR", "getAllCoordenadores: " + obj.getNome());
                } while (cursor.moveToNext());
            }
        }

        return coordenadores;
    }
    @SuppressLint("Range")
    public List<Curso> getAllCursos(String tabela){
        List<Curso> cursos = new ArrayList<>();
        db = getWritableDatabase();
        String sql = "select * from "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Curso obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Curso();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CursoDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CursoDataModel.NOME)));
                obj.setHorasnecessarias(cursor.getInt(cursor.getColumnIndex(CursoDataModel.HORASNECESSARIAS)));
                Integer idcoordenador = cursor.getInt(cursor.getColumnIndex(CursoDataModel.IDCOORDENADOR));
                List<Coordenador> coordenadores = getAllCoordenadores(CoordenadorDataModel.TABELA);
                for (Coordenador coordenador1:coordenadores) {
                    if (coordenador1.getId() == idcoordenador){
                        obj.setCoordenador(coordenador1);
                        Log.i(TAG, "getAllAlunos: CURSO AQUI: "+obj.getCoordenador().getNome());
                    }
                }
                cursos.add(obj);
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
            }while (cursor.moveToNext());
        }

        return cursos;
    } @SuppressLint("Range")
    public List<Coordenador> getAllCoordenadores(String tabela){
        List<Coordenador> coordenadores = new ArrayList<>();
        db = getWritableDatabase();
        String sql = "select * from "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Coordenador obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Coordenador();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CoordenadorDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.NOME)));
                obj.setEmail(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.EMAIL)));
                obj.setCPF(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CPF)));
                obj.setCelular(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CELULAR)));
                obj.setSenha(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.SENHA)));
                coordenadores.add(obj);
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
            }while (cursor.moveToNext());
        }

        return coordenadores;
    }@SuppressLint("Range")
    public Curso getCursoByNome(String tabela,String nome){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where nome = '"+nome+"'";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Curso obj;
        if(cursor.moveToFirst()){
                obj = new Curso();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CursoDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CursoDataModel.NOME)));
                obj.setHorasnecessarias(cursor.getInt(cursor.getColumnIndex(CursoDataModel.HORASNECESSARIAS)));
                return obj;
        }else{
            return null;
        }

    }@SuppressLint("Range")
    public Curso getCursoById(String tabela,String nome){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where id = "+nome+"";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Curso obj;
        if(cursor.moveToFirst()){
                obj = new Curso();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CursoDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CursoDataModel.NOME)));
                obj.setHorasnecessarias(cursor.getInt(cursor.getColumnIndex(CursoDataModel.HORASNECESSARIAS)));
                Integer idcoordenador=cursor.getInt(cursor.getColumnIndex(CursoDataModel.IDCOORDENADOR));
                cursor = db.rawQuery(sql,null);
                Coordenador coordenador = new Coordenador();
                coordenador = getCoordenadorById(CoordenadorDataModel.TABELA,idcoordenador.toString());
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
                obj.setCoordenador(coordenador);
                return obj;
        }else{
            return null;
        }

    }
    @SuppressLint("Range")
    public Coordenador getCoordenadorById2(String tabela,String nome){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where id = "+nome+"";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Coordenador obj;
        if(cursor.moveToFirst()) {
            obj = new Coordenador();

            obj.setId(cursor.getInt(cursor.getColumnIndex(CoordenadorDataModel.ID)));
            obj.setNome(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.NOME)));
            obj.setEmail(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.EMAIL)));
            obj.setCPF(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CPF)));
            obj.setCelular(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CELULAR)));
            obj.setSenha(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.SENHA)));
            return obj;
        }
        return null;
    }
    @SuppressLint("Range")
    public Coordenador getCoordenadorById(String tabela,String idcurso){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where id = "+idcurso+"";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Coordenador obj;
        if(cursor.moveToFirst()){
                obj = new Coordenador();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CoordenadorDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.NOME)));
                obj.setSenha(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.SENHA)));
                obj.setCelular(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CELULAR)));
                obj.setCPF(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.CPF)));
                obj.setEmail(cursor.getString(cursor.getColumnIndex(CoordenadorDataModel.EMAIL)));
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
                return obj;
        }else{
            return null;
        }

    }@SuppressLint("Range")
    public Aluno getAlunoById(String tabela,String id){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where matricula = "+id+"";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Aluno obj;
        if(cursor.moveToFirst()){
                obj = new Aluno();

                obj.setMatricula(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.MATRICULA)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(AlunoDataModel.NOME)));
                obj.setSenha(cursor.getString(cursor.getColumnIndex(AlunoDataModel.SENHA)));
                obj.setCelular(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CELULAR)));
                obj.setCPF(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CPF)));
                obj.setEmail(cursor.getString(cursor.getColumnIndex(AlunoDataModel.EMAIL)));
                obj.setHorasFaltando(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFALTANDO)));
                obj.setHorasFeitas(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFEITAS)));
               Integer idcurso = cursor.getInt(cursor.getColumnIndex(AlunoDataModel.IDCURSO));
               Curso cursos = getCursoById(CursoDataModel.TABELA,idcurso.toString());
               obj.setCurso(cursos);
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
                return obj;
        }else{
            return null;
        }

    }
    @SuppressLint("Range")
    public Aluno getAlunoByNome(String tabela,String nome){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where nome = '"+nome+"'";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Aluno obj;
        if(cursor.moveToFirst()){
            obj = new Aluno();

            obj.setMatricula(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.MATRICULA)));
            obj.setNome(cursor.getString(cursor.getColumnIndex(AlunoDataModel.NOME)));
            obj.setSenha(cursor.getString(cursor.getColumnIndex(AlunoDataModel.SENHA)));
            obj.setCelular(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CELULAR)));
            obj.setCPF(cursor.getString(cursor.getColumnIndex(AlunoDataModel.CPF)));
            obj.setEmail(cursor.getString(cursor.getColumnIndex(AlunoDataModel.EMAIL)));
            obj.setHorasFaltando(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFALTANDO)));
            obj.setHorasFeitas(cursor.getInt(cursor.getColumnIndex(AlunoDataModel.HORASFEITAS)));
            Integer idcurso = cursor.getInt(cursor.getColumnIndex(AlunoDataModel.IDCURSO));
            Curso cursos = getCursoById(CursoDataModel.TABELA,idcurso.toString());
            obj.setCurso(cursos);
            Log.i("LISTAR", "getAllCursos: "+obj.getNome());
            return obj;
        }else{
            return null;
        }

    }@SuppressLint("Range")
    public List<Categoria> getAllCategoria(String tabela){
        List<Categoria> categorias = new ArrayList<>();
        db = getWritableDatabase();
        String sql = "select * from "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Categoria obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Categoria();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CategoriaDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CategoriaDataModel.NOME)));
                categorias.add(obj);
                Log.i("LISTAR", "getAllCursos: "+obj.getNome());
            }while (cursor.moveToNext());
        }

        return categorias;
    }@SuppressLint("Range")
    public Categoria getCategoriaById(String tabela,String id){
        db = getWritableDatabase();
        String sql = "select * from "+tabela+" where id = "+id+"";
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Categoria obj = new Categoria();
        if(cursor.moveToFirst()){
            do {
                obj = new Categoria();

                obj.setId(cursor.getInt(cursor.getColumnIndex(CategoriaDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(CategoriaDataModel.NOME)));
            }while (cursor.moveToNext());
        }

        return obj;
    }@SuppressLint("Range")
    public List<Sugestao> getAllSugestao(String tabela){
        List<Sugestao> sugestoes = new ArrayList<>();
        db = getWritableDatabase();
        String sql = "select * from "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Sugestao obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Sugestao();

                obj.setId(cursor.getInt(cursor.getColumnIndex(SugestaoDataModel.ID)));
                obj.setTitulo(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.TITULO)));
                obj.setDescricao(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.DESCRICAO)));
                obj.setImgSugestao(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.IMGSUGESTAO)));
                sugestoes.add(obj);
            }while (cursor.moveToNext());
        }

        return sugestoes;
    }@SuppressLint("Range")
    public Sugestao getSugestaoById(String tabela, Integer id){
        db = getWritableDatabase();
        Log.i(TAG, "getById: ID"+id);
        String sql = "select * from "+tabela+" where id = "+id;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Sugestao obj;
        if(cursor.moveToFirst()){
            obj = new Sugestao();
            obj.setId(cursor.getInt(cursor.getColumnIndex(SugestaoDataModel.ID)));
            obj.setTitulo(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.TITULO)));
            obj.setDescricao(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.DESCRICAO)));
            obj.setImgSugestao(cursor.getString(cursor.getColumnIndex(SugestaoDataModel.IMGSUGESTAO)));
            return obj;
        }else{
            return null;
        }
    }@SuppressLint("Range")
    public List<Solicitacao> getAllSolicitacao(String tabela){
        List<Solicitacao> solicitacaos = new ArrayList<>();
        db = getWritableDatabase();
        String sql = "select * from "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Solicitacao obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Solicitacao();

                obj.setId(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.ID)));
                obj.setData(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DATA)));
                obj.setStatus(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.STATUS)));
                obj.setDescricao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DESCRICAO)));
                obj.setInstituicao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.INSTITUICAO)));
                obj.setResposta(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.RESPOSTA)));
                obj.setTitulo(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.TITULO)));
                obj.setCarga(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.CARGA)));
                obj.setImagem(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.IMAGEM)));
                Integer idaluno = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDALUNO));
                Integer idcoordenador = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCOORDENADOR));
                Integer idcategoria = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCATEGORIA));
                Aluno alunos = getAlunoById(AlunoDataModel.TABELA,idaluno.toString());
                Coordenador coordenador = getCoordenadorById(CoordenadorDataModel.TABELA,idcoordenador.toString());
                Categoria categoria = getCategoriaById(CategoriaDataModel.TABELA,idcategoria.toString());
                obj.setAluno(alunos);
                obj.setCoordenador(coordenador);
                obj.setCategoria(categoria);
                solicitacaos.add(obj);
                Log.i("LISTAR", "getAllSolicitacao: "+obj.getTitulo());
            }while (cursor.moveToNext());
        }

        return solicitacaos;
    }@SuppressLint("Range")
    public List<Solicitacao> getAllSolicitacaoByAluno(String tabela, Integer id, String status){
        List<Solicitacao> solicitacaos = new ArrayList<>();
        db = getWritableDatabase();
        String sql;
        if(status.equals("TODAS")){
            sql = "select * from "+tabela+" where idaluno = "+id;
        }else {
            sql = "select * from "+tabela+" where idaluno = "+id+" and status = '"+status+"'";
        }
        Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Solicitacao obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Solicitacao();

                obj.setId(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.ID)));
                obj.setData(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DATA)));
                obj.setStatus(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.STATUS)));
                obj.setDescricao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DESCRICAO)));
                obj.setInstituicao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.INSTITUICAO)));
                obj.setResposta(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.RESPOSTA)));
                obj.setTitulo(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.TITULO)));
                obj.setCarga(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.CARGA)));
                obj.setImagem(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.IMAGEM)));
                Integer idaluno = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDALUNO));
                Integer idcoordenador = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCOORDENADOR));
                Integer idcategoria = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCATEGORIA));
                Aluno alunos = getAlunoById(AlunoDataModel.TABELA,idaluno.toString());
                Coordenador coordenador = getCoordenadorById(CoordenadorDataModel.TABELA,idcoordenador.toString());
                Categoria categoria = getCategoriaById(CategoriaDataModel.TABELA,idcategoria.toString());
                obj.setAluno(alunos);
                obj.setCoordenador(coordenador);
                obj.setCategoria(categoria);
                solicitacaos.add(obj);
                Log.i("LISTAR", "getAllSolicitacao: "+obj.getTitulo());
            }while (cursor.moveToNext());
        }

        return solicitacaos;
    }@SuppressLint("Range")
    public List<Solicitacao> getAllSolicitacaoFiltro(String tabela, String filtro, Integer idcoordenador2){
        List<Solicitacao> solicitacaos = new ArrayList<>();
        db = getWritableDatabase();
        String sql;
        if(filtro.equals("TODAS")){
            sql = "select * from "+tabela+" where idcoordenador = "+idcoordenador2;
        }else {
            sql = "select * from " + tabela + " where status = '" + filtro + "' and idcoordenador = "+idcoordenador2;
        }Cursor cursor;

        cursor = db.rawQuery(sql,null);
        Solicitacao obj;
        if(cursor.moveToFirst()){
            do {
                obj = new Solicitacao();

                obj.setId(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.ID)));
                obj.setData(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DATA)));
                obj.setStatus(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.STATUS)));
                obj.setDescricao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.DESCRICAO)));
                obj.setInstituicao(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.INSTITUICAO)));
                obj.setResposta(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.RESPOSTA)));
                obj.setTitulo(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.TITULO)));
                obj.setCarga(cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.CARGA)));
                obj.setImagem(cursor.getString(cursor.getColumnIndex(SolicitacaoDataModel.IMAGEM)));
                Integer idaluno = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDALUNO));
                Integer idcoordenador = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCOORDENADOR));
                Integer idcategoria = cursor.getInt(cursor.getColumnIndex(SolicitacaoDataModel.IDCATEGORIA));
                Aluno alunos = getAlunoById(AlunoDataModel.TABELA,idaluno.toString());
                Coordenador coordenador = getCoordenadorById(CoordenadorDataModel.TABELA,idcoordenador.toString());
                Categoria categoria = getCategoriaById(CategoriaDataModel.TABELA,idcategoria.toString());
                obj.setAluno(alunos);
                obj.setCoordenador(coordenador);
                obj.setCategoria(categoria);
                solicitacaos.add(obj);
            }while (cursor.moveToNext());
        }
        return solicitacaos;
    }
}
