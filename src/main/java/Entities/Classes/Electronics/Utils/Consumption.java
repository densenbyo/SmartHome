package entities.classes.electronics.utils;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Consumption {
    private int electricityConsumption = 0;
    private int gasConsumption = 0;
    private int waterConsumption = 0;
    private final int electricityConsumptionPerRoundACTIVE;
    private final int electricityConsumptionPerRoundIDLE;
    private final int gasConsumptionPerRoundACTIVE;
    private final int gasConsumptionPerRoundIDLE;
    private final int waterConsumptionPerRoundACTIVE;
    private final int waterConsumptionPerRoundIDLE;

    /**
     * Creates representation of consumption of device in active and idle state.
     * @param electricityConsumptionPerRoundACTIVE electricity consumption per one round in active state
     * @param electricityConsumptionPerRoundIDLE electricity consumption per one round in idle state
     * @param waterConsumptionPerRoundACTIVE water consumption per one round in active state
     * @param waterConsumptionPerRoundIDLE water consumption per one round in idle state
     * @param gasConsumptionPerRoundACTIVE gas consumption per one round in active state
     * @param gasConsumptionPerRoundIDLE gas consumption per one round in idle state
     */
    public Consumption(int electricityConsumptionPerRoundACTIVE, int electricityConsumptionPerRoundIDLE, int waterConsumptionPerRoundACTIVE, int waterConsumptionPerRoundIDLE, int gasConsumptionPerRoundACTIVE, int gasConsumptionPerRoundIDLE) {
        this.electricityConsumptionPerRoundACTIVE = electricityConsumptionPerRoundACTIVE;
        this.electricityConsumptionPerRoundIDLE = electricityConsumptionPerRoundIDLE;
        this.waterConsumptionPerRoundACTIVE = waterConsumptionPerRoundACTIVE;
        this.waterConsumptionPerRoundIDLE = waterConsumptionPerRoundIDLE;
        this.gasConsumptionPerRoundACTIVE = gasConsumptionPerRoundACTIVE;
        this.gasConsumptionPerRoundIDLE = gasConsumptionPerRoundIDLE;
    }

    /**
     * Updates device's consumption based on it's activity - whether it is idle or active.
     * @param state current state od device
     */
    public void update(State state) {
        switch (state){
            case ON:
                electricityConsumption += electricityConsumptionPerRoundACTIVE;
                gasConsumption += gasConsumptionPerRoundACTIVE;
                waterConsumption += waterConsumptionPerRoundACTIVE;
            case IDLE:
                electricityConsumption += electricityConsumptionPerRoundIDLE;
                gasConsumption += gasConsumptionPerRoundIDLE;
                waterConsumption += waterConsumptionPerRoundIDLE;
        }
    }

    /**
     * Returns integer value of electricity consumption.
     * @return sum of electricity consumption
     */
    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    /**
     * Returns integer value of gas consumption.
     * @return sum of gas consumption
     */
    public int getGasConsumption() {
        return gasConsumption;
    }

    /**
     * Returns integer value of water consumption.
     * @return sum of water consumption
     */
    public int getWaterConsumption() {
        return waterConsumption;
    }
}