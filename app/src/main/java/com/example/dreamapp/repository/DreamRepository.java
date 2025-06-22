package com.example.dreamapp.repository;

import com.example.dreamapp.model.Dream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DreamRepository {
	private static final List<Dream> DREAMS = new ArrayList<>();

	public List<Dream> getAllDreams() {
		List<Dream> copy = new ArrayList<>(DREAMS);
		Collections.reverse(copy);
		return copy;
	}

	public void addDream(Dream dream) {
		DREAMS.add(dream);
	}
}
