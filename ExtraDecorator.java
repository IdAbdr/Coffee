public class ExtraDecorator implements CoffeeDecorator{
    private CoffeeDecorator wrappee;
    private String extraName;
    private double extraCost;

    public ExtraDecorator(CoffeeDecorator wrappee, String extraName, double extraCost){
        this.wrappee=wrappee;
        this.extraName=extraName;
        this.extraCost=extraCost;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription()+", "+extraName;
    }

    @Override
    public double getCost() {
        return wrappee.getCost()+extraCost;
    }
}
