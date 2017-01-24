package com.example.hp.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
public class BasicOperations extends AppCompatActivity {
    private String text="";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_operations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textView=(TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
    public void onKeyPress(View v){
        int id=v.getId();
        textView=(TextView) findViewById(R.id.textView);
        if(id==R.id.divide){
            text+="/";
            textView.setText(text);
        }
        else if(id==R.id.multiply){
            text+="*";
            textView.setText(text);
        }
        else if(id==R.id.add){
            text+="+";
            textView.setText(text);
        }
        else if(id==R.id.subtract){
            text+="-";
            textView.setText(text);
        }
        else if(id==R.id.one){
            text+="1";
            textView.setText(text);
        }
        else if(id==R.id.two){
            text+="2";
            textView.setText(text);
        }
        else if(id==R.id.three){
            text+="3";
            textView.setText(text);
        }
        else if(id==R.id.four){
            text+="4";
            textView.setText(text);
        }
        else if(id==R.id.five){
            text+="5";
            textView.setText(text);
        }
        else if(id==R.id.six){
            text+="6";
            textView.setText(text);
        }
        else if(id==R.id.seven){
            text+="7";
            textView.setText(text);
        }
        else if(id==R.id.eight){
            text+="8";
            textView.setText(text);
        }
        else if(id==R.id.nine){
            text+="9";
            textView.setText(text);
        }
        else if(id==R.id.point){
            text+=".";
            textView.setText(text);
        }
        else if(id==R.id.power){
            text+="^";
            textView.setText(text);
        }
        else if(id==R.id.zero){
            text+="0";
            textView.setText(text);
        }
        else if(id==R.id.open){
            text+="(";
            textView.setText(text);
        }
        else if(id==R.id.close){
            text+=")";
            textView.setText(text);
        }
        else if(id==R.id.ncr){
            text+="C";
            textView.setText(text);
        }
    }
    public void clear(View v){
        textView=(TextView) findViewById(R.id.textView);
        text=new String("");
        textView.setText(text);
    }
    public void equalTo(View v){
        textView=(TextView) findViewById(R.id.textView);
        try {
            if (text.equals("")) {
                text = new String("0");
                textView.setText(text);
            } else {
                DmasImplimentation expression = new DmasImplimentation();
                text = expression.evaluateExpressionWithBraces(text) + "";
                textView.setText(text);
            }
        }
        catch (Exception e){
            textView.setText("Syntax error");
        }
    }
    public void back(View v){
        textView=(TextView) findViewById(R.id.textView);
        if (text.equals("")) {
            text=new String("");
            textView.setText(text);}
        else if(!textView.getText().equals("")) {
            text = text.substring(0, text.length() - 1);
            textView.setText(text);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_operations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
