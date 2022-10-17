# ChancierCubes
Backport and add Chance Cube rewards, originally created with compatibility of GT: New Horizons (1.7.10) in mind.

Created with [GTNewHorizons' ExampleMod](https://github.com/GTNewHorizons/ExampleMod1.7.10). All the automated actions and gradle scripts are theirs.

## Dependencies
 - ChanceCubes: https://github.com/TheTurkeyDev/ChanceCubes

## Skeleton
This mod should be usable as a skeleton in the event that you want to make your own custom ChanceCube rewards with more than what the JSON methods will allow.

## Backport progress
Rewards from Chance Cubes 1.18

#### Giant Chance Cubes
-[x] BeaconArenaReward
-[ ] BioDomeReward.java
-[x] BlockInfectionReward.java
-[ ] BlockThrowerReward.java
-[ ] ChunkFlipReward.java
-[ ] ChunkReverserReward.java
-[x] FireworkShowReward.java
-[ ] FloorIsLavaReward.java
-[x] FluidSphereReward.java
-[x] MixedFluidSphereReward.java
-[ ] OrePillarReward.java
-[ ] OreSphereReward.java
-[ ] PotionsReward.java
-[ ] RandomExplosionReward.java
-[ ] SphereSnakeReward.java
-[ ] TNTSlingReward.java

#### Normal Cubes
-[ ] @TODO: Make list of normal rewards.

## Other changes
- Any CommandRewardType that uses /set time will set time relative to the current day to prevent jumping back to day 0.
- Added `/chanciercubes testreward <reward>` to execute a reward.
