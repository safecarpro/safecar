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

public class DriverListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Driver> driversList;

    public DriverListAdapter(Context context, int layout, ArrayList<Driver> driversList) {
        this.context = context;
        this.layout = layout;
        this.driversList = driversList;
    }

    @Override
    public int getCount() {
        return driversList.size();
    }

    @Override
    public Object getItem(int position) {
        return driversList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView did, dname,daddress,dage,dgender,dprice,dbadge,dlocation,dyoe,dphno,demail;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.did = row.findViewById(R.id.did);
            holder.dprice = (TextView) row.findViewById(R.id.dprice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgdriver);
            holder.dname = (TextView) row.findViewById(R.id.dname);
            holder.daddress =  row.findViewById(R.id.daddress);
            holder.dage = (TextView) row.findViewById(R.id.dage);
            holder.dgender = (TextView) row.findViewById(R.id.dgender);
            holder.dbadge = (TextView) row.findViewById(R.id.dbadge);
            holder.dlocation = (TextView) row.findViewById(R.id.dlocation);
            holder.dyoe = (TextView) row.findViewById(R.id.dyoe);
            holder.dphno = (TextView) row.findViewById(R.id.dphno);
            holder.demail = (TextView) row.findViewById(R.id.demail);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Driver driver = driversList.get(position);


        holder.did.setText(Integer.toString(driver.getId()));
        holder.dname.setText(driver.getPrice());
        holder.daddress.setText(driver.getAddress());
        holder.dage.setText(driver.getAge());
        holder.dgender.setText(driver.getGender());
        holder.dprice.setText(driver.getPrice());
        holder.dbadge.setText(driver.getBadge());
        holder.dlocation.setText(driver.getLocation());
        holder.dyoe.setText(driver.getYoe());
        holder.dphno.setText(driver.getPhno());
        holder.demail.setText(driver.getEmail());


        byte[] driverImage = driver.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(driverImage, 0, driverImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}

