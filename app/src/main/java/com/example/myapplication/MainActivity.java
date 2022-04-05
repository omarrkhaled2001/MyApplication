package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.*;
import java.io.*;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void connect(View view) throws Exception{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        System.out.println("clicked");
        TextView t1= (TextView) findViewById(R.id.textView);
        t1.setText("Press Send");
        try{
            Socket socket= new Socket("192.168.1.4", 1234);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream dIn = new DataInputStream(socket.getInputStream());

            Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_SHORT).show();
            TextInputEditText box = findViewById(R.id.textbox);
            box.setVisibility(View.VISIBLE);
            Button b1= findViewById(R.id.button1);
            b1.setText("Send Text to Server");
            b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            dOut.writeUTF(String.valueOf(box.getText()));
                            String sentence=dIn.readUTF();
                            Toast.makeText(getApplicationContext(),sentence, Toast.LENGTH_SHORT).show();

                        } catch (Exception E) {
                            Toast.makeText(getApplicationContext(),"Could not send message", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        }
        catch(Exception E){
            Toast.makeText(getApplicationContext(),"Could not Connect", Toast.LENGTH_SHORT).show();
        }

    }



}