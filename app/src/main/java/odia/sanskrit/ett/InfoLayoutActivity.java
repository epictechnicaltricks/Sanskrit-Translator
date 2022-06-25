package odia.sanskrit.ett;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class InfoLayoutActivity extends  AppCompatActivity  { 
	
	
	private String fontName = "";
	private String typeace = "";
	private String separated = "";
	
	private LinearLayout linear1;
	private ViewPager viewpager2;
	private BottomNavigationView bottomnavigation1;
	private TextView title;
	ImageView more_apps;
	
	private SharedPreferences sh;

	private SharedPreferences rate;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.info_layout);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		viewpager2 = (ViewPager) findViewById(R.id.viewpager2);
		bottomnavigation1 = (BottomNavigationView) findViewById(R.id.bottomnavigation1);
		title = (TextView) findViewById(R.id.title);
		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);
		rate = getSharedPreferences("rate",MODE_PRIVATE);

		more_apps = findViewById(R.id.more_apps);




			Glide.with(getApplicationContext())
					.load("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhK4yQpdygUwhb2vImxMPSvPrRfQ5gxVRNFmtwzs3OCjWxVo2Yd4YwkOKS5SoT8rWscvEwVbHq74OKJYTXXf7dRjuoK7GWKit01sPSxH3Wa6f4IDw4xEQhZDolismbI6CB3OIVN2LZOGp3TuIIrg9ozL8Z9kjNA6gNib57fougchaFEI-VREj7iQbDP/s640/4305-stone.gif")
					.thumbnail(0.2f)
					.transition(withCrossFade())
					.into(more_apps);


		more_apps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent in = new Intent(Intent.ACTION_VIEW);
				in.setData(Uri.parse("https://play.google.com/store/apps/dev?id=5643192849440401243"));
				startActivity(in);

				Util.showMessage(getApplicationContext(),"Other Cool Apps");
			}
		});






		viewpager2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				bottomnavigation1.getMenu().getItem(_position).setChecked(true);
				if (_position == 0) {
					title.setText("Translate");
				}
				if (_position == 1) {
					title.setText("About");
				}
				if (_position == 2) {
					title.setText("Favorites");
				}
				if (_position == 3) {
					title.setText("History");
				}
				_clickAnimation(title);
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();
				viewpager2.setCurrentItem((int)_itemId);
				return true;
			}
		});
		
		title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}

	@Override
	public void onBackPressed() {

		if(rate.getString("rate","").equals("")){

			show_rate_dialog();

		}
		else {
			super.onBackPressed();
		}
	}

	private void initializeLogic() {
		
		_UI();
		if (sh.getString("fav", "").equals("")) {
			sh.edit().putString("fav", "[]").commit();
		}
		if (sh.getString("his", "").equals("")) {
			sh.edit().putString("his", "[]").commit();
		}
	}



	public void _Open_url (final String _url) {
		String url = _url;

		try{

			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);

		}catch(Exception e){


			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}


	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class MyFragmentAdapter extends FragmentStatePagerAdapter {
		Context context;
		int tabCount;
		
		public MyFragmentAdapter(Context context, FragmentManager fm, int tabCount) {
			super(fm);
			this.context = context;
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount(){
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new HomeFragmentActivity();
			}
			if (_position == 1) {
				return new AboutFragmentActivity();
			}
			if (_position == 2) {
				return new FavoriteFragmentActivity();
			}
			if (_position == 3) {
				return new HistoryFragmentActivity();
			}
			return null;
		}
		
	}
	
	public void _UI () {
		viewpager2.setAdapter(new MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 4));
		bottomnavigation1.getMenu().add(0, 0, 0, "Translate").setIcon(R.drawable.home);
		bottomnavigation1.getMenu().add(0, 1, 0, "About").setIcon(R.drawable.user);
		bottomnavigation1.getMenu().add(0, 2, 0, "Favorites").setIcon(R.drawable.heart);
		bottomnavigation1.getMenu().add(0, 3, 0, "History").setIcon(R.drawable.history);
		_changeActivityFont("google_sans_medium");
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
	}


	public void show_rate_dialog(){

		final AlertDialog dialog2 = new AlertDialog.Builder(this).create();
		View inflate = getLayoutInflater().inflate(R.layout.rate,null);
		dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog2.setView(inflate);

		TextView title = (TextView) inflate.findViewById(R.id.title);
		TextView text = (TextView) inflate.findViewById(R.id.text);
		TextView btn1 = (TextView) inflate.findViewById(R.id.btn1);



		TextView btn2 = (TextView) inflate.findViewById(R.id.btn2);
		TextView btn3 = (TextView) inflate.findViewById(R.id.btn3);



		LinearLayout back = (LinearLayout) inflate.findViewById(R.id.back);

		dialog2.setCancelable(true);


		android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		SketchUi.setColor(0xFF121413);
		SketchUi.setCornerRadius(d*10);
		back.setBackground(SketchUi);




		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
				dialog2.dismiss();  //LATER BUTTON
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				rate.edit().putString("rate","rate_clicked").apply();

				_Open_url("https://play.google.com/store/apps/details?id="+getPackageName());

				dialog2.dismiss();  // RATE BUTTON

			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {



				dialog2.dismiss();  // support BUTTON

			}
		});
		dialog2.show();
	}
	
	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			Util.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.8f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(200);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
}
