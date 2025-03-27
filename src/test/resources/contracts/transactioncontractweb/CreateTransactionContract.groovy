package contracts.transactioncontractweb

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(consumer('/transacao'),
                producer('/transacao'))
        headers {
            contentType(applicationJson())
        }
        body("""
                {
                    "numero_conta" : 17,
                    "forma_pagamento" : "C",
                    "valor" : 10
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
