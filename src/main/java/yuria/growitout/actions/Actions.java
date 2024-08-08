package yuria.growitout.actions;

import com.google.common.collect.ImmutableSet;
import net.minecraftforge.fml.common.Loader;
import yuria.growitout.Twerk;
import yuria.growitout.actions.compats.AgricraftAction;
import yuria.growitout.actions.compats.IndustrialCropsAction;
import yuria.growitout.actions.compats.MysticalAction;
import yuria.growitout.actions.vanilla.BoneMeal;
import yuria.growitout.actions.vanilla.CropBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public enum Actions {
    INSTANCE;

    ImmutableSet<Action> actions_immu;
    final Set<Supplier<Action>> possibleActions = new HashSet<>();

    Actions()
    {
        register(BoneMeal::new);
        register(CropBlock::new);
        register(MysticalAction::new);
        register(AgricraftAction::new);

        if (Loader.isModLoaded("ic2")) {
            register(IndustrialCropsAction::new); // FUCKING SPECIAL CASE
        }
    }

    public void register(Supplier<Action> actionSupplier)
    {
        Twerk.LOGGER.info("Registering {}", actionSupplier.get().getClass().getName());
        this.possibleActions.add(actionSupplier);
    }

    public void setup()
    {
        Set<Action> actions_hash = new HashSet<>();
        for (Supplier<Action> action : this.possibleActions)
        {
            Action trueAction = action.get();
            Twerk.LOGGER.info("Trying to push action: {}, available: {}", action.getClass().getName(), trueAction.isAvailable().getAsBoolean());
            if (trueAction.isAvailable().getAsBoolean())
            {
                actions_hash.add(trueAction);
                Twerk.LOGGER.info("Registered action: {}", action.getClass().getName());
            } else {
                Twerk.LOGGER.info("Failed to register action: {}", action.getClass().getName());
            }
        }
        this.actions_immu = ImmutableSet.copyOf(actions_hash);
    }

    public static Actions get()
    {
        return INSTANCE;
    }

    public ImmutableSet<Action> getActions()
    {
        return actions_immu;
    }
}
