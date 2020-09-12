package android.example.tourguidend;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParksFragment extends Fragment {
    RecyclerView parksRecycler;
    PlaceAdapter parksAdapter;
    ArrayList<Place> parksPlaces = new ArrayList<Place>();
    ;


    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        if (savedInstanceState != null) {
            parksPlaces = savedInstanceState.getParcelableArrayList("parks_list");
        }

         parksRecycler = rootView.findViewById(R.id.recycler_view);

        //***the second argument fromMyFavourite is false because i'm not using the myFavouritesList
        // so the adapter will make the visibility of favImage in the list_item layout to Visible
        parksAdapter = new PlaceAdapter(getActivity(), parksPlaces, false);


        if (getActivity().getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            parksRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            parksRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        parksRecycler.setItemAnimator(new DefaultItemAnimator());
        parksRecycler.setHasFixedSize(true);
        parksRecycler.setAdapter(parksAdapter);
        parksAdapter.notifyDataSetChanged();

        parksAdapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Place itemPlace = parksPlaces.get(position);
                Toast.makeText(getActivity(), itemPlace.getPlaceName(), Toast.LENGTH_LONG).show();

                openDetailsActivity(itemPlace);


            }
        });

        return rootView;
    }

    //*****to handel the fragment in pause state,
    // if the list was created then get it from saved instant else create it
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            parksPlaces = savedInstanceState.getParcelableArrayList("parks_list");

        } else {
            parksPlaces.add(new Place(getString(R.string.park1_name), getString(R.string.park1_description), getString(R.string.park1_address), getString(R.string.park1_webSite), getString(R.string.park1_phone), getString(R.string.park1_overview),getString(R.string.prak1_expect), R.drawable.jungel_park, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park2_name), getString(R.string.park2_description), getString(R.string.park2_address), getString(R.string.park2_webSite), getString(R.string.park2_phone), getString(R.string.park2_overview),getString(R.string.prak2_expect), R.drawable.cleo_park, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park3_name), getString(R.string.park3_description), getString(R.string.park3_address), getString(R.string.park3_webSite), getString(R.string.park3_phone), getString(R.string.park3_overview),getString(R.string.prak3_expect), R.drawable.sindbad, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park4_name), getString(R.string.park4_description), getString(R.string.park4_address), getString(R.string.park4_webSite), getString(R.string.park4_phone),  getString(R.string.park4_overview),getString(R.string.prak4_expect), R.drawable.ghardaka_aquarium, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park5_name), getString(R.string.park5_description), getString(R.string.park5_address), getString(R.string.park5_webSite), "", getString(R.string.park5_overview), getString(R.string.prak5_expect), R.drawable.dream_bark, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park6_name), getString(R.string.park6_description), getString(R.string.park6_address), getString(R.string.park6_webSite), getString(R.string.park6_phone), getString(R.string.park6_overview), getString(R.string.prak6_expect), R.drawable.elguna, R.drawable.ic_favorite_border, false));
            parksPlaces.add(new Place(getString(R.string.park7_name), getString(R.string.park7_description), getString(R.string.park7_address), getString(R.string.park7_webSite), "", getString(R.string.park7_overview), getString(R.string.prak7_expect), R.drawable.alazharp, R.drawable.ic_favorite_border, false));

        }
    }

    //method to open detailsActivity to show the current place
    public void openDetailsActivity(Place currentPlace) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("placeItem", currentPlace);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("parks_list", parksPlaces);
    }
}
