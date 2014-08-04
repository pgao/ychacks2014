package com.paea.xavier;

import java.util.Arrays;
import java.util.List;

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

public class XavierWebViewActivity extends Activity implements MyoListener {

  protected static final String TAG = XavierWebViewActivity.class.getSimpleName();

  private static final String URL = "http://www.elizabethylin.com/ychacks/";

  private static final List<String> BEGIN_ACTIONS =
      Arrays.asList("thumb_to_pinky", "fingers_spread");

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

//    public void scrollRight() {
//    	webView.loadUrl("javascript:scrollRight()");
//    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    setContentView(R.layout.webview_layout);

    FrameLayout container = (FrameLayout) findViewById(R.id.camera_container);
    CameraPreview preview = new CameraPreview(this);
    container.addView(preview);

    webView = (WebView) findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebClient());
//    webView.loadUrl(URL);
    webView.setBackgroundColor(0x00000000);


    wsClient = MyoUtil.createWebSocketClient(this);
    wsClient.connect();
//    wsClient = MyoUtil.createWebSocketClient(new MyoListener() {
//        @Override
//        public void onPoseEvent(String poseType) {
//          Log.e(TAG, "Lol did this actually work, got " + poseType);
//        }
//    });
//    wsClient.connect();
  }

  @Override
  public void onPoseEvent(String poseType) {
    Log.e(TAG, "Lol did this actually work, got " + poseType);
    if (BEGIN_ACTIONS.contains(poseType)) {
      Log.e(TAG, "LOADING WEBVIEW!!!!!!!!!!!!!!!");
      XavierWebViewActivity.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          webView.loadUrl(URL);
          wsClient.disconnect();
        }
      });
    }
  }
}
