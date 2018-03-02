package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a POST request to api/tripmanagement should return 200")
    request {
        method 'POST'
        url '/api/trip'
        headers{
            contentType(applicationJson())
        }
        body(
            "originAddress":"Smoke on the Water, 1630 Bell Tower Ln, Weston, FL 33326",
            "destinationAddress":"Parking Lot, Hialeah, FL 33014",
            "userId":"123e4567-e89b-12d3-a456-426655440000"
        )
    }
    response {
        status 200
    }
}
