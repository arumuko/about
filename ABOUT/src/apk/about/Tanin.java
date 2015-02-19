package apk.about;

import apk.about.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Tanin extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tanin);
        AlertDialog.Builder mDlogBuilder = new AlertDialog.Builder(this);
     // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        Bundle bundle = getIntent().getExtras();
        Intent intent = this.getIntent();
        int user_id = bundle.getInt("sendId");
        //String user_id = intent.getStringExtra("sendId");
        String about_id = String.valueOf(user_id);
        String user_name = intent.getStringExtra("sendName");
        TextView name = (TextView)this.findViewById(R.id.textViewname);
        name.setText(user_name);
        
      //デバッグ用(変数の中身確認)
    	String s = about_id;
    	mDlogBuilder.setMessage(s).show();
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
}