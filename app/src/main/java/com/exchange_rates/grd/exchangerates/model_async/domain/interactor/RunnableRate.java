package com.exchange_rates.grd.exchangerates.model_async.domain.interactor;

import com.exchange_rates.grd.exchangerates.Rate;

import java.util.List;

public abstract class RunnableRate implements Runnable {

    //Модификатор volatile накладывает некоторые дополнительные условия на чтение/запись переменной.
    // Важно понять две вещи о volatile переменных:

    //1)  Операции чтения/записи volatile переменной являются атомарными.
    //2)  Результат операции записи значения в volatile переменную одним потоком, становится
    // виден всем другим потокам, которые используют эту переменную для чтения из нее значения.

    //volatile как бы говорит компилятору,
    // что значение переменной может в любой момент измениться из
    // другого потока и даже из другой программы, а он этого и не заметит.
    // Поэтому компилятор прекращает выполнять различную оптимизацию,
    // связанную с этой переменной, (к примеру,
    // помещение копии в регистры) и всегда читает ее значение из памяти.
        /*
        Определение переменной с ключевым словом volatile означает,
        что значение этой переменной может изменяться другими потоками.
        Чтобы понять, что делает volatile, полезно разобраться,
        как потоки обрабатывают обычные переменные.

В целях повышения производительности спецификация
языка Java допускает сохранение в JRE локальной копии переменной для каждого потока,
 который на нее ссылается. Такие "локальные" копии переменных напоминают кэш и помогают
 потоку избежать обращения к главной памяти каждый раз,
 когда требуется получить значение переменной.
 При запуске двух потоков один из них считывает переменную A как 5, а второй ― как 10.
 Если значение переменной А изменилось с 5 на 10,
  то первый поток не узнает об изменении и будет хранить неправильное значение A.
   Но если переменная А помечена как volatile, то когда бы поток не считывал значение A,
   он будет обращаться к главной копии A и считывать ее текущее значение.
   Локальный кэш потока имеет смысл в том случае,
   если переменные в ваших приложениях не будут изменяться извне.

Если переменная объявлена как volatile, это означает,
что она может изменяться разными потоками. Естественно ожидать,
что JRE обеспечит ту или иную форму синхронизации таких volatile-переменных.
JRE действительно неявно обеспечивает синхронизацию при доступе к volatile-переменным,
 но с одной очень большой оговоркой:
 чтение volatile-переменной и запись в volatile-переменную синхронизированы,
 а неатомарные операции ― нет.



голос «против»
для объектным ссылок volatile можно не писать. я прав?

Например, когда мы в многопоточном приложении используем паттерн Синглтон
в котором применяем синхронизацию и хотим чтобы синхронизация осуществлялась
только один раз при инициализации объекта, а не каждый раз,
когда мы вызываем getInstance(), тогда модификатора volatile используем для объектной ссылки:

public class Singleton {
    private static volatile Singleton instance;
    private Singleton(){
    }
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}

 */
    private volatile List<Rate> parameter;

    public RunnableRate(List<Rate> parameter){
        this.parameter = parameter;

    }

    //  public void run(){
    // do something with myParam here
    //  }

}