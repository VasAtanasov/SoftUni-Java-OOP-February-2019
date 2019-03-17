# Lab: Polymorphism

Problems for exercises and homework for the "Java OOP" course @ SoftUni.

## 1. MathOperation

Create a class MathOperation, which should have method add(). Method add() have to be invoked with two, three or four Integers.

You should be able to use the class like this:

### Main.java

 ```java
public static void main(String[] args) throws IOException {
    MathOperation math = new MathOperation();
    System.out.println(math.add(2, 2));
    System.out.println(math.add(3, 3, 3));
    System.out.println(math.add(4, 4, 4, 4));
}
 ```

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td></td>
<td>4<br>9<br>16</td>
</tr>
</tbody>
</table>

### Solution

Class MathOperation should look like this:

```java
public class MathOperation {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return add(a, b) + c;
    }

    public static int add(int a, int b, int c, int d) {
        return add(a, b, c) + d;
    }
}
 ```

<p><b>Solution: <a href="./mathoperation/src">Math Operation</a></b></p>

## 2. Shapes

Create class hierarchy, starting with abstract class Shape:

- Fields:
  - perimeter : Double
  - area : Double
- Encapsulation for this fields
- Abstract methods:
  - calculatePerimeter()
  - calculateArea()

Extend Shape class with two children:

- Rectangle
- Circle

Each of them need to have:

- Fields:

    For Rectangle

  - height : Double
  - width : Double

    For Circle
  - radius : Double

- Encapsulation for this fields
- Public constructor 
- Concrete methods for calculations (perimeter and area)

<p><b>Solution: <a href="./shapes/src">Shapes</a></b></p>

## 3. Animals

Create a class Animal, which holds two fields:

- name: String
- favouriteFood: String

Animal has one abstract method explainSelf(): String.

You should add two new classes - Cat and Dog. Override the explainSelf() method by adding concrete animal sound on a new line. (Look at examples below) 

You should be able to use the class like this:

### Main

```java
public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat("Pesho","Whiskas");
        Animal dog = new Dog("Gosho","Meat");
        
        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }
}
```

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td></td>
<td>I am Pesho and my fovourite food is Whiskas<br>MEEOW</td>
</tr>
<tr>
<td></td>
<td>I am Gosho and my fovourite food is Meat<br>DJAAF</td>
</tr>
</tbody>
</table>

### Solution

```java
public abstract class Animal {
    private String name;
    private String favoriteFood;

    public Animal(String name, String favoriteFood) {
        this.name = name;
        this.favoriteFood = favoriteFood;
    }

    public String explainSelf() {
        return String.format("I am %s and my favorite food is %s", this.name, this.favoriteFood);
    }

}
```

```java
public class Cat extends Animal {
    public Cat(String name, String favoriteFood) {
        super(name, favoriteFood);
    }

    @Override
    public String explainSelf() {
        return String.format("%s%nMEEOW", super.explainSelf());
    }
}
```

<p><b>Solution: <a href="./animals/src">Animals</a></b></p>

<p><b>Document with tasks description: <a href="./resources/05. Java-OOP-Polymorphism-Lab.docx">05. Java-OOP-Polymorphism-Lab.docx</a></b></p>