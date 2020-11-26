package cordova.plugin.pinpad.PinpadClass;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.paypad.cardreader.facade.PinpadFacade;
import com.paypad.impl.Paypad;

/**
 * This class echoes a string called from JavaScript.
 */
public class Pinpad extends CordovaPlugin {

    PinpadFacade pinpadFacade;
    Handler handler;
    Paypad paypad;
    MyBroadcastReceiver myBroadcastReceiver;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        paypad = new Paypad(cordova.getContext());
        handler = new Handler();
        pinpadFacade = new PinpadFacade(cordova.getContext());
        if (action.equals("initialize")) {
            this.initialize(args, callbackContext);
            return true;
        } else if (action.equals("activate")) {
            myBroadcastReceiver = new MyBroadcastReceiver(callbackContext);
            IntentFilter filter = new IntentFilter("com.esl.paypadlib");
            cordova.getContext().registerReceiver(myBroadcastReceiver, filter);
            this.activate(args, callbackContext);
            return true;
        }
        return false;
    }

    private void initialize(JSONArray args, CallbackContext callbackContext) {
        new Initialize().execute(cordova.plugin.pinpad.activities.ActivityClass.DeviceActivity.class);
    }

    private void activate(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                String code = args.getJSONObject(0).getString("code");
                if (code.equals("")) {
                    callbackContext.error("Do not pass an empty string");
                } else {
                    new Activate().execute(code);
                }
            } catch (Exception e) {
                callbackContext.error("Some error occured \n" + e);
            }
        } else {
            callbackContext.error("Do not pass a null value");
        }
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        cordova.getContext().unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        cordova.getContext().registerReceiver(myBroadcastReceiver, new IntentFilter("com.esl.paypadlib"));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        pinpadFacade.onActivityResult(requestCode, resultCode, intent);
    }

    class Activate extends AsyncTask<String, String, Long> {

        @Override
        protected Long doInBackground(String... params) {
            String regActivationCode = params[0];

            paypad.activate(regActivationCode);

            return null;

        }

        // This is executed in the context of the main GUI thread
        protected void onPostExecute(Long result) {

        }
    }

    class Initialize extends AsyncTask<Class<?>, String, Long> {

        @Override
        protected Long doInBackground(Class<?>... params) {
            Class<?> classactivity = params[0];
            paypad.initialize(classactivity);

            return null;

        }

        // This is executed in the context of the main GUI thread
        protected void onPostExecute(Long result) {

        }
    }
}