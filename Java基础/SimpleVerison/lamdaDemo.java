package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

public class lamdaDemo {
    public static void main(String[] args) {
        // 第一种
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程运行了...");
            }
        }).start();
        new Thread(() -> System.out.println("lamda线程运行了...")).start();

        // 第二种-其余为衍生，此处为标准.
        int res1 = calcAdd((int left, int right) -> {
            return left + right + 10;
        });
        System.out.println("执行结果:" + res1);

        // 第三种
        // printNum((value) -> {
        //     return value % 2 == 0;
        // });
        printNum((value) -> value % 2 == 0);

        // 第四种
    //    int res4 = typeCover(new Function<String, Integer>() {
    //        @Override
    //        public Integer apply(String s) {
    //            return Integer.parseInt(s);
    //        }
    //    });
    //    int res4 = typeCover(s->Integer.parseInt(s));
        int res4 = typeCover(Integer::parseInt);
        System.out.println(res4);
        
    //    foreachArray(new IntConsumer() {
    //        @Override
    //        public void accept(int value) {
    //            System.out.println(value);
    //        }
    //    });
        foreachArray(value -> System.out.println(value));
        foreachArray(System.out::println);

        HashMap<String, String> stingStringHashMap = new HashMap<String, String>();
        stingStringHashMap.put("张启山","星月");
        stingStringHashMap.put("二月红","吴心");
        stingStringHashMap.put("令狐冲","任盈盈");
        Stream<Map.Entry<String, String>> stream = stingStringHashMap.entrySet().stream();
        stream.filter(item->item.getValue().length()>2).forEach(item-> System.out.println(item.getValue()));

        // 打印值。
        stingStringHashMap.entrySet().stream().map(item->item.getValue()).forEach(System.out::println);



    }

    private static void foreachArray(IntConsumer intConsumer) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int item:array){
            intConsumer.accept(item);
        }
    }

    private static <R> R typeCover(Function<String, R> function) {
        String str = "12345";
        R result = function.apply(str);
        return result;
    }

    public static int calcAdd(IntBinaryOperator intBinaryOperator) {
        int a = 10;
        int b = 20;
        return intBinaryOperator.applyAsInt(a, b);
    }

    public static void printNum(IntPredicate intPredicate) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int item : array) {
            if (intPredicate.test(item)) {
                System.out.println(item);
            }
        }
    }
}
