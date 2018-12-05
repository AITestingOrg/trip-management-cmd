package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        description("When a PUT request for api/trip/completed/{id} should return 200")
        request {
            method 'PUT'
            url '/api/v1/trip/completed/f849769e-2534-84a6-d475-5c2d701343ab'
        }
        response {
            status 200
        }
    },
    Contract.make {
        description("When a PUT request for api/trip/completed/{id} with invalid id should return 400")
        request {
            method 'PUT'
            url '/api/v1/trip/completed/badIdString'
        }
        response {
            status 400
        }
    }
]