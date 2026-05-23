package task_1

import java.io.BufferedReader
import java.io.File

//0 - red
//1 - white
//2 - black
//3 - green
//4 - blue
//5 - yellow

val boxTotal = listOf<Int>(280, 260, 280, 240, 270, 230, 260)
val boxes = (0..6)
const val d = 2
const val m = 6
const val p_ChangeBox = 0.1
const val nExp = 10000
val boxIn = listOf<List<Int>>(listOf(44, 61, 76, 63, 8, 28), listOf(53, 49, 67, 19, 34, 38),
    listOf(38, 41, 26, 63, 68, 44), listOf(19, 9, 31, 68, 54, 59),
    listOf(63, 10, 35, 31, 69, 62), listOf(46, 29, 29, 16, 53, 57),
    listOf(65, 7, 58, 46, 73, 11)
)
val ballRatio = mutableListOf<List<Double>>()
// значение вероятности каждого цвета в каждой матрице
private fun fullBallRatio(){
    for(i in 0..6){
        val box = mutableListOf<Double>()
        for (j in 0..5){
            box.add(j, boxIn[i][j].toDouble()/ boxTotal[i])
        }
        ballRatio.add(i, box)
    }
}
val currentBalls = listOf<Int>(0, 0, 0, 0, 0)
//1b - определить после каждого извлечения корзины с наибольшей вероятность
val res1b = mutableListOf<List<Double>>();

private fun postProbability(){
    for (line in File("data").readLines()){
        val balls: Pair<Int, Int>;


    }
}

private fun main(){
    fullBallRatio();

}
