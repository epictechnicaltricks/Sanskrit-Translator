package odia.sanskrit.ett;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class AboutFragmentActivity extends  Fragment  { 
	
	
	private LinearLayout linear1;
	private ScrollView vscroll2;
	private LinearLayout linear6;
	private CardView cardview2;
	private CardView cardview1;
	private CardView cardview3;
	private CardView cardview4;
	private LinearLayout linear12;
	private LinearLayout linear2;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private TextView textview11;
	private LinearLayout linear3;
	private TextView textview4;
	private ImageView imageview9;
	private TextView textview5;
	private TextView textview6;
	private LinearLayout linear10;
	private ImageView imageview6;
	private ImageView imageview7;
	private ImageView imageview5;
	private LinearLayout linear4;
	private TextView textview7;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ImageView imageview2;
	private TextView textview8;
	private ImageView imageview3;
	private TextView textview9;
	private ImageView imageview4;
	private TextView textview12;
	private LinearLayout linear11;
	private TextView textview14;
	private ImageView imageview8;
	private TextView textview15;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.about_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		vscroll2 = (ScrollView) _view.findViewById(R.id.vscroll2);
		linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
		cardview2 = (CardView) _view.findViewById(R.id.cardview2);
		cardview1 = (CardView) _view.findViewById(R.id.cardview1);
		cardview3 = (CardView) _view.findViewById(R.id.cardview3);
		cardview4 = (CardView) _view.findViewById(R.id.cardview4);
		linear12 = (LinearLayout) _view.findViewById(R.id.linear12);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		textview1 = (TextView) _view.findViewById(R.id.textview1);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		textview3 = (TextView) _view.findViewById(R.id.textview3);
		textview11 = (TextView) _view.findViewById(R.id.textview11);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		textview4 = (TextView) _view.findViewById(R.id.textview4);
		imageview9 = (ImageView) _view.findViewById(R.id.imageview9);
		textview5 = (TextView) _view.findViewById(R.id.textview5);
		textview6 = (TextView) _view.findViewById(R.id.textview6);
		linear10 = (LinearLayout) _view.findViewById(R.id.linear10);
		imageview6 = (ImageView) _view.findViewById(R.id.imageview6);
		imageview7 = (ImageView) _view.findViewById(R.id.imageview7);
		imageview5 = (ImageView) _view.findViewById(R.id.imageview5);
		linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
		textview7 = (TextView) _view.findViewById(R.id.textview7);
		linear7 = (LinearLayout) _view.findViewById(R.id.linear7);
		linear8 = (LinearLayout) _view.findViewById(R.id.linear8);
		linear9 = (LinearLayout) _view.findViewById(R.id.linear9);
		imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
		textview8 = (TextView) _view.findViewById(R.id.textview8);
		imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
		textview9 = (TextView) _view.findViewById(R.id.textview9);
		imageview4 = (ImageView) _view.findViewById(R.id.imageview4);
		textview12 = (TextView) _view.findViewById(R.id.textview12);
		linear11 = (LinearLayout) _view.findViewById(R.id.linear11);
		textview14 = (TextView) _view.findViewById(R.id.textview14);
		imageview8 = (ImageView) _view.findViewById(R.id.imageview8);
		textview15 = (TextView) _view.findViewById(R.id.textview15);
		
		textview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://ettricks18.blogspot.com/p/privacy-policy.html?m=1");
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://www.instagram.com/epictechnicaltricks/?hl=en");
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://www.youtube.com/EPICTechnicalTricks");
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://www.linkedin.com/mwlite/in/shubhamjit-dash-61575919a");
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://play.google.com/store/apps/details?id="+ getContext().getPackageName());
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				AlertDialog.Builder dia1 = new AlertDialog.Builder(getActivity());
				dia1.setTitle("SUPPORT FOR THIS APP");
				dia1.setMessage("You can support me by donating some money \n\n UPI ID: subhamjeetdash2002-1@oksbi");
				dia1.setPositiveButton("COPY UPI ID", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						((ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE))
								.setPrimaryClip(ClipData.newPlainText("clipboard", "subhamjeetdash2002-1@oksbi"));
						Util.showMessage(getContext(), "Copied UPI ID, Thanks for support :-)");

					//	_Open_url("upi://pay?pa=&pn=Shubhamjit CSE&aid=uGICAgIC1hrecNw");

					}
				});
				dia1.create().show();
				}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("mailto:ettech1840@gmail.com");
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Open_url("https://play.google.com/store/apps/dev?id=5643192849440401243");
			}
		});
	}
	
	private void initializeLogic() {
		_font_b(textview1);
		_font_b(textview4);
		_font_b(textview7);
		_font_b(textview14);
		_font(textview2);
		_font(textview3);
		_font(textview5);
		_font(textview6);
		_font(textview8);
		_font(textview9);
		_font(textview11);
		_font(textview12);
		_font(textview15);
		Glide.with(getContext()).load(Uri.parse("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjGqvgHi46wPTnZDRcXMJ4ZflZyYWVUYi3gWbG1ZX7PXDiOGVOoLMJjBunrXOGzP9DHKUvpUutfHREuob5rmLPMUm9JC12aZjcb-d2usPBPhGbF37b4l75oSmwZdX6RVmyW-V1QudDTpCId0JNNNMIR5BJQZf20es2NpcscWyNDrjGTLwuEEPNxveP5/s600/ezgif-5-433659c2ad.gif")).into(imageview8);
		textview15.setText("Copyright 2022, Version 1.0");
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _font (final TextView _view) {
		_view.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
	}
	
	
	public void _font_b (final TextView _view) {
		_view.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
	}
	
	
	public void _Open_url (final String _url) {
		String url = _url; 

		try{

			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);

		}catch(Exception e){


			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		}

		
	}
	
	
	
}
