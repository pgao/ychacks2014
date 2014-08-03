package com.paea.xavier;

import javax.microedition.khronos.egl.EGLConfig;

import android.os.Bundle;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.EyeTransform;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

public class XavierCardboardActivity extends CardboardActivity
    implements CardboardView.StereoRenderer {
  
  private static final String TAG = XavierCardboardActivity.class.getName();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onDrawEye(EyeTransform arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onFinishFrame(Viewport arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onNewFrame(HeadTransform arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onRendererShutdown() {
    // TODO Auto-generated method stub
  }

  @Override
  public void onSurfaceChanged(int arg0, int arg1) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onSurfaceCreated(EGLConfig arg0) {
    // TODO Auto-generated method stub
  }
}
