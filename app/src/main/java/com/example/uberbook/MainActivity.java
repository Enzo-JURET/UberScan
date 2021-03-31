package com.example.uberbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.uberbook.schemas.Book;
import com.example.uberbook.schemas.User;
import com.example.uberbook.utils.Api;

import java.util.List;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Api api;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = new Api();
        context = this;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ExpandableBottomBar bottomBar = findViewById(R.id.expandable_bottom_bar);

        ExpandableBottomBarMenuItem.Builder itemsBuilder = new ExpandableBottomBarMenuItem.Builder(context);

        itemsBuilder.addItem;

        bottomBar.addItems("Test");

        Callback<Book> addBookCallback = new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                assert response.body() != null;
                Toast.makeText(context, "Logged in", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Echoué", Toast.LENGTH_LONG).show();
            }
        };

        Callback<User> loginCallback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                assert response.body() != null;

                Book newBook = new Book();
                newBook.setTitle("La semaine de 4 heures");
                newBook.setAuthor("Timoty FERRIS");
                newBook.setDescription("null");
                newBook.setIsbn(6358);
                api.addBooks(response.body(), newBook, addBookCallback);

                Toast.makeText(context, "Ajout du livre ok", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Ajout echoué", Toast.LENGTH_LONG).show();
            }
        };

        api.login("test", "test123", loginCallback);
    }
}