package com.vinicius.crispim.vprojeto.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.view.VisualizaAlunoActivity;

import java.util.ArrayList;
import java.util.List;


public class LinhaConsultarSolicitacoesAlunoTesteAdapter extends BaseAdapter{

    TextView txtStatusSolicitacaoAluno;
    private static LayoutInflater layoutInflater = null;
    private VisualizaAlunoActivity lista;
    List<Solicitacao> solicitacoes;
    private TextView txtStatus;
    SolicitacaoController solicitacaoController;
    public LinhaConsultarSolicitacoesAlunoTesteAdapter(VisualizaAlunoActivity lista, List<Solicitacao> solicitacoes){
        this.solicitacoes = solicitacoes;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.solicitacaoController = new SolicitacaoController(lista.getApplicationContext());
    }
    public void AtualizarLista(){
        this.solicitacoes.clear();
        this.solicitacoes = solicitacaoController.listar();
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount(){
        return solicitacoes.size();
    }

    @Override
    public Object getItem (int position){
        return position;
    }

    @Override
    public long getItemId (int position){
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final View viewLinhaLista = layoutInflater.inflate(R.layout.acitivity_linha_consultar_solicitacoes_aluno, null);
        TextView txtTituloLinha = (TextView) viewLinhaLista.findViewById(R.id.txtTituloHistoricoCoordenador);
        TextView txtNomeCoordenador = (TextView) viewLinhaLista.findViewById(R.id.txtCoordenadorHistoricoCoordenador);
        TextView txtData = (TextView) viewLinhaLista.findViewById(R.id.txtDataHistoricoCoordenador);
        TextView txtInstituicao = (TextView) viewLinhaLista.findViewById(R.id.txtInstituicaoHistoricoCoordenador);
        TextView txtCarga = (TextView) viewLinhaLista.findViewById(R.id.txtCargaHistoricoCoordenador);
        TextView txtCategoria = (TextView) viewLinhaLista.findViewById(R.id.txtCategoriaSolicitacaoHistoricoCoordenador);
        txtStatus = (TextView) viewLinhaLista.findViewById(R.id.txtStatusHistoricoCoordenador);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.txtDescricaoHistoricoCoordenador);
        ImageView imgSugestao = (ImageView) viewLinhaLista.findViewById(R.id.imgHistoricoCoordenador);
        byte[] imagemBites;
        if (Base64.decode(solicitacoes.get(position).getImagem(), Base64.DEFAULT) != null) {
            imagemBites = Base64.decode(solicitacoes.get(position).getImagem(), Base64.DEFAULT);
            Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites, 0, imagemBites.length);
            imgSugestao.setImageBitmap(imagemdecodificada);
        }

        txtTituloLinha.setText(solicitacoes.get(position).getTitulo());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());


        txtTituloLinha.setText(String.valueOf(solicitacoes.get(position).getTitulo()));
        txtInstituicao.setText(solicitacoes.get(position).getInstituicao());
        txtCategoria.setText(solicitacoes.get(position).getCategoria().getNome());
        txtStatus.setText(solicitacoes.get(position).getStatus());
        CorStatus();
        txtData.setText(solicitacoes.get(position).getData());
        txtCarga.setText(String.valueOf(solicitacoes.get(position).getCarga()));
        txtNomeCoordenador.setText(solicitacoes.get(position).getCoordenador().getNome());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());
        return viewLinhaLista;

    }

    protected void CorStatus() {
        if (txtStatus.getText().toString().equals("DEFERIDA")) {
            txtStatus.setTextColor(Color.parseColor("#669900"));
        } else if (txtStatus.getText().toString().equals("INDEFERIDA")) {
            txtStatus.setTextColor(Color.parseColor("#FFFF4444"));
        } else if (txtStatus.getText().toString().equals("EM ANÁLISE")) {
            txtStatus.setTextColor(Color.parseColor("#ffffbb33"));
        }
    }

}
