import java.util.Random;

public class Random_Value_Fix_Time implements Producer { // Случ. кол-во товара в определенное время
    private double value = 0; // количество товара на складе
    private Random r = new Random(); // генератор рандома
    private Thread thread;
    private String name = "Продавец 1";
    private double minPrice = 29; // так как .nextInt генерирует число от 1, то минимально будет 30
    private double price = r.nextInt(100) + minPrice;

    public String getName() {
        return name;
    }

    @Override
    public double getPrice(double boughtValue, boolean inside) {
        if (value >= boughtValue && inside == false) { // если количество товара больше запрашиваемого, то возращаем цену
            double outPrice = value * price; // итоговая цена зависит от рандома цены и кол-ва товара на складе
            return outPrice;
        } else if (inside) {
            return value * price;
        } else {
            return -1; // иначе возвращаем -1 (сообщает о недостатке товара)
        }
    }

    @Override
    public void buy(int boughtValue) {
        this.value -= boughtValue; // при покупке количество товара уменьшается соответвенно кол-ву купленного товара
    }

    @Override
    public double runProducing() { // Запускает поток заполнения склада товаром
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        thread.sleep(10000); // фиксированное фремя
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value += r.nextDouble() * 20; // количество товара увеличивается на (0.0, 20)
                    if (value > 100) { // больше ста не может быть? зачем это условие в примере - ХЗ
                        value = 100;
                    }
                    System.out.println(getName() + ": У меня " + value + " единиц товара по цене " + getPrice(value, true));
                }
            }
        });
        thread.start();
        return 0;
    }
}
