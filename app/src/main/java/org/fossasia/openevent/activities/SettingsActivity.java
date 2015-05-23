package org.fossasia.openevent.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import org.fossasia.openevent.R;

/**
 * Created by championswimmer on 23/5/15.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_settings);
    }
}
