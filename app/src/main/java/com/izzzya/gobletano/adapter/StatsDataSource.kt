package com.izzzya.gobletano.adapter

import java.sql.Time


data class Record(
    val name: String,
    val time: Long //milliseconds
)
class StatsDataSource {
    companion object{
        val statsList: MutableList<Record> = mutableListOf(
            Record(
                "isa",
                6000
            ),
            Record(
                "jopa666",
                5600
            ),
            Record(
                "nugget",
                7000
            ),
            Record(
                "hello kitty",
                8000
            )
        )
    }
}