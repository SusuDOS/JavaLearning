# Java基础

## Switch结构体

```java
import java.util.Scanner;

class switchMonth {
    public switchMonth() {
    }

    public void switchDemo(int month) {
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("冬季");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("秋季");
                break;
        }
    }
}

public class allTestCode {
    public static void main(String[] args) {
        int exit = 1;
        do {
            System.out.println("请输入一个数值，退出输入0：");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            switchMonth switchMonth = new switchMonth();
            switchMonth.switchDemo(i);
            exit = i;
        } while (exit != 0);

    }
}
```

## Random

```java
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        //创建对象
        Random r = new Random();
        //用循环获取10个随机数
        for (int i = 0; i < 10; i++) {
            //获取随机数
            int number = r.nextInt(10);
            System.out.println("number:" + number);
        }
        //需求：获取一个1-100之间的随机数
        int x = r.nextInt(100) + 1;
        System.out.println(x);
    }
}

```
## Random猜数字

```java
import java.util.Random;
import java.util.Scanner;

public class RandomTest {
    public static void main(String[] args) {
        //要完成猜数字的游戏，首先需要有一个要猜的数字，使用随机数生成该数字，范围1到100
        Random r = new Random();
        int number = r.nextInt(100) + 1;
        while (true) {
            //使用程序实现猜数字，每次均要输入猜测的数字值，需要使用键盘录入实现
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你要猜的数字：");
            int guessNumber = sc.nextInt();
            //比较输入的数字和系统产生的数据，需要使用分支语句。
            //这里使用if..else..if..格式，根据不同情况进行猜测结果显示
            if (guessNumber > number) {
                System.out.println("你猜的数字" + guessNumber + "大了");
            } else if (guessNumber < number) {
                System.out.println("你猜的数字" + guessNumber + "小了");
            } else {
                System.out.println("恭喜你猜中了");
                break;
            }
        }
    }
}
```
## 不重复的随机数

```java
package packageTwo;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

// 获取10个1-20之间的随机数.
public class RandomNX {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>();
        // 创建随机数对象
        Random r = new Random();
        // 判断集合的长度是不是小于10
        while (set.size() < 10) {
            // 产生一个随机数，添加到集合
            int number = r.nextInt(20) + 1;
            set.add(number);
        }
        // 遍历集合
        for (Integer i : set) {
            System.out.println(i);
        }
    }
}
```
## 数组

`int[] arr` 与 `int arr[]` 表达意思一样。

```java
# 定义
int[] arr = new int[5];
int[] arr = {12, 45, 98, 73, 60}
```

```java
public class ArrayTest {
    public static void main(String[] args) {
        //定义数组
        int[] arr = {12, 45, 98, 73, 60};
        
        int max = arr[0];
        //与数组中剩余的数据逐个比对，每次比对将最大值保存到变量中
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        //循环结束后打印变量的值
        System.out.println("max:" + max);
    }
}
```
## 变量有效

```java
public class ArrayTest {
    public static void main(String[] args) {
        int a = 10;
        add(a);
        System.out.println(a); //10
    }

    static void add(int a) {
        a = a + 10;
    }
}
```

## 带参方法调用

```java
public class MethodTest {
    public static void main(String[] args) {
        //定义一个数组，用静态初始化完成数组元素初始化
        int[] arr = {12, 45, 98, 73, 60};
        //调用获取最大值方法，用变量接收返回结果
        int number = getMax(arr);
        //把结果输出在控制台
        System.out.println("number:" + number); //98
    }

    //定义一个方法，用来获取数组中的最大值
    /*
    两个明确：
    返回值类型：int
    参数：int[] arr
    */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        return max;
    }
}
```
## 不死神兔

```java
/*
思路：
1:为了存储多个月的兔子对数，定义一个数组，用动态初始化完成数组元素的初始化，长度为20
2:因为第1个月，第2个月兔子的对数是已知的，都是1，所以数组的第1个元素，第2个元素值也都是1
3:用循环实现计算每个月的兔子对数
4:输出数组中最后一个元素的值，就是第20个月的兔子对数
*/
public class BuShi {
    public static void main(String[] args) {
        //为了存储多个月的兔子对数，定义一个数组，用动态初始化完成数组元素的初始化，长度为20
        int[] arr = new int[20];
        //因为第1个月，第2个月兔子的对数是已知的，都是1，所以数组的第1个元素，第2个元素值也都是1
        arr[0] = 1;
        arr[1] = 1;
        //用循环实现计算每个月的兔子对数
        for (int x = 2; x < arr.length; x++) {
            arr[x] = arr[x - 2] + arr[x - 1];
        }
        //输出数组中最后一个元素的值，就是第20个月的兔子对数
        System.out.println("第二十个月兔子的对数是：" + arr[19]);
    }
}

```

## 百钱百鸡

```java
/*
        思路：
        1:第1层循环，用于表示鸡翁的范围，初始化表达式的变量定义为 x=0，判断条件是x<=20
        2:第2层循环，用于表示鸡母的范围，初始化表达式的变量定义为 y=0，判断条件是y<=33
        3:这个时候，用于表示鸡雏的变量 z = 100 – x – y
        4:判断表达式 z%3==0 和表达式 5*x + 3*y + z/3 = 100 是否同时成立，如果成立，输出对应的
        x，y，z 的值，就是对应的鸡翁，鸡母，鸡雏的值
        */
public class BaiQian {
    public static void main(String[] args) {
        //第1层循环，用于表示鸡翁的范围，初始化表达式的变量定义为 x=0，判断条件是x<=20
        for (int x = 0; x <= 20; x++) {
            //第2层循环，用于表示鸡母的范围，初始化表达式的变量定义为 y=0，判断条件是y<=33
            for (int y = 0; y <= 33; y++) {
                //这个时候，用于表示鸡雏的变量 z = 100 – x – y
                int z = 100 - x - y;
                //判断表达式 z%3==0 和表达式 5*x + 3*y + z/3 = 100 是否同时成立
                if (z % 3 == 0 && 5 * x + 3 * y + z / 3 == 100) {
                    System.out.println(x + "," + y + "," + z);
                }
            }
        }
    }
}
```

## 继承多态

```java
class Animal {
    public int age = 10;

    public void eat() {
        System.out.println("动物吃东西");
    }
}


class Cat extends Animal {
    public int age = 20;
    public int weight = 10;

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    public void playGame() {
        System.out.println("猫捉迷藏");
    }
}


public class DuoTai {
    public static void main(String[] args) {
        /*
        多态演示，继承设定上写方法，方法为子类;
        继承的变量默认不重写覆盖,而是只使用Animal的属性.
        */
        Animal a = new Cat();
        System.out.println(a.age);
        a.eat();
    }
}
/*
输出结果:
10
猫吃鱼 
*/
```
## 抽象方法

```java
abstract class Animal {
    int age = 10;
    private final String city = "北京";

    public Animal() {
    }

    public Animal(int age) {
        this.age = age;
    }

    public void show() {
        age = 40;
        System.out.println(age);
        System.out.println(city);
    }

    // 有抽象方法必定抽象类，其他与普通类的成员方法，成员变量一样，注意抽象方法需要重写:注意抽象方法定义格式
    public abstract void eat();
}
```

## 权限修饰符

```html
Java中，可以使用访问控制符来保护对类、变量、方法和构造方法的访问。`Java` 支持 4 种不同的访问权限。
```

- private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）

