package OOP;

class Vehicle {
    String color;
    String type;

    public void write(){
        System.out.println("writing something");
    }

    public void printColor(){
        System.out.println(this.color);
        System.out.println(this.type);
    }
}

public class ThisKeyword {
    public static void main(String[] args) {
        Vehicle v1 =new Vehicle();
        v1.color = "blue";
        v1.type = "car";

        Vehicle v2 =new Vehicle();
        v2.color = "red";
        v2.type = "bike";

        // v1.printColor();
        // v2.printColor();
    }
}
