package apk.about;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import apk.about.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Collection_9 extends ListActivity {
	
	public static final String TAG = null;
	ListView listView;
	String[] result = null;
	String[] res = null;
	String url = "http://birdboy.sakura.ne.jp/about/collection/selectCollectionType.php";
	ProgressDialog mProgressDialog;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.collection_1);
                     
        // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
			 
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				mProgressDialog = new ProgressDialog(Collection_9.this);
				mProgressDialog.setTitle("楽器を読み込んでいます");
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.show();
				Log.d(TAG,"ちんぽこ");
				
			}
	 
			@Override
			protected Void doInBackground(Void... params) {
				try {
					// Connect to the web site
					Document document = Jsoup.connect(url).get();
					// Get the html document title
					String tableMax = document.getElementById("table").toString();
					String[] table = tableMax.split(":");
					int num = Integer.parseInt(table[1]);
					
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
				ArrayAdapter<String> adapter;
		        adapter = new ArrayAdapter<String>(Collection_9.this, android.R.layout.simple_list_item_1,0,item);

		        setListAdapter(adapter); 
					
				mProgressDialog.dismiss();
			}
	    }
}