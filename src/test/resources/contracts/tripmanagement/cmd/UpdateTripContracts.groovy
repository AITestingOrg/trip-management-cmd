package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {

            description("When a PUT request to api/trip/update/{id} should return 200")
            request {
                method 'PUT'
                url '/api/v1/trip/update/123e4567-e89b-12d3-a456-426655440000'
                headers{
                    contentType(applicationJson())
                }
                body(
                        "originAddress":"Miami Beach, FL",
                        "userId":"123e4567-e89b-12d3-a456-426655440000"
                )
            }
            response {
                status 200
            }
        },
        Contract.make {
            description("When a POST request to api/trip/update/{id} missing body should return 400")
            request {
                method 'PUT'
                url '/api/v1/trip/update/123e4567-e89b-12d3-a456-426655440000'
                headers{
                    contentType(applicationJson())
                }
            }
            response {
                status 400
            }
        }
]