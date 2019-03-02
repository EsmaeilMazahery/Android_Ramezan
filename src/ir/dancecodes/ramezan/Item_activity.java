package ir.dancecodes.ramezan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Item_activity extends Activity {

	Data obj_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_activity);
		obj_data = new Data(this);

		String qry = obj_data.CreateQuery_data(
				Data.Query_data.select_addressbyid.toint(), Integer
						.parseInt(getIntent().getExtras()
								.getString("num_topic")), "", "", 0, 0, 0);
		String address = obj_data.RunQuery_getString(qry, 0);
		WebView wv = (WebView) findViewById(R.id.webView1);
		wv.setWebChromeClient(new WebChromeClient());
		WebSettings settings = wv.getSettings();
		settings.setDomStorageEnabled(true);
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setLoadsImagesAutomatically(true);
		wv.loadUrl(address);
		Button btn_back = (Button) findViewById(R.id.item_btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}
}
