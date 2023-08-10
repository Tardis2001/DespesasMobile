package com.matheus.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class updateActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    Button update,back;
    Animation rightanim,leftanim,topanim;
    RadioButton lucro,despesa;
    CalendarView calendarView;
    TextView labelview;
    DatePicker date;
    Calendar calendar;
    EditText name,amount,description,id;
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
        id = findViewById(R.id.id);

        id.setAnimation(leftanim);
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
                    Toast.makeText(updateActivity.this,"Lucro Alterado com sucesso!",Toast.LENGTH_LONG).show();
                    updateToDb();
                }
                else if(despesa.isChecked()) {
                    Toast.makeText(updateActivity.this,"Despesa Alterado com sucesso!",Toast.LENGTH_LONG).show();

                    updateToDb();
                }else{
                    Toast.makeText(updateActivity.this,"Selecione se é uma despesa ou lucro!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateToDb() {

        double numdespesa = 0;
        try {

            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);
            ContentValues data = new ContentValues();
            data.put("name", String.valueOf(name.getText()));
            data.put("description", String.valueOf(description.getText()));
            if (despesa.isChecked())
                numdespesa = -1 * Double.valueOf(String.valueOf(amount.getText()));
            else if (lucro.isChecked())
                numdespesa = Double.valueOf(String.valueOf(amount.getText()));

            data.put("amount", String.valueOf(numdespesa));
            data.put("date", String.valueOf(getDate()));
            db.update("expense",data,"id = ?",new String[]{String.valueOf(id.getText())});
        }catch (SQLException e) {
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