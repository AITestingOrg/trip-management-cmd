package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a POST request to api/tri missing origin should return 400")
    request {
        method 'POST'
        url '/api/v1/trip'
        headers{
            contentType(applicationJson())
        }
        body(
                "destinationAddress":"Parking Lot, Hialeah, FL 33014",
                "userId":"123e4567-e89b-12d3-a456-426655440000"
        )
    }
    response {
        status 400
    }
}
