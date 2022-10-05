package tv.zeekdageek.chanciercubes.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import tv.zeekdageek.chanciercubes.ChancierCubes;

public class ChancierUtil
{

    public static int getRandomColor() {
        return (int) (0xFFFFFF * ChancierCubes.Rand.nextDouble()) | 0xFF000000;
    }

    public static ItemStack getRandomFirework() {
        ItemStack stack = new ItemStack(Items.fireworks);
        NBTTagCompound data = new NBTTagCompound();
        data.setInteger("Flight", ChancierCubes.Rand.nextInt(3) + 1);

        NBTTagList explosionList = new NBTTagList();

        for (int i = 0; i <= ChancierCubes.Rand.nextInt(2); i++) {
            NBTTagCompound explosionData = new NBTTagCompound();
            explosionData.setInteger("Type", ChancierCubes.Rand.nextInt());
            explosionData.setBoolean("Flicker", ChancierCubes.Rand.nextBoolean());
            explosionData.setBoolean("Trail", ChancierCubes.Rand.nextBoolean());
            int[] colors = new int[ChancierCubes.Rand.nextInt(2) + 1];

            for (int j = 0; j < colors.length; j++) {
                colors[j] = getRandomColor();
            }

            explosionData.setIntArray("Colors", colors);
            explosionList.appendTag(explosionData);
        }

        data.setTag("Explosions", explosionList);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Fireworks", data);

        stack.setTagCompound(nbt);

        return stack;
    }

    /**
     * Changes the time relative to the current day. No more going back to day 0
     * @param world World
     * @param timeTicks long Relative time to the day
     * @return long The new time
     */
    public static long setTime(World world, int timeTicks) {
        long currentTime = world.getWorldTime();
        double currentDay = Math.floor(currentTime / 24000);

        long newTime = currentTime * 24000 + timeTicks;

        if (newTime < currentTime)
            newTime += 24000;

        world.setWorldTime((long) (currentDay * 24000 + timeTicks));

        return world.getWorldTime();
    }
}
