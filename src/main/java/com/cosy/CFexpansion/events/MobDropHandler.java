package com.cosy.CFexpansion.events; // Jeśli zrobiłeś to w innej paczce, zmień to!

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import com.cosy.CFexpansion.CFexpansion;

public class MobDropHandler {

    //szukanie eventu
    @SubscribeEvent
    public void onMobDrop(LivingDropsEvent event) {

        //sprawdza czy mob jest zabity      V
        if (event.entityLiving instanceof EntityZombie) {

            //szansa w % od 1-100                                tutaj V
            if (event.entityLiving.worldObj.rand.nextInt(100) < 20) {

                //przedmiot dropu
                ItemStack customItem = new ItemStack(CFexpansion.itemCloth, 1);

                //rejestracja gdzie mob byl zabity i spawn itemu
                EntityItem drop = new EntityItem(
                        event.entityLiving.worldObj,
                        event.entityLiving.posX,
                        event.entityLiving.posY,
                        event.entityLiving.posZ,
                        customItem
                );

                //rejestracja dropow moba
                event.drops.add(drop);
            }
        }
    }
}