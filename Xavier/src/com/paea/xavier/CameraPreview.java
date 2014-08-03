package com.paea.xavier;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView
    implements SurfaceHolder.Callback, Camera.PreviewCallback, Camera.PictureCallback {

  private static final String TAG = CameraPreview.class.getName();

  private final Context context;

  private SurfaceHolder holder;
  private Camera camera;

  private boolean safeToTakePicture;

  public CameraPreview(Context context) {
    super(context);
    this.context = context;

    holder = getHolder();
    holder.addCallback(this);
  }

  public CameraPreview(Context context, AttributeSet attrs) {
    this(context);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    camera = Camera.open();
    try {
      camera.setPreviewDisplay(holder);
      camera.setPreviewCallback(this);
    } catch (IOException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    camera.setPreviewCallback(null);
    camera.stopPreview();
    camera.release();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    camera.setDisplayOrientation(getNeededRotation());
    camera.startPreview();
  }

  @Override
  public void onPreviewFrame(byte[] data, Camera camera) {
    safeToTakePicture = true;
    int format = camera.getParameters().getPreviewFormat();
//    Log.e(TAG, "camera preview frame image format: " + format);
    // Do stuff with frame data here, if this gets long maybe move it to its own class
  }

  @Override
  public void onPictureTaken(byte[] data, Camera camera) {
    Log.e(TAG, "picture data available");
    // this is supposedly jpeg image?? what
    camera.startPreview();
  }

  public void capture() {
    Log.e(TAG, "capture!");
    if (safeToTakePicture) {
      camera.takePicture(null, null, this);
      safeToTakePicture = false;
    }
  }

  private int getNeededRotation() {
    Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
    Camera.getCameraInfo(CameraInfo.CAMERA_FACING_BACK, info);

    // angle by which the display is rotated to be upright
    int rotation = ((Activity) context).getWindowManager().getDefaultDisplay().getRotation();

    // angle that the camera image needs to be rotated to offset camera orientation
    int cameraOrientationOffset = info.orientation;

    int degrees = 0;
    switch (rotation) {
    case Surface.ROTATION_0:
      degrees = 0;
      break;
    case Surface.ROTATION_90:
      degrees = 90;
      break;
    case Surface.ROTATION_180:
      degrees = 180;
      break;
    case Surface.ROTATION_270:
      degrees = 270;
      break;
    }

    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
      // camera's cw is our ccw
      cameraOrientationOffset = 360 - cameraOrientationOffset;
    }

    return ((cameraOrientationOffset - degrees + 360) % 360);
  }
}
