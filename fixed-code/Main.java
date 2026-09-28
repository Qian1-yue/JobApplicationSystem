import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationService applicationService = new ApplicationService();

        while (true) {
            showMenu();

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("请输入数字，而不是其他字符");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入数字，而不是其他字符");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAdd(scanner, applicationService);
                    break;
                case 2:
                    applicationService.showAllApplications();
                    break;
                case 3: {
                    System.out.println("请输入公司名称：");
                    String companyName = scanner.nextLine().trim();
                    applicationService.searchByCompany(companyName);
                    break;
                }
                case 4: {
                    int id = readInt(scanner, "请输入要删除的编号：");
                    if (applicationService.deleteById(id)) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("未找到编号为 " + id + " 的记录");
                    }
                    break;
                }
                case 5: {
                    int id = readInt(scanner, "请输入要修改的编号：");
                    String status = giveStatus(scanner);
                    if (applicationService.updateStatus(id, status)) {
                        System.out.println("修改成功，当前状态：" + status);
                    } else {
                        System.out.println("未找到编号为 " + id + " 的记录");
                    }
                    break;
                }
                case 0:
                    System.out.println("程序退出，感谢使用！");
                    return;
                default:
                    System.out.println("输入选项无效，请重新选择！");
                    break;
            }
        }
    }

    // 读一个整数：输入非法时不抛异常，而是重新询问
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入数字，而不是其他字符");
            }
        }
    }

    private static String giveStatus(Scanner scanner) {
        System.out.println("请选择状态：");
        System.out.println("1. 已投递");
        System.out.println("2. 笔试");
        System.out.println("3. 面试");
        System.out.println("4. Offer");
        System.out.println("5. 已拒绝");

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                        return "已投递";
                    case 2:
                        return "笔试";
                    case 3:
                        return "面试";
                    case 4:
                        // 与原菜单显示保持一致，原来返回的是小写 "offer"
                        return "Offer";
                    case 5:
                        return "已拒绝";
                    default:
                        // 修复：非法选项不再把 null 当状态返回，而是继续询问
                        System.out.println("输入选项无效，请重新选择！");
                }
            } catch (NumberFormatException e) {
                System.out.println("请输入数字，重新选择：");
            }
        }
    }

    public static void showMenu() {
        String eq = "=".repeat(5);
        String title = eq + " 求职投递管理系统 " + eq;
        System.out.println(title);
        System.out.println("1. 添加投递记录");
        System.out.println("2. 显示全部记录");
        System.out.println("3. 根据公司名称查询");
        System.out.println("4. 根据编号删除");
        System.out.println("5. 修改投递状态");
        System.out.println("0. 退出");
        System.out.println("请输入数字");
    }

    public static void handleAdd(Scanner scanner, ApplicationService applicationService) {
        System.out.println("请输入公司名称：");
        String companyName = scanner.nextLine().trim();
        System.out.println("请输入岗位名称：");
        String positionName = scanner.nextLine().trim();
        System.out.println("请输入城市：");
        String city = scanner.nextLine().trim();

        LocalDate applyDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println("请输入投递日期（格式：yyyy-MM-dd）：");
            String dateStr = scanner.nextLine().trim();
            try {
                applyDate = LocalDate.parse(dateStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("日期格式错误！");
            }
        }

        String status = giveStatus(scanner);

        // 编号由 ApplicationService 统一分配（原来硬编码 0，导致所有记录编号都是 0）
        JobApplication jobApplication =
                new JobApplication(0, companyName, positionName, city, applyDate, status);
        applicationService.addApplication(jobApplication);
        System.out.println("投递成功");
    }
}
