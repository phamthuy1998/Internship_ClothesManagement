package com.sg.snapshop.util


import java.util.ArrayList

class SpellChecker(dict: ArrayList<String>) {
    private var dict = ArrayList<String>()

    init {
        this.dict = dict
    }


    fun makeSuggestions(input: String): ArrayList<String> {
        val toReturn = ArrayList<String>()
        if (input.length > 1) {
            toReturn.addAll(charAppended(input))
            toReturn.addAll(charMissing(input))
            toReturn.addAll(charsSwapped(input))
        }
        return ArrayList(toReturn.distinct())
    }

    private fun charAppended(input: String): ArrayList<String> {
        val toReturn = ArrayList<String>()
        for (c in alphabet) {
            val atFront = c + input
            val atBack = input + c
            contain(toReturn, atFront)
            contain(toReturn, atBack)
        }
        return toReturn
    }

    private fun charMissing(input: String): ArrayList<String> {
        val toReturn = ArrayList<String>()

        if (input.length > 1) {
            val len = input.length - 1
            //try removing char from the front
            contain(toReturn, input.substring(1))
            for (i in 1 until len) {
                //try removing each char between (not including) the first and last
                var working = input.substring(0, i)
                working += input.substring(i + 1, input.length)
                contain(toReturn, working)
            }
            contain(toReturn, input.substring(0, len))
        }
        return toReturn
    }

    private fun contain(toReturn: ArrayList<String>, working: String) {
        if (working.length > 1) {
            for (word in dict) {
                if (word.startsWith(working, true) && !toReturn.contains(word)) {
                    toReturn.add(word)
                }
            }
        }
    }

    private fun charsSwapped(input: String): ArrayList<String> {
        val toReturn = ArrayList<String>()

        for (i in 0 until input.length - 1) {
            var working = input.substring(0, i)// System.out.println("    0:" + working);
            working += input[i + 1]  //System.out.println("    1:" + working);
            working += input[i] //System.out.println("    2:" + working);
            working += input.substring(i + 2)//System.out.println("    FIN:" + working);
            contain(toReturn, working)
        }
        return toReturn
    }

    companion object {


        internal val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    }


}
