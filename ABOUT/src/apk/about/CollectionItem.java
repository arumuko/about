package apk.about;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import apk.about.R;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CollectionItem extends ListActivity {
	
	public static final String TAG = null;
	String url = "http://birdboy.sakura.ne.jp/about/post/itemResponse.php";
	ProgressDialog mProgressDialog;
	String tableName;
	String recodeName;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                             
        // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        Intent intent = this.getIntent();
        tableName = intent.getStringExtra("tableName");
        recodeName = intent.getStringExtra("recodeName");
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);
        
        new getItem().execute();
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
			String[] item;
			int num;
			 
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				mProgressDialog = new ProgressDialog(CollectionItem.this);
				mProgressDialog.setTitle("動物を読み込んでいます");
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.show();
				Log.d(TAG,"ちんぽこ");
				
			}
	 
			@Override
			protected Void doInBackground(Void... params) {
				try {
					// Connect to the web site
					Connection con = Jsoup.connect(url);
					HashMap<String, String> param = new HashMap<String, String>();
					param.put("tableName",tableName);
					param.put("recodeName",recodeName);
					Connection.Response res = con.data(param).method(Method.POST).execute();
					Document document = res.parse();
					// Get the html document title
					String tableMax = document.getElementById("createdView").toString();
					String[] table = tableMax.split(":");
					num = Integer.parseInt(table[1]);
					
					String[] items = new String[num];
					
					for(int i=1;i <= num;i++){
					String str = document.getElementById("" + i + "").toString();
					String[] strs = str.split(":");
					items[i-1] = strs[1];
					}
					item = items;
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
	 
			@Override
			protected void onPostExecute(Void result) {
				// Set title into TextView

				LinearLayout linearLayout = new LinearLayout(CollectionItem.this);
		        linearLayout.setOrientation(LinearLayout.VERTICAL);
		        setContentView(linearLayout);
		        
				// Get the html document title
			
		        if(num <= 4){
			        TextView textview1 = (TextView) new TextView(CollectionItem.this);
			        	textview1.setText(item[3]);
			        	textview1.setLayoutParams(new LinearLayout.LayoutParams(
			        			LinearLayout.LayoutParams.MATCH_PARENT,
			        			LinearLayout.LayoutParams.WRAP_CONTENT));
			        	linearLayout.addView(textview1);
		        }
		        if(num <= 3){
			        	
			        	TextView textview2 = (TextView) new TextView(CollectionItem.this);
			        	textview2.setText(item[2]);
			        	textview2.setLayoutParams(new LinearLayout.LayoutParams(
			        			LinearLayout.LayoutParams.MATCH_PARENT,
			        			LinearLayout.LayoutParams.WRAP_CONTENT));
			        	linearLayout.addView(textview2);
		        if(num <= 2){
			        	TextView textview3 = (TextView) new TextView(CollectionItem.this);
			        	textview3.setText(item[1]);
			        	textview3.setLayoutParams(new LinearLayout.LayoutParams(
			        			LinearLayout.LayoutParams.MATCH_PARENT,
			        			LinearLayout.LayoutParams.WRAP_CONTENT));
			        	linearLayout.addView(textview3);
		        if(num <= 1){
		        	TextView textview4 = (TextView) new TextView(CollectionItem.this);
		        	textview4.setText(item[0]);
		        	textview4.setLayoutParams(new LinearLayout.LayoutParams(
		        			LinearLayout.LayoutParams.MATCH_PARENT,
		        			LinearLayout.LayoutParams.WRAP_CONTENT));
		        	linearLayout.addView(textview4);
		         }

		        setContentView(linearLayout);
				mProgressDialog.dismiss();
			}
		        }
			}
	   }
}
