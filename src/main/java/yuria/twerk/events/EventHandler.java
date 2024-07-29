package yuria.twerk.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yuria.twerk.Twerk;
import yuria.twerk.TwerkAction;

public class EventHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving().world.isRemote || !(event.getEntityLiving() instanceof EntityPlayer))
        {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        TwerkAction.perform(player.world, player);
    }
}
