package com.ihg.middleware.parser

class DateParser {
    def static te = { str -> Date.parse("yyyy-MM-dd'T'HH:mm:ss", str) }
}
