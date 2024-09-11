import java.lang.reflect.Method;

public class Employee {
private String name;
public Employee(String name){
    this.name=name;
}
public Employee(){

}
public void work(){
    System.out.println(this.name + " work in taj group of hotels");
}

}
class Test{
    public static void main(String[] args) throws ClassNotFoundException {
        Class cls=Class.forName("Employee");//it is going to create object of Employee class
        Method[] declaredMethods =cls.getDeclaredMethods();//it will get all the declared methods this is called reflection API
        //if given the dot class file.it tell u what all methods are there are these methods are public or private
        // what are the arguments of those methods. what are the types of that method everything u can inspect reflection API

        for(Method m:declaredMethods){
            System.out.println("Method name "+m.getName()+" has return type  "+m.getReturnType());
        }
    }
}