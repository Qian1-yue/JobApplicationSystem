import java.time.LocalDate;

public class JobApplication {
    private int id;
    private String companyName;
    private String positionName;
    private String city;
    private String status;
    private LocalDate Date;

    public JobApplication(int id,String companyName,String positionName,String city,LocalDate Date,String status) {
        this.id = id;
        this.companyName = companyName;
        this.positionName = positionName;
        this.city = city;
        this.Date = Date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "编号：" + id + ",公司： " + companyName + "，岗位: " + positionName + "，城市： " + city + "，日期： " + Date + ",状态： " + status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getDate() {
        return Date;
    }
    public void setDate(LocalDate Date) {
        this.Date = Date;
    }
}


