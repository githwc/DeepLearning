package com.yc.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class DlPracticeApplicationTests {

    @Test
    public void dayCompareStr(){
        List<String> list = Arrays.asList("kobe","Lamban","Dave","Lina","Lihong");

        List<String> outList = list.stream().
                filter(s-> s.startsWith("L"))
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(outList);

        String [] arr = {"kobe","Lamban","Dave","Lina","Lihong"};
        String[] outArr = Stream.of(arr).filter(s-> s.startsWith("L"))
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(outArr));
    }

    @Test
    public void a(){
        Emp emp1 = new Emp(1,12,"13");
        Emp emp2 = new Emp(2,22,"23");
        Emp emp3 = new Emp(3,32,"33");
        Emp emp4 = new Emp(4,42,"43");
        Emp emp5 = new Emp(5,52,"53");
        Emp emp6 = new Emp(6,62,"63");
        Emp emp7 = new Emp(7,72,"73");
        Emp emp8 = new Emp(8,82,"83b");
        Emp emp9 = new Emp(9,92,"93");

        List<Emp> list = Arrays.asList(emp1,emp2,emp3,emp4,emp5,emp6,emp7,emp8,emp9);
        list.sort(Comparator.comparing(Emp::getAge).reversed());
        System.out.println(list);
        // List outList = list.stream()
        //         // .filter(Emp.ageGreaterThan70.and(Emp.filterName).negate())
        //         .filter(Emp.ageGreaterThan70.or(Emp.filterName))
        //         .collect(Collectors.toList());
        // System.out.println(outList);
        //
        // String a = "hello";
        // System.out.println(a);
        // System.out.println(Arrays.toString(a.split("")));
    }


}
