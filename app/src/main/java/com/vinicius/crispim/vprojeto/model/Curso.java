package com.vinicius.crispim.vprojeto.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso{
    private Integer id;
    private String nome;
    private Integer horasnecessarias;

    public Curso(Integer id, String nome,Integer horasnecessarias) {
        this.id = id;
        this.nome = nome;
        this.horasnecessarias = horasnecessarias;
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

    @Override
    public String toString() {
        return                 nome;
    }

}
