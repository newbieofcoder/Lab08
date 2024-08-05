package hoanglv.fpoly.thithu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hoanglv.fpoly.thithu.database.DataHelper;
import hoanglv.fpoly.thithu.models.Note;

public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(Context context) {
        DataHelper dataHelper = new DataHelper(context);
        this.db = dataHelper.getReadableDatabase();
        this.db = dataHelper.getWritableDatabase();
    }

    public List<Note> getData() {
        List<Note> list = new ArrayList<>();
        String sql = "SELECT * FROM NOTE";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setNoidung(cursor.getString(1));
                note.setNgay(cursor.getString(2));
                list.add(note);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d("NoteDAO", "getData: " + list.size());
        return list;
    }

    public long insert(Note note) {
        ContentValues values = new ContentValues();
        values.put("noidung", note.getNoidung());
        values.put("ngay", note.getNgay());
        return db.insert("NOTE", null, values);
    }

    public int delete(Note note) {
        return db.delete("NOTE", "id = ?", new String[]{String.valueOf(note.getId())});
    }
}
