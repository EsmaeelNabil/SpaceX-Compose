package com.thermondo.common

class ThermondoException(error: String? = "Something went wrong!") : Throwable(message = error)