- default (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。

- protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。

- public : 对所有类可见。使用对象：类、接口、变量、方法。

    ![权限修饰符展:](../img/%E6%9D%83%E9%99%90%E4%BF%AE%E9%A5%B0%E7%AC%A6.JPG)


## 接口

```java
interface Inter {
    // public static final默认修饰符可略.
    public static final int num = 30;

    // public abstract默认修饰符可略.
    public abstract void method();

    // 无构造函数,至少我没有见过.
}
```
## 成员内部类

```java
class Outer {
    private int num = 10;

    public class Inner {
        public void show() {
            System.out.println(num);
        }
    }

    public void method() {
        Inner i = new Inner();
        i.show();
    }
}

public class InnerDemo {
    public static void main(String[] args) {
        // 第一种调用
        Outer.Inner oi = new Outer().new Inner();
        oi.show();
        // 第二种调用：私有化内部类后，通过方法
        Outer o = new Outer();
        o.method();
    }
}
```
## 局部内部类

```java
class Outer {
    private int num = 10;

    public void method() {
        // 局部内部类，镶嵌在成员方法中
        int num2 = 20;
        class Inner {
            public void show() {
                System.out.println(num);
                System.out.println(num2);
            }
        }
        Inner i = new Inner();
        i.show();
    }
}

public class OuterDemo {
    public static void main(String[] args) {
        Outer o = new Outer();
        o.method();
    }
}
```
## 匿名内部类

```java
interface AnonymousInter {
    void method();
}

public class Test {
    public static void main(String[] args) {
        new AnonymousInter() {
            @Override
            public void method() {
                System.out.println("匿名内部类.");
            }
        }.method();// 直接调用方法
    }
}
/*
匿名内部类.
*/
```
## 匿名内部类演示

```java
package testPackage;

interface Jumpping {
    void jump();
}

class Cat implements Jumpping {
    @Override
    public void jump() {
        System.out.println("猫可以跳高了");
    }
}

class Dog implements Jumpping {
    @Override
    public void jump() {
        System.out.println("狗可以跳高了");
    }
}

class JumppingOperator {
    public void method(Jumpping j) {
        j.jump();
    }
}

class JumppingDemo {
    public static void main(String[] args) {
        //需求：创建接口操作类的对象，调用method方法
        JumppingOperator jo = new JumppingOperator();
        Jumpping j = new Cat();
        jo.method(j);
        Jumpping j2 = new Dog();
        jo.method(j2);
        System.out.println("--------------");

        /*
        匿名内部类的简化,相对比较重要的方式.
        */
        jo.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("猫可以跳高了");
            }
        });
        // 匿名内部类的简化
        jo.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("狗可以跳高了");
            }
        });
    }
}
```
## Int与String互转

```java
// 核心就两行
String s2 = String.valueOf(num);
int y = Integer.parseInt(str);
```

```java
package testPackage;

public class Int2String {
    public static void main(String[] args) {
        //int -->>String
        int number = 100;
        //方式1
        String s1 = number + "";
        System.out.println(s1);
        //方式2 重要
        String s2 = String.valueOf(number);
        System.out.println(s2);
        System.out.println("--------");

        //String -->> int
        String s = "100";
        Integer i = Integer.valueOf(s);
        int x = i.intValue();
        System.out.println(x);
        //方式2 重要
        int y = Integer.parseInt(s);
        System.out.println(y);
    }
}
```
## Int与String互转演示

```java
package testPackage;

import java.util.Arrays;

public class OrderString {
    public static void main(String[] args) {
        // 目标：递增排序返回字符串
        String s = "91 27 46 38 50";

        // 字符串>>String数组
        String[] strArray = s.split(" ");

        // String数组>>>int数组
        int[] arr = new int[strArray.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strArray[i]);
        }

        // int数组排序
        Arrays.sort(arr);

        // int数组中>>>字符串:StringBuilder实现
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            // 最后一个不拼接空格
            if (i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(" ");
            }
        }
        String result = sb.toString();
        System.out.println(result);
    }
}
```
## Date使用

```java
package testPackage;

import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        //创建日期对象
        Date date = new Date();
        //public long getTime():获取的是日期对象从1970年1月1日 00:00:00到现在的毫秒值
        System.out.println(date.getTime() * 1.0 / 1000 / 60 / 60 / 24 / 365 + "年");
        //public void setTime(long time):设置时间，给的是毫秒值
        // long time = 1000*60*60;
        long time = System.currentTimeMillis();
        date.setTime(time);
        System.out.println(date);
    }
}
```
## SimpleDateFormat使用

```java
package testPackage;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) throws ParseException {
        // 创建日期对象
        Date d = new Date();
        System.out.println("Date:" + d);

        // 日期>>>String
        String s1 = DateUtils.dateToString(d, "yyyy年MM月dd日 HH:mm:ss");
        System.out.println(s1);

        // 日期>>>String
        String s2 = DateUtils.dateToString(d, "yyyy年MM月dd日");
        System.out.println(s2);

        // 日期>>>String
        String s3 = DateUtils.dateToString(d, "HH:mm:ss");
        System.out.println(s3);
        System.out.println("--------");

        // String>>Date
        String s = "2022-08-09 12:12:12";
        Date dd = DateUtils.stringToDate(s, "yyyy-MM-dd HH:mm:ss");
        System.out.println(dd);
/*
        控制台显示：
        Date:Tue Jun 14 17:02:24 CST 2022
        2022年06月14日 17:02:24
        2022年06月14日
        17:02:24
        --------
        Tue Aug 09 12:12:12 CST 2022
*/

    }
}

class DateUtils {
    private DateUtils() {
    }

    // Date>>>String
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }

    // String>>>Date
    public static Date stringToDate(String s, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(s);
        return d;
    }
}

```
## 日历Calendar

```java
package testPackage;

import java.util.Calendar;

public class CalendarDemo {

    public static void main(String[] args) {
        // 获取日历类对象
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        System.out.println(year + "年" + month + "月" + date + "日");

        c.set(2050, 10, 10);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        System.out.println(year + "年" + month + "月" + date + "日");
    }
}

```

```java
package testPackage;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarDemo {

    public static void main(String[] args) {
        // 任意年份的2月天数 键盘录入任意的年份
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入年：");
        int year = sc.nextInt();
        // 设置日历对象的年-月-日
        Calendar c = Calendar.getInstance();
        c.set(year, 2, 1);
        // 3月1日往前推一天，就是2月的最后一天
        c.add(Calendar.DATE, -1);

        int date = c.get(Calendar.DATE);
        System.out.println(year + "年的2月份有" + date + "天");
    }
}

```
## 异常处理

```java
package testPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("开始...");
        try {
            method2();
            // 以下的两个异常，使用起来没感觉到区别.
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束!");
    }

    // 编译时异常
    public static void method2() throws ParseException {
        // 故意不匹配而报错.
        String s = "2048/08-09";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(s);
        System.out.println(d);
    }

    // 运行时异常
    public static void method() throws ArrayIndexOutOfBoundsException {
        int[] arr = {1, 2, 3};
        System.out.println(arr[3]);
    }
}
```
## 自定义异常

```java
// 核心演示部分.
checkScore(int score) throws ScoreException {
    throw new ScoreException("你给的分数有误，分数应该在0-100之间!");
}
```

```java
package testPackage;

import java.util.Scanner;

// 自定义异常:ScoreException
class ScoreException extends Exception {
    public ScoreException() {
    }

    public ScoreException(String message) {
        super(message);
    }
}

// 特别注意throw与throws的使用位置.

class Teacher {
    public void checkScore(int score) throws ScoreException {
        if (score < 0 || score > 100) {
            throw new ScoreException("你给的分数有误，分数应该在0-100之间!");
        } else {
            System.out.println("成绩正常");
        }
    }
}

public class DefineException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入分数：");
        int score = sc.nextInt();
        Teacher t = new Teacher();
        try {
            t.checkScore(score);
        } catch (ScoreException e) {
            e.printStackTrace();
        }
        System.out.println("运行到末尾...");
    }
}
/*
ScoreException: 你给的分数有误，分数应该在0-100之间!
	at Teacher.checkScore(Main.java:16)
	at Main.main(Main.java:27)

运行到末尾...
*/
```
## 集合的三种迭代方式

```java
package testPackage;

/*演示三种迭代集合的方式:
需要注意迭代器迭代的并发修改异常,用普通for遍历即可.*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Student {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }
}

public class ThreeIterator {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        // 创建学生对象
        Student s1 = new Student("林青霞", 30);
        Student s2 = new Student("张曼玉", 35);
        Student s3 = new Student("王祖贤", 33);

        // 把学生添加到集合
        list.add(s1);
        list.add(s2);
        list.add(s3);
        //第一种方式>>>迭代器：集合特有的遍历方式
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            System.out.println(s.getName() + "," + s.getAge());
        }
        System.out.println("--------");
        //第二种方式>>>普通for：带有索引的遍历方式
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getName() + "," + s.getAge());
        }
        System.out.println("--------");
        //第三种方式>>>增强for：最方便的遍历方式
        for (Student s : list) {
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}

```

## Hash集合演示

```java
package testPackage;

import java.util.HashSet;
import java.util.Objects;

class StudentItem {
    private String name;
    private int age;

    public StudentItem() {
    }

    public StudentItem(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentItem)) return false;
        StudentItem that = (StudentItem) o;
        return getAge() == that.getAge() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }
}

public class HashCollection {
    public static void main(String[] args) {
        // 创建HashSet集合对象,对象属性一致则认为为相同对象.
        HashSet<StudentItem> hs = new HashSet<StudentItem>();

        // 创建学生对象
        StudentItem s1 = new StudentItem("林青霞", 30);
        StudentItem s2 = new StudentItem("张曼玉", 35);
        StudentItem s3 = new StudentItem("王祖贤", 33);
        StudentItem s4 = new StudentItem("王祖贤", 33);

        // 把学生添加到集合
        hs.add(s1);
        hs.add(s2);
        hs.add(s3);
        hs.add(s4);

        // 遍历集合(增强for)
        for (StudentItem s : hs) {
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}

```
## 自然排序Comparable的使用

```java
import java.util.Objects;
import java.util.TreeSet;

class Student implements Comparable<Student> {
    // 按照属性排序：年龄>姓名的字母

    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student s) {
        // 按照年龄从小到大排序.
        // 正数：递增顺序;负数递减;0存储的结果只有一个.
        int num = this.age - s.age;
        //年龄相同时，按照姓名的字母顺序排序
        int num2 = num == 0 ? this.name.compareTo(s.name) : num;
        return num2;
    }
}

public class collectionOrder {
    public static void main(String[] args) {
        TreeSet<Student> ts = new TreeSet<Student>();
        // 创建学生对象
        Student s1 = new Student("xishi", 29);
        Student s2 = new Student("wangzhaojun", 28);
        Student s3 = new Student("diaochan", 30);
        Student s4 = new Student("yangyuhuan", 33);
        Student s5 = new Student("linqingxia", 33);
        Student s6 = new Student("linqingxia", 33);
        // 把学生添加到集合
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        ts.add(s6);
        // 遍历集合
        for (Student s : ts) {
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}
```

## 比较器排序Comparator的使用

```java
package packageTwo;

// 个人感觉有点像匿名内部类的调用，上面的那个实现感觉是似乎要好一定.

import java.util.Comparator;
import java.util.TreeSet;

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class TreeSetDemo {
    public static void main(String[] args) {
        // 创建集合对象
        TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>
                () {
            @Override
            public int compare(Student s1, Student s2) {
                // this.age - s.age
                // s1,s2
                int num = s1.getAge() - s2.getAge();
                int num2 = num == 0 ? s1.getName().compareTo(s2.getName()) : num;
                return num2;
            }
        });
        // 创建学生对象
        Student s1 = new Student("xishi", 29);
        Student s2 = new Student("wangzhaojun", 28);
        Student s3 = new Student("diaochan", 30);
        Student s4 = new Student("yangyuhuan", 33);
        Student s5 = new Student("linqingxia", 33);
        Student s6 = new Student("linqingxia", 33);
        // 把学生添加到集合
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        ts.add(s6);
        // 遍历集合
        for (Student s : ts) {
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}
```
## 按属性优先级存为集合Set

```java
package storeCollection;

import java.util.Comparator;
import java.util.TreeSet;

public class storeCollection {
    public static void main(String[] args) {
        TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>
                () {
            @Override
            public int compare(Student s1, Student s2) {
                // 主要条件
                int num = s2.getSum() - s1.getSum();
                // 次要条件
                int num2 = num == 0 ? s1.getChinese() - s2.getChinese() : num;
                //第三条件
                int num3 = num2 == 0 ? s1.getName().compareTo(s2.getName()) : num2;
                return num3;
            }
        });
        // 创建学生对象
        Student s1 = new Student("林青霞", 98, 100);
        Student s8 = new Student("林青霞", 98, 100);
        Student s2 = new Student("张曼玉", 95, 95);
        Student s3 = new Student("王祖贤", 100, 93);
        Student s4 = new Student("柳岩", 100, 97);
        Student s5 = new Student("风清扬", 98, 98);
        Student s6 = new Student("左冷禅", 97, 99);
        Student s7 = new Student("赵云", 97, 99);
        // 把学生对象添加到集合
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        ts.add(s6);
        ts.add(s7);
        ts.add(s8);
        // 遍历集合
        for (Student s : ts) {
            System.out.println(s.getName() + "," + s.getChinese() + "," + s.getMath() + "," + s.getSum());
        }
        System.out.println("ts.size():" + ts.size());

    }
}

class Student {
    private String name;
    private int chinese;
    private int math;

    public Student(String name, int chinese, int math) {
        this.name = name;
        this.chinese = chinese;
        this.math = math;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getSum() {
        return this.chinese + this.math;
    }
}
```
## 泛型

```java
package storeCollection;

interface Generic<T> {
    void show(T t);
}

class GenericImpl<T> implements Generic<T> {
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}

public class FanXing {
    public static void main(String[] args) {
        Generic<String> g1 = new GenericImpl<String>();
        g1.show("林青霞");
        Generic<Integer> g2 = new GenericImpl<Integer>();
        g2.show(30);
    }
}

```
## 泛型通配符
```java
package storeCollection;

import java.util.ArrayList;
import java.util.List;

public class CommonFanXing {
    public static void main(String[] args) {
        //类型通配符：<?>
        List<?> list1 = new ArrayList<Object>();
        List<?> list2 = new ArrayList<Number>();
        List<?> list3 = new ArrayList<Integer>();

        System.out.println("--------");
        //类型通配符上限：<? extends 类型> ?为类型及其子类
        List<? extends Number> list5 = new ArrayList<Number>();
        List<? extends Number> list6 = new ArrayList<Integer>();

        System.out.println("--------");
        //类型通配符下限：<? super 类型> ?为类型及其父类
        List<? super Number> list7 = new ArrayList<Object>();
        List<? super Number> list8 = new ArrayList<Number>();
    }
}

```
## 可变参数函数

```java
package storeCollection;

public class VariableParam {
    public static void main(String[] args) {
        System.out.println(sum(10, 20));
        System.out.println(sum(10, 20, 30));
        System.out.println(sum(10, 20, 30, 40));
        System.out.println(sum(10, 20, 30, 40, 50));
        System.out.println(sum(10, 20, 30, 40, 50, 60));
        System.out.println(sum(10, 20, 30, 40, 50, 60, 70));
        System.out.println(sum(10, 20, 30, 40, 50, 60, 70, 80, 90, 100));
    }

    public static double sum(double floatParam, int... a) {
        double sum = floatParam;
        for (int i : a) {
            sum += i;
        }
        return sum;
    }
}
```
## java中的可变参数函数

```java
Arrays工具类中有一个静态方法：
public static List asList(T... a)：返回由指定数组支持的固定大小的列表，返回的集合不能做增删操作，可以做修改操作。

测试失败，找不到of函数.
List接口中有一个静态方法：
public static List of(E... elements)：返回包含任意数量元素的不可变列表，返回的集合不能做增删改操作。

测试失败，找不到of函数.
Set接口中有一个静态方法：
public static Set of(E... elements) ：返回一个包含任意数量元素的不可变集合，在给元素的时候，不能给重复的元素返回的集合不能做增删操作，没有修改的方法。

```
```java
package storeCollection;

import java.util.Arrays;
import java.util.List;

public class VariableParam {
    public static void main(String[] args) {
        // public static <T> List<T> asList(T... a)：返回由指定数组支持的固定大小的列表
        List<String> list = Arrays.asList("hello", "world", "java");
        // list.add("javaee"); //UnsupportedOperationException
        // list.remove("world"); //UnsupportedOperationException
        list.set(1, "javaee");
        System.out.println(list);
    }
}
```

## Java中Map集合遍历
```java
package storeCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapCollection {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        // 添加元素
        map.put("张无忌", "赵敏");
        map.put("郭靖", "黄蓉");
        map.put("杨过", "小龙女");

        // 键集合>>map.get(key)实现
        Set<String> keySet = map.keySet();

        // 遍历键集合：增强for实现
        for (String key : keySet) {
            // 根据键去找值。用get(Object key)方法实现
            String value = map.get(key);
            System.out.println(key + "," + value);
        }

        // 键值对 集合>>geKey,getValue.
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        // 遍历键值对对象的集合，得到每一个键值对对象
        for (Map.Entry<String, String> me : entrySet) {
            // 根据键值对对象获取键和值
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key + "," + value);
        }
    }
}
```
## Map值为引用类型遍历
```java
package HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Student {
    String name;
    int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class HashMapCode {
    public static void main(String[] args) {
        // 创建HashMap集合对象,键是学号(String)，值是学生对象(Student)
        HashMap<String, Student> stringStudentHashMap = new HashMap<String, Student>();
        // 创建学生对象
        Student s1 = new Student("林青霞", 30);
        Student s2 = new Student("张曼玉", 35);
        Student s3 = new Student("王祖贤", 33);

        // 把学生添加到集合
        stringStudentHashMap.put("itheima001", s1);
        stringStudentHashMap.put("itheima002", s2);
        stringStudentHashMap.put("itheima003", s3);

        //方式1：键找值
        Set<String> keySet = stringStudentHashMap.keySet();
        for (String key : keySet) {
            Student value = stringStudentHashMap.get(key);
            System.out.println(key + "," + value.getName() + "," + value.getAge());
        }
        System.out.println("--------");
        //方式2：键值对
        Set<Map.Entry<String, Student>> entrySet = stringStudentHashMap.entrySet();
        for (Map.Entry<String, Student> me : entrySet) {
            String key = me.getKey();
            Student value = me.getValue();
            System.out.println(key + "," + value.getName() + "," + value.getAge());
        }
    }
}
```
## Map键为引用类型遍历
```java
package HashMap;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() && getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }
}

public class HashMapCode {
    public static void main(String[] args) {
        // 创建HashMap集合对象,键是学生对象(Student),值是地点String类型.
        HashMap<Student, String> studentStringHashMap = new HashMap<Student, String>();

        // 键值对，键唯一，所以对象需要重写equals和hashcode方法

        // 创建学生对象
        Student s1 = new Student("林青霞", 30);
        Student s2 = new Student("张曼玉", 35);
        Student s3 = new Student("王祖贤", 33);
        Student s4 = new Student("王祖贤", 33);

        // 把学生添加到集合
        studentStringHashMap.put(s1, "西安");
        studentStringHashMap.put(s2, "武汉");
        studentStringHashMap.put(s3, "郑州");
        studentStringHashMap.put(s4, "北京");

        // 遍历集合
        Set<Student> keySet = studentStringHashMap.keySet();
        for (Student key : keySet) {
            String value = studentStringHashMap.get(key);
            System.out.println(key.getName() + "," + key.getAge() + "," + value);
        }
    }
}
```
## ArrayList与HashMap嵌套

**hashMap有实现非引用型键的唯一，若是引用型的键则需要自己重写hashCode和Equals方法来保证唯一性.**

- 集合嵌套之ArrayList嵌套HashMap

创建一个ArrayList集合，存储三个元素;每一个元素都是HashMap
每一个HashMap的键和值都是String，并遍历。

```java
package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HashMapCode {

    // Arraylist,元素是HashMap,并且遍历.
    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
        // 创建HashMap集合，并添加键值对元素
        HashMap<String, String> hm1 = new HashMap<String, String>();
        hm1.put("孙策", "大乔");
        hm1.put("周瑜", "小乔");
        // 把HashMap作为元素添加到ArrayList集合
        array.add(hm1);
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("郭靖", "黄蓉");
        hm2.put("杨过", "小龙女");
        // 把HashMap作为元素添加到ArrayList集合
        array.add(hm2);
        HashMap<String, String> hm3 = new HashMap<String, String>();
        hm3.put("令狐冲", "任盈盈");
        hm3.put("林平之", "岳灵珊");
        // 把HashMap作为元素添加到ArrayList集合
        array.add(hm3);
        // 遍历ArrayList集合
        for (HashMap<String, String> hm : array) {
            Set<String> keySet = hm.keySet();
            for (String key : keySet) {
                String value = hm.get(key);
                System.out.println(key + "," + value);
            }
        }
    }
}
```
- HashMap嵌套ArrayList

创建一个HashMap集合，存储三个键值对元素，每一个键值对元素的键是String，值是ArrayList；每一个ArrayList的元素是String，并遍历。

```java
package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HashMapCode {

    public static void main(String[] args) {
        // 创建HashMap集合
        HashMap<String, ArrayList<String>> hashMapArrayList = new HashMap<String, ArrayList<String>>();

        // 创建ArrayList集合，并添加元素
        ArrayList<String> sgyy = new ArrayList<String>();
        sgyy.add("诸葛亮");
        sgyy.add("赵云");

        // 把ArrayList作为元素添加到HashMap集合
        hashMapArrayList.put("三国演义", sgyy);
        ArrayList<String> xyj = new ArrayList<String>();
        xyj.add("唐僧");
        xyj.add("孙悟空");

        // 把ArrayList作为元素添加到HashMap集合
        hashMapArrayList.put("西游记", xyj);
        ArrayList<String> shz = new ArrayList<String>();
        shz.add("武松");
        shz.add("鲁智深");

        // 把ArrayList作为元素添加到HashMap集合
        hashMapArrayList.put("水浒传", shz);

        // 遍历HashMap集合
        Set<String> keySet = hashMapArrayList.keySet();
        for (String key : keySet) {
            System.out.println(key);
            ArrayList<String> value = hashMapArrayList.get(key);
            for (String s : value) {
                System.out.println("\t" + s);
            }
        }
    }
}

```
## HashMap统计字符种类

```java
package HashMap;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class HashMapCode {
    public static void main(String[] args) {
        // 键盘录入一个字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String line = sc.nextLine();

        // 创建HashMap集合，键是Character，值是Integer
        // HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        TreeMap<Character, Integer> hm = new TreeMap<Character, Integer>();
        // 遍历字符串，得到每一个字符
        for (int i = 0; i < line.length(); i++) {
            char key = line.charAt(i);
            // 拿得到的每一个字符作为键到HashMap集合中去找对应的值，看其返回值
            Integer value = hm.get(key);

            // value为空说明字符第一次出现，同时也说明了：缺省为null.
            if (value == null) {
                // 如果返回值是null：说明该字符在HashMap集合中不存在，就把该字符作为键，1作为值存储
                hm.put(key, 1);
            } else {
                // 如果返回值不是null：说明该字符在HashMap集合中存在，把该值加1，然后重新存储该字符和对应的值
                value++;
                hm.put(key, value);
            }
        }
        // 遍历HashMap集合，得到键和值，按照要求进行拼接
        StringBuilder sb = new StringBuilder();
        Set<Character> keySet = hm.keySet();
        for (Character key : keySet) {
            Integer value = hm.get(key);
            sb.append(key).append("(").append(value).append(")");
        }
        String result = sb.toString();
        // 输出结果
        System.out.println(result);
    }
}

```
## 斗地主发牌

```java
package HashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PokerDemo {

    // 斗地主创建牌演示.

    public static void main(String[] args) {
        //创建HashMap，键是编号，值是牌
        HashMap<Integer, String> hm = new HashMap<Integer, String>();

        //创建ArrayList，存储编号
        ArrayList<Integer> array = new ArrayList<Integer>();

        // 牌面结构为：ArrayList(<index,colorNumber>),size()=52+大小王

        //创建花色数组和点数数组
        String[] colors = {"♦", "♣", "♥", "♠"};
        String[] numbers = {
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "J",
                "Q",
                "K",
                "A",
                "2",
        };

        //从0开始往HashMap里面存储编号，并存储对应的牌。同时往ArrayList里面存储编号
        int index = 0;
        for (String number : numbers) {
            for (String color : colors) {
                hm.put(index, color + number);
                array.add(index);
                index++;
            }
        }
        hm.put(index, "小王");
        array.add(index);
        index++;
        hm.put(index, "大王");
        array.add(index);
        //洗牌(洗的是编号)，用Collections的shuffle()方法实现
        Collections.shuffle(array);
        //发牌(发的也是编号，为了保证编号是排序的，创建TreeSet集合接收)
        TreeSet<Integer> lqxSet = new TreeSet<Integer>();
        TreeSet<Integer> lySet = new TreeSet<Integer>();
        TreeSet<Integer> fqySet = new TreeSet<Integer>();
        TreeSet<Integer> dpSet = new TreeSet<Integer>();
        for (int i = 0; i < array.size(); i++) {
            int x = array.get(i);
            if (i >= array.size() - 3) {
                dpSet.add(x);
            } else if (i % 3 == 0) {
                lqxSet.add(x);
            } else if (i % 3 == 1) {
                lySet.add(x);
            } else if (i % 3 == 2) {
                fqySet.add(x);
            }
        }
        //调用看牌方法
        lookPoker("林青霞", lqxSet, hm);
        lookPoker("柳岩", lySet, hm);
        lookPoker("风清扬", fqySet, hm);
        lookPoker("底牌", dpSet, hm);
    }

    //定义方法看牌(遍历TreeSet集合，获取编号，到HashMap集合找对应的牌)
    public static void lookPoker(
            String name,
            TreeSet<Integer> ts,
            HashMap<Integer, String> hm
    ) {
        System.out.print(name + "的牌是：");
        for (Integer key : ts) {
            String poker = hm.get(key);
            System.out.print(poker + " ");
        }
        System.out.println();
    }
}
```
## File创建文件以及文件夹

```java
package FileCode;

import java.io.File;
import java.io.IOException;

public class CreateFileAndDir {
    public static void main(String[] args) throws IOException {
        // 没有E盘，代码运行有Ok，有毒吧？
        //File(String pathname)：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
/*        File f1 = new File("E:\\itcast\\java.txt");

        System.out.println(f1);
        //File(String parent, String child)：从父路径名字符串和子路径名字符串创建新的File实例。

        File f2 = new File("E:\\itcast", "java.txt");
        System.out.println(f2);

        //File(File parent, String child)：从父抽象路径名和子路径名字符串创建新的 File实例。
        File f3 = new File("E:\\itcast");
        File f4 = new File(f3, "java.txt");
        System.out.println(f4);*/

        // 下面的无法创建就会报错

/*        //需求1：我要在E:\\itcast目录下创建一个文件java.txt
        File f1 = new File("D:\\itcast\\java.txt");
        System.out.println(f1.createNewFile());
        System.out.println("--------");
        //需求2：我要在E:\\itcast目录下创建一个目录JavaSE
        File f2 = new File("D:\\itcast\\JavaSE");
        System.out.println(f2.mkdir());
        System.out.println("--------");
        */
        //需求3：我要在E:\\itcast目录下创建一个多级目录JavaWEB\\HTML
        File f3 = new File("D:\\itcast\\JavaWEB\\HTML");

        // System.out.println(f3.mkdir());
        System.out.println(f3.mkdirs());
        System.out.println("--------");
        //需求4：我要在E:\\itcast目录下创建一个文件javase.txt
        File f4 = new File("D:\\itcast\\javase.txt");
        // System.out.println(f4.mkdir());
        System.out.println(f4.createNewFile());

        // 总结：只有mkdirs的可以无视没有的路径，生成，其他的方式需要依赖有路径，不然报错.
        // 所以最好:f3为前置，套后面任意即可。

    }
}

```

## File创建删除文件以及文件夹

- 删除对应的目录似乎只有最后一个目录，而不是将整个目录删除,同时删除只能删除文件or空文件夹

```java
package DirOperation;

import java.io.File;
import java.io.IOException;

public class testCode {
    // 代码位置：JavaBase\IOTest\src\DirOperation\testCode.java

    public static void main(String[] args) throws IOException {
        // File f1 = new File("E:\\itcast\\java.txt");
        //需求1：创建java.txt文件，位置与模块在同一级，都在项目位置下.
        // 代码位置：JavaBase\IOTest\src\DirOperation\testCode.java
        // JavaBase\java.txt
        File f1 = new File("java.txt");

        System.out.println(f1.createNewFile());
        //需求2：删除当前模块目录下的java.txt文件
        System.out.println(f1.delete());
        System.out.println("--------");

        //需求3：在当前模块目录下创建itcast目录
        File f2 = new File("myFile\\itcast");
        System.out.println(f2.mkdirs());

        //需求4：删除当前模块目录下的itcast目录,myFile还在.
        System.out.println(f2.delete());
        System.out.println("--------");

        //需求5：在当前模块下创建一个目录itcast,然后在该目录下创建一个文件java.txt
        File f3 = new File("myFile\\itcast");
        System.out.println(f3.mkdir());
        File f4 = new File("myFile\\itcast\\java.txt");
        System.out.println(f4.createNewFile());
        System.out.println("--------");

        //需求6：删除当前模块下的目录itcast
        System.out.println(f4.delete());
        System.out.println(f3.delete());

        // 删除myFile文件夹,没有文件可以，有问题删除不了.
        File f8 = new File("myFile\\java.txt");
        System.out.println(f8.createNewFile());
        System.out.println(new File("myFile").delete());
    }
}
```
## 递归删除文件目录

- 运行Work，注意请使用测试目录，不进回收站.删除文件目录下的所有文件及其目录...

```java
package DirOperation;

import java.io.File;

/**
 * 此实用程序类可用于在java中递归删除文件夹
 */
public class DeleteFolderRecursively {

    public static void main(String[] args) {
        // String folder = "D:\\WorkSpace\\JavaBase - 副本";
        String folder = "D:/WorkSpace/maven/JavaBase";

        // 递归删除文件夹
        recursiveDelete(new File(folder));
    }

    public static void recursiveDelete(File file) {
        // 结束递归循环
        if (!file.exists()) return;

        // 如果是目录，请进入内部并递归调用
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // 调用递归
                recursiveDelete(f);
            }
        }
        // 调用delete删除文件和空目录
        file.delete();
        System.out.println("Deleted file/folder: " + file.getAbsolutePath());
    }
}
```
## 递归删除文件夹下的文件，但是不删除任何目录.

- 对比上面的方法，方向是有递归调用但是没有return.
```java
package DirOperation;

import java.io.File;
import java.util.Arrays;

public class DiGuiDemo {

    public static void main(String[] args) {
        // 根据给定的路径创建一个File对象
        File srcFile = new File("D:\\WorkSpace\\maven\\JavaBase");
        //调用方法
        getAllFilePath(srcFile);
    }

    // 定义一个方法，用于获取给定目录下的所有内容，参数为第1步创建的File对象
    public static void getAllFilePath(File srcFile) {
        // 获取给定的File目录下所有的文件或者目录的File数组
        File[] fileArray = srcFile.listFiles();
        // System.out.println("fileArray:"+Arrays.toString(fileArray));
        //遍历该File数组，得到每一个File对象
        if (fileArray != null) {
            for (File file : fileArray) {
                // 判断该File对象是否是目录
                if (file.isDirectory()) {
                    // 是：递归调用
                    getAllFilePath(file);
                } else {
                    // 不是：获取绝对路径输出在控制台
                    file.delete();
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
}
```
## 递归删除目录
```java
package DirOperation;

import java.io.File;

public class DiGuiDemo {

    public static void main(String[] args) {
        // 根据给定的路径创建一个File对象
        File srcFile = new File("D:\\WorkSpace\\maven\\JavaBase");
        //调用方法
        getAllFilePath(srcFile);
    }

    // 定义一个方法，用于获取给定目录下的所有内容，参数为第1步创建的File对象
    public static void getAllFilePath(File srcFile) {
        if (srcFile.isDirectory()) {
            for (File file : srcFile.listFiles()) {
                getAllFilePath(file);
            }
        }
        srcFile.delete();
        if (!srcFile.exists()) return;
    }
}
```

## IO字节流写数据

```java
package IOStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class IOStreamOperation {

    public static void main(String[] args) throws IOException {
        // 追加方式写入.
        FileOutputStream fos = new FileOutputStream("fos.txt", true);
        // 写数据
        for (int i = 0; i < 10; i++) {
            fos.write("hello".getBytes());
            fos.write("\r\n".getBytes());
        }
        // 释放资源
        fos.close();
    }
}
```

## IO字节流复制图片-文件
```java
package IOStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImage {

    public static void main(String[] args) throws IOException {
        //根据数据源创建字节输入流对象
        String inputImage = "D:\\WorkSpace\\JavaBase\\18_DDL_操作表_修改[00_03_01][20211009-203137].png";
        String outputImage = "D:\\WorkSpace\\JavaBase\\18_DDL_操作表_修改.png";

        FileInputStream fis = new FileInputStream(inputImage);
        FileOutputStream fos = new FileOutputStream(outputImage);

        //读写数据，复制图片(一次读取一个字节数组，一次写入一个字节数组)
        byte[] bys = new byte[1024];
        int len;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }
        //释放资源
        fos.close();
        fis.close();
    }
}
```

## 字节缓冲流复制文件

- 只有方法四有价值，其余的没意义,太慢了，适当增加写入数据大小可以提高写入速度.
```java
package IOStream;

import java.io.*;

public class CopyVideo {

    public static void main(String[] args) throws IOException {
        //记录开始时间
        String inputFile = "01.Redis企业实战课程介绍.mp4";
        String outputFile = "Redis企业实战课程介绍.mp4";

        long startTime = System.currentTimeMillis();
/*        method1(inputFile, outputFile);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");

        startTime = System.currentTimeMillis();
        method2(inputFile, outputFile);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");

        startTime = System.currentTimeMillis();
        method3(inputFile, outputFile);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");*/

        startTime = System.currentTimeMillis();
        method4(inputFile, outputFile);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    //字节缓冲流一次读写一个字节数组
    public static void method4(String inputFile, String outputFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputFile)
        );
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(outputFile)
        );
        // 每次写入1M字节=1024*1024=1048576
        byte[] bys = new byte[1048576];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        bos.close();
        bis.close();
    }

    //字节缓冲流一次读写一个字节
    public static void method3(String inputFile, String outputFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputFile)
        );
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(outputFile)
        );
        int by;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        bos.close();
        bis.close();
    }

    //基本字节流一次读写一个字节数组
    public static void method2(String inputFile, String outputFile) throws IOException {
        //E:\\itcast\\字节流复制图片.avi
        //模块目录下的 字节流复制图片.avi
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] bys = new byte[1024];
        int len;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }
        fos.close();
        fis.close();
    }

    //基本字节流一次读写一个字节
    public static void method1(String inputFile, String outputFile) throws IOException {
        //E:\\itcast\\字节流复制图片.avi
        //模块目录下的 字节流复制图片.avi
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(
                outputFile
        );
        int by;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        fos.close();
        fis.close();
    }
}

```

## 字符缓冲流复制文件

- 忽略字符流，因为缓冲流更加高效.
- 
```java
package IOStream;

import java.io.*;

class CopyJavaDemo {

    public static void main(String[] args) throws IOException {
        //根据数据源创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(
                new FileReader("javaFile.java")
        );
        //根据目的地创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("Copy.java")
        );
        //读写数据，复制文件
        //使用字符缓冲流特有功能实现
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        //释放资源
        bw.close();
        br.close();
    }
}
```

## IO流操作不完全小结

主要的IO流操作只用：字节缓冲流和字符缓冲流即可，非缓冲流可以使用缓冲流包装后高效的调用，具体如上面的文件复制演示。

```java
// 复制文件
String inputFile = "01.Redis企业实战课程介绍.mp4";
String outputFile = "Redis企业实战课程介绍.mp4";
BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));


// 复制文本
BufferedReader br = new BufferedReader(new FileReader("javaFile.java"));
BufferedWriter bw = new BufferedWriter(new FileWriter("Copy.java"));


// 从网络中读写-即时通讯中的演示
// 发送：
Socket socket = new Socket("127.0.0.1", 6666);
OutputStream os = socket.getOutputStream();
PrintStream ps = new PrintStream(os);

ps.println(msg);
ps.flush();


// 接受：
ServerSocket serverSocket = new ServerSocket(6666);
Socket socket = serverSocket.accept();
InputStream is = socket.getInputStream();

BufferedReader br = new BufferedReader(new InputStreamReader(is));
String msg = br.readLine();


```

## 文档与集合互写

- 文档写集合

```java
package IOStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class TxtToArrayList {

    public static void main(String[] args) throws IOException {
        //创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(
                new FileReader("array.txt")
        );
        //创建ArrayList集合对象
        ArrayList<String> array = new ArrayList<String>();
        //调用字符缓冲输入流对象的方法读数据
        String line;
        while ((line = br.readLine()) != null) {
            //把读取到的字符串数据存储到集合中
            array.add(line);
        }
        //释放资源
        br.close();
        //遍历集合
        for (String s : array) {
            System.out.println(s);
        }
    }
}

```
- 集合写文档

```java
package IOStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListToTxt {

    public static void main(String[] args) throws IOException {
        //创建ArrayList集合
        ArrayList<String> array = new ArrayList<String>();
        //往集合中存储字符串元素
        array.add("hello");
        array.add("world");
        array.add("java");
        //创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("array.txt")
        );
        //遍历集合，得到每一个字符串数据
        for (String s : array) {
            //调用字符缓冲输出流对象的方法写数据
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        //释放资源
        bw.close();
    }
}
```

## 点名器

```java
package IOStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DianMingQi {

    public static void main(String[] args) throws IOException {
        // 创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(
                new FileReader("names.txt")
        );
        // 创建ArrayList集合对象
        ArrayList<String> array = new ArrayList<String>();
        // 调用字符缓冲输入流对象的方法读数据
        String line;
        while ((line = br.readLine()) != null) {
            // 把读取到的字符串数据存储到集合中
            array.add(line);
        }
        // 释放资源
        br.close();
        // 使用Random产生一个随机数，随机数的范围在：[0,集合的长度)
        Random r = new Random();
        int index = r.nextInt(array.size());

        //根据索引取ArrayList值
        String name = array.get(index);
        // 把第7步得到的数据输出在控制台
        System.out.println("幸运者是：" + name);
    }
}
```

## ArrayList与File互写



- ArrayList>>>File
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Student {
    private String sid;
    private String name;
    private int age;
    private String address;

    public Student(String sid, String name, int age, String address) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class Main {
    // 对象写到文件:本质上就是保存属性即可，然后通过属性创建对象就可以得到原对象.

    public static void main(String[] args) throws IOException {
        // 创建ArrayList集合
        ArrayList<Student> array = new ArrayList<Student>();
        // 创建学生对象
        Student s1 = new Student("itheima001", "林青霞", 30, "西安");
        Student s2 = new Student("itheima002", "张曼玉", 35, "武汉");
        Student s3 = new Student("itheima003", "王祖贤", 33, "郑州");
        // 把学生对象添加到集合中
        array.add(s1);
        array.add(s2);
        array.add(s3);
        // 创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("students.txt"));
        // 遍历集合，得到每一个学生对象
        for (Student s : array) {
            // 把学生对象的数据拼接成指定格式的字符串
            // 不考虑多线程的安全性，StringBuilder的性能略高于StringBuffer可能是一个更好的选择.
            StringBuilder sb = new StringBuilder();
            sb.append(s.getSid()).append(",").append(s.getName()).append(",")
                    .append(s.getAge()).append(",").append(s.getAddress());
            // 调用字符缓冲输出流对象的方法写数据
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }
        // 释放资源
        bw.close();
    }
}
```
- File>>>>ArrayList

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Student {
    private String sid;
    private String name;
    private int age;
    private String address;

    public Student(String sid, String name, int age, String address) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

public class Main {
    // 文件中读取属性,再利用对应的属性创建对象.
    public static void main(String[] args) throws IOException {
        // 创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(
                new FileReader("students.txt"));
        // 创建ArrayList集合对象
        ArrayList<Student> array = new ArrayList<Student>();
        // 调用字符缓冲输入流对象的方法读数据
        String line;
        while ((line = br.readLine()) != null) {
            // 把读取到的字符串数据用split()进行分割，得到一个字符串数组
            String[] strArray = line.split(",");
            // 创建学生对象
            Student s = new Student();
            // 把字符串数组中的每一个元素取出来对应的赋值给学生对象的成员变量值
            // itheima001,林青霞,30,西安
            s.setSid(strArray[0]);
            s.setName(strArray[1]);
            s.setAge(Integer.parseInt(strArray[2]));
            s.setAddress(strArray[3]);
            // 把学生对象添加到集合
            array.add(s);
        }
        // 释放资源
        br.close();
        // 遍历集合
        for (Student s : array) {
            System.out.println(s);
        }
    }
}
```
## 递归复制文件夹

```java
package IOStream;

import java.io.*;

public class CopyFoldersDemo {

    public static void main(String[] args) throws IOException {
        // 创建数据源File对象，路径是D:\JavaBase
        File srcFile = new File("D:\\JavaBase");
        // 创建目的地File对象，路径是D:\JavaBaseCP
        File destFile = new File("D:\\JavaBaseCP");
        // 写方法实现文件夹的复制，参数为数据源File对象和目的地File对象
        long start = System.currentTimeMillis();
        copyFolder(srcFile, destFile);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms!");
    }

    // 复制文件夹
    private static void copyFolder(File srcFile, File destFile)
            throws IOException {
        // 判断数据源File是否是目录
        if (srcFile.isDirectory()) {
            // 在目的地下创建和数据源File名称一样的目录
            String srcFileName = srcFile.getName();
            File newFolder = new File(destFile, srcFileName);
            if (!newFolder.exists()) {
                newFolder.mkdirs();
            }
            // 获取数据源File下所有文件或者目录的File数组
            File[] fileArray = srcFile.listFiles();
            // 遍历该File数组，得到每一个File对象
            for (File file : fileArray) {
                // 把该File作为数据源File对象，递归调用复制文件夹的方法
                copyFolder(file, newFolder);
            }
        } else {
            // 说明是文件，直接复制，用字节流
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }
    }

    // 字节缓冲流复制文件
    private static void copyFile(File srcFile, File destFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(srcFile)
        );
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile)
        );
        byte[] bys = new byte[4096];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        bos.close();
        bis.close();
    }
}
```
## 递归复制文件夹-复制成而不是复制到

- 将文件夹复制为目标，而不是复制到目标下面.

```java
package IOStream;

import java.io.*;

public class CopyFoldersDemo {

    public static void main(String[] args) throws IOException {
        // 将D:\JavaBase的内容复制为D:\JavaBaseCP，而不是复制到D:\JavaBaseCP\JavaBase下面
        // 创建数据源File对象，路径是D:\JavaBase
        // 创建目的地File对象，路径是D:\JavaBaseCP

        String srcFilePath = "D:\\WorkSpace\\maven\\JavaBase";
        String destFilePath = "D:\\JavaBaseCP";

        File srcFile = new File(srcFilePath);
        File destFile = new File(destFilePath);

        long start = System.currentTimeMillis();
        copyFolder(srcFile, destFile);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms!");
    }

    // 复制文件夹
    private static void copyFolder(File srcFile, File destFile)
            throws IOException {
        // 判断数据源是目录
        if (srcFile.isDirectory()) {
            // 获取源目录下的目录结构.
            File[] fileArray = srcFile.listFiles();

            // 遍历File数组，得到每一个File对象.
            for (File file : fileArray) {

                // 创建目标文件对象.
                File newFolder = new File(destFile, file.getName());

                // 若源为文件夹，则创建目标文件夹.
                if (file.isDirectory()) {
                    if (!newFolder.exists()) {
                        newFolder.mkdirs();
                        System.out.println("创建目标文件夹：" + newFolder.getAbsolutePath());
                    }
                }
                copyFolder(file, newFolder);
            }

        } else {
            // 说明是文件直接复制
            copyFile(srcFile, destFile);
            System.out.println("复制文件:\n" + srcFile.getAbsolutePath() + "-->>>>>>" + destFile.getAbsolutePath() + "\n");
        }
    }

    // 字节缓冲流复制文件
    private static void copyFile(File srcFile, File destFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(srcFile)
        );
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile)
        );
        byte[] bys = new byte[4096];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        bos.close();
        bis.close();
    }
}
/*
控制台显示:
        创建目标文件夹：D:\JavaBaseCP\IOTest
        复制文件:
        D:\WorkSpace\maven\JavaBase\IOTest\IOTest.iml-->>>>>>D:\JavaBaseCP\IOTest\IOTest.iml

        创建目标文件夹：D:\JavaBaseCP\IOTest\src
        复制文件:
        D:\WorkSpace\maven\JavaBase\IOTest\src\IOTest.iml-->>>>>>D:\JavaBaseCP\IOTest\src\IOTest.iml

        耗时：8ms!

        Process finished with exit code 0
*/
```
## 字符打印流&标准输出流(略)

## 对象与序列化

- 对象序列化与反序列化

```java

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable {
    public String name;
    public int number;

    public String getName() {
        return name;
    }

    public Employee(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

public class Main {
    // 序列化后得到的文件不再可读，有一些注意事项.
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee e = new Employee("test", 100);

        // 序列化操作.
        try {
            FileOutputStream fileOut = new FileOutputStream("employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in employee.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }

        // 反序列化.
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.txt"));
        Object obj = ois.readObject();
        Employee s = (Employee) obj;
        System.out.println(s.getName() + "," + s.getNumber());
        ois.close();
    }
}
```
## 序列化问题
- 类文件修改，需要重新序列化才能读取出对象出来
```java
# 给对象设置属性也可以解决.
private static final long serialVersionUID = 42L;
```

- 不序列化对象的某一属性

```java
# 给属性添加关键字transient
private transient int age;
```

## properties集合

```java
package IOStream;

import java.util.Properties;
import java.util.Set;

public class PropertiesCollection {

    public static void main(String[] args) {
        // 创建集合对象
        // Properties<String,String> prop = new Properties<String,String>(); //错误
        Properties prop = new Properties();
        // 存储元素
        prop.put("itheima001", "林青霞");
        prop.put("itheima002", "张曼玉");
        prop.put("itheima003", "王祖贤");
        // 遍历集合
        Set<Object> keySet = prop.keySet();
        for (Object key : keySet) {
            Object value = prop.get(key);
            System.out.println(key + "," + value);
        }
    }
}
```
## Properties&IO流
```java
package IOStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

class PropertiesDemo {

    public static void main(String[] args) throws IOException {
        // 把集合中的数据保存到文件
        myStore();
        // 把文件中的数据加载到集合
        myLoad();
    }

    private static void myLoad() throws IOException {
        Properties prop = new Properties();
        FileReader fr = new FileReader("fw.txt");
        prop.load(fr);
        fr.close();
        System.out.println(prop);
    }

    private static void myStore() throws IOException {
        Properties prop = new Properties();
        prop.setProperty("itheima001", "林青霞");
        prop.setProperty("itheima002", "张曼玉");
        prop.setProperty("itheima003", "王祖贤");
        FileWriter fw = new FileWriter("fw.txt");
        prop.store(fw, null);
        fw.close();
    }
}
```
## Properties&游戏次数

```java
package IOStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

class PropertiesTest {

    public static void main(String[] args) throws IOException {
        // 从文件中读取数据到Properties集合，用load()方法实现
        Properties prop = new Properties();
        FileReader fr = new FileReader("game.txt");
        prop.load(fr);
        fr.close();
        // 通过Properties集合获取到玩游戏的次数
        String count = prop.getProperty("count");
        int number = Integer.parseInt(count);
        // 判断次数是否到到3次了
        if (number >= 3) {
            // 如果到了，给出提示：游戏试玩已结束，想玩请充值
            System.out.println("游戏试玩已结束，想玩请充值");
        } else {
            // 操作.
            // GuessNumber.start();
            System.out.println("游戏运行一次...");
            //次数+1，重新写回文件，用Properties的store()方法实现
            number++;
            prop.setProperty("count", String.valueOf(number));
            FileWriter fw = new FileWriter("game.txt");
            prop.store(fw, null);
            fw.close();
        }
    }
}
```
## Lambda表达式的使用

Lambda表达式的使用前提：
- 有一个接口.
- 接口中有且仅有一个抽象方法.

```java
package TCPTrans;

interface Flyable {
    void fly(String s);
}

public class lambdaOperation {
    public static void main(String[] args) {
        // 在主方法中调用useFlyable方法
        // 匿名内部类
        useFlyable(
                new Flyable() {
                    @Override
                    public void fly(String s) {
                        System.out.println(s);
                        System.out.println("飞机自驾游");
                    }
                }
        );
        System.out.println("--------");

        // Lambda
        useFlyable(
                (String s) -> {
                    System.out.println(s);
                    System.out.println("飞机自驾游");
                }
        );
    }

    private static void useFlyable(Flyable f) {
        f.fly("风和日丽，晴空万里");
    }
}

```

- lamda表达式&引用构造器

```java
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface StudentBuilder {
    Student build(String name, int age);
}

public class LambdaOperation {

    public static void main(String[] args) {
        // Lambda简化写法
        useStudentBuilder((name, age) -> new Student(name, age));
        // 引用构造器,简约很多啊.
        useStudentBuilder(Student::new);
    }

    private static void useStudentBuilder(StudentBuilder sb) {
        Student s = sb.build("林青霞", 30);
        System.out.println(s.getName() + "," + s.getAge());
    }
}
```

- Lamda表达式带参有返回

```java
/*
    Lambda表达式的格式：(形式参数) -> {代码块}

    练习3：
        1:定义一个接口(Addable)，里面定义一个抽象方法：int add(int x,int y);
        2:定义一个测试类(AddableDemo)，在测试类中提供两个方法
            一个方法是：useAddable(Addable a)
            一个方法是主方法，在主方法中调用useAddable方法
 */
interface Addable {
    int add(int x, int y);
}

public class AddableDemo {
    public static void main(String[] args) {
        //在主方法中调用useAddable方法
        useAddable((int x, int y) -> {
            return x + y;
        });

    }

    private static void useAddable(Addable a) {
        int sum = a.add(10, 20);
        System.out.println(sum);
    }
}
```





## 泛型

泛型貌似没有什么核心的代码,有需要在增加，需要记住三种方法:

- 类名.class属性
- 对象名.getClass()方法
- Class.forName(全类名)方法

```java
package TCPTrans;

public class FanXing {
    public static void main(String[] args) throws ClassNotFoundException {
        // 使用类的class属性来获取该类对应的Class对象
        Class<Student> c1 = Student.class;
        System.out.println(c1);
        Class<Student> c2 = Student.class;
        System.out.println(c1 == c2);
        System.out.println("--------");

        // 调用对象的getClass()方法，返回该对象所属类对应的Class对象
        Student s = new Student();
        Class<? extends Student> c3 = s.getClass();
        System.out.println(c1 == c3);
        System.out.println("--------");

        // 使用Class类中的静态方法forName(String className)
        Class<?> c4 = Class.forName("TCPTrans.Student");
        System.out.println(c1 == c4);
    }
}
```

## 构造代码块&静态代码块

```java

class Demo {
    static {
        System.out.println("静态代码块1...");
    }

    static {
        System.out.println("静态代码块2...");
    }

    {
        System.out.println("构造代码块...");
    }

    {
        System.out.println("构造代码块...");
    }

    public Demo() {
        System.out.println("无参构造方法");
    }

    public Demo(int i) {
        System.out.println("有参构造方法" + i);
    }
}

public class Main {
    public static void main(String[] args) {
        new Demo(1);
        System.out.println("------");
        new Demo(1);
    }
}
/*
 * 静态代码块1...
 * 静态代码块2...
 * 构造代码块...
 * 构造代码块...
 * 有参构造方法1
 * ------
 * 构造代码块...
 * 构造代码块...
 * 有参构造方法1
 */
```

## stream流

- 一个演示

```java
list.stream().filter(s->s.startWith("张")).filter(s->s.length()==3).forEach(System.out::println);
```

- 综合演示

```java
/*
    演员类
 */
public class Actor {
    private String name;

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

```java
import java.util.ArrayList;
import java.util.stream.Stream;

/*
    现在有两个ArrayList集合，分别存储6名男演员名称和6名女演员名称，要求完成如下的操作：
        1:男演员只要名字为3个字的前三人
        2:女演员只要姓林的，并且不要第一个
        3:把过滤后的男演员姓名和女演员姓名合并到一起
        4:把上一步操作后的元素作为构造方法的参数创建演员对象,遍历数据
            演员类Actor已经提供，里面有一个成员变量，一个带参构造方法，以及成员变量对应的get/set方法
*/
public class StreamTest {
    public static void main(String[] args) {
        //创建集合
        ArrayList<String> manList = new ArrayList<String>();
        manList.add("周润发");
        manList.add("成龙");
        manList.add("刘德华");
        manList.add("吴京");
        manList.add("周星驰");
        manList.add("李连杰");


        ArrayList<String> womanList = new ArrayList<String>();
        womanList.add("林心如");
        womanList.add("张曼玉");
        womanList.add("林青霞");
        womanList.add("柳岩");
        womanList.add("林志玲");
        womanList.add("王祖贤");

        /*
        //男演员只要名字为3个字的前三人
        Stream<String> manStream = manList.stream().filter(s -> s.length() == 3).limit(3);

        //女演员只要姓林的，并且不要第一个
        Stream<String> womanStream = womanList.stream().filter(s -> s.startsWith("林")).skip(1);

        //把过滤后的男演员姓名和女演员姓名合并到一起
        Stream<String> stream = Stream.concat(manStream, womanStream);

        //把上一步操作后的元素作为构造方法的参数创建演员对象,遍历数据
        // stream.map(Actor::new).forEach(System.out::println);
        stream.map(Actor::new).forEach(p -> System.out.println(p.getName()));
        */

        Stream.concat(manList.stream().filter(s -> s.length() == 3).limit(3),
                womanList.stream().filter(s -> s.startsWith("林")).skip(1)).map(Actor::new).
                forEach(p -> System.out.println(p.getName()));
    }
}
```
```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Stream流的收集方法
        R collect​(Collector collector)

    它是通过工具类Collectors提供了具体的收集方式
        public static <T> Collector toList​()：把元素收集到List集合中
        public static <T> Collector toSet​()：把元素收集到Set集合中
        public static Collector toMap​(Function keyMapper,Function valueMapper)：把元素收集到Map集合中
 */
public class CollectDemo {
    public static void main(String[] args) {
        //创建List集合对象
        List<String> list = new ArrayList<String>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");

        /*
        //需求1：得到名字为3个字的流
        Stream<String> listStream = list.stream().filter(s -> s.length() == 3);

        //需求2：把使用Stream流操作完毕的数据收集到List集合中并遍历
        List<String> names = listStream.collect(Collectors.toList());
        for(String name : names) {
            System.out.println(name);
        }
        */

        //创建Set集合对象
        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(33);
        set.add(35);

        /*
        //需求3：得到年龄大于25的流
        Stream<Integer> setStream = set.stream().filter(age -> age > 25);

        //需求4：把使用Stream流操作完毕的数据收集到Set集合中并遍历
        Set<Integer> ages = setStream.collect(Collectors.toSet());
        for(Integer age : ages) {
            System.out.println(age);
        }
        */


        //定义一个字符串数组，每一个字符串数据由姓名数据和年龄数据组合而成
        String[] strArray = {"林青霞,30", "张曼玉,35", "王祖贤,33", "柳岩,25"};

        //需求5：得到字符串中年龄数据大于28的流
        Stream<String> arrayStream = Stream.of(strArray).filter(s -> Integer.parseInt(s.split(",")[1]) > 28);

        //需求6：把使用Stream流操作完毕的数据收集到Map集合中并遍历，字符串中的姓名作键，年龄作值
        Map<String, Integer> map = arrayStream.collect(Collectors.toMap(s -> s.split(",")[0], s -> Integer.parseInt(s.split(",")[1])));

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Integer value = map.get(key);
            System.out.println(key + "," + value);
        }
    }
}
```

















