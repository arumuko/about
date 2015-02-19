
//SQLiteを実装するためのクラス

package apk.about;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//SQLiteOpenHelperを継承して，独自のHelperクラスを定義します．
public class Other_DBHelper extends SQLiteOpenHelper {
	
	// データベース名の入力
	private static final String DATABASE_NAME = "ABOUT";
	// テーブル名の入力
	private static final String TABLE_NAME = "OTHER";
	// データベースのバージョンを入力
	private static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
			+ "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "USER_ID INTEGER(5) NOT NULL, "
			+ "OTHER_NAME VARCHAR(10) NOT NULL);";
	
public Other_DBHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// テーブル作成用のSQL文を定義して，テーブルを作成する　
		db.execSQL(CREATE_TABLE);
		db.execSQL("INSERT INTO OTHER (USER_ID, OTHER_NAME) " +
				"VALUES ('00000', 'ABOUT');");
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	//テーブルデータ更新のメソッド
		public static void update(SQLiteDatabase db, int userid, String name) {
		      ContentValues cv=new ContentValues();
		       cv.put("USER_ID", userid);
		       cv.put("OTHER_NAME", name);
		       db.update(TABLE_NAME, cv, "id = 1", null);
		}
	
} 