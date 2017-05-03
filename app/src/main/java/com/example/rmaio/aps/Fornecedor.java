package com.example.rmaio.aps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario1 on 27/04/2017.
 */

public class Fornecedor implements Parcelable {
    /*
        Classe fornecedor implementa parcelable para recuperar informação na tela e passar dados entre activitys
     */
    private String razao;
    private String cnpj;
    private String telefone;
    private String endereco;
    public Fornecedor(){

    }
    protected Fornecedor(Parcel in) {
        setRazao(in.readString());
        setCnpj(in.readString());
        setTelefone(in.readString());
        setEndereco(in.readString());
    }

    private static final Creator<Fornecedor> CREATOR = new Creator<Fornecedor>() {
        @Override
        public Fornecedor createFromParcel(Parcel in) {
            return new Fornecedor(in);
        }

        @Override
        public Fornecedor[] newArray(int size) {
            return new Fornecedor[size];
        }
    };

    public static Creator<Fornecedor> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getRazao());
        dest.writeString(getCnpj());
        dest.writeString(getTelefone());
        dest.writeString(getEndereco());
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
