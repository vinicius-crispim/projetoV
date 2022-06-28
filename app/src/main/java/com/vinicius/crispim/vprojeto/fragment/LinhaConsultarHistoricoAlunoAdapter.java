package com.vinicius.crispim.vprojeto.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.controller.AlunoController;
import com.vinicius.crispim.vprojeto.controller.SolicitacaoController;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Solicitacao;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;
import com.vinicius.crispim.vprojeto.view.MenuActivity;

import java.util.ArrayList;
import java.util.List;


public class LinhaConsultarHistoricoAlunoAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    private HistoricoFragment lista;
    List<Solicitacao> solicitacoes = new ArrayList();
    private CardView card;
    private TextView txtStatus;
    SolicitacaoController solicitacaoController;
    Menu1Activity activity;

    public LinhaConsultarHistoricoAlunoAdapter(HistoricoFragment lista, List<Solicitacao> sugestoes) {
        this.solicitacoes = sugestoes;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.solicitacaoController = new SolicitacaoController(lista.getContext());
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
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar_historico, null);
        TextView txtTituloLinha = (TextView) viewLinhaLista.findViewById(R.id.txtTituloHistorico);
        TextView txtJustifica = (TextView) viewLinhaLista.findViewById(R.id.txtJustificativa2);
        TextView txtNomeCoordenador = (TextView) viewLinhaLista.findViewById(R.id.txtCoordenadorHistorico);
        TextView txtData = (TextView) viewLinhaLista.findViewById(R.id.txtDataHistorico);
        TextView txtInstituicao = (TextView) viewLinhaLista.findViewById(R.id.txtInstituicaoHistorico);
        TextView txtCarga = (TextView) viewLinhaLista.findViewById(R.id.txtCargaHistorico);
        TextView txtCategoria = (TextView) viewLinhaLista.findViewById(R.id.txtCategoriaSolicitacaoHistorico);
        txtStatus = (TextView) viewLinhaLista.findViewById(R.id.txtStatusHistorico);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.txtDescricaoHistorico);
        ImageView imgSugestao = (ImageView) viewLinhaLista.findViewById(R.id.imgHistorico);
        byte[] imagemBites;
        if (Base64.decode(solicitacoes.get(position).getImagem(), Base64.DEFAULT) != null) {
            imagemBites = Base64.decode(solicitacoes.get(position).getImagem(), Base64.DEFAULT);
            Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites, 0, imagemBites.length);
            imgSugestao.setImageBitmap(imagemdecodificada);
        }

        txtTituloLinha.setText(solicitacoes.get(position).getTitulo());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());
        if (solicitacoes.get(position).getResposta() != null) {
            txtJustifica.setText(solicitacoes.get(position).getResposta());
        }
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
