package com.example.dreamapp.ui;

import java.time.LocalDateTime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamapp.R;
import com.example.dreamapp.model.Dream;
import com.example.dreamapp.model.type.DreamType;
import com.example.dreamapp.repository.DreamRepository;

public class NewDreamActivity extends AppCompatActivity {

	private EditText etDreamTitle;
	private EditText etDreamContent;
	private CheckBox cbShowDescription;

	private RadioButton rbPositive, rbNegative, rbNeutral, rbLucid, rbRecurring, rbNightmare, rbDaydream;

	private DreamType selectedType = DreamType.NEUTRAL;
	private final DreamRepository repository = new DreamRepository();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_dream);

		etDreamTitle      = findViewById(R.id.etDreamTitle);
		etDreamContent    = findViewById(R.id.etDreamContent);
		cbShowDescription = findViewById(R.id.cbShowDescription);

		rbPositive  = findViewById(R.id.rbPositive);
		rbNegative  = findViewById(R.id.rbNegative);
		rbNeutral   = findViewById(R.id.rbNeutral);
		rbLucid     = findViewById(R.id.rbLucid);
		rbRecurring = findViewById(R.id.rbRecurring);
		rbNightmare = findViewById(R.id.rbNightmare);
		rbDaydream  = findViewById(R.id.rbDaydream);

		Button btnSave = findViewById(R.id.btnSave);

		View.OnClickListener typeClick = v -> {
			uncheckAll();
			((RadioButton) v).setChecked(true);
			selectedType = mapButtonToType(v.getId());
		};

		rbPositive .setOnClickListener(typeClick);
		rbNegative .setOnClickListener(typeClick);
		rbNeutral  .setOnClickListener(typeClick);
		rbLucid    .setOnClickListener(typeClick);
		rbRecurring.setOnClickListener(typeClick);
		rbNightmare.setOnClickListener(typeClick);
		rbDaydream .setOnClickListener(typeClick);
		btnSave.setOnClickListener(this::onSave);
	}

	private void onSave(View v) {
		String title   = etDreamTitle  .getText().toString().trim();
		String content = etDreamContent.getText().toString().trim();
		boolean showDesc = cbShowDescription.isChecked();
		repository.addDream(new Dream(title, content, selectedType, LocalDateTime.now(), showDesc));
		finish();
	}

	private void uncheckAll() {
		rbPositive.setChecked(false);
		rbNegative.setChecked(false);
		rbNeutral.setChecked(false);
		rbLucid.setChecked(false);
		rbRecurring.setChecked(false);
		rbNightmare.setChecked(false);
		rbDaydream.setChecked(false);
	}

	private DreamType mapButtonToType(int id) {
		if(id == R.id.rbPositive)  return DreamType.POSITIVE;
		else if (id == R.id.rbNegative)  return DreamType.NEGATIVE;
		else if (id == R.id.rbNeutral)   return DreamType.NEUTRAL;
		else if (id == R.id.rbLucid)     return DreamType.LUCID;
		else if (id == R.id.rbRecurring) return DreamType.RECURRING;
		else if (id == R.id.rbNightmare) return DreamType.NIGHTMARE;
		else if (id == R.id.rbDaydream)  return DreamType.DAYDREAM;
		else return DreamType.NEUTRAL;
	}
}
