package com.example.rmaio.aps;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.rmaio.aps.R.string.produto;

/**
 * Created by usuario1 on 27/04/2017.
 */

public class TelaModificaFornecedor extends Activity {
    EditText razao;
    EditText cnpj;
    EditText telefone;
    EditText endereco;
    Fornecedor fornecedor;
    int id;
    private SQLiteDatabase db;

    //inicia banco e atribui os componentes a variaveis para que possam ser alteradas
    // pesquisa fornecedor para que possa ser editado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_modifica_fornecedor);
        Banco banco = new Banco(this);
        db = banco.getWritableDatabase();
        razao = (EditText) findViewById(R.id.txtRazao);
        cnpj = (EditText) findViewById(R.id.txtCNPJ);

        endereco = (EditText) findViewById(R.id.txtEndereco);
        telefone = (EditText) findViewById(R.id.txtTelefone);
        pesquisarFornecedor();

    }
    //instancia fornecedor, define seus atributos e adiciona ao bundle para recuperação posterior
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fornecedor = new Fornecedor();
        fornecedor.setRazao(razao.getText().toString());
        fornecedor.setCnpj(cnpj.getText().toString());
        fornecedor.setTelefone(telefone.getText().toString());
        fornecedor.setEndereco(endereco.getText().toString());
        outState.putParcelable("fornecedor",fornecedor);
    }
    //recupera os dados do bundle e joga na tela

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fornecedor = savedInstanceState.getParcelable("fornecedor");
        razao.setText(fornecedor.getRazao());
        telefone.setText(fornecedor.getTelefone());
        endereco.setText(fornecedor.getEndereco());
        cnpj.setText(fornecedor.getCnpj());

    }

    public void voltar(View v){
        finish();
    }
    //pesquisa fornecedor com base na razao, atribui aos componentes os valores do banco
    private void pesquisarFornecedor(){
        String nFornecedor = getIntent().getStringExtra("fornecedor");
        String selection = Banco.TFornecedor.RAZAO +" = ? ";
        String[] selectionArgs = {nFornecedor};
        String orderBy = null;
        String having = null;
        String groupBy = null;
        Cursor c = db.query(Banco.TFornecedor.TABELA, null, selection, selectionArgs, groupBy, having, orderBy);
        if (c != null) {
            if  (c.moveToFirst()) {
                id = c.getInt(c.getColumnIndex(Banco.TProduto._ID));
                razao.setText(c.getString(c.getColumnIndex(Banco.TFornecedor.RAZAO)));
                endereco.setText(c.getString(c.getColumnIndex(Banco.TFornecedor.ENDERECO)));
                telefone.setText(c.getString(c.getColumnIndex(Banco.TFornecedor.TELEFONE)));
                cnpj.setText(c.getString(c.getColumnIndex(Banco.TFornecedor.CNPJ)));
            }
        }
    }
    //realiza a exclusao de um fornecedor com base em seu id, exibe toast confirmando ou nao a operaçao
    public void excluirFornecedor(View v){
        String whereClause = "_id = ?";
        String[] whereArgs = {String.valueOf(id)};
        if(db.delete(Banco.TFornecedor.TABELA,whereClause,whereArgs)!=-1){
            Toast.makeText(this,"Fornecedor excluido!", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this,"Falha ao excluir!", Toast.LENGTH_LONG).show();
        }
    }

    //altera fornecedor com base em seu id, salvando as alterações realizadas
    public void alterarFornecedor(View v){

        String whereClause = "_id = ?";
        String[] whereArgs = {String.valueOf(id)};

        ContentValues values = new ContentValues();
        values.put(Banco.TFornecedor.RAZAO,razao.getText().toString());
        values.put(Banco.TFornecedor.CNPJ,cnpj.getText().toString());
        values.put(Banco.TFornecedor.ENDERECO,endereco.getText().toString());
        values.put(Banco.TFornecedor.TELEFONE,telefone.getText().toString());

        if(db.update(Banco.TFornecedor.TABELA,values,whereClause,whereArgs)!=0){
            Toast.makeText(this,"Fornecedor alterado!", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this,"Falha ao alterar!", Toast.LENGTH_LONG).show();
        }
    }
}
