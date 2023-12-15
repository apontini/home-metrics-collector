package me.apontini.hmc.user

import com.welie.blessed.BluetoothCentralManager
import me.apontini.hmc.collector.application.CollectorService
import me.apontini.hmc.collector.domain.CollectionDelegate
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

val appModule = module {
    single { CollectorService() } withOptions { createdAtStart() }
    single { BluetoothCentralManager(CollectionDelegate()) } withOptions { createdAtStart() }
    single { CollectionDelegate() }

}
