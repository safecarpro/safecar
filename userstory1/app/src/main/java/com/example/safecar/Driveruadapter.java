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

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class Driveruadapter extends BaseAdapter {

    private ArrayList<Driver2> driverlist;
    private Context context;


    public Driveruadapter(ArrayList<Driver2> list,Context cont){
        this.driverlist = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.driverlist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.driverlist.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder{
        ImageView uimageView;
        TextView duid, duname,duaddress,duage,dugender,duprice,dubadge,dulocation,duyoe,duphno,duemail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.driverulist,null);

            holder = new ViewHolder();
            holder.duid = convertView.findViewById(R.id.duid);
            holder.duname = convertView.findViewById(R.id.duname);
            holder.uimageView = (ImageView) convertView.findViewById(R.id.imgdriver);
            holder.duaddress = (TextView) convertView.findViewById(R.id.dname);
            holder.duage =  convertView.findViewById(R.id.daddress);
            holder.dugender = (TextView) convertView.findViewById(R.id.dage);
            holder.duprice = (TextView) convertView.findViewById(R.id.dgender);
            holder.dubadge = (TextView) convertView.findViewById(R.id.dbadge);
            holder.dulocation = (TextView) convertView.findViewById(R.id.dlocation);
            holder.duyoe = (TextView) convertView.findViewById(R.id.dyoe);
            holder.duphno = (TextView) convertView.findViewById(R.id.dphno);
            holder.duemail = (TextView) convertView.findViewById(R.id.demail);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        Driver2 driver2 =driverlist.get(position);
        holder.duid.setText(Integer.toString(driver2.getId()));
        holder.duname.setText(driver2.getName());
        holder.duaddress.setText(driver2.getAddress());
        holder.duage.setText(driver2.getAge());
        holder.dugender.setText(driver2.getGender());
        holder.duprice.setText(driver2.getPrice());
        holder.dubadge.setText(driver2.getBadge());
        holder.dulocation.setText(driver2.getLocation());
        holder.duyoe.setText(driver2.getYoe());
        holder.duphno.setText(driver2.getPhno());
        holder.duemail.setText(driver2.getEmail());


        byte[] driverImage = driver2.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(driverImage, 0, driverImage.length);
        holder.uimageView.setImageBitmap(bitmap);

        return convertView;
    }

}
