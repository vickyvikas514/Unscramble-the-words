package com.example.android.unscramble.ui.game

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels
import com.example.android.unscramble.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GameViewModel : ViewModel() {


    private var _score = 0
    val score:Int
    get()=_score
    private var _currentWordCount = 0
    val currentWordCount:Int
    get()=_currentWordCount

    private lateinit var _currentScrambledWord:String
    val currentScrambledWord: String
    get()= _currentScrambledWord

    private lateinit var currentword:String
    private var wordslist:MutableList<String> = mutableListOf()

  fun getNextWord(){
        currentword= allWordsList.random()
      val tempWord = currentword.toCharArray()
      while (String(tempWord).equals(currentword,false)){
          tempWord.shuffle()
      }
      if (wordslist.contains(currentword)) {
          getNextWord()
      } else {
          _currentScrambledWord = String(tempWord)
          ++_currentWordCount
          wordslist.add(currentword)
      }

    }
    init{
        Log.d("GameFragment","GameViewModel created!")
        getNextWord()
    }
    fun nextWord():Boolean{
    return if(currentWordCount< MAX_NO_OF_WORDS){
        getNextWord()
        true
    } else false

    }
    private fun increaseScore (){
        _score+= SCORE_INCREASE
    }
    fun isUserWordCorrect(playerWord:String):Boolean{
        if(playerWord.equals(currentword,true)){
            increaseScore()
            return true
        }
        return false
    }
    fun reinitializeData(){
        _score=0
        _currentWordCount=0
        wordslist.clear()
        getNextWord()
    }

    /*
 * Creates and shows an AlertDialog with the final score.
 */





}