package com.paea.xavier;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class XavierCameraActivity extends Activity {    
  private CameraPreview preview;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
  
    preview = new CameraPreview(this);
    setContentView(preview);
  }
}
