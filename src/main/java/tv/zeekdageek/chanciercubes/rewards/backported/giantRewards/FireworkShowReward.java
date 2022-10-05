package tv.zeekdageek.chanciercubes.rewards.backported.giantRewards;

import chanceCubes.util.RewardsUtil;
import chanceCubes.util.Scheduler;
import chanceCubes.util.Task;
import ibxm.Player;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.rewards.BaseChancierBackport;
import tv.zeekdageek.chanciercubes.util.math.BlockPos;

public class FireworkShowReward extends BaseChancierBackport
{
    private static final String rewardName = "fluid_sphere";
    public EnumBackportValues rewardEnabled;

    /**
     * Register the reward and setup configs here.
     */
    public FireworkShowReward() {
        super();
    }

    /**
     * @return
     */
    @Override
    public String getReplacedName() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public boolean isGiantCube() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public String getConfigName() {
        return rewardName;
    }

    /**
     * @return
     */
    @Override
    public String getDesc() {
        return "Make it night and start the fireworks. Backport from Chance Cubes 1.18.x";
    }

    /**
     * @return
     */
    @Override
    public String getDefaultState() {
        return EnumBackportValues.ENABLED;
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
        boolean rule = world.getGameRules().getGameRuleBooleanValue("commandBlockOutput");
        ServerCommandManager cs = new ServerCommandManager();
        cs.executeCommand(player, "/time set 15000");
        world.getGameRules().setOrCreateGameRule("commandBlockOutput", String.valueOf(rule));

        stage1(world, new BlockPos(x, y, z), player);
    }

    public void stage1(World level, BlockPos pos, EntityPlayer player)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_1", 200, 5)
        {
            double angle = 0;

            @Override
            public void callback()
            {
                stage2(level, pos, player);
            }

            @Override
            public void update()
            {
                angle += 0.5;
                spawnFirework(level, pos.getX() + ((angle / 3f) * Math.cos(angle)), pos.getY(), pos.getZ() + ((angle / 3f) * Math.sin(angle)));
                spawnFirework(level, pos.getX() + ((angle / 3f) * Math.cos(angle + Math.PI)), pos.getY(), pos.getZ() + ((angle / 3f) * Math.sin(angle + Math.PI)));
            }

        });
    }

    public void stage2(ServerLevel level, BlockPos pos, Player player)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_2", 200, 5)
        {
            double tick = 0;

            @Override
            public void callback()
            {
                stage3(level, pos, player);
            }

            @Override
            public void update()
            {
                tick += 0.5;
                spawnFirework(level, pos.getX() + (tick - 20), pos.getY(), pos.getZ() + 1);
                spawnFirework(level, pos.getX() + (20 - tick), pos.getY(), pos.getZ() - 1);
            }

        });
    }

    public void stage3(ServerLevel level, BlockPos pos, Player player)
    {
        Scheduler.scheduleTask(new Task("Firework_Show_Task_Stage_2", 200, 3)
        {

            @Override
            public void callback()
            {

            }

            @Override
            public void update()
            {
                spawnFirework(level, pos.getX() + (RewardsUtil.rand.nextInt(10) - 5), pos.getY(), pos.getZ() + (RewardsUtil.rand.nextInt(10) - 5));
            }

        });
    }

    public void spawnFirework(World level, double x, double y, double z)
    {
        level.spawnEntityInWorld(new EntityFireworkRocket(level, x, y, z, RewardsUtil.getRandomFirework()));
    }

    /**
     * @return
     */
    @Override
    public int getChanceValue() {
        return super.getChanceValue();
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return super.getName();
    }
}
