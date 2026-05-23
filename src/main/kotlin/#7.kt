private val b1 = listOf<Int>(0, 0, 1, 1);
private val b2 = listOf<Int>(0, 1, 1, 1, 1, 1)

private fun chooseBall(): Int {
 val basket = (0..9).random()
    var ball = 0;
    var res = 0;
    return if (basket == 0) {
        ball = b2[(0..5).random()]
        if (ball == 1) {
            res = 2
            //println(res)
            return res;
        } else chooseBall();
    } else {
        ball = b1[(0..3).random()];
        if (ball == 1) {
            res = 1
            //println(1)
            return res
        } else {
            chooseBall()
        }
    }
}

private fun doTask(): Int{
    var secondB = 0;
    if(chooseBall() == 2){
        secondB++;
    }
    return secondB;
}

private fun doResearch(n:Int){
    println("Аналитическая вероятность: 0.15625")

    var time = 0;
    for (i in 0..n){
        time += doTask();
    }
    val v = time.toDouble()/n
    println("Моделирование: $v")
}

private fun main(){
    doResearch(1000000);
}