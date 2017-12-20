package net.prahasiwi.carirumahsakit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.prahasiwi.carirumahsakit.model.Rumah;

import java.util.ArrayList;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //mendefinisikan nama database, tabel, kolom dan versi database
    private final static String DATABASE_NAME = "dbrumah";
    private final static String DATABASE_TABLE = "table_rumah";
    private final static String RUMAH_ID = "id_rs";
    private final static String NAMA_RUMAH = "nama_rs";
    private final static String GAMBAR_RUMAH = "gambar_rs";
    private final static String ALAMAT_RUMAH = "lokasi_rs";
    private final static String EMAIL_RUMAH = "email_rs";
    private final static String TELP_RUMAH = "telepon_rs";
    private final static String WEB_RUMAH = "web_rs";
    private final static String LATITUDE_RUMAH = "latitude_rs";
    private final static String LONGITUDE_RUMAH = "longitude_rs";
    private final static int DATABASE_VERSION = 4;
    private final static String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE
            + " (" + RUMAH_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + NAMA_RUMAH + " VARCHAR(200), "
            + GAMBAR_RUMAH + " VARCHAR(200), "
            + ALAMAT_RUMAH + " TEXT, "
            + TELP_RUMAH + " VARCHAR(20), "
            + EMAIL_RUMAH + " VARCHAR(20), "
            + WEB_RUMAH + " VARCHAR(20), "
            + LATITUDE_RUMAH + " VARCHAR(20), "
            + LONGITUDE_RUMAH + " VARCHAR(20));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long insertData(Integer idRumah,
                            String namaRumah,
                           String gambarRumah,
                           String alamatRumah,
                           String teleponRumah,
                           String emailRumah,
                           String webRumah,
                           String latRumah,
                           String longRumah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RUMAH_ID, idRumah);
        contentValues.put(NAMA_RUMAH, namaRumah);
        contentValues.put(GAMBAR_RUMAH, gambarRumah);
        contentValues.put(ALAMAT_RUMAH, alamatRumah);
        contentValues.put(TELP_RUMAH, teleponRumah);
        contentValues.put(EMAIL_RUMAH, emailRumah);
        contentValues.put(WEB_RUMAH, webRumah);
        contentValues.put(LATITUDE_RUMAH, latRumah);
        contentValues.put(LONGITUDE_RUMAH, longRumah);
        long id = db.insert(DATABASE_TABLE, null, contentValues);
        db.close();
        return id;
    }

    public ArrayList<Rumah> getDataFavorite() {
        ArrayList<Rumah>listRumahFavorite = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columName = {RUMAH_ID,NAMA_RUMAH,
                GAMBAR_RUMAH,
                ALAMAT_RUMAH,
                TELP_RUMAH,
                EMAIL_RUMAH,
                WEB_RUMAH,
                LATITUDE_RUMAH,
                LONGITUDE_RUMAH};
        Cursor cursor = db.query(DATABASE_TABLE,
                columName,
                null,
                null,
                null,
                null,
                null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int idRumah = cursor.getInt(cursor.getColumnIndex(RUMAH_ID));
                String namaRumah = cursor
                        .getString(cursor.getColumnIndex(NAMA_RUMAH));
                String gambarRumah = cursor
                        .getString(cursor.getColumnIndex(GAMBAR_RUMAH));
                String alamatRumah = cursor
                        .getString(cursor.getColumnIndex(ALAMAT_RUMAH));
                String teleponRumah = cursor
                        .getString(cursor.getColumnIndex(TELP_RUMAH));
                String emailRumah = cursor
                        .getString(cursor.getColumnIndex(EMAIL_RUMAH));
                String webRumah = cursor
                        .getString(cursor.getColumnIndex(WEB_RUMAH));
                String latRumah = cursor
                        .getString(cursor.getColumnIndex(LATITUDE_RUMAH));
                String longRumah = cursor
                        .getString(cursor.getColumnIndex(LONGITUDE_RUMAH));


                Rumah rumahFavorite = new Rumah(String.valueOf(idRumah),
                        namaRumah,
                        gambarRumah,
                        alamatRumah,
                        teleponRumah,
                        emailRumah,
                        webRumah,latRumah,longRumah);

                listRumahFavorite.add(rumahFavorite);
            }
        }
        db.close();
        return listRumahFavorite;
    }

    public int delete(String namaRumah){
        SQLiteDatabase db = this.getWritableDatabase();
        String namaKolomnya = NAMA_RUMAH + " = ?";
        String[] nilaiFieldnya = {namaRumah};

        int count = db.delete(DATABASE_TABLE,namaKolomnya,nilaiFieldnya);
        return count;
    }
}
