package cordova.plugin.pinpad.PinpadClass;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private CallbackContext mCallbackContext;
    public MyBroadcastReceiver(CallbackContext callbackContext) {
        this.mCallbackContext = callbackContext;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction() != null) {
            String s = intent.getAction();
            if (s.equals("com.esl.paypadlib")) {

                String result = intent.getStringExtra("response");
                String[] resultarray = intent
                        .getStringArrayExtra("responsearray");
                String reversalResult = intent.getStringExtra("reversalResult");
                String encryptedMessage = intent.getStringExtra("encryptedMessage");

                if (result.equals("activatecomplete")) {
                    Toast.makeText(context,
                            "Activated Successfully", Toast.LENGTH_LONG)
                            .show();
                    this.mCallbackContext.success("Activated successfully");
                } else if (result.equals("invalidcode")) {
                    Toast.makeText(context,
                            "Invalid Activation Code", Toast.LENGTH_LONG)
                            .show();
                    this.mCallbackContext.error("Invalid Activation Code");
                } else if (result.equals("failedactivation")) {
                    Toast.makeText(context,
                            "Activation not Successful", Toast.LENGTH_LONG)
                            .show();
                    this.mCallbackContext.error("Activation was not successful");
                } else if (result.equals("connected")) {
//						mProgressDialog.dismiss();
                    Toast.makeText(context,
                            "Device is connected", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("startInitializeprogress")) {
                    Toast.makeText(context, "Initializing...", Toast.LENGTH_SHORT).show();

                } else if (result.equals("Initializecomplete")) {
                    Toast.makeText(context,
                            "Initialized Successfully", Toast.LENGTH_LONG)
                            .show();
                    this.mCallbackContext.success("Initialized Successfully");
                } else if (result.equals("initializenotcomplete")) {
                    Toast.makeText(context,
                            "Initialization not Successful",
                            Toast.LENGTH_LONG).show();
                    this.mCallbackContext.error("Initialization not Successful");
                } else if (result.equals("pinpadProcessing")) {
                    Toast.makeText(context,
                            "Processing, please wait...",
                            Toast.LENGTH_LONG).show();
                } else if (result.equals("enterPIN")) {
                    Toast.makeText(context,
                            "Enter PIN",
                            Toast.LENGTH_LONG).show();


                } else if (result.equals("PINentered")) {
                    Toast.makeText(context,
                            "PIN entered",
                            Toast.LENGTH_SHORT).show();

                } else if (result.equals("nibssProcessing")) {
                    Toast.makeText(context,
                            "Processing Transaction",
                            Toast.LENGTH_LONG).show();
                } else if (result.equals("errorTranx")) {
                    Toast.makeText(context,
                            "Some error occured", Toast.LENGTH_LONG)
                            .show();
                    this.mCallbackContext.error("Some error occured");
                } else if (result.equals("transactionresponse")) {
                    /*
                     * The returned String array has the following element
                     * with the indices below 0-Response Code 1-Response
                     * 2-Terminal ID 3-PAN 4-Card holder name 5-Date
                     * 6-Amount 7-Transaction ID 8-RRN
                     */
                    for (int i = 0; i < resultarray.length; i++) {
                        Toast.makeText(context,
                                resultarray[i], Toast.LENGTH_SHORT).show();
                    }
                    this.mCallbackContext.success(resultarray[1]);
                } else if (result.equals("reversal")) {

                    Toast.makeText(context,
                            reversalResult, Toast.LENGTH_SHORT).show();
                    this.mCallbackContext.error(reversalResult);

                } else if (result.equals("fcmbresponse")) {

//                        byte[] dectryptArray = encryptedMessage.getBytes();
//                        byte[] decarray = Base64.decodeBase64(dectryptArray);
//                        String message = null;
//                        try {
//                            message = new String(decarray,"UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//                        Log.i("Enc:", encryptedMessage);
//                        Log.i("Msg:", message);
//
//                        Toast.makeText(getApplicationContext(),
//                                message, Toast.LENGTH_LONG).show();

                }
            }
        }

    }
}
