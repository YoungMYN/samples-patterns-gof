package factoryMethod;

public abstract class CoffeeFabric {
    public Coffee getPackedCoffee(){
        Coffee coffee = produceCoffee();
        if(!coffee.isPacked()){
            coffee.setPacked(true);
            System.out.println("Coffee packed!");
        }
        return coffee;
    }/*
    Фабричный метод собственной персоной. Он абстрактен, его реализуют наследники кофейной фабрики
    и сами решат какой кофе производить. Мы делегируем создание продукта классам наследникам,
    поскольку не знаем,что именно мы будем производить. Возможно в будущем изобретут еще какой нибудь
    вид кофе, ранее неизведанный, и нам для его производства НЕ нужно будет писать новое полотно кода.
    Всего лишь надо будет создать фабрику, которая переопределит метод и создаст этот новый вид кофе.
    Прелесть в том что мы всегда будем пользоваться фабричным методом для создания кофе.
    Если вдруг процедура создания кофе изменится, например нужно будет вести счетчик
     произведенного кофе, мы будем считать это в данном методе, а не искать ключевое слово new по
    всем классам программы, вдруг где то забыли посчитать.
    */
    protected abstract Coffee produceCoffee();
}
