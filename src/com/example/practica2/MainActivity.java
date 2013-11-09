package com.example.practica2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText edURL;
	private Button butGo;
	private WebView wvShow;
	private ProgressBar progressWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.edURL = (EditText)findViewById(R.id.edURL);
		this.butGo = (Button)findViewById(R.id.butGo);
		this.wvShow = (WebView)findViewById(R.id.wvShow);
		this.progressWeb = (ProgressBar)findViewById(R.id.progressWeb);
		
		wvShow.setWebViewClient(new WebViewClient()
		{
			@Override
			 public void onPageStarted(WebView view, String url, Bitmap favicon) 
			 {
				super.onPageStarted(view, url, favicon);
				MainActivity.this.progressWeb.setVisibility(View.VISIBLE);	
				MainActivity.this.edURL.setText(url);
			 }
			
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				MainActivity.this.progressWeb.setVisibility(View.INVISIBLE);
			}
						
			
		});
		
		this.butGo.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Обработка нажатия на кнопку "Перейти"
	 */
	private void clickGo()
	{
		String workURL = this.edURL.getText().toString();
		
		if(TextUtils.isEmpty(workURL)){
			Toast.makeText(getApplicationContext(), "Введите текст", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(workURL.indexOf("://") < 0)
			workURL = "http://" + workURL;
		
		this.wvShow.loadUrl(workURL);
			
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.butGo)
			this.clickGo();
		
	}
	
	@Override
	public void onBackPressed() {
		if(this.wvShow.canGoBack()){
			this.wvShow.goBack();
			return;
		}
			
	}

}
