package tripmanagement.cmd

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
  request {
    method 'POST'
    url '/api/v1/trip'
    body("""
    {
      "originAddress":"Weston, FL",
      "destinationAddress":"Miami, FL",
      "userId":"4eaf29bc-3909-49d4-a104-3d17f68ba672"
    }
    """)
    headers {
      header('Authorization', 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzM2MTkwNDMsInVzZXJfbmFtZSI6InBhc3NlbmdlciIsImF1dGhvcml0aWVzIjpbIlJPTEVfUEFTU0VOR0VSIiwiUk9MRV9VU0VSIl0sImp0aSI6ImYwNjk2MTVjLTg0YTQtNGMzZS1iMmI0LTMyMWE0MzFhMGRkNCIsImNsaWVudF9pZCI6ImZyb250LWVuZCIsInNjb3BlIjpbIndlYmNsaWVudCJdfQ.0XlWE9xlBcMc6e82VwfK4-WqrQPY2k6-tgjTnWfl8iY')
      header('Content-Type', 'application/json')
    }
  }
  response {
    status 201
   }
  }
]
