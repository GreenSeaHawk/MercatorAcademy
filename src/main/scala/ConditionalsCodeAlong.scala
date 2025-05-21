object ConditionalsCodeAlong extends App {
  val weather: String = "rainy"

  //IF/ELSE
  if (weather == "rainy") {
    println("Take an umbrella")
  } else if (weather == "sunny") {
    println("Bring your send its")
  } else if (weather == "cold") {
    println("Wear a warm coat")
  } else {
    println("Unsure of forecast")
  }
  //If writing a test for this, would expect a minimum of 4 tests as there are 4 paths

  val season: String = "summer"
  //How we approach this when we have multiple options:
  if (weather == "cold" || season == "winter") {
    println("Take a coat")
  } else {
    println("A light jacket is fine")
  }

  //PATTERN MATCH
  weather match {
    case "rainy" => println("Take an umbrella")
    case "sunny" => println("Bring your send its")
    case "cold" => println("Wear a warm coat")
    case _ => println("Unsure of forecast")
  }

  (weather, season) match {
    case (weather, season) if weather == "cold" || season == "winter" => println("Take a coat")
    case _ => println("A light jacket is fine")
  }

  //INT -slightly more careful
  val age: Int = 0

  if (age >= 18){
    println(s"You are an adult because you are $age")
  } else if (0 <= age && age < 18) {
    println(s"You are a child because you are $age")
  } else {
    println(s"Please enter a valid age")
  }

  age match {
    case age if age >= 18 => println(s"You are an adult because you are $age")
    case age if age >= 0 => println(s"You are a child because you are $age")
    case _ => println("Please enter a valid age")
  }

  //TASK 1 (Class)
  val score: Int = 100

  if (score > 100) {
    println("Exam Score above 100")
  } else if (score >= 90) {
    println("A")
  } else if (score >= 80) {
    println("B")
  } else if (score >= 70) {
    println("C")
  } else if (score >= 60) {
    println("D")
  } else if (score >= 50) {
    println("E")
  } else if (score >= 0) {
    println("F")
  } else {
    println("Invalid Exam Score")
  }

  //TASK 2 (Class)
  val filmAge:Int = 101

  filmAge match {
    case filmAge if filmAge >= 18 => println("18, 15, 12, PG, U")
    case filmAge if filmAge >= 15 => println("15, 12, PG, U")
    case filmAge if filmAge >= 12 => println("12, PG, U")
    case filmAge if filmAge >= 8 => println("PG, U")
    case filmAge if filmAge >= 4 => println("U")
    case filmAge if filmAge >= 0 => println("Too young to watch films")
    case _ => println("Invalid age")
  }

  //OPTIONS (getOrElse)
  val name: Option[String] = Some("April")
  val emptyName: Option[String] = None
  println(name)
  println(emptyName)

  //getOrElse is used when we want to return a default value if the None is returned.
  //It will also take the Some away, leaving us with just the internals.
  def getName(name: Option[String]): String = name.getOrElse("This user has left the field empty")
  println(getName(name))
  println(getName(emptyName))

  //TRY/CATCH

  try {
    //ALL LOGIC - if/else, pattern match, def, val etc
    val number: Int = "123".toInt //I want to change the input to Int from String
    println(s"The number is $number")
  } catch {
    case error: NumberFormatException => println(s"$error was not a valid input")
  }



}
