package chainOfResponsibility;

import chainOfResponsibility.concreteHandlers.*;
/*
    Цепочка обязанностей – это поведенческий паттерн проектирования, который позволяет передавать
    запросы последовательно по цепочке обработчиков. Каждый последующий обработчик решает,
    может ли он обработать запрос сам и стоит ли передавать запрос дальше по цепи.

    Цепочка обязанностей базируется на том, чтобы превратить отдельные поведения в объекты.
    В нашем случае каждая проверка переедет в отдельный класс с единственным методом выполнения.
    Данные запроса, над которым происходит проверка, будут передаваться в метод как аргументы.
    А теперь по-настоящему важный этап. Паттерн предлагает связать объекты обработчиков в одну цепь.
    Каждый из них будет иметь ссылку на следующий обработчик в цепи. Таким образом, при получении запроса
    обработчик сможет не только сам что-то с ним сделать, но и передать обработку следующему объекту в цепочке.
    Передавая запросы в первый обработчик цепочки, вы можете быть уверены, что все объекты в цепи смогут
    его обработать. При этом длина цепочки не имеет никакого значения.
    И последний штрих. Обработчик не обязательно должен передавать запрос дальше,
    причём эта особенность может быть использована по-разному.
    В примере с фильтрацией доступа обработчики прерывают дальнейшие проверки, если текущая проверка
    не прошла. Ведь нет смысла тратить попусту ресурсы, если и так понятно, что с запросом что-то не так.

    Следующий обработчик мы можем устанавливать через конструктор, и тогда код приобретет вид:
        Handler handler = new Handler1(new Handler2(new Handler3(null)));
    Мы можем устанавливать значение с помощью классического сеттера setNext, а можем добавить возврат обьекта,
    установленного в поле next, что бы код создания цепочки выглядел так:
        handler1.setNext(handler2).setNext(handler3).setNext(handler4);

    Применимость
    -Когда программа должна обрабатывать разнообразные запросы несколькими способами, но заранее неизвестно,
     какие конкретно запросы будут приходить и какие обработчики для них понадобятся.

    -С помощью Цепочки обязанностей вы можете связать потенциальных обработчиков в одну цепь и
     при получении запроса поочерёдно спрашивать каждого из них, не хочет ли он обработать запрос.

    -Когда важно, чтобы обработчики выполнялись один за другим в строгом порядке.

    -Цепочка обязанностей позволяет запускать обработчиков последовательно один за другим в том порядке,
     в котором они находятся в цепочке.

    - Когда набор объектов, способных обработать запрос, должен задаваться динамически.

    -В любой момент вы можете вмешаться в существующую цепочку и переназначить связи так, чтобы убрать или добавить новое звено.

    В моем примере кода, различные платежные системы представляют собой обработчики.
    Скажем, дело происходит в онлайн магазине, и при нажатии кнопки КУПИТЬ, срабатывает умная система платежей
    и клиенту не нужно выбирать с какого счета спишутся деньги. Он просто указывает приоритет для различных
    платежных систем. Если денег на сбербанке недостаточно для покупки - система попробует списать деньги с
    карты Тинькофф банка, а если не не достаточно и там - с киви кошелька. Если платеж не проходит,
    а обработчик последний в цепочке - клиент получает сообщение, мол, недостаточно денег для покупки.
 */
public class CORMain {
    public static void main(String[] args) {
        PaymentTransactionHandler qiwi = new Qiwi(300);
        PaymentTransactionHandler tinkoff = new TinkoffBank(200);
        PaymentTransactionHandler sber = new SberBank(100);

        sber.setNext(tinkoff);
        tinkoff.setNext(qiwi);

        sber.pay(299);//Payed 299 from Qiwi. Balance: 1

        sber.pay(80);//Payed 80 from SberBank. Balance: 20

        sber.pay(30);//Payed 30 from TinkoffBank. Balance: 170

        sber.pay(200);//Can't pay, not enough money in your accounts
    }
}
/*
Преимущества
    +Уменьшает зависимость между клиентом и обработчиками.
    +Реализует принцип единственной обязанности.
    +Реализует принцип открытости/закрытости.
Недостатки
    -Запрос может остаться никем не обработанным.
 */