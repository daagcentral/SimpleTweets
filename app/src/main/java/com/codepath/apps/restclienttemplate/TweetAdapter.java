package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{

    private List<Tweet> mTweets;
    Context context;


    //pass in the tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets){

        mTweets = tweets;
    }

    //for each row, inflate the layout and cache the reference into ViewHolder

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    //bind the values based on the position of the element

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get tweet data according to position
        Tweet tweet = mTweets.get(position);
        //populate the view according to the data
        holder.tvUsename.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        //Glide.with(context). load(tweet.user.profileImageUrl).into(holder.ivProfileImage);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .apply(
                        RequestOptions.bitmapTransform(new RoundedCorners(200))
                )


                .into(holder.ivProfileImage);




    }



    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //create viewHolder class

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivProfileImage;
        public TextView tvUsename;
        public TextView tvBody;
        public TextView tvTime;


        public ViewHolder (View itemView){
            super(itemView);

            //preform the findViewById
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivMyProfileImage);
            tvUsename = (TextView) itemView.findViewById(R.id.tvUsername);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);



        }



    }
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }

}
