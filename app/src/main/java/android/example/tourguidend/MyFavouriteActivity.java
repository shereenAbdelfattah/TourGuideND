package android.example.tourguidend;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyFavouriteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Place> myFavourites;
    PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        Toolbar toolbar = findViewById(R.id.toolbar_favouriteAc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        myFavourites = intent.getParcelableArrayListExtra("favouriteList");
        recyclerView = findViewById(R.id.recycler_view_favourites);

        // set the second argument in the constructor to true to make
        // the visibility of favourite image to Gone
        adapter = new PlaceAdapter(this, myFavourites, true);
        //**********
        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //********
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // when click on the item it will diplay the place in the detailsActivity to display the current place
        adapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Place itemPlace = myFavourites.get(position);
                Toast.makeText(getApplicationContext(), itemPlace.getPlaceName(), Toast.LENGTH_LONG).show();

                openDetailsActivity(itemPlace);
            }
        });

    }

    private void initCollapsingToolbar() {

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbarFav);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbarfav);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.my_favourites_label));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("my_list",myFavourites);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myFavourites=savedInstanceState.getParcelableArrayList("my_list");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void openDetailsActivity(Place currentPlace) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("placeItem", currentPlace);
        startActivity(intent);
    }

}
