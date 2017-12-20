package net.prahasiwi.carirumahsakit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.prahasiwi.carirumahsakit.R;
import net.prahasiwi.carirumahsakit.activities.DetailRumahActivity;
import net.prahasiwi.carirumahsakit.helper.Constanst;
import net.prahasiwi.carirumahsakit.model.Rumah;

import java.util.List;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public class RumahAdapter extends RecyclerView.Adapter<RumahAdapter.RumahViewHolder> {
    private Context context;
    private List<Rumah> listRumah;

    public RumahAdapter(Context context, List<Rumah> listRumah){
        this.context = context;
        this.listRumah = listRumah;
    }
    @Override
    public RumahAdapter.RumahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rumah, parent, false);
        return new RumahViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RumahAdapter.RumahViewHolder holder, final int position) {
        String linkGambar = listRumah.get(position).getGambarRumah();
        Glide.with(context).load("https://drive.google.com/thumbnail?id=" + linkGambar).into(holder.ivItemGambarRumah);
        holder.tvItemNamaRumah.setText(listRumah.get(position).getNamaRumah());
        holder.tvItemAlamatRumah.setText(listRumah.get(position).getAlamatRumah());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();

                b.putString(Constanst.ID_RUMAH,listRumah.get(position).getIdRumah());
                b.putString(Constanst.NAMA_RUMAH,listRumah.get(position).getNamaRumah());
                b.putString(Constanst.GAMBAR_RUMAH,listRumah.get(position).getGambarRumah());
                b.putString(Constanst.ALAMAT_RUMAH,listRumah.get(position).getAlamatRumah());
                b.putString(Constanst.EMAIL_RUMAH,listRumah.get(position).getEmailRumah());
                b.putString(Constanst.TELP_RUMAH,listRumah.get(position).getTeleponRumah());
                b.putString(Constanst.WEB_RUMAH,listRumah.get(position).getWebRumah());
                b.putString(Constanst.LATITUDE_RUMAH,listRumah.get(position).getLatRumah());
                b.putString(Constanst.LONGITUDE_RUMAH,listRumah.get(position).getLongRumah());

                //mencetak Intent
                Intent i = new Intent(context, DetailRumahActivity.class);
                i.putExtras(b);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRumah.size();
    }

    public class RumahViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemGambarRumah;
        TextView tvItemNamaRumah;
        TextView tvItemAlamatRumah;
        public RumahViewHolder(View itemView) {
            super(itemView);
            ivItemGambarRumah = (ImageView) itemView.findViewById(R.id.iv_item_gambar);
            tvItemNamaRumah = (TextView) itemView.findViewById(R.id.tv_item_nama);
            tvItemAlamatRumah = (TextView) itemView.findViewById(R.id.tv_item_alamat);
        }
    }
}
