package ui.enums;

public enum Milan {

    MILAN ("Милан", 38);

    String club;
    int point;


    Milan(String club, int point) {this.club = club; this.point = point;}
    public String getName() {return club;}
    public int getPoint() {return point;}
}
