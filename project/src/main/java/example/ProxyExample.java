package example;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExample {
    public static void main(String[] args) {

        // JDK 动态代理
        AnimalProxy proxy = new AnimalProxy();
        Animal dogProxy = (Animal) proxy.getInstance(new Dog());
        dogProxy.eat();

        // CGLIB 动态代理
//        CglibProxy proxy = new CglibProxy();
//        Panda panda = (Panda)proxy.getInstance(new Panda());
//        panda.eat();

    }
}

interface Animal {
    void eat();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("The dog is eating");
    }
}

class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("The cat is eating");
    }
}

// JDK 代理类
class AnimalProxy implements InvocationHandler {
    private Object target; // 代理对象

    public Object getInstance(Object target) {
        this.target = target;
        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        Object result = method.invoke(target, args); // 方法调用
        System.out.println("调用后");
        return result;
    }
}

// JDK 反例 extends 不能代理示例
//class Animal {
//    void eat(){
//        System.out.println("The animal is eating");
//    }
//}
//
//class Dog extends Animal {
//    @Override
//    public void eat() {
//        System.out.println("The dog is eating");
//    }
//}
//
//class Cat extends Animal {
//    @Override
//    public void eat() {
//        System.out.println("The cat is eating");
//    }
//}

//====================== CGLIB 示例 ==========================

class Panda {
    public void eat() {
        System.out.println("The panda is eating");
    }
}

class CglibProxy implements MethodInterceptor {
    private Object target; // 代理对象

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        // 设置父类为实例类
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        Object result = methodProxy.invokeSuper(o, objects); // 执行方法调用
        System.out.println("调用后");
        return result;
    }
}