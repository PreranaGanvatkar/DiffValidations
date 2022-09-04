package com.prerana.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.prerana.demo.App.CHANNEL_1_ID;
import static com.prerana.demo.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText1,editText2,editText3,editText4;
    Button btnSub,btnSub1,btnSub2,btnSub3,btnSub4;
    TextView txt;
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =findViewById(R.id.edttxt);
        editText1 =findViewById(R.id.edttxt2);
        editText2 =findViewById(R.id.edttxt3);
        editText3 =findViewById(R.id.edttxt4);
        editText4 =findViewById(R.id.edttxt5);
        btnSub= findViewById(R.id.submit);
        btnSub1= findViewById(R.id.btn1);
        btnSub2= findViewById(R.id.btn2);
        btnSub3= findViewById(R.id.btn3);
        btnSub4= findViewById(R.id.btn4);
        txt =findViewById(R.id.txt);
        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edttxt2);
        editTextMessage = findViewById(R.id.edttxt6);
//         5th
        txt=findViewById(R.id.txt);
        btnSub4=findViewById(R.id.btn4);
        btnSub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Completed");
            }
        });

//        4th
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Modal pop up");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage(editText4.getText().toString());

        editText4=new EditText(this);
        builder.setView(editText4);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                   String txt=editText4.getText().toString();
                Toast.makeText(MainActivity.this, ""+txt, Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog ad= builder.create();

        btnSub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.show();
            }
        });



//         1st
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter the text", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        2nd
        btnSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                Toast.makeText(MainActivity.this, "Your text is "+ name , Toast.LENGTH_SHORT).show();
            }
        });



    }
    // 3rd
    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();


        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "dismiss", actionIntent)
                .build();

        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}