package com.example.android.agenda.ConexaoComServidor;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.android.agenda.converter.AlunoConverter;
import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.modelo.Aluno;

import java.util.List;

/**
 * Classe responsavel por enviar os dados para o servidor
 * e nao usar a thread primaria do android para isso,
 * e sim, usando uma thread secundaria para nao travar a app
 */
public class EnviaAlunosParaServidor extends AsyncTask {


    private Context context;

    //Tela de telaDeLoading
    ProgressDialog telaDeLoading;


    //Constructor
    public EnviaAlunosParaServidor(Context context) {
        this.context = context;
    }


    @Override
    //Metodo executado ANTES! de comecar o metodo "doInBackground()"
    //Esse metodo eh executado na THREAD PRIMARIA!
    protected void onPreExecute() {


        //Criando uma animacao de loading
        //----------------------------------------->( tela onde sera exibida a animacao, Titulo, Subtitulo, fica na tela por tempo indeterminado? (T or F), o user pode clicar na tela e cancelar a animacao? (T or F) )
        telaDeLoading = ProgressDialog.show ( context, "Por favor aguarde", "Enviado alunos...", true, true  );


    }




    @Override
    //Acoes que vao ser feitas na thread secundaria
    protected Object doInBackground(Object[] params) {



        AlunoDAO dao = new AlunoDAO( context );

        //pego a nota de todos os alunos no BD
        List<Aluno> listaDeAlunos = dao.buscaAlunos();
        dao.close();


        //Criando um objeto para facilitar a convercao do JSON
        AlunoConverter conversor = new AlunoConverter();


        //Converter para o formato JSON
        String json = conversor.converterParaJSON( listaDeAlunos );


        //Criando um objeto com.example.android.agenda.ConexaoComServidor.WebClient para fazer requisicao "post" ao servidor
        WebClient client = new WebClient();


        //Usando o metodo "post()" para realizar a coneccao com o servidor
        String retornoDoServidor = client.post( json );


        return retornoDoServidor;
    }




    @Override
    //Metodo executado depois que terminar o metodo "doInBackground()"
    //Esse metodo eh executado na THREAD PRIMARIA!
    //-------------------------->Esse paramentro eh o retorno do metodo "doInBackground()". Por incrivel que pareca o variavel "o" eh sim o retorno do metodo "doInBackground"
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //Fechar a tela de loading
        telaDeLoading.dismiss();


        //Mostrar o resultado que o servidor devolveu na Thread principal
        Toast.makeText( context,  (String) o, Toast.LENGTH_LONG).show();

    }





}//class
