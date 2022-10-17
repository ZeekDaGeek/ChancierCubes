package tv.zeekdageek.chanciercubes.mixins;

import chanceCubes.rewards.rewardparts.CommandPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tv.zeekdageek.chanciercubes.util.ChancierUtil;

@Mixin(chanceCubes.rewards.type.CommandRewardType.class)
public class CommandRewardType {

    @Inject(
        at = @At("HEAD"),
        method = "triggerCommand(LchanceCubes/rewards/rewardparts/CommandPart;Lnet/minecraft/world/World;IIILnet/minecraft/entity/player/EntityPlayer;)V",
        cancellable = true,
        remap = false
    )
    private void triggerCommand(CommandPart command, World world, int x, int y, int z, EntityPlayer player, CallbackInfo ci) {

        String[] splitCommand = command.getRawCommand().split(" ");

        // if the command is to change the time prevent it and cancel it.
        if (splitCommand.length >= 3 &&
                (splitCommand[0].equals("/time") || splitCommand[0].equals("time")) &&
                splitCommand[1].equals("set"))
        {

            int newTime = 0;
            boolean timeChanged = false;

            if (splitCommand[2].equalsIgnoreCase("day")) {
                newTime = 1000;
                timeChanged = true;
            } else if (splitCommand[2].equalsIgnoreCase("night")) {
                newTime = 13000;
                timeChanged = true;
            } else if (splitCommand[2].equalsIgnoreCase("noon")) {
                newTime = 6000;
                timeChanged = true;
            } else if (splitCommand[2].equalsIgnoreCase("midnight")) {
                newTime = 18000;
                timeChanged = true;
            } else if (splitCommand[2].equalsIgnoreCase("sunrise")) {
                newTime = 23000;
                timeChanged = true;
            } else if (splitCommand[2].equalsIgnoreCase("sunset")) {
                newTime = 12000;
                timeChanged = true;
            } else if (splitCommand[2].matches("[0-9]+")) {
                newTime = Integer.parseInt(splitCommand[2]);
                timeChanged = true;
            }

            if (timeChanged) {
                ChancierUtil.setTime(world, newTime);
                ci.cancel();
            }

        }
    }

}

