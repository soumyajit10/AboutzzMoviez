package com.example.aboutzzmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aboutzzmovies.Adapters.HomeRecyclerAdapter;
import com.example.aboutzzmovies.Listeners.OnMovieClickListeners;
import com.example.aboutzzmovies.Listeners.OnSearchApiListener;
import com.example.aboutzzmovies.Models.SearchApiResponse;

public class MainActivity extends AppCompatActivity  implements OnMovieClickListeners {

    SearchView search_view ;
    RecyclerView recyclerView_home;
    HomeRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        search_view = findViewById(R.id.search_view);
        recyclerView_home = findViewById(R.id.recycler_view_home);

        dialog = new ProgressDialog(this);

        manager = new RequestManager(this);


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("finding search results..");
                dialog.show();
                manager.searchMovies(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private  final OnSearchApiListener listener = new OnSearchApiListener() {
        @Override
        public void onResponse(SearchApiResponse searchApiResponse) {
            dialog.dismiss();
            if (searchApiResponse == null){
                Toast.makeText(MainActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(searchApiResponse);

        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();

        }
    };


    private void showResult(SearchApiResponse searchApiResponse) {
       recyclerView_home.setHasFixedSize(true);
       recyclerView_home.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
       adapter = new HomeRecyclerAdapter(this,searchApiResponse.getTitles(),this);
       recyclerView_home.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(String id) {
        Toast.makeText(MainActivity.this,"opening please hold on", Toast.LENGTH_SHORT).show();
       // Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",id));



    }
}