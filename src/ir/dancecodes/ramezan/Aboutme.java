package ir.dancecodes.ramezan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Aboutme extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.aboutme);
	
	Button btn_nazar=(Button) findViewById(R.id.btn_nazar);
	btn_nazar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_EDIT); 
			intent.setData(Uri.parse("bazaar://details?id=" + "ir.dancecodes.ramezan")); 
			intent.setPackage("com.farsitel.bazaar"); 
			startActivity(intent); 
		}
	});
	
	Button btn_myapp=(Button) findViewById(R.id.btn_myapp);
	btn_myapp.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW); 
			intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + "esmaeil-mazahery")); 
			intent.setPackage("com.farsitel.bazaar"); 
			startActivity(intent); 
		}
	});
}
}
