package Model.Entities;

import javafx.scene.image.Image;

public class LeadBoardItem {
    Image avatar;
    String name;
    int totalPoints;
    int rank;

    public LeadBoardItem(Image avatar, String name, int totalPoints, int rank) {
        this.avatar = avatar;
        this.name = name;
        this.totalPoints = totalPoints;
        this.rank = rank;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
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
}
