package com.example.listviewimage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ListView l;
	
	int images[] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5};
	String imageTitle[] = {"Pink","Blue","Yellow","Red","Grey"};
	String imageDescription[] = {"Bottle","Flower","Blood","Rose","Pen"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*	Resources res = getResources();
			imageTitle = res.getStringArray(R.array.title); // To get titles from (Resource folder)string.xml
			imageDescription = res.getStringArray(R.array.descriptions); //To get des from string.xml */
		
		l = (ListView)findViewById(R.id.listview); // activity_main.xml list view is attached with java code
		myAdapter adapter = new myAdapter(this, imageTitle, images, imageDescription);
		l.setAdapter(adapter);
	}
}

//This is the custom adapter to fetch the data source(imageview,2 textview) where c is creating view ,image with 1 title string array
class myAdapter extends ArrayAdapter<String>
{
	Context context;
	int[] images;
	String titleArray[];
	String descriptionArray[];


	public myAdapter(Context c,String titles[],int img[],String description[]) //taking title and image array as a parameter
	{
		super(c,R.layout.single_row,R.id.textview2,description);
		this.context = c;
		this.images = img;
		this.titleArray = titles;
		this.descriptionArray = description;
	}
	
	//Layout inflater is used for convert xml object to java object
	//row is a object which contains Relative layout (parent of the image and text view)
	// by using row object we are fetching imageview and textview
	//getview menthod is called each time for each row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.single_row,parent,false);
		
		
		ImageView myImage = (ImageView) row.findViewById(R.id.imageview);
		TextView myTitle = (TextView) row.findViewById(R.id.textview1);
		TextView myDescription = (TextView) row.findViewById(R.id.textview2);
		myImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
		myImage.setPadding(10, 10, 10, 10);
	    
		myImage.setImageResource(images[position]);
		myTitle.setText(titleArray[position]); 	
		myDescription.setText(descriptionArray[position]); 
		
		return row ;// this is the final view we want
	}
}