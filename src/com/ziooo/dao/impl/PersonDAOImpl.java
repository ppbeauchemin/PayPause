package com.ziooo.dao.impl;

import static com.ziooo.sqlite.PayPauseSQLiteHelper.ID_PERSON;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.NAME;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.SALARY_PER_SEC;
import static com.ziooo.sqlite.PayPauseSQLiteHelper.TABLE_PERSON;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ziooo.dao.PersonDAO;
import com.ziooo.model.Person;
import com.ziooo.sqlite.PayPauseSQLiteHelper;

public class PersonDAOImpl implements PersonDAO {

	private SQLiteDatabase database;
	private final PayPauseSQLiteHelper dbHelper;
	private final String[] allColumns = { ID_PERSON, NAME, SALARY_PER_SEC };

	public PersonDAOImpl(Context context) {
		dbHelper = new PayPauseSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private Person cursorToPerson(Cursor cursor) {
		Person person = new Person();
		person.setidPerson(cursor.getLong(0));
		person.setName(cursor.getString(1));
		person.setSalaryPerSec(cursor.getDouble(2));
		return person;
	}

	@Override
	public Person createPerson(String name, double salaryPerSec) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(SALARY_PER_SEC, salaryPerSec);
		long insertId = database.insert(TABLE_PERSON, null, values);
		Cursor cursor = database.query(TABLE_PERSON, allColumns, ID_PERSON + " = " + insertId, null, null, null, null);
		Person person = cursorToPerson(cursor);
		cursor.close();
		return person;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = database.query(TABLE_PERSON, allColumns, null, null, null, null, NAME);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person person = cursorToPerson(cursor);
			persons.add(person);
			cursor.moveToNext();
		}
		cursor.close();
		return persons;
	}

	@Override
	public Person getPersonById(long idPerson) {
		Cursor cursor = database.query(TABLE_PERSON, allColumns, ID_PERSON + " = " + idPerson, null, null, null, null);
		cursor.moveToFirst();
		Person person = cursorToPerson(cursor);
		cursor.close();
		return person;
	}

	@Override
	public int updatePerson(Person person) {
		ContentValues values = new ContentValues();
		values.put(NAME, person.getName());
		values.put(SALARY_PER_SEC, person.getSalaryPerSec());
		return database.update(TABLE_PERSON, values, ID_PERSON + " = ?", new String[] { String.valueOf(person.getidPerson()) });
	}

	@Override
	public int deletePerson(Person person) {
		return database.delete(TABLE_PERSON, ID_PERSON + " = " + person.getidPerson(), null);
	}

}
