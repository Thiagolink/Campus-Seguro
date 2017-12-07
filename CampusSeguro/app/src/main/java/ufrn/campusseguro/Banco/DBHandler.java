package ufrn.campusseguro.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ufrn.campusseguro.Model.RegistrosAssaltos;

/**
 * Created by Wesley Reuel on 05/12/2017.
 */

public class DBHandler  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "campusseguro";
    // Contacts table name
    private static final String TABLE_REGISTRY = "RegistrosAssaltos";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IDLOCAL = "idLocal";
    private static final String KEY_IDUSUARIO = "idUsuario";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String KEY_DATA = "data";
    private static final String KEY_DESCRICAO = "descricao";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLES = "CREATE TABLE " + TABLE_REGISTRY + "(" + KEY_IDLOCAL + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + KEY_ID + " INTEGER, " +  KEY_IDUSUARIO + " INTEGER, " + KEY_LAT + " REAL, " + KEY_LNG + " REAL, " + KEY_DATA + " TEXT, " + KEY_DESCRICAO + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRY);
        // Creating tables again
        onCreate(sqLiteDatabase);
    }

    public void addRegistro(RegistrosAssaltos registrosAssaltos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, registrosAssaltos.getId()); // Shop Name
        values.put(KEY_IDUSUARIO, registrosAssaltos.getIdUsuario()); // Shop Phone Number
        values.put(KEY_LAT, registrosAssaltos.getLat());
        values.put(KEY_LNG, registrosAssaltos.getLng());
        values.put(KEY_DATA, registrosAssaltos.getData());
        values.put(KEY_DESCRICAO, registrosAssaltos.getDescricao());
        // Inserting Row
        db.insert(TABLE_REGISTRY, null, values);
        db.close(); // Closing database connection
    }

    public RegistrosAssaltos getRegistroAssaltos(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTRY, new String[] { KEY_IDLOCAL,
                        KEY_ID, KEY_IDUSUARIO, KEY_LAT, KEY_LNG, KEY_DATA, KEY_DESCRICAO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        RegistrosAssaltos registrosAssaltos = new RegistrosAssaltos(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Double.parseDouble(cursor.getString(3)), Double.parseDouble(cursor.getString(4)), cursor.getString(5), cursor.getString(6));
        // return shop
        return registrosAssaltos;
    }

    public List<RegistrosAssaltos> getAllRegistros() {
        List<RegistrosAssaltos> registryList = new ArrayList<RegistrosAssaltos>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_REGISTRY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RegistrosAssaltos registrosAssaltos = new RegistrosAssaltos();
                registrosAssaltos.setIdLocal(Integer.parseInt(cursor.getString(0)));
                registrosAssaltos.setId(Integer.parseInt(cursor.getString(1)));
                registrosAssaltos.setIdUsuario(Integer.parseInt(cursor.getString(2)));
                registrosAssaltos.setLat(Double.parseDouble(cursor.getString(3)));
                registrosAssaltos.setLng(Double.parseDouble(cursor.getString(4)));
                registrosAssaltos.setData(cursor.getString(5));
                registrosAssaltos.setDescricao(cursor.getString(6));
                // Adding contact to list
                registryList.add(registrosAssaltos);
            } while (cursor.moveToNext());
        }
        // return contact list
        return registryList;
    }

    public int getRegistroCount() {
        String countQuery = "SELECT * FROM " + TABLE_REGISTRY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateRegistro(RegistrosAssaltos registrosAssaltos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, registrosAssaltos.getId()); // Shop Name
        values.put(KEY_IDUSUARIO, registrosAssaltos.getIdUsuario()); // Shop Phone Number
        values.put(KEY_LAT, registrosAssaltos.getLat());
        values.put(KEY_LNG, registrosAssaltos.getLng());
        values.put(KEY_DATA, registrosAssaltos.getData());
        values.put(KEY_DESCRICAO, registrosAssaltos.getDescricao());
        // updating row
        return db.update(TABLE_REGISTRY, values, KEY_ID + " = ?",
                new String[]{String.valueOf(registrosAssaltos.getIdLocal())});
    }

}