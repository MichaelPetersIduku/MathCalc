package cordova.plugin.pinpad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.paypad.cardreader.facade.PinpadFacade;
import com.paypad.impl.Paypad;
import cordova.plugin.pinpad.MyBroadcastReceiver;

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
        if (action.equals("add")) {
            this.add(args, callbackContext);
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

    private void add(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                int num1 = Integer.parseInt(args.getJSONObject(0).getString("num1"));
                int num2 = Integer.parseInt(args.getJSONObject(0).getString("num2"));

                callbackContext.success("" + (num1 + num2));
            } catch (Exception e) {
                callbackContext.error("Some error occured \n" + e);
            }
        } else {
            callbackContext.error("Do not pass a null value");
        }
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
}