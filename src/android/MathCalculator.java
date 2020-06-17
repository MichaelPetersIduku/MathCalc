package cordova.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("add")) {
            this.add(args, callbackContext);
            return true;
        }
        else if (action.equals("subtract")) {
            this.subtract(args, callbackContext);
            return true;
        }
        return false;
    }

    private void add(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                int num1 = Integer.parseInt(args.getJSONObject(0).getString("num1"));
                int num2 = Integer.parseInt(args.getJSONObject(0).getString("num2"));

                callbackContext.success("" + (num1+num2));
            }
            catch(Exception e) {
                callbackContext.error("Some error occured \n"+e);
            }
        }
        else {
            callbackContext.error("Do not pass a null value");
        }
    }

    private void subtract(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                int num1 = Integer.parseInt(args.getJSONObject(0).getString("num1"));
                int num2 = Integer.parseInt(args.getJSONObject(0).getString("num2"));

                callbackContext.success("" + (num1-num2));
            }
            catch(Exception e) {
                callbackContext.error("Some error occured \n"+e);
            }
        }
        else {
            callbackContext.error("Do not pass a null value");
        }
    }
}
