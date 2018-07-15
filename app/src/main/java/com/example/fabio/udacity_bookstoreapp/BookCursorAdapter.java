package com.example.fabio.udacity_bookstoreapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.fabio.udacity_bookstoreapp.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter{
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);
        TextView priceTextView = view.findViewById(R.id.price);

        // Find the columns of book attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int suppNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPP_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);

        // Read the book attributes from the Cursor for the current book
        String bookName = cursor.getString(nameColumnIndex);
        String suppName = cursor.getString(suppNameColumnIndex);
        Float bookPrice = cursor.getFloat(priceColumnIndex);


        // Update the TextViews with the attributes for the current book
        nameTextView.setText(bookName);
        summaryTextView.setText(suppName);
        priceTextView.setText(String.format("%10.2f" , bookPrice));
    }
}
