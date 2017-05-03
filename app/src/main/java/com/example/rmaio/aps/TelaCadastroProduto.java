package com.example.rmaio.aps;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario1 on 26/04/2017.
 */

public class TelaCadastroProduto extends Activity {

    private SQLiteDatabase db;
    String fornecedor;
    EditText nome;
    EditText preco;
    EditText descricao;

    //inicialização dos componentes, instanciação do objeto responsavel pelo banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_produto);
        Banco banco = new Banco(this);
        db = banco.getWritableDatabase();
        listarFornecedores();
    }
    public void voltar(View v){
        finish();
    }

    //verifica se o spinner foi populado(existem fornecedores cadastrados?)
    //valida os dados, se validos compara o retorno do metodo inserir, informa sucesso ou falha
    public void cadastrarProduto(View v){
        Spinner sp = (Spinner) findViewById(R.id.spFornecedor);
        if(sp.getCount()>0) {
            fornecedor = ((Spinner) findViewById(R.id.spFornecedor)).getSelectedItem().toString();
            nome = (EditText) findViewById(R.id.txtProduto);
            preco = (EditText) findViewById(R.id.txtPreco);
            descricao = (EditText) findViewById(R.id.txtDesc);
            if (validarDados()) {
                if (inserir() != -1) {
                    Toast.makeText(this, "Produto cadastrado!", Toast.LENGTH_LONG).show();
                    nome.setText("");
                    descricao.setText("");
                    preco.setText("");
                    nome.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } else {
                    Toast.makeText(this, "Falha ao cadastrar!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Falha ao validar dados!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Cadastre um fornecedor!!", Toast.LENGTH_LONG).show();
        }

    }
    //valida os dados
    private boolean validarDados(){
        if(fornecedor.length()<4)
            return false;
        if(nome.getText().length()<4)
            return false;
        if(preco.getText().toString().equals(""))
            return false;
        if(descricao.getText().length()<4)
            return false;
        return true;
    }

    //define os campos a serem preenchidos e os valores destes, executa a inserção, retorna -1 caso falha ou o numero da linha inserida
    private long inserir(){
        ContentValues valores = new ContentValues();
        valores.put(Banco.TProduto.FORNECEDOR,fornecedor);
        valores.put(Banco.TProduto.DESCRICAO,descricao.getText().toString());
        valores.put(Banco.TProduto.NOME,nome.getText().toString());
        valores.put(Banco.TProduto.PRECO,preco.getText().toString());
        return db.insert(Banco.TProduto.TABELA,null,valores);
    }
    //conecta com o banco e faz uma consulta na tabela fornecedores recuperando todos os registros
    //caso existam registros, popula o spinnner com a razao dos fornecedores
    private void listarFornecedores(){
        String[] columns = {Banco.TFornecedor.RAZAO};
        Cursor c = db.query(Banco.TFornecedor.TABELA, columns, null, null, null, null, null);
        if(c.getCount()>0){
            List<String> listaProdutos=new ArrayList<String>();
            try{
                while(c.moveToNext()){
                    listaProdutos.add(c.getString(c.getColumnIndex(Banco.TFornecedor.RAZAO)));
                }
            }finally {
                c.close();
                Spinner spinner = (Spinner) findViewById(R.id.spFornecedor);
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaProdutos);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

            }
        }
    }
}
