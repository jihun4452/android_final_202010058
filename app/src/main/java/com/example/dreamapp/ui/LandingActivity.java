package com.example.dreamapp.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamapp.R;
import com.google.android.material.button.MaterialButton;

public class LandingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_landing);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			WindowInsetsController c = getWindow().getInsetsController();
			if (c != null) c.hide(WindowInsets.Type.statusBars());
		} else {
			getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		MaterialButton btnStart = findViewById(R.id.btnStart);
		btnStart.setOnClickListener(v -> {
			startActivity(new Intent(this, MainActivity.class));
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			finish();
		});
	}
}
