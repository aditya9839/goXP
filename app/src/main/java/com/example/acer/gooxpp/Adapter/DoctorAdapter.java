package com.example.acer.gooxpp.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.gooxpp.Activity.BookAnAppoinment;
import com.example.acer.gooxpp.Activity.DoctorProfile;
import com.example.acer.gooxpp.Activity.MainActivity;
import com.example.acer.gooxpp.Model.Doctors;
import com.example.acer.gooxpp.R;

import java.util.List;

/**
 * Created by acer on 23-Jun-18.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context mCtx;
    private List<Doctors> doctorsList;


    public DoctorAdapter(Context mCtx, List<Doctors> doctorsList) {
        this.mCtx = mCtx;
        this.doctorsList = doctorsList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card2, null);
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
            holder.imageView.setBackground(mCtx.getResources().getDrawable(doctors.getImage(), null));
        }
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(doctors.getImage(),null));


    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {

        private Context context = null;
        ImageView imageView;
        TextView doctorName, availabilty, rating, price;
        Button mcallDoctor, mbook;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.imageView);
            mcallDoctor = itemView.findViewById(R.id.callDoctor);
            mbook = itemView.findViewById(R.id.book);
            doctorName = itemView.findViewById(R.id.textViewTitle);
            availabilty = itemView.findViewById(R.id.textViewShortDesc);
            mbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BookAnAppoinment.class);
                    context.startActivity(intent);
                }
            });
            mcallDoctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("call", "call");
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+919654161080"));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    context.startActivity(intent);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,DoctorProfile.class);
                    context.startActivity(intent);
                }
            });
//            rating = itemView.findViewById(R.id.textViewRating);
//            price = itemView.findViewById(R.id.textViewPrice);
        }

    }

}
