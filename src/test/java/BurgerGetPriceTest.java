import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceTest {
    private float bunPrice;
    private List<Float> ingredientPrice;
    private float expectedPrice;

    public BurgerGetPriceTest(float bunPrice,List<Float> ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "тестовые данные с ценами: bunPrice, ingredientPrice, expectedPrice")
    public static Object [][] data() {
        return new Object[][] {
                {50f, Arrays.asList(10f, 20f), 130f}, //расчет смтоимости для булки с двумя начинками
                {0f, Arrays.asList(10f, 20f), 30f},  //расчет смтоимости без булки с двумя начинками
                {50f, Arrays.asList(10f), 110f},  //расчет смтоимости для булки с одной начинкой
                {50f, Arrays.asList(), 100f},  //расчет смтоимости для булки без начинки
                {0f, Arrays.asList(), 0f},  //расчет смтоимости для пустого заказа
        };
    }

   @Before
   public void setUp() {
       MockitoAnnotations.openMocks(this);
   }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientFirst;

    @Mock
    private Ingredient ingredientSecond;

   @Test
   public void testGetPriceForBurger() {
        Burger burger = new Burger();
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        if (!ingredientPrice.isEmpty()) {
            when(ingredientFirst.getPrice()).thenReturn(ingredientPrice.get(0));
            burger.addIngredient(ingredientFirst);
            if (ingredientPrice.size() > 1) {
                when(ingredientSecond.getPrice()).thenReturn(ingredientPrice.get(1));
                burger.addIngredient(ingredientSecond);
            }
        }
        assertEquals(expectedPrice,burger.getPrice(),0.1f);
   }
}