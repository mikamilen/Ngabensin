package com.example.root.ngabensin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.ngabensin.R;
import com.example.root.ngabensin.Vechile.VechileItem;

import java.util.ArrayList;

/**
 * Created by sep on 13/01/18.
 */

public class VechileListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<VechileItem> vechilelist;
    private LayoutInflater inflater = null;

    public VechileListAdapter(Context context, int layout, ArrayList<VechileItem> vechilelist) {
        this.context = context;
        this.layout = layout;
        this.vechilelist = vechilelist;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return vechilelist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtName, txtJenis;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();

        if (convertView == null)

            convertView = inflater.inflate(layout, null);

        holder.txtName = (TextView) convertView.findViewById(R.id.textName);
        holder.txtJenis = (TextView) convertView.findViewById(R.id.textJenis);
        holder.txtJenis = (TextView) convertView.findViewById(R.id.textJenis);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);


        VechileItem vechileItem = vechilelist.get(position);

        holder.txtName.setText(vechileItem.getNmkendaraan());
        holder.txtJenis.setText(vechileItem.getJnkendaraan());

        byte[] vechileImage = vechileItem.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(vechileImage, 0, vechileImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return convertView;



    }
}

