package nz.ac.vuw.ecs.group16.openrocketextension;

import net.sf.openrocket.simulation.FlightEvent;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

/**
 * A simulation listener that stops the simulation after a specified number of steps or after a
 * specified about of simulation time.
 * 
 * @author David, Finn
 */
public class LandingSimulationListener extends AbstractSimulationListener {

  static SimulationStatus simStatus = null;

  public LandingSimulationListener() {
    super();
  }

  @Override
  public boolean handleFlightEvent(SimulationStatus status, FlightEvent event)
      throws SimulationException {

    if (event.getType() == FlightEvent.Type.SIMULATION_END) {
      LandingSimulationListener.simStatus = status;
    }

    return true;
  }
}
