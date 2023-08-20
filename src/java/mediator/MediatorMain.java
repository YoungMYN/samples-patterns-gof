package mediator;

import mediator.TaxiPackage.TaxiType;
import mediator.concreteMediators.TaxiMediator;

/*
    Посредник(Mediator) – это поведенческий паттерн проектирования, который позволяет уменьшить связанность
    множества классов между собой, благодаря перемещению этих связей в один класс-посредник.

    Паттерн Посредник заставляет объекты общаться не напрямую друг с другом, а через
    отдельный объект-посредник, который знает, кому нужно перенаправить тот или иной запрос.
    Благодаря этому, компоненты системы будут зависеть только от посредника,
                                                                        а не от десятков других компонентов.
                                                                        
    В моем примере до внедрения посредника, код вызова такси в классе клиента выглядел так:
    public void orderTaxi(String taxiType, String to){
        Taxi taxi = station.getTaxi(taxiType);
        taxi.driveToAddress(geolocation);
        taxi.driveToAddress(to);
    }
    Классы Taxi и Client оказываются тесно связаны и изменения в классе такси, затронут код клиента...
    Но почитать почему сильная связанность классов это плохо, ты можешь и сам. Наша задача - сделать
    классы слабо связанными.
    Для этого мы внедрим ссылку на медиатор в клиентский класс(если класс имел бы родителя, лучше было бы
    внедрить нашего посредника именно туда, для универсальности, если это требуется)
    Теперь, вызов метода orderTaxi вызовет вызов одноименного метода у TaxiMediator, а сам класс посредника
    уже выполнит бизнес логику.
    Вообще, лучше (если это возможно) создать абстрактный интерфейс общего медиатора,
    и хранить ссылку именно этого типа во всех необходимых обьектов.
    Таким образом, каждый конкретный медиатор будет инкапсулировать конкретную бизнес-логику для
    конкретной ситуации, а сами классы можно будет повторно использовать для абсолютно других целей,
    поскольку они не будут сильно замусорены бизнес-логикой.

    В моем примере я создал абстрактный интерфейс общего посредника для заказа чего-либо, назвал его
    OrderMediator, с единственным методом order, его реализует уже конкретный класс TaxiMediator
    и в нем будет находится бизнес-логика вызова именно такси, клиент же не будет знать ничего об этой логике,
    он просто будет делать заказ, передавая в обьект абстракного медиатора нужные параметры, а
    заботится о бизнес логике будет уже конкретный медиатор, который мы будем передавать через конструктор.
    Таким образом, код становится гибким!Если мы захотим добавить клиенту функционал заказа пиццы, то
    нам нужно будет лишь реализовать пиццерию и создать конкретного медиатора, наследника
    абстрактного медиатора, который переопределит метод order своим образом и реализует там необходимую
    бизнес-логику(заказа пиццы).
    Тогда, мы сможем передать этот PizzaMediator в класс клиента через конструктор, и класс не претерпит
    абсолютно никаких изменений!
 */
public class MediatorMain {
    public static void main(String[] args) {
        Client alexey = new Client("Alexey", new TaxiMediator());
        alexey.setGeolocation("Moscow, Varshavskaya str., 44");

        alexey.order(TaxiType.ECONOMY,"Moscow, Mira pr., 10");
    }
}
/*
Преимущества
    +Устраняет зависимости между компонентами, позволяя повторно их использовать.
    +Упрощает взаимодействие между компонентами.
    +Централизует управление в одном месте.
Недостатки
    -Посредник может сильно раздуться.
 */