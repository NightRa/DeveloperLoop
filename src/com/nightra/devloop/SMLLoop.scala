//Created By Ilan Godik
package com.nightra.devloop

import java.nio.file.Paths

object SMLLoop extends App {

  import SMLTools._
  import Tools._

  val illegalArgumentsErrorMessage =
    """
      |Running arguments: '--dir path file1 file2'
      |You don't need to put the .sml extension.
      |To run in local directory, you can omit the --dir path parameter.
      |Example: 'week1 hw1test' will run week1.sml and then hw1test.sml in the local directory.
      |Example: '--dir "C:/Programming Languages" week1 hw1test'
      |          will run week1.sml and then hw1test.sml in the C:/Programming Languages directory.
    """.stripMargin

  val (folderPath, files) =
    args.toList match {
      case Nil => {
        Console.err.println(illegalArgumentsErrorMessage)
        sys.exit(1)
      }

      case "-dir" :: path :: fileNames => (path, fileNames)
      case fileNames => (sys.props("user.dir"), fileNames)
    }

  def runAndPrintResult(files: Seq[String]) {
    val processed = runSMLFiles(folderPath, files)
    println("\r\n" * 10)
    println(processed)
    println()
    println("Press enter to exit.")
    println()
  }

  runAndPrintResult(files)
  watch(Paths.get(folderPath), runAndPrintResult(files))
  readLine()
  sys.exit()
}
