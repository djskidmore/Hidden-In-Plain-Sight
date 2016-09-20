package com.example.doug.hips;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Doug on 9/19/2016.
 */



public class IncomingSms extends BroadcastReceiver  {

    final SmsManager sms = SmsManager.getDefault();
    MsgBody msgBody = new MsgBody();

    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            String sender;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        // Here you have the sender(phone number)
                        sender = msgs[i].getOriginatingAddress();
                        msgBody.updateMsgBody(msgs[i].getMessageBody());
                        // you have the sms content in the msgBody


                        Toast.makeText(context,sender + ": " + msgBody.getMsgBody(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
