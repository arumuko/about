//ABOUT_v11

package apk.about;

import java.io.IOException;
import java.util.HashMap;

import apk.about.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class About extends Activity {
	String TAG = "ABOUT.java";
	ProgressDialog mProgressDialog;
	String url = "http://birdboy.sakura.ne.jp/about/post/calculation.php";
	int user_id;
	int height;
	int weight;
	String about_id;
	String name;
	String height_befor;
	String weight_befor;
	String birthday_befor;
	String bloodtype_befor;
	String job_befor;
	String rent_befor;
	String revenue_befor;
	String birthplace_befor;
	String height_after;
	String weight_after;
	String birthday_after;
	String bloodtype_after;
	String birthplace_after;
	String job_after;
	String rent_after;
	String revenue_after;
	
	TextView Name;
	TextView Height;
	TextView Weight;
	TextView Rent;
	TextView Bloodtype;
	TextView Revenue;
	TextView Birthplace;
	TextView Job;
	TextView Birthday;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);
        
//        Name = (TextView)this.findViewById(R.id.textViewnamae);
//		Height = (TextView)this.findViewById(R.id.textViewheight);
//		Weight = (TextView)this.findViewById(R.id.textViewweight);
//		Rent = (TextView)this.findViewById(R.id.textViewRent);
//		Bloodtype = (TextView)this.findViewById(R.id.textViewbloodtype);
//		Revenue = (TextView)this.findViewById(R.id.textViewRevenue);
//		Birthplace = (TextView)this.findViewById(R.id.textViewBirthplace);
//		Job = (TextView)this.findViewById(R.id.textViewJob);
//		Birthday = (TextView)this.findViewById(R.id.textViewDate);
        
      //データベースのセット(sqliteの方)
        ABOUT_DBHelper dbHelper = new ABOUT_DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //sqliteからデータを取ってくる
        Cursor c = db.query("USER", new String[] { "USER_ID", "NAME", "HEIGHT", "WEIGHT", "RENT", "BLOODTYPE", 
        		"REVENUE", "BIRTHPLACE", "JOB", "BIRTHDAY"}, null, null, null, null, null);
        		        boolean isEof = c.moveToFirst();
        		        while (isEof) {
        		        	// 取ってきたデータをそれぞれの変数に入れる(ごまかし前)
        		        	user_id = c.getInt(0);
        		            name = c.getString(1).toString();
        		            height = c.getInt(2);
        		            weight = c.getInt(3);
        		            rent_befor = c.getString(4).toString();
        		            bloodtype_befor = c.getString(5).toString();
        		            revenue_befor = c.getString(6).toString();
        		            birthplace_befor = c.getString(7).toString();
        		            job_befor = c.getString(8).toString();
        		            birthday_befor = c.getString(9).toString();
        		            isEof = c.moveToNext();
        		        }
        		        //sqlite接続終了
        		        c.close();
        		        db.close();

        
        Intent intent = this.getIntent();
        //ごまかし前の項目
        about_id = String.valueOf(user_id);
        height_befor = String.valueOf(height);
        weight_befor = String.valueOf(weight);
        
        
        //ごまかし後の項目
        height_after = intent.getStringExtra("height");
        weight_after = intent.getStringExtra("weight");
        rent_after = intent.getStringExtra("rent");
        bloodtype_after = intent.getStringExtra("bloodtype");
        revenue_after = intent.getStringExtra("revenue");
        birthplace_after = intent.getStringExtra("birthplace");
        job_after = intent.getStringExtra("job");
        birthday_after = intent.getStringExtra("birthday");
        
        new userUp().execute();
        
        Log.d(TAG, about_id);
        Log.d(TAG, height_befor);
        Log.d(TAG, weight_befor);
        Log.d(TAG, rent_befor);
        Log.d(TAG, bloodtype_befor);
        Log.d(TAG, revenue_befor);
        Log.d(TAG, birthplace_befor);
        Log.d(TAG, job_befor);
        Log.d(TAG, birthday_befor);
        
        Log.d(TAG, name);
        Log.d(TAG, height_after);
        Log.d(TAG, weight_after);
        Log.d(TAG, rent_after);
        Log.d(TAG, bloodtype_after);
        Log.d(TAG, revenue_after);
        Log.d(TAG, birthplace_after);
        Log.d(TAG, job_after);
        Log.d(TAG, birthday_after);
        
        // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    
    
    Button button2=(Button)findViewById(R.id.gomakasibutton);
    button2.setOnClickListener(new View.OnClickListener(){
    	
    	@Override
    	public void onClick(View v) {
   		Intent intent1=new Intent();
   		intent1.setClassName("apk.about","apk.about.MainActivity");
 		startActivity(intent1);	
    	}
    });
   }
    private class userUp extends AsyncTask<Void, Void, Void> {
    	String[] strs;
    	
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(About.this);
			mProgressDialog.setTitle(" データを読み込んでいます");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();			
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Connect to the web site
				Connection con = Jsoup.connect(url);
				HashMap<String, String> param = new HashMap<String, String>();
				param.put("id", about_id);
				param.put("height", height_befor);
				param.put("weight",weight_befor);
				param.put("birthday", birthday_after);
				param.put("bloodtype",bloodtype_after);
				param.put("job", job_after);
				param.put("rent", rent_befor);
				param.put("income", revenue_befor);
				param.put("been", birthplace_after);
				param.put("gomakasiHeight", height_after);
				param.put("gomakasiWeight", weight_after);
				param.put("gomakasiRent", rent_after);
				param.put("gomakasiIncome",revenue_after);
				Connection.Response res = con.data(param).method(Method.POST).execute();
				Document document = res.parse();
				// Get the html document title
				String tableMax = document.getElementById("table").toString();
				String[] table = tableMax.split(":");
				int num = Integer.parseInt(table[1]);
					
					String str = document.getElementById("1").toString();
					strs = str.split(":");
					
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(Void result) {
			// Set title into TextView
			
	        Name = (TextView)findViewById(R.id.textViewnamae);
			Height = (TextView)findViewById(R.id.textViewheight);
			Weight = (TextView)findViewById(R.id.textViewweight);
			Rent = (TextView)findViewById(R.id.textViewRent);
			Bloodtype = (TextView)findViewById(R.id.textViewbloodtype);
			Revenue = (TextView)findViewById(R.id.textViewRevenue);
			Birthplace = (TextView)findViewById(R.id.textViewBirthplace);
			Job = (TextView)findViewById(R.id.textViewJob);
			Birthday = (TextView)findViewById(R.id.textViewDate);
				
			
			//Name.setText(name);
			Height.setText(strs[1]);
			Weight.setText(strs[2]);
			Rent.setText(strs[3]);
			Bloodtype.setText(strs[4]);
			Revenue.setText(strs[5]);
			Birthplace.setText(strs[6]);
			Job.setText(strs[7]);
			Birthday.setText(strs[8]);
			
			
			mProgressDialog.dismiss();
		}
    }
}