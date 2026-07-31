------------------------------------------------------
CreateEncased 1.9.0-ht3
------------------------------------------------------

#### Bug Fixes

- Fix invalid chorium recipe #202 #203
- Fix mixin crash with Create Colored #201
- Fix shafts can be placed when clicking on the side of a configurable gearbox

------------------------------------------------------
CreateEncased 1.9.0-ht2
------------------------------------------------------

#### Bug Fixes

- Fix hose pulley not working #198
- Fix unavailable recipes for some people #197

------------------------------------------------------
CreateEncased 1.9.0-ht1
------------------------------------------------------

#### Gameplay Changes

- Encased Slicers ponder now uses the Slice and Dice translations making them available in more languages

#### Bug Fixes

- Fix crashes with Create Colorful Pipes #191 #192
- Fix crash on dedicated servers #193

------------------------------------------------------
CreateEncased 1.9.0 - The Fluid Update
------------------------------------------------------

#### Gameplay Changes

- Add Andesite & Zinc Sheets for crafting
- Add a Zinc casing (For now can only encase Shaft, Cogwheels and Fluid Pipes)
- Add custom (smart) fluid pipes, pumps, tanks, valves (handles), steam engines/whistles, portable fluid interfaces, item drains, spouts and hose pulleys (Declined in andesite, brass and zinc)
- Encased blocks ponder now uses the Create translations making them available in more languages
- Encased blocks now use the stress values from the default Create block (can be changed in configs) #126

#### Bug Fixes

- Fix encased presses recipes not the same as the normal press #189
- Fix auto clutches not working properly with the speed mode
- Fix incompatible crafting recipes between encased storage and fluid interfaces
- Encased custom shafts are now requiring the right item when placed with schematics

#### API Changes

- `encasedWoodenShaft()`, `encasedWoodenCogwheel()` & `encasedWoodenLargeCogwheel()` methods have been renamed to `encasedCustomShaft()` ,`encasedCustomCogwheel()` & `encasedCustomLargeCogwheel()` for casing sets
- Add `encasedCustomPipe()` method to allow the casing set to be used to create encased custom fluid pipes (Warning this option is enabled when calling `.fluids()`)
- Add `FluidSet` class to allow the creation of custom fluid sets with the mod
- Add `getAllBlocks()` method to CasingSet and FluidSet to allow the retrieval of all blocks (generated or not) in the set
- Renamed `ModConfigs` class to `EncasedConfigs` to avoid conflicts with other mods
