package ir.dancecodes.ramezan;


import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sub_menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_menu);
		Button btn_back=(Button) findViewById(R.id.sub_btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		obj_data=new Data(this);
		int te=Integer.parseInt(getIntent().getExtras().getString("Sub_num"));
		update_list(te);
	}
	
	Data obj_data;
	ArrayList<String> items_id;
	ArrayList<String> items_grp;

	private void update_list(int num_father) {
		
		String qry = obj_data.CreateQuery_data(
				Data.Query_data.select_allbyfather.toint(), 0, "", "", num_father, 0, 1);

		
		ArrayList<String> items = obj_data.RunQuery_getArray(qry, 1);
		items_id = obj_data.RunQuery_getArray(qry, 0);
		items_grp = obj_data.RunQuery_getArray(qry, 5);
		
		ListView lv = (ListView) findViewById(R.id.sub_list_main);
		lv.setAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1,
				R.id.item_text, items));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				if (items_grp.get(position).equals("1")) {
					Intent i = new Intent(getApplicationContext(),
							Sub_menu.class);
					i.putExtra("Sub_num",items_id.get(position));
					startActivity(i);
				}else
				{
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

	
	
}
