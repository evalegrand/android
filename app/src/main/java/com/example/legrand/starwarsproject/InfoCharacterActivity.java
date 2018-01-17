package com.example.legrand.starwarsproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by legrand on 16/01/2018.
 */

public class InfoCharacterActivity extends AppCompatActivity {

    Button infoperso;

    public static Intent getStartIntent(final Context context) {
        return new Intent(context, InfoCharacterActivity.class);
    }

    ImageView pictureView;
    TextView heightTextView;
    TextView massTextView;
    TextView hairTextView;
    TextView skinTextView;
    TextView eyeTextView;
    TextView bithTextView;
    TextView genderTextView;
    TextView homeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_character);

        String heightPerso;
        String massPerso;
        String hairColorPerso;
        String skinColorPerso;
        String eyeColorPerso;
        String bithDatePerso;
        String genderPerso;
        String homeworldPerso;

        Bundle extras = getIntent().getExtras();
        heightPerso = extras.getString("heightPerso");
        massPerso = extras.getString("massPerso");
        hairColorPerso = extras.getString("hairColorPerso");
        skinColorPerso = extras.getString("skinColorPerso");
        eyeColorPerso = extras.getString("eyeColorPerso");
        bithDatePerso = extras.getString("birthDayPerso");
        genderPerso = extras.getString("genderPerso");
        homeworldPerso = extras.getString("homeworldPerso");

        heightTextView = findViewById(R.id.height);
        heightTextView.setText("Height = " + getString(R.string.height, heightPerso));


        massTextView = findViewById(R.id.mass);
        massTextView .setText("Mass = " + getString(R.string.mass, massPerso));

        hairTextView = findViewById(R.id.hair_color);
        hairTextView .setText("Hair Color = " + getString(R.string.hair_color, hairColorPerso));

        skinTextView = findViewById(R.id.skin_color);
        skinTextView .setText("Skin Color = " + getString(R.string.skin_color, skinColorPerso));

        eyeTextView = findViewById(R.id.eye_color);
        eyeTextView .setText("Eye Color = " + getString(R.string.eye_color, eyeColorPerso));

        bithTextView = findViewById(R.id.birth_day);
        bithTextView.setText("BirthDay = " + getString(R.string.birth_day, bithDatePerso));

        genderTextView = findViewById(R.id.gender);
        genderTextView.setText("Gender = " + getString(R.string.gender, genderPerso));

        homeTextView = findViewById(R.id.homeworld);
        homeTextView.setText("HomeWorld = " + getString(R.string.homeworld, homeworldPerso));

    }

}
