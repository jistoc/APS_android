package com.example.rmaio.aps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.StringBuilderPrinter;

/**
 * Created by usuario1 on 25/04/2017.
 */

public class Banco extends SQLiteOpenHelper {

    /*
    Classe para utilização do SQLite, extebde SQliteOpenHelper, contem informações estáticas sobre
    cada tabela e seus campos, nome do arquivo e banco e versão. Metodos a implementar - onCreate e onUpdate
    criam e atualizam o banco de acordo com a necessidade (alteração na versão)
    db.execSQL executa a string em sql;
     */


    public static final String BANCO = "APS2.db";
    public static final int VERSAO = 34;

    public Banco(Context context){
        super(context, BANCO, null, VERSAO);
    }

    public static abstract class TCliente implements BaseColumns{

        public static final String TABELA = "clientes";
        public static final String NOME = "nome";
        public static final String CPF = "cpf";
        public static final String ENDERECO = "endereco";
        public static final String EMAIL = "email";
        public static final String SENHA = "senha";
    }

    public static abstract class TFornecedor implements BaseColumns{
        public static final String TABELA = "fornecedores";
        public static final String RAZAO = "razao";
        public static final String CNPJ = "cnpj";
        public static final String ENDERECO = "endereco";
        public static final String TELEFONE = "telefone";
    }
    public static abstract class TProduto implements BaseColumns{
        public static final String TABELA = "produtos";
        public static final String DESCRICAO = "descricao";
        public static final String PRECO = "preco";
        public static final String FORNECEDOR = "fornecedor";
        public static final String NOME = "nome";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        String cliente = builder.append("CREATE TABLE IF NOT EXISTS "+TCliente.TABELA)
                            .append("(")
                            .append(TCliente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                            .append(TCliente.CPF + " TEXT UNIQUE,")
                            .append(TCliente.NOME + " TEXT NOT NULL,")
                            .append(TCliente.ENDERECO + " TEXT NOT NULL,")
                            .append(TCliente.SENHA + " TEXT NOT NULL,")
                            .append(TCliente.EMAIL + " TEXT UNIQUE NOT NULL")
                            .append(");").toString();
        builder = new StringBuilder();
        String fornecedor = builder.append("CREATE TABLE IF NOT EXISTS "+TFornecedor.TABELA)
                .append("(")
                .append(TFornecedor._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(TFornecedor.CNPJ + " TEXT UNIQUE NOT NULL, ")
                .append(TFornecedor.RAZAO + " TEXT UNIQUE NOT NULL,")
                .append(TFornecedor.ENDERECO + " TEXT NOT NULL,")
                .append(TFornecedor.TELEFONE + " TEXT NOT NULL")
                .append(")").toString();
        builder = new StringBuilder();
        String produto = builder.append("CREATE TABLE IF NOT EXISTS "+TProduto.TABELA)
                .append("(")
                .append(TProduto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(TProduto.NOME + " TEXT UNIQUE, ")
                .append(TProduto.DESCRICAO+ " TEXT NOT NULL, ")
                .append(TProduto.PRECO + " TEXT NOT NULL, ")
                .append(TProduto.FORNECEDOR + " TEXT NOT NULL ")
                .append(")").toString();
        db.execSQL(cliente);
        db.execSQL(fornecedor);
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TCliente.TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TFornecedor.TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TProduto.TABELA);
        onCreate(db);
    }
}
