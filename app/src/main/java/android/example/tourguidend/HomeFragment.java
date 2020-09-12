package android.example.tourguidend;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class HomeFragment extends Fragment {

    RecyclerView homeRecycler;
    PlaceAdapter homeAdapter;
    public static ArrayList<Place> events = new ArrayList<Place>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        if (savedInstanceState!=null){
            events=savedInstanceState.getParcelableArrayList("events_list");
        }
        TextView overView = rootView.findViewById(R.id.about_eg_txtV);
        String ss=getString(R.string.home_overview) + "\n" + (getString(R.string.about_egypt));
        overView.setText(ss);

        homeRecycler = rootView.findViewById(R.id.recycler_view_home);
        //***the second argument fromMyFavourite is true because i want the visibility of favImage to be GONE

        homeAdapter = new PlaceAdapter(getActivity(), events, true);
        if (getActivity().getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            homeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {
            homeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        }


        homeRecycler.setItemAnimator(new DefaultItemAnimator());
        homeRecycler.setHasFixedSize(true);
        homeRecycler.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();






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

        if (savedInstanceState!=null){
            events=savedInstanceState.getParcelableArrayList("events_list");
        }else{
            events.add(new Place(getString(R.string.event1_name), getString(R.string.event1_description), R.drawable.ramadan));
            events.add(new Place(getString(R.string.event2_name), getString(R.string.event2_description), R.drawable.easter));
            events.add(new Place(getString(R.string.event3_name), getString(R.string.event3_description), R.drawable.sphinx));
            events.add(new Place(getString(R.string.event4_name), getString(R.string.event4_description), R.drawable.sun_ramsi));

        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("events_list",events);
    }
}
