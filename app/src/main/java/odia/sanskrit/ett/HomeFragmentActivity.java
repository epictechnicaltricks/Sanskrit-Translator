package odia.sanskrit.ett;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class HomeFragmentActivity extends  Fragment  {

	public final int REQ_CD_GOOGLE_VOICE_SEARCH = 101;

	private boolean swap_true = false;
	private HashMap<String, Object> param = new HashMap<>();
	private String seperated = "";
	private String from = "";
	private String to = "";
	private String text2 = "";
	private String url = "";
	private String share2 = "";
	private HashMap<String, Object> favorite = new HashMap<>();
	private String lang1 = "";
	private String lang2 = "";
	private String translate_from = "";
	private String translate_to = "";
	private String fontName = "";
	private String typeace = "";

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();

	private LinearLayout linear11;
	private ScrollView vscroll5;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout translate_layout;
	private AdView adview1;
	private TextView l1;
	private ImageView swap;
	private TextView l2;
	private ProgressBar progressbar1;
	private LinearLayout linear3;
	private LinearLayout linear8;
	private ScrollView vscroll1;
	private LinearLayout linear6;
	private TextView l1_h;
	private ImageView close;
	private EditText text;
	private TextView word;
	private ImageView speaker2;
	private ImageView voice;
	private LinearLayout linear9;
	private ScrollView vscroll4;
	private LinearLayout linear7;
	private TextView l2_h;
	private TextView translated;
	private ImageView fav;
	private ImageView wp_share;
	private ImageView share;
	private ImageView speaker;
	private ImageView copy;

	private RequestNetwork m;
	private RequestNetwork.RequestListener _m_request_listener;
	private TextToSpeech text_to_speech;
	private Intent in = new Intent();
	private SharedPreferences sh;
	private Calendar c = Calendar.getInstance();
	private Intent google_voice_search = new Intent(Intent.ACTION_GET_CONTENT);
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
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

		linear11 = (LinearLayout) _view.findViewById(R.id.linear11);
		vscroll5 = (ScrollView) _view.findViewById(R.id.vscroll5);
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
		translate_layout = (LinearLayout) _view.findViewById(R.id.translate_layout);
		adview1 = (AdView) _view.findViewById(R.id.adview1);
		l1 = (TextView) _view.findViewById(R.id.l1);
		swap = (ImageView) _view.findViewById(R.id.swap);
		l2 = (TextView) _view.findViewById(R.id.l2);
		progressbar1 = (ProgressBar) _view.findViewById(R.id.progressbar1);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		linear8 = (LinearLayout) _view.findViewById(R.id.linear8);
		vscroll1 = (ScrollView) _view.findViewById(R.id.vscroll1);
		linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
		l1_h = (TextView) _view.findViewById(R.id.l1_h);
		close = (ImageView) _view.findViewById(R.id.close);
		text = (EditText) _view.findViewById(R.id.text);
		word = (TextView) _view.findViewById(R.id.word);
		speaker2 = (ImageView) _view.findViewById(R.id.speaker2);
		voice = (ImageView) _view.findViewById(R.id.voice);
		linear9 = (LinearLayout) _view.findViewById(R.id.linear9);
		vscroll4 = (ScrollView) _view.findViewById(R.id.vscroll4);
		linear7 = (LinearLayout) _view.findViewById(R.id.linear7);
		l2_h = (TextView) _view.findViewById(R.id.l2_h);
		translated = (TextView) _view.findViewById(R.id.translated);
		fav = (ImageView) _view.findViewById(R.id.fav);
		wp_share = (ImageView) _view.findViewById(R.id.wp_share);
		share = (ImageView) _view.findViewById(R.id.share);
		speaker = (ImageView) _view.findViewById(R.id.speaker);
		copy = (ImageView) _view.findViewById(R.id.copy);
		m = new RequestNetwork((Activity)getContext());
		text_to_speech = new TextToSpeech(getContext(), null);
		sh = getContext().getSharedPreferences("sh", Activity.MODE_PRIVATE);
		google_voice_search.setType("*/*");
		google_voice_search.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);




		l1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

			}
		});

		swap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(l1);
				_clickAnimation(l2);
				if (swap_true) {
					swap_true = false;
					l1.setText(lang1);
					l2.setText(lang2);
					l1_h.setText(lang1);
					l2_h.setText(lang2);
					word.setText(word.getText().toString());
					translated.setText(translated.getText().toString());
				}
				else {
					swap_true = true;
					l1.setText(lang2);
					l2.setText(lang1);
					l1_h.setText(lang2);
					l2_h.setText(lang1);
					word.setText(translated.getText().toString());
					translated.setText(word.getText().toString());
				}
				RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); rotateAnimation.setInterpolator(new LinearInterpolator()); rotateAnimation.setDuration(500);

				swap.startAnimation(rotateAnimation);
			}
		});

		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					progressbar1.setVisibility(View.INVISIBLE);

					if (!translated.getText().toString().trim().equals("")) {
						if (sh.getString("his", "").equals("")) {
							sh.edit().putString("his", "[]").commit();
						}
						c = Calendar.getInstance();
						favorite.clear();
						listmap.clear();
						favorite = new HashMap<>();
						favorite.put("text", text.getText().toString());
						favorite.put("tran", translated.getText().toString());
						favorite.put("time", new SimpleDateFormat("hh:mm:ss aaa, dd MMM yyyy").format(c.getTime()));
						listmap = new Gson().fromJson(sh.getString("his", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						listmap.add(favorite);
						sh.edit().putString("his", new Gson().toJson(listmap)).commit();
					}
					else {

					}
					text.setText("");
					translated.setText("");
					word.setText("0 / 1000 Chars");
				} catch(Exception e) {
					Util.showMessage(getContext(), "Error");
				}
			}
		});

		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

			}
		});

		text.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				try {
					word.setText(String.valueOf((long)(_charSeq.trim().length())).concat(" / 1000 chars"));
					if (Util.isConnected(getContext())) {
						if (1001 > _charSeq.trim().length()) {
							word.setTextColor(0xFF757575);
							if (_charSeq.trim().length() > 0) {
								progressbar1.setVisibility(View.VISIBLE);
								param.put("User-Agent", "Mozilla/5.0");
								if (swap_true) {
									from = translate_to;
									to = translate_from;
								}
								else {
									from = translate_from;
									to = translate_to;
								}
								text2 = text.getText().toString();
								try {
									url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+from+"&tl="+to+"&dt=t&q="+java.net.URLEncoder.encode(text2, "utf-8");
								} catch (Exception e) {

									Util.showMessage(getContext(), "Error on translate\n\n" + e);

								}
								m.setParams(param, RequestNetworkController.REQUEST_PARAM);
								m.startRequestNetwork(RequestNetworkController.GET, url, "hidepain", _m_request_listener);
								fav.setEnabled(true);
								fav.setImageResource(R.drawable.heart);
							}
						}
						else {
							_Sneek_Message(linear1, "Limit exceeded !  chars > 1000 ");
							word.setTextColor(0xFFF44336);
						}
					}
					else {
						_Sneek_Message(linear1, "No Connection ! ");
						progressbar1.setVisibility(View.INVISIBLE);
					}
				} catch(Exception e) {
					Util.showMessage(getContext(), "Server Not Found !");
					progressbar1.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {

			}
		});





		word.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {

				return true;
				}
			 });

		speaker2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				text_to_speech.setSpeechRate((float)0.64d);
				text_to_speech.speak(text.getText().toString(), TextToSpeech.QUEUE_ADD, null);
				_Sneek_Message(linear1, "Reading..");
			}
		});

		voice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(voice);
				Intent intent = new Intent(android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH); intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); intent.putExtra(android.speech.RecognizerIntent.EXTRA_PROMPT, "Speak Now");
				try { startActivityForResult(intent, REQ_CODE_SPEECH_INPUT); }
				catch (ActivityNotFoundException a) {
					Toast.makeText(getContext(), "There was an error", Toast.LENGTH_SHORT).show(); }
			}
		});

		fav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					if (!translated.getText().toString().trim().equals("")) {
						if (sh.getString("fav", "").equals("")) {
							sh.edit().putString("fav", "[]").commit();
						}
						c = Calendar.getInstance();
						favorite.clear();
						listmap.clear();
						favorite = new HashMap<>();
						favorite.put("text", text.getText().toString());
						favorite.put("tran", translated.getText().toString());
						favorite.put("time", new SimpleDateFormat("hh:mm:ss aaa, dd MMM yyyy").format(c.getTime()));
						listmap = new Gson().fromJson(sh.getString("fav", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						listmap.add(favorite);
						sh.edit().putString("fav", new Gson().toJson(listmap)).commit();
						_clickAnimation(fav);
						fav.setImageResource(R.drawable.favorite);
						fav.setEnabled(false);
						_Sneek_Message(fav, "Added to Favorites");
					}
					else {
						_Sneek_Message(fav, "Empty Translation Text..");
					}
				} catch(Exception e) {
					Util.showMessage(getContext(), "Error");
				}
			}
		});

		wp_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getContext(), FullscreenActivity.class);
				in.putExtra("text", text.getText().toString());
				in.putExtra("tran", translated.getText().toString());
				startActivity(in);
			}
		});

		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(share);
				share2 = "Translated from ".concat(l1_h.getText().toString().concat(" to ".concat(l2_h.getText().toString().concat("\n\n\n".concat(text.getText().toString().concat("\n\n\" ".concat(translated.getText().toString().concat(" \"\n\n*Sanskrit Translator*"))))))));
				Intent i = new
 Intent(android.content.Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(android.content.Intent.EXTRA_TEXT,share2);
				startActivity(Intent.createChooser(i,"Share using"));

				_Sneek_Message(linear1, "Shareing..");
			}
		});

		speaker.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				text_to_speech.setSpeechRate((float)0.58d);
				text_to_speech.speak(translated.getText().toString(), TextToSpeech.QUEUE_ADD, null);
				_clickAnimation(speaker);
				_Sneek_Message(linear1, "Reading..");
			}
		});

		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", translated.getText().toString()));
				Util.showMessage(getContext(), "Copied to clipboard.");
				_clickAnimation(copy);
			}
		});

		_m_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_seperate_res_trans(_response);
				translated.setText(seperated);
				progressbar1.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				progressbar1.setVisibility(View.INVISIBLE);
			}
		};
	}

	private void initializeLogic() {
		wp_share.setColorFilter(0xFFFFFFFF);
		copy.setColorFilter(0xFFFFFFFF);
		fav.setColorFilter(0xFFFFFFFF);
		share.setColorFilter(0xFFFFFFFF);

		speaker.setColorFilter(0xFFFFFFFF);


		translate_layout.setVisibility(View.VISIBLE);
		lang1 = "Hindi | हिन्दी";
		lang2 = "SANSKRIT";
		translate_from = "hi";
		translate_to = "sa";
		l1.setText(lang1);
		l2.setText(lang2);
		l1_h.setText(lang1);
		l2_h.setText(lang2);
		translate_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF2196F3));
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFEEEEEE));
		text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)0, 0xFFEEEEEE));
		progressbar1.setVisibility(View.INVISIBLE);

		adview1.loadAd(new AdRequest.Builder().build());
		_textFont(l1);
		_textFont(l2);
		_textFont(l1_h);
		_textFont(text);
		_textFont(word);
		_textFont(l2_h);
		_textFont(translated);
	}

	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);

		switch (_requestCode) {
			case REQ_CD_GOOGLE_VOICE_SEARCH:
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
			}
			break;
			case REQ_CODE_SPEECH_INPUT:
			if ( null != _data) {


				ArrayList<String> result = _data.getStringArrayListExtra(android.speech.RecognizerIntent.EXTRA_RESULTS);

				text.setText(result.get(0));
			}
			else {
				_Sneek_Message(linear1, "Google Voice Error");
			}
			break;
			default:
			break;
		}
	}

	@Override
	public void onStop() {
		super.onStop();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		close.performClick();
	}
	public void _seperate_res_trans (final String _res) {
		try {
			seperated = new org.json.JSONArray(_res).getJSONArray(0).getJSONArray(0).getString(0);
		} catch (org.json.JSONException e) {
			seperated = "Server sent an invalid response";
		}
	}


	public void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(1.3f, 1f, 1.3f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(200);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}


	public void _Sneek_Message (final View _view, final String _msg) {
		com.google.android.material.snackbar.Snackbar.make(_view, _msg, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
			@Override
			public void onClick(View _view) {

			}
		}).show();
	}


	public void _G_speaker () {
	}
	public static final int REQ_CODE_SPEECH_INPUT = 1;
	{
	}


	public void _textFont (final TextView _view) {
		_view.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
	}



}
