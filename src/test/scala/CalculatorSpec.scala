import org.scalatest.flatspec.AnyFlatSpec

class CalculatorSpec extends AnyFlatSpec {
  val calc: Calculator = new Calculator
  "add" should "add 2 numbers" in {
    val input = calc.add(1,2)
    val expectedResult = 3
    assert(input == expectedResult)
  }
  "multiply" should "multiply 2 numbers" in {
    val input = calc.multiply(2,3)
    val expectedResult = 6
    assert(input == expectedResult)
  }
  "subtract" should "subtract the second input from the first" in {
    val input = calc.subtract(4,3)
    val expectedResult = 1
    assert(input == expectedResult)
  }
  "divide" should "divide the first input by the second" in {
    val input = calc.divide(5,2)
    val expectedResult = 2.5
    assert(input == expectedResult)
  }

}
