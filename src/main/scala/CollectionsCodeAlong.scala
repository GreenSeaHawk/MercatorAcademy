object CollectionsCodeAlong extends App {
  val firstSet: Set[Int] = Set(1,2,3,4,5,5) //Unordered, does not allow repeats
  println("First set: " + firstSet)
  //Can't mix and match what's in a set, need to use a map if you want to have ints and stings
  val firstSeq: Seq[Int] = Seq(1,2,3,4,5,5) //Ordered, allows repeats
  println("First sequence: " + firstSeq)

  //Maps take 2 params (Key -> Value)
  val firstMap: Map[String, Int] = Map(
    "one" -> 1,
    "two" -> 2,
    "three" -> 3,
    "four" -> 4,
    "five" -> 5
  )
  //If a key is repeated it will keep the last key, value pair only.
  println("First map: " + firstMap)

  //Accessing data
  val getSeqHead: Int = firstSeq.head //index pos 1
  val getSeqTail: Seq[Int] = firstSeq.tail //Everything but the head
  println("Seq Head: " + getSeqHead)
  println("Seq Tail: " + getSeqTail)
  val getSeqIndex: Int = firstSeq(3) //only works with ordered collections
  println("Seq Index: " + getSeqIndex)

  //SET
  val inSet: Boolean = firstSet(5) //same as .contains(5)
  println("Exist in set: " + inSet)
  //Often filter for information
  val filterSet: Set[Int] = firstSet.filter(_ < 3)
  println("Filter set: " + filterSet)
  val filterNotSet: Set[Int] = firstSet.filterNot(_ < 3)
  println("FilterNot set: " + filterNotSet)
  val evenSet: Set[Int] = firstSet.filter(_ % 2 == 0)
  println("Filter evens set: " + evenSet)

  //MAP
  val getMapValue: Int = firstMap("one") //Enter key to get value
  println("Get map value: " + getMapValue)
  val getMapKey: String = firstMap.find(_._2 == 4).get._1
  //find the value that is equal to one and get the key that matches it
  //values can be repeated so it will return whichever it finds first, so it's risky.
  //1 and 2 are referring to key and value
  //So you could flip this to get the value for the key but this would be the same as the first
  //method. val getMapKey: Int = firstMap.find(_._1 == "four").get._2 returns 4
  println("Get map key: " + getMapKey)

  //Task 1 (Class)
  val seqOfNames: Seq[String] = Seq("April", "Andy", "Chay")
  println("seqofNames: " + seqOfNames)
  val colourMap: Map[Int, String] = Map(
    1 -> "red",
    2 -> "yellow",
    3 -> "blue",
    4 -> "green"
  )
  val blueKey: Int = colourMap.find(_._2 == "blue").get._1
  val blueKeyFilter: Map[Int, String] = colourMap.filter(_._2 == "blue")
  println("filter by blue: " + blueKey)
  println("filter by blue 2: " + blueKeyFilter)
  val setOfInts: Set[Int] = Set(1,2,3,4,5,6,7,8)

  val intsHigherThan2: Set[Int] = setOfInts.filter(_ > 2)
  println("intsHigherThan2: " + intsHigherThan2)

  //map (method - lowercase 'm') not the same as Map (Map is a Map)
  //Use a map to iterate a collection

  def tripledSet(inputSet: Set[Int]): Set[Int] = inputSet.map {
//    _ * 3
    num => num * 3 //both these methods mean the same
  }
  println("Tripled set: " + tripledSet(setOfInts))

  def tripledSeq(inputSeq: Seq[Int]): Seq[Int] = inputSeq.map {
    //    _ * 3
    num => num * 3 //both these methods mean the same
  }
  println("Tripled seq: " + tripledSeq(firstSeq))

  def tripledMap(inputMap: Map[String, Int]): Map[String, Int] = inputMap.map {
    case (key, value) => (key + "s", value * 3) //can just do one of these if you like
  }
  println("Tripled map: " + tripledMap(firstMap))

  // .exists = all that could be (get collection back with all that match the criteria)
  // .contains = check - true/false (boolean)
  //Unsure if the above 2 things are correct, might have misunderstood

  //TASK 1 continued (class)
  def add1(inputSet: Set[Int]): Set[Int] = inputSet.map {
    _ + 1
  }
  println("Add 1 to set: " + add1(setOfInts))

  def containsR(inputSeq: Seq[String]): Boolean = inputSeq.exists(_.contains("r"))
  println("Contains r: " + containsR(seqOfNames))
  //I'm a little confused by exactly what's happened here

  println("Remove even numbers: " + setOfInts.filter(_ % 2 != 0))

  //Briefly look up flat maps! I think it flattens the Seq so in this case extracting each
  //char from each string in the seq.
  val flatMapNames: Seq[Char] = seqOfNames.flatMap(_.toUpperCase)
  println("FlatMap: " + flatMapNames)

  val newSeq: Seq[Int] = Seq(6,7,8)
  val appendedListOne = firstSeq :+ newSeq
  println("Appended with :+ " + appendedListOne) //appends list as a value
  val appendedListTwo = firstSeq ++ newSeq
  println("Appended with ++ " + appendedListTwo) //appends each value of the list as
  // individual values
  val prependedListOne = newSeq ++ firstSeq
  println("Prepended with ++ " + prependedListOne)
  val prependedListTwo = newSeq +: firstSeq
  println("Prepended with +: " + prependedListTwo)

}