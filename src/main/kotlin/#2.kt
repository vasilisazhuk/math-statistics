import java.lang.Math.pow
import kotlin.math.pow

private var times =0;

private fun doingTask(l: Int, k: Int){
    val s = mutableListOf<Int>();
    var inPlace = 0;
    for (i in 0..l){
        s.add(i, i);
    }
    for(i in l downTo 0){
        val j = (0..i).random()
        val tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
        if(s[j] == j){
            inPlace++;
        }
    }
    //println(s);
    if(inPlace == k){
        times++;
    }

}

private fun analitic(n: Int): Double{
    return (-1.0).pow(n) / factorial(n);
}

private fun doResearch(n: Int, l: Int, k: Int){
    times =0;
    for(i in 0..n) {
        doingTask(l, k);
    }
    var a = sumDig(0, l-k, ::analitic) / factorial(k)
    println("Аналитически высчитанная вероятность: $a")
    println("Количество шаров l: $l, количество совпадений k: $k");
    println("Количество перетасовок при которых k номеров совпали: $times");
    val v = times.toDouble()/n.toDouble();
    println("Полученная вероятность: $v");
}

private fun main(){
    println("\n Опыт №1");
    doResearch(1000000, 10, 4);

    println("\n Опыт №2");
    doResearch(1000000, 15, 5);

    println("\n Опыт №3");
    doResearch(1000000, 17, 3);
    //doResearch(10000, 10, 4);
}