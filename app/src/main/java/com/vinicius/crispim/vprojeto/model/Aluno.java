package com.vinicius.crispim.vprojeto.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Aluno implements Parcelable {
    private String nome;
    private Integer matricula;
    private String email;
    private String celular;
    private String CPF;
    private String senha;
    private Curso curso;
    private Integer horasFeitas;
    private Integer horasFaltando;

    public Aluno(String nome, String senha,Integer matricula, String email, String celular, String CPF, Curso curso, Integer horasFeitas, Integer horasFaltando) {
        this.nome = nome;
        this.senha =senha;
        this.matricula = matricula;
        this.email = email;
        this.celular = celular;
        this.CPF = CPF;
        this.curso = curso;
        this.horasFeitas = horasFeitas;
        this.horasFaltando = horasFaltando;
    }

    public Aluno() {
    }

    protected Aluno(Parcel in) {
        nome = in.readString();
        if (in.readByte() == 0) {
            matricula = null;
        } else {
            matricula = in.readInt();
        }
        email = in.readString();
        celular = in.readString();
        CPF = in.readString();
        senha = in.readString();
        if (in.readByte() == 0) {
            horasFeitas = null;
        } else {
            horasFeitas = in.readInt();
        }
        if (in.readByte() == 0) {
            horasFaltando = null;
        } else {
            horasFaltando = in.readInt();
        }
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Integer getHorasFeitas() {
        return horasFeitas;
    }

    public void setHorasFeitas(Integer horasFeitas) {
        this.horasFeitas = horasFeitas;
    }

    public Integer getHorasFaltando() {
        return horasFaltando;
    }

    public void setHorasFaltando(Integer horasFaltando) {
        this.horasFaltando = horasFaltando;
    }

    @Override
    public String toString() {
        return                 nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        if (matricula == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(matricula);
        }
        parcel.writeString(email);
        parcel.writeString(celular);
        parcel.writeString(CPF);
        parcel.writeString(senha);
        if (horasFeitas == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(horasFeitas);
        }
        if (horasFaltando == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(horasFaltando);
        }
    }
}
