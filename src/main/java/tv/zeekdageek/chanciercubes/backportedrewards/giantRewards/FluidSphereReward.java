package tv.zeekdageek.chanciercubes.backportedrewards.giantRewards;

import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.rewards.defaultRewards.IChanceCubeReward;
import chanceCubes.rewards.rewardparts.OffsetBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import tv.zeekdageek.chanciercubes.ChancierCubes;
import tv.zeekdageek.chanciercubes.Tags;

import java.util.ArrayList;
import java.util.List;

public class FluidSphereReward implements IChanceCubeReward
{
    @Override
    public void trigger(World world, int posX, int posY, int posZ, EntityPlayer player) {
        ChancierCubes.info("Test reload.");

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
        return Tags.MODID + ":fluid_sphere";
    }
}
