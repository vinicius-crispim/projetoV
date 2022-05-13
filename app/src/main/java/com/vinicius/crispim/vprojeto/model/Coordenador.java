package com.vinicius.crispim.vprojeto.model;

public class Coordenador {
    private String nome;
    private Integer id;
    private String email;
    private String celular;
    private String CPF;
    private Curso curso;


    public Coordenador(String nome, Integer id, String email, String celular, String CPF, Curso curso) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.celular = celular;
        this.CPF = CPF;
        this.curso = curso;
    }

    public Coordenador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
