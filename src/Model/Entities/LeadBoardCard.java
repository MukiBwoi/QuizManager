package Model.Entities;

import java.io.File;

public class LeadBoardCard {
    private int auth_id;
    private File avatar;
    private String name;
    private int totalPoints;
    private int rank;

    public LeadBoardCard(int auth_id , File avatar, String name, int totalPoints, int rank) {
        this.setAvatar(avatar);
        this.setName(name);
        this.setTotalPoints(totalPoints);
        this.setRank(rank);
        this.setAuth_id(auth_id);
    }


    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "LeadBoardCard{" +
                "avatar=" + avatar +
                ", name='" + name + '\'' +
                ", totalPoints=" + totalPoints +
                ", rank=" + rank +
                '}';
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }
}
