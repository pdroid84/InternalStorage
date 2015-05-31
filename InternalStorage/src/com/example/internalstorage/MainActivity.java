package com.example.internalstorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	EditText et1,et2,et3,et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void saveData(View v) {
		// TODO Auto-generated method stub
    	et1 = (EditText) findViewById(R.id.editText1);
    	et2 = (EditText) findViewById(R.id.editText2);
    	String s1 = et1.getText().toString();
    	String s2 = et2.getText().toString();
    	s1 = s1+" ";
    	FileOutputStream fo = null;
    	File fdir=null;
    	fdir = getFilesDir();
    	try {
			fo = openFileOutput("MyFile.txt", Context.MODE_PRIVATE);
			fo.write(s1.getBytes());
			fo.write(s2.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Toast.makeText(this, "File successfully saved in location.."+fdir+"/MyFile.txt", Toast.LENGTH_LONG).show();
    	
	}
    public void loadData(View v) {
		// TODO Auto-generated method stub
    	et3 = (EditText) findViewById(R.id.editText3);
    	et4 = (EditText) findViewById(R.id.editText4);
    	FileInputStream fi = null;
    	int read = -1;
    	try {
			fi = openFileInput("MyFile.txt");
			StringBuffer bfr = new StringBuffer();
			try {
				while((read=fi.read())!= -1)
				{
					bfr.append((char)(read));
				}
				Log.d("DEB",bfr.toString());
				String txt1 = bfr.substring(0,bfr.indexOf(" "));
				String txt2 = bfr.substring(bfr.indexOf(" ")+1);
				et3.setText(txt1);
				et4.setText(txt2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Toast.makeText(this, "Data loaded successflly...", Toast.LENGTH_LONG).show();
	}
}
