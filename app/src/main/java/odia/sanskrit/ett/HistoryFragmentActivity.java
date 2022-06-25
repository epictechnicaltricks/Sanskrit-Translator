package odia.sanskrit.ett;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class HistoryFragmentActivity extends  Fragment  { 
	
	
	private String text = "";
	private String tran = "";
	private String time = "";
	private String share2 = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private RecyclerView recyclerview1;
	private AdView adview1;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear3;
	private TextView textview2 , history_count;
	
	private SharedPreferences sh;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.history_fragment, _container, false);
		initialize(_savedInstanceState, _view);

		MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {

			}
		});

		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		recyclerview1 = (RecyclerView) _view.findViewById(R.id.recyclerview1);
		adview1 = (AdView) _view.findViewById(R.id.adview1);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		textview1 = (TextView) _view.findViewById(R.id.textview1);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		sh = getContext().getSharedPreferences("sh", Activity.MODE_PRIVATE);
		history_count = _view.findViewById(R.id.history_count);
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_refresh();
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sh.edit().putString("his", "[]").commit();
				_refresh();
			}
		});
	}
	
	private void initializeLogic() {
		_refresh();
		adview1.loadAd(new AdRequest.Builder().build());
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		history_count.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);


	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _refresh () {
		listmap.clear();
		listmap = new Gson().fromJson(sh.getString("his", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		Collections.reverse(listmap);

		history_count.setText(String.valueOf(listmap.size()).concat(" History Available"));
if(listmap.size()==0){
	textview2.setVisibility(View.GONE);
}else {
	textview2.setVisibility(View.VISIBLE);
}
		recyclerview1.setAdapter(new Recyclerview1Adapter(listmap));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final TextView disease_text = (TextView) _view.findViewById(R.id.disease_text);
			final TextView name_text = (TextView) _view.findViewById(R.id.name_text);
			final TextView snlo = (TextView) _view.findViewById(R.id.snlo);
			final TextView date_text = (TextView) _view.findViewById(R.id.date_text);
			final ImageView delete = (ImageView) _view.findViewById(R.id.delete);
			final ImageView copy = (ImageView) _view.findViewById(R.id.copy);
			final ImageView share = (ImageView) _view.findViewById(R.id.share);
			final TextView from_lang = _view.findViewById(R.id.from_lang);
			final ImageView full =  _view.findViewById(R.id.full);
			
			snlo.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
			text = listmap.get((int)_position).get("text").toString();
			tran = listmap.get((int)_position).get("tran").toString();
			time = listmap.get((int)_position).get("time").toString();
			disease_text.setText(text);
			name_text.setText(tran);

			from_lang.setVisibility(View.GONE);

			full.setColorFilter(0xFF000000);
			full.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent in = new Intent();
					in.setClass(getContext(), FullscreenActivity.class);
					in.putExtra("text", text);
					in.putExtra("tran", tran);
					startActivity(in);
				}
			});





			date_text.setText(time);
			copy.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					((ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "*Sanskrit Translation App* \n\n".concat(text.concat("\n\n".concat(tran.concat(""))))));
					Util.showMessage(getContext(), "Copied to clipboard.");
				}
			});
			share.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					share2 = "*Sanskrit Translation App* \n\n".concat(text.concat("\n\n".concat(tran.concat(""))));
					Intent i = new
 Intent(android.content.Intent.ACTION_SEND);
					i.setType("text/plain");
					i.putExtra(android.content.Intent.EXTRA_TEXT,share2);
					startActivity(Intent.createChooser(i,"Share using"));
					
				}
			});
			name_text.setBackgroundColor(0xFF43A047);
			linear1.setBackgroundColor(0xFFE8F5E9);
			date_text.setTextColor(0xFF1B5E20);
			disease_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			name_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
			snlo.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
			date_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder{
			public ViewHolder(View v){
				super(v);
			}
		}
		
	}
	
	
}
