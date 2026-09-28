import java.time.LocalDate;

public class JobApplication {
    private int id;
    private String companyName;
    private String positionName;
    private String city;
    private String status;

    // 原字段名是 Date（首字母大写，且与 java.util.Date 撞名），这里改名为 applyDate
    private LocalDate applyDate;

    public JobApplication(int id, String companyName, String positionName,
                          String city, LocalDate applyDate, String status) {
        // 关键修复：把参数赋值给字段，而不是用 getXxx() 取字段自己的默认值 null
        this.id = id;
        this.companyName = companyName;
        this.positionName = positionName;
        this.city = city;
        this.applyDate = applyDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "编号：" + id + "，公司：" + companyName + "，岗位：" + positionName
                + "，城市：" + city + "，日期：" + applyDate + "，状态：" + status;
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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }
}
