package com.example.aboutzzmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboutzzmovies.Adapters.CastRecyclerAdapter;
import com.example.aboutzzmovies.Listeners.OnDetailsApiListener;
import com.example.aboutzzmovies.Models.DetailApiResponse;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_name,textView_movie_released,textView_movie_runtime,textView_movie_rating,textView_movie_votes,textView_movie_plot;
    RecyclerView recycler_movie_cast;
    ImageView imageView_movie_poster;
    CastRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_movie_name = findViewById(R.id.textView_movie_name);
        textView_movie_released = findViewById(R.id.textView_movie_released);
        textView_movie_runtime = findViewById(R.id.textView_movie_runtime);
        textView_movie_rating = findViewById(R.id.textView_movie_rating);
        textView_movie_votes = findViewById(R.id.textView_movie_votes);
        textView_movie_plot = findViewById(R.id.textView_movie_plot);
        recycler_movie_cast = findViewById(R.id.recycler_movie_cast);
        imageView_movie_poster = findViewById(R.id.imageView_movie_poster);

        manager = new RequestManager(this);

        String movie_id = getIntent().getStringExtra("data");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Details");
        dialog.show();

        manager.searchMovieDetails(listener,movie_id);



    }
    private OnDetailsApiListener listener = new OnDetailsApiListener() {
        @Override
        public void onResponse(DetailApiResponse response) {
            dialog.dismiss();
          if (response.equals(null)){
              Toast.makeText(DetailsActivity.this, "ERROR!!", Toast.LENGTH_SHORT).show();
              return;
          }
          showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Sorry Something went wrong!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showResults(DetailApiResponse response) {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released.setText("Year of Release: "+ response.getYear());
        textView_movie_runtime.setText("Duration: " + response.getLength());
        textView_movie_rating.setText("Rating: " + response.getRating());
        textView_movie_votes.setText(  response.getRating_votes() +" Votes");
        textView_movie_plot.setText(response.getPlot());

        try {
            Picasso.get().load(response.getPoster()).into(imageView_movie_poster);
        }catch (Exception e){
            e.printStackTrace();
        }
        recycler_movie_cast.setHasFixedSize(true);
        recycler_movie_cast.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CastRecyclerAdapter(this,response.getCast());
        recycler_movie_cast.setAdapter(adapter);
    }
}