package ximulib;

public enum ErrorCodes {
        NoError,
        FactoryResetFailed,
        LowBattery,
        USBreceiveBufferOverrun,
        USBtransmitBufferOverrun,
        BluetoothReceiveBufferOverrun,
        BluetoothTransmitBufferOverrun,
        SDcardWriteBufferOverrun,
        TooFewBytesInPacket,
        TooManyBytesInPacket,
        InvalidChecksum,
        UnknownHeader,
        InvalidNumBytesForPacketHeader,
        InvalidRegisterAddress,
        RegisterReadOnly,
        InvalidRegisterValue,
        InvalidCommand,
        GyroscopeAxisNotAt200dps,
        GyroscopeNotStationary,
        AcceleroemterAxisNotAt1g,
        MagnetometerSaturation,
        IncorrectAuxillaryPortMode,
        UARTreceiveBufferOverrun,
        UARTtransmitBufferOverrun
    
}
