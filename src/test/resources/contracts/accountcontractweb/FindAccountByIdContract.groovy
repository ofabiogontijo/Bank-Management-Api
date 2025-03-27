package contracts.accountcontractweb

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(consumer('/conta?id=23'),
                producer('/conta?id=23'))
        headers {
            contentType(applicationJson())
        }

    }

    response {
        status 200
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
