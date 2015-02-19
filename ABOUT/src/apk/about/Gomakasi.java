//ABOUT_v11

package apk.about;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import apk.about.R;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;







//import apk.about.Plofile_main.QRCodeControler;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Gomakasi extends Activity {
	
	String[] url = {"http://birdboy.sakura.ne.jp/about/collection/selectCollectionAnimal.php",
					"http://birdboy.sakura.ne.jp/about/collection/selectCollectionAnimal.php",
					"http://birdboy.sakura.ne.jp/about/collection/selectCollectionMoney.php",
					"http://birdboy.sakura.ne.jp/about/gomakasi/selectCollectionType.php",
					"http://birdboy.sakura.ne.jp/about/collection/selectCollectionMoney.php",
					"http://birdboy.sakura.ne.jp/about/gomakasi/selectCollectionSightseeing.php",
					"http://birdboy.sakura.ne.jp/about/gomakasi/selectCollectionJob.php",
					"http://birdboy.sakura.ne.jp/about/gomakasi/selectCollectionAnniversary.php",
					};
	ProgressDialog mProgressDialog;
	
	String fileName = "qrcode.png"; 
	byte[] contents;
	Bitmap bmp;
	int user_id;
	String text1; //名前
	int text2; //身長
	int text3; //体重
	String height;
	String weight;
	String text4; //家賃
	String text5; //血液型
	String text6; //収入
	String text7; //出身地
	String text8; //職業
	String text9; //誕生日
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gomakasi_main);
        
      //データベースのセット(sqliteの方)
        ABOUT_DBHelper dbHelper = new ABOUT_DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //sqliteからデータを取ってくる
        Cursor c = db.query("USER", new String[] { "USER_ID", "NAME", "HEIGHT", "WEIGHT", "RENT", "BLOODTYPE", 
        		"REVENUE", "BIRTHPLACE", "JOB", "BIRTHDAY"}, null, null, null, null, null);
        		        boolean isEof = c.moveToFirst();
        		        while (isEof) {
        		        	// 取ってきたデータをそれぞれの変数に入れる
        		        	user_id = c.getInt(0);
        		            text1 = c.getString(1).toString();
        		            text2 = c.getInt(2);
        		            text3 = c.getInt(3);
        		            text4 = c.getString(4).toString();
        		            text5 = c.getString(5).toString();
        		            text6 = c.getString(6).toString();
        		            text7 = c.getString(7).toString();
        		            text8 = c.getString(8).toString();
        		            text9 = c.getString(9).toString();
        		            isEof = c.moveToNext();
        		        }
        		        //sqlite接続終了
        		        c.close();
        		        db.close();

        //QRにする文字列
        contents = (user_id + ":" + text1).getBytes();
        //QR生成
        QRCodeControler createQR = new QRCodeControler();
        //画面に表示
        bmp = createQR.createQRCode(contents);
        try {
        	//生成したQRコードを内部ストレージにpngで保存
			savePngLocalStorage(fileName, bmp, this);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} 
        
        // アクションバーに前画面に戻る機能をつける
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);      
        
      //インテント
        Bundle bundle = getIntent().getExtras();
        Intent intent = this.getIntent();
        String text1 = intent.getStringExtra("sendText1");
        String text2 = intent.getStringExtra("sendText2");
        String text3 = intent.getStringExtra("sendText3");
        String text5 = intent.getStringExtra("sendText5");
        String text6 = intent.getStringExtra("sendText6");
        String text7 = intent.getStringExtra("sendText7");
        String text8 = intent.getStringExtra("sendText8");
        String text9 = intent.getStringExtra("sendText9");
        int year = bundle.getInt("extra_year");
        int month = bundle.getInt("extra_month");
        int day = bundle.getInt("extra_day");
        
        TextView namae = (TextView)this.findViewById(R.id.textViewnamae);
        TextView sintyou = (TextView)this.findViewById(R.id.textViewsintyou);
        TextView taiju = (TextView)this.findViewById(R.id.textViewtaiju);
        TextView date = (TextView)this.findViewById(R.id.textViewdate);
        TextView yatin = (TextView)this.findViewById(R.id.textViewyatin);
        TextView ketueki = (TextView)this.findViewById(R.id.textViewketueki);
        TextView shuunyu = (TextView)this.findViewById(R.id.textViewshunyuu);
        TextView shussin = (TextView)this.findViewById(R.id.textViewshussin);
        TextView job = (TextView)this.findViewById(R.id.textViewjob);
        // intからStringに変換
        height = String.valueOf(text2);
        weight = String.valueOf(text3);
        
        namae.setText(text1);
        sintyou.setText(text2);
        taiju.setText(text3);
        date.setText(year+"年"+(month+1)+"月"+day+"日");
        yatin.setText(text5);
        ketueki.setText(text6);
        shuunyu.setText(text7);
        shussin.setText(text8);
        job.setText(text9);
        
