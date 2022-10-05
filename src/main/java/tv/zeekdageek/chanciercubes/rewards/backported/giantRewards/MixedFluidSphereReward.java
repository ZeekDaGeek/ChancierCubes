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

public class MixedFluidSphereReward extends BaseChancierBackport
{
    private static final String rewardName = "mixed_fluid_sphere";
    public EnumBackportValues rewardEnabled;

    public MixedFluidSphereReward() {
        super();
    }

    @Override
    public String getReplacedName() {
        return "chancecubes:Fluid_Tower";
    }

    @Override
    public boolean isGiantcube() {
        return true;
    }

    @Override
    public String getConfigName() {
        return rewardName;
    }

    @Override
    public String getDesc() {
        return "Creates a glass orb, each block inside a randomized fluid. Backported from ChanceCubes 1.18.x.";
    }

    @Override
    public String getDefaultState() {
        return EnumBackportValues.REPLACE.toString();
    }

    @Override
    public void trigger(World world, int x, int y, int z, EntityPlayer entityPlayer) {
        List<OffsetBlock> blocks = new ArrayList<>();

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
                            if(i == 0)
                            {
                                OffsetBlock osb = new OffsetBlock(xx, yy, zz, Blocks.glass, false, delay);
                                //osb.setBlockState(Blocks.GLASS.defaultBlockState());
                                blocks.add(osb);
                            }
                            else
                            {
                                Fluid fluid = ChanceCubeRegistry.getRandomFluid();
                                OffsetBlock osb = new OffsetBlock(xx, yy, zz, fluid.getBlock(), false, delay);
                                blocks.add(osb);
                            }
                            delay++;

                        }
                    }
                }
            }
            delay += 10;
        }

        for(OffsetBlock b : blocks)
            b.spawnInWorld(world, x, y, z);
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
