package com.paea.xavier;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.codebutler.android_websockets.WebSocketClient;

public class WebSocketUtil {
  
  private static final String TAG = WebSocketUtil.class.getName();
  private static final String URI_STRING = "ws://172.20.13.6:7204/myo/1";

  public static WebSocketClient createWebSocketClient() {
    List<BasicNameValuePair> extraHeaders = Arrays.asList(
        new BasicNameValuePair("Cookie", "session=abcd")
    );

    WebSocketClient client = new WebSocketClient(
        URI.create(URI_STRING), new WebSocketClient.Listener() {
      @Override
      public void onConnect() {
        Log.d(TAG, "Connected!");
      }

      @Override
      public void onMessage(String message) {
        Log.d(TAG, String.format("Got string message! %s", message));
      }

      @Override
      public void onMessage(byte[] data) {
        Log.d(TAG, "Got binary message!");
      }

      @Override
      public void onDisconnect(int code, String reason) {
        Log.d(TAG, String.format("Disconnected! Code: %d Reason: %s", code, reason));
      }

      @Override
      public void onError(Exception error) {
        Log.e(TAG, "Error!", error);
      }
    }, extraHeaders);
    
    return client;
  }
}
