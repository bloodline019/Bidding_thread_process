public interface Producer {
    public double getPrice(double boughtValue, boolean inside);

    public void buy(int boughtValue);

    public double runProducing();

    public String getName();
}
