package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a POST request to api/trip missing ID hould return 400")
    request {
        method 'POST'
        url '/api/trip'
        headers{
            contentType(applicationJson())
        }
        body(
                "originAddress":"Smoke on the Water, 1630 Bell Tower Ln, Weston, FL 33326",
                "destinationAddress":"Parking Lot, Hialeah, FL 33014"
        )
    }
    response {
        status 400
    }
}
