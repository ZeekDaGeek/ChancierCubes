package tv.zeekdageek.chanciercubes.rewards.backported.giantRewards;

import chanceCubes.rewards.rewardparts.OffsetBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.rewards.BaseChancierBackport;
import tv.zeekdageek.chanciercubes.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeaconArenaReward extends BaseChancierBackport
{
    private static final String rewardName = "beacon_arena";

    // @formatter:off
    private final Block[] whitelist = { Blocks.obsidian, Blocks.dirt,
        Blocks.stone, Blocks.melon_block, Blocks.bookshelf,
        Blocks.clay, Blocks.wool, Blocks.brick_block,
        Blocks.web, Blocks.glowstone, Blocks.netherrack };
    // @formatter:on

    private final int randomWool = new Random().nextInt(16);

    public BeaconArenaReward() {
        super();
    }

    @Override
    public String getReplacedName() {
        return null;
    }

    @Override
    public boolean isGiantCube() {
        return true;
    }

    @Override
    public String getConfigName() {
        return rewardName;
    }

    @Override
    public String getDesc() {
        return "Summons a large circular arena with 4 beacons in the diagonals, with a glass wall around the edges. Backport from ChanceCubes 1.18.x";
    }

    @Override
    public String getDefaultState() {
        return EnumBackportValues.ENABLED.toString();
    }

    @Override
    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {
        List<OffsetBlock> blocks = new ArrayList<>();
        spawnWall(blocks);
        spawnSmallBeacon(blocks, new BlockPos(17, 0, 17), Blocks.gold_block);
        spawnSmallBeacon(blocks, new BlockPos(-17, 0, 17), Blocks.diamond_block);
        spawnSmallBeacon(blocks, new BlockPos(-17, 0, -17), Blocks.emerald_block);
        spawnSmallBeacon(blocks, new BlockPos(17, 0, -17), Blocks.iron_block);
        spawnBigBeacon(blocks);
        editFloor(blocks);

        for(OffsetBlock b : blocks)
            b.spawnInWorld(world, x, y - 1, z);
    }

    public void spawnSmallBeacon(List<OffsetBlock> blocks, BlockPos at, Block b) {
        int delay = 0;
        for(int x = -1; x < 2; x++)
        {
            for(int z = -1; z < 2; z++)
            {
                blocks.add(new OffsetBlock(at.getX() + x, at.getY(), at.getZ() + z, b, false, delay));
                delay++;
            }
        }
        blocks.add(new OffsetBlock(at.getX(), at.getY() + 1, at.getZ(), Blocks.beacon, false, delay).setCausesBlockUpdate(true));
    }

    public void spawnBigBeacon(List<OffsetBlock> blocks) {
        int delay = 0;
        for(int y = 0; y < 2; y++)
        {
            for(int x = -2; x < 3; x++)
            {
                for(int z = -2; z < 3; z++)
                {
                    if(y != 1 || (x > -2 && x < 2 && z > -2 && z < 2))
                    {
                        blocks.add(new OffsetBlock(x, y, z, Blocks.iron_block, false, delay));
                        delay++;
                    }
                }
            }
        }
        blocks.add(new OffsetBlock(0, 2, 0, Blocks.beacon, false, delay).setCausesBlockUpdate(true));
    }

    public void spawnWall(List<OffsetBlock> blocks) {
        List<BlockPos> usedPositions = new ArrayList<>();
        BlockPos temp;
        for(int degree = 0; degree < 360; degree++)
        {
            double arcVal = Math.toRadians(degree);
            int x = (int) (28d * Math.cos(arcVal));
            int z = (int) (28d * Math.sin(arcVal));
            temp = new BlockPos(x, 0, z);
            if(!usedPositions.contains(temp))
            {
                usedPositions.add(temp);
            }
        }

        int delay = 0;
        for(BlockPos pos : usedPositions)
        {
            blocks.add(new OffsetBlock(pos.getX(), pos.getY(), pos.getZ(), Blocks.glass, false, delay));
            blocks.add(new OffsetBlock(pos.getX(), pos.getY() + 1, pos.getZ(), Blocks.glass, false, delay + 1));
            blocks.add(new OffsetBlock(pos.getX(), pos.getY() + 2, pos.getZ(), Blocks.glass, false, delay + 2));
            delay++;
        }
    }

    public void editFloor(List<OffsetBlock> blocks) {
        int delay = 0;
        List<BlockPos> usedPositions = new ArrayList<>();
        BlockPos temp;
        for(int radius = 0; radius < 28; radius++)
        {
            for(int degree = 0; degree < 360; degree++)
            {
                double arcVal = Math.toRadians(degree);
                int x = (int) (radius * Math.cos(arcVal));
                int z = (int) (radius * Math.sin(arcVal));
                temp = new BlockPos(x, 0, z);
                if(!usedPositions.contains(temp))
                {
                    usedPositions.add(temp);
                }
            }
        }
        for(BlockPos pos : usedPositions)
        {
            int rnd = new Random().nextInt(whitelist.length);
            Block select = whitelist[rnd];
            OffsetBlock placedBlock = new OffsetBlock(pos.getX(), -1, pos.getZ(), select, false, (delay / 8));
            if (select == Blocks.wool)
                placedBlock.setData((byte) randomWool);
            blocks.add(placedBlock);
            delay++;
        }
    }

    @Override
    public int getChanceValue() {
        return 0;
    }

    @Override
    public String getName() {
        return Tags.MODID + ":" + rewardName;
    }
}
