import java.util.ArrayList;
import java.util.List;

public class Buyer implements Consumer {
    private Thread thread;
    private List<Producer> producers = new ArrayList<>();
    private double storage = 0;
    private int buyValue;

    public Buyer(int value) {
        this.buyValue = value;
    }

    @Override
    public void addProducer(Producer p) {
        producers.add(p);
    }

    @Override
    public void startConsuming() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double bestPrice = Double.MAX_VALUE;
                    Producer bestProducer = null; // на каждом заходе сбрасываем лучшего продавца
                    for (var producer : producers) {
                        double price = producer.getPrice(buyValue, false); //  запрос цены у конкретного продавца (50 сколько нужно купить)
                        if (bestPrice > price && price != -1) {
                            bestProducer = producer;
                            bestPrice = price;
                        }
                    }
                    System.out.println("Покупатель: хочу приобрести " + buyValue + " единиц товара.");
                    if (bestProducer != null) {
                        bestProducer.buy(buyValue);
                        System.out.println("Покупатель приобрел товар у " + bestProducer.getName() + " по цене " + bestPrice + ", переход к следующему циклу торгов");
                    } else {
                        System.err.println("У продавцов недостаточно товара, переход к следующему циклу торгов");
                    }
                }
            }
        });
        thread.start();
    }
}
