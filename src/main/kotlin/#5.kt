import kotlin.math.pow
import kotlin.random.Random


private fun oneGame(p: Double):Int{
    val res = (Math.random() * 1)
    //println(res)
    if (res <= p) return 1
    else return 0
}

private  fun doTask(m: Int, n: Int, p: Double): Int{
    var win1 = 0
    var win2 = 0
    var pob = 0;
    for(i in 1 until m+n){
        pob = oneGame(p);
        if(win1 == m) break
        if (win2 == n) break
        if(pob == 1) win1 ++
        else win2++
    }
    if(win1 == m){
        return 1
    } else return 0
}

var mGlobal = 0;
var nGlobal = 0;
var pGlobal = 0.0;
private fun analitic1(m: Int, n: Int, p: Double, i:Int): Double{
    return  Cnm(m + i - 1, i) * (1 - p).pow(i)
}

private fun parser(i: Int): Double{
    return analitic1(mGlobal, nGlobal, pGlobal, i);
}

private fun doResearch (n: Int, m: Int, n1: Int, p: Double){

    mGlobal = m-1
    nGlobal = n1-1
    pGlobal = p
    val a = p.pow(m) * (1 + sumDig(1, n1 - 1, ::parser))
    println("Аналитическое решение: $a")
    println("При количестве победд 1го: $m вероятности p = $p количестве побед второго: $n1")

    var times = 0;
    for(i in 0..n){

            times += doTask(mGlobal, nGlobal, pGlobal)
    }
    println(times.toDouble()/n)

}


private fun main(){
    println("Опыт №1")
    doResearch(10000000, 3, 2, 0.9)
    println("Опыт №2")
    doResearch(10000000, 4, 2, 0.8)
    println("Опыт №3")
    doResearch(10000000, 3, 5, 0.9)


}