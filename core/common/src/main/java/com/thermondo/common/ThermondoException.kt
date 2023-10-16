package com.thermondo.common

class ThermondoException(error: String? = null, throwable: Throwable) :
    Throwable(message = "Something went wrong!")