package com.example.safedriving;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyft.lyftbutton.LyftButton;
import com.lyft.lyftbutton.RideParams;
import com.lyft.lyftbutton.RideTypeEnum;
import com.lyft.networking.ApiConfig;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.android.rides.RideRequestButton;

public class Uber_lyft extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        View v = inflater.inflate(R.layout.fragment_uber_lyft, container, false);


        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("_jY1PXnhmlqjWz1scvPrurbmCAL_GSNE")
                // required for enhanced button features
                .setServerToken("JA.VUNmGAAAAAAAEgASAAAABwAIAAwAAAAAAAAAEgAAAAAAAAH4AAAAFAAAAAAADgAQAAQAAAAIAAwAAAAOAAAAzAAAABwAAAAEAAAAEAAAAMMJyqNl0dxM4D7H7fXQ1zenAAAAaN_fnBg6It0_xz5chPYEs0wJh1NlyK62UaKCCsz6yIpOgS0G_-fHD8eZdUKeFTAXIlwQaReDylXmxMla7fMV4stkzzGPU58G11EOS_7TaxndNOKwNLKIdosgi8zr0kkezKsQi5TXmkYx8rx7TV4S9FGo7ZLerlck82YzCmGUoFhdCyJOJ5BXb79gqp-RsVnZrQrT6QUGfYYLBQoMuWDYA3yOCyNcPVcADAAAAFXfcS2-Otz25fRyeSQAAABiMGQ4NTgwMy0zOGEwLTQyYjMtODA2ZS03YTRjZjhlMTk2ZWU")
                // required for implicit grant authentication
                .setRedirectUri("www.google.com")
                // optional: set sandbox as operating environment
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();
        UberSdk.initialize(config);
//        get the context by invoking ``getApplicationContext()``, ``getContext()``, ``getBaseContext()`` or ``this`` when in the activity class
//        RideRequestButton requestButton = new RideRequestButton(this);
//        // get your layout, for instance:
//        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.activity_main);
//        layout.addView(requestButton);


// LYFT
        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("...")
                .setClientToken("...")
                .build();

        LyftButton lyftButton = (LyftButton) v.findViewById(R.id.lyft_button);
        lyftButton.setApiConfig(apiConfig);

        RideParams.Builder rideParamsBuilder = new RideParams.Builder()
                .setPickupLocation(37.7766048, -122.3943629)
                .setDropoffLocation(37.759234, -122.4135125);
        rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);

        lyftButton.setRideParams(rideParamsBuilder.build());
        lyftButton.load();

        return v;
    }

}