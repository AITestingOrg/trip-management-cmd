package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a PUT request for api/trip/completed/{id} with invalid id should return 400")
    request {
        method 'PUT'
        url '/api/trip/completed/badIdString'
    }
    response {
        status 400
    }
}
