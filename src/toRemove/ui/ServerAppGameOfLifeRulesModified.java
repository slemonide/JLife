package toRemove.ui;

import server.exceptions.InvalidDensityException;
import server.exceptions.InvalidDimensionException;
import server.model.Position;
import server.generator.WorldGenerator;
import server.model.World;
import server.rules.NeighbourhoodCellular;
import server.ui.ConsoleUI;

import java.util.HashSet;
import java.util.Set;

/**
 * @author      Danil Platonov <slemonide@gmail.com>
 * @version     0.1
 * @since       0.1
 *
 * Main app
 */
public class ServerAppGameOfLifeRulesModified {
    public static void main(String[] args) {
        System.out.print("Starting server... ");

        NeighbourhoodCellular gameOfLife = new NeighbourhoodCellular();
        gameOfLife.setLowerBound(1);
        gameOfLife.setUpperBound(3);

        Set<Position> neighbourhood = new HashSet<>();

        neighbourhood.add(new Position(1,0,-1));
        neighbourhood.add(new Position(1,0,0));
        neighbourhood.add(new Position(1,0,1));
        neighbourhood.add(new Position(0,0,-1));
        neighbourhood.add(new Position(0,0,1));
        neighbourhood.add(new Position(-1,0,-1));
        neighbourhood.add(new Position(-1,0,0));
        neighbourhood.add(new Position(-1,0,1));

        gameOfLife.setNeighbourhood(neighbourhood);

        //World.getInstance().setRule(gameOfLife);

        Thread worldThread = new Thread(World.getInstance());

        // Generate
        try {
            WorldGenerator.generate(0.1, 40, 20, 40);
        } catch (InvalidDensityException | InvalidDimensionException e) {
            e.printStackTrace();
        }

        //worldThread.start();
        System.out.println("OK");

        World.getInstance().addObserver(ConsoleUI.getInstance());
        World.getInstance().addObserver(PopulationGraphGUI.getInstance());
        //World.getInstance().addObserver(VisualGUI.getInstance());

        // Launch all windows
        //Application.launch(PopulationGraphGUI.class, args);
        VisualGUI.main(args);
    }
}
