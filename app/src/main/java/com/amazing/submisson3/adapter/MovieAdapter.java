package com.amazing.submisson3.adapter;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazing.submisson3.R;
import com.amazing.submisson3.activity.MovieDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> mData = new ArrayList<>();
    public void setData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movies, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPhoto;
        TextView textViewTitle, textViewDateReleased,
                textViewVoteAverage, textViewVoteCount;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_item_title);
            textViewDateReleased = itemView.findViewById(R.id.tv_item_dateReleased);
            textViewVoteAverage = itemView.findViewById(R.id.tv_item_voteAverege);
            textViewVoteCount = itemView.findViewById(R.id.tv_item_voteCount);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);


            itemView.setOnClickListener(this);
        }
        void bind(Movie movies) {
            String vote_average = Double.toString(movies.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();


            textViewTitle.setText(movies.getTitle());
            textViewDateReleased.setText(movies.getRelease_date());
            textViewVoteAverage.setText(vote_average);
            textViewVoteCount.setText(movies.getVote_count());


            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mData.get(position);
//
            movie.setTitle(movie.getTitle());
            movie.setOverview(movie.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetailActivity.class);
            moveWithObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
