import java.util.ArrayList;

public class ApplicationService {
    private ArrayList<JobApplication> applications =  new ArrayList<>();
    private int nextId = 1;
    public void addApplication(JobApplication application) {
        application.setId(nextId++);
        applications.add(application);
    }
    public void showAllApplications() {
        if (applications.isEmpty()) {
            System.out.println("暂无投递记录，请先选择 1 添加投递记录。");
            return;
        }
        System.out.println("共 " + applications.size() + " 条记录：");
        for (JobApplication application : applications) {
            System.out.println(application.toString());
        }
    }

    public void searchAByCompany(String companyName) {
        boolean found = false;
        for (JobApplication application : applications) {
            if (companyName.equals(application.getCompanyName())) {
                System.out.println(application);
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到岗位");
        }
    }

    public boolean deleteById(int id) {
        for(int i = 0;i<applications.size();i++) {
            if(applications.get(i).getId() == id) {
                applications.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateStatus(int id,String newStatus) {
        for (JobApplication application : applications) {
            if (application.getId() == id) {
                application.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }
}
