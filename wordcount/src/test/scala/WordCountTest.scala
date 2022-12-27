import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class WordCountTest extends AnyFunSuite with BeforeAndAfterAll {
  val sc = SparkSession.builder().appName("WordCountTest").getOrCreate()

  override def afterAll(): Unit = {
    sc.stop()
  }

  test("word count test") {
  case class Row(text: String)
  val text = sc.createDataFrame(Seq(Row("this is a test of the word count function")))
  val wordCounts = WordCount.main(Array("text","output"))
  val expected = Seq(("this", 1), ("is", 1), ("a", 1), ("test", 1), ("of", 1), ("the", 1), ("word", 1), ("count", 1), ("function", 1))
  val result = sc.textFile("output").collect()
  assert(expected == result)
}
}


