private val p = listOf(1, 2, 0, 0);

private var champion = 0.0;
private var player = 0.0;
private var games20 = 0;

private fun oneGame(){
    val r = p[(0..3).random()]
    if(r == 1){
        champion++
    } else if(r == 2){
        player++
    } else {
        player += 0.5
        champion += 0.5
    }
}

private fun doTask(): Int{
    champion = 0.0;
    player = 0.0;
    for(i in 0..23){
        oneGame()
        if(player == 7.5 && champion == 12.5){
            games20++
        }
        if(player == 7.0 && champion == 13.0){
            games20++
        }
        if(player == 12.0 && champion == 8.0){
            games20++
        }
        if(player == 12.5 && champion == 7.5)
        if(champion == 12.0){
            return 1
        }
    }
    if(player >= 12.5){
        return 2
    } else {
        return 1
    }
}

private fun doResearch(n: Int){
    games20 = 0
    var timesCh = 0;
    var timesPl = 0;
    for(i in 0..n){
        val w = doTask();
        if(w == 1){
            timesCh ++
        } else timesPl++
    }
    println("Вероятность победы чемпиона: 0.5577, вероятность победы претендента: 0.4423");
    println("Вероятность того что игр было = 20: 0.103")
    val v1 = timesCh.toDouble()/n
    val v2 = timesPl.toDouble()/n
    val v3 = games20.toDouble()/n
    println("---моделирование---")
    println("Вероятность победы чемпиона: $v1, вероятность победы претендента: $v2")
    println("Вероятность того что игр было = 20: $v3")
}

private fun main(){
    doResearch(1000000)
}