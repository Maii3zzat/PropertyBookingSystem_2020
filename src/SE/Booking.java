package SE;

import java.util.*;

public class Booking {

    private int id;
    private int userID;
    private int Pid;
    private String checkIn;
    private String checkOut;
    private String type;
    private int guestsNo;
    private String state;
    private int duration;

    public Booking() {
    }

    public Booking(int userID, String checkIn, String checkOut, int guestsNo, int duration) {
        this.userID = userID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestsNo = guestsNo;
        this.duration = duration;
    }

    public Booking(int id, int userID, int Pid, String checkIn, String checkOut, String type, int guestsNo, String state, int duration) {
        this.id = id;
        this.userID = userID;
        this.Pid = Pid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.type = type;
        this.guestsNo = guestsNo;
        this.state = state;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGuestsNo() {
        return guestsNo;
    }

    public void setGuestsNo(int guestsNo) {
        this.guestsNo = guestsNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", userID=" + userID + ", Pid=" + Pid + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", type=" + type + ", guestsNo=" + guestsNo + ", state=" + state + ", duration=" + duration + '}';
    }

}
