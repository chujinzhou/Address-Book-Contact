package com.example.addressbookcontact;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	//intent = an operation should be performed
	Intent intent;
	TextView contactId;
	
	//the object that allows me to manipulate the database
	
	DBTools dbTools = new DBTools(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<HashMap<String,String>> contactList = new ArrayList<HashMap <String,String>>();
		
		contactList = dbTools.getAllContacts();
		
		if(contactList.size() !=0){
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					contactId =(TextView) view.findViewById(R.id.contactId);
					String contactIdValue = contactId.getText().toString();
					Intent theIntent = new Intent(getApplication(),EditContact.class);
					theIntent.putExtra("contactId", contactIdValue);
					startActivity(theIntent);
					
				}
				
				
			});
			//yg talk antara database dan listView
			ListAdapter adapter = new SimpleAdapter(
					MainActivity.this, contactList, R.layout.contact_entry, new String[]
							{"contactId", "lastName","firstName"},new int[]{});
			
			setListAdapter(adapter);
		}
	}

	public void showAddContact(View view){
		Intent theIntent = new Intent(getApplication(), NewContact.class);
		startActivity(theIntent);
	}

}
