package br.com.module.situationworld.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.module.situationworld.room.entity.World.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class World(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int,
    @ColumnInfo(name = COLUMN_CASES) var cases: Long,
    @ColumnInfo(name = COLUMN_TODAY_CASES) var todayCases: Long,
    @ColumnInfo(name = COLUMN_TODAY_DEATHS) var todayDeaths: Long,
    @ColumnInfo(name = COLUMN_RECOVERED) var recovered: Long,
    @ColumnInfo(name = COLUMN_ACTIVE) var active: Long,
    @ColumnInfo(name = COLUMN_CRITICAL) var critical: Long,
    @ColumnInfo(name = COLUMN_CASES_PER_ONE_MILLION) var casesPerOneMillion: Long,
    @ColumnInfo(name = COLUMN_DEATHS_PER_ONE_MILLION) var deathsPerMillion: Float,
    @ColumnInfo(name = COLUMN_TESTS) var tests: Long,
    @ColumnInfo(name = COLUMN_TESTS_PER_ONE_MILLION) var testsPerOneMillion: Float,
    @ColumnInfo(name = COLUMN_AFFECTED_COUNTRIES) var affectedCountries: Long,
    @ColumnInfo(name = COLUMN_DEATHS) var deaths: Long){

    companion object{
        const val TABLE_NAME = "WORLD"
        const val COLUMN_ID = "ID"
        const val COLUMN_CASES = "CASES"
        const val COLUMN_TODAY_CASES = "TODAY_CASES"
        const val COLUMN_TODAY_DEATHS = "TODAY_DEATHS"
        const val COLUMN_RECOVERED = "RECOVERED"
        const val COLUMN_ACTIVE = "ACTIVE"
        const val COLUMN_CRITICAL = "CRITICAL"
        const val COLUMN_CASES_PER_ONE_MILLION = "CASES_PER_ONE_MILLION"
        const val COLUMN_DEATHS_PER_ONE_MILLION = "DEATHS_PER_ONE_MILLION"
        const val COLUMN_TESTS = "TESTS"
        const val COLUMN_TESTS_PER_ONE_MILLION = "TESTS_PER_ONE_MILLIONK"
        const val COLUMN_AFFECTED_COUNTRIES = "AFFECTED_COUNTRIES"
        const val COLUMN_DEATHS = "DEATHS"
    }

}