package com.example.rmaio.aps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario1 on 25/04/2017.
 */

public class Cliente implements Parcelable{

    /*
        Classe cliente implementa parcelable para recuperar informação na tela e passar dados entre activitys
     */
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    public Cliente(){

    }
    public Cliente(String cpf, String nome, String email, String senha, String endereco){
        this.cpf = cpf;
        this.nome=nome;
        this.email=email;
        this.senha=senha;
        this.endereco=endereco;
    }
    protected Cliente(Parcel in) {
        cpf = in.readString();
        nome = in.readString();
        email = in.readString();
        senha = in.readString();
        endereco = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cpf);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeString(endereco);
    }
}
