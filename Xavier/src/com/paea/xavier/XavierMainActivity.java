package com.paea.xavier;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.codebutler.android_websockets.WebSocketClient;
import com.paea.xavier.MyoUtil.MyoListener;

public class XavierMainActivity extends Activity implements MyoListener, OnClickListener {

  protected static final String TAG = XavierMainActivity.class.getSimpleName();

  private static final List<String> BEGIN_ACTIONS =
      Arrays.asList("fist", "thumb_to_pinky", "wave_out");

  private WebSocketClient wsClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    if (savedInstanceState == null) {
//      getFragmentManager().beginTransaction()
//          .add(R.id.container, new XavierMainFragment()).commit();
//    }
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    setContentView(R.layout.activity_main);

    getLayoutInflater().inflate(R.layout.fragment_main, (ViewGroup) findViewById(R.id.left_frame));
    getLayoutInflater().inflate(R.layout.fragment_main, (ViewGroup) findViewById(R.id.right_frame));

    Button beginButton = (Button) findViewById(R.id.begin_button);
    beginButton.setOnClickListener(this);

    wsClient = MyoUtil.createWebSocketClient(this);
    wsClient.connect();
  }

  @Override
  public void onPoseEvent(String poseType) {
    Log.e(TAG, "POSETYPE: " + poseType);
    if (BEGIN_ACTIONS.contains(poseType)) {
      wsClient.disconnect();
      startWebViewActivity();
    }
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.begin_button) {
      startWebViewActivity();
    }
  }

  private void startWebViewActivity() {
    startActivity(new Intent(this, XavierWebViewActivity.class));
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
