package com.paea.xavier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

public class XavierCameraActivity extends Activity {

  private static final String TAG = XavierCameraActivity.class.getName();

  private CameraPreview preview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

    setContentView(R.layout.camera_layout);

    FrameLayout container = (FrameLayout) findViewById(R.id.camera_container);
    Button captureButton = (Button) findViewById(R.id.capture_button);

    preview = new CameraPreview(this);
    container.addView(preview);

    captureButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        preview.capture();
      }
    });

//    captureButton.setVisibility(View.GONE);
  }
}
