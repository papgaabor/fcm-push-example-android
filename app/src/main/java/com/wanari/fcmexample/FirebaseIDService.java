package com.wanari.fcmexample;

import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by Papi on 2017.06.21..
 */

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";
    private static Set<TokenRefreshListener> listeners = new HashSet<>();

    public static void addTokenRefreshListener(TokenRefreshListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);

        for (TokenRefreshListener listener : listeners) {
            listener.onTokenRefresh(refreshedToken);
        }
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }

}
