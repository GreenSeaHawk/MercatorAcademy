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
  print("3) ")
  println(squareNumber(3)) //9

  //TASK 4
  def isItOdd(num: Int): Boolean = {
    (num % 2) == 1
  }
  print("4) ")
  println(isItOdd(3)) //true
  println(isItOdd(8)) //false

  //TASK 5
  print("5) ")
  class Book(val title: String, val pages: Int, val genre: String, val isFiction: Boolean) {
    val fictionString: String = if (isFiction) "Fiction" else "Non-Fiction"
    def description: String = {
      s"$title is a $fictionString book that is $pages long and is of the $genre genre"
    }
  }
  val narnia:Book = new Book(title="Narnia", pages=320, genre="fantasy", isFiction=true)
  println(narnia.description)
  val bible:Book = new Book(title="Bible", pages=3352, genre="religious", isFiction=false)
  println(bible.description)

  //EXTENSION
  //1
  val lowerString: String = "elephant"
  val upperString: String = lowerString.toUpperCase
  print("E1) ")
  println(lowerString)
  println(upperString)
  //2
  val capitaliseString: String = lowerString.capitalize
  print("E2) ")
  println(capitaliseString)
  //3
  //False because it will compare the Unicode values
  print("E3) ")
  println("STRING" == "string")
  //4
  //You might find a special library/function to do this but otherwise no
  //5
  //No but you can convert it to type string or vice versa as seen below
  val oneString: String = "1"
  val oneStringInt: Int = oneString.toInt
  print("E5) ")
  println(oneStringInt)
  println(oneStringInt.getClass)
  //6
  //Yes and you can do it in reverse.
  val oneStringIntBackToString: String = oneStringInt.toString
  print("E6) ")
  println(oneStringIntBackToString)
  println(oneStringIntBackToString.getClass)

  //RESEARCH
  //1
//  val new: String = "new string"
  //illegal start of simple pattern
  // because new is a reserved term used to make new entities of a class

  //2
  //You can use backticks to tell the compiler that you know you are using a reserved word
  //but you want to use it anyway. For example: val `new`: String = "new string"
  val `new`: String = "new string"
  println(`new`) // "new string"

  //3
  //I think a case class gives you more of a ready to go template. From research case class
  //is mutable whereas class is immutable. Case class also comes with some built-in methods
  //things like toString, equals and copy. I think it will become clearer when to use which
  //once we start creating more classes throughout the course.
}