package br.exemplorssatom;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;

import java.util.List;



public class MyAdapter extends BaseAdapter {
	Context ctx;
	List<SyndEntry> list;
	
	public MyAdapter(Context ctx, List<SyndEntry> list){
		this.ctx = ctx;
		this.list = list;
	}
	


	@Override
	public int getCount() {
		return list.size();
	}



	@Override
	public Object getItem(int position) {
		return list.get(position);
	}



	@Override
	public long getItemId(int position) {
		return position;
	}



	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.rss, null);
		
		TextView tv1 = (TextView) view.findViewById(R.id.textView1);
		tv1.setText(list.get(position).getTitle());
		
		TextView tv2 = (TextView) view.findViewById(R.id.textView2);
		tv2.setText(list.get(position).getAuthor());
		
		TextView tv3 = (TextView) view.findViewById(R.id.textView3);
		tv3.setText(list.get(position).getPublishedDate().toString());
		
		return view;
	}



	
}
