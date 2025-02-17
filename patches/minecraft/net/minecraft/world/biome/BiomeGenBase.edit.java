
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  5 : 6  @  5

+ 

> CHANGE  3 : 4  @  3 : 4

~ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

> DELETE  29  @  29 : 48

> CHANGE  9 : 11  @  9 : 11

~ import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
~ import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

> CHANGE  19 : 63  @  19 : 110

~ 	public static BiomeGenBase ocean;
~ 	public static BiomeGenBase plains;
~ 	public static BiomeGenBase desert;
~ 	public static BiomeGenBase extremeHills;
~ 	public static BiomeGenBase forest;
~ 	public static BiomeGenBase taiga;
~ 	public static BiomeGenBase swampland;
~ 	public static BiomeGenBase river;
~ 	public static BiomeGenBase hell;
~ 	public static BiomeGenBase sky;
~ 	public static BiomeGenBase frozenOcean;
~ 	public static BiomeGenBase frozenRiver;
~ 	public static BiomeGenBase icePlains;
~ 	public static BiomeGenBase iceMountains;
~ 	public static BiomeGenBase mushroomIsland;
~ 	public static BiomeGenBase mushroomIslandShore;
~ 	public static BiomeGenBase beach;
~ 	public static BiomeGenBase desertHills;
~ 	public static BiomeGenBase forestHills;
~ 	public static BiomeGenBase taigaHills;
~ 	public static BiomeGenBase extremeHillsEdge;
~ 	public static BiomeGenBase jungle;
~ 	public static BiomeGenBase jungleHills;
~ 	public static BiomeGenBase jungleEdge;
~ 	public static BiomeGenBase deepOcean;
~ 	public static BiomeGenBase stoneBeach;
~ 	public static BiomeGenBase coldBeach;
~ 	public static BiomeGenBase birchForest;
~ 	public static BiomeGenBase birchForestHills;
~ 	public static BiomeGenBase roofedForest;
~ 	public static BiomeGenBase coldTaiga;
~ 	public static BiomeGenBase coldTaigaHills;
~ 	public static BiomeGenBase megaTaiga;
~ 	public static BiomeGenBase megaTaigaHills;
~ 	public static BiomeGenBase extremeHillsPlus;
~ 	public static BiomeGenBase savanna;
~ 	public static BiomeGenBase savannaPlateau;
~ 	public static BiomeGenBase mesa;
~ 	public static BiomeGenBase mesaPlateau_F;
~ 	public static BiomeGenBase mesaPlateau;
~ 	public static BiomeGenBase field_180279_ad;
~ 	protected static NoiseGeneratorPerlin temperatureNoise;
~ 	protected static NoiseGeneratorPerlin GRASS_COLOR_NOISE;
~ 	protected static WorldGenDoublePlant DOUBLE_PLANT_GENERATOR;

> CHANGE  81 : 82  @  81 : 82

