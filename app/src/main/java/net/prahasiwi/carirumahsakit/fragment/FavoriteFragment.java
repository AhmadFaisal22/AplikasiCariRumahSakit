package net.prahasiwi.carirumahsakit.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.prahasiwi.carirumahsakit.R;
import net.prahasiwi.carirumahsakit.adapter.RumahAdapter;
import net.prahasiwi.carirumahsakit.db.DatabaseHelper;
import net.prahasiwi.carirumahsakit.model.Rumah;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvFavorit;
    DatabaseHelper database;
    ArrayList<Rumah> listRumahFavorite;

    public FavoriteFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        rvFavorit = (RecyclerView) view.findViewById(R.id.rv_favorite);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvFavorit.setLayoutManager(llm);

        // mencetak database
        database = new DatabaseHelper(getActivity());

        // mendapatkan data rumah favorite dan dimasukan ke dalam listRumahFavorite
        listRumahFavorite = database.getDataFavorite();

        RumahAdapter adapter = new RumahAdapter(getActivity(), listRumahFavorite);
        rvFavorit.setAdapter(adapter);

        return view;
    }

}
