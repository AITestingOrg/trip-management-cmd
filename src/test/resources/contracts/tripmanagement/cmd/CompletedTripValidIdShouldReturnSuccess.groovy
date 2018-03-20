package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a PUT request for api/trip/completed/{id} should return 200")
    request {
        method 'PUT'
        url '/api/v1/trip/completed/123e4567-e89b-12d3-a456-426655440000'
    }
    response {
        status 200
    }
}