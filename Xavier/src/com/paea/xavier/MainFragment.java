package com.paea.xavier;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

  private static final String TAG = MainFragment.class.getName();

  public MainFragment() {}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater
        .inflate(R.layout.fragment_main, container, false);

    Button button = (Button) rootView.findViewById(R.id.cardboard_button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.e(TAG, "Hello!");
      }
    });

    return rootView;
  }
}
