package odia.sanskrit.ett;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class FullscreenActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private ScrollView vscroll2;
	private LinearLayout linear3;
	private EditText edittext2;
	private EditText edittext1;
	private ImageView imageview1;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.fullscreen);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFEEEEEE));
				edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF2196F3));
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		edittext2.setText(getIntent().getStringExtra("text"));
		edittext1.setText(getIntent().getStringExtra("tran"));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

	
}
