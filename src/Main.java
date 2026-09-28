import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationService applicationService = new ApplicationService();
        while (true) {
            showMenu();
            int choice;
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入数字，而不是其他字符");
                continue;
            }

            switch(choice) {
                case 1:
                    handleAdd(scanner, applicationService);
                    break;
                case 2:
                    applicationService.showAllApplications();
                    break;
                case 3:
                    System.out.println("请输出公司名称：");
                    String companyName = scanner.nextLine();
                    applicationService.searchAByCompany(companyName);
                    break;
                case 4:
                    System.out.println("请输入要删除的编号：");
                    int id = Integer.parseInt(scanner.nextLine());
                    applicationService.deleteById(id);
                    break;
                case 5:
                    System.out.println("请输入要修改的编号：");
                    int Id = Integer.parseInt(scanner.nextLine());
                    String status = giveStatus(scanner);
                    applicationService.updateStatus(Id, status);
                    break;
                case 0:
                    System.out.println("程序退出，感谢使用！");
                    return;
                default:
                    System.out.println("输入选项无效，请重新选择！");
                    break;
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
            String input = scanner.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入数字,重新选择：");
                continue;
            }

            return switch(choice) {
                    case 1 -> "已投递";
                    case 2 -> "笔试";
                    case 3 -> "面试";
                    case 4 -> "offer";
                    case 5 -> "已拒绝";
                    default-> {
                        System.out.println("输入选项无效，请重新选择！");
                        yield null;
                    }

            };
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

    public static void handleAdd(Scanner scanner,ApplicationService applicationService) {
        System.out.println("请输入公司名称：");
        String companyName = scanner.nextLine();
        System.out.println("请输入岗位名称：");
        String positionName = scanner.nextLine();
        System.out.println("请输入城市：");
        String city = scanner.nextLine();
        LocalDate Date = null;
        while (true) {
            System.out.println("请输入投递日期（格式：yyyy-MM-dd）：");
            String dateStr = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Date = LocalDate.parse(dateStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("日期格式错误！");
            }
        }
        String status = giveStatus(scanner);
        JobApplication jobApplication = new JobApplication(0,companyName,positionName,city,Date,status);
        applicationService.addApplication(jobApplication);
        System.out.println("投递成功");
    }

}

