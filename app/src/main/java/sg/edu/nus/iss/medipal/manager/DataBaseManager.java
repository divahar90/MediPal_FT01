package sg.edu.nus.iss.medipal.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by : Navi on 06-03-2017.
 * Description : This is the database manager class which will have the db creation and modification methods
 * Modified by :
 * Reason for modification :
 */

public class DataBaseManager extends SQLiteOpenHelper {

    //Database name
    private static final String DATABASE_NAME = "medipalFT01DB";
    private static final int DATABASE_VERSION = 1;


    //variables used to create medicine table query
    public static final String MEDICINE_TABLE = "medicine";
    public static final String MEDICINE_ID = "id";
    public static final String MEDICINE_NAME = "medicine";
    public static final String MEDICINE_DES = "description";
    public static final String MEDICINE_CATID = "catid";
    public static final String MEDICINE_REMID = "reminderid";
    public static final String MEDICINE_REM = "remind";
    public static final String MEDICINE_QUANTITY = "quantity";
    public static final String MEDICINE_DOSAGE = "dosage";
    public static final String MEDICINE_DATEISSUED = "dateissued";
    public static final String MEDICINE_EXPIREFACTOR = "expirefactor";

    //medicine SQLite table creation SQL
    public static final String CREATE_MEDICINE_TABLE = "CREATE TABLE "
            + MEDICINE_TABLE + "(" + MEDICINE_ID + " INTEGER PRIMARY KEY, "
            + MEDICINE_NAME + " TEXT, " + MEDICINE_DES + " TEXT, "
            + MEDICINE_CATID + " INTEGER," + MEDICINE_REMID+ "INTEGER,"
            + MEDICINE_REM + "INTEGER," + MEDICINE_QUANTITY + "INTEGER,"
            + MEDICINE_DOSAGE + "INTEGER," + MEDICINE_DATEISSUED + "TEXT,"
            + MEDICINE_EXPIREFACTOR + "INTEGER"+")";

    //variables used to create category table query
    public static final String CATEGORY_TABLE = "category";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "category";
    public static final String CATEGORY_CODE = "code";
    public static final String CATEGORY_DES = "description";

    //category SQLite table creating SQL
    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE "
            + CATEGORY_TABLE + "(" + CATEGORY_ID + " INTEGER PRIMARY KEY, "
            + CATEGORY_NAME+ " TEXT, " + CATEGORY_CODE + " TEXT, "
            + CATEGORY_DES + " TEXT" + ")";

    //variables used for to form create appointment table query
    public static final String APPOINTMENT_TABLE = "appointment";
    public static final String APPMNT_ID = "id";
    public static final String APPMNT_LOCATION = "location";
    public static final String APPMNT_DATETIME = "appointment";
    public static final String APPMNT_DESCRIPTION = "description";


    //appointment table create query
    public static final String CREATE_APPOINTMENT_TABLE = "CREATE TABLE "
            + APPOINTMENT_TABLE + "(" + APPMNT_ID + " INTEGER PRIMARY KEY, "
            + APPMNT_LOCATION + " TEXT, " + APPMNT_DATETIME + " TEXT, "
            + APPMNT_DESCRIPTION + " TEXT" + ")";

    //To store current DB instance
    private static DataBaseManager instance;

    //returns current DB instance.(if not present, then creates an instance(during app installation))
    public static synchronized DataBaseManager getDBInstance(Context context) {
        if (instance == null)
            instance = new DataBaseManager(context);
        return instance;
    }

    //constructor
    private DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //is this required? have to check
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //Will be called whenever database instance is created (will be done during installation of app)
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_APPOINTMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseManager.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                );

        //the following code makes sure that the tables are recreated each time when db is upgraded.
        //If existing data needs to be preserved then following code needs to be changed accordingly
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENT_TABLE);
        onCreate(db);
    }}