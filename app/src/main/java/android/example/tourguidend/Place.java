package android.example.tourguidend;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {

    private String placeName;
    private String description;
    private String address;
    private String webSite;
    private String phoneNumber;
    private String overView;
    private String yourExpect;
    private int image;
    private int imageFavourite;
    boolean favouriteState;

    public Place(String placeName, String description, String address, String webSite, String phoneNumber, String overView, String yourExpect, int image, int imageFavourite, boolean favouriteState) {
        this.placeName = placeName;
        this.description = description;
        this.address = address;
        this.webSite = webSite;
        this.phoneNumber = phoneNumber;
        this.overView = overView;
        this.yourExpect = yourExpect;
        this.image = image;
        this.imageFavourite = imageFavourite;
        this.favouriteState = favouriteState;
    }


    // i use this constructor to display the special events
    public Place(String placeName, String description, int image) {
        this.placeName = placeName;
        this.description = description;
        this.image = image;
    }


    protected Place(Parcel in) {
        placeName = in.readString();
        description = in.readString();
        address = in.readString();
        webSite = in.readString();
        phoneNumber = in.readString();
        overView = in.readString();
        yourExpect = in.readString();
        image = in.readInt();
        imageFavourite = in.readInt();
        favouriteState = in.readByte() != 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return webSite;
    }

    public void setLocation(String webSite) {
        this.webSite = webSite;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isFavouriteState() {
        return favouriteState;
    }

    public void setFavouriteState(boolean favouriteState) {
        this.favouriteState = favouriteState;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getImageFavoutite() {
        return imageFavourite;
    }

    public void setImageFavoutite(int imageFavourite) {
        this.imageFavourite = imageFavourite;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getYourExpect() {
        return yourExpect;
    }

    public void setYourExpect(String yourExpect) {
        this.yourExpect = yourExpect;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", webSite=" + webSite +
                ", phoneNumber=" + phoneNumber +
                ", image=" + image +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeName);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(webSite);
        dest.writeString(phoneNumber);
        dest.writeString(overView);
        dest.writeString(yourExpect);
        dest.writeInt(image);
        dest.writeInt(imageFavourite);
        dest.writeByte((byte) (favouriteState ? 1 : 0));
    }
}
