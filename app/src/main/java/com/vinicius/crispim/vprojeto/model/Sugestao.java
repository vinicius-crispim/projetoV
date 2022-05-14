package com.vinicius.crispim.vprojeto.model;

public class Sugestao {
    private Integer id;
    private String titulo;
    private String descricao;
    private String imgSugestao;

    public Sugestao(Integer id, String titulo, String descricao, String imgSugestao) {
        this.titulo = titulo;
        this.id=id;
        this.descricao = descricao;
        this.imgSugestao = imgSugestao;
    }
    public Sugestao(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImgSugestao() {
        return imgSugestao;
    }

    public void setImgSugestao(String imgSugestao) {
        this.imgSugestao = imgSugestao;
    }
}
