package com.example.dreamapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamapp.R;
import com.google.android.material.button.MaterialButton;

public class LandingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);

		MaterialButton btnStart = findViewById(R.id.btnStart);
		btnStart.setOnClickListener(v -> {
			Intent intent = new Intent(LandingActivity.this, MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			finish();
		});
	}
}
