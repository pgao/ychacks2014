package com.paea.xavier;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.codebutler.android_websockets.WebSocketClient;

public class WebSocketUtil {
  
  private static final String TAG = WebSocketUtil.class.getName();
  private static final String URI_STRING = "ws://yc-2014-myo.herokuapp.com";

  public static WebSocketClient createWebSocketClient(final MyoListener myoListener) {
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
        try {
          // Look for:
          // [ "event", { "type" : "pose", "pose" : "<pose_value>" } ]
          JSONArray json = new JSONArray(message);
          String messageType = (String) json.get(0);
          if (messageType.equals("event")) {
            JSONObject eventData = (JSONObject) json.get(1);
            String eventType = eventData.getString("type");
            if (eventType.equals("pose")) {
              String pose = eventData.getString("pose");
              Log.d(TAG, pose);
              myoListener.onPoseEvent(pose);
            }
          }
        } catch (JSONException e) {
          return;
        }

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

  public interface MyoListener {
    public void onPoseEvent(String poseType);
  }
}
