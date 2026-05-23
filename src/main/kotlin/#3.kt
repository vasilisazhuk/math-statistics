import kotlin.math.abs
import kotlin.random.Random

private var time =0;

private fun doTask(l: Float, b: Double): Int{
    val a1 = (Random.nextFloat() * l);
    val a2 = (Random.nextFloat() * l);
    var t = 0;
    if(abs(a1 - a2) < l * b) {
        t++;
    }
    return t;
}

private fun analitic(l: Float, b: Double): Double{
    return b * (2 - b);
}
private fun doResearch(n: Int, l: Float, b: Double){
    time = 0;
    for(i in 0..n){
        time += doTask(l, b);

    }
    val a = analitic(l, b);
    println("Аналитически вычисленная вероятность: $a");
    println("Длина отрезка: $l, параметр b: $b")
    val v = time.toDouble()/n.toDouble()
    println("Вероятность: $v")
}

private fun main(){
    println("\n Опыт №1");
    doResearch(1000000, 110.1.toFloat(), 0.5);
    println("\n Опыт №2");
    doResearch(1000000, 13.888.toFloat(), 0.1);
    println("\n Опыт №3");
    doResearch(1000000, 1.8.toFloat(), 0.001);
}