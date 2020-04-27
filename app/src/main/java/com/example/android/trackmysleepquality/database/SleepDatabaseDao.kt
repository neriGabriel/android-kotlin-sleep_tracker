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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
    DEFINO MINHA INTERFACE => ELA É QUEM VAI CONTROLAR MINHAS OPERACOES DE BD
*/
@Dao
interface SleepDatabaseDao {

    /*
        @Insert => tag pré-definida para executar um comando de insert, parametro obrigatório é uma entidade
        da classe em questão
    */
    @Insert
    fun insert(night: SleepNight)

    /*
        @Update => tag pré-definida para executar um comando de update, parametro obrigatório é uma entidade
        da classe em questão
    */
    @Update
    fun update(night: SleepNight)

    /*
        @Query => tag utilziada para executar uma determinada query, parametro obrigatório é a query
     */
    @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    /*
        @Query => tag utilziada para executar uma determinada query, parametro obrigatório é a query
     */
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    /*
        @Query => tag utilziada para executar uma determinada query, parametro obrigatório é a query
     */
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>


    /*
        @Query => tag utilziada para executar uma determinada query, parametro obrigatório é a query
    */
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?
}