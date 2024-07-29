package yuria.twerk.actions;

import com.google.common.collect.ImmutableSet;
import yuria.twerk.Twerk;
import yuria.twerk.actions.compats.AgricraftAction;
import yuria.twerk.actions.compats.MysticalAction;
import yuria.twerk.actions.vanilla.BoneMeal;
import yuria.twerk.actions.vanilla.CropBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public enum actions {
    INSTANCE;

    ImmutableSet<action> actions_immu;
    final Set<Supplier<action>> possibleActions = new HashSet<>();

    actions()
    {
        register(BoneMeal::new);
        register(CropBlock::new);
        register(MysticalAction::new);
        register(AgricraftAction::new);
    }

    public void register(Supplier<action> actionSupplier)
    {
        this.possibleActions.add(actionSupplier);
    }

    public void setup()
    {
        Set<action> actions_hash = new HashSet<>();
        for (Supplier<action> action : this.possibleActions)
        {
            action trueAction = action.get();
            Twerk.LOGGER.info("Trying to register action: {}", action.getClass().getSimpleName());
            if (trueAction.isAvailable().getAsBoolean())
            {
                actions_hash.add(trueAction);
                Twerk.LOGGER.info("Registered action: {}", action.getClass().getSimpleName());
            } else {
                Twerk.LOGGER.info("Failed to register action: {}", action.getClass().getSimpleName());
            }
        }
        this.actions_immu = ImmutableSet.copyOf(actions_hash);
    }

    public static actions get()
    {
        return INSTANCE;
    }

    public ImmutableSet<action> getActions()
    {
        return actions_immu;
    }
}
