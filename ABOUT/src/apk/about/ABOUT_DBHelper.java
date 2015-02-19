
//SQLite データベースの作成、初期値の設定、更新の処理

package apk.about;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//SQLiteOpenHelperを継承して，独自のHelperクラスを定義します．
public class ABOUT_DBHelper extends SQLiteOpenHelper {
	// データベース名の入力
	private static final String DATABASE_NAME = "ABOUT";
	// テーブル名の入力
	private static final String TABLE_NAME = "USER";
	private static final String TABLE_NAME2 = "OTHER";
	// データベースのバージョンを入力
	private static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
			+ "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +"USER_ID INTEGER(5) NOT NULL," + "NAME VARCHAR(10) NOT NULL, "
			+ "HEIGHT INTEGER(3) NOT NULL, " + "WEIGHT INTEGER(3) NOT NULL, " + "RENT VARCHAR(12) NOT NULL, " 
			+ "BLOODTYPE VARCHAR(3) NOT NULL ," + "REVENUE VARCHAR(8) NOT NULL, "+ "BIRTHPLACE VARCHAR(10) NOT NULL, " 
			+ "JOB VARCHAR(10) NOT NULL, " + "BIRTHDAY VARCHAR(12) NOT NULL);";
	
	private static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 + "("
			+ "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "USER_ID INTEGER(5) NOT NULL, "
			+ "OTHER_NAME VARCHAR(10) NOT NULL);";
public ABOUT_DBHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// テーブル作成用のSQL文を定義して，テーブルを作成する　
		db.execSQL(CREATE_TABLE);
		db.execSQL(CREATE_TABLE2);
		//初期値の設定
		db.execSQL("INSERT INTO USER (USER_ID, NAME, HEIGHT, WEIGHT, RENT, BLOODTYPE, REVENUE, BIRTHPLACE, JOB, BIRTHDAY) " +
					"VALUES ('00000', 'ABOUT', 170, 60, 'ABOUT', 'ABOUT', 'ABOUT', 'ABOUT', 'ABOUT', 'ABOUT');");
		db.execSQL("INSERT INTO OTHER (USER_ID, OTHER_NAME) " +
				"VALUES ('00000', 'ABOUT');");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	//USERテーブルデータ更新のメソッド
	public static void update_Profile(SQLiteDatabase db, int userid, String name, int height, int weight, 
							String rent, String bloodtype, String revenue, 
							String birthplace, String job, String birthday) {
	      ContentValues cv=new ContentValues();
	       cv.put("USER_ID", userid);
	       cv.put("NAME", name);
	       cv.put("HEIGHT", height);
	       cv.put("WEIGHT", weight);
	       cv.put("RENT", rent);
	       cv.put("BLOODTYPE", bloodtype);
	       cv.put("REVENUE", revenue);
	       cv.put("BIRTHPLACE", birthplace);
	       cv.put("JOB", job);
	       cv.put("BIRTHDAY", birthday);
	       db.update(TABLE_NAME, cv, "id = 1", null);
	}
	
	//OTHERテーブルデータ更新のメソッド
		public static void update_Other(SQLiteDatabase db, int userid, String name) {
		      ContentValues cv=new ContentValues();
		       cv.put("USER_ID", userid);
		       cv.put("OTHER_NAME", name);
		       db.update(TABLE_NAME2, cv, "id = 1", null);
		}
		public static void insert_Other(SQLiteDatabase db, int userid, String name){
			ContentValues cv = new ContentValues();
            cv.put("USER_ID", userid);
            cv.put("OTHER_NAME", name);
            db.insert(TABLE_NAME2, null, cv);
		}
} 