package com.vinicius.crispim.vprojeto.fragment;
import static com.vinicius.crispim.vprojeto.api.AppUtil.TAG;

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


public class LinhaConsultarSolicitacoesAlunoAdapter extends BaseAdapter{

    TextView txtStatusSolicitacaoAluno;
    private static LayoutInflater layoutInflater = null;
    private PerfilFragment lista = new PerfilFragment();
    List<Solicitacao> solicitacoes;
    SolicitacaoController solicitacaoController;
    public LinhaConsultarSolicitacoesAlunoAdapter(PerfilFragment lista, List<Solicitacao> solicitacoes){
        this.solicitacoes = solicitacoes;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.solicitacaoController = new SolicitacaoController(lista.getContext());
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
        TextView txtTituloSolicitacao = (TextView) viewLinhaLista.findViewById(R.id.txtTituloSolicitacaoAluno);
        TextView txtDataSolicitacaoAluno = (TextView) viewLinhaLista.findViewById(R.id.txtDataSolicitacaoAluno);
        txtStatusSolicitacaoAluno = (TextView) viewLinhaLista.findViewById(R.id.txtStatusSolicitacaoAluno);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.txtDescricaoSolicitacaoAluno);
        solicitacoes = new ArrayList<>();
        solicitacoes = solicitacaoController.listar();
        Button btnVerMais = (Button) viewLinhaLista.findViewById(R.id.btnVerMaisSolicitacaoAluno);
//        String teste = sugestoes.get(position).getTitulo();
//        Log.i(AppUtil.TAG, "getView: TESTE: "+teste);
//        String testedes = sugestoes.get(position).getTitulo();
        txtTituloSolicitacao.setText(solicitacoes.get(position).getTitulo());
        txtDataSolicitacaoAluno.setText(solicitacoes.get(position).getData());
        txtDescricaoLinha.setText(solicitacoes.get(position).getDescricao());
        txtStatusSolicitacaoAluno.setText(solicitacoes.get(position).getStatus());
        CorStatus();
        return viewLinhaLista;

    }
    protected void CorStatus(){
        if (txtStatusSolicitacaoAluno.getText().toString().equals("DEFERIDA")){
            txtStatusSolicitacaoAluno.setTextColor(Color.parseColor("#669900"));
        }else if (txtStatusSolicitacaoAluno.getText().toString().equals("INDEFERIDA")){
            txtStatusSolicitacaoAluno.setTextColor(Color.parseColor("#FFFF4444"));
        }else if (txtStatusSolicitacaoAluno.getText().toString().equals("EM ANÁLISE")){
            txtStatusSolicitacaoAluno.setTextColor(Color.parseColor("#ffffbb33"));
        }
    }
}
