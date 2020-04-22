package com.clutchapp.a421go.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.clutchapp.a421go.R;

/**
 * Fragment affichant les informations en cours d'une partie
 */
public class GameInfoFragment extends Fragment {

    //Propriétés
    private LinearLayout rankPlayersList;

    /**
     * Constructeur vide
     */
    public GameInfoFragment() {
    }

    /**
     * Méthode utilisé lors de la création de l'activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
    * Méthode utilisé lors de la création de la vue de l'activity
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_info, container, false);
    }

}
