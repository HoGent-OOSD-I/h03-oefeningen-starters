import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RechthoekTest {

    @Test
    void maakRechthoek_ZonderOpgaveVanLengteEnBreedte_MaaktRechthoekMetLengte10Breedte7() {
        Rechthoek r = new Rechthoek();
        Assertions.assertEquals(10.0, r.getLengte());
        Assertions.assertEquals(7.0, r.getBreedte());
    }

    @Test
    void maakRechthoek_GeldigeLengteEnBreedte_MaaktRechthoek() {
        Rechthoek r = new Rechthoek(2.5, 4.5);
        Assertions.assertEquals(2.5, r.getLengte());
        Assertions.assertEquals(4.5, r.getBreedte());
    }

    @Test
    void maakRechthoek_LengteNul_MaaktRechthoek() {
        Rechthoek r = new Rechthoek(0, 4);
        Assertions.assertEquals(0, r.getLengte());
        Assertions.assertEquals(4.0, r.getBreedte());
    }

    @Test
    void maakRechthoek_BreedteNul_MaaktRechthoek() {
        Rechthoek r = new Rechthoek(4, 0);
        Assertions.assertEquals(4.0, r.getLengte());
        Assertions.assertEquals(0, r.getBreedte());
    }

    @Test
    void maakRechthoek_LengteNetNegatief_MaaktRechthoekMetLengte0() {
        Rechthoek r = new Rechthoek(-Double.MIN_VALUE, 4);
        Assertions.assertEquals(0, r.getLengte());
        Assertions.assertEquals(4.0, r.getBreedte());
    }

    @Test
    void maakRechthoek_LengteNegatief_MaaktRechthoekMetLengte0() {
        Rechthoek r = new Rechthoek(-10.55, 4);
        Assertions.assertEquals(0, r.getLengte());
        Assertions.assertEquals(4.0, r.getBreedte());
    }

    @Test
    void maakRechthoek_BreedteNetNegatief_MaaktRechthoekMetBreedte0() {
        Rechthoek r = new Rechthoek(4, -Double.MIN_VALUE);
        Assertions.assertEquals(4.0, r.getLengte());
        Assertions.assertEquals(0, r.getBreedte());
    }

    @Test
    void maakRechthoek_BreedteNegatief_MaaktRechthoekMetBreedte0() {
        Rechthoek r = new Rechthoek(4, -10.55);
        Assertions.assertEquals(4.0, r.getLengte());
        Assertions.assertEquals(0, r.getBreedte());
    }

    @Test
    void berekenOppervlakte_RetourneertJuisteOppervlakte() {
        Rechthoek r = new Rechthoek(2, 4);
        Assertions.assertEquals(8.0, r.berekenOppervlakte(), 0.01);
    }

    @Test
    void berekenOmtrek_RetourneertJuisteOmtrek() {
        Rechthoek r = new Rechthoek(2, 4);
        Assertions.assertEquals(12.0, r.berekenOmtrek(), 0.01);
    }

}
