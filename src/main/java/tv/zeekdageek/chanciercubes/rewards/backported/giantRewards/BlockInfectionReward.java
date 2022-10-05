package tv.zeekdageek.chanciercubes.rewards.backported.giantRewards;

import chanceCubes.rewards.rewardparts.OffsetBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.ChancierCubes;
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

        int randomWool = ChancierCubes.Rand.nextInt(16);

        BlockPos pos = new BlockPos(x, y, z);
        BlockPos lastPos = pos;
        List<BlockPos> possibleBlocks = new ArrayList<>();
        List<BlockPos> changedBlocks = new ArrayList<>();
        changedBlocks.add(new BlockPos(0, 0, 0));
        List<OffsetBlock> blocks = new ArrayList<>();
        addSurroundingBlocks(world, pos, new BlockPos(0, 0, 0), changedBlocks, possibleBlocks);

        Random rand = new Random();

        for(int i = 0; i < 5000; i++)
        {
            BlockPos nextPos;
            if(possibleBlocks.size() > 0)
            {
                int index = rand.nextInt(possibleBlocks.size());
                nextPos = possibleBlocks.get(index);
                possibleBlocks.remove(index);
            }
            else
            {
                nextPos = lastPos.add(touchingPos[rand.nextInt(touchingPos.length)]);
            }

            changedBlocks.add(nextPos);
            addSurroundingBlocks(world, pos, nextPos, changedBlocks, possibleBlocks);
            Block state = whitelist[rand.nextInt(whitelist.length)];
            OffsetBlock newBlock = new OffsetBlock(nextPos.getX(), nextPos.getY(), nextPos.getZ(), state, false, (delay / delayShorten));
            if (state == Blocks.wool)
                newBlock.setData((byte) randomWool);
            blocks.add(newBlock);
            delay++;
            lastPos = nextPos;
        }

        for(OffsetBlock b : blocks)
            b.spawnInWorld(world, pos.getX(), pos.getY(), pos.getZ());
    }

    private void addSurroundingBlocks(World world, BlockPos worldCord, BlockPos offsetCord, List<BlockPos> changedBlocks, List<BlockPos> possibleBlocks) {
        for(BlockPos pos : touchingPos) {
            BlockPos checkPos = offsetCord.add(pos);
            if (!changedBlocks.contains(checkPos) && !possibleBlocks.contains(checkPos)) {
                BlockPos localPos = worldCord.add(checkPos);
                if (!(world.getBlock(localPos.getX(), localPos.getY(), localPos.getZ()) == Blocks.air)) {
                    possibleBlocks.add(checkPos);
                }
            }
        }
    }
}
