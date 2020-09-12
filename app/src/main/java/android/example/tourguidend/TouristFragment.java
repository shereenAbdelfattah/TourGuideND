package android.example.tourguidend;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouristFragment extends Fragment {

    RecyclerView touristRecycler;
    PlaceAdapter touristAdapter;
    ArrayList<Place> touristPlaces;
    public static final String LIST_KEY = "touristList";

    public TouristFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        if (savedInstanceState != null) {
            touristPlaces = savedInstanceState.getParcelableArrayList(LIST_KEY);
        }

        touristRecycler = rootView.findViewById(R.id.recycler_view);
        touristAdapter = new PlaceAdapter(getActivity(), touristPlaces, false);

        if (getActivity().getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            touristRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            touristRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        }

        touristRecycler.setItemAnimator(new DefaultItemAnimator());
        touristRecycler.setHasFixedSize(true);
        touristRecycler.setAdapter(touristAdapter);
        touristAdapter.notifyDataSetChanged();

        touristAdapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Place itemPlace = touristPlaces.get(position);
                Toast.makeText(getActivity(), itemPlace.getPlaceName(), Toast.LENGTH_LONG).show();

                openDetailsActivity(itemPlace);

            }
        });


        return rootView;

    }

    public void openDetailsActivity(Place currentPlace) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("placeItem", currentPlace);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            touristPlaces = savedInstanceState.getParcelableArrayList(LIST_KEY);


        } else {
            touristPlaces = new ArrayList<Place>();
            touristPlaces.add(new Place(getString(R.string.tourist_place1_name), getString(R.string.tourist_description1), getString(R.string.tourist_address1), "", "", getString(R.string.hatshebsut_overView), getString(R.string.hatshebsut_excepect), R.drawable.hashupsut_temple, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place2_name), getString(R.string.tourist_description2), getString(R.string.tourist_address2), "", "", getString(R.string.karnak_overView), getString(R.string.karnk_expect), R.drawable.karnak_temple, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place3_name), getString(R.string.tourist_description3), getString(R.string.tourist_address3), "", "", getString(R.string.dendara_overView), getString(R.string.dendara_expect), R.drawable.dendara, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place4_name), getString(R.string.tourist_description4), getString(R.string.tourist_address4), "", "", getString(R.string.deir_elmadina_overView), getString(R.string.der_elmadina_expect), R.drawable.deir_almadina, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place5_name), getString(R.string.tourist_description5), getString(R.string.tourist_address5), getString(R.string.tourist_web_site5), "", getString(R.string.islamic_museum_overView), getString(R.string.islamic_museum_expect), R.drawable.islamic_museum, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place6_name), getString(R.string.tourist_description6), getString(R.string.tourist_address6), "", "", getString(R.string.pyramids_overView), getString(R.string.pyramids_expext), R.drawable.giza_pyramids, R.drawable.ic_favorite_border, false));
            touristPlaces.add(new Place(getString(R.string.tourist_place7_name), getString(R.string.tourist_description7), getString(R.string.tourist_address7), "", "", getString(R.string.abu_simbel_overView), getString(R.string.abu_simbel_expect), R.drawable.abu_simbel, R.drawable.ic_favorite_border, false));

        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(LIST_KEY, touristPlaces);
    }




}
