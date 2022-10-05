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

public class BlockInfectionReward extends BaseChancierBackport {
    private static final String rewardName = "world_infection";
    public EnumBackportValues rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    public BlockInfectionReward() {
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
        return "Randomized blocks that spread from the source. Backport from Chance Cubes 1.18.x";
    }

    @Override
    public String getDefaultState() {
        return EnumBackportValues.ENABLED.toString();
    }

    @Override
    public int getChanceValue() {
        return 0;
    }

    @Override
    public String getName() {
        return Tags.MODID + ":" + rewardName;
    }

    // @formatter:off
    private final Block[] whitelist = { Blocks.obsidian, Blocks.dirt,
        Blocks.stone, Blocks.melon_block, Blocks.bookshelf,
        Blocks.clay, Blocks.wool, Blocks.brick_block,
        Blocks.web, Blocks.glowstone, Blocks.netherrack };
    // @formatter:on

    private final int randomWool = new Random().nextInt(16);

    private final BlockPos[] touchingPos = {new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 1, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1), new BlockPos(0, -1, 0)};

    /**
     * @param world        {@link World}
     * @param x            int
     * @param y            int
     * @param z            int
     * @param entityPlayer {@link EntityPlayer}
     */
    @Override
    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {
        int delay = 0;
        int delayShorten = 20;

        BlockPos lastPos = new BlockPos(x, y, z);
        List<BlockPos> possibleBlocks = new ArrayList<>();
        List<BlockPos> changedBlocks = new ArrayList<>();
        changedBlocks.add(new BlockPos(0, 0, 0));
        List<OffsetBlock> blocks = new ArrayList<>();
        addSurroundingBlocks(level, pos, new BlockPos(0, 0, 0), changedBlocks, possibleBlocks);

        for(int i = 0; i < 5000; i++)
        {
            BlockPos nextPos;
            if(possibleBlocks.size() > 0)
            {
                int index = RewardsUtil.rand.nextInt(possibleBlocks.size());
                nextPos = possibleBlocks.get(index);
                possibleBlocks.remove(index);
            }
            else
            {
                nextPos = lastPos.offset(touchingPos[RewardsUtil.rand.nextInt(touchingPos.length)]);
            }

            changedBlocks.add(nextPos);
            addSurroundingBlocks(level, pos, nextPos, changedBlocks, possibleBlocks);
            BlockState state = whitelist[RewardsUtil.rand.nextInt(whitelist.length)];
            blocks.add(new OffsetBlock(nextPos.getX(), nextPos.getY(), nextPos.getZ(), state, false, (delay / delayShorten)));
            delay++;
            lastPos = nextPos;
        }

        for(OffsetBlock b : blocks)
            b.spawnInWorld(level, pos.getX(), pos.getY(), pos.getZ());
    }

    private void addSurroundingBlocks(World world, BlockPos worldCord, BlockPos offsetCord, List<BlockPos> changedBlocks, List<BlockPos> possibleBlocks) {
        for(BlockPos pos : touchingPos) {
            BlockPos checkPos = offsetCord.offset(pos);
            if (!changedBlocks.contains(checkPos) && !possibleBlocks.contains(checkPos)) {
                BlockPos localPos = worldCord.offset(checkPos);
                if (!(world.getBlock(localPos.getX(), localPos.getY(), localPos.getZ()) == Blocks.air)) {
                    possibleBlocks.add(checkPos);
                }
            }
        }
    }
}
