package com.example.mobileshoppingassistant;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getName();
	private List<User> mUserList = new ArrayList<User>();
	private ListView mListView;

	public class MyCustomAdapter extends ArrayAdapter<User> {

		public MyCustomAdapter(Context context, int textViewResourceId,
				List<User> list) {
			super(context, textViewResourceId, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Fix compile errors in this class

			final int rownumber = position;
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.row_users, parent, false);
			}

			row.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

				}

			});

			TextView list_name = (TextView) row.findViewById(R.id.list_name);

			TextView list_tagline = (TextView) row
					.findViewById(R.id.list_tagline);

			list_name.setText(mUserList.get(position).getFirstName());
			list_tagline.setText("" + mUserList.get(position).getPhoneNumber());

			int i = 0;

			ImageView imageView1 = (ImageView) row
					.findViewById(R.id.imageView1);

			return row;

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		this.populateUserData();

		mListView = (ListView) findViewById(R.id.listView);
		
		MyCustomAdapter adapter = new MyCustomAdapter(this, 0, mUserList);
		
		mListView.setAdapter(adapter);
		
		// TODO Although the following call to read will work 
		// PLease try to optimize the code using AsyncTask (refer to developer.android.com) 
		// http://developer.android.com/reference/android/os/AsyncTask.html
		// DEBUG it so that no exception is thrown
		read();

	}

	private void read() {
		String jsonFileUrl = "http://10.96.168.135/users.json";
		UsersResponse usersResponse = ObjectsReader.readUsersResponse(jsonFileUrl);
		for (User u : usersResponse.getUsers()) {
			Log.d(TAG, u.getFirstName());
			
		}
		
	}
	
	private void populateUserData() {
		User user = new User(1, "John", "416-252-3999");
		
		
		mUserList.add(user);

		user = new User(2, "Smith", "416-333-2232");

		mUserList.add(user);

		user = new User(3, "Kevin", "416-233-3434");

		mUserList.add(user);
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
