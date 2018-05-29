package co.sovan.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Alert {
    @Id
    private String id;
    private String vin;
    private Date timeStamp;
    private String Details;
    private String priority;
    private String vmake;
    public Alert(){
        this.id= UUID.randomUUID().toString();
        this.timeStamp=new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getVmake() {
        return vmake;
    }

    public void setVmake(String vmake) {
        this.vmake = vmake;
    }
}
