private val l1 = listOf<Int>(1, 1, 1, 1, 1, 1, 1, 0, 0, 0)
private val l2 = listOf<Int>(1, 1, 1, 0, 0, 0, 0, 0, 0, 0)

private fun chooseBrokenLamp():Int{
    val type = (0..1).random();
    return if (type == 0) {
        val v = l1[(0..9).random()]
        if (v == 1) {
            return 0
        } else chooseBrokenLamp();
    } else {
        val v = l2[(0..9).random()];
        if (v == 1) {
            return 1
        } else chooseBrokenLamp()
    }
}

private fun doTask(): Int{
    var t1 = 0;
    var t2 = 0;
    for(i in 0..4){
        val c = chooseBrokenLamp();
        if(c == 0){
            t1++
        } else {
            t2++
        }
    }
    if(t1 == 5 || t2 >= 2){
        return 1;
    }
    return 0
}

private fun doResearch(n: Int){
    println("Аналитическая вероятность: 0.64")
    var times = 0;
    for(i in 0..n){
        times+= doTask()
    }
    val v = times.toDouble()/n
    println("Результат моделирования: $v")
}

private fun main(){
    doResearch(1000000);
}