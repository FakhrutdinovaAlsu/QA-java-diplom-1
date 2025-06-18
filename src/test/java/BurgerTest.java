import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientFirst;

    @Mock Ingredient ingredientSecond;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBunToBurger() {
        burger.setBuns(bun);
        assertEquals(bun,burger.bun);
    }

    @Test
    public  void testAddIngredientToBurger() {
        burger.addIngredient(ingredientFirst);
        assertTrue(burger.ingredients.contains(ingredientFirst));
    }

    @Test
    public  void testCheckCountIngredientInBurgerAfterAdding() {
        burger.addIngredient(ingredientFirst);
        assertEquals("В наборе ингредиентов больше, чем было добавлено",1,burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientFromBurger() {
        burger.addIngredient(ingredientSecond);
        burger.removeIngredient(0);
        assertEquals("Ингредиент не удален",0,burger.ingredients.size());
    }

    @Test
    public void testMoveFirstIngredientInBurger() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(1,0);
        assertEquals("Ингредиент не перемещен",ingredientFirst,burger.ingredients.get(1));
    }

    @Test
    public void testMoveSecondIngredientInBurger() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(1,0);
        assertEquals("Ингредиент не перемещен", ingredientSecond,burger.ingredients.get(0));
    }
}