package com.vinicius.crispim.vprojeto.fragment;

import static com.vinicius.crispim.vprojeto.api.AppUtil.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LinhaConsultarSolicitacoesAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    private SolicitacoesFragment lista = new SolicitacoesFragment();
    private List<Solicitacao> solicitacoes;
    private SolicitacaoController solicitacaoController;
    private String spinner;
    private Integer idCoordenador;
    public LinhaConsultarSolicitacoesAdapter(SolicitacoesFragment lista, List<Solicitacao> solicitacoes, String spinner, Integer idcoordenador) {
        this.solicitacoes = solicitacoes;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.solicitacaoController = new SolicitacaoController(lista.getContext());
        this.idCoordenador = idcoordenador;
        this.spinner = spinner;
    }


    public void AtualizarLista() {
        this.solicitacoes.clear();
        this.solicitacoes = solicitacaoController.listarByFiltro(spinner.toUpperCase(Locale.ROOT),idCoordenador);
        Log.i(TAG, "AtualizarLista: "+spinner+" - "+idCoordenador);
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return solicitacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View viewLinhaLista = layoutInflater.inflate(R.layout.acitivity_linha_consultar_solicitacoes, null);
        TextView txtTituloLinha = (TextView) viewLinhaLista.findViewById(R.id.txtTituloSolicitacaoResposta2);
        EditText txtJustificativa = viewLinhaLista.findViewById(R.id.txtjustificativaSolicitacaoResposta);
        TextView txtNomeAluno = (TextView) viewLinhaLista.findViewById(R.id.txtNomeSolicitacaoResposta);
        TextView txtData = (TextView) viewLinhaLista.findViewById(R.id.txtDataSolicitacaoResposta);
        TextView txtInstituicao = (TextView) viewLinhaLista.findViewById(R.id.txtInstituicaoSolicitacaoResposta);
        EditText txtCarga = viewLinhaLista.findViewById(R.id.txtCargaSolicitacaoResposta);
        TextView txtCategoria = (TextView) viewLinhaLista.findViewById(R.id.txtCategoriaSolicitacaoResposta);
        TextView txtStatus = (TextView) viewLinhaLista.findViewById(R.id.txtStatusSolicitacaoResposta);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.txtDescricaoSolicitacaoResposta);
        ImageView imgSugestao = (ImageView) viewLinhaLista.findViewById(R.id.imgSolicitacaoResposta);
        Button btnInvalidar = (Button) viewLinhaLista.findViewById(R.id.btnInvalidar);
        Button btnValidar = (Button) viewLinhaLista.findViewById(R.id.btnValidar);
        txtTituloLinha.setText(solicitacoes.get(position).getTitulo());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());
        byte[] imagemBites;
        imagemBites = Base64.decode(solicitacoes.get(position).getImagem(), Base64.DEFAULT);
        Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites, 0, imagemBites.length);
        imgSugestao.setImageBitmap(imagemdecodificada);
        Log.i(TAG, "getView: NULO? "+solicitacoes.get(position).getImagem());
        /*byte[] imagemBites;
        imagemBites = Base64.decode(solicitacoes.get(position).getImagem(),Base64.DEFAULT);
        Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites,0,imagemBites.length);
        imgSugestao.setImageBitmap(imagemdecodificada);*/
        txtTituloLinha.setText(String.valueOf(solicitacoes.get(position).getTitulo()));
        txtInstituicao.setText(solicitacoes.get(position).getInstituicao());
        txtCategoria.setText(solicitacoes.get(position).getCategoria().getNome());
        txtStatus.setText(solicitacoes.get(position).getStatus());
        txtData.setText(solicitacoes.get(position).getData());
        txtCarga.setText(String.valueOf(solicitacoes.get(position).getCarga()));
        txtNomeAluno.setText(solicitacoes.get(position).getAluno().getNome());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());
        txtJustificativa.setText(solicitacoes.get(position).getResposta());
        btnInvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AlertDialog.Builder justificaResposta = new AlertDialog.Builder(lista.getContext());
                justificaResposta.setTitle("Justificativa");
                justificaResposta.setMessage("Informe uma justificativa");
                justificaResposta.setCancelable(false);
                EditText editjustifica = new EditText(lista.getContext());
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                editjustifica.setLayoutParams(layout);
                justificaResposta.setView(editjustifica);
                justificaResposta.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        solicitacoes.get(position).setResposta(editjustifica.getText().toString());
                    }
                });
                justificaResposta.setNegativeButton("Cancelar",null);
                justificaResposta.create().show();*/
                solicitacoes.get(position).setCarga(Integer.parseInt(txtCarga.getText().toString()));
                if (solicitacoes.get(position).getStatus().equals("DEFERIDA")){
                    solicitacoes.get(position).getAluno().setHorasFeitas(
                            solicitacoes.get(position).getAluno().getHorasFeitas() - solicitacoes.get(position).getCarga()
                    );
                    solicitacoes.get(position).getAluno().setHorasFaltando(
                            solicitacoes.get(position).getAluno().getHorasFaltando() + solicitacoes.get(position).getCarga()
                    );
                    AlunoController alunoController = new AlunoController(lista.getContext());
                    Aluno aluno = solicitacoes.get(position).getAluno();
                    alunoController.alterar(aluno);
                }
                solicitacoes.get(position).setResposta(txtJustificativa.getText().toString());
                solicitacoes.get(position).setStatus("INDEFERIDA");
                solicitacaoController.alterar(solicitacoes.get(position));
                Toast.makeText(view.getContext(), "Solicitação invalidada!",
                        Toast.LENGTH_SHORT).show();
                AtualizarLista();
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitacoes.get(position).setStatus("DEFERIDA");
                solicitacoes.get(position).setCarga(Integer.parseInt(txtCarga.getText().toString()));
                solicitacoes.get(position).getAluno().setHorasFeitas(
                        solicitacoes.get(position).getAluno().getHorasFeitas() + solicitacoes.get(position).getCarga()
                );
                solicitacoes.get(position).getAluno().setHorasFaltando(
                        solicitacoes.get(position).getAluno().getHorasFaltando() - solicitacoes.get(position).getCarga()
                );
                AlunoController alunoController = new AlunoController(lista.getContext());
                solicitacoes.get(position).setResposta(txtJustificativa.getText().toString());
                Aluno aluno = solicitacoes.get(position).getAluno();
                alunoController.alterar(aluno);
                solicitacaoController.alterar(solicitacoes.get(position));
                Toast.makeText(view.getContext(), "Solicitação validada com sucesso!",
                        Toast.LENGTH_SHORT).show();
                AtualizarLista();
            }
        });
        return viewLinhaLista;

    }

}
