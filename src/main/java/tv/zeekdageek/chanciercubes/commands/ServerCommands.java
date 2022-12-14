package tv.zeekdageek.chanciercubes.commands;

import chanceCubes.registry.ChanceCubeRegistry;
import chanceCubes.registry.GiantCubeRegistry;
import chanceCubes.rewards.defaultRewards.IChanceCubeReward;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ServerCommands implements ICommand {
    private final List<String> aliases = new ArrayList<>();

    private final int OP_ACCESS = 2;

    public ServerCommands() {
        this.aliases.add("ChancierCubes");
        this.aliases.add("crc");
    }

    @Override
    public String getCommandName() {
        return "chanciercubes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName() + " <testreward>";
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        if (args.length > 0 && (args[0].equalsIgnoreCase("testreward") || args[0].equalsIgnoreCase("tr"))) {

            if (!sender.canCommandSenderUseCommand(OP_ACCESS, this.getCommandName())) {
                denyCommandAccess(sender, args);
                return;
            }

            World world = sender.getEntityWorld();
            EntityPlayer player = (EntityPlayer) sender;

            if (args.length > 1) {
                String rewardName = args[1];

                IChanceCubeReward reward = ChanceCubeRegistry.INSTANCE.getRewardByName(rewardName);
                IChanceCubeReward giantReward = GiantCubeRegistry.INSTANCE.getRewardByName(rewardName);

                if (reward == null && giantReward == null) {
                    String newRewardName = String.format("chanciercubes:%s", rewardName);
                    reward = ChanceCubeRegistry.INSTANCE.getRewardByName(newRewardName);
                    giantReward = GiantCubeRegistry.INSTANCE.getRewardByName(newRewardName);
                }

                if (reward == null && giantReward == null) {
                    String newRewardName = String.format("chancecubes:%s", rewardName);
                    reward = ChanceCubeRegistry.INSTANCE.getRewardByName(newRewardName);
                    giantReward = GiantCubeRegistry.INSTANCE.getRewardByName(newRewardName);
                }

                if (reward == null && giantReward == null) {
                    sender.addChatMessage(new ChatComponentText(String.format("Unable to find a reward with the name %s.", args[1])));
                } else if (reward != null) {
                    sender.addChatMessage(new ChatComponentText(String.format("Triggering standard reward: %s", reward.getName())));
                    reward.trigger(world, (int) player.posX, (int) player.posY, (int) player.posZ, player);
                } else {
                    sender.addChatMessage(new ChatComponentText(String.format("Triggering giant reward: %s", giantReward.getName())));
                    giantReward.trigger(world, (int) player.posX, (int) player.posY, (int) player.posZ, player);
                }

            } else {

                sender.addChatMessage(new ChatComponentText("Simulating random reward."));

                ChanceCubeRegistry.INSTANCE.triggerRandomReward(world, (int) player.posX, (int) player.posY, (int) player.posZ, player, 0);

            }

        }

    }

    public void denyCommandAccess(ICommandSender sender, String[] args) {
        sender.addChatMessage(new ChatComponentText("Unable to use command."));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] part) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
