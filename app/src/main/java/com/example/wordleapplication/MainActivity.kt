package com.example.wordleapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wordleapplication.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity()
{
    var wordToGuess = ""
    var numOfGuesses = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordToGuess = getRandomFourLetterWord()

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.et_simple)
        val firstGuess = findViewById<TextView>(R.id.firstGuess)
        val firstGuessCheck = findViewById<TextView>(R.id.firstGuessCheck)
        val secondGuess = findViewById<TextView>(R.id.secondGuess)
        val secondGuessCheck = findViewById<TextView>(R.id.secondGuessCheck)
        val thirdGuess = findViewById<TextView>(R.id.thirdGuess)
        val thirdGuessCheck = findViewById<TextView>(R.id.thirdGuessCheck)
        val finalAnswer = findViewById<TextView>(R.id.finalAnswer)
        var revealAnswer: Boolean = false

        button.setOnClickListener{
            if(numOfGuesses > 2)
            {
                //Toast.makeText(this, "Stop poking me! >:(", Toast.LENGTH_SHORT).show()
                numOfGuesses = 0
                revealAnswer = false
                button.text = "Submit"
                firstGuess.visibility = View.INVISIBLE
                firstGuessCheck.visibility = View.INVISIBLE
                secondGuess.visibility = View.INVISIBLE
                secondGuessCheck.visibility = View.INVISIBLE
                thirdGuess.visibility = View.INVISIBLE
                thirdGuessCheck.visibility = View.INVISIBLE
                finalAnswer.visibility = View.INVISIBLE
                wordToGuess = getRandomFourLetterWord()
            }
            else
            {
                val guess = editText.getText().toString().uppercase()


                if (guess.length != 4)
                {
                    Toast.makeText(
                        this, "Please input a 4 letter string, ty!!! :)", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val guessCheckStr = checkGuess(guess)
                    if(guessCheckStr == "OOOO")
                    {
                        revealAnswer = true
                    }
                    if (numOfGuesses == 0)
                    {
                        firstGuess.text = guess
                        firstGuessCheck.text = guessCheckStr
                        firstGuess.visibility = View.VISIBLE
                        firstGuessCheck.visibility = View.VISIBLE
                        numOfGuesses++
                    }
                    else if (numOfGuesses == 1)
                    {
                        secondGuess.text = guess
                        secondGuessCheck.text = guessCheckStr
                        secondGuess.visibility = View.VISIBLE
                        secondGuessCheck.visibility = View.VISIBLE
                        numOfGuesses++
                    }
                    else if (numOfGuesses == 2)
                    {
                        thirdGuess.text = guess
                        thirdGuessCheck.text = guessCheckStr
                        thirdGuess.visibility = View.VISIBLE
                        thirdGuessCheck.visibility = View.VISIBLE
                        revealAnswer = true
                        numOfGuesses++
                    }
                }
            }

            if(revealAnswer)
            {
                finalAnswer.text = wordToGuess
                finalAnswer.visibility = View.VISIBLE
                numOfGuesses = 30
                button.text = "Reset?"
            }
            //Log.v("EditText", checkGuess(editText.getText().toString().uppercase()))
        }

    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3)
        {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}