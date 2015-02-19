//ABOUT_v12.3

package apk.about;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import apk.about.ABOUT_DBHelper;

public class Other extends ListFragment {
  
		public static String TAG = "Other";
		//Other_DBHelper helper;
		static ArrayAdapter<String> adapter;
		ListView list;
		Cursor c;
		int user_id;
	

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
		
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//			Log.d(TAG,"Fragment-onCreateView");
//            View v = inflater.inflate(R.layout.other,container,false);
//           list = (ListView)v.findViewById(R.id.listView);
//            
//            return v;
//        }
		
		public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		  }
		
		@Override
        public void onStart(){
            super.onStart();
            
            //DB接続
            ABOUT_DBHelper helper = new ABOUT_DBHelper(getActivity());
            final SQLiteDatabase db = helper.getReadableDatabase();
            
            //SQLiteからOTHER(他人)の名前とIDを取ってくる
            c = db.query("OTHER", new String[] { "USER_ID", "OTHER_NAME"},
            null, null, null, null, null, null);
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            
           //adapterにDBから文字列を追加
            boolean isEof = c.moveToFirst();
            while(isEof){
            adapter.add(c.getString(1));
            isEof = c.moveToNext();
            }
             
            //DBクローズ
            c.close();
            db.close();
            
            //SQLiteの内容をListにセット
            setListAdapter(adapter);
            
                        
            //Listの項目をクリックしたときの処理
            getListView().setOnItemClickListener(new AdapterView. OnItemClickListener() {
            	 
            	   @Override
            	   public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            		//クリック時LogにIDを表示
            		   ABOUT_DBHelper helper = new ABOUT_DBHelper(getActivity());
                       final SQLiteDatabase db = helper.getReadableDatabase();
            		   
            		   Intent intent = new Intent();
            		   intent.setClassName("apk.about","apk.about.Tanin");
            		   ListView list = (ListView)parent;
            		   String name = (String)list.getItemAtPosition(position); 
            		   //String where = "OTHER_NAME = " + name + "";
//            		   c = db.query("OTHER", new String[] { "USER_ID" },
//                               where, null, null, null, null,null);
//                               int user_id = c.getInt(0);
//                               
//                               //DBクローズ
//                               c.close();
//                               db.close();
            		 //SQL文
            		   String sql    = "SELECT `USER_ID` FROM OTHER WHERE `OTHER_NAME` = '" + name + "'";
            		                
            		   //SQL文の実行
            		   Cursor cursor = db.rawQuery(sql , null);
            		   cursor.moveToFirst();
            		   int user_id = cursor.getInt(0);
            		   intent.putExtra("sendName", name);
            		   intent.putExtra("sendId", user_id);
            		   startActivity(intent);
            	    
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