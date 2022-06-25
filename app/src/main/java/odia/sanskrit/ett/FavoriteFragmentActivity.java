package odia.sanskrit.ett;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class FavoriteFragmentActivity extends  Fragment  { 
	
	public final int REQ_CD_FP = 101;
	public final int REQ_CD_CAM = 102;
	
	private String text = "";
	private String tran = "";
	private String time = "";
	private String share2 = "";
	private String path = "";
	private String backup_name = "";
	private String file_type = "";
	private String file_text = "";
	private String path_dir = "";
	private String path2 = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private SwipeRefreshLayout swiperefreshlayout1;
	private LinearLayout linear1;
	private TextView textview5;
	private RecyclerView recyclerview1;
	private AdView adview1;
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout linear3;
	private TextView textview3;
	private LinearLayout linear4;
	private TextView textview4;
	
	private SharedPreferences sh;
	private AlertDialog.Builder d;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder edittext_1;
	private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_cam;
	private Calendar c = Calendar.getInstance();
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.favorite_fragment, _container, false);
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
		
		swiperefreshlayout1 = (SwipeRefreshLayout) _view.findViewById(R.id.swiperefreshlayout1);
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		textview5 = (TextView) _view.findViewById(R.id.textview5);
		recyclerview1 = (RecyclerView) _view.findViewById(R.id.recyclerview1);
		adview1 = (AdView) _view.findViewById(R.id.adview1);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		textview3 = (TextView) _view.findViewById(R.id.textview3);
		linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
		textview4 = (TextView) _view.findViewById(R.id.textview4);
		sh = getContext().getSharedPreferences("sh", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(getContext());
		fp.setType("text/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		edittext_1 = new AlertDialog.Builder(getContext());
		_file_cam = FileUtil.createNewPictureFile(getContext());
		Uri _uri_cam = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_cam= FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", _file_cam);
		}
		else {
			_uri_cam = Uri.fromFile(_file_cam);
		}
		cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
		cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_toast("Processing..");
				_share(sh.getString("fav", ""));
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (listmap.size() > 0) {
					_Edittext_1_block();
				}
				else {
					_toast("No data available to export !");
				}
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
	}
	
	private void initializeLogic() {
		_refresh();
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				_refresh();
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		path = FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/Translate Backup/");
		textview5.setText("Export on : ".concat("Internal/Download/Translate Backup/"));
		adview1.loadAd(new AdRequest.Builder().build());
		textview5.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview4.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		if(listmap.size()==0){
			textview2.setVisibility(View.GONE);
			textview5.setText("No Favorites Found !");
		}



	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getContext(), _data.getData()));
					}
				}
				try {
					listmap = new Gson().fromJson(FileUtil.readFile(_filePath.get((int)(0))), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					sh.edit().putString("fav", new Gson().toJson(listmap)).commit();
					_refresh();
					_toast("Successfully Imported.");
				} catch(Exception e) {
					_toast("Invalid backup file Imported.");
					_refresh();
				}
			}
			else {
				_toast("Failed to import !");
			}
			break;
			
			case REQ_CD_CAM:
			if (_resultCode == Activity.RESULT_OK) {
				 String _filePath = _file_cam.getAbsolutePath();
				
				
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _refresh () {
		try {
			listmap.clear();
			listmap = new Gson().fromJson(sh.getString("fav", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());




			recyclerview1.setAdapter(new Recyclerview1Adapter(listmap));
			recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
			if(listmap.size()==0){
				textview2.setVisibility(View.GONE);
				textview5.setText("No Favorites Found !");
			}else {
				textview2.setVisibility(View.VISIBLE);
				textview5.setText("Export on : ".concat("Internal/Download/Translate Backup/"));

			}


		} catch(Exception e) {
			sh.edit().putString("fav", "[]").commit();
		}
	}
	
	
	public void _toast (final String _msg) {
		Util.CustomToast(getContext(), _msg, 0xFFFFFFFF, 16, 0xD9000000, 12, Util.CENTER);
	}
	
	
	public void _Edittext_1_block () {
		edittext_1.setTitle(Html.fromHtml("<font color='#2196f3'>Save</font>"));
		edittext_1.setMessage("Please enter backup name.");
		edittext_1.setCancelable(false);
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		
		
		
		final EditText edittxt1 = new EditText(getActivity());
		edittxt1.setHint("Enter here.");
		edittxt1.setLayoutParams(lp);
		
		edittxt1.setElevation(0f);
		layout.addView(edittxt1);
		edittext_1.setView(layout);
		
		
		
		
		/*

final EditText edittxt2 = new EditText(MainActivity.this);
edittxt2.setHint("1 Edittext");
edittxt2.setLayoutParams(lp);

edittxt2.setElevation(0f);
layout.addView(edittxt2);
edittext_1.setView(layout);
*/
		edittext_1.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				backup_name = edittxt1.getText().toString();
				
				/* If(edittext_1_var ==("")|| edittext_1_var.contains("@"))
{

edittxt1.setError("Invalid input");
} */
				if (!backup_name.trim().equals("")) {
					if (!backup_name.contains("*\"':;!?&$#()+/-,.><~`|•√π÷×{}×¶∆%™®©[]")) {
						FileUtil.writeFile(path.concat(backup_name.trim().concat(".xhtml")), sh.getString("fav", ""));
						_toast("Backup Saved on\n".concat(path));
					}
					else {
						_toast(":;!?&$#()+/-,. invalid chars");
						textview3.performClick();
					}
				}
				else {
					_toast("Please provide some name !");
					textview3.performClick();
				}
			}
		});
		edittext_1.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		edittext_1.create().show();
	}
	
	
	public void _share (final String _text) {
		c = Calendar.getInstance();
		if (_text.length() > 0) {
			file_type = "/Favorites of Translator_".concat(new SimpleDateFormat("hh:mm:ss aaa, dd MMM yyyy").format(c.getTime()).concat(".htm"));
			/*

 you can change .txt file to any other file like image file or mp3 or pdf or csv file 

*/
			file_text = _text;
			path_dir = FileUtil.getPackageDataDir(getContext());
			path2 = path_dir.concat(file_type);
			FileUtil.writeFile(path2, file_text);
			/*. Must add the 

"camera components ", 

if you not add this you getting an file provider error 

*/
			
			Intent iten = new Intent(android.content.Intent.ACTION_SEND);
			
			iten.setType("text/*");
			/* here edit the file type like text/*, image/*, all/*, audio/*

and edit activitie name

project by epic technical tricks
on 9 th April 2022
 */
			
			iten.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(getActivity(),getContext().getPackageName() + ".provider", new java.io.File(path2) ));
			
			startActivity(Intent.createChooser (iten, "Send to"));
		}
		else {
			_toast("No data available to share !");
		}
	}
	
	
	public void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(1.3f, 1f, 1.3f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(200);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
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
			final ImageView full =  _view.findViewById(R.id.full);

			try {



				text = listmap.get((int)_position).get("text").toString();
				tran = listmap.get((int)_position).get("tran").toString();
				time = listmap.get((int)_position).get("time").toString();
				disease_text.setText(text);
				name_text.setText(tran);
				date_text.setText(time);
				snlo.setText(String.valueOf((long)(_position + 1)));

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

				delete.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_clickAnimation(delete);
						d.setMessage("Are you sure to delete ?");
						d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								listmap.remove((int)(_position));
								sh.edit().putString("fav", new Gson().toJson(listmap)).commit();
								_refresh();
							}
						});
						d.setNegativeButton("No", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {

							}
						});
						d.create().show();
					}
				});
				copy.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_clickAnimation(copy);
						((ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "*Sanskrit Translation App* \n\n".concat(text.concat("\n\n".concat(tran.concat(""))))));
						Util.showMessage(getContext(), "Copied to clipboard.");
					}
				});
				share.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_clickAnimation(share);
						share2 = "*Sanskrit Translation App* \n\n".concat(text.concat("\n\n".concat(tran.concat(""))));
						Intent i = new
								Intent(android.content.Intent.ACTION_SEND);
						i.setType("text/plain");
						i.putExtra(android.content.Intent.EXTRA_TEXT,share2);
						startActivity(Intent.createChooser(i,"Share using"));

					}
				});
				disease_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				name_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				snlo.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				date_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				name_text.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						((ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", name_text.getText().toString()));
						_toast("Copied.");
						_clickAnimation(name_text);
						return true;
					}
				});
			} catch(Exception e) {
				listmap.clear();
				_toast("Invalid file imported.");
			}
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
