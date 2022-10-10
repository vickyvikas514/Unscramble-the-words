package com.example.android.unscramble.ui.game

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.unscramble.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GameViewModel : ViewModel() {


    private val _score = MutableLiveData(0)

    val score:LiveData<Int>
    get()=_score

    //
    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount
    private val _currentScrambledWord= MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
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
          _currentScrambledWord.value = String(tempWord)
          _currentWordCount.value = (_currentWordCount.value)?.inc()
          wordslist.add(currentword)
      }

    }
    init{
        Log.d("GameFragment","GameViewModel created!")
        getNextWord()
    }

    fun nextWord():Boolean{
    return if(_currentWordCount.value!! < MAX_NO_OF_WORDS){
        getNextWord()
        true
    } else false

    }


    private fun increaseScore (){
        _score.value= (_score.value)?.plus(SCORE_INCREASE)
    }
    fun isUserWordCorrect(playerWord:String):Boolean{
        if(playerWord.equals(currentword,true)){
            increaseScore()
            return true
        }
        return false
    }
    fun reinitializeData(){
        _score.value=0
        _currentWordCount.value=0
        wordslist.clear()
        getNextWord()
    }

    /*
 * Creates and shows an AlertDialog with the final score.
 */





}