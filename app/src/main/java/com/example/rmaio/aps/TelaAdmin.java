package com.example.rmaio.aps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by usuario1 on 26/04/2017.
 */

public class TelaAdmin extends Activity {


    /*
        tela administração, abre todas as telas referentes a cadastro e pesquisa; através da pesquisa
        é possivel deletar ou alterar registros;
        utiliza intent e o nome da classe para disparar a intenção
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_admin);
    }
    public void cadastrarCliente(View v){
        Intent it = new Intent(this,TelaCadastroCliente.class);
        startActivity(it);
    }
    public void cadastrarProduto(View v){
        Intent it = new Intent(this,TelaCadastroProduto.class);
        startActivity(it);
    }
    public void cadastrarFornecedor(View v){
        Intent it = new Intent(this,TelaCadastroFornecedor.class);
        startActivity(it);
    }
    public void pesquisarProduto(View v){
        Intent it = new Intent(this,TelaPesquisaProduto.class);
        startActivity(it);
    }
    public void pesquisarFornecedores(View v){
        Intent it = new Intent(this,TelaPesquisaFornecedor.class);
        startActivity(it);
    }
    public void pesquisarCliente(View v){
        Intent it = new Intent(this,TelaPesquisaCliente.class);
        startActivity(it);
    }

    public void voltar(View v){
        finish();
    }

}
