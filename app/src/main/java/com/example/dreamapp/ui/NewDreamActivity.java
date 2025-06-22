package com.example.dreamapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dreamapp.R;
import com.example.dreamapp.model.Dream;
import com.example.dreamapp.model.type.DreamType;
import com.example.dreamapp.repository.DreamRepository;

public class NewDreamActivity extends AppCompatActivity {

	private EditText etDreamTitle;
	private EditText etDreamContent;
	private RadioGroup rgDreamType;
	private CheckBox cbShowDescription;
	private Button btnSave;
	private DreamRepository repository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_dream);

		Toolbar toolbar = findViewById(R.id.toolbar_new_dream);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		repository = new DreamRepository();
		etDreamTitle = findViewById(R.id.etDreamTitle);
		etDreamContent = findViewById(R.id.etDreamContent);
		rgDreamType = findViewById(R.id.rgDreamType);
		cbShowDescription= findViewById(R.id.cbShowDescription);
		btnSave = findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this::onSaveButtonClick);
	}

	private void onSaveButtonClick(View v) {
		String title   = etDreamTitle.getText().toString().trim();
		String content = etDreamContent.getText().toString().trim();
		int checkedId = rgDreamType.getCheckedRadioButtonId();
		DreamType type;
		if (checkedId == R.id.rbPositive) {
			type = DreamType.POSITIVE;
		} else if (checkedId == R.id.rbNegative) {
			type = DreamType.NEGATIVE;
		} else if (checkedId == R.id.rbNeutral) {
			type = DreamType.NEUTRAL;
		} else if (checkedId == R.id.rbLucid) {
			type = DreamType.LUCID;
		} else if (checkedId == R.id.rbRecurring) {
			type = DreamType.RECURRING;
		} else if (checkedId == R.id.rbNightmare) {
			type = DreamType.NIGHTMARE;
		} else if (checkedId == R.id.rbDaydream) {
			type = DreamType.DAYDREAM;
		} else {
			type = DreamType.NEUTRAL;
		}

		boolean showDesc = cbShowDescription.isChecked();
		repository.addDream(new Dream(title, content, type, showDesc));
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
