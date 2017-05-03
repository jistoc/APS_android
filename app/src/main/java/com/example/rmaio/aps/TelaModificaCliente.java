package com.example.rmaio.aps;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by usuario1 on 27/04/2017.
 */

public class TelaModificaCliente extends Activity {
    EditText nome;
    EditText email;
    EditText cpf;
    EditText endereco;
    EditText senha;
    Cliente cliente;
    int id;
    private SQLiteDatabase db;

    // inicia o banco, atribui os componentes, pesquisa o cliente enviado pela tela anterior (pesquisar)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_modifica_cliente);
        Banco banco = new Banco(this);
        db = banco.getWritableDatabase();
        nome = (EditText) findViewById(R.id.txtNome);
        email = (EditText) findViewById(R.id.txtEmail);
        cpf = (EditText) findViewById(R.id.txtCPF);
        endereco = (EditText) findViewById(R.id.txtEndereco);
        senha = (EditText) findViewById(R.id.txtSenha);
        pesquisarCliente();

    }
    // instancia um novo cliente, salva suas informações no objeto e adiciona ao bundle para recuperação
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        cliente = new Cliente();
        cliente.setCpf(cpf.getText().toString());
        cliente.setNome(nome.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setEndereco(endereco.getText().toString());
        cliente.setSenha(senha.getText().toString());

        outState.putParcelable("cliente",cliente);
    }

    //recupera informações do cliente
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cliente = savedInstanceState.getParcelable("cliente");
        nome.setText(cliente.getNome());
        senha.setText(cliente.getSenha());
        email.setText(cliente.getEmail());
        endereco.setText(cliente.getEndereco());
        cpf.setText(cliente.getCpf());

    }

    public void voltar(View v){
        finish();
    }

    //pesquisa de cliente feita pelo EMAIl (Unique no banco)
    //se encontrado carrega os dados nos compmentes na tela
    private void pesquisarCliente() {
        String nCliente = getIntent().getStringExtra("cliente");
        String selection = Banco.TCliente.EMAIL + " = ? ";
        String[] selectionArgs = {nCliente};
        String orderBy = null;
        String having = null;
        String groupBy = null;
        Cursor c = db.query(Banco.TCliente.TABELA, null, selection, selectionArgs, groupBy, having, orderBy);

        if (c != null) {
            if (c.moveToFirst()) {
                id = c.getInt(c.getColumnIndex(Banco.TCliente._ID));
                nome.setText(c.getString(c.getColumnIndex(Banco.TCliente.NOME)));
                cpf.setText(c.getString(c.getColumnIndex(Banco.TCliente.CPF)));
                endereco.setText(c.getString(c.getColumnIndex(Banco.TCliente.ENDERECO)));
                email.setText(c.getString(c.getColumnIndex(Banco.TCliente.EMAIL)));
                senha.setText(c.getString(c.getColumnIndex(Banco.TCliente.SENHA)));
            }
        }
    }
    //realiza exclusao de cliente baseado em seu id (armazenado quando o cliente é buscado)
    public void excluir(View v){
        String whereClause = "_id = ?";
        String[] whereArgs = {String.valueOf(id)};
        if(db.delete(Banco.TCliente.TABELA,whereClause,whereArgs)!=-1){
            Toast.makeText(this,"Cliente excluido!", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this,"Falha ao excluir!", Toast.LENGTH_LONG).show();
        }
    }
    //altera o cliente com base em seu id e informações definidas
    public void alterarCliente(View v){


        String whereClause = "_id = ?";
        String[] whereArgs = {String.valueOf(id)};

        ContentValues values = new ContentValues();
        values.put(Banco.TCliente.NOME,nome.getText().toString());
        values.put(Banco.TCliente.SENHA,senha.getText().toString());
        values.put(Banco.TCliente.ENDERECO,endereco.getText().toString());
        values.put(Banco.TCliente.EMAIL,email.getText().toString());
        values.put(Banco.TCliente.CPF,cpf.getText().toString());

        if(db.update(Banco.TCliente.TABELA,values,whereClause,whereArgs)!=0){
            Toast.makeText(this,"Cliente alterado!", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this,"Falha ao alterar!", Toast.LENGTH_LONG).show();
        }
    }
}
