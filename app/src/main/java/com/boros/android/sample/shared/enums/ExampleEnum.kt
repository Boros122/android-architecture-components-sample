package com.boros.android.sample.shared.enums

enum class ExampleEnum(val value: Int) {

    EXAMPLE_FIRST(0),
    EXAMPLE_SECOND(1);

    companion object {

        private val map = values().associateBy(ExampleEnum::value)
        fun fromInt(type: Int) = map[type]

    }

}