~ 	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom rand) {

> CHANGE  3 : 4  @  3 : 4

~ 	public WorldGenerator getRandomWorldGenForGrass(EaglercraftRandom rand) {

> CHANGE  3 : 4  @  3 : 4

~ 	public BlockFlower.EnumFlowerType pickRandomFlower(EaglercraftRandom rand, BlockPos pos) {

> CHANGE  94 : 95  @  94 : 95

~ 	public void decorate(World worldIn, EaglercraftRandom rand, BlockPos pos) {

> CHANGE  19 : 21  @  19 : 21

~ 	public void genTerrainBlocks(World worldIn, EaglercraftRandom rand, ChunkPrimer chunkPrimerIn, int parInt1,
~ 			int parInt2, double parDouble1) {

> CHANGE  3 : 5  @  3 : 5

~ 	public final void generateBiomeTerrain(World worldIn, EaglercraftRandom rand, ChunkPrimer chunkPrimerIn,
~ 			int parInt1, int parInt2, double parDouble1) {

> CHANGE  101 : 177  @  101 : 102

~ 	public static void doBootstrap() {
~ 		ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setHeight(height_Oceans);
~ 		plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains");
~ 		desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain()
~ 				.setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowPlains);
~ 		extremeHills = (new BiomeGenHills(3, false)).setColor(6316128).setBiomeName("Extreme Hills")
~ 				.setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
~ 		forest = (new BiomeGenForest(4, 0)).setColor(353825).setBiomeName("Forest");
~ 		taiga = (new BiomeGenTaiga(5, 0)).setColor(747097).setBiomeName("Taiga").setFillerBlockMetadata(5159473)
~ 				.setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
~ 		swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").setFillerBlockMetadata(9154376)
~ 				.setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
~ 		river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setHeight(height_ShallowWaters);
~ 		hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain()
~ 				.setTemperatureRainfall(2.0F, 0.0F);
~ 		sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("The End").setDisableRain();
~ 		frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow()
~ 				.setHeight(height_Oceans).setTemperatureRainfall(0.0F, 0.5F);
~ 		frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow()
~ 				.setHeight(height_ShallowWaters).setTemperatureRainfall(0.0F, 0.5F);
~ 		icePlains = (new BiomeGenSnow(12, false)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow()
~ 				.setTemperatureRainfall(0.0F, 0.5F).setHeight(height_LowPlains);
~ 		iceMountains = (new BiomeGenSnow(13, false)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow()
~ 				.setHeight(height_LowHills).setTemperatureRainfall(0.0F, 0.5F);
~ 		mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland")
~ 				.setTemperatureRainfall(0.9F, 1.0F).setHeight(height_LowIslands);
~ 		mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore")
~ 				.setTemperatureRainfall(0.9F, 1.0F).setHeight(height_Shores);
~ 		beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F)
~ 				.setHeight(height_Shores);
~ 		desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain()
~ 				.setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
~ 		forestHills = (new BiomeGenForest(18, 0)).setColor(2250012).setBiomeName("ForestHills")
~ 				.setHeight(height_LowHills);
~ 		taigaHills = (new BiomeGenTaiga(19, 0)).setColor(1456435).setBiomeName("TaigaHills")
~ 				.setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
~ 		extremeHillsEdge = (new BiomeGenHills(20, true)).setColor(7501978).setBiomeName("Extreme Hills Edge")
~ 				.setHeight(height_MidHills.attenuate()).setTemperatureRainfall(0.2F, 0.3F);
~ 		jungle = (new BiomeGenJungle(21, false)).setColor(5470985).setBiomeName("Jungle")
~ 				.setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F);
~ 		jungleHills = (new BiomeGenJungle(22, false)).setColor(2900485).setBiomeName("JungleHills")
~ 				.setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
~ 		jungleEdge = (new BiomeGenJungle(23, true)).setColor(6458135).setBiomeName("JungleEdge")
~ 				.setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.8F);
~ 		deepOcean = (new BiomeGenOcean(24)).setColor(48).setBiomeName("Deep Ocean").setHeight(height_DeepOceans);
~ 		stoneBeach = (new BiomeGenStoneBeach(25)).setColor(10658436).setBiomeName("Stone Beach")
~ 				.setTemperatureRainfall(0.2F, 0.3F).setHeight(height_RockyWaters);
~ 		coldBeach = (new BiomeGenBeach(26)).setColor(16445632).setBiomeName("Cold Beach")
~ 				.setTemperatureRainfall(0.05F, 0.3F).setHeight(height_Shores).setEnableSnow();
~ 		birchForest = (new BiomeGenForest(27, 2)).setBiomeName("Birch Forest").setColor(3175492);
~ 		birchForestHills = (new BiomeGenForest(28, 2)).setBiomeName("Birch Forest Hills").setColor(2055986)
~ 				.setHeight(height_LowHills);
~ 		roofedForest = (new BiomeGenForest(29, 3)).setColor(4215066).setBiomeName("Roofed Forest");
~ 		coldTaiga = (new BiomeGenTaiga(30, 0)).setColor(3233098).setBiomeName("Cold Taiga")
~ 				.setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F)
~ 				.setHeight(height_MidPlains).func_150563_c(16777215);
~ 		coldTaigaHills = (new BiomeGenTaiga(31, 0)).setColor(2375478).setBiomeName("Cold Taiga Hills")
~ 				.setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F)
~ 				.setHeight(height_LowHills).func_150563_c(16777215);
~ 		megaTaiga = (new BiomeGenTaiga(32, 1)).setColor(5858897).setBiomeName("Mega Taiga")
~ 				.setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
~ 		megaTaigaHills = (new BiomeGenTaiga(33, 1)).setColor(4542270).setBiomeName("Mega Taiga Hills")
~ 				.setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
~ 		extremeHillsPlus = (new BiomeGenHills(34, true)).setColor(5271632).setBiomeName("Extreme Hills+")
~ 				.setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
~ 		savanna = (new BiomeGenSavanna(35)).setColor(12431967).setBiomeName("Savanna")
~ 				.setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setHeight(height_LowPlains);
~ 		savannaPlateau = (new BiomeGenSavanna(36)).setColor(10984804).setBiomeName("Savanna Plateau")
~ 				.setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setHeight(height_HighPlateaus);
~ 		mesa = (new BiomeGenMesa(37, false, false)).setColor(14238997).setBiomeName("Mesa");
~ 		mesaPlateau_F = (new BiomeGenMesa(38, false, true)).setColor(11573093).setBiomeName("Mesa Plateau F")
~ 				.setHeight(height_HighPlateaus);
~ 		mesaPlateau = (new BiomeGenMesa(39, false, false)).setColor(13274213).setBiomeName("Mesa Plateau")
~ 				.setHeight(height_HighPlateaus);
~ 		field_180279_ad = ocean;
~ 

> INSERT  22 : 24  @  22

+ 		explorationBiomesList.clear();
+ 

> CHANGE  19 : 21  @  19 : 21

~ 		temperatureNoise = new NoiseGeneratorPerlin(new EaglercraftRandom(1234L), 1);
~ 		GRASS_COLOR_NOISE = new NoiseGeneratorPerlin(new EaglercraftRandom(2345L), 1);

> EOF
