import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriehoekTest {
    private Driehoek standaardDriehoek;
    private final int STANDAARD_WAARDE = 1;

    // Code die wordt uitgevoerd vóór elke test: geldige driehoek maken
    
    @BeforeEach
    void setUp() {
        standaardDriehoek = new Driehoek(3, 4, 5);
    }

    // Tests voor constructor

    @Test
    void maakDriehoek_DrieGeldigeZijden_MaaktDriehoek() {
        Assertions.assertEquals(3, standaardDriehoek.getA());
        Assertions.assertEquals(4, standaardDriehoek.getB());
        Assertions.assertEquals(5, standaardDriehoek.getC());
    }

    // Tests voor Zijde A

    @Test
    void maakDriehoek_ZijdeAGelijkAanNul_MaaktDriehoekMetZijdeAGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(0, 5, 8);
        Assertions.assertEquals(STANDAARD_WAARDE, d.getA());
        Assertions.assertEquals(5, d.getB());
        Assertions.assertEquals(8, d.getC());
    }

    @Test
    void maakDriehoek_ZijdeANegatief_MaaktDriehoekMetZijdeAGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(-10, 5, 8);
        Assertions.assertEquals(STANDAARD_WAARDE, d.getA());
        Assertions.assertEquals(5, d.getB());
        Assertions.assertEquals(8, d.getC());
    }

    // Tests voor Zijde B

    @Test
    void maakDriehoek_ZijdeBGelijkAanNul_MaaktDriehoekMetZijdeBGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(5, 0, 8);
        Assertions.assertEquals(5, d.getA());
        Assertions.assertEquals(STANDAARD_WAARDE, d.getB());
        Assertions.assertEquals(8, d.getC());
    }

    @Test
    void maakDriehoek_ZijdeBNegatief_MaaktDriehoekMetZijdeBGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(5, -10, 8);
        Assertions.assertEquals(5, d.getA());
        Assertions.assertEquals(STANDAARD_WAARDE, d.getB());
        Assertions.assertEquals(8, d.getC());
    }

    // Tests voor Zijde C

    @Test
    void maakDriehoek_ZijdeCGelijkAanNul_MaaktDriehoekMetZijdeCGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(5, 8, 0);
        Assertions.assertEquals(5, d.getA());
        Assertions.assertEquals(8, d.getB());
        Assertions.assertEquals(STANDAARD_WAARDE, d.getC());
    }

    @Test
    void maakDriehoek_ZijdeCNegatief_MaaktDriehoekMetZijdeCGelijkAanStandaardWaarde() {
        Driehoek d = new Driehoek(5, 8, -10);
        Assertions.assertEquals(5, d.getA());
        Assertions.assertEquals(8, d.getB());
        Assertions.assertEquals(STANDAARD_WAARDE, d.getC());
    }

    // Tests voor isRechthoekig

    @Test
    void isRechthoekig_RechthoekigeDriehoekMetSchuineZijdeA_retourneertTrue() {
        Driehoek d = new Driehoek(5, 3, 4);
        Assertions.assertTrue(d.isRechthoekig());
    }

    @Test
    void isRechthoekig_RechthoekigeDriehoekMetSchuineZijdeB_retourneertTrue() {
        Driehoek d = new Driehoek(3, 5, 4);
        Assertions.assertTrue(d.isRechthoekig());
    }

    @Test
    void isRechthoekig_RechthoekigeDriehoekMetSchuineZijdeC_retourneertTrue() {
        Assertions.assertTrue(standaardDriehoek.isRechthoekig());
    }

    @Test
    void isRechthoekig_GeenRechthoekigeDriehoek_retourneertFalse() {
        Driehoek d = new Driehoek(5, 4, 5);
        Assertions.assertFalse(d.isRechthoekig());
    }
}