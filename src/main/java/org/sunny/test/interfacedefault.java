package org.sunny.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class Student{
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
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Base{
    int num = 1;
    public void print(){
        System.out.println("Base.num="+num);
    }
    public Base(){
        this.print();
        num = 2;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

class Sub extends Base{
    int num = 3;
    public void print(){
        System.out.println("Base.num="+num);
    }
    public Sub(){
        this.print();
        num = 4;
    }
}

public class interfacedefault {
    public static void main(String[] args){
        /*Student s1 = new Student("zhansan",12);
        Student s2 = new Student("lucy",34);
        Student s3 = new Student("john",54);
        Student s4 = new Student("mark",78);

        Student[] ss = new Student[]{s1,s2,s3,s4};
        List<Student> list = Arrays.asList(ss);
        Optional<Student> o = list.stream().filter(stu->stu.getName().contains("n")).findFirst();
        if(o.isPresent()){
            System.out.print(o);
        }
        */
        Base b = new Sub();
        System.out.print(b.num);
    }
}
