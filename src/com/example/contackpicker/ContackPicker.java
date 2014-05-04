package com.example.contackpicker;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

public class ContackPicker extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contack_picker);
	final Cursor c=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	String[] from=new String[]{Contacts.DISPLAY_NAME_PRIMARY};
	int[] to=new int[] {R.id.itemTextView};
	SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to);
	ListView lv=(ListView)findViewById(R.id.contactListView);
	lv.setAdapter(adapter);
	
	lv.setOnItemClickListener(new ListView.OnItemClickListener ()
	{
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			c.moveToPosition(position);
			int rowId=c.getInt(c.getColumnIndexOrThrow("_id"));
			Uri outIRI =
					ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
			Intent outData=new Intent();
			outData.setData(outIRI);
			setResult(Activity.RESULT_OK, outData);
			finish();
		}
	});
	}
	
}

