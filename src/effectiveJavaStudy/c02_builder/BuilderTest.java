package effectiveJavaStudy.c02_builder;

public class BuilderTest {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
	public enum Size { SMALL, MEDIUM, LARGE }
	public static void main(String[] args) {
		
		NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
		        .addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
		Calzone calzone = new Calzone.Builder()
		        .addTopping(Pizza.Topping.HAM).sauceInside().build();
	}
}
