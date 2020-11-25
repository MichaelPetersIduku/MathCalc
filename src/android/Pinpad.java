package cordova.plugin.pinpad;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class echoes a string called from JavaScript.
 */
public class Pinpad extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("add")) {
            this.add(args, callbackContext);
            return true;
        } else if (action.equals("activate")) {
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
                int code = Integer.parseInt(args.getJSONObject(0).getString("code"));

                callbackContext.success("Activated successfully" + code);
            } catch (Exception e) {
                callbackContext.error("Some error occured \n" + e);
            }
        } else {
            callbackContext.error("Do not pass a null value");
        }
    }
}
