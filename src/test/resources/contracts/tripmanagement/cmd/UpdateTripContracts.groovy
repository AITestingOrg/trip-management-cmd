package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {

            description("When a PUT request to api/trip/update/{id} should return 200")
            request {
                method 'PUT'
                url '/api/v1/trip/update/f849769e-2534-84a6-d475-5c2d701343ab'
                headers{
                    contentType(applicationJson())
                }
                body(
                        "originAddress":"Miami Beach, FL",
                        "userId":"4eaf29bc-3909-49d4-a104-3d17f68ba672"
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
                url '/api/v1/trip/update/f849769e-2534-84a6-d475-5c2d701343ab'
                headers{
                    contentType(applicationJson())
                }
            }
            response {
                status 400
            }
        }
]