package com.example.android.agenda.modelo;
import java.io.Serializable;

public class Aluno implements Serializable{




    //Construtor
    public Aluno(){
     }



    private  Long   id                 ;
    private  String nome               ;
    private  String endereco           ;
    private  String telefone           ;
    private  String site               ;
    private  double nota               ;
    private  String caminhoFotoDoAluno ;


    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//----------------------------------------------------------------------------//

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//----------------------------------------------------------------------------//


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

//----------------------------------------------------------------------------//


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;}


//----------------------------------------------------------------------------//


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

//----------------------------------------------------------------------------//


    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

//----------------------------------------------------------------------------//


    public String getCaminhoFotoDoAluno() {
        return caminhoFotoDoAluno;
    }

    public void setCaminhoFotoDoAluno(String caminhoFotoDoAluno) {
        this.caminhoFotoDoAluno = caminhoFotoDoAluno;
    }

//----------------------------------------------------------------------------//



//Sobreescrevendo o metodo toString pra quando eu for colocar objetos
//do tipo "Aluno" dentro do ArrayAdapter
    @Override
    public String toString() {

      /**

         A String retornada sera dessa forma dessa forma:
           "1 - Nome"

         **/

        return    getId() + " - "  + getNome();
    }



}
