package com.jasonetto.seed.Modules.Jasonette;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jasonetto.MainApplication;
import com.jasonetto.seed.Core.JasonViewActivity;

public class JasonetteModule extends ReactContextBaseJavaModule {

    public JasonetteModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JasonetteAndroid";
    }

    @ReactMethod
    public void show(String jsnURL) {
        Intent intent = new Intent(getReactApplicationContext(), JasonViewActivity.class);
        if(!jsnURL.equals("")) {
            intent.putExtra("url", jsnURL);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getReactApplicationContext().startActivity(intent);
    }

}
