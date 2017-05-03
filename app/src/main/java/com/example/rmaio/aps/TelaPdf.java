package com.example.rmaio.aps;

import android.app.Activity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by usuario1 on 02/05/2017.
 */

public class TelaPdf extends Activity {
    //biblioteca PDFView foi necessaria, github.barteksc/PDFView
    //basta adicionar o documento a pasta assets e o metodo fromAsset, pode utilizar uri, etc..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pdf);
        PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("ecommerce.pdf").load();
    }
}
