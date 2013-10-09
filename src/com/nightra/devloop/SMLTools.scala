//Created By Ilan Godik
package com.nightra.devloop

object SMLTools  {
  import Tools._

  def runSML(folder: String, files: Seq[String]) = {
    val safeFiles = files map safe
    executeCommand(List(moveTo(folder), safeFiles.mkString("sml ", " ", "")))
  }

  def runSMLLocal(files: Seq[String]) = {
    val safeFiles = files map safe
    executeCommand(List(safeFiles.mkString("sml ", " ", "")))
  }

  def processOutput(s: String): String = {
    val startIndex = s.indexOf("[opening")
    val endIndex = s.lastIndexOf('-')
    val result = s.substring(startIndex, endIndex)
    result.trim
  }

  def runSMLFiles(folder:String, files: Seq[String]) = {
    val withExtension = files.map(_ + ".sml")
    val output = runSML(folder,withExtension)
    val processed = processOutput(output)
    processed
  }

}
