//Created By Ilan Godik
package com.nightra.devloop

import java.io.{OutputStream, InputStream, PrintWriter, ByteArrayOutputStream}
import java.nio.file.{FileSystems, StandardWatchEventKinds, Path}

object Tools {
  def executeCommand(commands: Seq[String]): String = {
    val command = Array("cmd")
    val p = Runtime.getRuntime.exec(command)
    val errStream = new ByteArrayOutputStream()

    new Thread(new SyncPipe(p.getInputStream, errStream)).start()

    val console = new PrintWriter(p.getOutputStream)

    commands foreach console.println

    console.close()
    p.waitFor()

    errStream.toString
  }

  def watch(path: Path, f: => Any) {
    val watcher = FileSystems.getDefault.newWatchService()
    val key = path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY)
    new Thread(new Runnable {
      def run() {
        while (true) {
          if (!key.pollEvents.isEmpty) f
          key.reset()
          Thread.sleep(10)
        }
      }
    }).start()
  }

  def safe(s: String): String = "\"" + s + "\""

  def moveTo(path: String): String = s"cd ${safe(path)}"

}

class SyncPipe(in: InputStream, out: OutputStream) extends Runnable {
  def run() {
    val buffer = new Array[Byte](1024)
    def readLoop(length: Int): Unit =
      if (length != -1) {
        out.write(buffer, 0, length)
        readLoop(in read buffer)
      }

    readLoop(in read buffer)
  }
}





