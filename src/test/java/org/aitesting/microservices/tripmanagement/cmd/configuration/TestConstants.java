package org.aitesting.microservices.tripmanagement.cmd.configuration;

import java.util.Date;
import java.util.UUID;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;


public class TestConstants {
    public static final UUID TRIP_ID1 = UUID.fromString("f849769e-2534-84a6-d475-5c2d701343ab");
    public static final UUID TRIP_ID2 = UUID.fromString("5b842415-9447-4b9b-85c6-2e1075214cc4");
    public static final UUID TRIP_ID3 = UUID.fromString("7a7d1e99-4823-4aa5-9d3b-2307e88cee0d");
    public static final UUID TRIP_ID4 = UUID.fromString("7a7d1e99-4823-4aa5-9d3b-2307e88cee08");
    public static final UUID USER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
    public static final String FROM = "from some place over there";
    public static final String TO = "to this other place";
    public static TripInvoice INVOICE = new TripInvoice(FROM, TO, 0, 0, 0, new Date());
}
