package com.example.safecar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class CarListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Car> carsList;

    public CarListAdapter(Context context, int layout, ArrayList<Car> carsList) {
        this.context = context;
        this.layout = layout;
        this.carsList = carsList;
    }

    @Override
    public int getCount() {
        return carsList.size();
    }

    @Override
    public Object getItem(int position) {
        return carsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice,txtmodel,txtbrand,txtagency,txtkms,txtphn,txtloc,txtemail;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            holder.txtmodel = (TextView) row.findViewById(R.id.txtmodel);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.txtbrand = (TextView) row.findViewById(R.id.txtbrand);
            holder.txtagency = (TextView) row.findViewById(R.id.txtagency);
            holder.txtkms = (TextView) row.findViewById(R.id.txtkms);
            holder.txtphn = (TextView) row.findViewById(R.id.txtphn);
            holder.txtloc = (TextView) row.findViewById(R.id.txtloc);
            holder.txtemail = (TextView) row.findViewById(R.id.txtemail);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Car car = carsList.get(position);


        holder.txtPrice.setText(car.getPrice());
        holder.txtbrand.setText(car.getBrand());
        holder.txtmodel.setText(car.getModel());
        holder.txtagency.setText(car.getAgency());
        holder.txtkms.setText(car.getkms());
        holder.txtphn.setText(car.getPhn());
        holder.txtloc.setText(car.getLocation());
        holder.txtemail.setText(car.getemail());

        byte[] foodImage = car.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
