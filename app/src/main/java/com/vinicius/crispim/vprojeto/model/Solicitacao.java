package com.vinicius.crispim.vprojeto.model;

public class Solicitacao {
    private Integer id;
    private Aluno aluno;
    private Coordenador coordenador;
    private String imagem;
    private String data;
    private String titulo;
    private Integer carga;
    private String instituicao;
    private String status;
    private String descricao;
    private String resposta;
    private Categoria categoria;

    public Solicitacao(Integer id, Aluno aluno, Coordenador coordenador, String imagem, String data, String titulo, Integer carga, String instituicao, String status, String descricao, String resposta, Categoria categoria) {
        this.id = id;
        this.aluno = aluno;
        this.coordenador = coordenador;
        this.imagem = imagem;
        this.data = data;
        this.titulo = titulo;
        this.carga = carga;
        this.instituicao = instituicao;
        this.status = status;
        this.descricao = descricao;
        this.resposta = resposta;
        this.categoria = categoria;
    }

    public Solicitacao(Integer id, Aluno aluno, String imagem, String data, String titulo, Integer carga, String instituicao, String status, String descricao, String resposta, Categoria categoria) {
        this.imagem = imagem;
        this.aluno = aluno;
        this.id = id;
        this.data = data;
        this.titulo = titulo;
        this.carga = carga;
        this.instituicao = instituicao;
        this.status = status;
        this.descricao = descricao;
        this.resposta = resposta;
        this.categoria = categoria;
    }
    public Solicitacao(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
