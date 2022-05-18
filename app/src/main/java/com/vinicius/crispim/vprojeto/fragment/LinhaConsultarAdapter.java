package com.vinicius.crispim.vprojeto.fragment;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.controller.SugestaoController;
import com.vinicius.crispim.vprojeto.model.Sugestao;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

import java.util.ArrayList;
import java.util.List;



public class LinhaConsultarAdapter extends BaseAdapter{

    private static LayoutInflater layoutInflater = null;
    private HomeFragment lista;
    List<Sugestao> sugestoes = new ArrayList();
    private CardView card;

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
        card = (CardView) viewLinhaLista.findViewById(R.id.card);
        TextView txtTituloLinha = (TextView) viewLinhaLista.findViewById(R.id.txtTituloSugestao);
        TextView txtDescricaoLinha = (TextView) viewLinhaLista.findViewById(R.id.descLong1);
        ImageView imgSugestao = (ImageView) viewLinhaLista.findViewById(R.id.imgSugestao);
        byte[] imagemBites;
        imagemBites = Base64.decode(sugestoes.get(position).getImgSugestao(),Base64.DEFAULT);
        Bitmap imagemdecodificada = BitmapFactory.decodeByteArray(imagemBites,0,imagemBites.length);
        imgSugestao.setImageBitmap(imagemdecodificada);
        txtTituloLinha.setText(String.valueOf(sugestoes.get(position).getTitulo()));
        txtDescricaoLinha.setText(sugestoes.get(position).getDescricao());
    /*    imgSugestao.setVisibility(View.GONE);
        txtDescricaoLinha.setVisibility(View.GONE);
        card .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDescricaoLinha.getVisibility() == View.VISIBLE){
                    txtDescricaoLinha.setVisibility(View.GONE);
                    //imgSugestao.setVisibility(View.GONE);
                }
                else{
                  //  imgSugestao.setVisibility(View.VISIBLE);
                    txtDescricaoLinha.setVisibility(View.VISIBLE);
                }
            }
        });*/
        return viewLinhaLista;

    }




}
