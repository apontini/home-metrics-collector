package me.apontini.hmc.user

import me.apontini.hmc.user.application.UserService
import me.apontini.hmc.user.web.CreateUser
import me.apontini.hmc.user.web.GetUser
import me.apontini.hmc.user.infrastructure.UserMongoRepository
import me.apontini.hmc.user.infrastructure.UserRepository
import me.apontini.hmc.user.web.DeleteUser
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val userModule = module {
    single {
        KMongo.createClient("mongodb://" + System.getenv("DB_USERNAME") + ":" + System.getenv("DB_PASSWORD") + "@mongodb:27017")
    }
    single { UserMongoRepository() as UserRepository }
    single { UserService() }

    single { GetUser() }
    single { CreateUser() }
    single { DeleteUser() }
}
