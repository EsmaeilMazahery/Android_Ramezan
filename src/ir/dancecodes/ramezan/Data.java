package ir.dancecodes.ramezan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.sql.Struct;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.lang.*;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Data {

	Context context;

	public Data(Context context) {
		this.context = context;
		if (!checkDataBase()) {
			CreateDB();
		}
	}

	// ------database------------------------------------
	String DATABASE_NAME = "Ramezan.db";
	String qry_create_data = "CREATE TABLE IF  NOT EXISTS tbl_data  "
			+ "(data_ID INTEGER PRIMARY KEY, data_name TEXT,data_address TEXT,data_father INTEGER,data_fave INTEGER,data_grp INTEGER);";

	public boolean checkDataBase() {
		boolean flag = false;
		File database = context.getApplicationContext().getDatabasePath(
				DATABASE_NAME);

		if (!database.exists()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	private class item {
		public String name;
		public String address;
		public int father;
		public int fav;
		public int grp;

		public item(String name, int father, int fav, String address, int grp) {
			this.address = address;
			this.father = father;
			this.fav = fav;
			this.name = name;
			this.grp = grp;
		}
	}

	private void savedata(SQLiteDatabase mydb) {
		item[] items = {
				
				new item("احکام روزه و ماه مبارک رمضان", 0, 0, "", 1),
				new item("ادعیه ماه رمضان", 0, 0, "", 1),
				new item("فضیلت های شب قدر", 0, 0, "file:///android_asset/www/ghadr/gh1.html", 0),
				new item("احاديث درباب ماه مبارك رمضان و روزه", 0, 0, "", 1),
				new item("اس ام اس ماه مبارك رمضان", 0, 0, "file:///android_asset/www/sms.html", 0),
		
				//hadis
				new item("1-پايه‏هاى اسلام ", 4, 0, "file:///android_asset/www/hadis/h1.html", 0),
				new item("2-فلسفه روزه ", 4, 0, "file:///android_asset/www/hadis/h2.html", 0),
				new item("3-روزه آزمون اخلاص ", 4, 0, "file:///android_asset/www/hadis/h3.html", 0),
				new item("4-روزه ياد آور قيامت", 4, 0, "file:///android_asset/www/hadis/h4.html", 0),
				new item("5-روزه زكات بدن ", 4, 0, "file:///android_asset/www/hadis/h5.html", 0),
				new item("6-روزه سپر آتش ", 4, 0, "file:///android_asset/www/hadis/h6.html", 0),
				new item("7-اهميت روزه", 4, 0, "file:///android_asset/www/hadis/h7.html", 0),
				new item("8-روزه نفس ", 4, 0, "file:///android_asset/www/hadis/h8.html", 0),
				new item("9-روزه واقعى ", 4, 0, "file:///android_asset/www/hadis/h9.html", 0),
				new item("10-برترين روزه ", 4, 0, "file:///android_asset/www/hadis/h10.html", 0),
				new item("11-روزه چشم و گوش ", 4, 0, "file:///android_asset/www/hadis/h11.html", 0),
				new item("12-روزه اعضا و جوارح ", 4, 0, "file:///android_asset/www/hadis/h12.html", 0),
				new item("13-روزه ناقص ", 4, 0, "file:///android_asset/www/hadis/h13.html", 0),
				new item("14-روزه بى ارزش ", 4, 0, "file:///android_asset/www/hadis/h14.html", 0),
				new item("15-روزه و صبر ", 4, 0, "file:///android_asset/www/hadis/h15.html", 0),
				new item("16-روزه و صدقه ", 4, 0, "file:///android_asset/www/hadis/h16.html", 0),
				new item("17-پاداش روزه ", 4, 0, "file:///android_asset/www/hadis/h17.html", 0),
				new item("18-جرعه نوشان بهشت", 4, 0, "file:///android_asset/www/hadis/h18.html", 0),
				new item("19-خوشا بحال روزه داران ", 4, 0, "file:///android_asset/www/hadis/h19.html", 0),
				new item("20-مژده به روزه‏داران ", 4, 0, "file:///android_asset/www/hadis/h20.html", 0),
				new item("21-شادى روزه دار ", 4, 0, "file:///android_asset/www/hadis/h21.html", 0),
				new item("22-بهشت و باب روزه‏داران ", 4, 0, "file:///android_asset/www/hadis/h22.html", 0),
				new item("23-دعاى روزه‏داران ", 4, 0, "file:///android_asset/www/hadis/h23.html", 0),
				new item("24-بهار مومنان ", 4, 0, "file:///android_asset/www/hadis/h24.html", 0),
				new item("25-روزه مستحبى ", 4, 0, "file:///android_asset/www/hadis/h25.html", 0),
				new item("26-روزه ماه رجب ", 4, 0, "file:///android_asset/www/hadis/h26.html", 0),
				new item("27-روزه ماه شعبان ", 4, 0, "file:///android_asset/www/hadis/h27.html", 0),
				new item("28-افطارى دادن(1) ", 4, 0, "file:///android_asset/www/hadis/h28.html", 0),
				new item("29-افطارى دادن (2) ", 4, 0, "file:///android_asset/www/hadis/h29.html", 0),
				new item("30-روزه خوارى ", 4, 0, "file:///android_asset/www/hadis/h30.html", 0),
				new item("31-رمضان ماه خدا ", 4, 0, "file:///android_asset/www/hadis/h31.html", 0),
				new item("32-رمضان ماه رحمت", 4, 0, "file:///android_asset/www/hadis/h32.html", 0),
				new item("33-فضيلت ماه رمضان ", 4, 0, "file:///android_asset/www/hadis/h33.html", 0),
				new item("34-اهميت ماه رمضان ", 4, 0, "file:///android_asset/www/hadis/h34.html", 0),
				new item("35-قرآن و ماه رمضان ", 4, 0, "file:///android_asset/www/hadis/h35.html", 0),
				new item("36-شب سرنوشت‏ساز ", 4, 0, "file:///android_asset/www/hadis/h36.html", 0),
				new item("37-برترى شب قدر ", 4, 0, "file:///android_asset/www/hadis/h37.html", 0),
				new item("38-تقدير اعمال ", 4, 0, "file:///android_asset/www/hadis/h38.html", 0),
				new item("39-احياء شب قدر ", 4, 0, "file:///android_asset/www/hadis/h39.html", 0),
				new item("40-زكات فطره ", 4, 0, "file:///android_asset/www/hadis/h40.html", 0),
				
				
				//ahkam
				new item("نيت", 1, 0, "file:///android_asset/www/AHKAM/AH1.html", 0),
				new item("چيزهايى كه روزه را باطل می‌كند", 1, 0, "file:///android_asset/www/AHKAM/AH2.html", 1),
					new item("خوردن و آشاميدن", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-1.html", 0),
					new item("دروغ بستن به خدا و پيغمبر(ص)", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-2.html", 0),
					new item("رساندن غبار غليظ به حلق", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-3.html", 0),
					new item("فرو بردن سر در آب", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-4.html", 0),
					new item("باقى ماندن بر جنابت و حيض تا اذان صبح", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-5.html", 0),
					new item("اماله كردن و قی کردن", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-6.html", 0),
					new item("", 47, 0, "file:///android_asset/www/AHKAM/ah2/AH2-7.html", 0),
				new item("احكام چيزهايى كه روزه را باطل می‌كند", 1, 0, "file:///android_asset/www/AHKAM/AH3.html", 0),
				new item("آنچه براى روزه‌دار مكروه است", 1, 0, "file:///android_asset/www/AHKAM/AH4.html", 0),
				new item("جاهايى كه قضا و كفاره واجب است", 1, 0, "file:///android_asset/www/AHKAM/AH5.html", 0),
				new item("كفاره روزه", 1, 0, "file:///android_asset/www/AHKAM/AH6.html", 0),
				new item("جاهايى كه فقط قضاى روزه واجب است", 1, 0, "file:///android_asset/www/AHKAM/AH7.html", 0),
				new item("احكام روزه قضا", 1, 0, "file:///android_asset/www/AHKAM/AH8.html", 0),
				new item("احكام روزه مسافر", 1, 0, "file:///android_asset/www/AHKAM/AH9.html", 0),
				new item("كسانى كه روزه بر آنها واجب نيست", 1, 0, "file:///android_asset/www/AHKAM/AH10.html", 0),
				new item("راه ثابت شدن اول ماه", 1, 0, "file:///android_asset/www/AHKAM/AH11.html", 0),
				new item("روزه‌هاى حرام و مكروه", 1, 0, "file:///android_asset/www/AHKAM/AH12.html", 0),
				new item("روزه‌هاى مستحب", 1, 0, "file:///android_asset/www/AHKAM/AH13.html", 0),
				new item("مواردى كه مستحب است انسان از كارهايى كه روزه را باطل می‌كند خوددارى نمايد", 1, 0, "file:///android_asset/www/AHKAM/AH14.html", 0),
				
				//doa
				new item("دعای روز اول تا دهم", 2, 0, "", 1),//67
				new item("دعای روز یازدهم تا بیستم", 2, 0, "", 1),//68
				new item("دعای روز بیست و یک تا سی ام", 2, 0, "", 1),//69
				
				new item("دعای روز اول ", 67, 0, "file:///android_asset/www/doa/d1.html", 0),
				new item("دعای روز دوم", 67, 0, "file:///android_asset/www/doa/d2.html", 0),
				new item("دعای روز سوم", 67, 0, "file:///android_asset/www/doa/d3.html", 0),
				new item("دعای روز چهارم", 67, 0, "file:///android_asset/www/doa/d4.html", 0),
				new item("دعای روز پنجم", 67, 0, "file:///android_asset/www/doa/d5.html", 0),
				new item("دعای روز ششم ", 67, 0, "file:///android_asset/www/doa/d6.html", 0),
				new item("دعای روز هفتم", 67, 0, "file:///android_asset/www/doa/d7.html", 0),
				new item("دعای روز هشتم", 67, 0, "file:///android_asset/www/doa/d8.html", 0),
				new item("دعای روز نهم", 67, 0, "file:///android_asset/www/doa/d9.html", 0),
				new item("دعای روز دهم ", 67, 0, "file:///android_asset/www/doa/d10.html", 0),
				
				new item("دعای روز يازدهم", 68, 0, "file:///android_asset/www/doa/d11.html", 0),
				new item("دعای روز دوازدهم", 68, 0, "file:///android_asset/www/doa/d12.html", 0),
				new item("دعای روز سيزدهم ", 68, 0, "file:///android_asset/www/doa/d13.html", 0),
				new item("دعای روز چهاردهم", 68, 0, "file:///android_asset/www/doa/d14.html", 0),
				new item("دعای روز پانزدهم", 68, 0, "file:///android_asset/www/doa/d15.html", 0),
				new item("دعای روز شانزدهم", 68, 0, "file:///android_asset/www/doa/d16.html", 0),
				new item("دعای روز هفدهم ", 68, 0, "file:///android_asset/www/doa/d17.html", 0),
				new item("دعای روز هجدهم", 68, 0, "file:///android_asset/www/doa/d18.html", 0),
				new item("دعای روز نوزدهم", 68, 0, "file:///android_asset/www/doa/d19.html", 0),
				new item("دعای روز بیستم ", 68, 0, "file:///android_asset/www/doa/d20.html", 0),
				
				new item("دعای روز بیست و یکم", 69, 0, "file:///android_asset/www/doa/d21.html", 0),
				new item("دعای روز بیست و دوم ", 69, 0, "file:///android_asset/www/doa/d22.html", 0),
				new item("دعای روز بیست و سوم", 69, 0, "file:///android_asset/www/doa/d23.html", 0),
				new item("دعای روز بیست و چهارم", 69, 0, "file:///android_asset/www/doa/d24.html", 0),
				new item("دعای روز بیست و پنجم", 69, 0, "file:///android_asset/www/doa/d25.html", 0),
				new item("دعای روز بیست و ششم", 69, 0, "file:///android_asset/www/doa/d26.html", 0),
				new item("دعای روز بیست و هفتم ", 69, 0, "file:///android_asset/www/doa/d27.html", 0),
				new item("دعای روز بیست و هشتم", 69, 0, "file:///android_asset/www/doa/d28.html", 0),
				new item("دعای روز بیست و نهم ", 69, 0, "file:///android_asset/www/doa/d29.html", 0),
				new item("دعای روز سی ام ", 69, 0, "file:///android_asset/www/doa/d30.html", 0)
				
		};

		for (item i : items) {
			mydb.execSQL("INSERT into tbl_data (data_name,data_father,data_fave,data_address,data_grp) "
					+ "VALUES ('"
					+ i.name
					+ "','"
					+ i.father
					+ "','"
					+ i.fav
					+ "','" + i.address + "','" + i.grp + "');");
		}
	}

	public boolean CreateDB() {
		boolean flag = false;
		try {
			SQLiteDatabase mydb = context.openOrCreateDatabase(DATABASE_NAME,
					Context.MODE_PRIVATE, null);
			mydb.execSQL(qry_create_data);
			savedata(mydb);
			mydb.close();
			flag = true;
		} catch (Exception e) {
			Toast.makeText(context.getApplicationContext(),
					"Error in creating table" + e.getMessage(),
					Toast.LENGTH_LONG).show();
			flag = false;
		}
		return flag;
	}

	public boolean RunQuery(String Query) {
		boolean flag = false;
		try {
			SQLiteDatabase mydb = context.openOrCreateDatabase(DATABASE_NAME,
					Context.MODE_PRIVATE, null);
			mydb.execSQL(Query);
			mydb.close();
			flag = true;
		} catch (Exception e) {
			Toast.makeText(context.getApplicationContext(),
					"Error encountered while dropping.", Toast.LENGTH_LONG)
					.show();
			flag = false;
		}
		return flag;
	}

	public String CreateQuery_data(int Query_Num, int data_ID,
			String data_name, String data_address, int data_father,
			int data_fave, int data_grp) {// tbl_data
		String qry = "";

		switch (Query_Num) {// Insert(0),delete(1),select(2),count(3),select_Name(4);
		case 0:// Insert
			qry = "insert into tbl_data (data_name, data_address,data_father,data_fave,data_grp) "
					+ "values ('"
					+ data_name
					+ "','"
					+ data_address
					+ "','"
					+ data_father + "','" + data_fave + "','" + data_grp + "')";
			break;
		case 1:// delete
			qry = "delete from tbl_data where activity_ID=" + data_ID;

			break;
		case 2:// select
			qry = "select * from tbl_data where data_father=" + data_father
					+ " and data_fave=" + data_fave;
			break;
		case 3:// select allbyfather
			qry = "select * from tbl_data where data_father=" + data_father;
			break;
		case 4:// select_addressbyname
			qry = "select data_address from tbl_data where data_name="
					+ data_name;
			break;
		case 5:// select_addressbyid
			qry = "select data_address from tbl_data where data_ID=" + data_ID;
			break;
		default:
			break;
		}
		return qry;
	}

	public static enum Query_data {
		Insert(0), delete(1), select(2), select_allbyfather(3), select_addressbyname(
				4), select_addressbyid(5);

		private Integer num;

		private Query_data(Integer num) {
			this.num = num;
		}

		public int toint() {
			return num;
		}

	}

	public ArrayList<String> RunQuery_getArray(String Query, Integer index) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			SQLiteDatabase mydb = context.openOrCreateDatabase(DATABASE_NAME,
					Context.MODE_PRIVATE, null);
			Cursor allrows = mydb.rawQuery(Query, null);
			if (allrows.moveToFirst()) {
				do {
					String value = allrows.getString(index);
					arr.add(value);
				} while (allrows.moveToNext());
			}
			mydb.close();

		} catch (Exception e) {
			Toast.makeText(context.getApplicationContext(), "Error",
					Toast.LENGTH_LONG).show();
		}
		return arr;
	}

	public String RunQuery_getString(String Query, Integer index) {
		String value = "";
		try {
			SQLiteDatabase mydb = context.openOrCreateDatabase(DATABASE_NAME,
					Context.MODE_PRIVATE, null);
			Cursor allrows = mydb.rawQuery(Query, null);
			if (allrows.moveToFirst()) {
				value = allrows.getString(index);
			}
			mydb.close();

		} catch (Exception e) {
			Toast.makeText(context.getApplicationContext(), "Error",
					Toast.LENGTH_LONG).show();
		}
		return value;
	}
	// --------------------------------------------------

}
