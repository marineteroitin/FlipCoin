package main.scala

import scala.io.StdIn.readLine
import scala.util.Random

object Utils {
  def showPrompt(): Unit = {
    print("\n(h)eads, (t)ails, (n)ew game or (q)uit: ")
  }

  def getUserInput(): String = readLine.trim.toUpperCase

  // returns "H" for heads, "T" for tails
  def tossCoin(r: Random): String = {
    val i = r.nextInt(2)
    i match {
      case 0 => "H"
      case 1 => "T"
    }
  }

  def printableFlipResult(flip: String): String = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameState(printableFlipResult: String, gameState: GameState): Unit = {
    print(s"Flip was $printableFlipResult. ")
    printGameState(gameState)
  }

  def printGameState(gameState: GameState): Unit = {
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrect}")
  }

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def displayNewGame(): Unit = {
    println("=== NEW GAME ===")
  }

  def displayPreviousGames(previousGames: List[GameState]): Unit = {
    println("=== SUMMARY ===")
    previousGames.foreach(game => printGameState(game))
  }

}
