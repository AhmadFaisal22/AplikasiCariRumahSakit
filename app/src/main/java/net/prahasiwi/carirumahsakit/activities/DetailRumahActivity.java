package net.prahasiwi.carirumahsakit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;

import net.prahasiwi.carirumahsakit.R;
import net.prahasiwi.carirumahsakit.db.DatabaseHelper;
import net.prahasiwi.carirumahsakit.helper.Constanst;

public class DetailRumahActivity extends AppCompatActivity {

    //deklarasi
    ImageView ivGambarRumah;
    TextView tvNamaRumah,tvAlamatRumah, tvEmailRumah, tvTelpRumah, tvWebRumah;
    String namaRumah, gambarRumah, alamatRumah, emailRumah, telpRumah, webRumah, latRumah, longRumah;
    Button btnmaps;
    String idRumah;
    private ShineButton btnFavorit;

    private static final String TAG = "DetailRumahSakitActicity";
    private static final String TAG_PREF = "setting";
    private static final String TAG_FAV = "favorite";
    /*Boolean isFav=true;
    FloatingActionButton fab;*/
    DatabaseHelper database = new DatabaseHelper(this);
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rumah);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //menghubungkan java dengan xml
        tvNamaRumah = (TextView) findViewById(R.id.txt_detail_nama);
        ivGambarRumah = (ImageView) findViewById(R.id.iv_detail_gambar);
        tvAlamatRumah = (TextView) findViewById(R.id.txt_detail_lokasi);
        tvEmailRumah = (TextView) findViewById(R.id.txt_detail_email);
        tvTelpRumah = (TextView) findViewById(R.id.txt_detail_telp);
        tvWebRumah = (TextView) findViewById(R.id.txt_detail_web);
        btnmaps = (Button) findViewById(R.id.btnMap);
        btnFavorit = (ShineButton) findViewById(R.id.po_image2);

        // menampung data yang dikirim
        Bundle b = getIntent().getExtras();
        idRumah = b.getString(Constanst.ID_RUMAH);
        namaRumah = b.getString(Constanst.NAMA_RUMAH);
        gambarRumah = b.getString(Constanst.GAMBAR_RUMAH);
        alamatRumah = b.getString(Constanst.ALAMAT_RUMAH);
        emailRumah = b.getString(Constanst.EMAIL_RUMAH);
        telpRumah = b.getString(Constanst.TELP_RUMAH);
        webRumah = b.getString(Constanst.WEB_RUMAH);
        latRumah = b.getString(Constanst.LATITUDE_RUMAH);
        longRumah = b.getString(Constanst.LONGITUDE_RUMAH);

        //memasukan data yang didapat ke layout
        getSupportActionBar().setTitle(namaRumah);
        Glide.with(this).load("https://drive.google.com/thumbnail?id=" + gambarRumah).into(ivGambarRumah);
        tvNamaRumah.setText(namaRumah);
        tvAlamatRumah.setText(alamatRumah);
        tvEmailRumah.setText(emailRumah);
        tvTelpRumah.setText(telpRumah);
        tvWebRumah.setText(webRumah);

        //intent ke maps
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetaBtnActivity.class);
                Bundle b = new Bundle();
                b.putString("latitude", latRumah);
                b.putString("longitude", longRumah);
                b.putString("namaRs", namaRumah);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        pref = getSharedPreferences(TAG_PREF, MODE_PRIVATE);
        Boolean isFav = pref.getBoolean(TAG_FAV + idRumah, false);

        if (isFav) {
            btnFavorit.setChecked(true);
        }else{
            btnFavorit.setChecked(false);
        }

        btnFavorit.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked) {
                    long id = database.insertData(Integer.valueOf(idRumah),namaRumah,
                            gambarRumah,
                            alamatRumah,
                            telpRumah,
                            emailRumah,
                            webRumah, latRumah, longRumah);
                    if (id <= 0) {
                        Snackbar.make(view, "database gagal dimasuki data",
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "telah ditambahkan ke favorit",
                                Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        SharedPreferences sp =
                                getSharedPreferences(TAG_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(TAG_FAV + idRumah, true);
                        editor.commit();
                    }
                } else {
                    database.delete(namaRumah);

                    Snackbar.make(view, "berhasil dihapus dari favorit",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    SharedPreferences sp = getSharedPreferences(TAG_PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    //membuat status fav menjadi false
                    editor.putBoolean(TAG_FAV + idRumah, false);
                    editor.commit();
                }
            }
        });
    }
}
