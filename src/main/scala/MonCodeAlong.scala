object MonCodeAlong extends App {
  val melons = 5 * 50
  val apples = 2 * 10
  val fizzyDrink = 6 * (1.20 * 100)
  //  val total = melons + apples + fizzyDrink

  var total: Double = 0
  total = total + melons
  println(total)
  total = total + apples
  println(total)
  total = total + fizzyDrink
  println(total)

  println(melons)
  println(apples)
  println(fizzyDrink)
  println(total)

  //TYPES
  val wholeNumber: Int = 42 //INT MAX IS LIKE 2 BILLION OR SOMETHING
  val littleNumber: Short = 7 //RARE - JUST USE INT
  val bigNumber: Long = 3948502934850293859L //MUST PUT L
  val littleDecimal: Float = 3.14159f //MUST PUT f (RARE)
  val decimal: Double = 335445.9494 //USE THIS
  val text: String = "Hello world!"
  val trueFalse: Boolean = true //false
  val falseTrue: Boolean = true

  println(trueFalse == falseTrue)

  //OPERATORS

  val a: Int = 10
  val b: Int = 5
  val c: Int = 3

  val add: Int = a + b
  val subtract: Int = a - b
  val divide: Double = a / c
  val multiply: Int = a * b
  val modulus: Int = a % c

  //Relational
  val equality: Boolean = a == a
  val inequality: Boolean = a != b
  val lessThan: Boolean = b < c
  val lessThanEqualTo: Boolean = b <= c
  val greaterThan: Boolean = b > c
  val greaterThanEqualTo: Boolean = b >= c

  //LOGICAL
  val and: Boolean = true && true //if either side is false it will return false
  val or: Boolean = true || false //if either side is true it will return true
  val not: Boolean = !false

  //METHODS
  def makeACupOfTea(sugar: Int, milk: Boolean): String = {
    s"You have made a cup of tea with $sugar spoons of sugar. Your milk selection is: $milk"
  }

  println(makeACupOfTea(sugar = 7, milk = true)) //calling the method

  val vat: Double = 1.2
  //Write a method that calculates the final price of a product with VAT

  def priceWithVAT(price: Double): Double = {
    price * vat
  }

  println(priceWithVAT(price = 10.0))

  //CLASSES think of these as a type
  class Dog(val name: String, val age: Int, val likesBananas: Boolean) {
    def speak: String = "woof" //declared in advance as it is the same for every Dog this could be
    // overridden if you wanted
  }

  //Use the class to make objects (e.g Dog)
  val loki: Dog = new Dog("Loki", 5, true)
  println(loki.speak)
  println(loki.name)

  //AFTERNOON TASK
  //TASK 1
  //a) false
  val aa: Boolean = (3 + 4) * 57 < 300
  print("a) ")
  println(aa)
  //b) true
  val bb: Boolean = (144 / 12) >= 12
  print("b) ")
  println(bb)
  //c) false WRONG actually true
  val cc: Boolean = "Cat" < "Dog"
  print("c) ")
  println(cc)
  //I was wrong because it doesn't compare the length of the string but the Unicode values
  //it does this letter by letter so 'C' is 67 and 'D' is 68 so it stops there. If I was
  //comparing 'Car' and 'Cat' it would compare 'C' and 'C', then move to 'a' and 'a' then to
  //'r' and 't'. But it only moves on if the 2 characters in the string are the same.
  //d) false
  val dd: Boolean = "Rabbit" < "Hamster"
  print("d) ")
  println(dd)
  //e) true
  val ee: Boolean = (17 % 2) == 1
  print("e) ")
  println(ee)
  //f) true
  val ff: Boolean = ((75 / 9) < 30) && ((89 / 6) < 20)
  print("f) ")
  println(ff)

  //TASK 2
  //"I love scala already" is a string whereas println("I love scala already") is a unit.
  //Also a string is data that is stored whereas println is an action that prints to the console.

  //TASK 3
  def squareNumber(num: Int): Int = {
    num * num
  }
  println(squareNumber(3)) //9

  //TASK 4
  def isItOdd(num: Int): Boolean = {
    (num % 2) == 1
  }
  println(isItOdd(3)) //true
  println(isItOdd(8)) //false

  //TASK 5
  class Book(val title: String, val pages: Int, val genre: String, val isFiction: Boolean) {
    val fictionStatus: String = {

    }
    def description: String = {
      s"$title is a $"
    }
  }

}