package com.example.roomcomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    private static final String Note_added = "new_note";

    private EditText newnote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        newnote = findViewById(R.id.newnote);

        Button button = findViewById(R.id.added);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
             Intent resultIntent =new Intent();
             if (TextUtils.isEmpty(newnote.getText())){
                 setResult(RESULT_CANCELED,resultIntent);
             }
             else {
                 String note = newnote.getText().toString();
                 resultIntent.putExtra("note_added",note);
                 setResult(RESULT_OK,resultIntent);
             }
             finish();
            }
        });
    }
}
