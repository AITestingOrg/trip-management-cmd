package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        description("When a PUT request to api/trip/start/{id} should return 200")
        request {
            method 'PUT'
            url '/api/v1/trip/start/123e4567-e89b-12d3-a456-426655440000'
        }
        response {
            status 200
        }
    },
    Contract.make {
        description("When a PUT request for api/trip/start/{id} with invalid id should return 400")
        request {
            method 'PUT'
            url '/api/v1/trip/start/badIdString'
        }
        response {
            status 400
        }
    }
]