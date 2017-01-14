package com.example.android.agenda.modelo;
import java.io.Serializable;
import java.util.List;



public class Prova implements Serializable{


    //Atributos
    private String       materia ;
    private String       data;
    private List<String> topicos;



    //Constructor
    public Prova(  String materia, String data, List<String> topicos ) {

        //Passando os valores recebidos para as variaveis locais
        this.data    = data;
        this.materia = materia;
        this.topicos = topicos;

    }//Constructor







    //------Getters and Setters------//


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    //-------

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


    //-------

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }






    //Sobreescrevendo o metodo toString pra quando eu for colocar objetos
    //do tipo "Prova" dentro do ArrayAdapter
    @Override
    public String toString() {



        /**
         A String retornada sera dessa forma dessa forma:
         "xxxxxx"
         **/

        return this.materia;
    }
}//class
