package tv.zeekdageek.chanciercubes.rewards.backported.giantRewards;

import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.rewards.rewardparts.OffsetBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import tv.zeekdageek.chanciercubes.Tags;
import tv.zeekdageek.chanciercubes.config.EnumBackportValues;
import tv.zeekdageek.chanciercubes.rewards.BaseChancierBackport;

import java.util.ArrayList;
import java.util.List;

public class FluidSphereReward extends BaseChancierBackport
{
    private static final String rewardName = "fluid_sphere";
    public EnumBackportValues rewardEnabled;

    public FluidSphereReward() {
        super();
    }

    @Override
    public boolean isGiantCube() {
        return true;
    }

    @Override
    public String getReplacedName() {
        return "chancecube:Fluid_Tower";
    }

    @Override
    public String getConfigName() {
        return rewardName;
    }

    @Override
    public String getDesc() {
        return "Newer version of chancecube:Fluid_Tower";
    }

    @Override
    public String getDefaultState() {
        return EnumBackportValues.ENABLED.toString();
    }

    @Override
    public void trigger(World world, int posX, int posY, int posZ, EntityPlayer player) {
        List<OffsetBlock> blocks = new ArrayList<>();

        Fluid fluid = ChanceCubeRegistry.getRandomFluid();

        int delay = 0;
        for(int i = 0; i <= 5; i++)
        {
            for(int yy = -5; yy < 6; yy++)
            {
                for(int zz = -5; zz < 6; zz++)
                {
                    for(int xx = -5; xx < 6; xx++)
                    {
                        double dist = Math.sqrt(Math.abs(xx * xx + yy * yy + zz * zz));
                        if(dist <= 5 - i && dist > 5 - (i + 1))
                        {
                            OffsetBlock osb;
                            if(i == 0)
                            {
                                osb = new OffsetBlock(xx, yy, zz, Blocks.glass, false, delay);
                                //osb.setBlockState(Blocks.glass);
                            }
                            else
                            {
                                osb = new OffsetBlock(xx, yy, zz, fluid.getBlock(), false, delay);
                            }
                            blocks.add(osb);
                            delay++;

                        }
                    }
                }
            }
            delay += 10;
        }

        for(OffsetBlock b : blocks) {
            b.spawnInWorld(world, posX, posY, posZ);
        }
    }

    @Override
    public int getChanceValue() {
        return 15;
    }


    @Override
    public String getName() {
        return Tags.MODID + ":" + rewardName;
    }
}
