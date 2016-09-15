package com.example.doug.hips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.PendingIntent;
import android.telephony.SmsManager;
import android.util.Log;



public class Encrypt extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.HIPS.MESSAGE";
    public String decoded = "Not Set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        //EditText sending_message = (EditText) findViewById(R.id.sending_message);
        Button sendBtn = (Button) findViewById(R.id.btnSendSMS);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    /** Called when the user clicks the Encrypt button*/
    public void encryptMessage(View view) {
        Intent intent = new Intent(this, DisplayMessage.class);
        EditText sending_message = (EditText) findViewById(R.id.sending_message);
        TextView encrypted_MSG = (TextView) findViewById(R.id.encryptedMsg);
        // Stores the data from the input box
        String message = sending_message.getText().toString();
        decoded = sending_message.getText().toString();
        //converts the message into a pig latin msg
        if (!message.equals("")) {
            message = convert(message);
        }
        else
        {
            // Put a pop-up here saying
            // "Are you sure you want to send a blank message?"
            // Options "Yes" and "Cancel"
            message = "Error: Message was blank!";
        }

        //stores the convertd string into Extra Message
        // to be used by any other activity
        intent.putExtra(EXTRA_MESSAGE, message.toString());
        //startActivity(intent);
        encrypted_MSG.setText(message);
        view.invalidate();
        //setContentView(R.layout.activity_encrypt);
    }

    public void sendMessage() {
        Intent intent = getIntent();
        EditText phoneNum = (EditText) findViewById(R.id.phoneNum);
        EditText sending_message = (EditText) findViewById(R.id.sending_message);
        // TO DO: This doesnt gather the correct value
        String encrypt_sent_MSG = intent.getStringExtra(EXTRA_MESSAGE);

        Log.i("Send SMS", "");
        String phoneNo = phoneNum.getText().toString();
        //Just takes the text that was typed into the message and sends it
        String message = sending_message.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            // Trying to use the Encrypted version of the text
            //smsManager.sendTextMessage(phoneNo, null, encrypt_sent_MSG, null, null);

            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
    private void sendSMS(String phoneNumber, String message)
    {
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, Encrypt.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);
    }
    public void decodeMessage(View view) {
        TextView decoded_MSG = (TextView) findViewById(R.id.decodedMsg);
        decoded_MSG.setText(decoded);
        view.invalidate();
    }

    /**
     *
     * @param x is the string to be converted
     * @return string converted into pig latin
     */
    public String convertAlg(String x)
    {
        // Rules:
        //  First character is vowel add -yay at the end
        //  Take first set of constants and move to the end and add ay
        String y = "";
        String vowels = "aeiou";
        int firstV = 0;
        boolean flag = false;
        String a = x.toLowerCase();
        // For loop to go through given string and get to first vowel
        for (int i = 0; i < x.length(); i++)
        {
            char b = a.charAt(i);
            for (int j = 0; j < vowels.length(); j++)
            {
                if (b == vowels.charAt(j) && i == 0)
                {
                    firstV = i;
                    flag = true;
                }
                else if (b == vowels.charAt(j) && !flag)
                {
                    firstV = i;
                    flag = true;
                }
            }
        }

        if (firstV == 0)
        {
            y = x + "-ay";
        }
        else
        {
            y = x.substring(firstV) + "-" + x.substring(0, firstV) + "ay";
        }

        return y;
    }

    /**
     * Takes a given string and then converts it into a pig latin string
     * by using the convert alg
     * @param s given string to be converted
     * @return converted string
     */
    public String convert(String s)
    {
        String[] strArr = s.split(" ");
        String output = "";
        for(int i = 0; i < strArr.length; i++)
        {
            output = output + convertAlg(strArr[i]) + " ";
        }
        return output;
    }


}
