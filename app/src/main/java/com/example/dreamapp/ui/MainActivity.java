package com.example.dreamapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamapp.R;
import com.example.dreamapp.model.Dream;
import com.example.dreamapp.repository.DreamRepository;
import com.example.dreamapp.ui.adapter.DreamAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerDreams;
	private FloatingActionButton fabAddDream;
	private DreamRepository repository;
	private DreamAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
			return insets;
		});

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		repository     = new DreamRepository();
		recyclerDreams = findViewById(R.id.recyclerDreams);
		recyclerDreams.setLayoutManager(new LinearLayoutManager(this));
		adapter = new DreamAdapter(repository.getAllDreams(), this::onDreamSelected);
		recyclerDreams.setAdapter(adapter);

		fabAddDream = findViewById(R.id.fabAddDream);
		fabAddDream.setOnClickListener(v -> {
			startActivity(new Intent(this, NewDreamActivity.class));
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		List<Dream> updated = repository.getAllDreams();
		adapter.updateData(updated);
	}

	private void onDreamSelected(Dream dream) {
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra("DREAM_TITLE", dream.getTitle());
		intent.putExtra("DREAM_TEXT", dream.getContent());
		intent.putExtra("DREAM_TYPE", dream.getType().name());
		intent.putExtra("SHOW_DESC", dream.isShowDescription());
		startActivity(intent);
	}
}
