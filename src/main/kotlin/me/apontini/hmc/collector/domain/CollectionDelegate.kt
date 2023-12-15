package me.apontini.hmc.collector.domain

import com.welie.blessed.BluetoothCentralManagerCallback
import com.welie.blessed.BluetoothCommandStatus
import com.welie.blessed.BluetoothPeripheral
import com.welie.blessed.ScanResult

class CollectionDelegate: BluetoothCentralManagerCallback() {
    override fun onConnectedPeripheral(peripheral: BluetoothPeripheral) {
        println("Connected ${peripheral.name}")
    }

    override fun onConnectionFailed(
        peripheral: BluetoothPeripheral,
        status: BluetoothCommandStatus
    ) {
        println("Failed to connect to ${peripheral.name}, ${status.value}")
    }

    override fun onDisconnectedPeripheral(
        peripheral: BluetoothPeripheral,
        status: BluetoothCommandStatus
    ) {
        println("Disconnected from ${peripheral.name}, ${status.value}")
    }

    override fun onDiscoveredPeripheral(peripheral: BluetoothPeripheral, scanResult: ScanResult) {
        println("Discovered ${peripheral.name}")
    }
}