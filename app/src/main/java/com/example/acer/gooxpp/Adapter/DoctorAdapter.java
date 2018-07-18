package com.example.acer.gooxpp.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.gooxpp.Model.Doctors;
import com.example.acer.gooxpp.R;

import java.util.List;

/**
 * Created by acer on 23-Jun-18.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context mCtx;
    private List<Doctors> doctorsList ;


    public DoctorAdapter(Context mCtx, List<Doctors> doctorsList) {
        this.mCtx = mCtx;
        this.doctorsList = doctorsList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card2,null);
        DoctorViewHolder holder = new DoctorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DoctorViewHolder holder, int position) {
        Doctors doctors = doctorsList.get(position);
        holder.doctorName.setText(doctors.getDoctorName());
        holder.availabilty.setText(doctors.getAvailabilty());
//        holder.price.setText(String.valueOf(doctors.getPrice()));
//        holder.rating.setText(String.valueOf(doctors.getRating()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.imageView.setBackground(mCtx.getResources().getDrawable(doctors.getImage(),null));
        }
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(doctors.getImage(),null));


    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView doctorName,availabilty,rating,price;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            doctorName = itemView.findViewById(R.id.textViewTitle);
            availabilty = itemView.findViewById(R.id.textViewShortDesc);
//            rating = itemView.findViewById(R.id.textViewRating);
//            price = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
