package br.exemplorssatom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContent;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView lv;
	private MyAdapter adapter;
	private List<SyndEntry> list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		list = new ArrayList<SyndEntry>();
		
		adapter = new MyAdapter(this, list);
		
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				readFeed("http://www.mnot.net/blog/index.atom");
			}
		});
	}
	




	private void readFeed(final String urlFeed){
		new Thread(){
			public void run(){
				URL url;
				try {
					url = new URL(urlFeed);
					SyndFeedInput input = new SyndFeedInput();
					SyndFeed feed = input.build(new XmlReader(url));
					List entradas = feed.getEntries();
					
					Iterator it = entradas.iterator();
					
					while(it.hasNext()){
						SyndEntry aux = (SyndEntry) it.next();
						list.add(aux);
					}
					
					runOnUiThread(new Runnable(){
						public void run(){
							adapter.notifyDataSetChanged();
						}
					});
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FeedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		
	}
	





	@Override
	public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
		Intent it = new Intent(this, RSSContentActivity.class);
		it.putExtra("rss", getContentFeed(list.get(position)));
		startActivity(it);
	}




	
	private String getContentFeed(SyndEntry se){
		StringBuilder sb = new StringBuilder();
		
		if(se.getContents() != null){
			List contents = se.getContents();
			
			Iterator it = contents.iterator();
			
			while(it.hasNext()){
				SyndContent aux = (SyndContent) it.next();
				sb.append(aux.getValue());
			}
		}
		
		return(sb.toString());
	}





}
