package com.example.rmaio.aps;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class TelaInicio extends Activity {

    Cliente cliente = new Cliente();
    private SQLiteDatabase db;
    EditText senha;
    EditText email;
    //define o acesso de apenas leitura do banco, atribuição dos componentes email e senha
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicio);
        Banco banco = new Banco(this);
        db = banco.getReadableDatabase();
        email = (EditText) findViewById(R.id.txtEmail);
        senha = (EditText) findViewById(R.id.txtSenha);

    }
    //inicia activity para cadastro de cliente
    public void criarConta(View v){
        Intent it = new Intent(this,TelaCadastroCliente.class);
        startActivity(it);

    }
    //verifica se o usuario é admin (admin usuario e nimda senha)
    //caso contrário verifica se o usuário é cadastrado (realiza uma busca com base em usuario e senha, se o resultado for >0
    //usuário correto. caso contrário exibi toast
    public void login(View v){
        if(email.getText().toString().equals("admin") && senha.getText().toString().equals("nimda")){
            Intent it = new Intent(this,TelaAdmin.class);
            email.setText("");
            senha.setText("");
            startActivity(it);
        } else {
            String[] columns = null;
            String selection = Banco.TCliente.EMAIL + " = ? AND " + Banco.TCliente.SENHA + " = ? ";

            String[] selectionArgs = {email.getText().toString(), senha.getText().toString()};
            String orderBy = null;
            String having = null;
            String groupBy = null;

            Cursor c = db.query(Banco.TCliente.TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
            if (c.getCount() > 0) {
                c.moveToFirst();
                cliente = new Cliente();
                cliente.setNome(c.getString(c.getColumnIndex(Banco.TCliente.NOME)));
                email.setText("");
                senha.setText("");
                Intent it = new Intent(this, TelaCliente.class);
                it.putExtra("cliente", cliente);
                c.close();
                startActivity(it);
            } else {
                Toast.makeText(this, "Credencials inválidas!", Toast.LENGTH_LONG).show();
            }
        }
    }
    //inicia uma nova activity, telapdf para exibir o pdf
    public void mostrarPdf(View v){
       Intent it = new Intent(this,TelaPdf.class);
       startActivity(it);
    }
}
