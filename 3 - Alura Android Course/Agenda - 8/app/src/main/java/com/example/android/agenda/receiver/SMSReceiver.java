package com.example.android.agenda.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.android.agenda.R;
import com.example.android.agenda.dao.AlunoDAO;
//Essa classe possui "receiver.SMSReceiver" permission

//---> Extends que transforma a classe eum um Broadcast Receiver
public class SMSReceiver extends BroadcastReceiver{



    @Override
    //Quando chegar um evento de SMS, o Android vai chamar esse metodo
    //Lembrando que eh necessario declarar essa classe no Android Manifest
    //------------------->(Contexto, dados que chegaram)
    public void onReceive(Context context, Intent intent) {


        //**************************FALTAR EMPLEMENTAR 18/04/2016**********************************************
        //*Nesse momento eu tenho que verificar se o usuario autoriza a usar
        //*a captacao de SMS
        //Tem que fazer o esquema igual quando fiz para autorizar o numero de chamada
        //************************************************************************



        //Desempacotando as PDUs (Mensagem codificada em bytes que representa um SMS) que vieram na intent e colocando em um array objeto
        //------------------------>(Chave do que quero pegar - essa chave em particular ja eh do android esta descrita na documentacao)
        Object[] pdusRecebidos = (Object[]) intent.getSerializableExtra( "pdus" );


        //Pegando a primeira posicao da array de pdus recebido
        //Isso pq a primeira posicao sempre vai existir e contem o numero do telefone que enviou o sms
        //Eh preciso tambem transformar tudo emm byte para poder manipular
        byte[] pdu = (byte[]) pdusRecebidos [0];



        //Pegando o formato do SMS recebido
        String formatoDoSmsRecebido = (String) intent.getSerializableExtra( "format" );



        //Pegando a PDU que vem dentro da intent do metodo onReceive.
        //-------------------------------------->()
        SmsMessage smsRecebido = SmsMessage.createFromPdu(pdu, formatoDoSmsRecebido);

        //Pegando o numero de telefone que enviou o sms
        String telefone = smsRecebido.getDisplayOriginatingAddress();


        //Verificando se o numero que enviou o sms eh um contato da agenda
        AlunoDAO dao = new AlunoDAO (context);

        if ( dao.ehAluno( telefone ) == true  ){

            //------------>(Esse context foi o sistema do android que passou)
            Toast.makeText( context, "You have received a SMS!", Toast.LENGTH_SHORT ).show();

            //Tocar um sonzinho
            MediaPlayer somQueVaiTocar = MediaPlayer.create( context, R.raw.msg );
            somQueVaiTocar.start();

        }//if


        dao.close();


    }



}//SMSReceiver
