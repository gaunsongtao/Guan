package com.bawei.day11;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo {

    private int imageId;
    private String name;

    public UserInfo(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    protected UserInfo(Parcel in) {
        imageId = in.readInt();
        name = in.readString();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
    }

}
