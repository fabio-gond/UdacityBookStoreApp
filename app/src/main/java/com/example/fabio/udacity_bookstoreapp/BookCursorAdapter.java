package com.example.fabio.udacity_bookstoreapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabio.udacity_bookstoreapp.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter{
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    private static final int EXISTING_BOOK_LOADER = 0;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);
        TextView priceTextView = view.findViewById(R.id.price);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        Button saleButton = view.findViewById(R.id.btn_sale);

        // Find the columns of book attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int suppNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPP_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);

        // Read the book attributes from the Cursor for the current book
        String bookName = cursor.getString(nameColumnIndex);
        String suppName = cursor.getString(suppNameColumnIndex);
        Float bookPrice = cursor.getFloat(priceColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);


        // Update the TextViews with the attributes for the current book
        nameTextView.setText(bookName);
        summaryTextView.setText(suppName);
        priceTextView.setText(String.format("%10.2f", bookPrice));
        quantityTextView.setText(String.valueOf(quantity) + " " + context.getString(R.string.pieces_abbr));

        saleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                final long id = listView.getItemIdAtPosition(position);

                mainActivity.saleBook(id);
            }
        });
    }

}
