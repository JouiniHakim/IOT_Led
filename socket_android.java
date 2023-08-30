package com.example.socket_webserver;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
package com.example.socket_webserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.socket_webserver.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        binding.on.setOnClickListener(v -> sendCommand("ON"));

        binding.off.setOnClickListener(v -> sendCommand("OFF"));
    }

        private void sendCommand(final String command) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    String serverAddress = "your device address";
                    int serverPort = 80;
                    try {
                        Socket socket = new Socket(serverAddress, serverPort);
                        PrintWriter output = null;
                        output = new PrintWriter(socket.getOutputStream(), true);
                        output.println(command);
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            });



        }}

////////////////////xml





 <Button
        android:id="@+id/on"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true"
        android:text="ON" />

    <Button
        android:id="@+id/off"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_below="@+id/on"
        android:text="OFF" />
