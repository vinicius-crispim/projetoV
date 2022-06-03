package com.vinicius.crispim.vprojeto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Sugestao;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class EditActivity extends AppCompatActivity {

    SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
    EditText txtTitulo;
    EditText txtDescricao;
    Button btnImagem;
    Button btnEnviar;
    Coordenador coordenador;
    String fotoEmString;
    ImageView imgfoto;
    Integer id;
    SugestaoController sugestaoController;
    Sugestao sugestao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnEnviar = findViewById(R.id.btnPostarEdita);
        txtTitulo = findViewById(R.id.txtTituloPostarEdita);
        txtDescricao = findViewById(R.id.txtDescricaoPostarEdita);
        btnImagem = findViewById(R.id.btnImagemPostarEdita);
        imgfoto = findViewById(R.id.imgfotoPostarEdita);
        CarregaValoresCampos();
        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sugestao.setTitulo(txtTitulo.getText().toString());
                sugestao.setDescricao(txtDescricao.getText().toString());
                /*solicitacaoController.incluir(new Solicitacao(aluno, fotoEmString,
                        sdf1.format(new Date()),
                        txtTitulo.getText().toString(),Integer.parseInt(txtCarga.getText().toString()),
                        txtInstituicao.getText().toString(),
                        "EM ANALISE",txtDescricao.getText().toString(),
                        "Não Respondido",testecat));*/
                Log.i(AppUtil.TAG, "onCreate: sugestao postada " + sugestao.getTitulo());
                Alertar_onClick();

            }
        });
    }
    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    public void onActivityResult(int requestCode, int resultCode, Intent dados) {
        super.onActivityResult(requestCode,resultCode,dados);
        if (resultCode == -1) {
            try{
                Cursor c;
                Uri selectedImage = dados.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                c= getContentResolver().query(selectedImage,filePath,null,null,null);
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
                fotoEmString = Base64.encodeToString(fotoembyte,Base64.DEFAULT);
                sugestao.setImgSugestao(fotoEmString);
                Log.i(AppUtil.TAG, "onActivityResult: FOTO: "+sugestao.getImgSugestao());
                Toast.makeText(this, "Foto adicionada.",
                        Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }
    }
    protected void Alertar_onClick(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Deseja alterar a postagem?");
        alertDialog.setPositiveButton("alterar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sugestaoController.alterar(sugestao);
                Toast.makeText(EditActivity.this,"Sugestão alterada com sucesso!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditActivity.this,MenuCoordenadorActivity.class);
                coordenador= new Coordenador();
                Intent intentreceptor = getIntent();
                Bundle parametros = intentreceptor.getExtras();
                coordenador.setNome(parametros.getString("nome"));
                coordenador.setSenha(parametros.getString("senha"));
                coordenador.setCelular(parametros.getString("celular"));
                coordenador.setCPF(parametros.getString("CPF"));
                coordenador.setEmail(parametros.getString("email"));
                coordenador.setId(parametros.getInt("id"));
                parametros.putString("nome", coordenador.getNome());
                parametros.putString("senha", coordenador.getSenha());
                parametros.putString("celular", coordenador.getCelular());
                parametros.putString("CPF", coordenador.getCPF());
                parametros.putString("email", coordenador.getEmail());
                parametros.putInt("id",coordenador.getId());
                intent.putExtras(parametros);
                startActivity(intent);
                finish();

            }
        });
        alertDialog.show();
    }
    protected void CarregaValoresCampos(){
        sugestaoController = new SugestaoController(this);
        Bundle extra = this.getIntent().getExtras();
        int id = extra.getInt("id_sugestao");
        sugestao = sugestaoController.getSugestaoById(extra.getInt("id_sugestao"));
        this.id = id;
        txtTitulo.setText(sugestao.getTitulo());
        txtDescricao.setText(sugestao.getDescricao());
        txtDescricao.setText(sugestao.getDescricao());
        fotoEmString = sugestao.getImgSugestao();
        Log.i(AppUtil.TAG, "CarregaValoresCampos: id "+id);
        byte[] imagemBites;
        imagemBites = Base64.decode(fotoEmString,Base64.DEFAULT);
        Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites,0,imagemBites.length);
        imgfoto.setImageBitmap(imagemdecodificada);
    }
}