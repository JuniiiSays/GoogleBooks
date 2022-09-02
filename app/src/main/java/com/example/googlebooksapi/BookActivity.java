package com.example.googlebooksapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    /** Adapter for the list of Books */
    private BookAdapter mAdapter;

    public static final String QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the AsyncTask to fetch the earthquake data
        BookAsyncTask task = new BookAsyncTask();
        task.execute(QUERY_URL);


        EditText queryEditText = (EditText) findViewById(R.id.query_edit_text);
        Button searchButton = (Button) findViewById(R.id.search_button);

        /*ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book("Bio", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Physics", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Chemistry", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));
        books.add(new Book("Boi", "Juni", "Ryk Tech Studio"));*/

        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mAdapter);
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>>{

        @Override
        protected List<Book> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null){
                return null;
            }

            List<Book> result = QueryUtils.fetchBooksData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            mAdapter.clear();

            if (books != null && !books.isEmpty()) {
                mAdapter.addAll(books);
            }
        }
    }
}