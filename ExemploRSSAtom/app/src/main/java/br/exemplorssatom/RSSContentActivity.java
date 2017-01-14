package br.exemplorssatom;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;



public class RSSContentActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsscontent);
		
		TextView tv = (TextView) findViewById(R.id.tv);
		
		String html = Html.fromHtml(getIntent().getExtras().getString("rss")).toString();
		tv.setText(html);
	}




}
