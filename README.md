# SmartHome project

# Team
Atazhanov Mukan (atazhmuk)

# About project
Main aim of the project is to implement simulation of smart home, by using patter designs, where can be simulated whole "life" of home.

# Functional requirements
- F1. Used entities
  - Creatures
  - House
  - Sensors
  - Devices
  - Tools
- F2. API is not used. But devices have their own states (electronics/utils/state)
- F3. Consumptions (electronics/utils/consumption)
- F4. API is not used. But every data from devices are added to logger.
- F5. Actions of creatures are generated randomly by methods nextActivity() and newLap()
- F6. Due to newLap() method, alerts and events are generated randomly
- F7. Not implemented as required, but all infos generated in that way
  - ```
    *something* is *any change*. *someone* is noticed
    ```
- F8. Reports can be generated. Location is Reports folder
- F9. Implemented, if device is broken. Noticed user will firstly look for manual and only after repairs
- F10. User looks for any available to use device or tool and randomly choose (50%) what to do. If there is nothing to do, will stay afk

# Nonfunctional requirements
- NF1. Reports are generated in .txt format

# Design patterns
- State machine
- Singleton
- Observer
- Factory

# Required outputs
- UML can be found in uml folder
- JavaDoc can be found in javaDoc folder
- Two different configs 
  - Config1
  - Config2