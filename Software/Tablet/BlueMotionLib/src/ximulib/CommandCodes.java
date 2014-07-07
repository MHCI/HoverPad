package ximulib;

public enum CommandCodes
{
    NullCommand,
    FactoryReset,
    Reset,
    Sleep,
    ResetSleepTimer,
    SampleGyroscopeAxisAt200dps,
    CalculateGyroscopeSensitivity,
    SampleGyroscopeBiasTemp1,
    SampleGyroscopeBiasTemp2,
    CalculateGyroscopeBiasParameters,
    SampleAccelerometerAxisAt1g,
    CalculateAccelerometerBiasAndSensitivity,
    MeasureMagnetometerBiasAndSensitivity,
    AlgorithmInitialise,
    AlgorithmTare,
    AlgorithmClearTare,
    AlgorithmInitialiseThenTare
}