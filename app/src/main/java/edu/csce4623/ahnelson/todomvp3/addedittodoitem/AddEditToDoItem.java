package edu.csce4623.ahnelson.todomvp3.addedittodoitem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;

import edu.csce4623.ahnelson.todomvp3.R;
import edu.csce4623.ahnelson.todomvp3.data.ToDoItem;
import edu.csce4623.ahnelson.todomvp3.data.ToDoItemRepository;
import edu.csce4623.ahnelson.todomvp3.data.ToDoProvider;

public class AddEditToDoItem extends AppCompatActivity {
    ToDoItem item;
    EditText etTitle;
    EditText etContent;
    CheckBox check;
    EditText etTime;
    EditText etDueDate;
    long dueDate;
    Calendar calendar = Calendar.getInstance();





    boolean checked = false;
    Button btnSaveItem;
    Button btnDeleteItem;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_add_edit_to_do_item);

        etTitle = findViewById(R.id.etItemTitle);
        etContent = findViewById(R.id.etItemContent);
        btnSaveItem = findViewById(R.id.btnSaveToDoItem);
        check = (CheckBox) findViewById(R.id.checkBox);
        btnDeleteItem = findViewById(R.id.btnDelete);
        etDueDate= findViewById(R.id.etDate);


        Intent callingIntent = getIntent();
        if(callingIntent!= null){
            if(callingIntent.hasExtra("ToDoItem") ){
                item = (ToDoItem)callingIntent.getSerializableExtra("ToDoItem");
            }else {
                item = new ToDoItem();
                item.setTitle("Title");
                item.setContent("Content");
                item.setCompleted(false);

            }
        }

        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItems();
            }
        });
        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  deleteToDoItem(item.getId());
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        etTitle.setText(item.getTitle());
        etContent.setText(item.getContent());
        if (item.getCompleted()== true) {
            check.setChecked(true);
        }else
            check.setChecked(false);
        //etDueDate.setText(item.getDueDate());
        //etDueDate.setText(item.getDueDate().toString());


    }

    @Override
    protected void onStop(){
        super.onStop();
        item.setTitle(etTitle.getText().toString());
        item.setContent(etContent.getText().toString());
    }

    private void saveItems(){
        item.setTitle(etTitle.getText().toString());
        item.setContent(etContent.getText().toString());
        // item.setDueDate(etDueDate.getText().parseLong());
        if (check.isChecked()) {
            item.setCompleted(true);
        }else
            item.setCompleted(false);
        Intent dataIntent = new Intent();
        dataIntent.putExtra("ToDoItem",item);
        setResult(RESULT_OK,dataIntent);
        // Calendar c = Calendar.getInstance();
        // c.add(Calendar.SECOND,30);
        finish();
    }

}


