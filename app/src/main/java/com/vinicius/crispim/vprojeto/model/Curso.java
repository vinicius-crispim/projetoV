package com.vinicius.crispim.vprojeto.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso{
    private Integer id;
    private String nome;
    private Integer horasnecessarias;
    private Coordenador coordenador;

    public Curso(Integer id, String nome,Integer horasnecessarias, Coordenador coordenador) {
        this.id = id;
        this.nome = nome;
        this.horasnecessarias = horasnecessarias;
        this.coordenador=coordenador;
    }

    public Curso() {
    }

    public Integer getHorasnecessarias() {
        return horasnecessarias;
    }

    public void setHorasnecessarias(Integer horasnecessarias) {
        this.horasnecessarias = horasnecessarias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    @Override
    public String toString() {
        return                 nome;
    }

}
