package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a PUT request for api/trip/start/{id} with invalid id should return 400")
    request {
        method 'PUT'
        url '/api/trip/start/badIdString'
    }
    response {
        status 400
    }
}
