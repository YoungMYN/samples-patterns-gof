package behavioral.strategy;

import behavioral.strategy.concreteStrategies.Firearm;

/*
    Стратегия(Strategy, Policy) - это поведенческий паттерн проектирования, который определяет семейство схожих
    алгоритмов и помещает каждый из них в собственный класс, после чего алгоритмы можно взаимозаменять
    прямо во время исполнения программы.

    Паттерн Стратегия предлагает определить семейство схожих алгоритмов, которые часто изменяются
    или расширяются, и вынести их в собственные классы, называемые стратегиями.

    Вместо того, чтобы изначальный класс сам выполнял тот или иной алгоритм, он будет играть роль контекста,
    ссылаясь на одну из стратегий и делегируя ей выполнение работы.
    Чтобы сменить алгоритм, вам будет достаточно подставить в контекст другой объект-стратегию.

    Важно, чтобы все стратегии имели общий интерфейс. Используя этот интерфейс, контекст будет независимым
    от конкретных классов стратегий. С другой стороны, вы сможете изменять и добавлять новые виды алгоритмов,
    не трогая код контекста.

    По сути, структура паттерна очень схожа с паттерном Состояние(State), однако есть несколько существенных
    отличий.
    Состояние можно рассматривать как надстройку над Стратегией. Оба паттерна используют композицию,
    чтобы менять поведение основного объекта, делегируя работу вложенным объектам-помощникам.
    Однако в Стратегии эти объекты не знают друг о друге и никак не связаны.
    В Состоянии сами конкретные состояния могут переключать контекст.

    По сути, разница в том, что паттерн Стратегия мы используем тогда, когда на одну и ту же задачу нам нужно
    несколько различных алгоритмов которые работают по разному.
    А паттерн Состояние мы используем тогда, когда речь идет о той ситуации, когда внутреннее состяние обьекта
    изменяет его поведение определенным образом.

    Вот пример:
    Когда в игре(как у меня в примере) вы даете персонажу разное оружие - вы переключаете стратегию.
    Например: одноручный меч (определенная анимация, скорость атаки),
    берем в руки двуручную кувалду - анимация ударов меняется, скорость атаки тоже.
    При этом вы просто жмете ту же кнопку "атаковать", но делается это по разному, в зависимости от оружия
    (стратегии).
    Теперь про State : представим что в игре есть магическое оружие, которое обладает двумя состояниями:
    1. Есть заряды душ - оно наносит дополнительный урон молнией, огнем, льдом..,
        соответственно меняется анимация оружия и урон.
    2. Заряды закончились - оружие просто наносит обычный физический урон без дополнительных эффектов,
        до тех пор пока его не зарядить камнем душ снова (то есть изменится его состояние).

    Применимость:
        -Когда вам нужно использовать разные вариации какого-то алгоритма внутри одного объекта.

        -Когда у вас есть множество похожих классов, отличающихся только некоторым поведением.

        -Когда вы не хотите обнажать детали реализации алгоритмов для других классов.

        -Когда различные вариации алгоритмов реализованы в виде развесистого условного оператора.
            Каждая ветка такого оператора представляет собой вариацию алгоритма.
 */
public class StrategyMain {
    public static void main(String[] args) {
        Hero hero = new Hero("IceHero2004");
        hero.attack();
        hero.setStrategy(new Firearm());
        hero.attack();
    }
}
/*
    Преимущества
        +Горячая замена алгоритмов на лету.
        +Изолирует код и данные алгоритмов от остальных классов.
        +Уход от наследования к делегированию.
        +Реализует принцип открытости/закрытости.
    Недостатки
        -Усложняет программу за счёт дополнительных классов.
        -Клиент должен знать, в чём состоит разница между стратегиями, чтобы выбрать подходящую.
 */