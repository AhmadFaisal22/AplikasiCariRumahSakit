package net.prahasiwi.carirumahsakit.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.prahasiwi.carirumahsakit.R;
import net.prahasiwi.carirumahsakit.adapter.RumahAdapter;
import net.prahasiwi.carirumahsakit.helper.ServiceClient;
import net.prahasiwi.carirumahsakit.helper.ServiceGenerator;
import net.prahasiwi.carirumahsakit.model.ListRumah;
import net.prahasiwi.carirumahsakit.model.Rumah;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView rvHome;
    List<Rumah> listRumah = new ArrayList<>();
    ProgressDialog pd;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome =(RecyclerView)view.findViewById(R.id.rv_home); //menghubungkan logic dengan kulitnya
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(llm);

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);

        //memilih jenis service yang dibutuhkan
        retrofit2.Call<ListRumah> getListRumah = service.getRumah("semarang");

        pd = new ProgressDialog(getActivity());
        pd.setMessage("mengambil data dari server...");
        pd.show();

        //mengirim request dan menerima respon dari server
        getListRumah.enqueue(new Callback<ListRumah>() {
            @Override
            public void onResponse(retrofit2.Call<ListRumah> call, Response<ListRumah> response) {
                pd.dismiss();

                //menyimpan respon server ke listRumah
                listRumah = response.body().getListRumahSemarang();

                //memasukan listRumah ke dalam adapter
                RumahAdapter adapter = new RumahAdapter(getActivity(),listRumah);
                rvHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(retrofit2.Call<ListRumah> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
