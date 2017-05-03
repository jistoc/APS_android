package com.example.rmaio.aps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by usuario1 on 25/04/2017.
 */

public class TelaCliente extends Activity {

    //Inicia a tela adicionando o nome do cliente ao bem vindo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cliente);
        TextView bemVindo = (TextView) findViewById(R.id.txtSaudacao);
        Cliente cliente = (Cliente) getIntent().getExtras().getParcelable("cliente");

        bemVindo.setText(bemVindo.getText()+" "+cliente.getNome());
    }
    //finaliza activity
    public void sair(View v){
        finish();
    }
}
