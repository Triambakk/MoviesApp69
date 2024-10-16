package com.example.moviesapp.Adapters;

import com.example.moviesapp.R; // Ensure you have this import

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.Domains.SliderItems;

import java.util.List;

public class SlidersAdapter extends RecyclerView.Adapter<SlidersAdapter.SliderViewholder> {
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Update this logic based on your requirements
            if (!sliderItems.isEmpty()) {
                sliderItems.add(sliderItems.get(0)); // Add first item to create an infinite loop effect
                notifyDataSetChanged();
            }
        }
    };

    public SlidersAdapter(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlidersAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        // Inflate the layout for the ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.slider_viewholder, parent, false);
        return new SliderViewholder(view); // Make sure SliderViewholder constructor accepts View
    }

    @Override
    public void onBindViewHolder(@NonNull SlidersAdapter.SliderViewholder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2) {
            viewPager2.post(runnable); // Trigger the runnable when reaching the second to last item
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTxt, genreTxt, ageTxt, yearTxt, timeTxt;

        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            genreTxt = itemView.findViewById(R.id.genreTxt);
            ageTxt = itemView.findViewById(R.id.ageTxt);
            yearTxt = itemView.findViewById(R.id.yearTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
        }

        void setImage(SliderItems sliderItems) { // Parameter name 'sliderItems'
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));

            // Load image using Glide
            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);

            // Set text for the TextViews
            nameTxt.setText(sliderItems.getName());
            genreTxt.setText(sliderItems.getGenre());
            ageTxt.setText(sliderItems.getAge());
            yearTxt.setText(String.valueOf(sliderItems.getYear())); // More readable way to convert to String
            timeTxt.setText(sliderItems.getTime());
        }
    }
}
