package main.scala

import scala.annotation.tailrec
import scala.util.Random
import Utils._

object CoinFlip extends App {
  val r = Random
  val s = GameState(0, 0)

  var previousGames: List[GameState] = List() // archive all the game
  mainLoop(s, r)

  @tailrec
  def mainLoop(gameState: GameState, random: Random) {
    showPrompt()

    val userInput = getUserInput()

    //handle the result
    userInput match {
      case "H" | "T" => {
        val coinTossResult = tossCoin(random)
        val newNumFlips = gameState.numFlips + 1

        if (userInput == coinTossResult) {
          val newNumCorrect = gameState.numCorrect + 1
          val newGameState = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)

          printGameState(printableFlipResult(coinTossResult), newGameState)

          mainLoop(newGameState, random)
        } else {
          val newGameState = gameState.copy(numFlips = newNumFlips)

          printGameState(printableFlipResult(coinTossResult), newGameState)

          mainLoop(newGameState, random)
        }
      }

      case "N" => {
        previousGames = previousGames :+ gameState
       val newGameState = gameState.copy(0, 0)

        displayPreviousGames(previousGames)
        displayNewGame()
        mainLoop(GameState(0, 0), random)
      }
      case _ => {
        printGameOver()
        printGameState(gameState)
        // return out of the recursion here
      }
    }
  }
}