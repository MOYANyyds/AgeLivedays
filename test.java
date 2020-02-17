import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

/**
 * 根据生日计算精确的出生天数与年龄
 *
 * @author Regino
 * now(): 使用SimpleDateFormat类把2020-2-15转换为2020年2月15日，可以通过applyPattern(String pattern) 修改SimpleDateFormat对象的日期格式字符串
 * day(): 用System.currentTimeMillis()获取当前系统的毫秒值，运算并转换后得到精确天数
 * age(): 用Calender对象获取日期对象，比较当前日期与出生日期，先运算年份后运算日期得到精确年龄
 * test: 可循环测试
 */

public class test {
    static String now() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

    static int day(String line) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long daysMS = System.currentTimeMillis() - df.parse(line).getTime();
        long days = daysMS / 1000 / 60 / 60 / 24;
        return (int) days;
    }

    static int age(String line) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = df.parse(line);
        Calendar now = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        now.setTime(new Date());
        birthday.setTime(birth);
        int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < birthday.get(Calendar.DAY_OF_YEAR)) {
            age -= 1;
        }
        return age;
    }

    static boolean isFuture(String line) throws ParseException {
        return (day(line) <= 0);
    }

    public static void main(String[] args) throws ParseException {
        boolean repeat = false;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的出生日期（格式：1999-9-9）：");
            String line = sc.nextLine();
            System.out.println("现在是" + now() + ",");
            if (isFuture(line)) {
                System.out.println("您好，未来的朋友！");
            } else {
                System.out.println("您今年" + age(line) + "岁，已生活" + day(line) + "天,");
            }
            System.out.println("是否重新输入？（1-是；2-否）");
            if (sc.nextInt() == 1) {
                repeat = true;
                System.out.println("----------");
            } else {
                System.out.println("再见！");
                System.exit(0);
            }
        } while (repeat);
    }
}
