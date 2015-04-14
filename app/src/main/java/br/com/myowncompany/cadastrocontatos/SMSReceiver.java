package br.com.myowncompany.cadastrocontatos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android5193 on 13/04/15.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object msgs[] = (Object[]) bundle.get("pdus");
        SmsMessage SmsMsgs[] = new SmsMessage[msgs.length];

        for (int n =0; n < msgs.length; n++) {
            SmsMsgs[n] = SmsMessage.createFromPdu((byte[]) msgs[n]);
        }

        ContatoDao dao = new ContatoDao(context);
        if(dao.isContato(SmsMsgs[0].getDisplayOriginatingAddress())){
            Toast.makeText(context, "SMS do contato: " + SmsMsgs[0].getMessageBody(), Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
        dao.close();
    }
}
