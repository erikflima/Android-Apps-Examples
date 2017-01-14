package com.example.android.agenda_erikWay;

import android.widget.EditText;
import android.widget.RatingBar;
import com.example.android.agenda_erikWay.modelo.Aluno;


public class FormularioHelper {


    private  EditText  campoNome;
    private  EditText  campoEndereco;
    private  EditText  campoTelefone;
    private  EditText  campoSite;
    private  RatingBar campoNota;



    //Metodo para retornar um objeto aluno com os dados preenchidos na tela de formulario
    //Pedi a tela que esta chamando como parametro
    public Aluno pegaAluno(Formulario telaDoFormulario){


        campoNome     = (EditText)  telaDoFormulario.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText)  telaDoFormulario.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText)  telaDoFormulario.findViewById(R.id.formulario_telefone);
        campoSite     = (EditText)  telaDoFormulario.findViewById(R.id.formulario_site);
        campoNota     = (RatingBar) telaDoFormulario.findViewById(R.id.formulario_nota);


        //Objeto que vai receber os dados digitados na tela, ai depois vou retornar ele
        Aluno alunoRetornado = new Aluno();

        alunoRetornado.setNome    (campoNome    .getText().toString());
        alunoRetornado.setEndereco(campoEndereco.getText().toString());
        alunoRetornado.setTelefone(campoTelefone.getText().toString());
        alunoRetornado.setSite    (campoSite    .getText().toString());
        alunoRetornado.setNota    (Double.valueOf(campoNota.getProgress()));

       return   alunoRetornado;
    }

}
