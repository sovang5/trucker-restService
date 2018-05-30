# Trucker-restService
This is a rest Service to track Car location and Status of the car

Get the information from  Iot Sensor which is placed in car

Implemented Alerts:

Create alerts with given priority when following rules are triggered

Rule: engineRpm > readlineRpm, Priority: HIGH

Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM

Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW

Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW

There are Total 4 rest Points:
1. Fetch details of all the vehicles like VIN, make, model, year etc.
2. Fetch HIGH alerts within last 2 hours for all the vehicles and ability to sort list of vehicles based on it.
3. Ability to list vehicle's geolocation within last 30minutes on a map.
4. Ability to list a vehicle's all historical alerts.
