package com.example.rmaio.aps;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by usuario1 on 25/04/2017.
 */

public class TelaCadastroFornecedor extends Activity {
    EditText telefone;
    EditText razao;
    EditText endereco;
    EditText cnpj;
    private SQLiteDatabase db;

    //inicialização dos componentes, instanciação do objeto responsavel pelo banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_fornecedor);
        cnpj = (EditText) findViewById(R.id.txtCNPJ);
        razao = (EditText) findViewById(R.id.txtRazao);
        endereco = (EditText) findViewById(R.id.txtEndereco);
        telefone = (EditText) findViewById(R.id.txtTelefone);
        Banco banco = new Banco(this);
        db = banco.getWritableDatabase();
    }
    //define os campos a serem preenchidos e os valores destes, executa a inserção, retorna uma string de sucesso ou falha
    private String inserir(){
        ContentValues valores = new ContentValues();
        valores.put(Banco.TFornecedor.CNPJ,cnpj.getText().toString());
        valores.put(Banco.TFornecedor.RAZAO,razao.getText().toString());
        valores.put(Banco.TFornecedor.ENDERECO,endereco.getText().toString());
        valores.put(Banco.TFornecedor.TELEFONE,telefone.getText().toString());
        if (db.insert(Banco.TFornecedor.TABELA,null,valores)!=-1)
            return "Fornecedor cadastrado!";
        else
            return "Erro ao cadastrar";

    }
    public void sair(View v){
        finish();
    }
    //valida os dados, caso validado, emite um toast com a string retornada pelo metodo inserir
    //"esconde" o teclado;
    public void cadastrarFornecedor(View v){
        if(validarDados()){
            Toast.makeText(this,inserir(), Toast.LENGTH_LONG).show();
            telefone.setText("");
            razao.setText("");
            endereco.setText("");
            cnpj.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            Toast.makeText(this,"Falha ao validar dados!", Toast.LENGTH_LONG).show();
        }
    }
    //valida dados, retorna true ou false
    private boolean validarDados(){
        if(razao.getText().length()<4)
            return false;
        if(cnpj.getText().length()!=14 || !isNumeric(cnpj.getText().toString()))
            return false;
        if(endereco.getText().length()<10)
            return false;
        if(telefone.getText().length()<9)
            return false;
        return true;
    }
    //verifica se string é numérica
    private boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
