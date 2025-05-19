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
  def makeACupOfTea(sugar:Int, milk:Boolean): String = {
    s"You have made a cup of tea with $sugar spoons of sugar. Your milk selection is: $milk"
  }
  println(makeACupOfTea(sugar = 7, milk = true)) //calling the method

  val vat: Double = 1.2
  //Write a method that calculates the final price of a product with VAT

  def priceWithVAT(price:Double): Double = {
    price * vat
  }
  println(priceWithVAT(price = 10.0))

  //CLASSES think of these as a type
  class Dog (val name:String, val age:Int, val likesBananas:Boolean) {
    def speak: String = "woof" //declared in advance as it is the same for every Dog this could be
    // overridden if you wanted
  }
  //Use the class to make objects (e.g Dog)
  val loki:Dog = new Dog("Loki", 5, true)
  println(loki.speak)
  println(loki.name)



}