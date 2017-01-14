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

    //aluno auxiliar
    private Aluno auxAluno;



    //Constructor
    public FormularioHelper(Formulario formularioRecebido){


        campoNome     = (EditText)   formularioRecebido.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText)   formularioRecebido.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText)   formularioRecebido.findViewById(R.id.formulario_telefone);
        campoSite     = (EditText)   formularioRecebido.findViewById(R.id.formulario_site);
        campoNota     = (RatingBar)  formularioRecebido.findViewById(R.id.formulario_nota);

        //aluno auxiliar
        auxAluno = new Aluno();


    }//FormularioHelper




    //Metodo para retornar um objeto aluno com os dados preenchidos na tela de formulario
    public Aluno pegaAluno(){

        auxAluno.setNome    (campoNome    .getText().toString()      );
        auxAluno.setEndereco(campoEndereco.getText().toString()      );
        auxAluno.setTelefone(campoTelefone.getText().toString()      );
        auxAluno.setSite    (campoSite    .getText().toString()      );
        auxAluno.setNota    (Double.valueOf(campoNota.getProgress() ));

        //O campo "id" esta sendo devolvido aqui também
        //id vazio se o se for um aluno novo / ou id preenchido se for uma alteracao
       return   auxAluno;
    }


    public void preencheFormulario(Aluno alunoRecebidoDoIntent) {

        //*Importante: id vai ser passado no final do metodo!
        campoNome     .setText ( alunoRecebidoDoIntent.getNome()     );
        campoEndereco .setText ( alunoRecebidoDoIntent.getEndereco() );
        campoTelefone .setText ( alunoRecebidoDoIntent.getTelefone() );
        campoSite     .setText ( alunoRecebidoDoIntent.getSite()     );

        double notaDouble        =  alunoRecebidoDoIntent.getNota();
        int    notaConvertidaInt = (int)notaDouble;
        campoNota.setProgress      ( notaConvertidaInt );



        //Coloco o objeto que veio do intent dentro do auxAluno
        //Nesse momento eu passo o campo "id" para "auxAluno"!
        this.auxAluno = alunoRecebidoDoIntent;
    }




}
