package terakoyalabo.authjwt.test

import terakoyalabo.authjwt.annotation.TerakoyaAuthDsl

@TerakoyaAuthDsl
fun createNode(settings: () -> TerakoyaSettings): KtorServerNode =
    KtorServerNode(settings = settings.invoke())
