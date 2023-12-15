package me.apontini.hmc.collector.application

import com.welie.blessed.BluetoothCentralManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CollectorService : KoinComponent {

    val bluetoothManager by inject<BluetoothCentralManager>()
    fun collect() {
        bluetoothManager.scanForPeripherals()
    }

}