package apk.about;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.DirectionalViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import apk.about.R;

//縦スワイプのコード

public class PagerFragment extends Fragment {

	static final String TAG = "PagerFragment";
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.d(TAG, "onCreate");
	 }
	 
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pager, null);
        final DirectionalViewPager pager = (DirectionalViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        return view;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    

    private static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            //Fragmentをセット
            fragments = Arrays.asList(new Home(),new Profile());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}