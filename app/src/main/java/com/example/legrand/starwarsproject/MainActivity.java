package com.example.legrand.starwarsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final ApiService apiService = ApiService.Builder.getInstance();
    Button list_itemButton;
    ArrayList<String> namePersoList = new ArrayList<>();
    ArrayList<String> heightPersoList = new ArrayList<>();
    ArrayList<String> massPersoList = new ArrayList<>();
    ArrayList<String> hairColorPersoList = new ArrayList<>();
    ArrayList<String> skinColorPersoList = new ArrayList<>();
    ArrayList<String> eyeColorPersoList = new ArrayList<>();
    ArrayList<String> bithDatePersoList = new ArrayList<>();
    ArrayList<String> genderPersoList = new ArrayList<>();
    ArrayList<String> homeworldPersoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_itemButton = findViewById(R.id.list_itemButton);
        list_itemButton.setOnClickListener(onListButtonClicked);

    }

    private final View.OnClickListener onListButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {

            namePersoList.clear();
            heightPersoList.clear();
            massPersoList.clear();
            hairColorPersoList.clear();
            skinColorPersoList.clear();
            eyeColorPersoList.clear();
            bithDatePersoList.clear();
            genderPersoList.clear();
            homeworldPersoList.clear();
            list_itemButton.setEnabled(false); // Permet de prevoir si 2 clicks sur le bouton

            int i;

            /*
             * Permet de récuperer les 87 personnages
             *
            */
            for(i=1; i<=87; i++){
                String str = Integer.toString(i);
                apiService.readCharacter(str).enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(final Call<Character> call, final Response<Character> response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                handleResponse(response);
                                list_itemButton.setEnabled(true); // Permet de réactiver le bouton
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Call<Character> call, final Throwable t) {
                        t.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list_itemButton.setEnabled(true);
                                Toast.makeText(MainActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    };

    /**
     * Handle default response for both GET/POST methods
     */
    private void handleResponse(final Response<Character> response) {

        /*
         * Lorsque la reponse est récupéré (le json d'un personnage),
         * les infomations sont sépare pour récuperer les différentes infomations des personnages
         * comme nom, height, mass, hair color, ...
         *
         * Les informations sont ensuite ajoutés dans différent tableaux
         * Chaque tableau comporte une information (ex: nom) et a la liste pour tous les personnages
         */
        if (response.isSuccessful()) {
            namePersoList.add(response.body().getName());
            heightPersoList.add(response.body().getHeight());
            massPersoList.add(response.body().getMass());
            hairColorPersoList.add(response.body().getHair_color());
            skinColorPersoList.add(response.body().getSkin_color());
            eyeColorPersoList.add(response.body().getEye_color());
            bithDatePersoList.add(response.body().getBirth_year());
            genderPersoList.add(response.body().getGender());
            homeworldPersoList.add(response.body().getHomeworld());

            if(namePersoList.size() == 86) {
                final Intent i = CharacterListActivity.getStartIntent(MainActivity.this);

                /*
                 * Envoie les listes des informations des personnages dans Activity suivante qui va afficher la liste des personnages
                 */
                i.putStringArrayListExtra("namePersoList", namePersoList);
                i.putStringArrayListExtra("heightPersoList", heightPersoList);
                i.putStringArrayListExtra("massPersoList", massPersoList);
                i.putStringArrayListExtra("hairColorPersoList", hairColorPersoList);
                i.putStringArrayListExtra("skinColorPersoList", skinColorPersoList);
                i.putStringArrayListExtra("eyeColorPersoList", eyeColorPersoList);
                i.putStringArrayListExtra("birthDayPersoList", bithDatePersoList);
                i.putStringArrayListExtra("genderPersoList",  genderPersoList);
                i.putStringArrayListExtra("homeworldPersoList", homeworldPersoList);

                startActivity(i);
            }

        } else { // error HTTP
            try {
                //Essayer de transformer l'erreur de la  gson et on va chercher le erreur body qu'on transforme en string
                final HttpError error = new Gson().fromJson(response.errorBody().string(), HttpError.class);
                //Message d'erreur en cas de problème
                if(error.getMessage() != null) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (final IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
