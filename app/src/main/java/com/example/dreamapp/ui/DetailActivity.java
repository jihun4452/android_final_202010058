package com.example.dreamapp.ui;

import java.time.LocalDateTime;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dreamapp.R;
import com.example.dreamapp.model.Dream;
import com.example.dreamapp.model.type.DreamType;
import com.example.dreamapp.util.DreamInterpreter;

public class DetailActivity extends AppCompatActivity {

	private TextView tvDreamTitle;
	private TextView tvDreamContent;
	private TextView tvDreamType;
	private TextView tvInterpretation;
	private ImageView ivTypeIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Toolbar toolbar = findViewById(R.id.toolbar_detail);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle("꿈 게시물");
		}

		tvDreamTitle = findViewById(R.id.tvDreamTitle);
		tvDreamContent = findViewById(R.id.tvDreamContent);
		tvDreamType = findViewById(R.id.tvDreamType);
		tvInterpretation = findViewById(R.id.tvInterpretation);
		ivTypeIcon = findViewById(R.id.ivTypeIcon);

		String title = getIntent().getStringExtra("DREAM_TITLE");
		if (title == null || title.isEmpty()) title = "제목 없음";
		tvDreamTitle.setText(title);

		String content = getIntent().getStringExtra("DREAM_TEXT");
		if (content == null) content = "게시물의 내용이 없습니다.";
		tvDreamContent.setText(content);

		String typeStr = getIntent().getStringExtra("DREAM_TYPE");
		DreamType type;
		try {
			type = typeStr == null ? DreamType.NEUTRAL : DreamType.valueOf(typeStr);
		} catch (IllegalArgumentException e) {
			type = DreamType.NEUTRAL;
		}

		String typeLabel;
		switch (type) {
			case POSITIVE:  typeLabel = "긍정적 꿈"; break;
			case NEGATIVE:  typeLabel = "부정적 꿈"; break;
			case LUCID:     typeLabel = "자각몽";  break;
			case RECURRING: typeLabel = "반복 꿈";  break;
			case NIGHTMARE: typeLabel = "악몽";    break;
			case DAYDREAM:  typeLabel = "백일몽";  break;
			default:        typeLabel = "중립적 꿈"; break;
		}
		tvDreamType.setText(typeLabel);
		ivTypeIcon.setImageResource(getIconResForType(type));

		boolean showDesc = getIntent().getBooleanExtra("SHOW_DESC", false);
		if (showDesc) {
			String explanation = DreamInterpreter.interpret(new Dream(title, content, type, LocalDateTime.now(), true));
			tvInterpretation.setText(explanation);
			tvInterpretation.setVisibility(View.VISIBLE);
		} else {
			tvInterpretation.setVisibility(View.GONE);
		}

		Log.d("DetailActivity", "Received title=" + title + ", type=" + type + ", showDesc=" + showDesc);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSupportNavigateUp() {
		finish();
		return true;
	}

	private int getIconResForType(DreamType type) {
		return R.drawable.img_8;
	}
}
