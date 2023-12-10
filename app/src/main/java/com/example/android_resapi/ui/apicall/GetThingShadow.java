package com.example.android_resapi.ui.apicall;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.android_resapi.R;
import com.example.android_resapi.httpconnection.GetRequest;

public class GetThingShadow extends GetRequest {
    final static String TAG = "AndroidAPITest";
    String urlStr;
    public GetThingShadow(Activity activity, String urlStr) {
        super(activity);
        this.urlStr = urlStr;
    }

    @Override
    protected void onPreExecute() {
        try {
            Log.e(TAG, urlStr);
            url = new URL(urlStr);

        } catch (MalformedURLException e) {
            Toast.makeText(activity,"URL is invalid:"+urlStr, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            activity.finish();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        Map<String, String> state = getStateFromJSONString(jsonString); //getStateFromJSONString의 output이 state에 할당된다

        //TextView 가져오기
        TextView reported_temp = activity.findViewById(R.id.reported_temp);
        TextView reported_humidity = activity.findViewById(R.id.reported_humidity);
        TextView reported_sm = activity.findViewById(R.id.reported_sm);
        TextView reported_light = activity.findViewById(R.id.reported_light);

        //TextView에 데이터 설정
        //state라는 Map에 저장된 데이터를 사용하여 UI의 TextView에 각각의 값을 설정
        reported_temp.setText(state.get("reported_temperature"));//Map에서 "temperature"이라는 키에 해당하는 값을 반환
        reported_humidity.setText(state.get("reported_humidity"));
        reported_sm.setText(state.get("reported_sm"));
        reported_light.setText(state.get("reported_light"));

        TextView desired_temp = activity.findViewById(R.id.desired_temp);
        /*TextView desired_humidity = activity.findViewById(R.id.desired_humidity);
        TextView desired_sm = activity.findViewById(R.id.desired_sm);
        TextView desired_light = activity.findViewById(R.id.desired_light);*/
        desired_temp.setText(state.get("temperature"));
        /*desired_humidity.setText(state.get("humidity"));
        desired_sm.setText(state.get("soil_moisture"));
        desired_light.setText(state.get("light"));*/


    }



    protected Map<String, String> getStateFromJSONString(String jsonString) {
        Map<String, String> output = new HashMap<>();
        try {
            // 처음 double-quote와 마지막 double-quote 제거
            jsonString = jsonString.substring(1,jsonString.length()-1);
            // \\\" 를 \"로 치환
            jsonString = jsonString.replace("\\\"","\"");
            Log.i(TAG, "jsonString="+jsonString);
            JSONObject root = new JSONObject(jsonString);
            JSONObject state = root.getJSONObject("state");
            JSONObject reported = state.getJSONObject("reported");
            String tempValue = reported.getString("temperature");
            String humidity = reported.getString("humidity");
            String light = reported.getString("light");
            String sm = reported.getString("soil_moisture");

            output.put("reported_temperature", tempValue);//각각의 데이터를 output이라는 Map 자료구조에 저장
            output.put("reported_humidity",humidity); //reported_humdity가 키값
            output.put("reported_sm",sm);
            output.put("reported_light",light);

            JSONObject desired = state.getJSONObject("desired");
            String desired_tempValue = desired.getString("temperature");
            /*String desired_humidity = desired.getString("humidity");
            String desired_light = desired.getString("light");
            String desired_sm = desired.getString("soil_moisture");*/
            output.put("desired_temperature", desired_tempValue);
            /*output.put("desired_humidity", desired_humidity);
            output.put("desired_light", desired_light);
            output.put("desired_sm", desired_sm);*/


        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    } //원래 이게 onPostExecute 보다 아래
}
