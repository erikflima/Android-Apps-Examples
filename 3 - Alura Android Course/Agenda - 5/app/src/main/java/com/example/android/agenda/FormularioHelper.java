package com.example.android.agenda;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.example.android.agenda.modelo.Aluno;

public class FormularioHelper {

    private  final EditText  campoNome;
    private  final EditText  campoEndereco;
    private  final EditText  campoTelefone;
    private  final EditText  campoSite;
    private  final RatingBar campoNota;
    private final ImageView  campoFoto;


    //aluno auxiliar
    private Aluno auxAluno;



    //Constructor
    public FormularioHelper(Formulario formularioRecebido){


        campoNome          = (EditText)   formularioRecebido.findViewById(R.id.formulario_nome    );
        campoEndereco      = (EditText)   formularioRecebido.findViewById(R.id.formulario_endereco);
        campoTelefone      = (EditText)   formularioRecebido.findViewById(R.id.formulario_telefone);
        campoSite          = (EditText)   formularioRecebido.findViewById(R.id.formulario_site    );
        campoNota          = (RatingBar)  formularioRecebido.findViewById(R.id.formulario_nota    );
        campoFoto          = (ImageView)  formularioRecebido.findViewById(R.id.formulario_imvPhoto);

        //aluno auxiliar
        auxAluno = new Aluno();


    }//FormularioHelper



    //Metodo para retornar um objeto aluno com os dados preenchidos na tela de formulario
    public Aluno pegaAluno(){

        auxAluno.setNome    (campoNome.getText().toString());
        auxAluno.setEndereco(campoEndereco.getText().toString());
        auxAluno.setTelefone(campoTelefone.getText().toString());
        auxAluno.setSite    (campoSite.getText().toString());
        auxAluno.setNota    (Double.valueOf(campoNota.getProgress()));
        auxAluno.setCaminhoFotoDoAluno((String) campoFoto.getTag());
        ////////////////////////////////////////////////////////

        //O campo "id" esta sendo devolvido aqui tambem
        //id vazio se o se for um aluno novo / ou id preenchido se for uma alteracao
       return   auxAluno;
    }



    public void preencheFormulario(Aluno alunoRecebidoDoIntent) {

        //*Importante: id vai ser passado no final do metodo!
        campoNome     .setText(alunoRecebidoDoIntent.getNome());
        campoEndereco .setText(alunoRecebidoDoIntent.getEndereco());
        campoTelefone .setText(alunoRecebidoDoIntent.getTelefone());
        campoSite     .setText(alunoRecebidoDoIntent.getSite());

        double notaDouble        =  alunoRecebidoDoIntent.getNota();
        int    notaConvertidaInt = (int)notaDouble;
        campoNota.setProgress(notaConvertidaInt);

        carregaImagemNaTela( alunoRecebidoDoIntent.getCaminhoFotoDoAluno() );


        //Coloco o objeto que veio do intent dentro do auxAluno
        //Nesse momento eu passo o campo "id" para "auxAluno"!
        this.auxAluno = alunoRecebidoDoIntent;
    }



    //Esse metodo carrega a foto que foi tirada
    public void carregaImagemNaTela(String caminhoDaFoto) {


        //Verifico se o caminho da foto eh valido
        // pq pode ser que seja o aluno nao tenha foto cadastrada tals
        if ( caminhoDaFoto != null) {

            //Pegando a foto que foi tirada, decodificando e convertendo para Bitmap
            //
            Bitmap bitmapRecebido = BitmapFactory.decodeFile( caminhoDaFoto );


            //Reduzindo a resolucao para colocar no imageView da tela
            //------------------------------------------------>(imagem, tamannho desejado, passar filtro para melhorar a qualidade da imagem )
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmapRecebido, 100, 100, true);


            //Coloco a imagem em Bitmap e finalmente jogo na dentro no imageView da tela
            //
            campoFoto.setImageBitmap( bitmapRecebido );


            //Ajustar a imagem estendendo ela
            //
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);


            //
            //Associo o caminho da foto com o imageView que esta na tela
            //Fiz isso para conseguir pegar o caminho da foto dentro da classe "FormularioHelper", sacas!
            //Esse metodo setTag eh fodao, pq da pra colocar essa "tag", ou seja, da pra colocar qualquer objeto
            // como uma tag e depois recuperar! phodastico
            campoFoto.setTag( caminhoDaFoto );

        }//if



    }
}
