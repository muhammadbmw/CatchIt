package com.breezemaxweb.catchit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Insights extends AppCompatActivity {
    WebView simpleWebView;
    String googleUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);
        this.setTitle("INSIGHTS");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        Intent intent = getIntent();

        if(intent.hasExtra("query")){
            String query = intent.getStringExtra("query");
            googleUrl = "https://www.google.ca/search?q="+query;

        }
        else if(intent.hasExtra("address")){
            String address = intent.getStringExtra("address");
            googleUrl = "https://www.google.ca/maps?q="+address;
        }
        simpleWebView.getSettings().setLoadsImagesAutomatically(true);
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(googleUrl); // load a web page in a web view
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(intent);
                finish();
                return true;
            }
            if (url.contains("intent://")) {
                url = Uri.decode(url);
                String bkpUrl = null;
                Pattern regexBkp = Pattern.compile("intent://(.*?)#");
                Matcher regexMatcherBkp = regexBkp.matcher(url);
                if (regexMatcherBkp.find()) {
                    bkpUrl = regexMatcherBkp.group(1);
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+bkpUrl));
                    startActivity(myIntent);

                    return true;
                }
                else

                    return false;
            }
           view.loadUrl(url);
            return true;

        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            if(simpleWebView.canGoBack()){
                simpleWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.back){
            simpleWebView.goBack();
            return true;

        }
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
            // NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
