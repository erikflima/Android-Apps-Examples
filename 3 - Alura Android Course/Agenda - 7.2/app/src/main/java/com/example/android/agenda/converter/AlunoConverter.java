package com.example.android.agenda.converter;
import com.example.android.agenda.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

//Classe responsavel por gerar json
public class AlunoConverter {


    public String converterParaJSON(List<Aluno> listaDeAlunos) {


        //JSONStringer é tipo um concatenador que ajuda a criar um JSON
        //Assim eh mais facil que criar o formato JSON manualmente
        //O formato vai ser igual ao que esta no arquivo que criei ( JSON Format Example )
        JSONStringer js = new JSONStringer();


        try {
            //Step 1 - Criando o cabecalho do JSON

            //->abrindo {
            js.object();

            //->nome do item, ou seja  "list":
            js.key("list");

            //->abrindo [
            js.array();

            //->abrindo {
            js.object();

            //->nome do item, ou seja  "aluno":
            js.key("aluno");

            //->abrindo [
            js.array();



            //Para cada posicao  do array, faca...
            for( Aluno auxAluno : listaDeAlunos){

                //Step 2 - Criando a parte de campos do objeto

                //->abrindo {
                js.object();

                //->Digitando o campo  "nome": "xxx",
                js.key("nome").value(auxAluno.getNome());

                //->Digitando o endereco  "endereco": "xxx",
                js.key("endereco").value( auxAluno.getEndereco() );

                //->Fechando }
                js.endObject();

            }//for



            //Step 3 - Criando o Rodape do JSON

            //Fechando ]
            js.endArray();

            //Fechando }
            js.endObject();

            //Fechando ]
            js.endArray();

            //Fechando }
            js.endObject();



        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Retornando o JSON completo :)
        return js.toString();
    }



}//class
