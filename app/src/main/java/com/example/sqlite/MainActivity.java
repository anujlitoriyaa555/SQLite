package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText anujsText;
    TextView anujView;
    MyDbHandllers dbHandller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anujsText = (EditText) findViewById(R.id.anujsText);

        anujView  = (TextView) findViewById(R.id.anujView);

        dbHandller = new MyDbHandllers(this,null,null,1);
        printDatabase();

    }

    public  void addButtonClicked(){
        Products product = new Products(anujsText.getText().toString());
        dbHandller.addProduct(product);
        printDatabase();

    }

    // delete button clicked
     public  void deleteButtonClicked(){
        String inputText = anujsText.getText().toString();
        dbHandller.deleteProduct(inputText);
        printDatabase();
     }

    public void printDatabase(){
        String dbString = dbHandller.databaseToString();
        anujView.setText(dbString);
        anujsText.setText("");



    }
}
