package com.example.rmaio.aps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario1 on 27/04/2017.
 */

public class Produto implements Parcelable{

    /*
        Classe produto implementa parcelable para recuperar informação na tela e passar dados entre activitys
     */
    private String nome;
    private String descricao;
    private String preco;
    private String fornecedor;
    public Produto(){

    }
    protected Produto(Parcel in) {
        nome = in.readString();
        descricao = in.readString();
        preco = in.readString();
        fornecedor = in.readString();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(preco);
        dest.writeString(fornecedor);
    }
}
