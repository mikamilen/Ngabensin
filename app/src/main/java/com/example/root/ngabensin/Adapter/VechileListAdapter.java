package com.example.root.ngabensin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.ngabensin.Model.Kendaraan;
import com.example.root.ngabensin.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by sep on 13/01/18.
 */

public class VechileListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Kendaraan> vechilelist;
    private LayoutInflater inflater = null;

    public VechileListAdapter(Context context, int layout, ArrayList<Kendaraan> vechilelist) {
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


        Kendaraan vechileItem = vechilelist.get(position);

        holder.txtName.setText(vechileItem.getNamaKendaraan());
        holder.txtJenis.setText(vechileItem.getJenisKendaraan());

        if (vechileItem.getFotoKendaraan().equals("satu")) {
            holder.imageView.setImageResource(R.drawable.motornew);
        }
        else if (vechileItem.getFotoKendaraan().equals("dua")) {
            holder.imageView.setImageResource(R.drawable.account);
        }
        else if (vechileItem.getFotoKendaraan().equals("tiga")) {
            holder.imageView.setImageResource(R.drawable.home);
        }
        else{
            Log.d(TAG, "goblog lieur");
        }
        return convertView;



    }
}

