package com.matheus.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class updateActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    Button update,back;
    Animation rightanim,leftanim,topanim;
    RadioButton lucro,despesa;
    CalendarView calendarView;
    TextView labelview;
    DatePicker date;
    Calendar calendar;
    EditText name,amount,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        rightanim = AnimationUtils.loadAnimation(this,R.anim.right_animation);
        leftanim = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        name = findViewById(R.id.name);
        amount = findViewById(R.id.valor);
        description = findViewById(R.id.descricao);
        despesa =  findViewById(R.id.RadioDespesa);
        lucro = findViewById(R.id.RadioLucro);
        update = findViewById(R.id.update);
        back =findViewById(R.id.back);
        labelview = findViewById(R.id.labeladd);
        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();


        labelview.setAnimation(topanim);
        name.setAnimation(rightanim);
        amount.setAnimation(leftanim);
        description.setAnimation(rightanim);
        calendarView.setAnimation(leftanim);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateActivity.this,Options.class);
                startActivity(intent);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lucro.isChecked()){
                    Toast.makeText(updateActivity.this,"Lucro adicionado com sucesso!",Toast.LENGTH_LONG).show();
                }
                else if(despesa.isChecked()) {
                    Toast.makeText(updateActivity.this,"Despesa adicionado com sucesso!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(updateActivity.this,"Selecione se é uma despesa ou lucro!",Toast.LENGTH_LONG).show();
                }
                updateToDb();
            }
        });
    }

    private void updateToDb() {
    }
}