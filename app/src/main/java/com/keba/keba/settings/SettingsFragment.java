package com.keba.keba.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

import com.keba.keba.R;

import java.util.Map;

/**
 * Created by Sparrow on 11/7/2017.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;
    private EditTextPreference userNamePreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load the preferences from your XML resource (which I assume you already do anyway)
        addPreferencesFromResource(R.xml.preferences);

        userNamePreference = (EditTextPreference) findPreference("PREF_USER_NAME");
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = getPreferenceManager().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        String str = sharedPreferences.getString("PREF_USER_NAME", "");
        userNamePreference.setSummary(str);

    }

    @Override
    public void onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals("PREF_USER_NAME")) {
            String str = sharedPreferences.getString("PREF_USER_NAME", "");
            userNamePreference.setSummary(sharedPreferences.getString("PREF_USER_NAME", ""));
        }

    }

}