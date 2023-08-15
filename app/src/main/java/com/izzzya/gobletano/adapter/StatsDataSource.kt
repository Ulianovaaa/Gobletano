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
                125000
            ),
            Record(
                "jopa666",
                135600
            ),
            Record(
                "nugget",
                71000
            ),
            Record(
                "hello kitty",
                58000
            )
        )
    }
}