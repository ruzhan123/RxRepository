package com.ruzhan.rxrepository.source

import io.reactivex.functions.Consumer


class EmptyConsumer : Consumer<Any> {

    @Throws(Exception::class)
    override fun accept(o: Any) {

    }

    companion object {

        fun create(): EmptyConsumer {
            return EmptyConsumer()
        }
    }
}
