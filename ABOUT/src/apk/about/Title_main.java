package apk.about;

import apk.about.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Title_main extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		  View title = (View)findViewById(R.id.titleImage);
	        title.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Sub 画面を起動
	                Intent intent = new Intent();
	                intent.setClassName("apk.about", "apk.about.Plofile_main");
	                startActivity(intent);
	            }
	        });		
		}
  }