package com.woncheol.yuljeon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
 
public class PrefsFragment extends PreferenceActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
    
    Preference userButton = (Preference) findPreference("singo");
	userButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference arg0) {
	    	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	                "mailto","dnjscjf098@gmail.com", null));
	               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "�̸�(�й�)�� �Է��ϼ���");
	               emailIntent.putExtra(Intent.EXTRA_TEXT, "����, ���̵��, ���۱� ���� �� �ߴ��� ������ �ƴϸ� ���� ������ ������");
	               startActivity(Intent.createChooser(emailIntent, "���� ������"));
	        return true;
	    }
	});
	
	Preference button = (Preference) findPreference("Opensource");
	button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
	    @Override
	    public boolean onPreferenceClick(Preference preference) {
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	        intent.setData(Uri.parse("https://github.com/gpillusion/Yuljeon_Project"));
	        startActivity(intent);
	        return true;
	    }
	});
	
	}
}