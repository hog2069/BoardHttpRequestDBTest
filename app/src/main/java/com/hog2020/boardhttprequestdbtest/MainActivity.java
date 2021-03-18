package com.hog2020.boardhttprequestdbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText etname;
    EditText etmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.et_name);
        etmsg=findViewById(R.id.et_message);
    }

    public void clicksave(View view) {

        new Thread(){
            @Override
            public void run() {
                String name =etname.getText().toString();
                String msg = etmsg.getText().toString();

                String serverUrl="http://hog2069.dothome.co.kr/Android/boardtest.php";

                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    String data ="name="+name+"&msg="+msg;
                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);
                    writer.write(data,0,data.length());
                    writer.flush();
                    writer.close();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);
                    final StringBuffer buffer = new StringBuffer();

                    while(true){
                        String line =reader.readLine();
                        if(line==null)break;
                        buffer.append(line+"\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(etname,buffer.toString(),Snackbar.LENGTH_SHORT).show();
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void clickload(View view) {

        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}