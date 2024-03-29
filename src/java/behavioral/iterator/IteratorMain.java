package behavioral.iterator;

import behavioral.iterator.concreteAggregates.MyContainer1;
import behavioral.iterator.concreteAggregates.MyContainer2;

/*
    Итератор – это поведенческий паттерн проектирования, который даёт возможность последовательно
    обходить элементы составных объектов, не раскрывая их внутреннего представления.

    Идея паттерна Итератор состоит в том, чтобы вынести поведение обхода коллекции из самой коллекции
    в отдельный класс.
    Объект-итератор будет отслеживать состояние обхода, текущую позицию в коллекции, и сколько элементов
    ещё осталось обойти. Одну и ту же коллекцию смогут одновременно обходить различные итераторы,
    а сама коллекция не будет даже знать об этом.

    Клиент, пользуясь интерфейсом Aggregate, получает итератор Concretelterator
    для обхода определенного класса списков.
    Concretelterator отслеживает текущую позицию обхода списка, может возвратить текущий или следующий
    по порядку элемент, а также может сказать не достигли ли мы конца списка.
    Пользуясь этими операциями, клиент может проитерировать любой список,
    предоставляющий свой Concretelterator.

    Обычно, для итерирования коллекции, нам будет достаточно 1го итератора, поэтому в Java классы
    конкретных итераторов - приватные внутренние классы конкретных коллекций(При этом имплементирующие
    интерфейс Iterator, a коллекция в свою очередь интерфейс Aggregate(в Java - Iterable)), поскольку
    не имеет смысла рассматривать конкретный итератор конкретной коллекции в отрыве от нее.
    Однако так же возможен вариант с отдельным классом итератора и передачу в него коллекции через конструктор.

    Java обеспечивает поддержку данного паттерна на уровне ядра языка(interface Iterable<E>,
    interface Iterator<T>), и интерфейс Collection наследуется от Iterable.

    В данном примере у меня есть 2 контейнера, обладающие определенной логикой.
    Контейнер с именами, например я хочу итерировать по очереди, с первого до последнего элемента,
    а контейнер хранящий мои задачи на день, я хочу итерировать, проходясь по их приоритету: самые важные
    задачи будут итерироваться первыми, а остальные - в порядке приоритета.

    Внутренние массивы инкапсулированы, поскольку я хочу, что бы их итерация или изменение проходили только
    с помощью итератора(который так же инкапсулирован внутри класса контейнера, поскольку он привязан к нему)
    Пользователю не обязательно знать детали реализации контейнеров/коллекций, пользователь будет работать с
    обьектом Итератора, который всегда обладает одним и тем же простым интерфейсом, а получить этот обьект
    можно будет методом getIterator(в java.util этот метод почему то называется просто behavioral.iterator)

    Итератор полезен, когда вам хочется иметь единый интерфейс обхода различных структур данных.
    Итератор позволяет вынести реализации различных вариантов обхода в подклассы.
    Это позволит легко взаимозаменять объекты итераторов, в зависимости от того, с какой
    структурой данных приходится работать.
*/

public class IteratorMain {
    public static void main(String[] args) {
        Aggregate c1 = new MyContainer1();
        Iterator iterator = c1.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("----------------------");

        Aggregate c2 = new MyContainer2();
        Iterator it = c2.getIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}
