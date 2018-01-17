package com.example.legrand.starwarsproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by legrand on 16/01/2018.
 */

public class CharacterAdapter extends ArrayAdapter<Character> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnCharacterSelectedListener {
        void handle(final Character personnage);
    }

    private final OnCharacterSelectedListener onCharacterSelectedListener;

        CharacterAdapter(@NonNull final Context context, final List<Character> devices, final OnCharacterSelectedListener listener) {
        super(context, R.layout.character_list_item, devices);
        onCharacterSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.character_list_item, null);
        }

        final Character personnage = getItem(position);
        if (personnage == null) {
            return holder;
        }

        // display the name
        final TextView deviceName = holder.findViewById(R.id.characterName);
        if (deviceName != null) {
            deviceName.setText(personnage.getName());
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onCharacterSelectedListener != null) {
                    onCharacterSelectedListener.handle(personnage);
                }
            }
        });

        return holder;
    }
}
