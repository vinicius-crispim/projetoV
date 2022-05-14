package com.vinicius.crispim.vprojeto.model;

import java.util.List;

public class Coordenador {
    private String nome;
    private Integer id;
    private String email;
    private String celular;
    private String senha;
    private String CPF;
    private List<Curso> cursos;


    public Coordenador(String nome, Integer id, String email, String celular, String CPF, List<Curso> curso,String senha) {
        this.senha=senha;
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.celular = celular;
        this.CPF = CPF;
        this.cursos = curso;
    }

    public Coordenador() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
