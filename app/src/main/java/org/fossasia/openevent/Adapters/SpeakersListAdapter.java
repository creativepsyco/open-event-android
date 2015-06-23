package org.fossasia.openevent.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.fossasia.openevent.R;
import org.fossasia.openevent.data.Speaker;
import org.fossasia.openevent.utils.CircleTransform;

import java.util.List;

/**
 * Created by MananWason on 11-06-2015.
 */
public class SpeakersListAdapter extends RecyclerView.Adapter<SpeakersListAdapter.ViewHolder> {
    List<Speaker> speakers;

    public SpeakersListAdapter(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    @Override
    public SpeakersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.speakers_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpeakersListAdapter.ViewHolder holder, int position) {
        Speaker current = speakers.get(position);

        Uri uri = Uri.parse(current.getPhoto());
        Picasso.with(holder.speaker_image.getContext()).load(uri).transform(new CircleTransform()).into(holder.speaker_image);

        holder.designation.setText(current.getPosition());
        holder.name.setText(current.getName());
        holder.bio.setText(current.getBio());
        Log.d("LIST SIZE", speakers.size() + "");
    }

    @Override
    public int getItemCount() {
        return speakers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView speaker_image;

        TextView name;

        TextView designation;

        TextView bio;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            speaker_image = (ImageView) itemView.findViewById(R.id.speaker_image);

            name = (TextView) itemView.findViewById(R.id.speaker_name);
            bio = (TextView) itemView.findViewById(R.id.speaker_bio);
            designation = (TextView) itemView.findViewById(R.id.speaker_designation);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
