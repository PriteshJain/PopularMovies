package com.priteshjain.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.presenters.IMoviesListingView;
import com.squareup.picasso.Picasso;

import java.util.List;
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;
    private IMoviesListingView mMoviesListingView;

    public MovieListAdapter(List<Movie> mMovies, Context context, IMoviesListingView moviesListingView) {
        this.mMovies = mMovies;
        this.mContext = context;
        this.mMoviesListingView = moviesListingView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragement_movie_list_adapter_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.mMovie = mMovies.get(position);
        holder.mMovieName.setText(holder.mMovie.getTitle());
        Picasso.with(mContext).load(holder.mMovie.getMediumPosterUrl())
                .placeholder( R.drawable.loading )
                .into(holder.mMoviePoster,
                PicassoPalette.with(holder.mMovie.getMediumPosterUrl(), holder.mMoviePoster)
                        .use(PicassoPalette.Profile.VIBRANT)
                        .intoBackground(holder.mTitleBackground, PicassoPalette.Swatch.RGB));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mMovieName;
        public ImageView mMoviePoster;
        public View mTitleBackground;
        public Movie mMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            mMovieName = (TextView) itemView.findViewById(R.id.movie_name);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            mTitleBackground = itemView.findViewById(R.id.title_background);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.movie_container){
                mMoviesListingView.onMovieClicked(mMovie);
            }
        }
    }
}
