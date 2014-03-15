package com.example.addressbookcontact;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditContact extends Activity {
	EditText firstName;
	EditText lastName;
	EditText phoneNumber;
	EditText emailAddress;
	EditText homeAddress;
	
	DBTools dbtools = new DBTools(this);
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_contact);
		
		firstName = (EditText) findViewById(R.id.firstNameEditText);
		lastName = (EditText) findViewById(R.id.lastNameEditText);
		phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
		emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
		homeAddress = (EditText) findViewById(R.id.homeAddressEditText);
		
		Intent theIntent = getIntent();
		String contactId = theIntent.getStringExtra("contactId");
		HashMap<String,String> contactList = dbtools.getContactInfo(contactId);
		
		if(contactList.size() != 0){
			firstName.setText(contactList.get("firstName"));
			lastName.setText(contactList.get("lastName"));
			phoneNumber.setText(contactList.get("phoneNumber"));
			emailAddress.setText(contactList.get("emailAddress"));
			homeAddress.setText(contactList.get("homeAddress"));
		}
		
		
		
	}
	
	public void editContact(View view){
		HashMap<String,String> queryValuesMap = new HashMap<String,String>();
		
		firstName = (EditText) findViewById(R.id.firstNameEditText);
		lastName = (EditText) findViewById(R.id.lastNameEditText);
		phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
		emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
		homeAddress = (EditText) findViewById(R.id.homeAddressEditText);
		
		Intent theIntent = getIntent();
		String contactId = theIntent.getStringExtra("contactId");
		
		queryValuesMap.put("contactId", contactId);
		queryValuesMap.put("firstName", firstName.getText().toString());
		queryValuesMap.put("lastName", lastName.getText().toString());
		queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
		queryValuesMap.put("emailAddress", emailAddress.getText().toString());
		queryValuesMap.put("homeAddress", homeAddress.getText().toString());
		
		dbtools.updateContact(queryValuesMap);
		
		this.callMainActivity(view);
		
	}
	
	public void removeContact(View view){
		Intent theIntent = getIntent();
		String contactId = theIntent.getStringExtra("contactId");
		dbtools.deleteContact(contactId);
		this.callMainActivity(view);
	}

	private void callMainActivity(View view) {
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		startActivity(objIntent);
		
	}
}
