import edu.curtin.comp2003.rover.core.MarsRover;
import edu.curtin.comp2003.roverapi.EarthComm;
import edu.curtin.comp2003.roverapi.EngineSystem;
import edu.curtin.comp2003.roverapi.Sensors;
import edu.curtin.comp2003.roverapi.SoilAnalyser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Launch {

    EarthComm earthComm = new EarthComm();
    EngineSystem engine = new EngineSystem();
    Sensors sensor = new Sensors();
    SoilAnalyser soilAnalyser = new SoilAnalyser();


    MarsRover rover = new MarsRover(earthComm, engine, sensor, soilAnalyser);

//    @Test
}
