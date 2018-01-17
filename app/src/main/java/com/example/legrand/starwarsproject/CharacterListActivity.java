package com.example.legrand.starwarsproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by legrand on 16/01/2018.
 */

public class CharacterListActivity extends AppCompatActivity {

    public static Intent getStartIntent(final Context context) {
        return new Intent(context, CharacterListActivity.class);
    }

    private final List<Character> listOfPersonnnage = new ArrayList<>();
    private CharacterAdapter personnageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcharacter);

        ArrayList<String> namePersoList;
        ArrayList<String> heightPersoList;
        ArrayList<String> massPersoList;
        ArrayList<String> hairColorPersoList;
        ArrayList<String> skinColorPersoList;
        ArrayList<String> eyeColorPersoList;
        ArrayList<String> bithDatePersoList;
        ArrayList<String> genderPersoList;
        ArrayList<String> homeworldPersoList;

        Bundle extras = getIntent().getExtras();
        namePersoList = extras.getStringArrayList("namePersoList");
        heightPersoList = extras.getStringArrayList("heightPersoList");
        massPersoList = extras.getStringArrayList("massPersoList");
        hairColorPersoList = extras.getStringArrayList("hairColorPersoList");
        skinColorPersoList = extras.getStringArrayList("skinColorPersoList");
        eyeColorPersoList = extras.getStringArrayList("eyeColorPersoList");
        bithDatePersoList = extras.getStringArrayList("birthDayPersoList");
        genderPersoList = extras.getStringArrayList("genderPersoList");
        homeworldPersoList = extras.getStringArrayList("homeworldPersoList");

        final ListView personnages = findViewById(R.id.listView);

        personnageAdapter = new CharacterAdapter(this, listOfPersonnnage, personnageSelectedListener);
        personnages.setAdapter(personnageAdapter);

        final List<Character> values = new ArrayList<>();
        for (int i = 1; i <= 86; i++) {

            values.add(new Character(namePersoList.get(i-1),
                    heightPersoList.get(i-1),
                    massPersoList.get(i-1),
                    hairColorPersoList.get(i-1),
                    skinColorPersoList.get(i-1),
                    eyeColorPersoList.get(i-1),
                    bithDatePersoList.get(i-1),
                    genderPersoList.get(i-1),
                    homeworldPersoList.get(i-1)));
        }

        updatePersonnage(values);
    }

    /**
     * Triggered when a device is selected
     */
    private final CharacterAdapter.OnCharacterSelectedListener personnageSelectedListener = new CharacterAdapter.OnCharacterSelectedListener() {
        @Override
        public void handle(final Character personnage) {
            final Intent i = InfoCharacterActivity.getStartIntent(CharacterListActivity.this);
            i.putExtra("heightPerso", personnage.getHeight());
            i.putExtra("massPerso", personnage.getMass());
            i.putExtra("hairColorPerso", personnage.getHair_color());
            i.putExtra("skinColorPerso", personnage.getSkin_color());
            i.putExtra("eyeColorPerso", personnage.getEye_color());
            i.putExtra("birthDayPerso", personnage.getBirth_year());
            i.putExtra("genderPerso" , personnage.getGender());
            i.putExtra("homeworldPerso", personnage.getHomeworld());

            startActivity(i);
            finish();
        }
    };

    /**
     * Refresh the list of devices with new values.
     * If the new list is empty, show a message to inform the user.
     */
    private void updatePersonnage(final List<Character> personnages) {
        listOfPersonnnage.clear();
        if (personnages != null && personnages.size() > 0) {
            listOfPersonnnage.addAll(personnages);
            // personnageAdapter.notifyDataSetChanged();
        } else {
            // noDeviceFound.setVisibility(View.VISIBLE);
        }
    }
}
