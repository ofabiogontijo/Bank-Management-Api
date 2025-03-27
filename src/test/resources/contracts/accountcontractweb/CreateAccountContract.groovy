package contracts.accountcontractweb

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(consumer('/conta'),
                producer('/conta'))
        headers {
            contentType(applicationJson())
        }
        body("""
                {
                    "numero_conta" : 18,
                    "saldo" : 100
                }
        """)
    }

    response {
        status 201
        headers {
            contentType(applicationJson())
        }
        body("""
               {
                  "numero_conta": "${value(test(anyNumber()), stub(110))}",
                  "saldo": "${value(test(anyNumber()), stub(300))}",
               }
        """)
    }

}
