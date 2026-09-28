import java.util.ArrayList;
import java.util.List;

public class ApplicationService {
    private final List<JobApplication> applications = new ArrayList<>();

    // 编号从 1 开始，并且真正被用起来（原代码里 nextId++ 之后从未被读取）
    private int nextId = 1;

    public void addApplication(JobApplication application) {
        application.setId(nextId++);
        applications.add(application);
    }

    public void showAllApplications() {
        // 修复：列表为空时给出明确提示，不再静默无输出
        if (applications.isEmpty()) {
            System.out.println("暂无投递记录，请先选择 1 添加投递记录。");
            return;
        }

        System.out.println("共 " + applications.size() + " 条记录：");
        for (JobApplication application : applications) {
            System.out.println(application);
        }
    }

    public void searchByCompany(String companyName) {
        boolean found = false;
        for (JobApplication application : applications) {
            // 修复：把可能为 null 的一方放到 equals 的调用者里（或用参数.equals），避免 NPE
            if (companyName.equals(application.getCompanyName())) {
                System.out.println(application);
                found = true;
            }
        }

        // 修复：原来的 if (found) 写反了，导致"找到了反而说没找到，没找到却什么都不打印"
        if (!found) {
            System.out.println("未找到该公司的投递记录");
        }
    }

    public boolean deleteById(int id) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getId() == id) {
                applications.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateStatus(int id, String newStatus) {
        for (JobApplication application : applications) {
            if (application.getId() == id) {
                application.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }
}
