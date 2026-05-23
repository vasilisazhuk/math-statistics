private val domino = listOf<Pair<Int, Int>>(Pair(0, 0), Pair (0, 1), Pair (0, 2), Pair (0, 3), Pair (0, 4), Pair (0, 5), Pair (0, 6),
    Pair (1, 1), Pair (1, 2), Pair (1, 3), Pair (1, 4), Pair (1, 5), Pair (1, 6),
    Pair (2, 2), Pair (2, 3), Pair (2, 4), Pair (2, 5), Pair (2, 6),
    Pair (3, 3), Pair (3, 4), Pair (3, 5), Pair (3, 6),
    Pair (4, 4),  Pair (4, 5),  Pair (4, 6),
    Pair (5, 5),  Pair (5, 6),
    Pair (6, 6));

private fun doTask(): Boolean{
    val copy = domino.toMutableList()
    val n = (0..copy.size - 1).random();
    val first = copy[n];
    copy.removeAt(n);
    val n1 = (0..copy.size - 1).random()
    val second = copy[n1]
    if(first.first == second.second || first.first == second.first
        || first.second == second.first || first.second == second.second){
        return true
    }
    return false
}

private fun doResearch(n: Int){
    val b = 7.0/18;
    println("Аналитическое решение: $b")
    var time = 0;
    for(i in 0..n){
        if(doTask()){
            time++;
        }
    }
    val v = time.toDouble()/n
    println("Моделирование: $v")

}

private fun main(){
    doResearch(1000000);
}