package com.ziooo.dao.impl;

import static com.ziooo.sqlite.PayPauseSQLiteHelper.ID_TYPE;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.NAME;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.TABLE_TYPE;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ziooo.dao.TypeDAO;
import com.ziooo.model.Type;
import com.ziooo.sqlite.PayPauseSQLiteHelper;

public class TypeDAOImpl implements TypeDAO {

	private SQLiteDatabase database;
	private final PayPauseSQLiteHelper dbHelper;
	private final String[] allColumns = { ID_TYPE, NAME };

	public TypeDAOImpl(Context context) {
		dbHelper = new PayPauseSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private Type cursorToType(Cursor cursor) {
		Type type = new Type();
		type.setIdType(cursor.getLong(0));
		type.setName(cursor.getString(1));
		return type;
	}

	@Override
	public Type createType(String name) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		long insertId = database.insert(TABLE_TYPE, null, values);
		Cursor cursor = database.query(TABLE_TYPE, allColumns, ID_TYPE + " = " + insertId, null, null, null, null);
		Type type = cursorToType(cursor);
		cursor.close();
		return type;
	}

	@Override
	public List<Type> getAllTypes() {
		List<Type> types = new ArrayList<Type>();
		Cursor cursor = database.query(TABLE_TYPE, allColumns, null, null, null, null, NAME);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Type type = cursorToType(cursor);
			types.add(type);
			cursor.moveToNext();
		}
		cursor.close();
		return types;
	}

	@Override
	public Type getTypeById(long idType) {
		Cursor cursor = database.query(TABLE_TYPE, allColumns, ID_TYPE + " = " + idType, null, null, null, null);
		cursor.moveToFirst();
		Type type = cursorToType(cursor);
		cursor.close();
		return type;
	}

	@Override
	public int updateType(Type type) {
		ContentValues values = new ContentValues();
		values.put(NAME, type.getName());
		return database.update(TABLE_TYPE, values, ID_TYPE + " = ?", new String[] { String.valueOf(type.getIdType()) });
	}

	@Override
	public int deleteType(Type type) {
		return database.delete(TABLE_TYPE, ID_TYPE + " = " + type.getIdType(), null);
	}

}
