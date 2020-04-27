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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
    DEFINO MINHA CLASSE DE CONEXAO
    PARAMETROS NECESSIARIOS:
    entites => contem todas as entidades relacionadas a essa tabela
    version => verão do banco
    exportSchema => se o banco poderá ser exportado
*/
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    /*
        INSTANCIO A CLASSE DE DAO
    */
    abstract val sleepDatabaseDao: SleepDatabaseDao

    /*
      CRIO UM CAMPANION OBJECT
      QUE TERÁ COMO OBJETIVO DEIXAR A GENTE CRIAR OU ATUALIZAR UM BANCO SEM INSTANCIAR A CLASSE
    */
    companion object {

        /*
            CRIAMOS ESSA VARIAVEL DE INSTANCE QUE TERÁ COMO OBEJTIVO MANTER A INSTANCIA DA CONEXAO SE EXISTIR
            @Volatile => GARANTE QUE O VALOR DA INSTANCIA ESTÁ SEMPRE ATUALIZA E NA MESMA TRHEAD DE EXECUÇÃO
        */
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        /*
            FUNÇÃO QUE RETORNA A INSTANCIA
        */
        fun getInstance(context: Context) : SleepDatabase {

            /*
                synchronized => defino que será sincrono e que o bd será inicializado uma vez
                e precisamos passar essa classe mesmo (this) para ter acesso ao contexto
            */
            synchronized( this) {
                /*
                    copiamos o valor da instancia atual para nosso atributo
                */
                var instance = INSTANCE

                /*
                    verificamos se nossa variavel de instancia está nula, se sim
                    precisamos startar uma nova instancia e adicionala ao atributo

                    senão retornamos a instancia
                */
                if(instance == null) {
                    /*
                        nosso contexto,
                        a classe que queremos executar
                        o nome do banco
                    */
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SleepDatabase::class.java,
                            "sleep_history_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()

                    INSTANCE = instance
                }

                return instance
            }

        }
    }

}