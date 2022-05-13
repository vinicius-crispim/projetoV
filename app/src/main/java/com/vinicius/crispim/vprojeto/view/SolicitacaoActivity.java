package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CategoriaController;
import com.vinicius.crispim.vprojeto.controller.CursoController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.model.Solicitacao;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoActivity extends AppCompatActivity {
    SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
    Spinner spinner;
    Categoria ultimo;
    EditText txtTitulo;
    EditText txtCarga;
    EditText txtInstituicao;
    EditText txtDescricao;
    Button btnImagem;
    Button btnEnviar;
    ImageView imgfoto;
    CategoriaController categoriaController;
    SolicitacaoController solicitacaoController;
    List<Categoria> categorias = new ArrayList<>();
    Solicitacao solicitacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_solicitacao);
        categoriaController = new CategoriaController(getApplicationContext());
        solicitacaoController = new SolicitacaoController(getApplicationContext());
        categorias = categoriaController.listar();
        solicitacao = new Solicitacao();
        Intent intentreceptor = getIntent();
        Bundle parametros = intentreceptor.getExtras();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        aluno.setNome(parametros.getString("nome"));
        aluno.setSenha(parametros.getString("senha"));
        aluno.setCelular(parametros.getString("celular"));
        aluno.setCPF(parametros.getString("CPF"));
        aluno.setEmail(parametros.getString("email"));
        aluno.setMatricula(parametros.getInt("matricula"));
        aluno.setHorasFeitas(parametros.getInt("horasfeitas"));
        aluno.setHorasFaltando(parametros.getInt("horasfaltando"));
        curso.setHorasnecessarias(parametros.getInt("horasnecessariascurso"));
        curso.setId(parametros.getInt("idcurso"));
        curso.setNome(parametros.getString("nomecurso"));
        aluno.setCurso(curso);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtDescricao);
        btnImagem = findViewById(R.id.btnImagem);
        txtCarga = findViewById(R.id.txtCarga);
        imgfoto = findViewById(R.id.imgfoto);
        txtInstituicao = findViewById(R.id.txtInstituicao);
        ArrayAdapter<Categoria> adpter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_dropdown_item,categorias);
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spnCategorias);
        spinner.setAdapter(adpter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ultimo = categorias.get(position);
                solicitacao.setCategoria(categorias.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitacao.setTitulo(txtTitulo.getText().toString());
                solicitacao.setInstituicao(txtInstituicao.getText().toString());
                solicitacao.setDescricao(txtDescricao.getText().toString());
                solicitacao.setCarga(Integer.parseInt(txtCarga.getText().toString()));
                solicitacao.setResposta("Não Respondido");
                solicitacao.setStatus("EM ANÁLISE");
                solicitacao.setData(sdf1.format(new Date()));
                solicitacao.setAluno(aluno);
                solicitacaoController.incluir(solicitacao);
                Log.i(AppUtil.TAG, "onCreate: aluno cadastrado "+solicitacao.getTitulo());
                Toast.makeText(SolicitacaoActivity.this,"Aluno "+solicitacao.getTitulo()+" registrado com sucesso",
                        Toast.LENGTH_SHORT).show();
                parametros.putString("nome",aluno.getNome());
                parametros.putString("senha",aluno.getSenha());
                parametros.putString("celular",aluno.getCelular());
                parametros.putString("CPF",aluno.getCPF());
                parametros.putString("email",aluno.getEmail());
                parametros.putInt("matricula",aluno.getMatricula());
                parametros.putInt("horasfeitas",aluno.getHorasFeitas());
                parametros.putInt("horasfaltando",aluno.getHorasFaltando());
                parametros.putString("nomecurso",aluno.getCurso().getNome());
                parametros.putInt("idcurso",aluno.getCurso().getId());
                parametros.putInt("horasnecessariascurso",aluno.getCurso().getHorasnecessarias());
                Intent troca = new Intent(SolicitacaoActivity.this, MenuActivity.class);
                troca.putExtras(parametros);
                startActivity(troca);
            }
        });
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        try {
            startActivityForResult(
                    Intent.createChooser(new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Select a File to Upload"),
                    FILE_SELECT_CODE);
            //startActivityForResult(intent,FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private static final int FILE_SELECT_CODE = 0;

    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {
        super.onActivityResult(requestCode,resultCode,dados);
        if (resultCode == RESULT_OK) {
            try{
                Cursor c;
                Uri selectedImage = dados.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                c=getContentResolver().query(selectedImage,filePath,null,null,null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap imagemGalery = (BitmapFactory.decodeFile(picturePath));
                imgfoto.setImageBitmap(imagemGalery);
                byte[] fotoembyte;
                ByteArrayOutputStream streamdafoto = new ByteArrayOutputStream();
                imagemGalery.compress(Bitmap.CompressFormat.JPEG,70,streamdafoto);
                fotoembyte = streamdafoto.toByteArray();
                String fotoEmString = Base64.encodeToString(fotoembyte,Base64.DEFAULT);
                solicitacao.setImagem(fotoEmString);
                Log.i(AppUtil.TAG, "onActivityResult: FOTO: "+solicitacao.getImagem());
                Toast.makeText(this, "FOTOSALVA.",
                        Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }
    }
}

