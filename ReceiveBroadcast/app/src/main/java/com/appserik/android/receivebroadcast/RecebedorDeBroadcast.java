package com.appserik.android.receivebroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class RecebedorDeBroadcast extends BroadcastReceiver {
    public RecebedorDeBroadcast() {
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText( context, "Mensagem do app ReceiveBroadcast: O Broadcast foi recebido", Toast.LENGTH_LONG ).show();

    }



}//class
