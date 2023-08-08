package com.matheus.crudapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
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

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Adicionar extends AppCompatActivity {
    private SQLiteDatabase db;
    Button add,back;
    Animation rightanim,leftanim,topanim;
    RadioButton lucro,despesa;
    CalendarView calendarView;
    TextView labelview;
    DatePicker date;
    Calendar calendar;
    EditText name,amount,description;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        rightanim = AnimationUtils.loadAnimation(this,R.anim.right_animation);
        leftanim = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        name = findViewById(R.id.name);
        amount = findViewById(R.id.valor);
        description = findViewById(R.id.descricao);
        despesa =  findViewById(R.id.RadioDespesa);
        lucro = findViewById(R.id.RadioLucro);
        add = findViewById(R.id.Add);
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
                Intent intent = new Intent(Adicionar.this,Options.class);
                startActivity(intent);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lucro.isChecked()){
                    Toast.makeText(Adicionar.this,"Lucro adicionado com sucesso!",Toast.LENGTH_LONG).show();
                }
                else if(despesa.isChecked()) {
                    Toast.makeText(Adicionar.this,"Despesa adicionado com sucesso!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Adicionar.this,"Selecione se é uma despesa ou lucro!",Toast.LENGTH_LONG).show();
                }
                addToDb();
            }
        });
    }
    public void addToDb(){
        double numdespesa = 0;
        try {
            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);
            String sql = "INSERT INTO expense (name,amount,date,description) VALUES (?,?,?,?)";
            SQLiteStatement stmt = db.compileStatement(sql);
            if(despesa.isChecked()) numdespesa = -1 * Double.valueOf(String.valueOf(amount.getText()));
            else if(lucro.isChecked()) numdespesa = Double.valueOf(String.valueOf(amount.getText()));
            stmt.bindString(1, String.valueOf(name.getText()));
            stmt.bindDouble(2, numdespesa);
            stmt.bindString(3,getDate());
            stmt.bindString(4,String.valueOf(description.getText()));
            stmt.executeInsert();

            db.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public String getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        return selected_date;
    }
}