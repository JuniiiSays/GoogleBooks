package com.example.googlebooksapi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0 ,books);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView bookNameTextView = (TextView) listItemView.findViewById(R.id.book_name_text_view);
        TextView bookAuthorTextView = (TextView) listItemView.findViewById(R.id.book_author_text_view);
        /*TextView bookPublisherTextView = (TextView) listItemView.findViewById(R.id.book_publisher_text_view);*/

        String name = currentBook.getBookTitle();
        String author = currentBook.getBookAuthor();
        String publisher = currentBook.getBookPublisher();

        bookNameTextView.setText(name);
        bookAuthorTextView.setText(author);
        /*bookPublisherTextView.setText(publisher);*/

        return listItemView;
    }
}
