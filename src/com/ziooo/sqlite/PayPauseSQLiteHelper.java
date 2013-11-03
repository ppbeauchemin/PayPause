package com.ziooo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PayPauseSQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "paypause.db";
	private static final int DATABASE_VERSION = 1;

	/* Commons */
	private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
	private static final String CREATE_TABLE = "CREATE TABLE ";
	public static final String NAME = "name";

	/* Person */
	public static final String TABLE_PERSON = "person";
	public static final String ID_PERSON = "id_person";
	public static final String SALARY_PER_SEC = "salaryPerSec";

	/* Type */
	public static final String TABLE_TYPE = "type";
	public static final String ID_TYPE = "id_type";

	/* Pause */
	public static final String TABLE_PAUSE = "pause";
	public static final String ID_PAUSE = "id_pause";
	public static final String DATE = "date";
	public static final String TIME = "time";
	public static final String GAIN = "gain";

	private static final String CREATE_PERSON = CREATE_TABLE + TABLE_PERSON + "(" + ID_PERSON + " integer primary key autoincrement, "
			+ NAME + " text not null, " + SALARY_PER_SEC + " real);";

	private static final String CREATE_TYPE = CREATE_TABLE + TABLE_TYPE + "(" + ID_TYPE + "integer primary key autoincrement, " + NAME
			+ "text not null);";

	private static final String CREATE_PAUSE = CREATE_TABLE + TABLE_PAUSE + "(" + ID_PAUSE + " integer primary key autoincrement, " + DATE
			+ " text, " + TIME + " text, " + GAIN + " real, " + ID_PERSON + " integer, " + ID_TYPE + " integer);";

	public PayPauseSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_PERSON);
		database.execSQL(CREATE_TYPE);
		database.execSQL(CREATE_PAUSE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_PAUSE);
		db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_TYPE);
		db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_PERSON);
		onCreate(db);
	}

}
