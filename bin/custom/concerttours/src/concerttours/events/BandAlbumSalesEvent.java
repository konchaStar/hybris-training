package concerttours.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class BandAlbumSalesEvent extends AbstractEvent {
    private String bandName;
    private Long albumSales;

    public BandAlbumSalesEvent(String bandName, Long albumSales) {
        this.bandName = bandName;
        this.albumSales = albumSales;
    }

    public String getBandName() {
        return bandName;
    }

    public Long getAlbumSales() {
        return albumSales;
    }
}