//        namae.setText(user_name);
//        sintyou.setText(user_height);
//        taiju.setText(user_weight);
//        yatin.setText(user_rent);
//        ketueki.setText(user_bloodtype);
//        shuunyu.setText(user_revenue);
//        shussin.setText(user_birthplace);
//        job.setText(user_job);
//        date.setText(user_birthday);
        
        new getItem().execute();

        //ごまかし送る
        Button button1=(Button)findViewById(R.id.gomakasibutton1);
        button1.setOnClickListener(new View.OnClickListener(){
        	
        	@Override
        	public void onClick(View v) {
        		
        		//インテントの作成
        		Intent intent=new Intent();
        		intent.setClassName("apk.about","apk.about.Kakunin");
        		
        		//遷移先にデータをセット
        		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        		Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        		Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
        		Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);
        		Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);
        		Spinner spinner6 = (Spinner)findViewById(R.id.spinner6);
        		Spinner spinner7 = (Spinner)findViewById(R.id.spinner7);
        		Spinner spinner8 = (Spinner)findViewById(R.id.spinner8);
        		TextView textview1 = (TextView)findViewById(R.id.textViewnamae);
        		TextView textview2 = (TextView)findViewById(R.id.textViewsintyou);
        		TextView textview3 = (TextView)findViewById(R.id.textViewtaiju);
        		TextView textview5 = (TextView)findViewById(R.id.textViewyatin);
        		TextView textview6 = (TextView)findViewById(R.id.textViewketueki);
        		TextView textview7 = (TextView)findViewById(R.id.textViewshunyuu);
        		TextView textview8 = (TextView)findViewById(R.id.textViewshussin);
        		TextView textview9 = (TextView)findViewById(R.id.textViewjob);
        		TextView textview10 = (TextView)findViewById(R.id.textViewdate);
    	
        		String sintyou = (String)spinner1.getSelectedItem();
        		String taiju = (String)spinner2.getSelectedItem();
        		String yatin = (String)spinner3.getSelectedItem();
        		String ketueki = (String)spinner4.getSelectedItem();
        		String shuunyuu = (String)spinner5.getSelectedItem();
        		String shussin = (String)spinner6.getSelectedItem();
        		String job = (String)spinner7.getSelectedItem();
        		String seinengappi = (String)spinner8.getSelectedItem();
    	
        		intent.putExtra("height",sintyou);
        		intent.putExtra("weight",taiju);
        		intent.putExtra("rent",yatin);
        		intent.putExtra("bloodtype",ketueki);
        		intent.putExtra("revenue",shuunyuu);
        		intent.putExtra("birthplace",shussin);
        		intent.putExtra("job",job);
        		intent.putExtra("birthday",seinengappi);
        		intent.putExtra("sendText11",sintyou);
        		intent.putExtra("sendText12",taiju);
        		intent.putExtra("sendText14",yatin);
        		intent.putExtra("sendText15",ketueki);
        		intent.putExtra("sendText16",shuunyuu);
        		intent.putExtra("sendText17",shussin);
        		intent.putExtra("sendText18",job);
        		intent.putExtra("sendText19",seinengappi);
        		intent.putExtra("sendText21",textview1.getText().toString());
        		intent.putExtra("sendText22",textview2.getText().toString());
        		intent.putExtra("sendText23",textview3.getText().toString());
        		intent.putExtra("sendText25",textview5.getText().toString());
        		intent.putExtra("sendText26",textview6.getText().toString());
        		intent.putExtra("sendText27",textview7.getText().toString());
        		intent.putExtra("sendText28",textview8.getText().toString());
        		intent.putExtra("sendText29",textview9.getText().toString());
        		intent.putExtra("sendText30",textview10.getText().toString());
        		//遷移先の画面を起動
        		startActivity(intent);
        	}
    	});

    Button button2=(Button)findViewById(R.id.gomakasibutton0);
    button2.setOnClickListener(new View.OnClickListener(){
    	
    	public void onClick( View v_ ){
    		// アクティビティを終了させる事により、一つ前のアクティビティへ戻る事が出来る。
    		finish();
    	}
    });
   }
    
    //戻るボタン処理
    @Override  
	public boolean onOptionsItemSelected(MenuItem item) {     
		switch(item.getItemId()) {  
    		case android.R.id.home:  
    			finish();  
    		return true;  
		}  
    	return super.onOptionsItemSelected(item);
    
    }
    
    //QRコード生成するクラス↓
    public class QRCodeControler {
  	  
  	  /// エンコード設定
  	  private static final String ENCORD_NAME = "ISO-8859-1";

  	  @SuppressWarnings({ "unchecked", "rawtypes" })
  	  public Bitmap createQRCode(byte[] qrData){
  		  Bitmap ret = null;
  		  try{
  			  // QRコードを生成するにあたり、バイナリデータをStringへ変換
  			  String contents = new String(qrData, ENCORD_NAME);
  			  
  			  // QRコードを生成
  			  QRCodeWriter writer = new QRCodeWriter();
  			  Hashtable encodeHint = new Hashtable();
  			  encodeHint.put(EncodeHintType.CHARACTER_SET, ENCORD_NAME);
  			  encodeHint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
  			  BitMatrix bitData = writer.encode(contents, BarcodeFormat.QR_CODE, 700, 700, encodeHint);
  			 
  			  int width = bitData.getWidth();
  			  int height = bitData.getHeight();
  			  int[] pixels = new int[width * height];
  			  
  			  // All are 0, or black, by default
  			  for (int y = 0; y < height; y++) {
  				  int offset = y * width;
  				  for (int x = 0; x < width; x++) {
  					  pixels[offset + x] = bitData.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
  					  }
  			  }
  			  // Bitmapに変換
  			  ret = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
  			  ret.setPixels(pixels, 0, width, 0, 0, width, height);

  			  return ret;
  		  }catch(Exception e){
  		 e.printStackTrace();
  		 	return null;
  		  }
    		}
  	 }
    
    //内部ストレージに、画像ファイルを保存する(png) (Android 用)
    public static final boolean savePngLocalStorage(String fileName, Bitmap bitmap, Context context) throws IOException {
        BufferedOutputStream bos = null;
        Bitmap tmp = null;
        try {
            bos = new BufferedOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE)); //他アプリアクセス不可
            tmp = bitmap.copy(Config.ARGB_8888, true);
            return tmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        } finally {
            if (tmp != null) {
                tmp.recycle();
                tmp = null;
            }
            try {
                bos.close();
            } catch (Exception e) {
                //IOException, NullPointerException
            }
        }
    }
    
    private class getItem extends AsyncTask<Void, Void, Void> {
    	String[][] items = new String[url.length][];
    	String[] target = {height,weight,text4,text5,text6,text7,text8,text9};
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(Gomakasi.this);
			mProgressDialog.setTitle(" データを読み込んでいます");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();			
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Connect to the web site
				for(int j=0;j<url.length;j++){
					boolean flag = true;
					switch(j){
					case 0:
						flag = true;
						break;					
					case 1 :
						flag = true;
						break;
					case 2 :
						flag = true;
						break;
					case 3 :
						flag = false;
						break;
					case 4 :
						flag = true;
						break;
					case 5 :
						flag = false;
						break;
					case 6 :
						flag = false;
						break;
					case 7 :
						flag = false;
						break;
					default:
						flag = true;
					}
					if(flag == true){
						Document document = Jsoup.connect(url[j]).get();
					// Get the html document title
						String tableMax = document.getElementById("table").toString();
						String[] table = tableMax.split(":");
						int num = Integer.parseInt(table[1]);
				
						items[j] = new String[num];
				
						for(int i=1;i <= num;i++){
							String str = document.getElementById("" + i + "").toString();
							String[] strs = str.split(":");
							items[j][i-1] = strs[1];
						}
					}else if(flag == false){
						Connection con = Jsoup.connect(url[j]);
						HashMap<String, String> param = new HashMap<String, String>();
						param.put("type",target[j]);
						Connection.Response res = con.data(param).method(Method.POST).execute();
						Document document = res.parse();
						// Get the html document title
						String tableMax = document.getElementById("table").toString();
						String[] table = tableMax.split(":");
						int num = Integer.parseInt(table[1]);
					
						items[j] = new String[num];
					
						for(int i=1;i <= num;i++){
							String str = document.getElementById("" + i + "").toString();
							String[] strs = str.split(":");
							items[j][i-1] = strs[1];
						}
					}
				}
				
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
			ArrayAdapter<String> adapter4;
			ArrayAdapter<String> adapter5;
			ArrayAdapter<String> adapter6;
			ArrayAdapter<String> adapter7;
			ArrayAdapter<String> adapter8;
			
	        adapter1 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[0]);
	        Spinner spin1 = (Spinner)findViewById(R.id.spinner1);
	        adapter2 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[1]);
	        Spinner spin2 = (Spinner)findViewById(R.id.spinner2);
	        adapter3 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[2]);
	        Spinner spin3 = (Spinner)findViewById(R.id.spinner3);
	        adapter4 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[3]);
	        Spinner spin4 = (Spinner)findViewById(R.id.spinner4);
	        adapter5 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[4]);
	        Spinner spin5 = (Spinner)findViewById(R.id.spinner5);
	        adapter6 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[5]);
	        Spinner spin6 = (Spinner)findViewById(R.id.spinner6);
	        adapter7 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[6]);
	        Spinner spin7 = (Spinner)findViewById(R.id.spinner7);
	        adapter8 = new ArrayAdapter<String>(Gomakasi.this,android.R.layout.simple_spinner_dropdown_item,0,items[7]);
	        Spinner spin8 = (Spinner)findViewById(R.id.spinner8);
	        
	        spin1.setAdapter(adapter1);
	        spin2.setAdapter(adapter2);
	        spin3.setAdapter(adapter3);
	        spin4.setAdapter(adapter4);
	        spin5.setAdapter(adapter5);
	        spin6.setAdapter(adapter6);
	        spin7.setAdapter(adapter7);
	        spin8.setAdapter(adapter8);
				
			mProgressDialog.dismiss();
		}
    }
}