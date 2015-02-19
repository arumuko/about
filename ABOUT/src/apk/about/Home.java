
//このアプリのメインページ
//QRコード表示されるページ

package apk.about;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import apk.about.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends Fragment implements OnClickListener{

	public static String TAG = "Home";
	String fileName = "qrcode.png";
	ImageView image;
	Bitmap bmp;
	
	@Override
    public void onAttach(Activity act){
        super.onAttach(act);
        Log.d(TAG,"Fragment-onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Fragment-onCreate");
        
      //内部ストレージからQRcodeの画像を取得
        try {
      		bmp = loadBitmapLocalStorage(fileName, getActivity());
      	} catch (FileNotFoundException e) {
      		// TODO 自動生成された catch ブロック
      		e.printStackTrace();
      	} catch (IOException e) {
      		// TODO 自動生成された catch ブロック
      		e.printStackTrace();
      	}

    }
    
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	  View view = inflater.inflate(R.layout.home, container, false);
    
    Button QRr = (Button)view.findViewById(R.id.QRreader);
    QRr.setOnClickListener(this);
    image = (ImageView)view.findViewById(R.id.result_view);

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
      Log.d(TAG,"Fragment-onStart");
      //ImageViewにQRコードをセット
      image.setImageBitmap(bmp);
  }
  
  @Override
  public void onResume(){
      super.onResume();
      Log.d(TAG,"Fragment-onResume");
  }

  @Override
  public void onPause(){
      super.onPause();
      //bmp.recycle();
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
  
  //QRコード生成するクラス↓
//  public class QRCodeControler {
//	  
//	  /// エンコード設定
//	  private static final String ENCORD_NAME = "ISO-8859-1";
//
//	  @SuppressWarnings({ "unchecked", "rawtypes" })
//	  public Bitmap createQRCode(byte[] qrData){
//		  Bitmap ret = null;
//		  try{
//			  // QRコードを生成するにあたり、バイナリデータをStringへ変換
//			  String contents = new String(qrData, ENCORD_NAME);
//			  
//			  // QRコードを生成
//			  QRCodeWriter writer = new QRCodeWriter();
//			  Hashtable encodeHint = new Hashtable();
//			  encodeHint.put(EncodeHintType.CHARACTER_SET, ENCORD_NAME);
//			  encodeHint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//			  BitMatrix bitData = writer.encode(contents, BarcodeFormat.QR_CODE, 350, 350, encodeHint);
//			 
//			  int width = bitData.getWidth();
//			  int height = bitData.getHeight();
//			  int[] pixels = new int[width * height];
//			  
//			  // All are 0, or black, by default
//			  for (int y = 0; y < height; y++) {
//				  int offset = y * width;
//				  for (int x = 0; x < width; x++) {
//					  pixels[offset + x] = bitData.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
//					  }
//			  }
//			  // Bitmapに変換
//			  ret = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//			  ret.setPixels(pixels, 0, width, 0, 0, width, height);
//
//			  return ret;
//		  }catch(Exception e){
//		 e.printStackTrace();
//		 	return null;
//		  }
//  		}
//	 }
  
  //内部ストレージから、画像ファイルを読み込む
  public static final Bitmap loadBitmapLocalStorage(String fileName, Context context) 
		  throws IOException, FileNotFoundException {
	  		BufferedInputStream bis = null;
	  		try {
	  			bis = new BufferedInputStream(context.openFileInput(fileName));
	  			return BitmapFactory.decodeStream(bis);
	  		} finally {
	  			try {
	  				bis.close();
	  			} catch (Exception e) {
	  				//IOException, NullPointerException
	  			}
	  		}
  }

	@Override
	public void onClick(View v) {
		Intent i = new Intent(getActivity(), QRcodeReader.class);
    
		startActivity(i);
	}

}
