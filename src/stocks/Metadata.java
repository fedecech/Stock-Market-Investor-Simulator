package stocks;

public class Metadata{
    private String info;
    private String ticker;
    private String last_refreshed;
    private String output_size;
    private String time_zone;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getLast_refreshed() {
        return last_refreshed;
    }

    public void setLast_refreshed(String last_refreshed) {
        this.last_refreshed = last_refreshed;
    }

    public String getOutput_size() {
        return output_size;
    }

    public void setOutput_size(String output_size) {
        this.output_size = output_size;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public Metadata(String info, String ticker, String last_refreshed, String output_size, String time_zone) {
        this.info = info;
        this.ticker = ticker;
        this.last_refreshed = last_refreshed;
        this.output_size = output_size;
        this.time_zone = time_zone;
    }
}
