package com.example.dreamapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamapp.R;
import com.example.dreamapp.model.Dream;
import com.example.dreamapp.model.type.DreamType;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DreamAdapter extends RecyclerView.Adapter<DreamAdapter.DreamViewHolder> {

	public interface OnItemClickListener {
		void onItemClick(Dream dream);
	}

	private final List<Dream> dreams;
	private final OnItemClickListener listener;
	private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	public DreamAdapter(List<Dream> dreams, OnItemClickListener listener) {
		this.dreams = dreams;
		this.listener = listener;
	}

	@NonNull
	@Override
	public DreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_dream, parent, false);
		return new DreamViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull DreamViewHolder holder, int pos) {
		holder.bind(dreams.get(pos), listener, fmt);
	}

	@Override
	public int getItemCount() {
		return dreams.size();
	}

	public void updateData(List<Dream> newList) {
		dreams.clear();
		dreams.addAll(newList);
		notifyDataSetChanged();
	}

	static class DreamViewHolder extends RecyclerView.ViewHolder {
		private final TextView tvTitle, tvContent, tvCreatedAt;
		private final ImageView ivTypeIcon;

		DreamViewHolder(@NonNull View itemView) {
			super(itemView);
			tvTitle     = itemView.findViewById(R.id.tvDreamTitle);
			tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
			tvContent   = itemView.findViewById(R.id.tvDreamContent);
			ivTypeIcon  = itemView.findViewById(R.id.ivTypeIcon);
		}

		void bind(Dream d, OnItemClickListener l, DateTimeFormatter fmt) {
			tvTitle.setText(d.getTitle());
			tvCreatedAt.setText(fmt.format(d.getCreatedAt()));
			tvContent.setText(d.getContent());
			ivTypeIcon.setImageResource(icon(d.getType()));
			itemView.setOnClickListener(v -> l.onItemClick(d));
		}

		private int icon(DreamType t) {
			return R.drawable.img_8;
		}
	}
}
