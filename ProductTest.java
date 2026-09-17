package domein;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    // Constanten

    final int ONDERGRENS_BTW = 6, BOVENGRENS_BTW = 21,
            BOVENGRENS_KORTING_STUKS = 50, DEFAULT_PRIJS_EXCL_BTW = 1;

    // Zet twee geldige producten klaar vóór elke @Test

    private Product p1, p2;

    @BeforeEach
    void before() {
        p1 = new Product("Veggie Smoothie", 1.48, 18, 5);
        p2 = new Product("Fruit Smoothie", 1.48, 12, 10);
    }

    // Tests constructor

    @Test
    void maakProduct_GeldigeParams_MaaktProduct() {
        Product p = new Product("Spa Bruis", 1.50, 10, 20);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(1.50, p.getPrijsExclBtw());
        Assertions.assertEquals(10, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }

    // dubbele code vermijden: gebruik geparametriseerde tests waar mogelijk
    @ParameterizedTest
    @ValueSource(ints = { 0, BOVENGRENS_KORTING_STUKS})
    void maakProduct_KortingStuksPercentageNetOK_MaaktProduct(int kortingStuksPercentage) {
        Product p = new Product("Spa Bruis", 1.50, 10, kortingStuksPercentage);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(1.50, p.getPrijsExclBtw());
        Assertions.assertEquals(10, p.getBtwPercentage());
        Assertions.assertEquals(kortingStuksPercentage, p.getKortingStuksPercentage());
    }


    @ParameterizedTest
    @ValueSource(ints = { -1, -50, BOVENGRENS_KORTING_STUKS + 1, 88})
    void maakProduct_KortingStuksPercentageNietOK_MaaktProductMetKortingStuksPercentage0(int kortingStuksPercentage) {
        Product p = new Product("Spa Bruis", 1.50, 21, kortingStuksPercentage);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(1.50, p.getPrijsExclBtw());
        Assertions.assertEquals(21, p.getBtwPercentage());
        Assertions.assertEquals(0, p.getKortingStuksPercentage());
    }


    @ParameterizedTest
    @ValueSource(ints = {ONDERGRENS_BTW, BOVENGRENS_BTW})
    void maakProduct_BtwPercentageNetOK_MaaktProduct(int btw) {
        Product p = new Product("Spa Bruis", 1.50, btw, 20);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(1.50, p.getPrijsExclBtw());
        Assertions.assertEquals(btw, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }


    @ParameterizedTest
    @ValueSource(ints = {BOVENGRENS_BTW + 1, 35, ONDERGRENS_BTW - 1, 1})
    void maakProduct_BtwPercentageNietOK_MaaktProductMetBtwPercentage21(int btw) {
        Product p = new Product("Spa Bruis", 1.50, btw, 20);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(1.50, p.getPrijsExclBtw());
        Assertions.assertEquals(BOVENGRENS_BTW, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }


    @Test
    void maakProduct_PrijsExclBtwNetPositief_MaaktProduct() {
        Product p = new Product("Spa Bruis", Double.MIN_VALUE, 20, 20);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(Double.MIN_VALUE, p.getPrijsExclBtw());
        Assertions.assertEquals(20, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-10})
    void maakProduct_PrijsExclBtwNulOfNegatief_MaaktProductMetPrijs1(int prijs) {
        Product p = new Product("Spa Bruis", prijs, 20, 20);
        Assertions.assertEquals("Spa Bruis", p.getNaam());
        Assertions.assertEquals(DEFAULT_PRIJS_EXCL_BTW, p.getPrijsExclBtw());
        Assertions.assertEquals(20, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void maakProduct_NaamNullOfBlanco_MaaktProductMetNaamOnbekend(String naam) {
        Product p = new Product(naam, 20, 15, 20);
        Assertions.assertEquals("naam onbekend", p.getNaam());
        Assertions.assertEquals(20, p.getPrijsExclBtw());
        Assertions.assertEquals(15, p.getBtwPercentage());
        Assertions.assertEquals(20, p.getKortingStuksPercentage());
    }

    // Tests setPrijsExclBtw

    @ParameterizedTest
    @ValueSource(doubles = {33.66,Double.MIN_VALUE})
    void setPrijsExlBtw_PrijsPositief_SteltPrijsIn(double prijs) {
        p2.setPrijsExclBtw(prijs);
        Assertions.assertEquals(prijs, p2.getPrijsExclBtw());
    }


    @ParameterizedTest
    @ValueSource(doubles = {0, -10.5})
    void setPrijsExlBtw_PrijsNulOfNegatief_WijzigtPrijsNiet(double prijs) {
        p2.setPrijsExclBtw(prijs);
        Assertions.assertEquals(1.48, p2.getPrijsExclBtw());
    }

    // Tests berekenPrijsMetBtw

    @Test
    void berekenPrijsMetBtw_Aantal1_RetourneertPrijsMetBtwZonderKorting() {
        Assertions.assertEquals(1.7464, p1.berekenPrijs(1), 0.01);
    }

    @Test
    void berekenPrijsMetBtw_Aantal5_RetourneertPrijsMetBtwZonderKorting() {
        Assertions.assertEquals(8.732, p1.berekenPrijs(5), 0.01);
    }

    @Test
    void berekenPrijsMetBtw_Aantal6_RetourneertPrijsMetBtwMetKorting() {
        Assertions.assertEquals(9.95448, p1.berekenPrijs(6), 0.01);
    }

    @Test
    void berekenPrijsMetBtw_Aantal10_RetourneertPrijsMetBtwMetKorting() {
        Assertions.assertEquals(16.5908, p1.berekenPrijs(10), 0.01);
    }

    @Test
    void berekenPrijsMetBtw_Aantal0_Retourneert0() {
        Assertions.assertEquals(0.0, p1.berekenPrijs(0));
    }
}
