package tv.zeekdageek.chanciercubes.rewards.backported.giantRewards;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.rewards.BaseChancierBackport;
import tv.zeekdageek.chanciercubes.util.ChancierUtil;
import tv.zeekdageek.chanciercubes.util.Scheduler;
import tv.zeekdageek.chanciercubes.util.Task;
import tv.zeekdageek.chanciercubes.util.math.BlockPos;

public class FireworkShowReward extends BaseChancierBackport
{
    private static final String rewardName = "firework_show";
    public EnumBackportValues rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    public FireworkShowReward() {
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
        return "Make it night and start the fireworks. Backport from Chance Cubes 1.18.x";
    }

    @Override
    public String getDefaultState() {
        return EnumBackportValues.ENABLED.toString();
    }

    /**
     * @param world  {@link World}
     * @param x      int
     * @param y      int
     * @param z      int
     * @param player {@link EntityPlayer}
     */
    @Override
    public void trigger(World world, int x, int y, int z, EntityPlayer player) {
        //RewardsUtil.executeCommand(world, player, pos, "/time set 15000");

        long newtime = ChancierUtil.setTime(world, 15000);
        ChancierCubes.info(String.format("New time is %d.", newtime));

        //stage1(world, new BlockPos(x, y, z));
    }

    public void stage1(World world, BlockPos pos)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_1", 200, 5)
        {
            double angle = 0;

            @Override
            public void callback()
            {
                stage2(world, pos);
            }

            @Override
            public void update() {
                angle += 0.5;
                spawnFirework(world, pos.getX() + ((angle / 3f) * Math.cos(angle)), pos.getY(), pos.getZ() + ((angle / 3f) * Math.sin(angle)));
                spawnFirework(world, pos.getX() + ((angle / 3f) * Math.cos(angle + Math.PI)), pos.getY(), pos.getZ() + ((angle / 3f) * Math.sin(angle + Math.PI)));
            }

        });
    }

    public void stage2(World world, BlockPos pos)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_2", 200, 5)
        {
            double tick = 0;

            @Override
            public void callback()
            {
                stage3(world, pos);
            }

            @Override
            public void update()
            {
                tick += 0.5;
                spawnFirework(world, pos.getX() + (tick - 20), pos.getY(), pos.getZ() + 1);
                spawnFirework(world, pos.getX() + (20 - tick), pos.getY(), pos.getZ() - 1);
            }

        });
    }

    public void stage3(World world, BlockPos pos)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_2", 200, 3)
        {

            @Override
            public void callback() {}

            @Override
            public void update() {
                spawnFirework(world, pos.getX() + (ChancierCubes.Rand.nextInt(10) - 5), pos.getY(), pos.getZ() + (ChancierCubes.Rand.nextInt(10) - 5));
            }

        });
    }

    public void spawnFirework(World level, double x, double y, double z)
    {
        level.spawnEntityInWorld(new EntityFireworkRocket(level, x, y, z, ChancierUtil.getRandomFirework()));
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
