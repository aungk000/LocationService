package me.aungkooo.locationservice.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;


import me.aungkooo.locationservice.R;

/**
 * Created by Ko Oo on 7/5/2018.
 */

public class SettingsFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener
{
    public static final String KEY_PROVIDER = "pref.KEY_PROVIDER";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        bindPreference(KEY_PROVIDER);
    }

    private void bindPreference(CharSequence key)
    {
        Preference preference = findPreference(key);
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(getContext())
                .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        String value = newValue.toString();

        if(preference instanceof ListPreference)
        {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);

            if(index >= 0)
            {
                preference.setSummary(listPreference.getEntries()[index]);
                preference.setDefaultValue(listPreference.getEntryValues()[index]);
            }
        }
        else {
            preference.setSummary(value);
        }

        return true;
    }
}
