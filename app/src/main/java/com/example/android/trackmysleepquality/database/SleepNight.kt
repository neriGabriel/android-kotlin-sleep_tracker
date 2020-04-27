/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    DEFINO MINHA DATA CLASS => ELA É QUEM VAI SER MINHA 'ENTIDADE'
    @Entity() => define que será uma tabela/entidade, o parametro é opcional para o nome
 */
@Entity(tableName= "daily_sleep_quality_table")

data class SleepNight(
        /*
           @PrimaryKey => chave primaria da entidade/tabela, o parametro é opcional para ser um campo
           auto-incremente
        */
        @PrimaryKey(autoGenerate = true)
        var nightId: Long = 0L,

        /*
            @ColumnInfo => informações da coluna, o parametro é opcional com o nome da coluna
         */
        @ColumnInfo(name = "start_time_milli")
        val startTimeMilli: Long = System.currentTimeMillis(),

        /*
            @ColumnInfo => informações da coluna, o parametro é opcional com o nome da coluna
         */
        @ColumnInfo(name = "end_time_milli")
        var endTimeMilli: Long = startTimeMilli,

        /*
            @ColumnInfo => informações da coluna, o parametro é opcional com o nome da coluna
         */
        @ColumnInfo(name = "sleep_quality")
        var sleepQuality: Int = -1
)