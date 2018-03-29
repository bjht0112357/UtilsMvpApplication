package com.utils.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class Des:
 * Created by bjh on 2018/3/28.
 */
public class Entity implements Parcelable {
    private String str;
    private int position;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.str);
        dest.writeInt(this.position);
    }

    public Entity() {
    }

    protected Entity(Parcel in) {
        this.str = in.readString();
        this.position = in.readInt();

    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel source) {
            return new Entity(source);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };
}
