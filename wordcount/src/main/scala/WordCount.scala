import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]): Unit = {
    val inputFile = args(0) // input file
    val outputFile = args(1) // output file

    // create a SparkContext object
    val conf = new SparkConf().setAppName("WordCount")
    val sc = new SparkContext(conf)

    // read in the input file and create an RDD
    val input = sc.textFile(inputFile)

    // split the lines into words and count the occurrences of each word
    val counts = input.flatMap(line => line.split(" "))
                     .map(word => (word, 1))
                     .reduceByKey(_ + _)

    // save the results to the output file
    counts.saveAsTextFile(outputFile)
  }
}
