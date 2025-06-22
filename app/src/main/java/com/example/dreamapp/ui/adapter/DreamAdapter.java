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

import java.util.List;

public class DreamAdapter extends RecyclerView.Adapter<DreamAdapter.DreamViewHolder> {

	public interface OnItemClickListener {
		void onItemClick(Dream dream);
	}

	private final List<Dream> dreams;
	private final OnItemClickListener listener;

	public DreamAdapter(List<Dream> dreams, OnItemClickListener listener) {
		this.dreams = dreams;
		this.listener = listener;
	}

	@NonNull
	@Override
	public DreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_dream, parent, false);
		return new DreamViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DreamViewHolder holder, int position) {
		holder.bind(dreams.get(position), listener);
	}


	public void updateData(List<Dream> newList) {
		dreams.clear();
		dreams.addAll(newList);
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return dreams.size();
	}

	static class DreamViewHolder extends RecyclerView.ViewHolder {
		private final TextView tvTitle;
		private final TextView tvContent;
		private final ImageView ivTypeIcon;

		DreamViewHolder(@NonNull View itemView) {
			super(itemView);
			tvTitle     = itemView.findViewById(R.id.tvDreamTitle);
			tvContent   = itemView.findViewById(R.id.tvDreamContent);
			ivTypeIcon  = itemView.findViewById(R.id.ivTypeIcon);
		}

		void bind(Dream dream, OnItemClickListener listener) {
			tvTitle.setText(dream.getTitle());
			tvContent.setText(dream.getContent());
			ivTypeIcon.setImageResource(getIconForType(dream.getType()));
			itemView.setOnClickListener(v -> listener.onItemClick(dream));
		}

		private int getIconForType(DreamType type) {
			switch (type) {
				case POSITIVE:   return R.drawable.img_8;
				case NEGATIVE:   return R.drawable.img_8;
				case NEUTRAL:    return R.drawable.img_8;
				case LUCID:      return R.drawable.img_8;
				case RECURRING:  return R.drawable.img_8;
				case NIGHTMARE:  return R.drawable.img_8;
				case DAYDREAM:   return R.drawable.img_8;
				default: return R.drawable.img_8;
			}
		}
	}
}
