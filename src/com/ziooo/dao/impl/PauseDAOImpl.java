package com.ziooo.dao.impl;

import static com.ziooo.sqlite.PayPauseSQLiteHelper.DATE;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.GAIN;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.ID_PAUSE;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.ID_PERSON;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.ID_TYPE;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.TABLE_PAUSE;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.TIME;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ziooo.dao.PauseDAO;
import com.ziooo.model.Pause;
import com.ziooo.model.Person;
import com.ziooo.model.Type;
import com.ziooo.sqlite.PayPauseSQLiteHelper;

public class PauseDAOImpl implements PauseDAO {

	private SQLiteDatabase database;
	private final PayPauseSQLiteHelper dbHelper;
	private final PersonDAOImpl personDAO;
	private final TypeDAOImpl typeDAO;
	private final String[] allColumns = { ID_PAUSE, DATE, TIME, GAIN, ID_PERSON, ID_PAUSE };

	public PauseDAOImpl(Context context) {
		dbHelper = new PayPauseSQLiteHelper(context);
		personDAO = new PersonDAOImpl(context);
		typeDAO = new TypeDAOImpl(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private Pause cursorToPause(Cursor cursor) {
		Pause pause = new Pause();
		pause.setIdPause(cursor.getLong(0));
		pause.setDate(cursor.getString(1));
		pause.setTime(cursor.getString(2));
		pause.setGain(cursor.getDouble(3));
		pause.setPerson(personDAO.getPersonById(cursor.getLong(4)));
		pause.setType(typeDAO.getTypeById(cursor.getLong(5)));
		return pause;
	}

	@Override
	public Pause createPause(String date, String time, double gain, Person person, Type type) {
		ContentValues values = new ContentValues();
		values.put(DATE, date);
		values.put(TIME, time);
		values.put(GAIN, gain);
		values.put(ID_PERSON, person.getidPerson());
		values.put(ID_TYPE, type.getIdType());
		long insertId = database.insert(TABLE_PAUSE, null, values);
		Cursor cursor = database.query(TABLE_PAUSE, allColumns, ID_PAUSE + " = " + insertId, null, null, null, null);
		Pause pause = cursorToPause(cursor);
		cursor.close();
		return pause;
	}

	@Override
	public List<Pause> getAllPauses() {
		List<Pause> pauses = new ArrayList<Pause>();
		Cursor cursor = database.query(TABLE_PAUSE, allColumns, null, null, null, null, DATE);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Pause pause = cursorToPause(cursor);
			pauses.add(pause);
			cursor.moveToNext();
		}
		cursor.close();
		return pauses;
	}

	@Override
	public Pause getPauseById(long idPause) {
		Cursor cursor = database.query(TABLE_PAUSE, allColumns, ID_PAUSE + " = " + idPause, null, null, null, null);
		cursor.moveToFirst();
		Pause pause = cursorToPause(cursor);
		cursor.close();
		return pause;
	}

	@Override
	public int updatePause(Pause pause) {
		ContentValues values = new ContentValues();
		values.put(DATE, pause.getDate());
		values.put(TIME, pause.getTime());
		values.put(GAIN, pause.getGain());
		values.put(ID_PERSON, pause.getPerson().getidPerson());
		values.put(ID_TYPE, pause.getType().getIdType());
		return database.update(TABLE_PAUSE, values, ID_PAUSE + " = ?", new String[] { String.valueOf(pause.getIdPause()) });
	}

	@Override
	public int deletePause(Pause pause) {
		return database.delete(TABLE_PAUSE, ID_PAUSE + " = " + pause.getIdPause(), null);
	}

}
