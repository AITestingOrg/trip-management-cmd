package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a PUT request for api/trip/cancel/{id} with invalid id should return 400")
    request {
        method 'PUT'
        url '/api/trip/cancel/badIdString'
    }
    response {
        status 400
    }
}
