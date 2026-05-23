fun factorial(n: Int): Int{
    var result = 1;
    if(n <= 0) {
        return result
    }
    for( i in 1..n) result *= i
    return result;
}

fun sumDig(a: Int, b: Int, function: (n: Int) -> Double): Double{
    var res = 0.0;
    for(i in a..b){
        res += function(i);
    }
    return res;
}

fun Cnm(down: Int, up: Int): Double{
   return factorial(down).toDouble()/(factorial(up) * factorial(down - up));

}