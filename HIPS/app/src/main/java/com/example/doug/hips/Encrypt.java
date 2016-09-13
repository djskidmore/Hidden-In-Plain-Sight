package com.example.doug.hips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Encrypt extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.HIPS.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    /** Called when the user clicks the Send button*/
    public void encryptMessage(View view) {
        Intent intent = new Intent(this, DisplayMessage.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        // Stores the data from the input box
        String message = editText.getText().toString();
        //converts the message into a pig latin msg
        message = convert(message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
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
