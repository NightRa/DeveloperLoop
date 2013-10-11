//Created By Ilan Godik
package com.nightra.devloop

object SMLTools {

  import Tools._

  def runSML(folder: String, files: Seq[String]) = {
    val safeFiles = files map safe
    executeCommand(List(moveTo(folder), safeFiles.mkString("sml ", " ", "")))
  }

  def runSMLLocal(files: Seq[String]) = {
    val safeFiles = files map safe
    executeCommand(List(safeFiles.mkString("sml ", " ", "")))
  }

  def processOutput(s: String): String = cutOutput(s)

  /**
   * Processes the raw console output in the following way:
     From the first line with [opening ...] until the end of the proper REPL output
     End of REPL output:
      if no errors, then last - (It waits for a command - kind of stable)
      else until the uncaught exception line. (Very vague and not exact)
    (How to surely know if everything is alright?)
   */
  //TODO: This function takes specific assumptions, which can change in the future, or be platform specific. Should weaken them somehow.
  //TODO: Improve the abstractions in this function.
  def cutOutput(s: String): String = {
    // Assumption 1 - structure and ordering.

    // Assumption 2.
    val startIndex = s.indexOf("[opening")
    if (s.contains("Error")) {
      val fromOpening = s.substring(startIndex)
      val lines = fromOpening.lines.toVector
      // Assumption 3.
      val lastErrorLine = lines.lastIndexWhere(_.contains("Error"))
      val needed = lines.take(lastErrorLine + 1)
      needed.mkString("", "\r\n", "")
    } else {
      // Everything is alright.
      // Assumption 4.
      val endIndex = s.lastIndexOf('-')
      s.substring(startIndex, endIndex)
    }.trim
  }

  def runSMLFiles(folder: String, files: Seq[String]) = {
    val withExtension = files.map(_ + ".sml")
    val output = runSML(folder, withExtension)
    val processed = processOutput(output)
    processed
  }

}
