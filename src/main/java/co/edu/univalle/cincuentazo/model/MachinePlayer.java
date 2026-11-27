package co.edu.univalle.cincuentazo.model;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MachinePlayer extends Player {
    private final Random random = new Random();

    public MachinePlayer(String name) {
        super(name);
    }

    public Optional<Move> chooseMove(Table table) {
        List<Move> moves = getPlayableMoves(table);
        if (moves.isEmpty()) {
            return  Optional.empty();
        }
        return Optional.of(moves.get(random.nextInt(moves.size())));
     }
}
