package SE;

import SE.interfaces.TenantReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Review implements TenantReadOnly {

    int id;
    String description;
    double rating;
    //User user;
    //Property prop;

    DB x = new DB();
    ArrayList<Review> revs = new ArrayList<Review>();

    public Review() {
    }

    public Review(String description, double rating) {
        this.description = description;
        this.rating = rating;
    }

    public Review(int id, String description, double rating) {
        this.id = id;
        this.description = description;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void addReview(String d, double r) {
        Review rev = new Review(d, r);
        revs.add(rev);
        x.giveReview(revs);
    }

    public void viewReviews() {
        revs = x.getReviews();
        for (int i = 0; i < revs.size(); i++) {
            System.out.println(revs.get(i).id + " , " + revs.get(i).description + " , " + revs.get(i).rating);
        }
    }

    @Override
    public ResultSet searchProperty(String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewProperty(int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rentProperty(String name, Booking b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CheckAvailableProperty(String name, String checkIn, String checkOut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shareProperty(String name, Booking b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Property chooseProperty(int pid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
