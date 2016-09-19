package com.giao.stockcheck;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button itemManagementButton;
    private Button itemCheckButton;
    private Button historyButton;
    private Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare View Controls
        itemManagementButton=(Button)findViewById(R.id.itemManagementButton);
        itemCheckButton=(Button)findViewById(R.id.itemCheckButton);
        historyButton=(Button)findViewById(R.id.historyButton);
        exitButton=(Button)findViewById(R.id.exitButton);
        //ViewControls Events
        CreateEvent();

    }
    private void CreateEvent()
    {
        itemManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        itemCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
