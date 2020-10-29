package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void testaaKonstruktoriaKunNegatiivinenTilavuus() {
        Varasto v = new Varasto(-1);

        assertEquals(0.0, v.getTilavuus(), 0.0000001);
    }

    @Test
    public void testaaKonstruktoriaLiianIsollaAlkuSaldolla() {
        Varasto v = new Varasto(2.0,3.0);

        assertEquals(2.0, v.getSaldo(), 0.000001);
    }

    @Test
    public void testaaKonstruktoriaKunSaldoaVahemmanKuinTilavuutta() {
        Varasto v = new Varasto(2.0,1.0);

        assertEquals(1.0, v.getSaldo(), 0.000001);
    }

    @Test
    public void testaaKonstruktoriaNegatiivisellaAlkusaldolla() {
        Varasto v = new Varasto(2.0,-1.0);

        assertEquals(0.0,v.getSaldo(),0.000001);
    }

    @Test
    public void testaaAlkusaldollistaKonstruktoriaNegatiivisellaVarastonTilavuudella() {
        Varasto v = new Varasto(-2.0,1.0);
        assertEquals(0.0,v.getTilavuus(),0.000001);
    }

    @Test
    public void metodiLisaaVarastoonEiTeeMitaanJosMaaraNegatiivinen() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);

        assertEquals(saldo,varasto.getSaldo(),0.00000001);
    }

    @Test
    public void lisaaVarastoonTayttaaSenMitaMahtuu() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(12);

        assertEquals(tilavuus,varasto.getSaldo(),0.000001);
    }

    @Test
    public void lisaaVarastoonLisaaOikeinKunTilaaOn() {
        varasto.lisaaVarastoon(4);
        assertEquals(4,varasto.getSaldo(),0.00001);
    }

    @Test
    public void otaVarastostaEiToimiNegatiiviellaMaaralla() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-3);
        assertEquals(saldo,varasto.getSaldo(),0.0000001);
    }

    @Test
    public void otaVarastostaOttaaVainSenMitaJaljellaJosYritetaanOttaaEnemman() {
        varasto.lisaaVarastoon(3);
        
        assertEquals(6,varasto.otaVarastosta(5),0.0001);
    }

    @Test
    public void toStringToimii() {
        String str = "saldo = 0.0, vielä tilaa 10.0";

        assertEquals(str,varasto.toString());
    }

}