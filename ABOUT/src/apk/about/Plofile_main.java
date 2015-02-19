//ABOUT_v11

package apk.about;

import apk.about.R;
//import apk.about.Home.QRCodeControler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import apk.about.ABOUT_DBHelper;
import java.io.IOException;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Plofile_main extends Activity{
	
	String[] url = {"http://birdboy.sakura.ne.jp/about/collection/selectCollectionType.php",
					"http://birdboy.sakura.ne.jp/about/collection/selectCollectionArea.php",
					"http://birdboy.sakura.ne.jp/about/collection/selectCollectionJob.php",
					};
	ProgressDialog mProgressDialog;
	
	String userName;
	String user_fech;

	Random rnd = new Random();
	int user_id = rnd.nextInt(9999);
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plofile_main);
        
        //データベースのセット(sqliteの方)
        ABOUT_DBHelper dbHelper = new ABOUT_DBHelper(getApplicationContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        //final AlertDialog.Builder mDlogBuilder = new AlertDialog.Builder(this);
        
        new getItem().execute();
        
        //ボタンを配置
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
    	
    	@Override
    	public void onClick(View v) {
        
    	//インテントの作成
    	Intent intent=new Intent();
    	intent.setClassName("apk.about","apk.about.Gomakasi");
    	
    	//遷移先にデータをセット
    	EditText name = (EditText)findViewById(R.id.editText1); 		//名前
    	EditText height = (EditText)findViewById(R.id.editText2); 		//身長
    	EditText weight = (EditText)findViewById(R.id.editText3); 		//体重 
    	EditText rent = (EditText)findViewById(R.id.editText4); 		//家賃
    	Spinner bloodtype = (Spinner)findViewById(R.id.spinner3); 		//血液型
    	EditText revenue = (EditText)findViewById(R.id.editText5); 		//収入
    	Spinner birthplace = (Spinner)findViewById(R.id.spinner5); 		//出身地
    	Spinner job = (Spinner)findViewById(R.id.spinner6); 			//職業
    	DatePicker birthday =(DatePicker)findViewById(R.id.datePicker1);//誕生日
    	
    	//DatePickerから年、月、日を取得
    	int year = birthday.getYear();
    	int month = birthday.getMonth();
    	int day = birthday.getDayOfMonth();
    	
    	//EditTextから文字列を取得
    	String userName = name.getText().toString();
    	String u_height = height.getText().toString();
    	int user_height = Integer.parseInt(u_height);
    	String u_weight = weight.getText().toString();
    	int user_weight = Integer.parseInt(u_weight);
    	
    	//Spinnerから文字列を取得
    	String user_rent = (String)rent.getText().toString();
    	String user_bloodtype = (String)bloodtype.getSelectedItem();
    	String user_revenue = (String)revenue.getText().toString();
    	String user_birthplace = (String)birthplace.getSelectedItem();
    	String user_job = (String)job.getSelectedItem();
    	
    	//年、月、日をuser_birthdayに入れる
    	String user_birthday = ((month + 1) + "月" +  day + "日");
    	
    	//デバッグ用(変数の中身確認)
    	//String s = userName;
    	//mDlogBuilder.setMessage(s).show();
    	
    	//SQLiteのUSERのテーブルデータを更新
    	ABOUT_DBHelper.update_Profile(db, user_id, userName, user_height, user_weight,  user_rent,
				user_bloodtype, user_revenue, user_birthplace, user_job, user_birthday);
    	ABOUT_DBHelper.update_Other(db, user_id, userName);
    	db.close();

    	//遷移先にデータの送信？
    	intent.putExtra("sendText1",userName);
    	intent.putExtra("sendText2",u_height);
    	intent.putExtra("sendText3",u_weight);
    	intent.putExtra("sendText5",user_rent);
    	intent.putExtra("sendText6",user_bloodtype);
    	intent.putExtra("sendText7",user_revenue);
    	intent.putExtra("sendText8",user_birthplace);
    	intent.putExtra("sendText9",user_job);
    	intent.putExtra("extra_year", year);
    	intent.putExtra("extra_month", month);
    	intent.putExtra("extra_day", day);
    	
    	exec_post();
    	
    	//遷移先の画面を起動
    	startActivity(intent);
    	}
    	});
        
        // 確認ダイアログの生成
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("利用規約");
        alertDlg.setMessage("このアプリはプロフィール交換アプリです" + "\n" + "個人情報を扱うので何かあったら" + "\n" + "自己責任でお願いします");
        alertDlg.setPositiveButton(
            "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // OK ボタンクリック処理
                }
            });
        alertDlg.setNegativeButton(
            "Cancel",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	Intent intent = new Intent();
	                intent.setClassName("apk.about", "apk.about.Title_main");
	                startActivity(intent);
                    // Cancel ボタンクリック処理
                }
            });

        // 表示
        alertDlg.create().show();
    }
    
     // POST通信を実行（AsyncTaskによる非同期処理を使うバージョン）
     private void exec_post() {

          // 非同期タスクを定義
          HttpPostTask task = new HttpPostTask(
            this,
            "http://birdboy.sakura.ne.jp/about/post/userJoin.php",

            // タスク完了時に呼ばれるUIのハンドラ
            new HttpPostHandler(){

              @Override
              public void onPostCompleted(String response) {
            	  //String res = response;
              }

              @Override
              public void onPostFailed(String response) {
            	  Toast.makeText(
                  getApplicationContext(), 
                  "エラーが発生しました" + response + "", 
                  Toast.LENGTH_LONG
                ).show();
              }
            }
          );
          task.addPostParam("id","" + user_id + "");

          // タスクを開始
          task.execute();
        }
     @Override  
 	public boolean onOptionsItemSelected(MenuItem item) {     
 		switch(item.getItemId()) {  
     		case android.R.id.home:  
     			finish();  
     		return true;  
 		}  
     	return super.onOptionsItemSelected(item);
 	}
     
     private class getItem extends AsyncTask<Void, Void, Void> {
 		String[] itemA;
 		String[] itemB;
 		String[] itemC;
 		
 		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(Plofile_main.this);
			mProgressDialog.setTitle(" データを読み込んでいます");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();			
		}
 		
 		@Override
		protected Void doInBackground(Void... params) {
			try {
				Document document1 = Jsoup.connect(url[0]).get();
				Document document2 = Jsoup.connect(url[1]).get();
				Document document3 = Jsoup.connect(url[2]).get();
				
				String tableMax1 = document1.getElementById("table").toString();
				String tableMax2 = document2.getElementById("table").toString();
				String tableMax3 = document3.getElementById("table").toString();
				
				String[] table1 = tableMax1.split(":");
				String[] table2 = tableMax2.split(":");
				String[] table3 = tableMax3.split(":");
				
				int num1 = Integer.parseInt(table1[1]);
				int num2 = Integer.parseInt(table2[1]);
				int num3 = Integer.parseInt(table3[1]);
				
				String[] items1 = new String[num1];
				String[] items2 = new String[num2];
				String[] items3 = new String[num3];
				
				for(int i=1;i <= num1;i++){
					String str = document1.getElementById("" + i + "").toString();
					String[] strs = str.split(":");
					items1[i-1] = strs[1];
			}
			for(int i=1;i <= num2;i++){
				String str = document2.getElementById("" + i + "").toString();
				String[] strs = str.split(":");
				items2[i-1] = strs[1];
		}
			for(int i=1;i <= num3;i++){
				String str = document3.getElementById("" + i + "").toString();
				String[] strs = str.split(":");
				items3[i-1] = strs[1];
		}
			itemA = items1;
			itemB = items2;
			itemC = items3;
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(Void result) {
			// Set title into TextView
			ArrayAdapter<String> adapter1;
			ArrayAdapter<String> adapter2;
			ArrayAdapter<String> adapter3;
			
			adapter1 = new ArrayAdapter<String>(Plofile_main.this,android.R.layout.simple_spinner_dropdown_item,0,itemA);
		    Spinner spin1 = (Spinner)findViewById(R.id.spinner3);
		    adapter2 = new ArrayAdapter<String>(Plofile_main.this,android.R.layout.simple_spinner_dropdown_item,0,itemB);
		    Spinner spin2 = (Spinner)findViewById(R.id.spinner5);
		    adapter3 = new ArrayAdapter<String>(Plofile_main.this,android.R.layout.simple_spinner_dropdown_item,0,itemC);
		    Spinner spin3 = (Spinner)findViewById(R.id.spinner6);
		       
		    spin1.setAdapter(adapter1);
		    spin2.setAdapter(adapter2);
		    spin3.setAdapter(adapter3);
		    
		    mProgressDialog.dismiss();
		}
	}
}

