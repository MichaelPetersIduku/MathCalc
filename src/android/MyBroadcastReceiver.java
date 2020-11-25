package cordova.plugin.pinpad;

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
                }
            }
        }

    }
}
