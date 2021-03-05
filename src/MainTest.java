import static org.junit.Assert.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void getBMICategory() {
        // Boundary (7.5, 30) - smallest change = 1
        // ON Point - Min
        assertEquals("Underweight", Main.getBMICategory(7.5));
        // OFF point - Open bound = internal point
        assertEquals("Underweight", Main.getBMICategory(8.5));
        // Internal point
        assertEquals("Normal weight", Main.getBMICategory(24));
        // OFF point - open bound = internal point
        assertEquals("Overweight", Main.getBMICategory(29));
        // ON Point - Max
        assertEquals("Obese", Main.getBMICategory(30));
    }

    @org.junit.jupiter.api.Test
    void calcBMI() {
        // weight is only changing
        assertEquals(1.728395, Main.calcBMI(4, 6, 7),0.000001);
        assertEquals(1.975308, Main.calcBMI(4, 6, 8),0.000001);
        assertEquals(148.1481, Main.calcBMI(4, 6, 600),0.0001);
        assertEquals(148.3950, Main.calcBMI(4, 6, 601),0.001);
        // inches is only changing
        assertEquals(93.75, Main.calcBMI(4, 0,300),0.01);
        assertEquals(89.96251, Main.calcBMI(4, 1, 300),0.00001);
        assertEquals(64.20927, Main.calcBMI(4, 10, 300),0.00001);
        assertEquals(60, Main.calcBMI(4, 12, 300),0);
        // feet is only changing
        assertEquals(6000, Main.calcBMI(0, 6, 300),0);
        assertEquals(666.667, Main.calcBMI(1, 6, 300),0.001);
        assertEquals(20.7612, Main.calcBMI(8, 6, 300),0.0001);
        assertEquals(16.6205, Main.calcBMI(9, 6, 300),0.0001);
        // internal point
        assertEquals(74.0740, Main.calcBMI(4, 6, 300),0.0001);
    }

    @org.junit.jupiter.api.Test
    void calcSavingsPerYr() {
        // Boundary of salary (0, 500000]
        // Boundary of % saved (0, 1.0]
        // Weak 1x1 of salary
        assertEquals(0, Main.calcSavingsPerYr(0, 0.5),0);
        assertEquals(135, Main.calcSavingsPerYr(200, 0.5),0);
        assertEquals(337500, Main.calcSavingsPerYr(500000, 0.5),0);
        assertEquals(337500.675, Main.calcSavingsPerYr(500001, 0.5),0.1);
        // Weak 1x1 of percent saved
        assertEquals(0, Main.calcSavingsPerYr(150000, 0),0);
        assertEquals(40500, Main.calcSavingsPerYr(150000, 0.2),0);
        assertEquals(202500, Main.calcSavingsPerYr(150000, 1.0),0);
        assertEquals(222750, Main.calcSavingsPerYr(150000, 1.1),0);
        // Internal Point
        assertEquals(101250, Main.calcSavingsPerYr(150000, 0.5),0);
    }

    @org.junit.jupiter.api.Test
    void yrsTillGoal() {
        // Boundary of savingsGoal (0, 2000000]
        // Boundary of savings per year (1, 20000]
        // Weak 1x1 of savings goal
        assertEquals(0, Main.yrsTillGoal(0, 10000),0);
        assertEquals(20, Main.yrsTillGoal(200000, 10000),0);
        assertEquals(200, Main.yrsTillGoal(2000000, 10000),0);
        assertEquals(201, Main.yrsTillGoal(2000001, 10000),0);
        // Weak 1x1 of savings per year
        assertEquals(1000000, Main.yrsTillGoal(1000000, 1),0);
        assertEquals(200, Main.yrsTillGoal(1000000, 5000),0);
        assertEquals(50, Main.yrsTillGoal(1000000, 20000),0);
        assertEquals(50, Main.yrsTillGoal(1000000, 20001),0);
        // Internal Point
        assertEquals(100, Main.yrsTillGoal(1000000, 10000),0);
    }

    @org.junit.jupiter.api.Test
    void ageWhenMet() {
        // boundary of age (0,100)
        // boundary of salary (0, 500000]
        // boundary of % saved (0, 1.0]
        // boundary of savings goal (0,2000000]

        // age only changes
        assertEquals(15, Main.ageWhenMet(0, 100000, 0.5, 1000000));
        assertEquals(16, Main.ageWhenMet(1, 100000, 0.5, 1000000));
        assertEquals(114, Main.ageWhenMet(99, 100000, 0.5, 1000000));
        assertEquals(115, Main.ageWhenMet(100, 100000, 0.5, 1000000));
        // salary only changes
        assertEquals(1481532, Main.ageWhenMet(50, 1, 0.5, 1000000));
        assertEquals(7458, Main.ageWhenMet(50, 200, 0.5, 1000000));
        assertEquals(53, Main.ageWhenMet(50, 500000, 0.5, 1000000));
        assertEquals(53, Main.ageWhenMet(50, 500001, 0.5, 1000000));
        // percent saved only changes
        assertEquals(791, Main.ageWhenMet(50, 100000, 0.01, 1000000));
        assertEquals(88, Main.ageWhenMet(50, 100000, 0.2, 1000000));
        assertEquals(58, Main.ageWhenMet(50, 100000, 1.0, 1000000));
        assertEquals(57, Main.ageWhenMet(50, 100000, 1.1, 1000000));
        // savings goal only changes
        assertEquals(50, Main.ageWhenMet(50, 100000, 0.5, 0));
        assertEquals(53, Main.ageWhenMet(50, 100000, 0.5, 200000));
        assertEquals(80, Main.ageWhenMet(50, 100000, 0.5, 2000000));
        assertEquals(80, Main.ageWhenMet(50, 100000, 0.5, 2000001));
        // internal point
        assertEquals(65, Main.ageWhenMet(50, 100000, 0.5, 1000000));
    }

    @org.junit.jupiter.api.Test
    void isGoalMet() {
        // boundary of age (0, 100)
        assertTrue(Main.isGoalMet(0));
        assertTrue(Main.isGoalMet(1));
        assertTrue(Main.isGoalMet(50));
        assertTrue(Main.isGoalMet(99));
        assertFalse(Main.isGoalMet(100));
    }
}