package com.example.android.justjavav2;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public  void decrement(View view) {

        if(quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);}
        else{
            Toast.makeText(this, "I'm sorry, but you cannot order less than 1 coffee :0", Toast.LENGTH_LONG).show();
        }//else
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public  void submitOrder(View view) {

        EditText text      = (EditText) findViewById(R.id.editText1_editText);
        String clientName  = text.getText().toString(); //Pegando a String que foi digitada no EditText do app


        CheckBox checkBoxStatusCream           = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean  checkBoxStatusResult1Cream    = checkBoxStatusCream .isChecked(); //Esse metodo retorna um boolean para saber se o checkbox foi marcado ou nao

        CheckBox checkBoxStatusChocolate       = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean  checkBoxStatusResultChocolate = checkBoxStatusChocolate.isChecked(); //Esse metodo retorna um boolean para saber se o checkbox foi marcado ou nao


        int totalPrice = calculateCoffeePrice(quantity, checkBoxStatusResult1Cream, checkBoxStatusResultChocolate); //calculando o valor total a ser apresentado na tela


        String finalMessage = createOderSummary(clientName, checkBoxStatusResult1Cream, checkBoxStatusResultChocolate, totalPrice); //chamando o metodo que constroi a mensagem
        displaySummary(finalMessage); // Passando a mensagem completa para ser exibida na tela
    }

    public  int calculateCoffeePrice(int qtdCoffees, boolean checkBoxStatusResult1Cream, boolean checkBoxStatusResultChocolate){

        int coffeePrice = 5; //Preco por unidade da xicara de cafe

        int extra=0;

        if(checkBoxStatusResult1Cream == true)  //Se o cliente adicionou corbetura
        {extra = extra +1;}

        if(checkBoxStatusResultChocolate == true) //Se o cliente adicionou chocolate
        {extra = extra +2;}


        return  (quantity * coffeePrice) + (quantity * extra ) ;
    }

    public  String createOderSummary(String clientName, boolean checkBoxStatus1, boolean checkBoxStatus2, int price){

        String priceMessage  = "Name: "                 +clientName;
               priceMessage +="\nAdd Whipped cream?: "  + checkBoxStatus1;
               priceMessage +="\nAdd Chocolate?: "      + checkBoxStatus2;
               priceMessage +="\nQuantity: "            + quantity;
               priceMessage +="\nTotal price: $"        + price;
               priceMessage +="\n\nThanks you!";
        return priceMessage;
    }

    private void displaySummary(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


}//main
