package android.example.tourguidend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    Context context;
    ArrayList<Place> places = new ArrayList<Place>();
    boolean fromMyFavourites;//to make favourite_btn Gone when display from MyFavourites


    public static ArrayList<Place> favouritePlaces = new ArrayList<Place>();
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //****** amethod to handel the item click
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView placeNameTxtView;
        TextView placeDescriptionTxtView;
        ImageView placeImageView;
        ImageView favouriteImg;
        boolean ismyFavourite;

        public PlaceViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            placeNameTxtView = itemView.findViewById(R.id.place_name_txtV);
            placeDescriptionTxtView = itemView.findViewById(R.id.description_hint_txtv);
            placeImageView = itemView.findViewById(R.id.image_place);
            favouriteImg = itemView.findViewById(R.id.favImg);
            ismyFavourite = false;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }


    public PlaceAdapter(Context context, ArrayList<Place> places, boolean fromMyFavourites) {
        this.context = context;
        this.places = places;
        this.fromMyFavourites = fromMyFavourites;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        PlaceViewHolder pvh = new PlaceViewHolder(view, mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceViewHolder placeViewHolder, int i) {

        final Place currentPlace = places.get(i);

        placeViewHolder.placeNameTxtView.setText(currentPlace.getPlaceName());
        placeViewHolder.placeDescriptionTxtView.setText(currentPlace.getDescription());
        placeViewHolder.placeImageView.setImageResource(currentPlace.getImage());
        placeViewHolder.favouriteImg.setImageResource(currentPlace.getImageFavoutite());

        //fromFavourites will be true when displaying from the home Fragment tab to make the favourite Image gone
        if (fromMyFavourites == false) {

            placeViewHolder.favouriteImg.setVisibility(View.VISIBLE);
            placeViewHolder.favouriteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!currentPlace.isFavouriteState()) {
                        currentPlace.setImageFavoutite(R.drawable.ic_favorite);
                        favouritePlaces.add(currentPlace);
                        currentPlace.setFavouriteState(true);
                        notifyItemChanged(placeViewHolder.getAdapterPosition());
                    } else {
                        currentPlace.setImageFavoutite(R.drawable.ic_favorite_border);
                        favouritePlaces.remove(currentPlace);
                        currentPlace.setFavouriteState(false);
                        notifyItemChanged(placeViewHolder.getAdapterPosition());
                    }

                }
            });
        } else {
            placeViewHolder.favouriteImg.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
