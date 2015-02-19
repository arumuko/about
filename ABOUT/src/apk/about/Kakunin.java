//ABOUT_v11

package apk.about;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import apk.about.Other_DBHelper;
import apk.about.R;

public class Kakunin extends Activity {
    /** Called when the activity is first created. */
	
	int user_id;
	String user_name; //名前
	String user_height; //身長
	String user_weight; //体重
	int height;
	int weight;
	String user_rent; //家賃
	String user_bloodtype; //血液型
	String user_revenue; //収入
	String user_birthplace; //出身地
	String user_job; //職業
	String user_birthday; //誕生日
	//ごまかし前
	TextView namae;
	TextView sintyou;
	TextView taiju;
	TextView yatin;
	TextView ketueki;
	TextView shuunyu;
	TextView shussin;
	TextView job;
	TextView date;
	
	//ごまかし後
	TextView sintyou2;
	TextView taiju2;
	TextView yatin2;
	TextView ketueki2;
	TextView shuunyu2;
	TextView shussin2;
	TextView job2;
	TextView date2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kakunin);
        
      //データベースのセット(sqliteの方)
        ABOUT_DBHelper dbHelper = new ABOUT_DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //sqliteからデータを取ってくる
        Cursor c = db.query("USER", new String[] { "USER_ID", "NAME", "HEIGHT", "WEIGHT", "RENT", "BLOODTYPE", 
        		"REVENUE", "BIRTHPLACE", "JOB", "BIRTHDAY"}, null, null, null, null, null);
        		        boolean isEof = c.moveToFirst();
        		        while (isEof) {
        		        	// 取ってきたデータをそれぞれの変数に入れる
        		        	user_id = c.getInt(0);
        		            user_name = c.getString(1).toString();
        		            height = c.getInt(2);
        		            weight = c.getInt(3);
        		            user_rent = c.getString(4).toString();
        		            user_bloodtype = c.getString(5).toString();
        		            user_revenue = c.getString(6).toString();
        		            user_birthplace = c.getString(7).toString();
        		            user_job = c.getString(8).toString();
        		            user_birthday = c.getString(9).toString();
        		            
        		            isEof = c.moveToNext();
        		        }
        		        //sqlite接続終了
        		        c.close();
        		        db.close();
        
        // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
       
        												//インテント

        Intent intent = this.getIntent();
        String Height = intent.getStringExtra("height"); //身長
        String Weight = intent.getStringExtra("weight"); //体重
        String Rent = intent.getStringExtra("rent"); //家賃
        String Bloodtype = intent.getStringExtra("bloodtype"); //血液型
        String Revenue = intent.getStringExtra("revenue"); //収入
        String Birthplace = intent.getStringExtra("birthplace"); //出身地
        String Job = intent.getStringExtra("job"); //職業
        String Birthday = intent.getStringExtra("birthday"); //誕生日
        
        String user_name = intent.getStringExtra("sendText21");
        String user_height = intent.getStringExtra("sendText22");
        String user_weight = intent.getStringExtra("sendText23");
        String user_rent = intent.getStringExtra("sendText25");
        String user_bloodtype = intent.getStringExtra("sendText26");
        String user_revenue = intent.getStringExtra("sendText27");
        String user_birthplace = intent.getStringExtra("sendText28");
        String user_job = intent.getStringExtra("sendText29");
        String user_birthday = intent.getStringExtra("sendText30");
        
        namae = (TextView)this.findViewById(R.id.textViewnamae);
        sintyou = (TextView)this.findViewById(R.id.textViewsintyou);
        taiju = (TextView)this.findViewById(R.id.textViewtaiju);
        yatin = (TextView)this.findViewById(R.id.textViewyatin);
        ketueki = (TextView)this.findViewById(R.id.textViewketueki);
        shuunyu= (TextView)this.findViewById(R.id.textViewshunyuu);
        shussin = (TextView)this.findViewById(R.id.textViewshussin);
        job = (TextView)this.findViewById(R.id.textViewjob);
        date = (TextView)this.findViewById(R.id.textViewdate);
        
        sintyou2 = (TextView)this.findViewById(R.id.textViewgomakasi1);
        taiju2 = (TextView)this.findViewById(R.id.textViewgomakasi2);
        yatin2 = (TextView)this.findViewById(R.id.textViewgomakasi3);
        ketueki2 = (TextView)this.findViewById(R.id.textViewgomakasi4);
        shuunyu2 = (TextView)this.findViewById(R.id.textViewgomakasi5);
        shussin2 = (TextView)this.findViewById(R.id.textViewgomakasi6);
        job2 = (TextView)this.findViewById(R.id.textViewgomakasi7);
        date2 = (TextView)this.findViewById(R.id.textViewgomakasi8);
        
        //ごまかし前
        namae.setText(user_name);
        sintyou.setText(user_height);
        taiju.setText(user_weight);
        yatin.setText(user_rent);
        ketueki.setText(user_bloodtype);
        shuunyu.setText(user_revenue);
        shussin.setText(user_birthplace);
        job.setText(user_job);
        date.setText(user_birthday);
        
        //ごまかし後
        sintyou2.setText(Height);
        taiju2.setText(Weight);
        yatin2.setText(Rent);
        ketueki2.setText(Bloodtype);
        shuunyu2.setText(Revenue);
        shussin2.setText(Birthplace);
        job2.setText(Job);
        date2.setText(Birthday);

    Button button2=(Button)findViewById(R.id.gomakasibutton1);
    button2.setOnClickListener(new View.OnClickListener(){
    	
    	@Override
    	public void onClick(View v) {
   		Intent intent = new Intent();
   		intent.setClassName("apk.about","apk.about.About");
		
   		//ごまかし後の項目をintent
   		intent.putExtra("revenue",shuunyu2.getText().toString());
		intent.putExtra("weight",taiju2.getText().toString());
		intent.putExtra("rent",yatin2.getText().toString());
		intent.putExtra("bloodtype",ketueki2.getText().toString());
		intent.putExtra("height",sintyou2.getText().toString());
		intent.putExtra("birthplace",shussin2.getText().toString());
		intent.putExtra("job",job2.getText().toString());
		intent.putExtra("birthday",date2.getText().toString());

		startActivity(intent);
    	}
    });
    
    Button button3=(Button)findViewById(R.id.gomakasibutton0);
    button3.setOnClickListener(new View.OnClickListener(){
    public void onClick( View v_ )
    {
     // アクティビティを終了させる事により、一つ前のアクティビティへ戻る事が出来る。
     finish();
    }
    });
   }
    //戻るボタン処理
    @Override  
	public boolean onOptionsItemSelected(MenuItem item) {     
		switch(item.getItemId()) {  
    		case android.R.id.home:  
    			finish();  
    		return true;  
		}  
    	return super.onOptionsItemSelected(item);
    }
}