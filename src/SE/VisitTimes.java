package SE;

public class VisitTimes {

    private String day;
    private String time;

    public VisitTimes() {
    }

    public VisitTimes(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "VisitTimes{" + "day=" + day + ", time=" + time + '}';
    }

}
