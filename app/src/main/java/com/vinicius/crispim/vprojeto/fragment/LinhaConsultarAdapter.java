package com.vinicius.crispim.vprojeto.fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Sugestao;

import java.util.ArrayList;
import java.util.List;



public class LinhaConsultarAdapter extends BaseAdapter{

    private static LayoutInflater layoutInflater = null;
    private HomeFragment lista;
    List<Sugestao> sugestoes = new ArrayList();

    SugestaoController sugestaoController;

    public LinhaConsultarAdapter(HomeFragment lista, List<Sugestao> sugestoes){
        this.sugestoes = sugestoes;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sugestaoController = new SugestaoController(lista.getContext());
    }


    public void AtualizarLista(){
        this.sugestoes.clear();
        this.sugestoes = sugestaoController.listar();
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount(){
        return sugestoes.size();
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
        final View viewLinhaLista = layoutInflater.inflate(R.layout.acitivity_linha_consultar, null);
        TextView txtTituloLinha = (TextView) viewLinhaLista.findViewById(R.id.txtTituloLinha);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.txtDescricaoLinha);
        ImageView imgSugestao = (ImageView) viewLinhaLista.findViewById(R.id.imgSugestao);
        Button btnExcluir = (Button) viewLinhaLista.findViewById(R.id.btnExcluir);
        Button btnEditar = (Button) viewLinhaLista.findViewById(R.id.btnEditar);

        txtTituloLinha.setText(String.valueOf(sugestoes.get(position).getTitulo()));
        txtDescricaoLinha.setText(sugestoes.get(position).getDescricao());

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sugestaoController.deletar(sugestoes.get(position).getId());
                Toast.makeText(lista.getContext(), "Registro excluido com sucesso!", Toast.LENGTH_SHORT).show();
                AtualizarLista();

            }
        });

        /*btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lista, EditAcitivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id_aluno", alunoModels.get(position).getId());

                lista.startActivity(intent);

            }
        });*/
        return viewLinhaLista;

    }




}
