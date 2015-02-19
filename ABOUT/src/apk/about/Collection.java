
//コレクションのページ
//ここから色々なコレクションのページに遷移

package apk.about;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import apk.about.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Collection extends Fragment {
			
	public static String TAG = "Collection";
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	
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
        View view = inflater.inflate(R.layout.collection, container, false);
        
        button1 = (Button)view.findViewById(R.id.button1);
        button2 = (Button)view.findViewById(R.id.button2);
        button3 = (Button)view.findViewById(R.id.button3);
        button4 = (Button)view.findViewById(R.id.button4);
        button5 = (Button)view.findViewById(R.id.button5);
        button6 = (Button)view.findViewById(R.id.button6);
        button7 = (Button)view.findViewById(R.id.button7);
        button8 = (Button)view.findViewById(R.id.button8);
        button9 = (Button)view.findViewById(R.id.button9);
        
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
       
       button1.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
    		   Intent animal = new Intent(getActivity(), Collection_1.class);
               startActivity(animal);
           }
       });
       button2.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
    		   Intent area = new Intent(getActivity(), Collection_2.class);
               
               startActivity(area);
           }
       });
       button3.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_3.class);
               
               startActivity(i);
           }
       });
       button4.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_4.class);
               
               startActivity(i);
           }
       });
       button5.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_5.class);
               
               startActivity(i);
           }
       });
       button6.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_6.class);
               
               startActivity(i);
           }
       });
       button7.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_7.class);
               
               startActivity(i);
           }
       });
       button8.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_8.class);
               
               startActivity(i);
           }
       });
       button9.setOnClickListener(new View.OnClickListener() {

    	   @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), Collection_9.class);
               
               startActivity(i);
           }
       });
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
    
    
//    private class getItem extends AsyncTask<Void, Void, Void> {
//		String[] item;
//		 
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			mProgressDialog = new ProgressDialog(Collection,this);
//			mProgressDialog.setTitle("Android Basic JSoup Tutorial");
//			mProgressDialog.setMessage("Loading...");
//			mProgressDialog.setIndeterminate(false);
//			mProgressDialog.show();
//			Log.d(TAG,"ちんぽこ");
//			
//		}
// 
//		@Override
//		protected Void doInBackground(Void... params) {
//			try {
//				// Connect to the web site
//				Document document = Jsoup.connect(url).get();
//				// Get the html document title
//				String str = document.getElementById("1").toString();
//				String[] strs = str.split(":");
//				item = strs;
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
// 
//		@Override
//		protected void onPostExecute(Void result) {
//			// Set title into TextView
//			animal.putExtra("KEY",item);
//			Log.d(TAG,"ぽんぽこ");
//				
//			//mProgressDialog.dismiss();
//		}
//    }
}
