package com.example.hp.loginpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.loginpage.ModelClass.UserProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator extends AppCompatActivity {


    public Spinner SubjectNo;
    public Spinner Credit_1,Credit_2,Credit_3,Credit_4,Credit_5,Credit_6,Credit_7;
    public Spinner Grade_1,Grade_2,Grade_3,Grade_4,Grade_5,Grade_6,Grade_7;

    public Button buttonCheck;

    public AlertDialog.Builder aleartDailogueBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        setTitle("CGPA Calculator");

        SubjectNo = (Spinner) findViewById(R.id.subjectNo);


        Credit_1 = (Spinner) findViewById(R.id.credit_1);
        Credit_2 = (Spinner) findViewById(R.id.credit_2);
        Credit_3 = (Spinner) findViewById(R.id.credit_3);
        Credit_4 = (Spinner) findViewById(R.id.credit_4);
        Credit_5 = (Spinner) findViewById(R.id.credit_5);
        Credit_6 = (Spinner) findViewById(R.id.credit_6);
        Credit_7 = (Spinner) findViewById(R.id.credit_7);

        Grade_1 = (Spinner) findViewById(R.id.grade_1);
        Grade_2 = (Spinner) findViewById(R.id.grade_2);
        Grade_3 = (Spinner) findViewById(R.id.grade_3);
        Grade_4 = (Spinner) findViewById(R.id.grade_4);
        Grade_5 = (Spinner) findViewById(R.id.grade_5);
        Grade_6 = (Spinner) findViewById(R.id.grade_6);
        Grade_7 = (Spinner) findViewById(R.id.grade_7);

        buttonCheck = (Button) findViewById(R.id.buttonCheck);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Subject_S = SubjectNo.getSelectedItem().toString();
                double Subject_D = Double.parseDouble(Subject_S);

                double sum=0;


                if(Subject_D == 1){

                        String Credit_S1 = Credit_1.getSelectedItem().toString().trim();
                        String Grade_S1 = Grade_1.getSelectedItem().toString().trim();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    double Grade_D1 = Double.parseDouble(Grade_S1);

                    if(Credit_D1 == 0.00 || Grade_D1 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();

                    }else {

                        double mul1 = Credit_D1 * Grade_D1;
                        sum = mul1 / Credit_D1;



                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Calculator.this,Calculator.class));
                                    }
                                });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);
                    }
                }
                else  if(Subject_D == 2){

                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);

                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);

                    if(Credit_D1 == 0.00 || Credit_D2 == 0.00|| Grade_D1 == 0.00 || Grade_D2 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();

                    }else {


                        double mul1 = Credit_D1 * Grade_D1;
                        double mul2 = Credit_D2 * Grade_D2;

                        sum = (mul1 + mul2) / (Credit_D1 + Credit_D2);


                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Calculator.this,Calculator.class));
                            }
                        });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);
                    }
                }
                else  if(Subject_D == 3){

                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);
                    String Credit_S3 = Credit_3.getSelectedItem().toString();
                    double Credit_D3 = Double.parseDouble(Credit_S3);

                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);
                    String Grade_S3 = Grade_3.getSelectedItem().toString();
                    double Grade_D3 = Double.parseDouble(Grade_S3);

                    if(Credit_D1 == 0.00 || Credit_D2 == 0.00 || Credit_D3 == 0.00 || Grade_D1 == 0.00 || Grade_D2 == 0.00 || Grade_D3 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();

                    }else {


                        double mul1 = Credit_D1 * Grade_D1;
                        double mul2 = Credit_D2 * Grade_D2;
                        double mul3 = Credit_D3 * Grade_D3;


                        sum = (mul1 + mul2 + mul3) / (Credit_D1 + Credit_D2 + Credit_D3);


                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Calculator.this,Calculator.class));
                                    }
                                });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);
                    }
                }
                else  if(Subject_D == 4){

                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);
                    String Credit_S3 = Credit_3.getSelectedItem().toString();
                    double Credit_D3 = Double.parseDouble(Credit_S3);
                    String Credit_S4 = Credit_4.getSelectedItem().toString();
                    double Credit_D4 = Double.parseDouble(Credit_S4);


                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);
                    String Grade_S3 = Grade_3.getSelectedItem().toString();
                    double Grade_D3 = Double.parseDouble(Grade_S3);
                    String Grade_S4 = Grade_4.getSelectedItem().toString();
                    double Grade_D4 = Double.parseDouble(Grade_S4);

                        if(Credit_D1 == 0.00 || Credit_D2 == 0.00 || Credit_D3 == 0.00 || Credit_D4 == 0.00 ||
                                Grade_D1 == 0.00 || Grade_D2 == 0.00 || Grade_D3 == 0.00 || Grade_D4 == 0.00) {

                            Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();

                        }else {

                            double mul1 = Credit_D1 * Grade_D1;
                            double mul2 = Credit_D2 * Grade_D2;
                            double mul3 = Credit_D3 * Grade_D3;
                            double mul4 = Credit_D4 * Grade_D4;

                            sum = (mul1 + mul2 + mul3 + mul4) / (Credit_D1 + Credit_D2 + Credit_D3 + Credit_D4);


                            DecimalFormat df = new DecimalFormat("#.##");
                            String result = df.format(sum);

                            aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                            aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Calculator.this,Calculator.class));
                                        }
                                    });


                            AlertDialog alertDialog = aleartDailogueBuilder.create();
                            alertDialog.show();
                            alertDialog.getWindow().setLayout(650,350);
                            TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                            messageView.setGravity(Gravity.CENTER);
                            messageView.setTextSize(20);
                            messageView.setPadding(0,70,0,30);
                        }
                }
                else  if(Subject_D == 5){

                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);
                    String Credit_S3 = Credit_3.getSelectedItem().toString();
                    double Credit_D3 = Double.parseDouble(Credit_S3);
                    String Credit_S4 = Credit_4.getSelectedItem().toString();
                    double Credit_D4 = Double.parseDouble(Credit_S4);
                    String Credit_S5 = Credit_5.getSelectedItem().toString();
                    double Credit_D5 = Double.parseDouble(Credit_S5);

                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);
                    String Grade_S3 = Grade_3.getSelectedItem().toString();
                    double Grade_D3 = Double.parseDouble(Grade_S3);
                    String Grade_S4 = Grade_4.getSelectedItem().toString();
                    double Grade_D4 = Double.parseDouble(Grade_S4);
                    String Grade_S5 = Grade_5.getSelectedItem().toString();
                    double Grade_D5 = Double.parseDouble(Grade_S5);

                    if(Credit_D1 == 0.00 || Credit_D2 == 0.00 || Credit_D3 == 0.00 || Credit_D4 == 0.00 || Credit_D5 == 0.00 ||
                                    Grade_D1 == 0.00 || Grade_D2 == 0.00 || Grade_D3 == 0.00 || Grade_D4 == 0.00 || Grade_D5 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();
                    }else {

                        double mul1 = Credit_D1 * Grade_D1;
                        double mul2 = Credit_D2 * Grade_D2;
                        double mul3 = Credit_D3 * Grade_D3;
                        double mul4 = Credit_D4 * Grade_D4;
                        double mul5 = Credit_D5 * Grade_D5;


                        sum = (mul1 + mul2 + mul3 + mul4 + mul5) / (Credit_D1 + Credit_D2 + Credit_D3 + Credit_D4 + Credit_D5);


                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Calculator.this,Calculator.class));
                                    }
                                });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);
                    }
                }
                else  if(Subject_D == 6){


                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);
                    String Credit_S3 = Credit_3.getSelectedItem().toString();
                    double Credit_D3 = Double.parseDouble(Credit_S3);
                    String Credit_S4 = Credit_4.getSelectedItem().toString();
                    double Credit_D4 = Double.parseDouble(Credit_S4);
                    String Credit_S5 = Credit_5.getSelectedItem().toString();
                    double Credit_D5 = Double.parseDouble(Credit_S5);
                    String Credit_S6 = Credit_6.getSelectedItem().toString();
                    double Credit_D6 = Double.parseDouble(Credit_S6);


                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);
                    String Grade_S3 = Grade_3.getSelectedItem().toString();
                    double Grade_D3 = Double.parseDouble(Grade_S3);
                    String Grade_S4 = Grade_4.getSelectedItem().toString();
                    double Grade_D4 = Double.parseDouble(Grade_S4);
                    String Grade_S5 = Grade_5.getSelectedItem().toString();
                    double Grade_D5 = Double.parseDouble(Grade_S5);
                    String Grade_S6 = Grade_6.getSelectedItem().toString();
                    double Grade_D6 = Double.parseDouble(Grade_S6);

                    if(Credit_D1 == 0.00 || Credit_D2 == 0.00 || Credit_D3 == 0.00 || Credit_D4 == 0.00 || Credit_D5 == 0.00 || Credit_D6 == 0.00 ||
                            Grade_D1 == 0.00 || Grade_D2 == 0.00 || Grade_D3 == 0.00 || Grade_D4 == 0.00 || Grade_D5 == 0.00 || Grade_D6 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();
                    }else {

                        double mul1 = Credit_D1 * Grade_D1;
                        double mul2 = Credit_D2 * Grade_D2;
                        double mul3 = Credit_D3 * Grade_D3;
                        double mul4 = Credit_D4 * Grade_D4;
                        double mul5 = Credit_D5 * Grade_D5;
                        double mul6 = Credit_D6 * Grade_D6;

                        sum = (mul1 + mul2 + mul3 + mul4 + mul5 + mul6) / (Credit_D1 + Credit_D2 + Credit_D3 + Credit_D4 + Credit_D5 + Credit_D6);


                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Calculator.this,Calculator.class));
                                    }
                                });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);
                    }
                }
                else  if(Subject_D == 7){


                    String Credit_S1 = Credit_1.getSelectedItem().toString();
                    double Credit_D1 = Double.parseDouble(Credit_S1);
                    String Credit_S2 = Credit_2.getSelectedItem().toString();
                    double Credit_D2 = Double.parseDouble(Credit_S2);
                    String Credit_S3 = Credit_3.getSelectedItem().toString();
                    double Credit_D3 = Double.parseDouble(Credit_S3);
                    String Credit_S4 = Credit_4.getSelectedItem().toString();
                    double Credit_D4 = Double.parseDouble(Credit_S4);
                    String Credit_S5 = Credit_5.getSelectedItem().toString();
                    double Credit_D5 = Double.parseDouble(Credit_S5);
                    String Credit_S6 = Credit_6.getSelectedItem().toString();
                    double Credit_D6 = Double.parseDouble(Credit_S6);
                    String Credit_S7 = Credit_7.getSelectedItem().toString();
                    double Credit_D7 = Double.parseDouble(Credit_S7);


                    String Grade_S1 = Grade_1.getSelectedItem().toString();
                    double Grade_D1 = Double.parseDouble(Grade_S1);
                    String Grade_S2 = Grade_2.getSelectedItem().toString();
                    double Grade_D2 = Double.parseDouble(Grade_S2);
                    String Grade_S3 = Grade_3.getSelectedItem().toString();
                    double Grade_D3 = Double.parseDouble(Grade_S3);
                    String Grade_S4 = Grade_4.getSelectedItem().toString();
                    double Grade_D4 = Double.parseDouble(Grade_S4);
                    String Grade_S5 = Grade_5.getSelectedItem().toString();
                    double Grade_D5 = Double.parseDouble(Grade_S5);
                    String Grade_S6 = Grade_6.getSelectedItem().toString();
                    double Grade_D6 = Double.parseDouble(Grade_S6);
                    String Grade_S7 = Grade_7.getSelectedItem().toString();
                    double Grade_D7 = Double.parseDouble(Grade_S7);

                    if(Credit_D1 == 0.00 || Credit_D2 == 0.00 || Credit_D3 == 0.00 || Credit_D4 == 0.00 || Credit_D5 == 0.00 || Credit_D6 == 0.00 || Credit_D7 == 0.00||
                            Grade_D1 == 0.00 || Grade_D2 == 0.00 || Grade_D3 == 0.00 || Grade_D4 == 0.00 || Grade_D5 == 0.00 || Grade_D6 == 0.00 || Grade_D7 == 0.00) {

                        Toast.makeText(Calculator.this, "please enter all the details", Toast.LENGTH_SHORT).show();
                    }else {


                        double mul1 = Credit_D1 * Grade_D1;
                        double mul2 = Credit_D2 * Grade_D2;
                        double mul3 = Credit_D3 * Grade_D3;
                        double mul4 = Credit_D4 * Grade_D4;
                        double mul5 = Credit_D5 * Grade_D5;
                        double mul6 = Credit_D6 * Grade_D6;
                        double mul7 = Credit_D7 * Grade_D7;

                        sum = (mul1 + mul2 + mul3 + mul4 + mul5 + mul6 + mul7) /
                                (Credit_D1 + Credit_D2 + Credit_D3 + Credit_D4 + Credit_D5 + Credit_D6 + Credit_D7);



                        DecimalFormat df = new DecimalFormat("#.##");
                        String result = df.format(sum);

                        aleartDailogueBuilder = new AlertDialog.Builder(Calculator.this);
                        aleartDailogueBuilder.setMessage("Your CGPA: "+result)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Calculator.this,Calculator.class));
                                    }
                                });


                        AlertDialog alertDialog = aleartDailogueBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(650,350);
                        TextView messageView = (TextView)alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        messageView.setTextSize(20);
                        messageView.setPadding(0,70,0,30);

                    }


                }
                
            }
        });

    }






}
