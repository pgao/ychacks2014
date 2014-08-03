package com.paea.xavier;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.vrtoolkit.cardboard.samples.treasurehunt.MainActivity;

public class XavierMainFragment extends Fragment {

  private static final String TAG = XavierMainFragment.class.getName();

  public XavierMainFragment() {}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater
        .inflate(R.layout.fragment_main, container, false);

    Button cardboardButton = (Button) rootView.findViewById(R.id.cardboard_button);
    cardboardButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(getActivity(), MainActivity.class));
      }
    });

    Button cameraButton = (Button) rootView.findViewById(R.id.camera_button);
    cameraButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(getActivity(), XavierCameraActivity.class));
      }
    });

    return rootView;
  }
}
