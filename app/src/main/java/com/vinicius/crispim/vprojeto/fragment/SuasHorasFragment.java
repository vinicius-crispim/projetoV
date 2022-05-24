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
import com.vinicius.crispim.vprojeto.controller.CoordenadorController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.datamodel.CoordenadorDataModel;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Categoria;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuasHorasFragment extends Fragment {
    SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
    Spinner spinner;
    Categoria ultimo;
    Categoria testecat;
    EditText txtTitulo;
    EditText txtCarga;
    EditText txtInstituicao;
    EditText txtDescricao;
    Button btnImagem;
    Button btnEnviar;
    Aluno aluno;
    String fotoEmString;
    ImageView imgfoto;
    CategoriaController categoriaController;
    SolicitacaoController solicitacaoController;
    List<Categoria> categorias = new ArrayList<>();
    Solicitacao solicitacao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suas_horas, container,false);
        btnEnviar = view.findViewById(R.id.btnEnviar);
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescricao = view.findViewById(R.id.txtDescricao);
        btnImagem = view.findViewById(R.id.btnImagem);
        txtCarga = view.findViewById(R.id.txtCarga);
        imgfoto = view.findViewById(R.id.imgfoto);
        txtInstituicao = view.findViewById(R.id.txtInstituicao);
        categoriaController = new CategoriaController(getContext());
        solicitacaoController = new SolicitacaoController(getContext());
        categorias = categoriaController.listar();
        solicitacao = new Solicitacao();
        testecat = new Categoria();
        Menu1Activity activity = (Menu1Activity) getActivity();
        Log.i(AppUtil.TAG, "onCreateView: ALUNO PASSADO:"+activity.getAluno().getNome());
        aluno = activity.getAluno();
        ArrayAdapter<Categoria> adpter = new ArrayAdapter<Categoria>(getContext(), android.R.layout.simple_spinner_dropdown_item,categoriaController.listar());
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = view.findViewById(R.id.spnCategorias);
        spinner.setAdapter(adpter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ultimo = categorias.get(position);
                solicitacao.setCategoria(categoriaController.listar().get(position));
                testecat = categoriaController.listar().get(position);
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
                CoordenadorController coordenadorController = new CoordenadorController(getContext());
                solicitacao.setTitulo(txtTitulo.getText().toString());
                solicitacao.setInstituicao(txtInstituicao.getText().toString());
                solicitacao.setDescricao(txtDescricao.getText().toString());
                solicitacao.setCarga(Integer.parseInt(txtCarga.getText().toString()));
                solicitacao.setResposta("Não Respondido");
                solicitacao.setStatus("EM ANÁLISE");
                solicitacao.setData(sdf1.format(new Date()));
                solicitacao.setAluno(aluno);
                Log.i(AppUtil.TAG, "onClick: IDCOORDENADOR: "+aluno.getCurso().getCoordenador().getId().toString());
               // solicitacao.setCoordenador(coordenadorController.getCoordenadorById(CoordenadorDataModel.TABELA,aluno.getCurso().getCoordenador().getId().toString()));
                /*solicitacaoController.incluir(new Solicitacao(aluno, fotoEmString,
                        sdf1.format(new Date()),
                        txtTitulo.getText().toString(),Integer.parseInt(txtCarga.getText().toString()),
                        txtInstituicao.getText().toString(),
                        "EM ANALISE",txtDescricao.getText().toString(),
                        "Não Respondido",testecat));*/
                solicitacaoController.incluir(solicitacao);
                Log.i(AppUtil.TAG, "onCreate: aluno cadastrado "+solicitacao.getTitulo());
                Toast.makeText(getContext(),"Solicitação enviada com sucesso, aguarde resposta do seu coordenador!",
                        Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    protected void Alertar_onClick(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("A solicitação será enviada");
        alertDialog.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Solicitação enviada com sucesso!",
                        Toast.LENGTH_SHORT).show();
                imgfoto.setImageBitmap(null);
                txtTitulo.setText("");
                txtDescricao.setText("");
                txtInstituicao.setText("");
                txtCarga.setText("");
                imgfoto.setImageBitmap(null);
            }
        });
        alertDialog.show();
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
                imagemGalery.compress(Bitmap.CompressFormat.JPEG,100,streamdafoto);
                fotoembyte = streamdafoto.toByteArray();
                fotoEmString = Base64.encodeToString(fotoembyte,Base64.DEFAULT);
                solicitacao.setImagem(fotoEmString);
                Log.i(AppUtil.TAG, "onActivityResult: FOTO: "+solicitacao.getImagem());
                Toast.makeText(getActivity(), "FOTOSALVA.",
                        Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }
    }

    }
