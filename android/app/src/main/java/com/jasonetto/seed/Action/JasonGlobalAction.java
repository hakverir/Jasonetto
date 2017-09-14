package com.jasonetto.seed.Action;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jasonetto.seed.Core.JasonViewActivity;
import com.jasonetto.seed.Helper.JasonHelper;
import com.jasonetto.MainApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class JasonGlobalAction {
    public void reset(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {

        /********************

         The following resets a global variable named "db".
         When a variable is reset, the key itself gets destroyed, so when you check ('db' in $global), it will return false

         {
             "type": "$global.reset",
             "options": {
                 "items": ["db"]
             }
         }

         ********************/

        try {
            SharedPreferences pref = context.getSharedPreferences("global", 0);
            SharedPreferences.Editor editor = pref.edit();

            JSONObject options = action.getJSONObject("options");
            if(options.has("items")){
                JSONArray items = options.getJSONArray("items");
                for (int i=0; i<items.length(); i++) {
                    String item = items.getString(i);
                    editor.remove(item);
                    ((MainApplication)context.getApplicationContext()).resetGlobal(item);
                }
                editor.commit();
            }

            // Execute next
            JasonHelper.next("success", action, ((MainApplication)context.getApplicationContext()).getGlobal(), event, context);

        } catch (Exception e) {
            Log.d("Warning", e.getStackTrace()[0].getMethodName() + " : " + e.toString());
        }

    }
    public void set(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {

        /********************

         The following sets a global variable named "db".

         {
             "type": "$global.set",
             "options": {
                 "db": ["a", "b", "c", "d"]
             }
         }

         Once set, you can access them through template expressions from ANYWHERE within the app, like this:

         {
             "items": {
                 "{{#each $global.db}}": {
                     "type": "label",
                     "text": "{{this}}"
                 }
             }
         }

         ********************/

        try {
            SharedPreferences pref = context.getSharedPreferences("global", 0);
            SharedPreferences.Editor editor = pref.edit();

            JSONObject options = action.getJSONObject("options");

            Iterator<String> keysIterator = options.keys();
            while (keysIterator.hasNext()) {
                String key = (String) keysIterator.next();
                Object val = options.get(key);
                editor.putString(key, val.toString());
                ((MainApplication)context.getApplicationContext()).setGlobal(key, val);
            }
            editor.commit();

            // Execute next
            JasonHelper.next("success", action, ((MainApplication)context.getApplicationContext()).getGlobal(), event, context);

        } catch (Exception e) {
            Log.d("Warning", e.getStackTrace()[0].getMethodName() + " : " + e.toString());
        }
    }
}
