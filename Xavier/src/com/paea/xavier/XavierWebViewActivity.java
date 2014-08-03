package com.paea.xavier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

public class XavierWebViewActivity extends Activity {

  private static final String URL = "http://www.elizabethylin.com/ychacks/";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

    WebView webView = new WebView(this);
    webView.loadUrl(URL);

    setContentView(webView);
  }
}
