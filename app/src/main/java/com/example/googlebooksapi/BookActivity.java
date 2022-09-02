package com.example.googlebooksapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText queryEditText = (EditText) findViewById(R.id.query_edit_text);
        Button searchButton = (Button) findViewById(R.id.search_button);

        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));

        BookAdapter adapter = new BookAdapter(this, books);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}