package android.example.tourguidend;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResturantFragment extends Fragment {
    RecyclerView restourantRecycler;
    PlaceAdapter restAdapter;
    ArrayList<Place> restourantPlaces = new ArrayList<Place>();


    public ResturantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        if (savedInstanceState != null) {
            restourantPlaces = savedInstanceState.getParcelableArrayList("LIST");

        }
        restourantRecycler = rootView.findViewById(R.id.recycler_view);

        //***the second argument fromMyFavourite is false because i'm not using the myFavouritesList
        // so the adapter will make the visibility of favImage in the list_item layout to Visible
        restAdapter = new PlaceAdapter(getActivity(), restourantPlaces, false);
        if (getActivity().getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            restourantRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            restourantRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        }

        restourantRecycler.setItemAnimator(new DefaultItemAnimator());
        restourantRecycler.setHasFixedSize(true);
        restourantRecycler.setAdapter(restAdapter);
        restAdapter.notifyDataSetChanged();

        restAdapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Place itemPlace = restourantPlaces.get(position);
                openDetailsActivity(itemPlace);
            }
        });


        return rootView;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            restourantPlaces = savedInstanceState.getParcelableArrayList("LIST");
        } else {
            restourantPlaces.add(new Place(getString(R.string.restaurant1_name), getString(R.string.restaurant1_description), getString(R.string.restaurant1_address), getString(R.string.restaurant1_webSite), getString(R.string.restaurant1_phone), getString(R.string.restaurant1_overView), getString(R.string.restaurant1_expect), R.drawable.bab_elkasr, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant2_name), getString(R.string.restaurant2_description), getString(R.string.restaurant2_address), getString(R.string.restaurant2_webSite), getString(R.string.restaurant2_phone), getString(R.string.restaurant2_overView), getString(R.string.restaurant2_expect), R.drawable.marshmallo_cafee, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant3_name), getString(R.string.restaurant3_description), getString(R.string.restaurant3_address), getString(R.string.restaurant3_webSite), getString(R.string.restaurant3_phone), getString(R.string.restaurant3_overView), getString(R.string.restaurant3_expect), R.drawable.makai, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant4_name), getString(R.string.restaurant4_description), getString(R.string.restaurant4_address), getString(R.string.restaurant4_webSite), getString(R.string.restaurant4_phone), getString(R.string.restaurant4_overView), getString(R.string.restaurant4_expect), R.drawable.moby_dick, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant5_name), getString(R.string.restaurant5_description), getString(R.string.restaurant5_address), getString(R.string.restaurant5_webSite), getString(R.string.restaurant5_phone), getString(R.string.restaurant5_overView), getString(R.string.restaurant5_expect), R.drawable.fusion, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant6_name), getString(R.string.restaurant6_description), getString(R.string.restaurant6_address), getString(R.string.restaurant6_webSite), getString(R.string.restaurant6_phone), getString(R.string.restaurant6_overView), getString(R.string.restaurant6_expect), R.drawable.kababji, R.drawable.ic_favorite_border, false));
            restourantPlaces.add(new Place(getString(R.string.restaurant7_name), getString(R.string.restaurant7_description), getString(R.string.restaurant7_address), getString(R.string.restaurant7_webSite), getString(R.string.restaurant7_phone), getString(R.string.restaurant7_overView),
                    getString(R.string.restaurant7_expect)
                    , R.drawable.smoky, R.drawable.ic_favorite_border, false));

        }
    }

    public void openDetailsActivity(Place currentPlace) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("placeItem", currentPlace);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("LIST", restourantPlaces);
    }


}
