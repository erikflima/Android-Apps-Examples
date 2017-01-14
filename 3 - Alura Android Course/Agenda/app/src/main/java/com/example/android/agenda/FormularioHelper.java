package com.example.android.agenda;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.android.agenda.modelo.Aluno;

public class FormularioHelper {

    private  final EditText  campoNome;
    private  final EditText  campoEndereco;
    private  final EditText  campoTelefone;
    private  final EditText  campoSite;
    private  final RatingBar campoNota;



    //Constructor
    public FormularioHelper(Formulario formularioRecebido){


        campoNome     = (EditText)   formularioRecebido.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText)   formularioRecebido.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText)   formularioRecebido.findViewById(R.id.formulario_telefone);
        campoSite     = (EditText)   formularioRecebido.findViewById(R.id.formulario_site);
        campoNota     = (RatingBar)  formularioRecebido.findViewById(R.id.formulario_nota);

    }//FormularioHelper


    //Metodo para retornar um objeto aluno com os dados preenchidos na tela de formulario
    public Aluno pegaAluno(){

        //Objeto que vai receber os dados digitados na tela
        Aluno alunoRetornado = new Aluno();

        alunoRetornado.setNome    (campoNome    .getText().toString() );
        alunoRetornado.setEndereco(campoEndereco.getText().toString());
        alunoRetornado.setTelefone(campoTelefone.getText().toString());
        alunoRetornado.setSite    (campoSite    .getText().toString());
        alunoRetornado.setNota(Double.valueOf(campoNota.getProgress()));

       return   alunoRetornado;
    }

}
