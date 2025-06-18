import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {
        private Ingredient[] ingredients;
        private String expectedReceipt;
        private String bunName;
        private float bunPrice;


        public BurgerGetReceiptTest(String bunName, float bunPrice,  Ingredient[] ingredients, String expectedReceipt) {
            this.bunName = bunName;
            this.bunPrice = bunPrice;
            this.ingredients = ingredients;
            this. expectedReceipt = expectedReceipt;
        }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private Bun bun;

    @Parameterized.Parameters(name ="BunName, Bunprice, Ingredient [], expectedReceipt")
    public static Object [][] data() {
            return new Object[][] {
                    {"black bun", 100f,
                            new Ingredient[]{
                                    makeMockIngredient(IngredientType.FILLING, "cutlet", 100f),
                                    makeMockIngredient(IngredientType.SAUCE, "hot sauce", 100f)},
                            "(==== black bun ====)"+ System.lineSeparator() +
                            "= filling cutlet =" + System.lineSeparator() +
                            "= sauce hot sauce =" + System.lineSeparator() +
                            "(==== black bun ====)" + System.lineSeparator() +
                                    System.lineSeparator() +
                            "Price: 400,000000" + System.lineSeparator()}, //проверка бургера с булочкой с соусом и начинкой
                    {"black bun", 100f,
                            new Ingredient[]{
                                    makeMockIngredient(IngredientType.SAUCE, "hot sauce", 100f),
                                    makeMockIngredient(IngredientType.SAUCE, "sour cream", 200f)},
                            "(==== black bun ====)" + System.lineSeparator() +
                            "= sauce hot sauce =" + System.lineSeparator() +
                            "= sauce sour cream =" + System.lineSeparator() +
                            "(==== black bun ====)" + System.lineSeparator() +
                                    System.lineSeparator() +
                            "Price: 500,000000" + System.lineSeparator()}, //проверка бургера с булочкой и двумя соусами
                    {"black bun", 100f,
                            new Ingredient[]{
                                    makeMockIngredient(IngredientType.FILLING, "cutlet", 100f)},
                            "(==== black bun ====)"+ System.lineSeparator() +
                            "= filling cutlet ="+ System.lineSeparator() +
                            "(==== black bun ====)"+ System.lineSeparator() +
                                    System.lineSeparator() +
                            "Price: 300,000000" + System.lineSeparator()}, //проверка бургера с булочкой с начинкой
                    {"black bun", 100f, new Ingredient[]{},
                            "(==== black bun ====)"+ System.lineSeparator() +
                            "(==== black bun ====)"+ System.lineSeparator() +
                                    System.lineSeparator() +
                            "Price: 200,000000" + System.lineSeparator()} //проверка бургера с булочкой без ингредиентов
            };
    }

    private static Ingredient makeMockIngredient (IngredientType type, String name, float price) {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(type);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Test
    public void testGetReceiptForBurger() {
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
            for (Ingredient ingredient:ingredients) {
                burger.addIngredient(ingredient);
    }
    String actualReceipt = burger.getReceipt();
    assertEquals(expectedReceipt,actualReceipt);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWhenBunIsNull() {
        new Burger().getReceipt();
    }
}