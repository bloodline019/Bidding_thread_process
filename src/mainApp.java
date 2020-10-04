public class mainApp {

    public static void main(String[] args) {
        Random_Value_Fix_Time seller1 = new Random_Value_Fix_Time();
        Random_Value_Random_Time seller2 = new Random_Value_Random_Time();
        Fix_Value_Fix_Time seller3 = new Fix_Value_Fix_Time();
        Buyer buyer = new Buyer(100);
        seller1.runProducing(); // первый продавец (случайное количество в определенное время)
        seller2.runProducing(); // второй продавец (случайное количество в случайное время)
        seller3.runProducing(); // третий продавец (фиксированное количество в определенное время)
        buyer.addProducer(seller1);
        buyer.addProducer(seller2);
        buyer.addProducer(seller3);
        buyer.startConsuming();

    }
}
