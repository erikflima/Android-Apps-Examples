package com.example.android.agenda.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.android.agenda.modelo.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper{

    //Constructor
    public AlunoDAO(Context context) {

        //Criando o BD
        //-->(Contexto ou seja..eh a tela no qual o objeto foi criado sacas!, nome do db, Customizador de parametros que nao sera utilizado nesse app , Versao do DB)
        super(context, "Agenda", null, 1);
    }



    @Override
    //Metodo obrigatorio a ser implementado
    //Esse metodo eh chamado toda vez que o Android precisar criar o as tabelas do DB
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Alunos ( id         INTEGER PRIMARY KEY, " +
                                          "nome        TEXT NOT NULL," +
                                          "endereco    TEXT, " +
                                          "telefone    TEXT, " +
                                          "site        TEXT, " +
                                          "nota        real, " +
                                          "caminhofoto TEXT );";
        db.execSQL(sql);
    }




    @Override
    //Metodo obrigatorio a ser implementado
    //Esse metodo eh chamado toda vez que o Android precisar atualizar as tabelas do DB de acordo com a versao
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String auxSQL = "";


        switch (oldVersion) {

            case 1:
                //instrucao para a primeira atualizacao

                //Exemplo
                //auxSQL = "ALTER TABLE Alunos ADD COLUMN caminhofoto TEXT;";
                //db.execSQL(auxSQL);


            case 2:
                //instrucao para a segunda atualizacao

        }//switch

    }//onUpgrade



   //Buscando uma lista de alunos cadastrados na tabela "Alunos" do DB "Agenda"
    public List<Aluno> buscaAlunos() {


        String sql = " SELECT * FROM Alunos; ";

        //------------------------------Explicacao rapida! :)------------------------//
        //Estou usando esse metodo pq eu preciso ter a referencia do banco de dados que foi criado,
        //pois como fui eu que criei esse metodo, esse metodo nao enxerga o banco que criei,
        //pois ele eh um objeto fora desse metodo, sacas!
        //Entao eu preciso tipo linkar o banco que criei com uma variavel dentro desse metodo...
        //Ai sim eu posso pedir para ele executar os comandos usando o comando insert() da classe pai SQLiteDatabase
        //O comando "getReadableDatabase()" da o poder de leitura para o objeto
        SQLiteDatabase db = getReadableDatabase();


        //Preciso usar o metodo "rawQuery()" para obter um objeto "Cursor" como retorno
        //e apartir disso manipular o resultado.
        //------------------------>( intrucao, parametros complementares)
        Cursor cursorDoResultadoDaQuery = db.rawQuery(sql, null);



        //Criando um objeto do tipo "List" que sera o retorno do metodo
        //
        List<Aluno> listaDeAlunosASerDevolvida = new ArrayList<Aluno>();




        //"moveToNext()" devolve:
        // - "true" : ainda tem linhas no cursor
        // - "false": acabou as linha do cursor
        while( cursorDoResultadoDaQuery.moveToNext() ){

            Aluno auxAluno = new Aluno();


            //Pegando o resultado da linha do cursor e colocando no auxAluno

            //----------------------------------------------------->(indice de coluna da tabela para pegar o resultado)
            auxAluno.setId                (cursorDoResultadoDaQuery.getLong  (cursorDoResultadoDaQuery.getColumnIndex("id")          ));
            auxAluno.setNome              (cursorDoResultadoDaQuery.getString(cursorDoResultadoDaQuery.getColumnIndex("nome")        ));
            auxAluno.setEndereco          (cursorDoResultadoDaQuery.getString(cursorDoResultadoDaQuery.getColumnIndex("endereco")    ));
            auxAluno.setTelefone          (cursorDoResultadoDaQuery.getString(cursorDoResultadoDaQuery.getColumnIndex("telefone")    ));
            auxAluno.setSite              (cursorDoResultadoDaQuery.getString(cursorDoResultadoDaQuery.getColumnIndex("site")        ));
            auxAluno.setNota              (cursorDoResultadoDaQuery.getDouble(cursorDoResultadoDaQuery.getColumnIndex("nota")        ));
            auxAluno.setCaminhoFotoDoAluno(cursorDoResultadoDaQuery.getString(cursorDoResultadoDaQuery.getColumnIndex("caminhofoto") ));



            //Adiciono na ArrayList
            listaDeAlunosASerDevolvida.add(auxAluno);

        }//while


        //Fecha o cursor
        cursorDoResultadoDaQuery.close();

       return listaDeAlunosASerDevolvida;
    }



    //Inseri o aluno no BD "Agenda" na Tabela "Alunos"
    public void insere(Aluno alunoASerSalvoNoDB) {


        //------------------------------Explicacao rapida! :)------------------------//
        //Estou usando esse metodo pq eu preciso ter a referencia do banco de dados que foi criado,
        //pois como fui eu que criei esse metodo, esse metodo nao enxerga o banco que criei,
        //pois ele eh um objeto fora desse metodo, sacas!
        //Entao eu preciso tipo linkar o banco que criei com uma variavel dentro desse metodo...
        //Ai sim eu posso pedir para ele executar os comandos usando o comando insert() da classe pai SQLiteDatabase
        //O comando "getWritableDatabase()" da o poder de insercao para o objeto
        SQLiteDatabase db = getWritableDatabase();



        //Pego os dados do Objeto Aluno que vai ser inserido no BD e coloco em um obj ContentValues
        ContentValues dados = getContentValues(alunoASerSalvoNoDB) ;



        //Executando a query
        //Quando executo o comando "insert()", internamente o conteudo da query vai ser tratado,
        // para ver se tem algum erro ou SQLInjection, se tiver zuado, ele sera arrumado e executado
        //sem problemas :)
        //Sem falar que nao precisa colocar o comando sql em um string tals :)
        //----->(Qual a tabela, Customizador de parametros que nao sera utilizado nesse app, um objeto "ContentValues" que eh um objeto que tem os valores a serem inseridos )
        db.insert("Alunos", null, dados);
    }



    //Alterar um aluno ja existente no BD "Agenda" na Tabela "Alunos"
    public void altera(Aluno alunoASerAtualizado) {


        //------------------------------Explicacao rapida! :)------------------------//
        //Estou usando esse metodo pq eu preciso ter a referencia do banco de dados que foi criado,
        //pois como fui eu que criei esse metodo, esse metodo nao enxerga o banco que criei,
        //pois ele eh um objeto fora desse metodo, sacas!
        //Entao eu preciso tipo linkar o banco que criei com uma variavel dentro desse metodo...
        //Ai sim eu posso pedir para ele executar os comandos usando o comando update() da classe pai SQLiteDatabase
        //O comando "getWritableDatabase()" da o poder de update para o objeto
        SQLiteDatabase db = getWritableDatabase();



        //Objeto que contem os dados para fazer a query.
        //Obs: Eh feito dessa forma para evitar SQLInjection tals...
        ContentValues dados = getContentValues(alunoASerAtualizado);


        //Parametros que ficaram no lugar dos "?"
        //Vou passar o ID do objeto a sofre o update
        String [] subtitutosDeInterrogacao = { alunoASerAtualizado.getId().toString() };


        //Executando a query
        //Quando executo o comando "update()", internamente o conteudo da query vai ser tratado,
        // para ver se tem algum erro ou SQLInjection, se tiver zuado, ele sera arrumado e executado
        //sem problemas :)
        //Sem falar que nao precisa colocar o comando sql em um string tals :)

        //----->(Qual a tabela, um objeto "ContentValues" que eh um objeto que tem os valores que vao ser atualizados, Uma String com os substitutos do caracter interrogação "?" )
        db.update("Alunos", dados, "id = ?", subtitutosDeInterrogacao);








    }




    //Deletando um item da tabela
    public void deleta(Aluno alunoRecebido) {


        //------------------------------Explicacao rapida! :)------------------------//
        //Estou usando esse metodo pq eu preciso ter a referencia do banco de dados que foi criado,
        //pois como fui eu que criei esse metodo, esse metodo nao enxerga o banco que criei,
        //pois ele eh um objeto fora desse metodo, sacas!
        //Entao eu preciso tipo linkar o banco que criei com uma variavel dentro desse metodo...
        //Ai sim eu posso pedir para ele executar os comandos usando o comando insert() da classe pai SQLiteDatabase
        //O comando "getWritableDatabase()" da o poder de deletar para o objeto
        SQLiteDatabase db = getWritableDatabase();

        //Parametros que ficaram n lugar dos "?"
        String [] subtitutosDeInterrogacao = { alunoRecebido.getId().toString() };

        //------>(Qual a tabela, clausula where, array de string que vai substituir os "?" )
        db.delete("Alunos", "id = ?", subtitutosDeInterrogacao);



    }




    @NonNull
    //Metodo para preparar um objeto ContentValues que vai ser passado para um comando SQL
    private ContentValues getContentValues(Aluno alunoQueVouPegarOsDados) {


        //Objeto que contem os dados para fazer a query.
        //Obs: Eh feito dessa forma para evitar SQLInjection tals...

        ContentValues dados = new ContentValues();
        dados.put( "nome"       , alunoQueVouPegarOsDados.getNome());
        dados.put( "endereco"   , alunoQueVouPegarOsDados.getEndereco()           );
        dados.put( "telefone"   , alunoQueVouPegarOsDados.getTelefone()           );
        dados.put( "site"       , alunoQueVouPegarOsDados.getSite()               );
        dados.put( "nota"       , alunoQueVouPegarOsDados.getNota()               );
        dados.put("caminhofoto", alunoQueVouPegarOsDados.getCaminhoFotoDoAluno());

        return dados;
    }



    //Metodo para verificar se o numero de telefone que enviou um sms "eh" ou "nao" um telefone que
    //esta salvo na agenda
    public boolean ehAluno( String telefoneDeQuemEnviouoSms ){


        //------------------------------Explicacao rapida! :)------------------------//
        //Estou usando esse metodo pq eu preciso ter a referencia do banco de dados que foi criado,
        //pois como fui eu que criei esse metodo, esse metodo nao enxerga o banco que criei,
        //pois ele eh um objeto fora desse metodo, sacas!
        //Entao eu preciso tipo linkar o banco que criei com uma variavel dentro desse metodo...
        //Ai sim eu posso pedir para ele executar os comandos usando o comando update() da classe pai SQLiteDatabase
        //O comando "getWritableDatabase()" da o poder de update para o objeto
        SQLiteDatabase db = getWritableDatabase();


        //Preciso usar o metodo "rawQuery()" para obter um objeto "Cursor" como retorno
        //e apartir disso manipular o resultado.
        //------------------------------------------>( instrucao, parametros complementares - nesse caso sera o numero do telefone)
        Cursor cursorDoResultadoDaQuery = db.rawQuery( "SELECT * FROM Alunos WHERE telefone = ?", new String[]{telefoneDeQuemEnviouoSms} );


        int quantidadeDeLinhasDoCursor = cursorDoResultadoDaQuery.getCount();
        cursorDoResultadoDaQuery.close();


        //Verificando se o numero de telefone que enviou o sms esta na eh um contato da agenda
        if( quantidadeDeLinhasDoCursor > 0 ){
            //sim, esse contato existe na agenda
            return true;
        }
        else{
            //nao, esse contato nao existe na agenda
            return false;
        }


    }




}//class
