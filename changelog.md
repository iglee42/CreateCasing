------------------------------------------------------
CreateEncased 1.10.0 - The In-World Update
------------------------------------------------------

#### Gameplay Changes

- Add Industrial Iron, Weathered Iron, Shadow Steel, Refined Radiance and Chorium Sheets for crafting
- Encased Blocks are no longer changeable in-world with the casing or the ingot.
- Add the Recaser, a new item that allows you to change the casing/material of encased blocks in-world. It uses the plate of the material you want to change to, and can be used on any encased block.
- Recipes for encased blocks have been changed to only use the plates. Encased blocks no longer uses the recipe from the original Create block, but instead uses the plate of the material you want to use.
- Encased blocks can now be crafted by using a deployer with the associated plate.
- Add Zinc stuff + New Zinc casing texture
- Zinc casing now uses deepslate to be crafted
- Shadow Steel and Refined Radiance stuff is now hidden, if shadow steel and refined radiance are not in the Create's creative tab

#### Translations
- Update zh_cn translation #206

#### Bug Fixes

- Fix UV locking on custom cogwheels and large cogwheels #208
- Custom Tanks can no longer connect together

#### API Changes

- Removed KubeJS Compat
------------------------------------------------------
CreateEncased 1.9.0-ht3
------------------------------------------------------

#### Translation Changes
- Update zh_cn translation #204

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
