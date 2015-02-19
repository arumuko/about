
//自分のプロフィールのページ

package apk.about;

import apk.about.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Profile extends Fragment{

	public static String TAG = "Home";
	Button Editing;
	Intent i;
	
	@Override
    public void onAttach(Activity act){
        super.onAttach(act);
        Log.d(TAG,"Fragment-onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Fragment-onCreate");
    }
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.profile, container, false);
    	
    	Editing = (Button)view.findViewById(R.id.editing);
    	Log.d(TAG,"Fragment-onCreateView");
    return view;
    }
    
    @Override
    public void onActivityCreated(Bundle bundle){
    	super.onActivityCreated(bundle);
    	Log.d(TAG,"Fragment-onActivityCreated");
    }
  
    @Override
    public void onStart() {
    	super.onStart();
    	Editing.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			i = new Intent(getActivity(), Gomakasi.class);
    			startActivity(i);
    		} 
    	});
    	Log.d(TAG,"Fragment-onStart");
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.d(TAG,"Fragment-onResume");
    }

    @Override
    public void onPause(){
    	super.onPause();
    	Log.d(TAG,"Fragment-onPause");
    }

    @Override
    public void onStop(){
    	super.onStop();
    	Log.d(TAG,"Fragment-onStop");
    }

    @Override
    public void onDestroyView(){
    	super.onDestroyView();
    	Log.d(TAG,"Fragment-onDestroyView");
    }

    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Log.d(TAG,"Fragment-onDestroy");
    }

    @Override
    public void onDetach(){
    	super.onDetach();
    	Log.d(TAG,"Fragment-onDetach");
    }
}