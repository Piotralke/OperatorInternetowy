package pl.psk.upc.infrastructure.db.init;

import lombok.Getter;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.infrastructure.enums.ProductType;

@Getter
public enum Offers {
    INTERNET_300Mb("Internet 300Mb/s", 50.0, "Internet światłowodowy", ProductType.INTERNET, true, OfferType.INTERNET, false),
    INTERNET_600Mb("Internet 600Mb/s", 60.0, "Internet światłowodowy", ProductType.INTERNET, true, OfferType.INTERNET, false),
    INTERNET_1Gb("Internet 1Gb/s", 80.0, "Internet światłowodowy", ProductType.INTERNET, true, OfferType.INTERNET, false),
    TV_50("Telewizja 50 kanałów", 20.0, "Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, false, OfferType.TV, false),
    TV_136("Telewizja 136 kanałów", 40.0, "Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, false, OfferType.TV, false),
    TV_179("Telewizja 179 kanałów", 50.0, "Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, false, OfferType.TV, false),
    INTERNET_300Mb_PLUS_TV_50("Internet 300Mb/s + Telewizja 50 kanałów", 85.0, "Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, true, OfferType.INTERNET_PLUS_TV, false),
    INTERNET_600Mb_PLUS_TV_136("Internet 600Mb/s + Telewizja 136 kanałów", 110.0, "Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, true, OfferType.INTERNET_PLUS_TV, false),
    INTERNET_1Gb_PLUS_TV_179("Internet 1Gb/s + Telewizja 179 kanałów", 140.0, "Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).", ProductType.INTERNET, true, OfferType.INTERNET_PLUS_TV, false);

    private String offerName;
    private double price;
    private String description;
    private ProductType productType;
    private boolean withDevice;
    private boolean isArchival;
    private OfferType offerType;

    Offers(String offerName, double price, String description, ProductType productType, boolean withDevice, OfferType offerType, boolean isArchival) {
        this.offerName = offerName;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.withDevice = withDevice;
        this.offerType = offerType;
        this.isArchival = isArchival;
    }
}
