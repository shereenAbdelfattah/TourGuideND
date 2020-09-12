package android.example.tourguidend;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    String webSite;
    String telephoneNum;
    String placeName;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar_detailsAc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);

        // get the place from the fragment host
        Bundle data = getIntent().getExtras();
        Place place = data.getParcelable("placeItem");

        //*** make placeName, the title of the actionBar
        placeName = place.getPlaceName();
        getSupportActionBar().setTitle(placeName);

        address = place.getAddress();

        //defining the views
        ImageView imageView = findViewById(R.id.backdrop_detailsAc);
        TextView addressText = findViewById(R.id.address_txtV);
        TextView overViewText = findViewById(R.id.overView_textV);
        TextView expectationText = findViewById(R.id.expect_txtV);
        ImageButton callImage = findViewById(R.id.imgCall);
        ImageButton webImage = findViewById(R.id.web_imgV);

        ImageButton mapImage = findViewById(R.id.img_map);

        //set the views by its corresponding data from the place object
        addressText.setText(getString(R.string.addressPrefix) + address);
        overViewText.setText(place.getOverView());
        expectationText.setText(place.getYourExpect());
        imageView.setImageResource(place.getImage());

        webSite = place.getWebSite();
        telephoneNum = place.getPhoneNumber();
        //****************************
        webImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the web site for that place
                openWebSite();

            }
        });
        //****************************
        callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //send call for that place
                callMe();
            }
        });

        //********************

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }


    public void openWebSite() {

        //check is the place has a web site or not
        //if it has a site then open it
        if (webSite.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_available_site)
                    + placeName, Toast.LENGTH_LONG).show();

        } else {
            //intent to open the corresponding web site
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(webSite));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

    }

    //***********intent to call
    public void callMe() {

        //check is it has telephone number or not
        if (telephoneNum.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.noAvailablePhone)
                    + placeName, Toast.LENGTH_LONG).show();

        } else {
            //intent to call
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + telephoneNum));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    //***********open map ******
    public void openMap() {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=query" + placeName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
