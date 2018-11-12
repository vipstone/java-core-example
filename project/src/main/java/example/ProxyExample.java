package example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 方式一：forName()
//        Class<?> myClass = Class.forName("example.PeopleImpl");

        // 方式二：getClass()
//        String className = new String("example.PeopleImpl");
//        Class myClass = className.getClass();

        // 方式三：.class直接获取
        Class myClass = PeopleImpl.class;

        // 调用普通方法
//        Object object = myClass.newInstance();
//        Method method = myClass.getMethod("sayHi",String.class);
//        method.invoke(object,"老王");

//        // 调用静态（static）方法
//        Method getSex = myClass.getMethod("getSex");
//        getSex.invoke(myClass);

        // 获取所有 public 方法
        for (Method method : myClass.getDeclaredMethods()) {
            System.out.println(method);
        }

        // 获取字段
        for (Field field : myClass.getDeclaredFields()) {
            System.out.println(field);
        }

        // Declared 获取当前类的变量或方法，private和public都可以获取到，但不能获取到父类任何信息
        // 非 Declared 的只能获取到 public 的变量或方法，并且可以获取到父类的


    }
}
