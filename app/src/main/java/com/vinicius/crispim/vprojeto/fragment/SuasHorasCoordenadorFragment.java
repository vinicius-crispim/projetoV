package com.vinicius.crispim.vprojeto.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.CategoriaController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;
import com.vinicius.crispim.vprojeto.view.MenuCoordenadorActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuasHorasCoordenadorFragment extends Fragment {
    SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
    EditText txtTitulo;
    EditText txtDescricao;
    Button btnImagem;
    Button btnEnviar;
    Coordenador coordenador;
    String fotoEmString;
    ImageView imgfoto;
    SugestaoController sugestaoController;
    Sugestao sugestao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suas_horas_coordenador, container,false);
        btnEnviar = view.findViewById(R.id.btnPostar);
        txtTitulo = view.findViewById(R.id.txtTituloPostar);
        txtDescricao = view.findViewById(R.id.txtDescricaoPostar);
        btnImagem = view.findViewById(R.id.btnImagemPostar);
        imgfoto = view.findViewById(R.id.imgfotoPostar);
        sugestaoController = new SugestaoController(getContext());
        sugestao = new Sugestao();
        MenuCoordenadorActivity activity = (MenuCoordenadorActivity) getActivity();
        Log.i(AppUtil.TAG, "onCreateView: COORDENADOR:"+activity.getCoordenador().getNome());
        coordenador = activity.getCoordenador();
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
                Alertar_onClick();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

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
            Toast.makeText(getActivity(), "Favor instalar um gerenciador de arquivos.",
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
                c=getActivity().getContentResolver().query(selectedImage,filePath,null,null,null);
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
                Toast.makeText(getActivity(), "Foto adicionada.",
                        Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }
    }
    protected void Alertar_onClick(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Deseja realizar a postagem");
        alertDialog.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sugestaoController.incluir(sugestao);
                Toast.makeText(getContext(),"Sugestão postada com sucesso!",
                        Toast.LENGTH_SHORT).show();
                imgfoto.setImageBitmap(null);
                txtTitulo.setText("");
                txtDescricao.setText("");
                Bundle parametros = new Bundle();
                parametros.putString("nome", coordenador.getNome());
                parametros.putString("senha", coordenador.getSenha());
                parametros.putString("celular", coordenador.getCelular());
                parametros.putString("CPF", coordenador.getCPF());
                parametros.putString("email", coordenador.getEmail());
                parametros.putInt("id", coordenador.getId());
                Intent intent = new Intent(getContext(),MenuCoordenadorActivity.class);
                intent.putExtras(parametros);
                startActivity(intent);
            }
        });
        alertDialog.show();
    }
}
