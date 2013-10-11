//Created By Ilan Godik
package com.nightra.devloop

import org.scalatest.{GivenWhenThen, Matchers, FunSpec}

class SMLToolsTest extends FunSpec with Matchers with GivenWhenThen {
  describe("processOutput") {
    it("should return only the REPL output if the code is correct") {
      pending
    }
    it("should return the error if the code is invalid") {
      val rawOut =
        """
          |C:\Users\ilan3_000\Programming\Programming Languages\Homework\week2>sml week2.sml hw2test.sml
          |Standard ML of New Jersey v110.76 [built: Sun Jul 14 09:59:19 2013]
          |[opening week2.sml]
          |val same_string = fn : string * string -> bool
          |val all_except_option = fn : string * string list -> string list option
          |datatype suit = Clubs | Diamonds | Hearts | Spades
          |datatype rank = Ace | Jack | King | Num of int | Queen
          |type card = suit * rank
          |datatype color = Black | Red
          |datatype move = Discard of suit * rank | Draw
          |exception IllegalMove
          |[opening hw2test.sml]
          |hw2test.sml:8.13-8.31 Error: unbound variable or constructor: get_substitutions1
          |hw2test.sml:10.13-10.31 Error: unbound variable or constructor: get_substitutions2
          |hw2test.sml:12.13-12.26 Error: unbound variable or constructor: similar_names
          |hw2test.sml:16.13-16.23 Error: unbound variable or constructor: card_color
          |hw2test.sml:18.13-18.23 Error: unbound variable or constructor: card_value
          |hw2test.sml:20.13-20.24 Error: unbound variable or constructor: remove_card
          |hw2test.sml:22.13-22.27 Error: unbound variable or constructor: all_same_color
          |hw2test.sml:24.13-24.22 Error: unbound variable or constructor: sum_cards
          |hw2test.sml:26.14-26.19 Error: unbound variable or constructor: score
          |hw2test.sml:28.14-28.23 Error: unbound variable or constructor: officiate
          |hw2test.sml:30.14-30.23 Error: unbound variable or constructor: officiate
          |hw2test.sml:35.16-35.25 Error: unbound variable or constructor: officiate
          |C:\Program Files (x86)\SMLNJ\\bin\.run\run.x86-win32.exe: Fatal error -- Uncaught exception Error wi
          |th 0
          | raised at ../compiler/TopLevel/interact/evalloop.sml:66.19-66.27
          |
          |
          |C:\Users\ilan3_000\Programming\Programming Languages\Homework\week2>
        """.stripMargin

      val theExpectedOutput =
        """
          |[opening week2.sml]
          |val same_string = fn : string * string -> bool
          |val all_except_option = fn : string * string list -> string list option
          |datatype suit = Clubs | Diamonds | Hearts | Spades
          |datatype rank = Ace | Jack | King | Num of int | Queen
          |type card = suit * rank
          |datatype color = Black | Red
          |datatype move = Discard of suit * rank | Draw
          |exception IllegalMove
          |[opening hw2test.sml]
          |hw2test.sml:8.13-8.31 Error: unbound variable or constructor: get_substitutions1
          |hw2test.sml:10.13-10.31 Error: unbound variable or constructor: get_substitutions2
          |hw2test.sml:12.13-12.26 Error: unbound variable or constructor: similar_names
          |hw2test.sml:16.13-16.23 Error: unbound variable or constructor: card_color
          |hw2test.sml:18.13-18.23 Error: unbound variable or constructor: card_value
          |hw2test.sml:20.13-20.24 Error: unbound variable or constructor: remove_card
          |hw2test.sml:22.13-22.27 Error: unbound variable or constructor: all_same_color
          |hw2test.sml:24.13-24.22 Error: unbound variable or constructor: sum_cards
          |hw2test.sml:26.14-26.19 Error: unbound variable or constructor: score
          |hw2test.sml:28.14-28.23 Error: unbound variable or constructor: officiate
          |hw2test.sml:30.14-30.23 Error: unbound variable or constructor: officiate
          |hw2test.sml:35.16-35.25 Error: unbound variable or constructor: officiate
        """.stripMargin


      val processed = SMLTools.processOutput(rawOut.trim).trim
      //      println(s"Actual: $processed")
      processed should be(theExpectedOutput.trim)
    }


  }
}
