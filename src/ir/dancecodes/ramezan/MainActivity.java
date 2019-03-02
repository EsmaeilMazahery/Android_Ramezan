package ir.dancecodes.ramezan;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Data obj_data;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Button btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		obj_data = new Data(this);

		Button btn_aboutme = (Button) findViewById(R.id.aboutme);
		btn_aboutme.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Aboutme.class));
			}
		});

		Button btn_res = (Button) findViewById(R.id.btn_resource);
		btn_res.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						Resource.class));
			}
		});

		update_list();
	}

	ArrayList<String> items_id;
	ArrayList<String> items_grp;

	private void update_list() {
		String qry = obj_data.CreateQuery_data(
				Data.Query_data.select_allbyfather.toint(), 0, "", "", 0, 0, 1);

		ArrayList<String> items = obj_data.RunQuery_getArray(qry, 1);
		items_id = obj_data.RunQuery_getArray(qry, 0);
		items_grp = obj_data.RunQuery_getArray(qry, 5);

		ListView lv = (ListView) findViewById(R.id.list_main);
		lv.setAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1,
				R.id.item_text, items));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				if (items_grp.get(position).equals("1")) {

					Intent i = new Intent(getApplicationContext(),
							Sub_menu.class);
					i.putExtra("Sub_num", items_id.get(position));
					startActivity(i);
				} else {
					Intent i = new Intent(getApplicationContext(),
							Item_activity.class);
					i.putExtra("num_topic", items_id.get(position));
					startActivity(i);
				}
			}
		});
	}

	private class MyAdapter extends ArrayAdapter<String> {
		ArrayList<String> names;

		public MyAdapter(Context context, int resource, int textViewResourceId,
				ArrayList<String> strings) {
			super(context, resource, textViewResourceId, strings);
			names = strings;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.list_item, parent, false);

			String my_stringname = names.get(position);
			TextView tvname = (TextView) row.findViewById(R.id.item_text);
			tvname.setText(my_stringname);

			return row;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.btn_exit) {
			finish();
			return true;
		} else if (id == R.id.btn_exit) {
			startActivity(new Intent(getApplicationContext(), Resource.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
