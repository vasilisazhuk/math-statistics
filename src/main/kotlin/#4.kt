 private fun stopSignAB ():Int {
     //0.1 - вероятность остановки
     return if((0..9).random() == 0) 1
     else 0;
 }

 private fun stopSignBC():Int{
     //0.3 - вероятность остановки
     return if((0..9).random() <= 2) 1
     else 0;
 }

 private fun doTask(): Int{
     var h = 0
  for(i in 0..11){
      h += stopSignAB();
     }
     h += stopSignBC();
     if(h == 0) return 1
     else return 0
 }

 private fun doReseatch(n: Int){
     var time =0;
     for(i in 0..n){
        time += doTask()
     }

     println("Аналитически вычисленная вероятность: 0.197");
     val v =  time.toDouble()/n.toDouble()
     println("Вероятность: $v")
 }

 private fun main(){
     doReseatch(1000000);
 }