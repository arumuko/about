package apk.about;

import java.util.List;
import java.util.Locale;
import java.util.Arrays;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.app.ActionBar;
import android.view.MenuItem;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import apk.about.R;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.DirectionalViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	public static String TAG = "MainActivity";
	DirectionalViewPager pager;
	MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //タブを使う時はNAVIGATION_MODE_TABSをセット
	    final ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        pager = (DirectionalViewPager) findViewById(R.id.d_pager);
        //viewpagerにadapterをセット
        pager.setAdapter(adapter);
	    
        //ViewPagerのページ数だけタブを追加
	    for(int i = 0; i < adapter.getCount(); i++) {
	    	actionBar.addTab(actionBar.newTab()
	    			.setText(adapter.getPageTitle(i)).setTabListener(this));
	    }
	    
	    //ViewPagerのページが切り替わったら、選択されているタブの位置を変更
	    pager.setOnPageChangeListener(new DirectionalViewPager.SimpleOnPageChangeListener() {
	    	@Override
	    	public void onPageSelected(int position){
	    		actionBar.setSelectedNavigationItem(position);
	    	}
	    });
    }
    @Override
    public void onStart() {
     super.onStart();
     Log.d(TAG, "Activity-onStart");
    }
    
    @Override
    public void onRestart() {
     super.onRestart();
     Log.d(TAG, "Fragment-onRestart");
    }
    
    @Override
    public void onResume() {
     super.onResume();
     Log.d(TAG, "Activity-onResume");
    }
    
    @Override
    public void onPause() {
     super.onPause();
     Log.d(TAG, "Activity-onPause");
    }
    
    @Override
    public void onStop() {
     super.onStop();
     Log.d(TAG, "Activity-onStop");
    }
    
    @Override
    public void onDestroy() {
     super.onDestroy();
     Log.d(TAG, "Activity-onDestroy");
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		//タブを選択されたら、ViewPagerのページをその位置に移動
		pager.setCurrentItem(tab.getPosition());
	}

	//タブの選択が外れた場合の処理
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	}

	//タブが2度目以降に選択された場合の処理
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	}
    
    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        
        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            fragments = Arrays.asList(
                    new PagerFragment(), // 上下にスワイプするフラグメント
                    new Other(),
                    new Collection());
        }

        //ここでタブに表示する画面を設定する
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        
        //タブの数を決定
        @Override
        public int getCount() {
            return fragments.size();
        }
        
      //タブのタイトルを決定
      @Override
	  public CharSequence getPageTitle(int position) {
		  Locale l = Locale.getDefault();
		  switch (position) {
		  case 0:
			  return getString(R.string.title_main).toUpperCase(l);
		  case 1:
			  return getString(R.string.title_other).toUpperCase(l);
		  case 2:
			  return getString(R.string.title_collection).toUpperCase(l);
		  }
	    return null;
	  }
	}
}