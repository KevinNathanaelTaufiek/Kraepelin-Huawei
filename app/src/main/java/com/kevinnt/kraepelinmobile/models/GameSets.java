package com.kevinnt.kraepelinmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GameSets implements Parcelable {

    private char operator;
    private String level;
    private int life;
    private int score;

    public GameSets() {
    }

    protected GameSets(Parcel in) {
        operator = (char) in.readInt();
        level = in.readString();
        life = in.readInt();
        score = in.readInt();
    }

    public static final Creator<GameSets> CREATOR = new Creator<GameSets>() {
        @Override
        public GameSets createFromParcel(Parcel in) {
            return new GameSets(in);
        }

        @Override
        public GameSets[] newArray(int size) {
            return new GameSets[size];
        }
    };

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt((int) operator);
        dest.writeString(level);
        dest.writeInt(life);
        dest.writeInt(score);
    }
}
