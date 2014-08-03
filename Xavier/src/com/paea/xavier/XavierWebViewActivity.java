package com.paea.xavier;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.codebutler.android_websockets.WebSocketClient;
import com.paea.xavier.MyoUtil.MyoListener;

public class XavierWebViewActivity extends Activity {

  protected static final String TAG = XavierWebViewActivity.class.getSimpleName();

  private static final String URL = "http://www.elizabethylin.com/ychacks/";

  private WebView webView;
  private WebSocketClient wsClient;

  private class WebClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
      webView.loadUrl(url);
      return true;
    }

    @Override
    public void onPageFinished(WebView webView, String url) {
//      webView.loadUrl("javascript:changeBackground()");
    }

    public void scrollRight() {
    	webView.loadUrl("javascript:scrollRight()");
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    wsClient = MyoUtil.createWebSocketClient(new MyoListener() {
        @Override
        public void onPoseEvent(String poseType) {
          Log.e(TAG, "Lol did this actually work, got " + poseType);
          if (poseType.equals("wave_out")) {
//          	scrollRight();
          }
        }
    });
    wsClient.connect();

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    setContentView(R.layout.webview_layout);

    webView = (WebView) findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebClient());
    webView.loadUrl(URL);
    webView.setBackgroundColor(0x00000000);

    FrameLayout container = (FrameLayout) findViewById(R.id.camera_container);
    CameraPreview preview = new CameraPreview(this);
    container.addView(preview);
  }
}
